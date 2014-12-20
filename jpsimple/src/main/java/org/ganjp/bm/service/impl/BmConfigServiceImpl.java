/*
 * $Id: BmConfigServiceImpl.java,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.bm.service.impl;

import java.util.List;
import java.util.Map;

import org.ganjp.bm.dao.BmConfigDao;
import org.ganjp.bm.model.BmConfig;
import org.ganjp.bm.service.BmConfigService;
import org.ganjp.core.db.base.Page;
import org.ganjp.core.service.BaseService;

/**
 * {annotation}
 * 
 * @author ganjp
 * @version 1.0
 * @since 1.0
 */
public class BmConfigServiceImpl extends BaseService implements BmConfigService {
	/**
	 * add BmConfig
	 * 
	 * @param bmConfig
	 */
	public void addBmConfig(BmConfig bmConfig) {
		bmConfigDao().addBmConfig(bmConfig);
	}
	
	/**
	 * add BmConfig
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addBmConfig(final String[] columnNames, final Object[] paramObjects) {
		bmConfigDao().addBmConfig(columnNames, paramObjects);
	}
	
	/**
	 * edit BmConfig
	 * 
	 * @param bmConfig
	 */
	public void editBmConfig(BmConfig bmConfig) {
		bmConfigDao().editBmConfig(bmConfig);
	}
	
	/**
	 * edit BmConfig
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editBmConfig(final String[] columnNames, final Object[] paramObjects) {
		bmConfigDao().editBmConfig(columnNames, paramObjects);
	}
	
	/**
	 * delete BmConfig records by primary key
	 * 
	 * @param configId
	 */
	public void deleteBmConfigByPk(String configId) {
		bmConfigDao().deleteBmConfigByPk(configId);
	}
	
	/**
	 * batch delete BmConfig records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteBmConfigsByPks(String pks) {
		bmConfigDao().deleteBmConfigsByPks(pks);
	}
	
	/**
	 * get BmConfig by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public BmConfig getBmConfigByConfigByPk(final String pk) {
		return bmConfigDao().getBmConfigByConfigByPk(pk);
	}
	
	/**
	 * get all BmConfig records 
	 * 
	 * @return
	 */
	public List getAll() {
		return bmConfigDao().getAll();
	}
	
	/**
	 * get all BmConfig record Page 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, final Map searchSqlAndValuesMap) {
		return bmConfigDao().getAllPage(pageNo, pageSize, searchSqlAndValuesMap);
	}
	
	public BmConfigDao bmConfigDao() {
		return new BmConfigDao();
	}
	
}