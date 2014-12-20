/*
 * $Id: AmRoleServiceImpl,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ganjp.am.dao.AmRoleDao;
import org.ganjp.am.model.AmRole;
import org.ganjp.am.service.AmRoleService;
import org.ganjp.core.db.base.Page;
import org.ganjp.core.service.BaseService;
import org.ganjp.core.util.JsonUtil;

/**
 * {annotation}
 * 
 * @author ganjp
 * @version 1.0
 * @since 1.0
 */
public class AmRoleServiceImpl extends BaseService implements AmRoleService {
	
	/**
	 * add AmRole
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addAmRole(final String[] columnNames, final Object[] paramObjects) {
		amRoleDao().addAmRole(columnNames, paramObjects);
	}
	
	/**
	 * edit AmRole
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editAmRole(final String[] columnNames, final Object[] paramObjects) {
		amRoleDao().editAmRole(columnNames, paramObjects);
	}
	
	/**
	 * delete AmRole records by primary key
	 * 
	 * @param roleId
	 */
	public void deleteAmRoleByPk(String roleId) {
		amRoleDao().deleteAmRoleByPk(roleId);
	}
	
	/**
	 * batch delete AmRole records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteAmRolesByPks(String pks) {
		amRoleDao().deleteAmRolesByPks(pks);
	}
	
	/**
	 * get AmRole by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public AmRole getAmRoleByPk(final String pk) {
		return amRoleDao().getAmRoleByPk(pk);
	}
	
	/**
	 * get all AmRole records 
	 * 
	 * @return
	 */
	public List getAll() {
		return amRoleDao().getAll();
	}
	
	/**
	 * get all AmRole record Page 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, final Map searchSqlAndValuesMap) {
		return amRoleDao().getAllPage(pageNo, pageSize, searchSqlAndValuesMap);
	}
	
	/**
	 * 
	 * @param newRecordArrStr []
	 * @param modifyRecordArrStr
	 * @param delNodeIds
	 */
	public void crudRoleTree(String newRecordArrStr, String modifyRecordArrStr, String delNodeIds) {
		try {
			amRoleDao().openTransaction();
			
			List newParamList = JsonUtil.getParamArrList(newRecordArrStr,"id,cd,name");
			amRoleDao().batchAddAmRole("role_id,role_cd,role_name",newParamList,false);
			
			List editParamArrList = JsonUtil.getParamArrList(modifyRecordArrStr,"cd,name,id");
			amRoleDao().batchEditAmRole("role_cd,role_name,role_id",editParamArrList,false);

			this.deleteAmRolesByPks(delNodeIds);
			amRoleDao().commit();
		} catch (Exception e) {
			amRoleDao().rollback();
			log.error("crudRoleTree() error:" + e.getMessage());
		} finally {
			amRoleDao().closeConnection();
		}
	}
	
	/**
	 * get role tree json data
	 * 
	 * @param rolePid
	 * @return
	 */
	public String getRoleTreeJsonNodes() {
		StringBuffer roleJsonStrBuff= new StringBuffer(JsonUtil.EMPTY_JSON_ARR_STR);
		List amRoleList =  amRoleDao().getAll();
		for (int i = 0; i < amRoleList.size(); i++) {
			AmRole amRole = (AmRole)amRoleList.get(i);
			String data = "roleId:id,roleCd:cd,roleName:name";
			StringBuffer jsonObjectStrBuffer = amRole.toJsonStrBuffer(data);
			JsonUtil.addJsonObjectItem(jsonObjectStrBuffer, "\"isParent\":false");
			JsonUtil.addJsonObject(roleJsonStrBuff, jsonObjectStrBuffer);
		}
		log.info("get role tree nodes json data success: " + roleJsonStrBuff.toString());
		return roleJsonStrBuff.toString();
	}
	
	public AmRoleDao amRoleDao() {
		return new AmRoleDao();
	}
	
	
}