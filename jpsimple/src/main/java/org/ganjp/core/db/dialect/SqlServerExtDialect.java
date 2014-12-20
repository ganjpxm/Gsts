/*
 * $Id: SqlServerExtDialect.java,v 1.1 2010/08/26 12:02:04 zhaoqy Exp $
 *
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 *
 */
package org.ganjp.core.db.dialect;

/**
 * <p>
 * SQLServer方言的扩展，提供高性能的分页函数
 * </p>
 * 
 * @author ResourceOne BizFoundation Team: zhaoqy
 * 
 * @version 1.0
 * @since 4.2
 */
public class SqlServerExtDialect extends SqlServerDialect {

	/**
	 * <p>
	 * 获取sql中select子句位置。
	 * </p>
	 * @param sql
	 * @return int
	 */
	protected static int getSqlAfterSelectInsertPoint(String sql) {
		int selectIndex = sql.toLowerCase().indexOf("select");
		int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
		return selectIndex + ((selectDistinctIndex == selectIndex) ? 15 : 6);
	}
	
	public boolean supportsLimit() {
		return true;
	}
	
	public boolean supportsLimitNoOrderBy(String sql){
		return sql.toLowerCase().indexOf("order by")!=-1;
	}

	public boolean useMaxForLimit() {
		return true;
	}

	public String getCountResultString(String querySql) {
		StringBuffer buf = new StringBuffer();
		buf.append("select count(*) from (");
		buf.append(hasOrderBy(querySql));
		buf.append(") wrapped__");
		return buf.toString();
	}

	private String hasOrderBy(String querySql) {
		String temp = querySql.toLowerCase();
		if (temp.indexOf("order by") != -1) {
			if (temp.indexOf("select ") != -1) {
				temp = temp.replaceAll("select ", "select TOP 100 PERCENT ");
			}
		}
		return temp;
	}

	/**
	 * 首页top，以后用ROW_NUMBER
	 * 
	 * @param query
	 * @param offset
	 * @param limit
	 * @return
	 */
	public String getLimitString(String query, int offset, int limit) {
		if (offset == 0) {
			return new StringBuffer(query.length() + 8).append(query).insert(
					getSqlAfterSelectInsertPoint(query), " top " + limit).toString();
		}
		return getLimitStringWithRowNumber(query, offset, limit);
	}

	/**
	 * 通过row number实现分页
	 * 
	 * @param sql
	 * @param offset
	 * @param limit
	 * @return
	 */
	public String getLimitStringWithRowNumber(String sql, int offset, int limit) {
		int orderByIndex = sql.toLowerCase().lastIndexOf("order by");
		if (orderByIndex <= 0) {
			throw new UnsupportedOperationException(
					"must specify 'order by' statement to support limit operation with offset in sql server 2005");
		}
		String sqlOrderBy = sql.substring(orderByIndex + 8);
		String sqlRemoveOrderBy = sql.substring(0, orderByIndex);
		int insertPoint = getSqlAfterSelectInsertPoint(sql);
		return new StringBuffer(sql.length() + 100).append("with tempPagination as(")
				.append(sqlRemoveOrderBy).insert(insertPoint + 23,
						" ROW_NUMBER() OVER(ORDER BY " + sqlOrderBy + ") as RowNumber,").append(
						") select * from tempPagination where RowNumber>").append(offset).append(
						" and RowNumber<=").append(limit).toString();
	}
}
