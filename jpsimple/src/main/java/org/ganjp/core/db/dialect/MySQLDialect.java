/*
 * $Id: MySQLDialect.java,v 1.1 2010/08/24 06:54:43 zhaoqy Exp $
 *
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 * 
 */
package org.ganjp.core.db.dialect;

import org.ganjp.core.util.StringUtil;

/**
 * An SQL DIALECT for MySQL.
 * @author hanpeng
 */
public class MySQLDialect extends DBDialect {
	public MySQLDialect() {
		super();
	}

	public String getAddColumnString() {
		return "add column";
	}

	public boolean dropConstraints() {
		return false;
	}

	public boolean qualifyIndexName() {
		return false;
	}

	public boolean supportsIdentityColumns() {
		return true;
	}

	public String getIdentitySelectString() {
		return "SELECT LAST_INSERT_ID()";
	}

	public String getIdentityColumnString() {
		return "NOT NULL AUTO_INCREMENT";
	}
	
	public String getCountResultString(String querySql) {
		StringBuffer buf = new StringBuffer();
		buf.append("select count(*) from (");
		buf.append(querySql);
		buf.append(") wrapped__");
		return buf.toString();
	}

	public String getAddForeignKeyConstraintString(String constraintName,
			String[] foreignKey, String referencedTable, String[] primaryKey) {
		String cols = StringUtil.join(StringUtil.COMMA_SPACE, foreignKey);
		return new StringBuffer(30).append(" add index (").append(cols).append(
				"), add constraint ").append(constraintName).append(
				" foreign key (").append(cols).append(") references ").append(
				referencedTable).append(" (").append(
				StringUtil.join(StringUtil.COMMA_SPACE, primaryKey))
				.append(')').toString();
	}

	public boolean supportsLimit() {
		return true;
	}

	public String getLimitString(String sql, int start, int end) {
		StringBuffer pagingSelect = new StringBuffer(100);
		pagingSelect.append(sql);
		pagingSelect.append(" limit ");
		pagingSelect.append(String.valueOf(start));
		pagingSelect.append(" ,");
		pagingSelect.append(String.valueOf(end));
		return pagingSelect.toString();
	}

	public boolean preferLimit() {
		return true;
	}

	public char closeQuote() {
		return '`';
	}

	public char openQuote() {
		return '`';
	}

}