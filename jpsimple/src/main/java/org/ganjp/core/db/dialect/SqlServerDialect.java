/*
 * $Id: SqlServerDialect.java,v 1.2 2010/08/26 12:02:04 zhaoqy Exp $
 *
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 * 
 */
package org.ganjp.core.db.dialect;

/**
 * An SQL DIALECT compatible with Sybase and MS SQL Server.
 * @author Gavin King
 */

public class SqlServerDialect extends DBDialect {
	public SqlServerDialect() {
		super();
	}

	public String getAddColumnString() {
		return "add";
	}

	public String getNullColumnString() {
		return " null";
	}

	public boolean qualifyIndexName() {
		return false;
	}

	public boolean supportsForUpdate() {
		return false;
	}

	public boolean supportsIdentityColumns() {
		return true;
	}

	public String getIdentitySelectString() {
		return "select @@identity";
	}

	public String getIdentitySelectString(String table) {
		return "SELECT IDENT_CURRENT('" + table + "')+1 ";
	}

	public String getMaxColumnValue(String schema, String table, String column) {
		return "SELECT MAX(" + column + ") FROM " + table;
	}

	public String getIdentityColumnString(String table) {
		StringBuffer sql = new StringBuffer(
				"SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.columns WHERE TABLE_NAME='");
		sql.append(table).append("' AND COLUMNPROPERTY( OBJECT_ID('").append(
				table).append("'),COLUMN_NAME,'IsIdentity')=1 ");
		return sql.toString();
	}

	public String getIdentityInsertOnString(String table, boolean on) {
		String insert = " ON ";
		if (!on) {
			insert = " OFF ";
		}
		StringBuffer sql = new StringBuffer("SET IDENTITY_INSERT ").append(
				table).append(insert);
		return sql.toString();
	}

	public String getAlterIdentityString(String table, long start) {
		StringBuffer sql = new StringBuffer("DBCC CHECKIDENT ('").append(table)
				.append("' , RESEED, ").append(start).append(")");
		return sql.toString();
	}

	public String getNoColumnsInsertString() {
		return "DEFAULT VALUES";
	}

	public char closeQuote() {
		return ']';
	}

	public char openQuote() {
		return '[';
	}
	
	public boolean useMaxForLimit() {
		return true;
	}
}
