/*
 * $Id: AmUserDetailDao,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.dao;

import java.util.List;
import java.util.Map;

import org.ganjp.core.dao.BaseDAO;
import org.ganjp.am.dao.AmUserDetailDao;
import org.ganjp.am.model.AmUserDetail;
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
public class AmUserDetailDao extends BaseDAO {
	/**
	 * add AmUserDetail
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addAmUserDetail(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildAddFullSql(columnNames, "am_user_detail", "user_detail_id");
		Object[] fullParamObjects = SqlUtil.buildAddFullParamValues(paramObjects,!columnNames[0].equalsIgnoreCase("user_detail_id"));
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, fullParamObjects));
		this.update(fullSqlWithParam, fullParamObjects, true);
		log.info("add AmUserDetail record success");
	}
	
	/**
	 * edit AmUserDetail
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editAmUserDetail(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildEditFullSql(columnNames, "am_user_detail");
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, paramObjects));
		this.update(fullSqlWithParam, paramObjects, true);
		log.info("add AmUserDetail record success");
	}
	
	/**
	 * delete AmUserDetail records by primary key
	 * 
	 * @param userDetailId
	 */
	public void deleteAmUserDetailByPk(String userDetailId) {
		String sqlWithParam = "delete from am_user_detail WHERE user_detail_id=?";
		Object[] paramObjects = new Object[] {userDetailId};
		this.update(sqlWithParam, paramObjects, true);
		log.info("delete AmUserDetail record success");
	}
	
	/**
	 * batch delete AmUserDetail records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteAmUserDetailsByPks(String pks) {
		if (StringUtil.isNotBlank(pks)) {
			String deleteSql = "delete from am_user_detail WHERE user_detail_id=?";
			this.batchDeleteByIdArr(deleteSql, pks.split(","), true);
			log.info("delete AmUserDetail records success");
		}
	}
	
	/**
	 * get AmUserDetail by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public AmUserDetail getAmUserDetailByPk(final String pk) {
		String sqlWithParam = "SELECT * FROM am_user_detail WHERE user_detail_id=?";
		Object object = (Object)this.getObject(sqlWithParam, pk, AmUserDetail.class, true);
		if (object!=null) {
			AmUserDetail amUserDetail = (AmUserDetail) object;
			log.debug("select AmUserDetail by pk = " + pk);
			return amUserDetail;
		} else {
			log.error("select AmUserDetail by pk = " + pk + " is null" );
			return null;
		}
	}
	
	/**
	 * get all AmUserDetail records where data_state = 0 ORDER BY modify_timestamp DESC
	 * 
	 * @return
	 */
	public List getAll() {
		String sql = "SELECT * FROM am_user_detail WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
		List amUserDetails = this.getObjectList(sql, AmUserDetail.class, false);
		log.info("select All AmUserDetail record success");
		return amUserDetails;
	}
	
	/**
	 * get all AmUserDetail record Page where data_state = 0 ORDER BY modify_timestamp DESC
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
			String sql = "SELECT * FROM am_user_detail WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
			page = this.getObjectPage(sql, AmUserDetail.class, page, true);
			log.debug("select AmUserDetail page records success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
	
	/**
	 * get all AmUserDetail record Page where data_state = 0 with search condition ORDER BY modify_timestamp DESC
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
			String sql = "SELECT * FROM am_user_detail WHERE data_state = " + Constants.DATA_STATE_NORMAL + 
					searchSqlAndValuesMap.get(SqlUtil.SQL_SUFFIX).toString() + " ORDER BY modify_timestamp DESC";
			Object[] objectValues = (Object[])searchSqlAndValuesMap.get(SqlUtil.VALUES);
			page = this.getObjectPage(sql, objectValues, AmUserDetail.class, page, true);
			log.debug("select AmUserDetail page records with search condition success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
}
/**
  * userDetailId - user_detail_id
 * userId - user_id
 * mobilePhone - mobile_phone
 * homePhone - home_phone
 * countryId - country_id
 * provinceId - province_id
 * cityId - city_id
 * birthPlace - birth_place
 * livePlace - live_place
 * annualIncome - annual_income
 * jobCategoryPid - job_category_pid
 * jobCategoryId - job_category_id
 * jobPositionId - job_position_id
 * companyName - company_name
 * shoolName - shool_name
 * educationId - education_id
 * interestIds - interest_ids
 * passwordTipCustom - password_tip_custom
 * passwordTipId - password_tip_id
 * passwordTipAnswer - password_tip_answer
 * signature - signature
 * attach - attach
 * attachName - attach_name
 * remark - remark
 * createDateTime - create_date_time
 * modifyTimestamp - modify_timestamp
 * dataState - data_state
 */