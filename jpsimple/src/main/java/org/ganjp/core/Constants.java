package org.ganjp.core;

public class Constants {
    public static final String FILE_SEP = System.getProperty("file.separator");
    public static final String WEB_CONFIG = "webConfig";// application scope's hashmap var name
    public static final String CSS_THEME = "csstheme";
    public static final String CSS_SUFFIX = "csssuffix";
    public static final String JS_SUFFIX = "jssuffix";
    public static final String NEW_FORM = "newForm";
    public static final String I18N_LANGUAGE = "language";
    public static final String ENCODING = "encoding";
    public static final String HEAD_FILTER = "headers";//used by AddHeaderFilter.java
    public static final String PAGE_SIZES = "pageSizes";
    
    //表主键
    public static final String PUT_PRIMARY_KEY_LAST = "putPrimaryKeyLast";    
    
    //日期格式
    public static final String DATE_FORMATE = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMATE = "yyyy-MM-dd HH:mm:ss";
    
    //消息常量的定义
	public static final String MESSAGE_INFO = "info";
    public static final String MESSAGE_ERROR = "error";
    public static final String MESSAGE_WARNING = "warning";
    public static final String MESSAGE_SUCCESS = "success";
    public static final String MESSAGE_INFO_FOOTER = "infoFooter";
    public static final String MESSAGE_ERROR_FOOTER = "errorFooter";
    public static final String MESSAGE_WARNING_FOOTER = "warningFooter";
    public static final String MESSAGE_SUCCESS_FOOTER = "successFooter";
    
	//------------------------------------------------数据库信息---------------------------
	public static final String DEFAULT_BATCH_SIZE = "15";
	public static final String NO_BATCH = "0";
	//---------数据库类型名称-------------
	public static final String DATABASE_TYPE_DB2 = "IBMDB2";
	public static final String DATABASE_TYPE_ORACLE = "ORACLE";
	public static final String DATABASE_TYPE_SQLSERVER = "MSSQLSERVER";
	public static final String DATABASE_TYPE_MYSQL = "MYSQL";
	public static final String DATABASE_TYPE_DERBY = "DERBY";
	public static final String DATABASE_TYPE_DM = "DMDB";
	public static final String DATABASE_TYPE_KB = "KBDB";
	public static final String DATABASE_TYPE_H2 = "H2";
	public static final String DATA_STATE_NORMAL = "0";
	public static final String DATA_STATE_DELETE = "1";
	public static final String END_FLAG_YES = "1";
	public static final String END_FLAG_NO = "0";
	
	//-----------------------------------缓存数据库的key----------------
	public static final String ALL_ROLEIDS_ROLENAMES = "roleIdAndNames";
	public static final String ALL_MENUIDS_MENUNAMES = "menuIdAndNames";
	
	//-----------------------------------------------jp.properties---------------------------
	public static final String IS_STARTUP_H2_CONFIG = "isStartupH2";
	public static final String PAGE_SIZES_CONFIG = "page.sizes";
	
	
	//---------------------------------database----------------
	public static final String SUPER_ADMIN_ROLE_ID = "40283804364e8f7701364e8f778d0000";
	public static final String GUEST_ROLE_ID = "40283804364f0c1901364f0c191b0000";
	public static final String WEBSITE_MENU_ROOT_ID = "00000000000000000000000000000002";
	public static final String HOME_MENU_ID = "402838043652c2ec013652c2ece90000";
	public static final String WORD_MENU_ID = "4028380436614083013661448cf80007";
	public static final String PHRASE_WORD_MENU_ID = "40283804366c388001366c3880e50000";
	
	
}
