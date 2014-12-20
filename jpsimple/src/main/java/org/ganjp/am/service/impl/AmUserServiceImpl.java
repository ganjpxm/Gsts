/*
 * $Id: AmUserServiceImpl,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.service.impl;

import org.ganjp.am.model.AmUser;
import org.ganjp.am.service.AmUserService;
import org.ganjp.am.dao.AmUserDao;
import org.ganjp.core.service.BaseService;
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
public class AmUserServiceImpl extends BaseService implements AmUserService {
	
	/**
	 * add AmUser
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addAmUser(final String[] columnNames, final Object[] paramObjects) {
		amUserDao().addAmUser(columnNames, paramObjects);
	}
	
	/**
	 * edit AmUser
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editAmUser(final String[] columnNames, final Object[] paramObjects) {
		amUserDao().editAmUser(columnNames, paramObjects);
	}
	
	/**
	 * delete AmUser records by primary key
	 * 
	 * @param userId
	 */
	public void deleteAmUserByPk(String userId) {
		amUserDao().deleteAmUserByPk(userId);
	}
	
	/**
	 * batch delete AmUser records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteAmUsersByPks(String pks) {
		amUserDao().deleteAmUsersByPks(pks);
	}
	
	/**
	 * get AmUser by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public AmUser getAmUserByPk(final String pk) {
		return amUserDao().getAmUserByPk(pk);
	}
	
	/**
	 * get all AmUser records 
	 * 
	 * @return
	 */
	public List getAll() {
		return amUserDao().getAll();
	}
	
	/**
	 * get all AmUser record Page 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, final Map searchSqlAndValuesMap) {
		return amUserDao().getAllPage(pageNo, pageSize, searchSqlAndValuesMap);
	}
	
	public AmUserDao amUserDao() {
		return new AmUserDao();
	}
}