/*
 * $Id: JpException.java,v 1.1 2011/05/24 13:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */

package org.ganjp.core.exception;

/**
 * The base {@link Throwable} type for Jp.
 * <p/>
 * 
 * @author gan jiaping
 */
public class JpException extends RuntimeException {

	public JpException(Throwable root) {
		super(root);
	}

	public JpException(String string, Throwable root) {
		super(string, root);
	}

	public JpException(String s) {
		super(s);
	}
}






