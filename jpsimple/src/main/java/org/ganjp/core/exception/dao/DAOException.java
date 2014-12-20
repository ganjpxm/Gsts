/*
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 */
package org.ganjp.core.exception.dao;

import java.sql.SQLException;

import org.ganjp.core.exception.base.UnexpectedException;
import org.ganjp.core.util.StringUtil;

/**
 * 是一个Runtime异常.
 * 
 * 
 * @author BizFoundation Team: zhaoqy
 * @since 4.2
 * 
 * $Revision: 1.2 $
 */
public class DAOException extends UnexpectedException {
	private static final long serialVersionUID = 5071002648081458703L;

	private String title = "数据库操作异常";
	private String sql = "";
	private String sqlstate = "";

	/**
	 * 构造器
	 */
	public DAOException(String msg) {
		super(msg);
	}

	/**
	 * 构造器
	 * 
	 * @param msg:错误内容
	 * @param e
	 */
	public DAOException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public DAOException(String msg, Throwable cause,int errorCode) {
		super(msg, cause);
		this.errorCode = errorCode;
	}

	/**
	 * 构造函数
	 * 
	 * @param title 异常标题
	 * @param msg 异常信息
	 * @param cause
	 */
	public DAOException(String title, String msg, Throwable cause) {
		super(msg, cause);
		this.title = title;
	}

	/**
	 * 构造器
	 * 
	 * @param e
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}

	public String getErrorTitle() {
		return title;
	}

	public String getSql() {
		return this.sql;
	}

	public int getErrorCode() {
		if (this.getCause() != null && this.getCause() instanceof SQLException) {
			return ((SQLException) this.getCause()).getErrorCode();
		}
		return errorCode;
	}

	public String getStatus() {
		if (this.getCause() != null && this.getCause() instanceof SQLException) {
			return ((SQLException) this.getCause()).getSQLState();
		}
		return sqlstate;
	}

	public String getErrorDescription() {
		return StringUtil.format("数据库操作异常：<p>%s：<p><strong>%s</strong>", getMessage(), getCause() == null ? ""
				: getCause().getMessage());
	}
}
