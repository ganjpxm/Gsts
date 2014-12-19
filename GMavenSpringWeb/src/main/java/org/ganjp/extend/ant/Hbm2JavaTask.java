/**
 * $Id: Hbm2JavaTask.java,v 1.7 2011/02/25 08:28:09 ganjp Exp $
 *
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 *
 */
package org.ganjp.extend.ant;

import org.ganjp.gcore.util.StringUtil;
import org.ganjp.extend.xml.HbmParser;

/**
 * <p>根据Hbm生成PO对象的任务</p>
 * 
 * @author ResourceOne BizFoundation Team: ganjp
 * @since 1.0
 */
public class Hbm2JavaTask extends BaseGenCodeTask {

	protected String moduleName;
	
	protected String className;
	
	protected String basePackage;

	protected String tableName;
	
	protected String hibernateFile;

	protected void beforeExecute() throws Exception{
		String lowerClassName = StringUtil.lowerFirst(className);
		this.putVelocityContext("MODULE_NAME", moduleName);
		this.putVelocityContext("CLASS_NAME", className);
		this.putVelocityContext("CLASS_NAME_WITHOUT_MODULE_NAME", (lowerClassName.replace(moduleName.toLowerCase(), "")).toLowerCase());
		this.putVelocityContext("BASE_PACKAGE", basePackage);
		this.putVelocityContext("TABLE_NAME", tableName);
		
		this.putVelocityContext("UPPER_CLASS_NAME", StringUtil.upperFirst(className));
		this.putVelocityContext("LOWER_CLASS_NAME", StringUtil.lowerFirst(className));

		HbmParser bean = new HbmParser(this.hibernateFile);
		this.putVelocityContext("ID", bean.getId());
		this.putVelocityContext("KEY_GEN", bean.getGenerator());
		this.putVelocityContext("ALL_FIELD_NAMES", bean.getAllFieldNames());
		this.putVelocityContext("ALL_UPPER_FIELD_NAMES", bean.getAllFirstUpperFieldNames());
		this.putVelocityContext("ALL_FIELD_NAMES_LENGHT",String.valueOf(bean.getAllFieldNames().size()));
		this.putVelocityContext("ALL_SIMPLE_FIELD_TYPES", bean.getAllSimpleFieldTypes());
		this.putVelocityContext("ALL_COLUMN_NAMES", bean.getAllColumnNames());
		this.putVelocityContext("FIELD_NAMES", bean.getFieldNames());
		this.putVelocityContext("IMPORT_TYPES", bean.getImportTypeList());
		this.putVelocityContext("COLUMN_NAMES", bean.getColumnNames());
		this.putVelocityContext("COLUMN_ID", bean.getIdColumnName());
	}
	
	public void setHibernateFile(String hibernateFile) {
		this.hibernateFile = hibernateFile;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
