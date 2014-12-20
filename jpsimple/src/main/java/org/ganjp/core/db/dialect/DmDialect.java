package org.ganjp.core.db.dialect;

/**
 * @author Rone Framework Team : zhaoqy
 * 
 * 国产达梦数据库方言
 * 
 * @version Revision: 1.0
 * @since 4.0
 * @param <DmDialect>
 */
public class DmDialect extends DBDialect {
	public DmDialect() {
		super();
	}

	/**
	 * Does this DIALECT support identity column key generation?
	 * 
	 * @return True if IDENTITY columns are supported; false otherwise.
	 */
	public boolean supportsIdentityColumns() {
		return true;
	}

	/**
	 * Whether this DIALECT have an Identity clause added to the data type or a
	 * completely seperate identity data type
	 * 
	 * @return boolean
	 */
	public boolean hasDataTypeInIdentityColumn() {
		return true;
	}

	/**
	 * Get the select command to use to retrieve the last generated IDENTITY
	 * value.
	 * 
	 * @return The appropriate select command
	 * @throws MappingException
	 *             If IDENTITY generation is not supported.
	 */
	public String getIdentitySelectString() {
		return "select scope_identity()";
	}

	public String appendIdentitySelectToInsert(String insertSQL) {
		return insertSQL + " select scope_identity()";
	}

	/**
	 * The syntax used during DDL to define a column as being an IDENTITY.
	 * 
	 * @return The appropriate DDL fragment.
	 * @throws MappingException
	 *             If IDENTITY generation is not supported.
	 */
	public String getIdentityColumnString() {
		return "identity not null";
	}

	// SEQUENCE support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Does this DIALECT support sequences?
	 * 
	 * @return True if sequences supported; false otherwise.
	 */
	public boolean supportsSequences() {
		return true;
	}

	/**
	 * Generate the appropriate select statement to to retreive the next value
	 * of a sequence. <p/> This should be a "stand alone" select statement.
	 * 
	 * @param sequenceName
	 *            the name of the sequence
	 * @return String The "nextval" select string.
	 * @throws MappingException
	 *             If sequences are not supported.
	 */
	public String getSequenceNextValString(String sequenceName) {
		return "select " + sequenceName + ".nextval";
	}

	public String getQuerySequencesString(String sequenceName) {
		return "select name from SYSDBA.SYSSEQUENCES where name = '"
				+ sequenceName.toUpperCase() + "'";
	}

	/**
	 * Typically dialects which support sequences can create a sequence with a
	 * single command. This is convenience form of
	 * {@link #getCreateSequenceStrings} to help facilitate that. <p/> Dialects
	 * which support sequences and can create a sequence in a single command
	 * need *only* override this method. Dialects which support sequences but
	 * require multiple commands to create a sequence should instead override
	 * {@link #getCreateSequenceStrings}.
	 * 
	 * @param sequenceName
	 *            The name of the sequence
	 * @return The sequence creation command
	 * @throws MappingException
	 *             If sequences are not supported.
	 */
	public String getCreateSequenceString(String sequenceName) {
		return "create sequence " + sequenceName;
	}

	/**
	 * Typically dialects which support sequences can drop a sequence with a
	 * single command. This is convenience form of
	 * {@link #getDropSequenceStrings} to help facilitate that. <p/> Dialects
	 * which support sequences and can drop a sequence in a single command need
	 * *only* override this method. Dialects which support sequences but require
	 * multiple commands to drop a sequence should instead override
	 * {@link #getDropSequenceStrings}.
	 * 
	 * @param sequenceName
	 *            The name of the sequence
	 * @return The sequence drop commands
	 * @throws MappingException
	 *             If sequences are not supported.
	 */
	public String getDropSequenceString(String sequenceName) {
		return "drop sequence " + sequenceName;
	}

	/**
	 * Get the command used to select a GUID from the underlying database. <p/>
	 * Optional operation.
	 * 
	 * @return The appropriate command.
	 */
	public String getSelectGUIDString() {
		return "select GUID()";
	}

	/**
	 * Does this DIALECT support some form of limiting query results via a SQL
	 * clause?
	 * 
	 * @return True if this DIALECT supports some form of LIMIT.
	 */
	public boolean supportsLimit() {
		return true;
	}

	/**
	 * Does this DIALECT's LIMIT support (if any) additionally support
	 * specifying an offset?
	 * 
	 * @return True if the DIALECT supports an offset within the limit support.
	 */
	public boolean supportsLimitOffset() {
		return supportsLimit();
	}

	/**
	 * ANSI SQL defines the LIMIT clause to be in the form LIMIT offset, limit.
	 * Does this DIALECT require us to bind the parameters in reverse order?
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
	 * Does the <tt>LIMIT</tt> clause take a "maximum" row number instead of a
	 * total number of returned rows? <p/> This is easiest understood via an
	 * example. Consider you have a table with 20 rows, but you only want to
	 * retrieve rows number 11 through 20. Generally, a limit with offset would
	 * say that the offset = 11 and the limit = 10 (we only want 10 rows at a
	 * time); this is specifying the total number of returned rows. Some
	 * dialects require that we instead specify offset = 11 and limit = 20,
	 * where 20 is the "last" row we want relative to offset (i.e. total number
	 * of rows = 20 - 11 = 9) <p/> So essentially, is limit relative from
	 * offset? Or is limit absolute?
	 * 
	 * @return True if limit is relative from offset; false otherwise.
	 */
	public boolean useMaxForLimit() {
		return false;
	}

	static int getAfterSelectInsertPoint(String sql) {
		int selectIndex = sql.toLowerCase().indexOf("select");
		int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
		return selectIndex + (selectDistinctIndex != selectIndex ? 6 : 15);
	}

	/**
	 * Given a limit and an offset, apply the limit clause to the query.
	 * 
	 * @param query
	 *            The query to which to apply the limit.
	 * @param offset
	 *            The offset of the limit
	 * @param limit
	 *            The limit of the limit ;)
	 * @return The modified query statement with the limit applied.
	 */
	public String getLimitString(String query, int offset, int limit) {
		return (new StringBuffer(query.length() + 8)).append(query).insert(
				getAfterSelectInsertPoint(query),
				" top " + offset + "," + limit + " ").toString();
	}

	public String getCountResultString(String querySql) {
		StringBuffer buf = new StringBuffer("select count(*) from (");
		buf.append(querySql);
		buf.append(")");
		return buf.toString();
	}

	/**
	 * Apply s limit clause to the query. <p/> Typically dialects utilize
	 * {@link #supportsVariableLimit() variable} limit caluses when they support
	 * limits. Thus, when building the select command we do not actually need to
	 * know the limit or the offest since we will just be using placeholders.
	 * <p/> Here we do still pass along whether or not an offset was specified
	 * so that dialects not supporting offsets can generate proper exceptions.
	 * In general, dialects will override one or the other of this method and
	 * {@link #getLimitString(String, int, int)}.
	 * 
	 * @param query
	 *            The query to which to apply the limit.
	 * @param hasOffset
	 *            Is the query requesting an offset?
	 * @return the modified SQL
	 */
	protected String getLimitString(String query, boolean hasOffset) {
		return (new StringBuffer(query.length() + 8)).append(query).insert(
				getAfterSelectInsertPoint(query),
				hasOffset ? " top ?, ? " : " top ? ").toString();
	}

	/**
	 * Does this DIALECT support a way to retrieve the database's current
	 * timestamp value?
	 * 
	 * @return True if the current timestamp can be retrieved; false otherwise.
	 */
	public boolean supportsCurrentTimestampSelection() {
		return true;
	}

	/**
	 * Retrieve the command used to retrieve the current timestammp from the
	 * database.
	 * 
	 * @return The command.
	 */
	public String getCurrentTimestampSelectString() {
		return "select current_timestamp";
	}

	// identifier quoting support ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * The character specific to this DIALECT used to begin a quoted identifier.
	 * 
	 * @return The DIALECT's specific open quote character.
	 */
	public char openQuote() {
		return '"';
	}

	/**
	 * The character specific to this DIALECT used to close a quoted identifier.
	 * 
	 * @return The DIALECT's specific close quote character.
	 */
	public char closeQuote() {
		return '"';
	}

	/**
	 * The syntax used to add a column to a table (optional).
	 * 
	 * @return The "add column" fragment.
	 */
	public String getAddColumnString() {
		return "add";
	}

	public String getBeforeAlterSequenceRestart(String sequenceName) {
		return getDropSequenceString(sequenceName);
	}

	public boolean isSupportAlterSequenceRestart(String sequenceName) {
		return false;
	}

	public String getAlterSequencesString(String sequenceName, long newValue) {
		return "create sequence " + sequenceName + " start with "
				+ String.valueOf(newValue);
	}
}