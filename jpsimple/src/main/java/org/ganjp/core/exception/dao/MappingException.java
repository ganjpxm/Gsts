/*
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 *
 */
package org.ganjp.core.exception.dao;

import org.ganjp.core.exception.base.UnexpectedException;

/**
 * ORM 映射异常,是一个纯业务逻辑的异常,复写了fillInStackTrace方法，提高性能。
 * 
 * 
 * @author BizFoundation Team: zhaoqy
 * 
 * @version 1.0
 * @since 4.2
 * 
 * $Revision: 1.3 $
 */
public class MappingException extends UnexpectedException {

	private static final long serialVersionUID = 5969433818850646075L;

	public MappingException(String msg, Throwable root) {
		super(msg, root);
	}

	public MappingException(Throwable root) {
		super(root);
	}

	public MappingException(String s) {
		super(s);
	}
	
	public Throwable fillInStackTrace() {
		return null;
	}
}
