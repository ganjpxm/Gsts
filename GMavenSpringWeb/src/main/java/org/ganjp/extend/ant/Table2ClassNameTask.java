package org.ganjp.extend.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.ganjp.gcore.util.StringUtil;

public class Table2ClassNameTask extends Task {

	private String tableName;

	private String classNameProperty;

	public void execute() throws BuildException {
		this.getProject().setProperty(this.classNameProperty, StringUtil.formatJavaName(tableName, true));
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setClassNameProperty(String classNameProperty) {
		this.classNameProperty = classNameProperty;
	}

}
