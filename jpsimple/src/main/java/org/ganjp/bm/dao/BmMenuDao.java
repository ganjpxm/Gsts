/*
 * $Id: BmMenuDao,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.bm.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ganjp.bm.model.BmMenu;
import org.ganjp.core.Constants;
import org.ganjp.core.dao.BaseDAO;
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
public class BmMenuDao extends BaseDAO {
	/**
	 * add BmMenu
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addBmMenu(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildAddFullSql(columnNames, "bm_menu", "menu_id");
		Object[] fullParamObjects = SqlUtil.buildAddFullParamValues(paramObjects, !columnNames[0].equalsIgnoreCase("menu_id"));
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, fullParamObjects));
		this.update(fullSqlWithParam, fullParamObjects, true);
		log.info("add BmMenu record success");
	}
	
	/**
	 * 
	 * @param columnNames "config_key,config_value"
	 * @param paramObjectArrs List(new Object[]{"ganjp","1"})
	 */
	public void batchAddBmMenu(final String columnNamesWithComma, final List paramObjectArrs, final boolean isCloseConnection) {
		if (paramObjectArrs!=null && !paramObjectArrs.isEmpty()) {
			String fullSqlWithParam = SqlUtil.buildAddFullSql(columnNamesWithComma.split(","), "bm_menu", "menu_id");
			List fullParamObjectArrs = new ArrayList();
			for (Iterator iterator = paramObjectArrs.iterator(); iterator.hasNext();) {
				Object[] paramObjectArr = (Object[]) iterator.next();
				Object[] fullParamObjects = SqlUtil.buildAddFullParamValues(paramObjectArr, columnNamesWithComma.indexOf("menu_id")==-1);
				fullParamObjectArrs.add(fullParamObjects);
			}
			super.batchExecute(fullSqlWithParam, fullParamObjectArrs, isCloseConnection);
			log.debug("batch add BmMenu success");
		} else {
			log.debug("no BmMenu record needs to add");
		}
	}
	
	/**
	 * edit BmMenu
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editBmMenu(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildEditFullSql(columnNames, "bm_menu");
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, paramObjects));
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, paramObjects));
		this.update(fullSqlWithParam, paramObjects, true);
		log.info("add BmMenu record success");
	}
	
	public void batchEditBmMenu(final String columnNamesWithComma, final List paramObjectArrs, final boolean isCloseConnection) {
		if (paramObjectArrs!=null && !paramObjectArrs.isEmpty()) {
			String fullSqlWithParam = SqlUtil.buildEditFullSql(columnNamesWithComma.split(","), "bm_menu");
			super.batchExecute(fullSqlWithParam, paramObjectArrs, isCloseConnection);
			log.debug("batch add BmMenu success");
		} else {
			log.debug("no BmMenu record needs to add");
		}
	}
	/**
	 * delete BmMenu records by primary key
	 * 
	 * @param menuId
	 */
	public void deleteBmMenuByPk(String menuId) {
		String sqlWithParam = "delete from bm_menu WHERE menu_id=?";
		Object[] paramObjects = new Object[] {menuId};
		this.update(sqlWithParam, paramObjects, true);
		log.info("delete BmMenu record success");
	}
	
	/**
	 * batch delete BmMenu records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteBmMenusByPks(String pks) {
		if (StringUtil.isNotBlank(pks)) {
			String deleteSql = "delete from bm_menu WHERE menu_id=?";
			this.batchDeleteByIdArr(deleteSql, pks.split(","), true);
			log.info("delete BmMenu records success");
		}
	}
	
	/**
	 * get BmMenu by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public BmMenu getBmMenuByPk(final String pk) {
		String sqlWithParam = "SELECT * FROM bm_menu WHERE menu_id=?";
		Object object = (Object)this.getObject(sqlWithParam, pk, BmMenu.class, true);
		if (object!=null) {
			BmMenu bmMenu = (BmMenu) object;
			log.debug("select BmMenu by pk = " + pk);
			return bmMenu;
		} else {
			log.error("select BmMenu by pk = " + pk + " is null" );
			return null;
		}
	}
	
	/**
	 * get BmMenu records by pid
	 * 
	 * @param pid
	 * @return
	 */
	public List getBmMenusByPid(final String menuPid) {
		String sqlWithParam = "SELECT * FROM bm_menu WHERE menu_pid=? AND data_state=? ORDER BY display_no";
		List bmMenus = this.getObjectList(sqlWithParam, new Object[]{menuPid,Constants.DATA_STATE_NORMAL}, BmMenu.class, true);
		log.info("select BmMenu records by pid success");
		return bmMenus;
	}
	
	/**
	 * get all BmMenu records where data_state = 0 ORDER BY modify_timestamp DESC
	 * 
	 * @return
	 */
	public List getAll() {
		String sql = "SELECT * FROM bm_menu WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ";
		List bmMenus = this.getObjectList(sql, BmMenu.class, false);
		log.info("select All BmMenu record success");
		return bmMenus;
	}
	
	/**
	 * get all BmMenu record Page where data_state = 0 ORDER BY modify_timestamp DESC
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
			String sql = "SELECT * FROM bm_menu WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
			page = this.getObjectPage(sql, BmMenu.class, page, true);
			log.debug("select BmMenu page records success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
	
	/**
	 * get all BmMenu record Page where data_state = 0 with search condition ORDER BY modify_timestamp DESC
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
			String sql = "SELECT * FROM bm_menu WHERE data_state = " + Constants.DATA_STATE_NORMAL + 
					searchSqlAndValuesMap.get(SqlUtil.SQL_SUFFIX).toString() + " ORDER BY modify_timestamp DESC";
			Object[] objectValues = (Object[])searchSqlAndValuesMap.get(SqlUtil.VALUES);
			page = this.getObjectPage(sql, objectValues, BmMenu.class, page, true);
			log.debug("select BmMenu page records with search condition success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
}
/**
  * menuId - menu_id
 * menuPid - menu_pid
 * menuCd - menu_cd
 * menuName - menu_name
 * menuType - menu_type
 * url - url
 * belongUserId - belong_user_id
 * displayLevel - display_level
 * displayNo - display_no
 * endFlag - end_flag
 * modifyTimestamp - modify_timestamp
 * createDateTime - create_date_time
 * dataState - data_state
 */