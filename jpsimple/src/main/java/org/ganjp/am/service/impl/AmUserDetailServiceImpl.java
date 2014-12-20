/*
 * $Id: AmUserDetailServiceImpl,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.service.impl;

import org.ganjp.am.model.AmUserDetail;
import org.ganjp.am.service.AmUserDetailService;
import org.ganjp.am.dao.AmUserDetailDao;
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
public class AmUserDetailServiceImpl extends BaseService implements AmUserDetailService {
	
	/**
	 * add AmUserDetail
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addAmUserDetail(final String[] columnNames, final Object[] paramObjects) {
		amUserDetailDao().addAmUserDetail(columnNames, paramObjects);
	}
	
	/**
	 * edit AmUserDetail
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editAmUserDetail(final String[] columnNames, final Object[] paramObjects) {
		amUserDetailDao().editAmUserDetail(columnNames, paramObjects);
	}
	
	/**
	 * delete AmUserDetail records by primary key
	 * 
	 * @param userDetailId
	 */
	public void deleteAmUserDetailByPk(String userDetailId) {
		amUserDetailDao().deleteAmUserDetailByPk(userDetailId);
	}
	
	/**
	 * batch delete AmUserDetail records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteAmUserDetailsByPks(String pks) {
		amUserDetailDao().deleteAmUserDetailsByPks(pks);
	}
	
	/**
	 * get AmUserDetail by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public AmUserDetail getAmUserDetailByPk(final String pk) {
		return amUserDetailDao().getAmUserDetailByPk(pk);
	}
	
	/**
	 * get all AmUserDetail records 
	 * 
	 * @return
	 */
	public List getAll() {
		return amUserDetailDao().getAll();
	}
	
	/**
	 * get all AmUserDetail record Page 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, final Map searchSqlAndValuesMap) {
		return amUserDetailDao().getAllPage(pageNo, pageSize, searchSqlAndValuesMap);
	}
	
	public AmUserDetailDao amUserDetailDao() {
		return new AmUserDetailDao();
	}
}