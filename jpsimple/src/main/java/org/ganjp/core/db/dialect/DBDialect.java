package org.ganjp.core.db.dialect;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.ganjp.core.Constants;
import org.ganjp.core.db.base.MultiDBSupportUtil;
import org.ganjp.core.exception.dao.DAOException;
import org.ganjp.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DBDialect {
	protected Logger log = LoggerFactory.getLogger(getClass());

	private static final Map aggregateFunctions = new HashMap();
	private static final Map databaseMappings = new HashMap();
	private final Properties properties = new Properties();

	/**
	 * Characters used for quoting SQL identifiers
	 */
	public static final String QUOTE = "`\"[";
	public static final String CLOSED_QUOTE = "`\"]";

	static {
		databaseMappings.put(Constants.DATABASE_TYPE_DB2, "DB2Dialect");
		databaseMappings.put(Constants.DATABASE_TYPE_MYSQL, "MySQLDialect");
		databaseMappings.put(Constants.DATABASE_TYPE_ORACLE, "OracleDialect");
		// databaseMappings.put(Constants.DATABASE_TYPE_SQLSERVER, "SqlServerDialect");
		databaseMappings.put(Constants.DATABASE_TYPE_SQLSERVER, "SqlServerExtDialect");
		databaseMappings.put(Constants.DATABASE_TYPE_DERBY, "DerbyDialect");
		databaseMappings.put(Constants.DATABASE_TYPE_DM, "DmDialect");// DM(达梦数据库--一个国产的数据库)
		databaseMappings.put(Constants.DATABASE_TYPE_KB, "KBDialect");// 人大金仓
		databaseMappings.put(Constants.DATABASE_TYPE_H2, "H2Dialect");
	}

	protected DBDialect() {
	}

	public String toString() {
		return getClass().getName();
	}

	/**
	 * Does this DIALECT support the <tt>ALTER TABLE</tt> syntax?
	 * 
	 * @return boolean
	 */
	public boolean hasAlterTable() {
		return true;
	}

	/**
	 * Do we need to drop constraints before dropping tables in this DIALECT?
	 * 
	 * @return boolean
	 */
	public boolean dropConstraints() {
		return true;
	}

	/**
	 * Do we need to qualify index names with the schema name?
	 * 
	 * @return boolean
	 */
	public boolean qualifyIndexName() {
		return true;
	}

	/**
	 * Does this DIALECT support the <tt>FOR UPDATE</tt> syntax?
	 * 
	 * @return boolean
	 */
	public boolean supportsForUpdate() {
		return true;
	}

	/**
	 * Does this DIALECT support <tt>FOR UPDATE OF</tt>, allowing particular rows to be
	 * locked?
	 */
	public boolean supportsForUpdateOf() {
		return false;
	}

	/**
	 * Does this DIALECT support the Oracle-style <tt>FOR UPDATE NOWAIT</tt> syntax?
	 * 
	 * @return boolean
	 */
	public boolean supportsForUpdateNowait() {
		return false;
	}

	/**
	 * Does this DIALECT support the <tt>UNIQUE</tt> column syntax?
	 * 
	 * @return boolean
	 */
	public boolean supportsUnique() {
		return true;
	}

	public String getMaxColumnValue(String schema, String table, String column) {
		return "SELECT MAX(" + column + ") FROM " + schema + "." + table;
	}

	/**
	 * The syntax used to add a column to a table (optional).
	 */
	public String getAddColumnString() {
		throw new UnsupportedOperationException("No add column syntax supported by Dialect");
	}

	/**
	 * The syntax used to add a foreign key constraint to a table.
	 * 
	 * @return String
	 */
	public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey,
			String referencedTable, String[] primaryKey) {
		return new StringBuffer(30).append(" add constraint ").append(constraintName)
				.append(" foreign key (").append(StringUtil.join(StringUtil.COMMA_SPACE, foreignKey)).append(
						") references ").append(referencedTable).toString();
	}

	/**
	 * The syntax used to add a primary key constraint to a table.
	 * 
	 * @return String
	 */
	public String getAddPrimaryKeyConstraintString(String constraintName) {
		return " add constraint " + constraintName + " primary key ";
	}

	/**
	 * The keyword used to specify a nullable column.
	 * 
	 * @return String
	 */
	public String getNullColumnString() {
		return StringUtil.EMPTY_STRING;
	}

	/**
	 * Does this DIALECT support identity column key generation?
	 * 
	 * @return boolean
	 */
	public boolean supportsIdentityColumns() {
		return false;
	}

	/**
	 * Does this DIALECT support sequences?
	 * 
	 * @return boolean
	 */
	public boolean supportsSequences() {
		return false;
	}

	public String getIdentitySelectString() throws DAOException {
		throw new DAOException("Dialect does not support native key generation");
	}

	/**
	 * The syntax that returns the identity value of the last insert, if native key
	 * generation is supported.
	 * 
	 * @throws DAOException if no native key generation
	 */
	public String getIdentitySelectString(String table) throws DAOException {
		throw new DAOException("Dialect does not support native key generation");
	}

	/**
	 * The keyword used to specify an identity column, if native key generation is
	 * supported.
	 * 
	 * @throws DAOException if no native key generation
	 */
	public String getIdentityColumnString(String table) throws DAOException {
		throw new DAOException("Dialect does not support native key generation");
	}

	/**
	 * The keyword used to insert a generated value into an identity column (or null)
	 * 
	 * @return String
	 */
	public String getIdentityInsertString(String table) {
		return null;
	}

	/**
	 * used to reseed the Identity value
	 * 
	 * @param table
	 * @param start
	 * @return
	 */
	public String getAlterIdentityString(String table, long start) {
		return null;
	}

	public String getIdentityInsertOnString(String table, boolean on) {
		return null;
	}

	/**
	 * The keyword used to insert a row without specifying any column values
	 */
	public String getNoColumnsInsertString() {
		return "values ( )";
	}

	/**
	 * The syntax that fetches the next value of a sequence, if sequences are supported.
	 * 
	 * @param sequenceName the name of the sequence
	 * @return String
	 * @throws DAOException if no sequences
	 */
	public String getSequenceNextValString(String sequenceName) throws DAOException {
		throw new DAOException("Dialect does not support sequences");
	}

	/**
	 * The syntax used to create a sequence, if sequences are supported.
	 * 
	 * @param sequenceName the name of the sequence
	 * @return String
	 * @throws DAOException if no sequences
	 */
	public String getCreateSequenceString(String sequenceName) throws DAOException {
		throw new DAOException("Dialect does not support sequences");
	}

	/**
	 * The syntax used to drop a sequence, if sequences are supported.
	 * 
	 * @param sequenceName the name of the sequence
	 * @return String
	 * @throws DAOException if no sequences
	 */
	public String getDropSequenceString(String sequenceName) throws DAOException {
		throw new DAOException("Dialect does not support sequences");
	}

	/**
	 * A query used to find all sequences
	 * 
	 */
	public String getQuerySequencesString() {
		return null;
	}

	public boolean isSupportAlterSequenceRestart() {
		return false;
	}

	/**
	 * 如果不支持直接修改sequence的开始值，那么得到修改前所需操作的sql
	 * 
	 * @return
	 * @throws DAOException
	 */
	public String getBeforeAlterSequenceRestart(String seqname) throws DAOException {
		throw new DAOException("Dialect does not support sequences");
	}

	/**
	 * A query used to find given sequence
	 * 
	 */
	public String getQuerySequencesString(String sequenceName) {
		return null;
	}

	/**
	 * A syntax used to alter and restart sequence value
	 * 
	 * @return
	 */
	public String getAlterSequencesString(String sequenceName, long newValue) {
		return null;
	}

	/**
	 * Get the <tt>Dialect</tt> specified by the given properties or system properties.
	 * 
	 * @param props
	 * @return Dialect
	 * @throws DAOException
	 */
	public static DBDialect getDialect(Connection conn) throws DAOException {
		String dialectName = "org.ganjp.core.db.dialect." + databaseMappings.get(MultiDBSupportUtil.getDBType(conn));
		try {
			return (DBDialect) Class.forName(dialectName).newInstance();
		} catch (ClassNotFoundException cnfe) {
			throw new DAOException("Dialect class not found: " + dialectName);
		} catch (Exception e) {
			throw new DAOException("Could not instantiate DIALECT class", e);
		}
	}

	public static DBDialect getDialect(String dbname) throws DAOException {
		String dialectName = "org.ganjp.core.db.dialect." + databaseMappings.get(dbname);

		try {
			return (DBDialect) Class.forName(dialectName).newInstance();
		} catch (ClassNotFoundException cnfe) {
			throw new DAOException("Dialect class not found: " + dialectName);
		} catch (Exception e) {
			throw new DAOException("Could not instantiate DIALECT class", e);
		}
	}

	/**
	 * Retrieve a set of default Hibernate properties for this database.
	 * 
	 * @return a set of Hibernate properties
	 */
	public final Properties getDefaultProperties() {
		return properties;
	}

	/**
	 * Completely optional cascading drop clause
	 * 
	 * @return String
	 */
	public String getCascadeConstraintsString() {
		return StringUtil.EMPTY_STRING;
	}

	/**
	 * The name of the SQL function that transforms a string to lowercase
	 * 
	 * @return String
	 */
	public String getLowercaseFunction() {
		return "lower";
	}

	/**
	 * Does this <tt>Dialect</tt> have some kind of <tt>LIMIT</tt> syntax?
	 */
	public boolean supportsLimit() {
		return false;
	}

	/**
	 * Add a <tt>LIMIT</tt> clause to the given SQL <tt>SELECT</tt>
	 * 
	 * @return the modified SQL
	 */
	public String getLimitString(String querySelect, int start, int end) {
		throw new UnsupportedOperationException("paged queries not supported");
	}

	public String getCountResultString(String querySql) {
		throw new UnsupportedOperationException("Not Implemented!");
	}

	/**
	 * Does the <tt>LIMIT</tt> clause specify arguments in the "reverse" order limit,
	 * offset instead of offset, limit?
	 * 
	 * @return true if the correct order is limit, offset
	 */
	public boolean bindLimitParametersInReverseOrder() {
		return false;
	}

	/**
	 * Does the <tt>LIMIT</tt> clause come at the start of the <tt>SELECT</tt>
	 * statement, rather than at the end?
	 * 
	 * @return true if limit parameters should come before other parameters
	 */
	public boolean bindLimitParametersFirst() {
		return false;
	}

	/**
	 * Does the <tt>LIMIT</tt> clause take a "maximum" row number instead of a total
	 * number of returned rows?
	 */
	public boolean useMaxForLimit() {
		return false;
	}

	/**
	 * Should we use a <tt>LIMIT</tt> clause when there is no first result specified?
	 */
	public boolean preferLimit() {
		return false;
	}

	/**
	 * The opening quote for a quoted identifier
	 */
	public char openQuote() {
		return '"';
	}

	/**
	 * The closing quote for a quoted identifier
	 */
	public char closeQuote() {
		return '"';
	}

	/**
	 * Aggregate SQL functions as defined in general. This results of this method should
	 * be integrated with the specialisation's data.
	 */
	public Map getAggregateFunctions() {
		return aggregateFunctions;
	}

	// --------------------------- ADD FOR ORM------------

	private final TypeNames typeNames = new TypeNames();

	/**
	 * Get the name of the database type associated with the given <tt>java.sql.Types</tt>
	 * typecode.
	 * 
	 * @param code <tt>java.sql.Types</tt> typecode
	 * @param length the length or precision of the column
	 * @param precision the scale of the column
	 * @param scale TODO
	 * 
	 * @return the database type name
	 */
	public String getTypeName(int code, int length, int precision, int scale) {
		String result = typeNames.get(code, length, precision, scale);
		if (result == null) {
			throw new DAOException("No type mapping for java.sql.Types code: " + code + ", length: " + length);
		}
		return result;
	}

	/**
	 * 是否支持对无order by的查询进行排序
	 * 
	 * @return
	 */
	public boolean supportsLimitNoOrderBy(String sql) {
		return true;
	}
}
