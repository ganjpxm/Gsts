/*
 * $Id: AmUserRoleService.java,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.service;

import org.ganjp.am.model.AmUserRole;
import org.ganjp.core.db.base.Page;

import java.util.List;
import java.util.Map;

/**
 * {annotation}
 * 
 * @author ganjp
 * @version 1.0
 * @since 1.0
 */
public interface AmUserRoleService {
	/**
	 * add AmUserRole
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addAmUserRole(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * edit AmUserRole
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editAmUserRole(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * delete AmUserRole records by primary key
	 * 
	 * @param userRoleId
	 */
	public void deleteAmUserRoleByPk(String userRoleId);
	
	/**
	 * batch delete AmUserRole records by primary keys
	 * 
	 * @param pks
	 */
	public AmUserRole getAmUserRoleByPk(final String pk);
	
	/**
	 * get AmUserRole by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public void deleteAmUserRolesByPks(String pks);
	
	/**
	 * get all AmUserRole records 
	 * 
	 * @return
	 */
	public List getAll();
	
	/**
	 * get all AmUserRole record Page 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, final Map searchSqlAndValuesMap);
	
	/**
	 * batch add AmUserRole
	 * @param roleIdsWithComma
	 * @param userId
	 */
	public void batchAddUserRole(final String roleIdsWithComma, final String userId);
	
	/**
	 * get roleIds with comma by userId
	 * 
	 * @param userId
	 * @return
	 */
	public String getRoleIdsWithCommaByUserId(final String userId);
}