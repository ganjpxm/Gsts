/*
 * $Id: AmUserServiceImpl,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ganjp.am.dao.AmUserDao;
import org.ganjp.am.dao.AmUserRoleDao;
import org.ganjp.am.model.AmUser;
import org.ganjp.am.service.AmCommonService;
import org.ganjp.core.service.BaseService;
import org.ganjp.core.util.StringUtil;

/**
 * {annotation}
 * 
 * @author ganjp
 * @version 1.0
 * @since 1.0
 */
public class AmCommonServiceImpl extends BaseService implements AmCommonService {
	
	/**
	 * edit AmUser, add AmUserRole and delete AmUserRole
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 * @param roleIdsWithComma '1234567890123456789012345679012,1234567890123456789012345679013'
	 */
	public void editAmUserAndAmUserRole(final String[] columnNames, final Object[] paramObjects,final String roleIdsWithComma) {
		String userId = (String)paramObjects[paramObjects.length-1];
		amUserDao().openTransaction();
		amUserDao().editAmUser(columnNames, paramObjects);
		String[] roleIds = null;
		if (StringUtil.isNotBlank(roleIdsWithComma)) {
			roleIds = roleIdsWithComma.split(",");
		}
		List addUserRoles = new ArrayList();
		String removeUserRoleIds = "";
		Map roleIdAndUserRoleIds = amUserRoleDao().getRoleIdAndUserRoleIdMapByUserId(userId, false);
		if (roleIds!=null && roleIds.length>0) {
			if (roleIdAndUserRoleIds==null || roleIdAndUserRoleIds.isEmpty()) {
				for (int i = 0; i < roleIds.length; i++) {
					addUserRoles.add(new String[]{userId, roleIds[i]});
				}
			} else {
				for (Iterator iterator = roleIdAndUserRoleIds.keySet().iterator(); iterator.hasNext();) {
					String roleId = (String) iterator.next();
					if (roleIdsWithComma.indexOf(roleId)==-1) {
						removeUserRoleIds += (String)roleIdAndUserRoleIds.get(roleId)+",";
					}
				}
				for (int i = 0; i < roleIds.length; i++) {
					String roleId = roleIds[i];
					if (!roleIdAndUserRoleIds.containsKey(roleId)) {
						addUserRoles.add(new String[]{userId, roleId});
					}
				}
			}
		} else {
			if (roleIdAndUserRoleIds!=null && !roleIdAndUserRoleIds.isEmpty()) {
				for (Iterator iterator = roleIdAndUserRoleIds.keySet().iterator(); iterator.hasNext();) {
					String roleId = (String) iterator.next();
					removeUserRoleIds += (String)roleIdAndUserRoleIds.get(roleId)+",";
				}
			}
		}
		log.debug("get addUserRole = + " + addUserRoles.toString() + ", get removeUserRoleIds = " + removeUserRoleIds) ;
		if (!addUserRoles.isEmpty()) {
			amUserRoleDao().batchAddUserRole("user_id,role_id", addUserRoles, false);
		} 
		if (StringUtil.isNotBlank(removeUserRoleIds)) {
			amUserRoleDao().deleteAmUserRolesByPks(removeUserRoleIds);
		} 
		amUserDao().commit();
	}
	
	/**
	 * get amUser exist in AmUserRole
	 * 
	 * @param userCd
	 * @param password
	 * @return
	 */
	public AmUser getAmUserInAmUserRole(String userCd, String password) {
		return amUserDao().getAmUserInAmUserRole(userCd, password);
	}
	public AmUserDao amUserDao() {
		return new AmUserDao();
	}
	
	public AmUserRoleDao amUserRoleDao() {
		return new AmUserRoleDao();
	}
}