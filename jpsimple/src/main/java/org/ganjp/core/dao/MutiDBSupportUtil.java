/*
 * $Id: MutiDBSupportUtil.java,v 1.5 2011/08/18 07:31:57 zhaoqy Exp $
 *
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 */
package org.ganjp.core.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.ganjp.core.Constants;

/**
 * @author hanpeng
 *
 * 根据Connection动态判断数据库类型
 */
public class MutiDBSupportUtil {
	

//	public static String getDefaultDBType(){
//		return AbstractBaseDao.dbMeta().getDbType();
//	}

	public static String getDBType(Connection conn) {
		String dbtypename = null;
		try {
			DatabaseMetaData _dmdata = conn.getMetaData();
			String dbProductName = _dmdata.getDatabaseProductName();
			dbtypename = parseDBProductName(dbProductName);
		} catch (SQLException e) {
			System.out.println("Database error");
		}
		return dbtypename;
	}

	private static String parseDBProductName(String productName) {
		productName = productName.toLowerCase();
		String dbType = "";
		if (productName.indexOf("db2") != -1) {
			dbType = Constants.DATABASE_TYPE_DB2;
		} else if (productName.indexOf("oracle") != -1) {
			dbType = Constants.DATABASE_TYPE_ORACLE;
		} else if (productName.indexOf("mysql") != -1) {
			dbType = Constants.DATABASE_TYPE_MYSQL;
		} else if (productName.indexOf("sql") != -1) {
			dbType = Constants.DATABASE_TYPE_SQLSERVER;
		} else if (productName.indexOf("apache derby") != -1
				|| productName.indexOf("derby") != -1) {
			dbType = Constants.DATABASE_TYPE_DERBY;
		} else if (productName.indexOf("dm dbms") != -1
				|| productName.indexOf("dm") != -1) {
			dbType = Constants.DATABASE_TYPE_DM;
		} else if (productName.indexOf("kingbasees") != -1
				|| productName.indexOf("kingbase") != -1) {
			dbType = Constants.DATABASE_TYPE_KB;
		} else if (productName.indexOf("h2") != -1 ) {
			dbType = Constants.DATABASE_TYPE_H2;
		}
		return dbType;
	}
}