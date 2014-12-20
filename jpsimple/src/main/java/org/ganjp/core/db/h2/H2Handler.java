/**
 * $Id: H2Startup.java,v 1.1 2011/04/18 09:30:48 ganjp Exp $
 *
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 *
 */
package org.ganjp.core.db.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于容器启动时，初始化一些参数放置在Constants.WEB_CONFIG的Map对象里
 * 
 * @author ResourceOne BizFoundation Team: ganjp
 * 
 * @since 1.0
 */
public class H2Handler {
	private static Logger log = LoggerFactory.getLogger(H2Handler.class);
	private static Server serverWeb;
	private static Server serverTcp;
	
	/**
	 * 启动H2
	 */
	public static void startH2() {
		try {
			serverWeb = Server.createWebServer(new String[] { "-trace" });//"-webPort", "8082"
			serverTcp = Server.createTcpServer(new String[] { "-tcpPort", "9092" });
			serverWeb.start();
			serverTcp.start();
			//jdbc:h2:http://localhost:8082/~/test
			log.info("access url: 'http://localhost:8082'");
			//jdbc:h2:tcp://localhost/~/test  jdbc:h2:tcp://localhost:9092/~/test (user: sa, password: )
			log.info("database jdbc url='jdbc:h2:tcp://localhost:9092/file:c:/db/test', username='sa', password='', " +
					" driverClassName:org.h2.Driver。start H2 success");
	    } catch (Exception ex) {
	    	log.error(ex.getMessage());
	    }
	}
	
	 /**
	 * 创建表
	 * 
	 * @param conn
	 */
	public static void createTable(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("create table if not exists bm_config(config_id char(32), config_key varchar(40) primary key, " +
					"config_value varchar(50), description varchar(250), data_state char(1))");
			stmt.execute("create table if not exists bm_record(record_id char(32) primary key, user_id char(32), " +
					"table_name varchar(20), operate_type char(1), remark varchar(50), create_time timestamp)"); 
		    stmt.execute("create table if not exists bm_code_name(code_name_id char(32) primary key, code_name_pid char(32), " +
		    		"code varchar(50) not null, name varchar(50) not null, show_order integer, leaf_flag char(1), " +
		    		"data_state char(1), create_time timestamp)");
		    stmt.execute("create table if not exists am_user(user_id char(32) primary key, user_cd varchar(40) not null, " +
		    		" user_name varchar(30) not null, password varchar(30), birthday date, data_state char(1), create_time timestamp");
		    stmt.execute("create table if not exists am_user_detail(user_detail_id char(32) primary key, user_id char(32), " +
		    		"country_id char(32), province_id(32), city_id(32), birth_place varchar(100), live_place varchar(100), " +
		    		"attach blob, attach_name varchar(40), data_state char(1))");
		    stmt.execute("create table if not exists am_role(role_id char(32) primary key, role_cd varchar(40) not null, " +
		    		"role_name varchar(30) not null, description varchar(250), data_state char(1), create_time timestamp)");
		    stmt.execute("create table if not exists am_user_role(user_role_id char(32) primary key, user_id char(32) not null, " +
		    		"role_id varchar(32) not null, data_state char(1))");
		    log.info("create org table success");
		} catch (Exception ex) {
	    	log.error("create table fail：" + ex.getMessage());
	    }
	}
	
	/**
	 * 获得H2连接
	 * 
	 * @return Connection
	 */
	public static Connection getH2Connection() {
		try {
			Class.forName("org.h2.Driver");
	        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/file:c:/db/test", "sa", "");
	        return conn;
		} catch (Exception ex) {
	    	log.error(ex.getMessage());
	    	return null;
	    }
	}
	
	/**
	 * 关闭H2
	 */
	public static void stopH2() {
		if (serverWeb!=null || serverTcp!=null) {
			serverWeb.stop();
			serverTcp.stop();
			log.info("关闭H2成功");
		} else {
			log.info("H2已关闭");
		}
	}
	
	public static boolean isStartup(){
		if (serverWeb == null){
			return false;
		} else {
			return true;
		}
	}
	public static void main(String[] args) throws Exception {
		startH2();
//		stopH2();
//		H2Startup.createTable(H2Startup.getH2Connection());
	}
}
