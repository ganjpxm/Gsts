/*
 * $Id: AmRoleService.java,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.service;

import org.ganjp.am.model.AmRole;
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
public interface AmRoleService {
	/**
	 * add AmRole
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addAmRole(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * edit AmRole
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editAmRole(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * delete AmRole records by primary key
	 * 
	 * @param roleId
	 */
	public void deleteAmRoleByPk(String roleId);
	
	/**
	 * batch delete AmRole records by primary keys
	 * 
	 * @param pks
	 */
	public AmRole getAmRoleByPk(final String pk);
	
	/**
	 * get AmRole by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public void deleteAmRolesByPks(String pks);
	
	/**
	 * get all AmRole records 
	 * 
	 * @return
	 */
	public List getAll();
	
	/**
	 * get all AmRole record Page 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, final Map searchSqlAndValuesMap);
	
	/**
	 * 
	 * @param newRecordArrStr []
	 * @param modifyRecordArrStr
	 * @param delNodeIds
	 */
	public void crudRoleTree(String newRecordArrStr, String modifyRecordArrStr, String delNodeIds);
	
	/**
	 * get menu tree json data
	 * 
	 * @param menuPid
	 * @return
	 */
	public String getRoleTreeJsonNodes() ;
}