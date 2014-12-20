/*
 * $Id: AmUserRoleDao,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ganjp.am.model.AmUserRole;
import org.ganjp.core.Constants;
import org.ganjp.core.dao.BaseDAO;
import org.ganjp.core.db.base.Page;
import org.ganjp.core.util.SqlUtil;
import org.ganjp.core.util.StringUtil;
/**
 * {annotation}
 * 
 * @author ganjp
 * @version 1.0
 * @since 1.0
 */
public class AmUserRoleDao extends BaseDAO {
	/**
	 * add AmUserRole
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addAmUserRole(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildAddFullSql(columnNames, "am_user_role", "user_role_id");
		Object[] fullParamObjects = SqlUtil.buildAddFullParamValues(paramObjects,!columnNames[0].equalsIgnoreCase("user_role_id"));
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, fullParamObjects));
		this.update(fullSqlWithParam, fullParamObjects, true);
		log.info("add AmUserRole record success");
	}
	
	/**
	 * edit AmUserRole
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editAmUserRole(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildEditFullSql(columnNames, "am_user_role");
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, paramObjects));
		this.update(fullSqlWithParam, paramObjects, true);
		log.info("add AmUserRole record success");
	}
	
	/**
	 * delete AmUserRole records by primary key
	 * 
	 * @param userRoleId
	 */
	public void deleteAmUserRoleByPk(String userRoleId) {
		String sqlWithParam = "delete from am_user_role WHERE user_role_id=?";
		Object[] paramObjects = new Object[] {userRoleId};
		this.update(sqlWithParam, paramObjects, true);
		log.info("delete AmUserRole record success");
	}
	
	/**
	 * batch delete AmUserRole records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteAmUserRolesByPks(String pks) {
		if (StringUtil.isNotBlank(pks)) {
			String deleteSql = "delete from am_user_role WHERE user_role_id=?";
			this.batchDeleteByIdArr(deleteSql, pks.split(","), true);
			log.info("delete AmUserRole records success");
		}
	}
	
	/**
	 * get AmUserRole by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public AmUserRole getAmUserRoleByPk(final String pk) {
		String sqlWithParam = "SELECT * FROM am_user_role WHERE user_role_id=?";
		Object object = (Object)this.getObject(sqlWithParam, pk, AmUserRole.class, true);
		if (object!=null) {
			AmUserRole amUserRole = (AmUserRole) object;
			log.debug("select AmUserRole by pk = " + pk);
			return amUserRole;
		} else {
			log.error("select AmUserRole by pk = " + pk + " is null" );
			return null;
		}
	}
	
	/**
	 * get all AmUserRole records where data_state = 0 ORDER BY modify_timestamp DESC
	 * 
	 * @return
	 */
	public List getAll() {
		String sql = "SELECT * FROM am_user_role WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
		List amUserRoles = this.getObjectList(sql, AmUserRole.class, false);
		log.info("select All AmUserRole record success");
		return amUserRoles;
	}
	
	/**
	 * get all AmUserRole record Page where data_state = 0 ORDER BY modify_timestamp DESC
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize) {
		Page page = new Page();
		try {
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			String sql = "SELECT * FROM am_user_role WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
			page = this.getObjectPage(sql, AmUserRole.class, page, true);
			log.debug("select AmUserRole page records success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
	
	/**
	 * get all AmUserRole record Page where data_state = 0 with search condition ORDER BY modify_timestamp DESC
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param searchSqlAndValuesMap
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, Map searchSqlAndValuesMap) {
		if (searchSqlAndValuesMap == null) {
			return this.getAllPage(pageNo, pageSize);
		}
		Page page = new Page();
		try {
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			String sql = "SELECT * FROM am_user_role WHERE data_state = " + Constants.DATA_STATE_NORMAL + 
					searchSqlAndValuesMap.get(SqlUtil.SQL_SUFFIX).toString() + " ORDER BY modify_timestamp DESC";
			Object[] objectValues = (Object[])searchSqlAndValuesMap.get(SqlUtil.VALUES);
			page = this.getObjectPage(sql, objectValues, AmUserRole.class, page, true);
			log.debug("select AmUserRole page records with search condition success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
	
	public List getAmUserRoleByUserId(final String userId, boolean isCloseConnection) {
		String sqlWithParam = "SELECT * FROM am_user_role WHERE user_id=? and data_state = " + Constants.DATA_STATE_NORMAL;
		List amUserRoleList = (List)this.getObjectList(sqlWithParam, userId, AmUserRole.class, isCloseConnection);
		log.info("select AmUserRoles record by userId:" + userId + " success");
		return amUserRoleList;
	}
	
	public Map getRoleIdAndUserRoleIdMapByUserId(final String userId, boolean isCloseConnection) {
		List amUserRoles = this.getAmUserRoleByUserId(userId, isCloseConnection);
		if (amUserRoles == null || amUserRoles.isEmpty()) {
			return null;
		}
		Map roleIds = new HashMap();
		for (Iterator iterator = amUserRoles.iterator(); iterator.hasNext();) {
			AmUserRole AmUserRole = (AmUserRole) iterator.next();
			roleIds.put(AmUserRole.getRoleId(), AmUserRole.getUserRoleId());
		}
		return roleIds;
	}
	
	public void batchEditAmUserRole(final String columnNamesWithComma, final List paramObjectArrs, final boolean isCloseConnection) {
		if (paramObjectArrs!=null && !paramObjectArrs.isEmpty()) {
			String fullSqlWithParam = SqlUtil.buildEditFullSql(columnNamesWithComma.split(","), "am_user_role");
			super.batchExecute(fullSqlWithParam, paramObjectArrs, isCloseConnection);
			log.debug("batch add AmUserRole success");
		} else {
			log.debug("no AmUserRole record needs to add");
		}
	}
	
	public void batchAddUserRole(final String columnNamesWithComma, final List paramObjectArrs, final boolean isCloseConnection) {
		if (paramObjectArrs!=null && !paramObjectArrs.isEmpty()) {
			String fullSqlWithParam = SqlUtil.buildAddFullSql(columnNamesWithComma.split(","), "am_user_role", "user_role_id");
			List fullParamObjectArrs = new ArrayList();
			for (Iterator iterator = paramObjectArrs.iterator(); iterator.hasNext();) {
				Object[] paramObjectArr = (Object[]) iterator.next();
				Object[] fullParamObjects = SqlUtil.buildAddFullParamValues(paramObjectArr, columnNamesWithComma.indexOf("user_role_id")==-1);
				fullParamObjectArrs.add(fullParamObjects);
			}
			super.batchExecute(fullSqlWithParam, fullParamObjectArrs, isCloseConnection);
			log.debug("batch add UserRole success");
		} else {
			log.debug("no UserRole record needs to add");
		}
	}
}
/**
  * userRoleId - user_role_id
 * roleId - role_id
 * userId - user_id
 * createDateTime - create_date_time
 * modifyTimestamp - modify_timestamp
 * dataState - data_state
 */