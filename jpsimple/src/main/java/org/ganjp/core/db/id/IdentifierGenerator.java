/*
 * $Id: IdentifierGenerator.java,v 1.2 2010/12/06 06:43:33 zhaoqy Exp $
 *
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 * 
 */
package org.ganjp.core.db.id;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import org.ganjp.core.exception.dao.DAOException;

/**
 * The general contract between a class that generates unique identifiers and
 * the <tt>Session</tt>. It is not intended that this interface ever be
 * exposed to the application. It <b>is</b> intended that users implement this
 * interface to provide custom identifier generation strategies.<br>
 * <br>
 * Implementors should provide a public default constructor.<br>
 * <br>
 * Implementations that accept configuration parameters should also implement
 * <tt>Configurable</tt>. <br>
 * Implementors <em>must</em> be threadsafe
 * 
 */
public interface IdentifierGenerator {

	/**
	 * 生成一个新的标识符
	 * 
	 * @param Connection
	 * @param object 
	 * 	the entity or toplevel collection for which the id is being generated
	 * @return Serializable a new identifier
	 * @throws SQLException
	 * @throws IdentifierGenerationException
	 */
	public Serializable generate(Connection conn, Object object)
			throws SQLException, DAOException;
}
