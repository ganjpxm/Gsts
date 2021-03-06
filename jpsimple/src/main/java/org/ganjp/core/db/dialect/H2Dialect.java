package org.ganjp.core.db.dialect;
import org.ganjp.core.util.ReflectUtil;

/**
 * A dialect compatible with the H2 database.
 * 
 * @author Thomas Mueller
 * 
 */
public class H2Dialect extends DBDialect {

	private String querySequenceString;

	public H2Dialect() {
		super();

		querySequenceString = "select sequence_name from information_schema.sequences";
		try {
			// HHH-2300
			Class constants = ReflectUtil.classForName("org.h2.engine.Constants");
			Integer build = (Integer) constants.getDeclaredField("BUILD_ID").get(null);
			int buildid = build.intValue();
			if (buildid < 32) {
				querySequenceString = "select name from information_schema.sequences";
			}
		} catch (Throwable e) {
			// ignore (probably H2 not in the classpath)
		}

		//getDefaultProperties().setProperty("hibernate.jdbc.batch_size", Constants.DEFAULT_BATCH_SIZE);

	}

	public String getAddColumnString() {
		return "add column";
	}

	public boolean supportsIdentityColumns() {
		return true;
	}

	public String getIdentityColumnString() {
		return "generated by default as identity"; // not null is implicit
	}

	public String getIdentitySelectString() {
		return "call identity()";
	}

	public String getIdentityInsertString() {
		return "null";
	}

	public String getForUpdateString() {
		return " for update";
	}

	public boolean supportsUnique() {
		return true;
	}

	public boolean supportsLimit() {
		return true;
	}

	public String getCountResultString(String querySql) {
		StringBuffer buf = new StringBuffer();
		buf.append("select count(*) from (");
		buf.append(querySql);
		buf.append(") wrapped__");
		return buf.toString();
	}

	/**
	 * add by zhaoqy
	 * <p>
	 * h2数据库分页语法特别
	 * </p>
	 * 
	 * @param start : 一共获取多少条数据
	 * @param end : 从第几页开始获取数据
	 */
	public String getLimitString(String sql, int start, int end) {
		StringBuffer pagingSelect = new StringBuffer(100);
		pagingSelect.append(sql);
		pagingSelect.append(" limit ");
		pagingSelect.append(String.valueOf(end));
		pagingSelect.append(" offset ");
		pagingSelect.append(String.valueOf(start));
		return pagingSelect.toString();
	}

	public String getLimitString(String sql, boolean hasOffset) {
		return new StringBuffer(sql.length() + 20).append(sql).append(
				hasOffset ? " limit ? offset ?" : " limit ?").toString();
	}

	public boolean bindLimitParametersInReverseOrder() {
		return true;
	}

	public boolean bindLimitParametersFirst() {
		return false;
	}

	public boolean supportsIfExistsAfterTableName() {
		return true;
	}

	public boolean supportsSequences() {
		return true;
	}

	public boolean supportsPooledSequences() {
		return true;
	}

	public String getCreateSequenceString(String sequenceName) {
		return "create sequence " + sequenceName;
	}

	public String getDropSequenceString(String sequenceName) {
		return "drop sequence " + sequenceName;
	}

	public String getSelectSequenceNextValString(String sequenceName) {
		return "next value for " + sequenceName;
	}

	public String getSequenceNextValString(String sequenceName) {
		return "call next value for " + sequenceName;
	}

	public String getQuerySequencesString() {
		return querySequenceString;
	}

	public boolean supportsTemporaryTables() {
		return true;
	}

	public String getCreateTemporaryTableString() {
		return "create temporary table if not exists";
	}

	public boolean supportsCurrentTimestampSelection() {
		return true;
	}

	public boolean isCurrentTimestampSelectStringCallable() {
		return false;
	}

	public String getCurrentTimestampSelectString() {
		return "call current_timestamp()";
	}

	public boolean supportsUnionAll() {
		return true;
	}

	// Overridden informational metadata ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public boolean supportsLobValueChangePropogation() {
		return false;
	}
}