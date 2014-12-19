/**
 * $Id: BaseGenCodeTask.java,v 1.8 2011/02/25 10:36:20 liujun Exp $
 *
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 *
 */
package org.ganjp.extend.ant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.ganjp.gcore.util.StringUtil;

/**
 * <p>基本的生成代码任务</p>
 * 
 * @author ResourceOne BizFoundation Team: ganjp
 * @since 1.0
 */
public abstract class BaseGenCodeTask extends BaseTask{
    
    private static String ENCODE = "UTF-8";

	private String templateDir;

	private String templateFile;

	private String templatePropertiesFile;

	private String outputFileName;

	private VelocityContext velocityContext = null;

	private Template template = null;

	public void execute() throws Exception{
		try {
			Properties properites = new Properties();
			properites.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, this.templateDir);
			//把模板的目录初始化到Velocity,并创建velocityContext对象
			this.init(properites);
			
			Properties varp = new Properties();
			if (StringUtil.isNotBlank(this.templatePropertiesFile)) {
				FileInputStream in = new FileInputStream(this.templatePropertiesFile);
				varp.load(in);
				Iterator it = varp.keySet().iterator();
				String key = "";
				String value = "";
				while (it.hasNext()) {
					key = (String) it.next();
					value = varp.getProperty(key);
					this.putVelocityContext(key, value);
				}
			}

			this.setTemplate(templateFile, "UTF-8");
			this.beforeExecute();
			//new File(this.outputFileName).createNewFile();
			this.toFile(this.outputFileName);
			this.afterExecute();
		} catch (Exception e) {
			throw e;
		}
	}
	
	protected abstract void beforeExecute() throws Exception;
	
	protected void afterExecute(){
		
	}

	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}

	public void setTemplatePropertiesFile(String templatePropertiesFile) {
		this.templatePropertiesFile = templatePropertiesFile;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}
	
	public String getOutputFileName(){
		return this.outputFileName;
	}

	/**
	 * 对
	 * @param properties
	 * @throws Exception
	 */
	public void init(String properties) throws Exception {
		if (properties != null && properties.trim().length() > 0) {
			Velocity.init(properties);
		} else {
			Velocity.init();
		}
		velocityContext = new VelocityContext();
	}

	/**
	 * 把properties对应的值初始化到Velocity
	 * @param properties
	 * @throws Exception
	 */
	public void init(Properties properties) throws Exception {
		Velocity.init(properties);
		velocityContext = new VelocityContext();
	}

	/**
	 * 把key和value放入velocityContext对象
	 * @param key
	 * @param value
	 */
	public void putVelocityContext(String key, Object value) {
		velocityContext.put(key, value);
	}

	/**
	 * 设置模版
	 * 
	 * @param templateFile
	 *            模版文件
	 * @throws AppException
	 */
	public void setTemplate(String templateFile) throws RuntimeException {
		try {
			template = Velocity.getTemplate(templateFile);
		} catch (ResourceNotFoundException rnfe) {
			rnfe.printStackTrace();
			throw new RuntimeException(" error : cannot find template " + templateFile);
		} catch (ParseErrorException pee) {
			throw new RuntimeException(" Syntax error in template " + templateFile + ":" + pee);
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}

	}

	/**
	 * 设置模版
	 * 
	 * @param templateFile 模版文件
	 * @throws RuntimeException
	 */
	public void setTemplate(String templateFile, String characterSet) throws RuntimeException {
		try {
			template = Velocity.getTemplate(templateFile, characterSet);
		} catch (ResourceNotFoundException rnfe) {
			rnfe.printStackTrace();
			throw new RuntimeException(" error : cannot find template " + templateFile);
		} catch (ParseErrorException pee) {
			throw new RuntimeException(" Syntax error in template " + templateFile + ":" + pee);
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}

	}

	/**
	 * 转换为文本文件
	 */
	public String toText() throws RuntimeException {
		StringWriter sw = new StringWriter();
		try {
			template.merge(velocityContext, sw);
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}
		return sw.toString();
	}

	/**
	 * 
	 * @param fileName
	 */
	public void toFile(String fileName) throws RuntimeException {
	    OutputStreamWriter filewriter = null;
        try {
            StringWriter stringWriter = new StringWriter();
            template.merge(velocityContext, stringWriter);

            File file = new File(fileName);
            if(!file.exists()){
            	String tmpFile = fileName.replace('/',File.separatorChar);
            	String dir = fileName.substring(0,tmpFile.lastIndexOf(File.separatorChar));
            	File dirFile = new File(dir);
            	if(!dirFile.exists()){
            		dirFile.mkdir();
            	}
            	file.createNewFile();
            }
            
            filewriter = new OutputStreamWriter(new FileOutputStream(fileName), ENCODE);
            filewriter.write(stringWriter.toString());
            filewriter.flush();
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        } finally {
            if (filewriter != null) {
                try {
                    filewriter.close();
                } catch (Exception e) {
                }
            }
        }
	}

	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}

	/**
	 * 单元测试
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
	    BaseGenCodeTask bt = new BaseGenCodeTask(){

            protected void beforeExecute() throws Exception {
            }
	        
	    };
	    bt.setTemplateDir("c:\\");
	    bt.setTemplateFile("1.vm");
	    bt.setOutputFileName("c:\\2.jsp");
	    bt.execute();
    }
}
