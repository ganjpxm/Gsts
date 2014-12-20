/*
 * $Id: AmRoleDao,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ganjp.core.dao.BaseDAO;
import org.ganjp.am.dao.AmRoleDao;
import org.ganjp.am.model.AmRole;
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
public class AmRoleDao extends BaseDAO {
	/**
	 * add AmRole
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addAmRole(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildAddFullSql(columnNames, "am_role", "role_id");
		Object[] fullParamObjects = SqlUtil.buildAddFullParamValues(paramObjects,!columnNames[0].equalsIgnoreCase("role_id"));
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, fullParamObjects));
		this.update(fullSqlWithParam, fullParamObjects, true);
		log.info("add AmRole record success");
	}
	
	/**
	 * 
	 * @param columnNames "config_key,config_value"
	 * @param paramObjectArrs List(new Object[]{"ganjp","1"})
	 */
	public void batchAddAmRole(final String columnNamesWithComma, final List paramObjectArrs, final boolean isCloseConnection) {
		if (paramObjectArrs!=null && !paramObjectArrs.isEmpty()) {
			String fullSqlWithParam = SqlUtil.buildAddFullSql(columnNamesWithComma.split(","), "am_role", "role_id");
			List fullParamObjectArrs = new ArrayList();
			for (Iterator iterator = paramObjectArrs.iterator(); iterator.hasNext();) {
				Object[] paramObjectArr = (Object[]) iterator.next();
				Object[] fullParamObjects = SqlUtil.buildAddFullParamValues(paramObjectArr, columnNamesWithComma.indexOf("role_id")==-1);
				fullParamObjectArrs.add(fullParamObjects);
			}
			super.batchExecute(fullSqlWithParam, fullParamObjectArrs, isCloseConnection);
			log.debug("batch add AmRole success");
		} else {
			log.debug("no AmRole record needs to add");
		}
	}
	
	/**
	 * edit AmRole
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editAmRole(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildEditFullSql(columnNames, "am_role");
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, paramObjects));
		this.update(fullSqlWithParam, paramObjects, true);
		log.info("add AmRole record success");
	}
	
	public void batchEditAmRole(final String columnNamesWithComma, final List paramObjectArrs, final boolean isCloseConnection) {
		if (paramObjectArrs!=null && !paramObjectArrs.isEmpty()) {
			String fullSqlWithParam = SqlUtil.buildEditFullSql(columnNamesWithComma.split(","), "am_role");
			super.batchExecute(fullSqlWithParam, paramObjectArrs, isCloseConnection);
			log.debug("batch add AmRole success");
		} else {
			log.debug("no AmRole record needs to add");
		}
	}
	
	/**
	 * delete AmRole records by primary key
	 * 
	 * @param roleId
	 */
	public void deleteAmRoleByPk(String roleId) {
		String sqlWithParam = "delete from am_role WHERE role_id=?";
		Object[] paramObjects = new Object[] {roleId};
		this.update(sqlWithParam, paramObjects, true);
		log.info("delete AmRole record success");
	}
	
	/**
	 * batch delete AmRole records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteAmRolesByPks(String pks) {
		if (StringUtil.isNotBlank(pks)) {
			String deleteSql = "delete from am_role WHERE role_id=?";
			this.batchDeleteByIdArr(deleteSql, pks.split(","), true);
			log.info("delete AmRole records success");
		}
	}
	
	/**
	 * get AmRole by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public AmRole getAmRoleByPk(final String pk) {
		String sqlWithParam = "SELECT * FROM am_role WHERE role_id=?";
		Object object = (Object)this.getObject(sqlWithParam, pk, AmRole.class, true);
		if (object!=null) {
			AmRole amRole = (AmRole) object;
			log.debug("select AmRole by pk = " + pk);
			return amRole;
		} else {
			log.error("select AmRole by pk = " + pk + " is null" );
			return null;
		}
	}
	
	/**
	 * get all AmRole records where data_state = 0 ORDER BY modify_timestamp DESC
	 * 
	 * @return
	 */
	public List getAll() {
		String sql = "SELECT * FROM am_role WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
		List amRoles = this.getObjectList(sql, AmRole.class, false);
		log.info("select All AmRole record success");
		return amRoles;
	}
	
	/**
	 * get all AmRole record Page where data_state = 0 ORDER BY modify_timestamp DESC
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
			String sql = "SELECT * FROM am_role WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
			page = this.getObjectPage(sql, AmRole.class, page, true);
			log.debug("select AmRole page records success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
	
	/**
	 * get all AmRole record Page where data_state = 0 with search condition ORDER BY modify_timestamp DESC
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
			String sql = "SELECT * FROM am_role WHERE data_state = " + Constants.DATA_STATE_NORMAL + 
					searchSqlAndValuesMap.get(SqlUtil.SQL_SUFFIX).toString() + " ORDER BY modify_timestamp DESC";
			Object[] objectValues = (Object[])searchSqlAndValuesMap.get(SqlUtil.VALUES);
			page = this.getObjectPage(sql, objectValues, AmRole.class, page, true);
			log.debug("select AmRole page records with search condition success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
}
/**
  * roleId - role_id
 * roleCd - role_cd
 * roleName - role_name
 * description - description
 * createDateTime - create_date_time
 * modifyTimestamp - modify_timestamp
 * dataState - data_state
 */