package org.ganjp.core.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.ganjp.core.db.dbutils.DbUtils;
import org.ganjp.core.db.dialect.DBDialect;
import org.ganjp.core.exception.dao.DAOException;

public class DataSourceHolder {
	private static final ThreadLocal contextHolder = new ThreadLocal();
	private static DataSource datasource = null;
	/**
	 * 根据Connection获取数据库信息
	 * 不要在这个方法里面关闭Connection
	 * 
	 * @param conn
	 * @return
	 */
	public static DbMeta getDbMeta(Connection conn){
		DbMeta meta = new DbMeta();
		meta.setDbType(MutiDBSupportUtil.getDBType(conn));
		meta.setDialect(DBDialect.getDialect(meta.getDbType()));
		return meta;
	}
	
	/**
	 * 根据DataSource获取数据库信息
	 * @param currentDs
	 * @return
	 */
	public static DbMeta getDbMeta(){
		Connection conn = null;
		DbMeta meta = new DbMeta();
		try {
			conn = datasource.getConnection();
			meta.setDbType(MutiDBSupportUtil.getDBType(conn));
			meta.setDialect(DBDialect.getDialect(meta.getDbType()));
		} catch (SQLException e) {
			throw new DAOException("ErrorCode:10001,获取数据库方言报错",e,1001);
		}finally{
			//由于设置的事务是*Server，所以需要手动关闭
			DbUtils.closeQuietly(conn);
		}
		return meta;
	}

	public static void setDataSource(DataSource ds) {
		datasource = ds;
	}
	
	public static DataSource getDataSource() {
		return datasource;
	}
	

	public static String getConnection() {
		return (String) contextHolder.get();
	}
}