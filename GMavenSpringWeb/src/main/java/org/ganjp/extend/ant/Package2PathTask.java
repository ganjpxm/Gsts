/*
 * $Id: Package2Path.java,v 1.1 2011/05/12 09:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.extend.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * <p>把包名转成包路径</p>
 * 
 * @author ganjp
 * @since 1.0
 */
public class Package2PathTask extends Task {

	private String packageName;

	private String pathProperty;

	public void execute() throws BuildException {
		this.getProject().setProperty(this.pathProperty, this.packageName.replaceAll("\\.", "/"));
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void setPathProperty(String pathProperty) {
		this.pathProperty = pathProperty;
	}
	
}
