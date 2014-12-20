/*
 * $Id: EmProductServiceImpl,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.em.service.impl;

import org.ganjp.em.model.EmProduct;
import org.ganjp.em.service.EmProductService;
import org.ganjp.em.dao.EmProductDao;
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
public class EmProductServiceImpl extends BaseService implements EmProductService {
	
	/**
	 * add EmProduct
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addEmProduct(final String[] columnNames, final Object[] paramObjects) {
		emProductDao().addEmProduct(columnNames, paramObjects);
	}
	
	/**
	 * edit EmProduct
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editEmProduct(final String[] columnNames, final Object[] paramObjects) {
		emProductDao().editEmProduct(columnNames, paramObjects);
	}
	
	/**
	 * delete EmProduct records by primary key
	 * 
	 * @param productId
	 */
	public void deleteEmProductByPk(String productId) {
		emProductDao().deleteEmProductByPk(productId);
	}
	
	/**
	 * batch delete EmProduct records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteEmProductsByPks(String pks) {
		emProductDao().deleteEmProductsByPks(pks);
	}
	
	/**
	 * get EmProduct by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public EmProduct getEmProductByPk(final String pk) {
		return emProductDao().getEmProductByPk(pk);
	}
	
	/**
	 * get all EmProduct records 
	 * 
	 * @return
	 */
	public List getAll() {
		return emProductDao().getAll();
	}
	
	/**
	 * get all EmProduct record Page 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, final Map searchSqlAndValuesMap) {
		return emProductDao().getAllPage(pageNo, pageSize, searchSqlAndValuesMap);
	}
	
	public EmProductDao emProductDao() {
		return new EmProductDao();
	}
}