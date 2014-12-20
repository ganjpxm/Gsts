/*
 * $Id: EmProductDao,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.em.dao;

import java.util.List;
import java.util.Map;

import org.ganjp.core.dao.BaseDAO;
import org.ganjp.em.dao.EmProductDao;
import org.ganjp.em.model.EmProduct;
import org.ganjp.core.Constants;
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
public class EmProductDao extends BaseDAO {
	/**
	 * add EmProduct
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addEmProduct(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildAddFullSql(columnNames, "em_product", "product_id");
		Object[] fullParamObjects = SqlUtil.buildAddFullParamValues(paramObjects,!columnNames[0].equalsIgnoreCase("product_id"));
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, fullParamObjects));
		this.update(fullSqlWithParam, fullParamObjects, true);
		log.info("add EmProduct record success");
	}
	
	/**
	 * edit EmProduct
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editEmProduct(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildEditFullSql(columnNames, "em_product");
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, paramObjects));
		this.update(fullSqlWithParam, paramObjects, true);
		log.info("add EmProduct record success");
	}
	
	/**
	 * delete EmProduct records by primary key
	 * 
	 * @param productId
	 */
	public void deleteEmProductByPk(String productId) {
		String sqlWithParam = "delete from em_product WHERE product_id=?";
		Object[] paramObjects = new Object[] {productId};
		this.update(sqlWithParam, paramObjects, true);
		log.info("delete EmProduct record success");
	}
	
	/**
	 * batch delete EmProduct records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteEmProductsByPks(String pks) {
		if (StringUtil.isNotBlank(pks)) {
			String deleteSql = "delete from em_product WHERE product_id=?";
			this.batchDeleteByIdArr(deleteSql, pks.split(","), true);
			log.info("delete EmProduct records success");
		}
	}
	
	/**
	 * get EmProduct by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public EmProduct getEmProductByPk(final String pk) {
		String sqlWithParam = "SELECT * FROM em_product WHERE product_id=?";
		Object object = (Object)this.getObject(sqlWithParam, pk, EmProduct.class, true);
		if (object!=null) {
			EmProduct emProduct = (EmProduct) object;
			log.debug("select EmProduct by pk = " + pk);
			return emProduct;
		} else {
			log.error("select EmProduct by pk = " + pk + " is null" );
			return null;
		}
	}
	
	/**
	 * get all EmProduct records where data_state = 0 ORDER BY modify_timestamp DESC
	 * 
	 * @return
	 */
	public List getAll() {
		String sql = "SELECT * FROM em_product WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
		List emProducts = this.getObjectList(sql, EmProduct.class, false);
		log.info("select All EmProduct record success");
		return emProducts;
	}
	
	/**
	 * get all EmProduct record Page where data_state = 0 ORDER BY modify_timestamp DESC
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
			String sql = "SELECT * FROM em_product WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
			page = this.getObjectPage(sql, EmProduct.class, page, true);
			log.debug("select EmProduct page records success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
	
	/**
	 * get all EmProduct record Page where data_state = 0 with search condition ORDER BY modify_timestamp DESC
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
			String sql = "SELECT * FROM em_product WHERE data_state = " + Constants.DATA_STATE_NORMAL + 
					searchSqlAndValuesMap.get(SqlUtil.SQL_SUFFIX).toString() + " ORDER BY modify_timestamp DESC";
			Object[] objectValues = (Object[])searchSqlAndValuesMap.get(SqlUtil.VALUES);
			page = this.getObjectPage(sql, objectValues, EmProduct.class, page, true);
			log.debug("select EmProduct page records with search condition success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
}
/**
  * productId - product_id
 * productName - product_name
 * price - price
 * publishName - publish_name
 * publishDate - publish_date
 * imagepath - imagepath
 * description - description
 * createDateTime - create_date_time
 * modifyTimestamp - modify_timestamp
 * dataState - data_state
 */