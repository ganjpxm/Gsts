/**
 * $Id: Config.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.gone.common.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import org.ganjp.gcore.Const;
import org.ganjp.gone.am.model.AmUser;
import org.ganjp.gone.common.GOneConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>store sytem's configuration</p> 
 *
 * @author GanJianping
 * @since 1.0
 */
public class Config {
	/**
	 * <p>get value by key from system config file</p>
	 * 
	 * @param key eg:"timestamp.format"
	 * @return String
	 */
	public static String getValue(String key) {
		try {
			String value = RESOURCE_BUNDLE.getString(key).trim();
			log.debug("get " + key + " = " + value + " from " + GOneConst.PROJECT_NAME + ".properties ");
			return value;
		} catch (MissingResourceException e) {
			log.error(GOneConst.PROJECT_NAME + ".properties doesn't exist key = " + key );
			return null;
		}
	}

    public static String getJsSuffix() {
        return getValue(Const.JS_SUFFIX);
    }
    
    public static String getCssSuffix() {
        return getValue(Const.CSS_SUFFIX);
    }
    
    public static String getDefaultJpTheme() {
        return getValue(GOneConst.JP_THEME);
    }

    public static String getDefaultJqmTheme() {
        return getValue(GOneConst.JQM_THEME);
    }
    
    public static String getDefaultLanguage() {
        return getValue(Const.LANGUAGE);
    }

    public static void setCacheFromDb(final String key, final Object value) {
		cashFromDbMap.put(key, value);
	}
    
	public static Object getCacheFromDb(final String key) {
		return cashFromDbMap.get(key);
	}
	
	public static List getCashParamMaps(String paramType, String lang) {
		return (List)cashParamMap.get(paramType+lang);
	}

	public static void setCacheParamMap(Map cashParamMap) {
		Config.cashParamMap = cashParamMap;
	}

	public static void setCacheMenuMap(Map cashMenuMap) {
		Config.cashMenuMap = cashMenuMap;
	}
	
	public static Map getCashMenuMap() {
		return Config.cashMenuMap;
	}
	
	public static String getCashValue(final String key1, final String key2) {
		return ((Map)cashFromDbMap.get(key1)).get(key2).toString();
	}

	public static Map getAllCashParamIdNames() {
		Map map = new HashMap();
		if (cashParamMap != null) {
			Collection collect = cashParamMap.values();
			for (Iterator iterator = collect.iterator(); iterator.hasNext();) {
				List paramIdNameArrs = (List) iterator.next();
				for (Iterator iterator2 = paramIdNameArrs.iterator(); iterator2.hasNext();) {
					String[] paramIdNameArr = (String[]) iterator2.next();
					map.put(paramIdNameArr[0], paramIdNameArr[1]);
				}
			}
		}
		return map;
	}
	
	public static Locale getLocale(String lang) {
		Locale locale = null;
		if (Const.LANGUAGE_ZH_CN.equalsIgnoreCase(lang)) {
			locale = Locale.SIMPLIFIED_CHINESE;
		} else {
			locale = Locale.ENGLISH;
		}
		return locale;
	}

    private static Logger log = LoggerFactory.getLogger(Config.class);
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(GOneConst.PROJECT_NAME);
	private static Map cashFromDbMap = new HashMap();
	private static Map cashParamMap = null;
	private static Map cashMenuMap = null;
	public static ConcurrentHashMap<String, AmUser> loginAmUserMap = new ConcurrentHashMap<String, AmUser>();
}