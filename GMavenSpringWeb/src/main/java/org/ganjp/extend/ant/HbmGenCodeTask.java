/**
 * $Id: HbmGenCodeTask.java,v 1.10 2011/02/25 08:45:54 ganjp Exp $
 *
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 *
 */
package org.ganjp.extend.ant;

import org.ganjp.gcore.util.StringUtil;
import org.ganjp.extend.xml.HbmParser;

/**
 * <p>根据Hbm生成一张表的任务</p>
 * 
 * @author ResourceOne BizFoundation Team: ganjp
 * @since 1.0
 */
public class HbmGenCodeTask extends BaseGenCodeTask {

	protected String moduleName;
	
	protected String className;
	
	protected String basePackage;

	protected String hibernateFile;

	protected void beforeExecute() throws Exception{
		String lowerClassName = StringUtil.lowerFirst(className);
		//类的完整信息:基本包名,模块名,类名
		super.putVelocityContext("BASE_PACKAGE", basePackage);
		super.putVelocityContext("MODULE_NAME", moduleName);
		super.putVelocityContext("CLASS_NAME", className);
		this.putVelocityContext("CLASS_NAME_WITHOUT_MODULE_NAME", (lowerClassName.replace(moduleName.toLowerCase(), "")).toLowerCase());
		super.putVelocityContext("UPPER_CLASS_NAME", StringUtil.upperFirst(className));
		super.putVelocityContext("LOWER_CLASS_NAME", StringUtil.lowerFirst(className));
		
		//hbm文件的相关信息
		HbmParser bean = new HbmParser(this.hibernateFile);
		super.putVelocityContext("ID", bean.getId());
		super.putVelocityContext("UPPER_ID", StringUtil.upperFirst(bean.getId()));
		super.putVelocityContext("ID_TYPE", bean.getIdType());
		super.putVelocityContext("ID_SIMPLE_TYPE", HbmParser.convertSimpleType(bean.getIdType()));
		super.putVelocityContext("KEY_GEN", bean.getGenerator());
		super.putVelocityContext("FIELD_NAMES", bean.getFieldNames());
		super.putVelocityContext("ALL_FIELD_NAMES", bean.getAllFieldNames());
		super.putVelocityContext("FIELD_NAMES_LENGHT", new Integer(bean.getFieldNames().size()));
		super.putVelocityContext("ALL_FIELD_NAMES_LENGHT", new Integer(bean.getAllFieldNames().size()));
		super.putVelocityContext("FIELD_TYPES", bean.getFieldTypes());
		super.putVelocityContext("ALL_FIELD_TYPES", bean.getAllFieldTypes());
		super.putVelocityContext("FIELD_SHOW_TYPES", bean.getFieldShowTypes());
		super.putVelocityContext("COLUMN_NAMES", bean.getColumnNames());
		super.putVelocityContext("COLUMN_ID", bean.getIdColumnName());
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
	
}
