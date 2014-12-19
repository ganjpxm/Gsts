/**
 * $Id: Constants.java,v 1.0 2012/04/14 09:24:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Illume Technology. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.gcore;

/**
 * <p>define constant</p>
 * 
 * @author ganjianping
 * @since 1.0
 */
public class Const {
	//------------------------------ java system constant ---------------------
    public static final String FILE_SEP = System.getProperty("file.separator");
    public static final String PATH_SEP = System.getProperty("path.separator");
    public static final String LINE_SEP = System.getProperty("line.separator");
    public static final String SYSTEM_USER_NAME = System.getProperty("user.name");
    public static final String SYSTEM_USER_HOME = System.getProperty("user.home");
    public static final String SYSTEM_USER_DIR = System.getProperty("user.dir");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String JAVA_VENDOR = System.getProperty("java.vendor");
    public static final String JAVA_HOME = System.getProperty("java.home");
    public static final String JAVA_IO_TMP_DIR = System.getProperty("java.io.tmpdir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String OS_VERSION = System.getProperty("os.version");
    
	//------------------------------ define project constant ---------------------
    public static final String LANGUAGE = "lang";
    public static final String LANGUAGE_ZH_CN = "zh_CN";
    public static final String LANGUAGE_EN_SG = "en_SG";
   
    // ------------------------ database information -------------------------------------------
	public static final String DB_DATASTATE_NORMAL = "0";
	public static final String DB_DATASTATE_DELETE = "1";
	
	//------------------------------ key and value ---------------------
	public static final String KEY_RESULT = "result";
	public static final String VALUE_SUCCESS = "success";
	public static final String VALUE_FAIL = "fail";
	
    public static void main(String arg[]) {
    	System.out.println(FILE_SEP);
    	System.out.println(PATH_SEP);
    	System.out.println(LINE_SEP);
        System.out.println(System.getProperty("file.separator"));
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.vendor"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("java.io.tmpdir"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));
    }

}

