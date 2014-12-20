/*
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 * 
 */
package org.ganjp.core.dao;

import org.ganjp.core.db.dialect.DBDialect;

/**
 * 
 * 
 * @author BizFoundation Team: zhaoqy
 * @version1.0
 * @since 4.2 fixpatch2
 * 
 * $Revision: 1.1 $
 *
 */
public class DbMeta {
	
	private String dbType;
	
	private DBDialect dialect;

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public DBDialect getDialect() {
		return dialect;
	}

	public void setDialect(DBDialect dialect) {
		this.dialect = dialect;
	}

}
