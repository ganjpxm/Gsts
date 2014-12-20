package org.ganjp.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.ganjp.core.Configuration;
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
public abstract class BaseDAO {
	protected Logger log = LoggerFactory.getLogger(getClass());
	private Connection conn=null;
	public BaseDAO() {
		
	}

	/**
	 * 这个方法负责加载驱动程序，并负责创建连接对象,异常不要抓取，throws出去
	 */
	private void openConnection() {
		try {
			if(conn==null || conn.isClosed()) {
				if (null == DataSourceHolder.getDataSource()) {
					String driveClass = Configuration.getString("database.driver_class");
					String url = Configuration.getString("database.url");
					String userName = Configuration.getString("database.username");
					String password = Configuration.getString("database.password");
					if (StringUtil.isBlank(driveClass) || StringUtil.isBlank(url) || StringUtil.isBlank(userName) ) {
						log.error("please set database.driver_class,database.url,database.username,database.usernam value in jp.properties");
					} else {
						DbUtils.loadDriver(driveClass); 
						conn = DriverManager.getConnection(url, userName, password);
					}
				} else {
					conn = DataSourceHolder.getDataSource().getConnection();
				}
				log.debug("get connection success");
			}
		} catch(Exception ex) {
			log.error("数据库获取连接出错：" + ex.getMessage());
		}
	}
	
	protected Connection getConnection() {
		openConnection();
		return conn;
	}
	
	private QueryRunner createQueryRunner() {
		openConnection();
		return new QueryRunner();
	}
	
	
	/**
	 * 增、删、改（无参数）， 该方法拥有执行sql命令，这种sql命令没有返回结果集，用于insert,update,delete语句
     * 调用该方法的时候需要给它传递sql语句
	 * @param sql
	 * @throws SQLException
	 */
	protected void update(String sql, boolean isCloseConnection) {
		try {
			QueryRunner runner = createQueryRunner();
			runner.update(conn, sql);
		} catch(Exception ex) {
			log.error("执行更新出错：" + ex.getMessage());
		} finally {
			if (isCloseConnection) {
	    		closeConnection();
	    	}
		}
	}
	
	/**
	 * 增、删、改（带参数）
	 * @param sql
	 * @param paramArr
	 * @throws SQLException
	 */
	protected int update(String sql, Object[] paramArr, boolean isCloseConnection) {
		try {	
			QueryRunner runner = createQueryRunner();
			return runner.update(conn, sql, paramArr);
		} catch (Exception ex) {
			log.error("执行更新出错：" + ex.getMessage());
		} finally {
			if (isCloseConnection) {
	    		closeConnection();
	    	}
		}
		return 0;
	}
	
	/**
	 * 对一条sql语句执行多次
	 * @param sql  
	 * @param list: List(Object[]｛"xiaogan","2008"｝,Object[]｛"ganjp","2008"｝)
	 * @throws SQLException
	 */
	protected int[] batchExecute(String sql, List paramArrList, boolean isCloseConnection) {
		PreparedStatement ps = null;
		try {
			QueryRunner runner = createQueryRunner();
			ps = conn.prepareStatement(sql);
			for (Iterator iterator = paramArrList.iterator(); iterator.hasNext();) {
				Object[] paramArr = (Object[]) iterator.next();
				runner.fillStatement(ps, paramArr);
				ps.addBatch();
			}
			return ps.executeBatch();
	    } catch (SQLException e) {
	        log.error("执行批量更新出错：" + e.getMessage());
	    } finally {
	    	DbUtils.closeQuietly(ps);
	    	if (isCloseConnection) {
	    		closeConnection();
	    	}
	    }
		return null;
	}
	
	protected int[] batchDeleteByIdArr(String sql, Object[] primaryKeyValueArr, boolean isCloseConnection) {
		PreparedStatement ps = null;
		try {
			QueryRunner runner = createQueryRunner();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < primaryKeyValueArr.length; i++) {
				Object primaryKey = primaryKeyValueArr[i];
				runner.fillStatement(ps, new Object[]{primaryKey});
				ps.addBatch();
			}
			return ps.executeBatch();
	    } catch (SQLException e) {
	        log.error("执行批量删除出错：" + e.getMessage());
	    } finally {
	    	DbUtils.closeQuietly(ps);
	    	if (isCloseConnection) {
	    		closeConnection();
	    	}
	    }
		return null;
	}
	/**
	 * 执行多条sql语句（事务处理）
	 * 输入： String[] sqlArr = new String[3];
	 *	   sqlArr[0] = "insert into org (org_id, org_pid, org_cd, org_name, leaf_flag) values(?,?,?,?,?)";
	 *	   sqlArr[1] = "update org set org_cd=? where org_id=?";
	 *	   List paramArrList = new ArrayList();
	 *	   paramArrList.add(new Object[] {"2", "0", "02", "北京1", "0"});
	 *	   paramArrList.add(new Object[] {"05", "2"});
	 * @param sqlArr
	 * @param paramArrList
	 * @param isCloseConnection
	 */
	protected void batchExecute(String[] sqlArr, List paramArrList, boolean isCloseConnection) {
		if (sqlArr==null || paramArrList==null || paramArrList.isEmpty() || paramArrList.size()!=sqlArr.length ) {
			return;
		}
		PreparedStatement ps = null;
		try {
			QueryRunner runner = createQueryRunner();
			//关闭Connection的自动提交
			conn.setAutoCommit(false);
			int i = 0;
			for (Iterator iterator = paramArrList.iterator(); iterator.hasNext(); i++) {
				Object[] paramArr = (Object[]) iterator.next();
				ps = conn.prepareStatement(sqlArr[i]);
				runner.fillStatement(ps, paramArr);
				ps.execute();
			}
			conn.commit(); 
	    } catch (SQLException sqlEx) {
	    	this.rollback();
	        log.error("执行批量更新出错：" + sqlEx.getMessage());
	    } finally {
	    	DbUtils.closeQuietly(ps);
	    	if (isCloseConnection) {
	    		closeConnection();
	    	}
	    }
	}
	
	/**
	 * 
	 * @param sql
	 * @param object: new User("ganjp","12")
	 * @param propertyName: new String()[]{"name","password"}
	 * @throws SQLException
	 */
	protected int update(String sql, Object object, String[] propertyNameArr, boolean isCloseConnection) {
		PreparedStatement ps = null;
		try { 
			QueryRunner runner = createQueryRunner();
			ps = conn.prepareStatement(sql);
			runner.fillStatementWithBean(ps, object, propertyNameArr);   
			return ps.executeUpdate(); 
		} catch (SQLException e) {
	        log.error("执行更新出错：" + e.getMessage());
	    } finally {
	    	DbUtils.closeQuietly(ps);
	    	if (isCloseConnection) {
	    		closeConnection();
	    	}
	    }
		return 0;
	}
	
	/**
	 * 
	 * @param sql
	 * @param beanArr
	 * @param propertyNameArr
	 * @return
	 * @throws SQLException
	 */
	protected int[] batchUpdate(String sql, Object[] beanArr, String[] propertyNameArr, boolean isCloseConnection) {
		PreparedStatement ps = null;
		try {
			QueryRunner runner = createQueryRunner();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < beanArr.length; i++) {
				Object bean = beanArr[i];
				runner.fillStatementWithBean(ps, bean, propertyNameArr);
				ps.addBatch();
			}
			return ps.executeBatch();
		} catch (SQLException e) {
	        log.error("执行批量更新出错：" + e.getMessage());
	    } finally {
	    	DbUtils.closeQuietly(ps);
	    	if (isCloseConnection) {
	    		closeConnection();
	    	}
	    }
		return null;
	}

	/**
	 * 
	 * @param sql
	 * @param beanList: list(new User("ganjp","12"))
	 * @param propertyName: new String()[]{"name","password"}
	 * @return
	 * @throws SQLException
	 */
	protected int[] batchUpdate(String sql, List beanList, String[] propertyNameArr, boolean isCloseConnection) {
		return this.batchUpdate(sql, beanList.toArray(), propertyNameArr, isCloseConnection);
	}
	
	/**
	 * 获得查询对象
	 * 
	 * @param sql
	 * @param param
	 * @param rsh
	 * @return
	 * @throws SQLException
	 */
	protected Object getObject(String sql, Object[] param, ResultSetHandler rsh, boolean isCloseConnection) {
		try {	
			QueryRunner runner = createQueryRunner();
			return runner.query(conn, sql, rsh, param);
		} catch (Exception ex) {
			log.error("执行更新出错：" + ex.getMessage());
		} finally {
			if (isCloseConnection) {
	    		closeConnection();
	    	}
		}
		return null;
	}
	
	
	
	/**
	 * 获取查询对象
	 * 
	 * @param sql
	 * @param param
	 * @param rsh
	 * @return
	 * @throws SQLException
	 */
	protected Object getObject(String sql, Object param, ResultSetHandler rsh, boolean isCloseConnection) {
		return getObject(sql, new Object[]{param}, rsh, isCloseConnection);
	}
	
	protected Object getObject(String sql, Object param, Class classs, boolean isCloseConnection) {
		ResultSetHandler rsh = new BeanHandler(classs);
		return getObject(sql, new Object[]{param}, rsh, isCloseConnection);
	}
	
	protected Object getObject(String sql, ResultSetHandler rsh, boolean isCloseConnection) {
		try {	
			QueryRunner runner = createQueryRunner();
			return runner.query(conn, sql, rsh);
		} catch (Exception ex) {
			log.error("执行getObject出错：" + ex.getMessage());
		} finally {
			if (isCloseConnection) {
	    		closeConnection();
	    	}
		}
		return null;
	}
	
	/** 无参数查询
	 * @param sql
	 * @param rsh
	 * @return
	 * @throws SQLException
	 */
	protected List getObjectList(String sql, ResultSetHandler rsh, boolean isCloseConnection) {
		return (List)this.getObject(sql, rsh, isCloseConnection);
	}
	
	protected List getObjectList(String sql, Class classs, boolean isCloseConnection) {
		ResultSetHandler rsh = new BeanListHandler(classs);
		return getObjectList(sql, rsh, isCloseConnection);
	}
	
	protected List getMapList(String sql, boolean isCloseConnection) throws SQLException {
		return getObjectList(sql, new MapListHandler(), isCloseConnection);
	}
	
	/**
	 * 
	 * @param sql
	 * @param param
	 * @param rsh
	 * @param isCloseConnection
	 * @return
	 */
	protected List getObjectList(String sql, Object[]param, ResultSetHandler rsh, boolean isCloseConnection) {
		try {
			QueryRunner runner = createQueryRunner();
			return (List)runner.query(conn, sql, rsh, param);
		} catch (SQLException e) {
			log.error("获取对象集出错：" + e.getMessage());
	    } finally {
	    	if (isCloseConnection) {
	    		closeConnection();
	    	}
	    }
		return null;
	}
	
	protected List getObjectList(String sql, Object[] paramArr, Class classs, boolean isCloseConnection) {
		ResultSetHandler rsh = new BeanListHandler(classs);
		return getObjectList(sql, paramArr, rsh, isCloseConnection);
	}
	
	protected List getObjectList(String sql, Object param, Class classs, boolean isCloseConnection) {
		return getObjectList(sql, new Object[]{param}, classs, isCloseConnection);
	}
	
	protected List getMapList(String sql, Object[]param, boolean isCloseConnection) {
		return getObjectList(sql, param, new MapListHandler(), isCloseConnection);
	}
	
	protected List getMapList(String sql, Object param, boolean isCloseConnection) {
		return getObjectList(sql, new Object[]{param}, new MapListHandler(), isCloseConnection);
	}
	
	protected Page getObjectPage(final String sql, Object[] paramArr, ResultSetHandler rsh, Page page) throws SQLException{
		if(conn==null || conn.isClosed()){
			openConnection();
		}
		int totalRowCount = 0;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			QueryRunner runner = null;
			if (paramArr != null && paramArr.length>0) {
				runner = createQueryRunner();
			}
			DBDialect dbDialect = DBDialect.getDialect(conn);
			if (page.isAutoCount()) {
				String countSql = dbDialect.getCountResultString(sql);
				ps = conn.prepareStatement(countSql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				if (runner!=null) {
					runner.fillStatement(ps, paramArr);
				}
				rs = ps.executeQuery();
				if (rs.next()) {
					totalRowCount = rs.getInt(1);
					page.setTotalCount(totalRowCount);
				}
				page.setAutoCount(false);
			}
			int start = page.getFirst() - 1;
			String limitSql = dbDialect.getLimitString(sql, start, getMaxOrLimit(page, dbDialect));
			ps = conn.prepareStatement(limitSql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			if (runner!=null) {
				runner.fillStatement(ps, paramArr);
			}
			rs = ps.executeQuery();
			page.setResult((List)rsh.handle(rs));
		} catch (java.sql.SQLException se) {
			throw se;
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
		}
		return page;
	}
	
	protected Page getObjectPage(final String sql, Object[] paramArr, Class classs, Page page) throws SQLException {
		ResultSetHandler rsh = new BeanListHandler(classs);
		return this.getObjectPage(sql, paramArr, rsh, page);
	}
	
	protected Page getObjectPage(final String sql, Object[] paramArr, Class classs, Page page, boolean isCloseConnection) 
		throws SQLException {
		this.getObjectPage(sql, paramArr, classs, page);
		if (isCloseConnection) {
    		closeConnection();
    	}
		return page;
	}
	
	protected Page getObjectPage(final String sql, Class classs, Page page) throws SQLException {
		ResultSetHandler rsh = new BeanListHandler(classs);
		return this.getObjectPage(sql, null, rsh, page);
	}
	
	protected Page getObjectPage(final String sql, Class classs, Page page, boolean isCloseConnection) throws SQLException {
		this.getObjectPage(sql, classs, page);
		if (isCloseConnection) {
    		closeConnection();
    	}
		return page;
	}
	protected List getMapPage(String sql, boolean isCloseConnection) throws SQLException {
		return getObjectList(sql, new MapListHandler(), isCloseConnection);
	}
	/**
	 * 根据数据库方言，或者分页end值
	 * 
	 * @param page
	 * @param DIALECT
	 * @return
	 */
	public static int getMaxOrLimit(final Page page, final DBDialect dialect) {
		final int firstRow = page.getFirst() - 1;
		final int lastRow = page.getPageSize();
		if (!dialect.useMaxForLimit()) {
			return lastRow;
		} else {
			return lastRow + firstRow;
		}
	}
	
	/**
	 * 关闭连接
	 * @throws SQLException
	 */
	public void closeConnection() {
		try {
			if (conn!=null) {
				if (!conn.isClosed()) {
					DbUtils.close(conn);
					log.debug("close connection success");
				}
			}
		} catch(SQLException ex) {
			log.error("关闭连接失败:"+ex.getMessage());
		}
	}
	
	/**
	 * 开启事务
	 */
	public void openTransaction() {
		try {
			if (conn==null || conn.isClosed()) {
				this.openConnection();
			}
			conn.setAutoCommit(true);
			log.debug("open transaction success");
		} catch(SQLException ex) {
			log.error("开启事务失败:"+ex.getMessage());
		}
	} 
	
	/**
	 * 提交事务
	 */
	public void commit() {
		try {
			if (conn!=null) {
				if (!conn.isClosed()) {
					conn.commit();
					log.debug("submit transaction success");
				}
			}
		} catch(SQLException ex) {
			log.error("提交事务失败:"+ex.getMessage());
		}
	}
	/**
	 * 事务回滚
	 */
	public void rollback() {
		try {
			if (conn!=null) {
				if (!conn.isClosed()) {
					conn.rollback();
					log.debug("roll transaction success");
				}
			}
		} catch(SQLException ex) {
			log.error("事务回滚失败:"+ex.getMessage());
		}
	}
	
}

