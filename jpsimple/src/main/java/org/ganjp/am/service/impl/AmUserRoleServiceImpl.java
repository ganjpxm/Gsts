/*
 * $Id: AmUserRoleServiceImpl,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ganjp.am.dao.AmUserRoleDao;
import org.ganjp.am.model.AmUserRole;
import org.ganjp.am.service.AmUserRoleService;
import org.ganjp.core.db.base.Page;
import org.ganjp.core.service.BaseService;
import org.ganjp.core.util.StringUtil;

/**
 * {annotation}
 * 
 * @author ganjp
 * @version 1.0
 * @since 1.0
 */
public class AmUserRoleServiceImpl extends BaseService implements AmUserRoleService {
	
	/**
	 * add AmUserRole
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addAmUserRole(final String[] columnNames, final Object[] paramObjects) {
		amUserRoleDao().addAmUserRole(columnNames, paramObjects);
	}
	
	/**
	 * edit AmUserRole
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editAmUserRole(final String[] columnNames, final Object[] paramObjects) {
		amUserRoleDao().editAmUserRole(columnNames, paramObjects);
	}
	
	/**
	 * delete AmUserRole records by primary key
	 * 
	 * @param userRoleId
	 */
	public void deleteAmUserRoleByPk(String userRoleId) {
		amUserRoleDao().deleteAmUserRoleByPk(userRoleId);
	}
	
	/**
	 * batch delete AmUserRole records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteAmUserRolesByPks(String pks) {
		amUserRoleDao().deleteAmUserRolesByPks(pks);
	}
	
	/**
	 * get AmUserRole by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public AmUserRole getAmUserRoleByPk(final String pk) {
		return amUserRoleDao().getAmUserRoleByPk(pk);
	}
	
	/**
	 * get all AmUserRole records 
	 * 
	 * @return
	 */
	public List getAll() {
		return amUserRoleDao().getAll();
	}
	
	/**
	 * get all AmUserRole record Page 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, final Map searchSqlAndValuesMap) {
		return amUserRoleDao().getAllPage(pageNo, pageSize, searchSqlAndValuesMap);
	}
	
	/**
	 * batch add AmUserRole
	 * 
	 * @param roleIdsWithComma
	 * @param userId
	 */
	public void batchAddUserRole(final String roleIdsWithComma, final String userId) {
		List objectArrs = new ArrayList();
		if (StringUtil.isNotBlank(roleIdsWithComma)) {
			String[] roleIds = roleIdsWithComma.split(",");
			for (int i = 0; i < roleIds.length; i++) {
				String roleId = roleIds[i];
				objectArrs.add(new String[]{userId,roleId});
			}
			amUserRoleDao().batchAddUserRole("user_id,role_id", objectArrs, true);
		}
	}
	
	/**
	 * get roleIds with comma by userId
	 * 
	 * @param userId
	 * @return
	 */
	public String getRoleIdsWithCommaByUserId(final String userId) {
		List amUserRoles = amUserRoleDao().getAmUserRoleByUserId(userId, true);
		if (amUserRoles==null || amUserRoles.isEmpty()) {
			return "";
		}
		String roleIdsWithComma = "";
		for (Iterator iterator = amUserRoles.iterator(); iterator.hasNext();) {
			AmUserRole amUserRole = (AmUserRole) iterator.next();
			roleIdsWithComma += amUserRole.getRoleId() + ",";
		}
		return roleIdsWithComma.substring(0, roleIdsWithComma.length()-1);
	}
	
	public AmUserRoleDao amUserRoleDao() {
		return new AmUserRoleDao();
	}
}