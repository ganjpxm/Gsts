package org.ganjp.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PropertiesUtil {
	private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);
    /**
     * Load a properties file from the classpath
     *
     * @param propertiesName the properties file to load.
     * @return a properties instance loaded with the properties from
     * the file. If no file can be found it returns an empty instance.
     * @throws Exception
     */
    public static Properties loadFromClassPath(final String propertiesClassPath) {
        Properties properties = new Properties();
        ClassLoader classLoader = PropertiesUtil.class.getClassLoader();
        InputStream inputStream = null;
        try {
    	    inputStream = classLoader.getResourceAsStream(propertiesClassPath);
            properties.load(inputStream);
        } catch(Exception ex) {
        	log.error("loadFromClassPath() has IOException : " + ex.getMessage());
        } finally {
            if (inputStream != null) {
            	try {
            		inputStream.close();
            	} catch(IOException ex) {
                	log.error("loadFromClassPath() finally inputStream close fail : " + ex.getMessage());
                } 
            }
        }
        log.debug("load classpath file: " + propertiesClassPath);
        return properties;
    }
    

    
    
    public static void main(String[] args) throws Exception {
    	Properties properties = PropertiesUtil.loadFromClassPath("i18n/messages_zh_cn.properties");
    	System.out.println(properties.get("validation.required"));
    }
}
