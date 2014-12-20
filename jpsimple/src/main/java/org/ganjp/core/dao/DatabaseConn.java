package org.ganjp.core.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DatabaseConn {
	private static final String DRIVER = "com.mysql.jdbc.Driver"; //驱动 
	private static final String URL = "jdbc:mysql://localhost:3306/example?useUnicode=true&amp;characterEncoding=utf-8";//URL 
	private static final String NAME = "example"; //数据库登录账号 
	private static final String PASS = "1"; //数据库登录密码 

	public static Connection getConn(){
		Connection conn = null; 
		try { 
			Class.forName(DRIVER); //注册驱动 
			conn = DriverManager.getConnection(URL,NAME,PASS);//获取连接字符串 
		} catch (ClassNotFoundException e) { 
			System.out.println(e.getMessage() +"注册驱动失败"); 
			e.printStackTrace(); 
		} catch(SQLException e){ 
			System.out.println(e.getMessage() + "获取连接字符串错误"); 
			e.printStackTrace(); 
		}catch(Exception e){ 
			System.out.println(e.getMessage() + "数据库连接错误"); 
		} 
		return conn; 
	}
	
	//通过连接池获得连接的
	public static synchronized Connection getJNDIConnection() throws Exception {
		Connection conn = null;
		try {
			Context initialContext = new InitialContext();
			if (initialContext == null){
		        System.out.println("JNDI problem. Cannot get InitialContext.");
		    }
			DataSource datasource = (DataSource)initialContext.lookup("jdbc/example");
			if (datasource != null) {
				conn = datasource.getConnection();
		    } else {
		    	System.out.println("Failed to lookup datasource.");
		    }
			
			return conn;
		} catch (SQLException e) {
			throw e;
		} catch(NamingException e) {
			throw e;
		}
	}
}