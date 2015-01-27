/**
 * $Id: Constants.java,v 1.0 2012/04/14 09:24:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Illume Technology. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.gone.common;

import org.ganjp.gcore.Const;

/**
 * <p>define constant</p>
 * 
 * @author ganjianping
 * @since 1.0
 */
public class GOneConst {
	//------------------------------ define project constant ---------------------
    public static final String PROJECT_NAME = "gone";
    public static final String PROJECT_VERSION = "1.0.0";
    public static final String MESSAGE_BUNDLE_NAME = "i18n" + Const.FILE_SEP + "messages";
    public static final String JP_THEME = "jpTheme";
    public static final String JQM_THEME = "jqmTheme";
    public static final String HEAD_FILTER = "headers";//used by AddHeaderFilter.java
    public static final String EXCLUDE_PATTERNS = "excludePatterns";//used by AddHeaderFilter.java
    
    // ------------------------ DB -------------------------------------------
	public static final String DB_DATASTATE_SEND_NO = "0";
	public static final String DB_DATASTATE_SEND_YES = "1";
	public static final String DB_ORDER_TYPE_ASC = "asc";
	public static final String DB_ORDER_TYPE_DESC = "desc";
    
	// ------------------------ KEY -------------------------------------------
    public static final String KEY_USER = "user";

}

