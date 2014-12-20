/*
 * $Id: AmUserDao,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ganjp.core.dao.BaseDAO;
import org.ganjp.am.dao.AmUserDao;
import org.ganjp.am.model.AmUser;
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
public class AmUserDao extends BaseDAO {
	/**
	 * add AmUser
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addAmUser(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildAddFullSql(columnNames, "am_user", "user_id");
		Object[] fullParamObjects = SqlUtil.buildAddFullParamValues(paramObjects,!columnNames[0].equalsIgnoreCase("user_id"));
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, fullParamObjects));
		this.update(fullSqlWithParam, fullParamObjects, true);
		log.info("add AmUser record success");
	}
	
	/**
	 * edit AmUser
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editAmUser(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildEditFullSql(columnNames, "am_user");
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, paramObjects));
		this.update(fullSqlWithParam, paramObjects, true);
		log.info("add AmUser record success");
	}
	
	/**
	 * delete AmUser records by primary key
	 * 
	 * @param userId
	 */
	public void deleteAmUserByPk(String userId) {
		String sqlWithParam = "delete from am_user WHERE user_id=?";
		Object[] paramObjects = new Object[] {userId};
		this.update(sqlWithParam, paramObjects, true);
		log.info("delete AmUser record success");
	}
	
	/**
	 * batch delete AmUser records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteAmUsersByPks(String pks) {
		if (StringUtil.isNotBlank(pks)) {
			String deleteSql = "delete from am_user WHERE user_id=?";
			this.batchDeleteByIdArr(deleteSql, pks.split(","), true);
			log.info("delete AmUser records success");
		}
	}
	
	/**
	 * get AmUser by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public AmUser getAmUserByPk(final String pk) {
		String sqlWithParam = "SELECT * FROM am_user WHERE user_id=?";
		Object object = (Object)this.getObject(sqlWithParam, pk, AmUser.class, true);
		if (object!=null) {
			AmUser amUser = (AmUser) object;
			log.debug("select AmUser by pk = " + pk);
			return amUser;
		} else {
			log.error("select AmUser by pk = " + pk + " is null" );
			return null;
		}
	}
	
	/**
	 * get all AmUser records where data_state = 0 ORDER BY modify_timestamp DESC
	 * 
	 * @return
	 */
	public List getAll() {
		String sql = "SELECT * FROM am_user WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
		List amUsers = this.getObjectList(sql, AmUser.class, false);
		log.info("select All AmUser record success");
		return amUsers;
	}
	
	/**
	 * get all AmUser record Page where data_state = 0 ORDER BY modify_timestamp DESC
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
			String sql = "SELECT * FROM am_user WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
			page = this.getObjectPage(sql, AmUser.class, page, true);
			log.debug("select AmUser page records success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
	
	/**
	 * get all AmUser record Page where data_state = 0 with search condition ORDER BY modify_timestamp DESC
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
			String sqlSuffix = searchSqlAndValuesMap.get(SqlUtil.SQL_SUFFIX).toString();
			String sql;
			if (sqlSuffix.indexOf("role_id")!=-1) {
				sql = "SELECT a.user_id,a.user_cd,a.user_name,a.password,a.user_alias,a.user_level,a.user_score,a.login_times," +
						" a.gender,a.qq,a.email" +
						" FROM am_user a,am_user_role b WHERE a.user_id=b.user_id and a.data_state = " + Constants.DATA_STATE_NORMAL +
						sqlSuffix + " ORDER BY a.modify_timestamp DESC";
			} else {
				sql = "SELECT * FROM am_user WHERE data_state = " + Constants.DATA_STATE_NORMAL + 
					sqlSuffix + " ORDER BY modify_timestamp DESC";
			}
			Object[] objectValues = (Object[])searchSqlAndValuesMap.get(SqlUtil.VALUES);
			page = this.getObjectPage(sql, objectValues, AmUser.class, page, true);
			log.debug("select AmUser page records with search condition success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
	
	/**
	 * get amUser exist in AmUserRole
	 * 
	 * @param userCd
	 * @param password
	 * @return
	 */
	public AmUser getAmUserInAmUserRole(String userCd, String password) {
		String sql = "SELECT a.* FROM am_user a, (select distinct user_id from am_user_role where data_state = " + Constants.DATA_STATE_NORMAL +
				" ) b WHERE a.user_id=b.user_id and a.user_cd=? and a.password=? and a.data_state = " + Constants.DATA_STATE_NORMAL;
		List amUsers = this.getObjectList(sql, new Object[] {userCd, password}, AmUser.class, false);
		AmUser amUser = amUsers==null || amUsers.isEmpty()?null:(AmUser)amUsers.get(0);
		String roleIds = "";
		if (amUser!=null) {
			sql = "SELECT role_id FROM am_user_role where user_id=? and data_state = " + Constants.DATA_STATE_NORMAL;
			List list = this.getMapList(sql, amUser.getUserId(), true);
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				roleIds += (String)map.get("role_id") + ",";
			}
			amUser.setRoleIds(roleIds.substring(0, roleIds.length()-1));
		}
		log.info("select AmUser in AmUserRole record and set roleIds success");
		return amUser;
	}
}
/**
  * userId - user_id
 * userCd - user_cd
 * userName - user_name
 * userAlias - user_alias
 * userLevel - user_level
 * userScore - user_score
 * loginTimes - login_times
 * password - password
 * gender - gender
 * qq - qq
 * email - email
 * birthYear - birth_year
 * birthMonth - birth_month
 * birthDay - birth_day
 * createDateTime - create_date_time
 * modifyTimestamp - modify_timestamp
 * dataState - data_state
 */