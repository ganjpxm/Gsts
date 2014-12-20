package org.ganjp.core.db.dialect;

import java.util.HashMap;
import java.util.Map;

/**
 * An SQL DIALECT for Oracle 9 (uses ANSI-style syntax where possible).
 * @author Gavin King, David Channon
 */
public class Oracle9Dialect extends DBDialect {

	private final Map aggregateFunctions = new HashMap();

	public Oracle9Dialect() {
		super();
	}

	public String getAddColumnString() {
		return "add";
	}

	public String getSequenceNextValString(String sequenceName) {
		return "select " + sequenceName + ".nextval from dual";
	}

	public String getCreateSequenceString(String sequenceName) {
		return "create sequence " + sequenceName + " nocache";
	}

	public String getDropSequenceString(String sequenceName) {
		return "drop sequence " + sequenceName;
	}

	public String getCascadeConstraintsString() {
		return " cascade constraints";
	}

	public boolean supportsForUpdateNowait() {
		return true;
	}

	public boolean supportsSequences() {
		return true;
	}

	public boolean supportsLimit() {
		return true;
	}

	public String getLimitString(String sql, int start, int end) {
		if (start > 0)
			start = start + 1;
		StringBuffer pagingSelect = new StringBuffer(100);
		//		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		//		pagingSelect.append(sql);
		//		//pagingSelect.append(" ) row_ where rownum <= ?) where rownum_ > ?");
		//		pagingSelect.append(" ) row_ where rownum <= ");
		//		pagingSelect.append(String.valueOf(end));
		//		pagingSelect.append(") where rownum_ > ");
		//		pagingSelect.append(String.valueOf(start));
		//		return pagingSelect.toString();

		pagingSelect
				.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ ) where rownum_ between ");
		pagingSelect.append(String.valueOf(start));
		pagingSelect.append(" and ");
		pagingSelect.append(String.valueOf(end));
		return pagingSelect.toString();
	}

	public String getCountResultString(String querySql) {
		StringBuffer buf = new StringBuffer("select count(*) from (");
		buf.append(querySql);
		buf.append(")");
		// TODO Auto-generated method stub
		return buf.toString();
	}

	public String getAlterSequencesString(String sequenceName, long newValue) {
		return "create sequence " + sequenceName + " start with "
				+ String.valueOf(newValue);
	}

	public String getQuerySequencesString() {
		return "select sequence_name from USER_SEQUENCES";
	}

	public String getQuerySequencesString(String sequenceName) {
		return "select sequence_name from USER_SEQUENCES where sequence_name = '"
				+ sequenceName.toUpperCase() + "'";
	}

	public boolean bindLimitParametersInReverseOrder() {
		return true;
	}

	public boolean supportsForUpdateOf() {
		return true;
	}

	public Map getAggregateFunctions() {
		return aggregateFunctions;
	}

	public boolean useMaxForLimit() {
		return true;
	}

	public boolean isSupportAlterSequenceRestart() {
		return false;
	}

	public String getBeforeAlterSequenceRestart(String sequenceName) {
		return getDropSequenceString(sequenceName);
	}
}
