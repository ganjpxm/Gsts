/**
 * $Id: SimpleGenCodeTask.java,v 1.3 2011/02/25 08:28:08 ganjp Exp $
 *
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 *
 */
package org.ganjp.extend.ant;

import org.ganjp.gcore.util.StringUtil;

/**
 * <p>简单生成代码模板的任务</p> 
 * 
 * @author Rone Framework Team: ganjp
 * @version 1.0
 * @since 4.2
 */
public class SimpleGenCodeTask extends BaseGenCodeTask {
	protected String moduleName;
	
	protected String className;
	
	protected String basePackage;

	protected void beforeExecute() throws Exception{
		String lowerClassName = StringUtil.lowerFirst(className);
		this.putVelocityContext("CLASS_NAME", className);
		this.putVelocityContext("UPPER_CLASS_NAME_WITHOUT_MODULE_NAME", lowerClassName.replace(moduleName.toLowerCase(), ""));
		this.putVelocityContext("LOWER_CLASS_NAME_WITHOUT_MODULE_NAME", StringUtil.lowerFirst(lowerClassName.replace(moduleName.toLowerCase(), "")));
		this.putVelocityContext("MODULE_NAME", moduleName);
		this.putVelocityContext("BASE_PACKAGE", basePackage);
		
		this.putVelocityContext("UPPER_CLASS_NAME", StringUtil.upperFirst(className));
		this.putVelocityContext("LOWER_CLASS_NAME", lowerClassName);
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
