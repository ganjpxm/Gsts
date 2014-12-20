/*
 * $Id: Configuration.java,v 1.4 2010/10/21 02:54:19 zhaoqy Exp $
 *
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 * 
 */
package org.ganjp.core;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.ganjp.core.util.PropertiesUtil;
import org.ganjp.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Configuration {
	private static Logger log = LoggerFactory.getLogger(Configuration.class);
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("jp");
	private static final Properties MESSAGE_ZH = PropertiesUtil.loadFromClassPath("i18n/messages_zh.properties");
	private static final Properties MESSAGE_EN = PropertiesUtil.loadFromClassPath("i18n/messages_en.properties");
	private static Map cashFromDbMap = new HashMap();
	private static ThreadLocal languageThreadLocal = new ThreadLocal();
	public Configuration() {
		super();
	}

	/**
	 * 获取properties配置文件中key对应的值
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key).trim();
		} catch (MissingResourceException e) {
			return null;
		}
	}
	
	public static int getInt(String key){
		String value = getString(key);
		if (value==null) {
			log.error("getInt() please set " + key + " vale in jp.properties");
		}
		return Integer.parseInt(value);
	}

	/**
	 * 获取properties配置文件中key对应的值
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(String key) {
		String value = getString(key);
		if (value != null)
			return new Boolean(value).booleanValue();
		return false;
	}
	
	/**
	 * 获取properties配置文件中key对应的值
	 * @param key
	 * @return
	 */
	public static boolean getBooleanValue(String key) {
		String value = getString(key);
		if (value != null)
			return new Boolean(value).booleanValue();
		return false;
	}
	
	/**
	 * 从指定的properties文件中加载数据
	 * @param resourceName
	 * @param key
	 * @return
	 */
	public static String getString(String resourceName,String key){
		ResourceBundle bundle = ResourceBundle.getBundle(resourceName);
		try {
			return bundle.getString(key).trim();
		} catch (MissingResourceException e) {
			log.error(resourceName + " don't key=" + key + " value");
			return null;
		}
	}
	
	/**
	 * 
	 * @param resourceName
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(String resourceName,String key) {
		String value = getString(resourceName,key);
		if (value != null)
			return new Boolean(value).booleanValue();
		return false;
	}
	
	public static void setLanguage(final String localLanguage) {
		languageThreadLocal.set(localLanguage);
	}
	
	public static String getLanguage() {
		return languageThreadLocal.get().toString();
	}
	
	public static void setCashFromDb(final String key, final Object value) {
		cashFromDbMap.put(key, value);
	}
	public static Object getCashFromDb(final String key) {
		return cashFromDbMap.get(key);
	}
	
	public static String getNameFromDbId(final String mapKey, final String mapMapKey) {
		return (String)((Map)cashFromDbMap.get(mapKey)).get(mapMapKey);
	}
	
	public static String getValue(final String key, final String localLanguage) {
		Object obj = null;
		if ("zh".equalsIgnoreCase(localLanguage)) {
			obj = MESSAGE_ZH.get(key);
			if (null == obj) {
				log.error("messages_zh_CN.properties don't find key:" + key);
				return null;
			}
		} else {
			obj = MESSAGE_EN.get(key);
			if (null == obj) {
				log.error("messages_en.properties don't find key:" + key);
				return null;
			}
		}
		log.debug("get messages_" + localLanguage + ".properties key = " + key + ", value = " + obj.toString());
		return obj.toString().trim();
	}
	
	public static String getValue(final String key,final Object[] argArr,final String localLanguage) {
		String value = getValue(key, localLanguage);
		return StringUtil.resolvePlaceholders(value, argArr);
	}
	
	public static String getColumnName(String className,String fieldName) {
		if ("BmConfig".equalsIgnoreCase(className)) {
 	    	if ("configId".equalsIgnoreCase(fieldName)) {
		   		return "config_id";
			} else if ("configKey".equalsIgnoreCase(fieldName)) {
		   		return "config_key";
			} else if ("configKeyDisplayName".equalsIgnoreCase(fieldName)) {
		   		return "config_key_display_name";
			} else if ("configValue".equalsIgnoreCase(fieldName)) {
		   		return "config_value";
			} else if ("description".equalsIgnoreCase(fieldName)) {
		   		return "description";
			} else if ("dataState".equalsIgnoreCase(fieldName)) {
		   		return "data_state";
			} else if ("createDateTime".equalsIgnoreCase(fieldName)) {
		   		return "create_date_time";
			} else if ("modifyTimestamp".equalsIgnoreCase(fieldName)) {
		   		return "modify_timestamp";
			}
		} else if ("BmCodeName".equalsIgnoreCase(className)) {
 	    	if ("codeNameId".equalsIgnoreCase(fieldName)) {
		   		return "code_name_id";
			} else if ("codeNamePid".equalsIgnoreCase(fieldName)) {
		   		return "code_name_pid";
			} else if ("code".equalsIgnoreCase(fieldName)) {
		   		return "code";
			} else if ("name".equalsIgnoreCase(fieldName)) {
		   		return "name";
			} else if ("displayNo".equalsIgnoreCase(fieldName)) {
		   		return "display_no";
			} else if ("displayLevel".equalsIgnoreCase(fieldName)) {
		   		return "display_level";
			} else if ("endFlag".equalsIgnoreCase(fieldName)) {
		   		return "end_flag";
			} else if ("createDateTime".equalsIgnoreCase(fieldName)) {
		   		return "create_date_time";
			} else if ("modifyTimestamp".equalsIgnoreCase(fieldName)) {
		   		return "modify_timestamp";
			} else if ("dataState".equalsIgnoreCase(fieldName)) {
		   		return "data_state";
			}
		} else if ("BmMenu".equalsIgnoreCase(className)) {
 	    	if ("menuId".equalsIgnoreCase(fieldName)) {
		   		return "menu_id";
			} else if ("menuPid".equalsIgnoreCase(fieldName)) {
		   		return "menu_pid";
			} else if ("menuCd".equalsIgnoreCase(fieldName)) {
		   		return "menu_cd";
			} else if ("menuName".equalsIgnoreCase(fieldName)) {
		   		return "menu_name";
			} else if ("menuType".equalsIgnoreCase(fieldName)) {
		   		return "menu_type";
			} else if ("url".equalsIgnoreCase(fieldName)) {
		   		return "url";
			} else if ("belongUserId".equalsIgnoreCase(fieldName)) {
		   		return "belong_user_id";
			} else if ("displayLevel".equalsIgnoreCase(fieldName)) {
		   		return "display_level";
			} else if ("displayNo".equalsIgnoreCase(fieldName)) {
		   		return "display_no";
			} else if ("endFlag".equalsIgnoreCase(fieldName)) {
		   		return "end_flag";
			} else if ("modifyTimestamp".equalsIgnoreCase(fieldName)) {
		   		return "modify_timestamp";
			} else if ("createDateTime".equalsIgnoreCase(fieldName)) {
		   		return "create_date_time";
			} else if ("dataState".equalsIgnoreCase(fieldName)) {
		   		return "data_state";
			}
		} else if ("AmUser".equalsIgnoreCase(className)) {
 	    	if ("userId".equalsIgnoreCase(fieldName)) {
		   		return "user_id";
			} else if ("userCd".equalsIgnoreCase(fieldName)) {
		   		return "user_cd";
			} else if ("userName".equalsIgnoreCase(fieldName)) {
		   		return "user_name";
			} else if ("userAlias".equalsIgnoreCase(fieldName)) {
		   		return "user_alias";
			} else if ("userLevel".equalsIgnoreCase(fieldName)) {
		   		return "user_level";
			} else if ("userScore".equalsIgnoreCase(fieldName)) {
		   		return "user_score";
			} else if ("loginTimes".equalsIgnoreCase(fieldName)) {
		   		return "login_times";
			} else if ("password".equalsIgnoreCase(fieldName)) {
		   		return "password";
			} else if ("gender".equalsIgnoreCase(fieldName)) {
		   		return "gender";
			} else if ("qq".equalsIgnoreCase(fieldName)) {
		   		return "qq";
			} else if ("email".equalsIgnoreCase(fieldName)) {
		   		return "email";
			} else if ("birthYear".equalsIgnoreCase(fieldName)) {
		   		return "birth_year";
			} else if ("birthMonth".equalsIgnoreCase(fieldName)) {
		   		return "birth_month";
			} else if ("birthDay".equalsIgnoreCase(fieldName)) {
		   		return "birth_day";
			} else if ("createDateTime".equalsIgnoreCase(fieldName)) {
		   		return "create_date_time";
			} else if ("modifyTimestamp".equalsIgnoreCase(fieldName)) {
		   		return "modify_timestamp";
			} else if ("dataState".equalsIgnoreCase(fieldName)) {
		   		return "data_state";
			}
		} else if ("AmRole".equalsIgnoreCase(className)) {
 	    	if ("roleId".equalsIgnoreCase(fieldName)) {
		   		return "role_id";
			} else if ("roleCd".equalsIgnoreCase(fieldName)) {
		   		return "role_cd";
			} else if ("roleName".equalsIgnoreCase(fieldName)) {
		   		return "role_name";
			} else if ("description".equalsIgnoreCase(fieldName)) {
		   		return "description";
			} else if ("createDateTime".equalsIgnoreCase(fieldName)) {
		   		return "create_date_time";
			} else if ("modifyTimestamp".equalsIgnoreCase(fieldName)) {
		   		return "modify_timestamp";
			} else if ("dataState".equalsIgnoreCase(fieldName)) {
		   		return "data_state";
			}
		} else if ("AmUserRole".equalsIgnoreCase(className)) {
 	    	if ("userRoleId".equalsIgnoreCase(fieldName)) {
		   		return "user_role_id";
			} else if ("roleId".equalsIgnoreCase(fieldName)) {
		   		return "role_id";
			} else if ("userId".equalsIgnoreCase(fieldName)) {
		   		return "user_id";
			} else if ("createDateTime".equalsIgnoreCase(fieldName)) {
		   		return "create_date_time";
			} else if ("modifyTimestamp".equalsIgnoreCase(fieldName)) {
		   		return "modify_timestamp";
			} else if ("dataState".equalsIgnoreCase(fieldName)) {
		   		return "data_state";
			}
		} else if ("CmArticle".equalsIgnoreCase(className)) {
 	    	if ("articleId".equalsIgnoreCase(fieldName)) {
		   		return "article_id";
			} else if ("articleCategoryIds".equalsIgnoreCase(fieldName)) {
		   		return "article_category_ids";
			} else if ("articleCd".equalsIgnoreCase(fieldName)) {
		   		return "article_cd";
			} else if ("articleTitle".equalsIgnoreCase(fieldName)) {
		   		return "article_title";
			} else if ("articleContent".equalsIgnoreCase(fieldName)) {
		   		return "article_content";
			} else if ("commentAuthorId".equalsIgnoreCase(fieldName)) {
		   		return "comment_author_id";
			} else if ("commentAuthorName".equalsIgnoreCase(fieldName)) {
		   		return "comment_author_name";
			} else if ("articleRecommendLevelId".equalsIgnoreCase(fieldName)) {
		   		return "article_recommend_level_id";
			} else if ("displayNo".equalsIgnoreCase(fieldName)) {
		   		return "display_no";
			} else if ("remark".equalsIgnoreCase(fieldName)) {
		   		return "remark";
			} else if ("auditFlag".equalsIgnoreCase(fieldName)) {
		   		return "audit_flag";
			} else if ("operatorId".equalsIgnoreCase(fieldName)) {
		   		return "operator_id";
			} else if ("operatorName".equalsIgnoreCase(fieldName)) {
		   		return "operator_name";
			} else if ("createDateTime".equalsIgnoreCase(fieldName)) {
		   		return "create_date_time";
			} else if ("modifyTimestamp".equalsIgnoreCase(fieldName)) {
		   		return "modify_timestamp";
			} else if ("dataState".equalsIgnoreCase(fieldName)) {
		   		return "data_state";
			}
		} else if ("CmVocabulary".equalsIgnoreCase(className)) {
 	    	if ("vocabularyId".equalsIgnoreCase(fieldName)) {
		   		return "vocabulary_id";
			} else if ("foreignLanguage".equalsIgnoreCase(fieldName)) {
		   		return "foreign_language";
			} else if ("phonogram".equalsIgnoreCase(fieldName)) {
		   		return "phonogram";
			} else if ("chinese".equalsIgnoreCase(fieldName)) {
		   		return "chinese";
			} else if ("propertyCatagoryIds".equalsIgnoreCase(fieldName)) {
		   		return "property_catagory_ids";
			} else if ("vocabularyCatagoryIds".equalsIgnoreCase(fieldName)) {
		   		return "vocabulary_catagory_ids";
			} else if ("levelId".equalsIgnoreCase(fieldName)) {
		   		return "level_id";
			} else if ("recommendState".equalsIgnoreCase(fieldName)) {
		   		return "recommend_state";
			} else if ("displayNo".equalsIgnoreCase(fieldName)) {
		   		return "display_no";
			} else if ("description".equalsIgnoreCase(fieldName)) {
		   		return "description";
			} else if ("operatorId".equalsIgnoreCase(fieldName)) {
		   		return "operator_id";
			} else if ("operatorName".equalsIgnoreCase(fieldName)) {
		   		return "operator_name";
			} else if ("createDateTime".equalsIgnoreCase(fieldName)) {
		   		return "create_date_time";
			} else if ("modifyTimestamp".equalsIgnoreCase(fieldName)) {
		   		return "modify_timestamp";
			} else if ("dataState".equalsIgnoreCase(fieldName)) {
		   		return "data_state";
			}
		} else if ("EmProduct".equalsIgnoreCase(className)) {
 	    	if ("productId".equalsIgnoreCase(fieldName)) {
		   		return "product_id";
			} else if ("productCd".equalsIgnoreCase(fieldName)) {
		   		return "product_cd";
			} else if ("productNameCn".equalsIgnoreCase(fieldName)) {
		   		return "product_name_cn";
			} else if ("productNameEn".equalsIgnoreCase(fieldName)) {
		   		return "product_name_en";
			} else if ("price".equalsIgnoreCase(fieldName)) {
		   		return "price";
			} else if ("imagepath".equalsIgnoreCase(fieldName)) {
		   		return "imagepath";
			} else if ("description".equalsIgnoreCase(fieldName)) {
		   		return "description";
			} else if ("createDateTime".equalsIgnoreCase(fieldName)) {
		   		return "create_date_time";
			} else if ("modifyTimestamp".equalsIgnoreCase(fieldName)) {
		   		return "modify_timestamp";
			} else if ("dataState".equalsIgnoreCase(fieldName)) {
		   		return "data_state";
			}
		}
		log.error("please set className:" + className + "-fieldName:" + fieldName + "in Configuration.java");
		return null;
    }  
}