/*
 * $Id: AmUserService.java,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.service;

import org.ganjp.am.model.AmUser;
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
public interface AmUserService {
	/**
	 * add AmUser
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addAmUser(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * edit AmUser
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editAmUser(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * delete AmUser records by primary key
	 * 
	 * @param userId
	 */
	public void deleteAmUserByPk(String userId);
	
	/**
	 * batch delete AmUser records by primary keys
	 * 
	 * @param pks
	 */
	public AmUser getAmUserByPk(final String pk);
	
	/**
	 * get AmUser by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public void deleteAmUsersByPks(String pks);
	
	/**
	 * get all AmUser records 
	 * 
	 * @return
	 */
	public List getAll();
	
	/**
	 * get all AmUser record Page 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, final Map searchSqlAndValuesMap);
}