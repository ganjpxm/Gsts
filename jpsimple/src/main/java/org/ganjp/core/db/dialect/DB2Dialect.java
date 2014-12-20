package org.ganjp.core.db.dialect;

import org.ganjp.core.util.StringUtil;

public class DB2Dialect extends DBDialect {

	public DB2Dialect() {
		super();
	}

	public String getAddColumnString() {
		return "add column";
	}

	public boolean dropConstraints() {
		return false;
	}

	public boolean supportsIdentityColumns() {
		return false;
	}

	public String getIdentitySelectString(String table) {
		return "values IDENTITY_VAL_LOCAL()";
	}

	public String getIdentityColumnString(String table) {
		return "not null generated by default as identity";
	}

	public String getIdentityInsertString(String table) {
		return "default";
	}

	public String getSequenceNextValString(String sequenceName) {
		return "values nextval for " + sequenceName;
	}

	public String getCreateSequenceString(String sequenceName) {
		return "create sequence " + sequenceName;
	}

	public String getDropSequenceString(String sequenceName) {
		return "drop sequence " + sequenceName + " restrict";
	}

	public String getAlterSequencesString(String sequenceName, long newValue) {
		return "alter sequence " + sequenceName + " restart with " + String.valueOf(newValue);
	}

	public String getQuerySequencesString() {
		return "select seqname from sysibm.SYSSEQUENCES";
	}

	public String getQuerySequencesString(String sequenceName) {
		return "select seqname from sysibm.SYSSEQUENCES where seqname = '" + sequenceName.toUpperCase()
				+ "'";
	}

	public String getCountResultString(String querySql) {
		StringBuffer buf = new StringBuffer();
		buf.append("select count(*) from (");
		buf.append(querySql);
		buf.append(") wrapped__");
		return buf.toString();
	}

	/**
	 * by zhaoqy 2008-10-10
	 * 
	 * 修改DB2 分页时，当使用具有重复数据的字段作为排序字段时，分页显示不正确的问题
	 */
	public String getLimitString(String querySql, int start, int end) {
		StringBuffer buf = new StringBuffer();
		buf.append("select * from (select query__.*,");
		buf.append(getRowNumber(querySql));
		buf.append(" from (");
		buf.append(querySql);
		buf.append(") query__) wrapped__ ");
		buf.append("where rownum__ ");
		buf.append("between " + (start + 1) + " and " + end);
		return buf.toString();
	}
	
	/**
	 * 构造RowNumber
	 * @param sql
	 * @return
	 * 
	 * add by zhaoqy 2008-10-10
	 */
	public static String getRowNumber(String sql) {
		StringBuffer rownumber = new StringBuffer(50)
				.append("rownumber() over(");
		sql = sql.toLowerCase();
		int orderByIndex = sql.indexOf(" order by");
		if (orderByIndex > 0 && !hasDistinct(sql)) {
			String orderBy = sql.substring(sql.indexOf(" order by")+9);
			String[] orderBys = StringUtil.split(orderBy, ",");
			orderBy = " order by ";
			for(int i=0;i<orderBys.length;i++){
				int point = orderBys[i].indexOf("."); 
				
				if(point!=-1){
					orderBy = orderBy +  orderBys[i].substring(point+1);
				}else{
					orderBy = orderBy +  orderBys[i];
				}
				if(i!=(orderBys.length-1)){
					orderBy = orderBy + ",";
				}
			}
			rownumber.append(orderBy);
		}
		rownumber.append(") as rownum__");
		return rownumber.toString();
	}

	/**
	 * 是否有distinct
	 * 
	 * @param sql
	 * @return
	 * 
	 * add by zhaoqy 2008-10-10
	 */
	private static boolean hasDistinct(String sql) {
		return sql.toLowerCase().indexOf("select distinct") >= 0;
	}

	public boolean supportsSequences() {
		return true;
	}

	public boolean supportsLimit() {
		boolean isSupports = true;
		// //兼容V3的翻页，可以通过参数控制是否使用V3的翻页
		// if(Boolean.valueOf(USED_V3_PAGINATION).booleanValue()){
		// isSupports = false;
		// }
		return isSupports;
	}

	public boolean isSupportAlterSequenceRestart() {
		return true;
	}

	public String getBeforeAlterSequenceRestart(String seqname) {
		return null;
	}
	
	public boolean useMaxForLimit() {
		return true;
	}
}