package org.ganjp.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.ganjp.am.model.AmRole;
import org.ganjp.core.Configuration;
import org.ganjp.core.Constants;
import org.ganjp.core.db.base.Page;
import org.ganjp.core.db.dbutils.DbUtils;
import org.ganjp.core.db.dbutils.QueryRunner;
import org.ganjp.core.db.dbutils.ResultSetHandler;
import org.ganjp.core.db.dbutils.handles.BeanHandler;
import org.ganjp.core.db.dbutils.handles.BeanListHandler;
import org.ganjp.core.db.dbutils.handles.MapListHandler;
import org.ganjp.core.db.dialect.DBDialect;
import org.ganjp.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用dbutils1.2，执行完以后一定要把连接关闭
 * 修改了BeanProcessor.java文件：在395增加String columnName = rsmd.getColumnName(col);;
 * 
 * 
 * @author ganjp
 *
 */
public class CommonDAO extends BaseDAO {
	protected Logger log = LoggerFactory.getLogger(getClass());
}

