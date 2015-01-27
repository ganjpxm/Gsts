/**
 * $Id: ServletUtil.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.gone.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ganjp.gcore.Const;
import org.ganjp.gcore.util.DateUtil;
import org.ganjp.gcore.util.ReflectUtil;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.am.model.AmUser;
import org.ganjp.gone.common.GOneConst;
import org.ganjp.gone.common.model.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Servlet Util</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public class ServletUtil {
	public static void setValue(HttpServletRequest request, BaseModel baseModel, String suffix) {
		Field[] fields = baseModel.getClass().getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			if ("modifyTimestamp".equalsIgnoreCase(fieldName)) {
				try {
					Method method = ReflectUtil.getSetMethod(baseModel.getClass(), fieldName);
					method.invoke(baseModel, DateUtil.getNowTimstamp());
					continue;
				} catch (Exception ex) {
					log.error("set method " + fieldName + " value fail:" + ex.getMessage());
				}
			} else if ("operatorId".equalsIgnoreCase(fieldName)) {
				AmUser amUser = (AmUser)request.getSession().getAttribute(GOneConst.KEY_USER);
				try {
					if (amUser!=null) {
						Method method = ReflectUtil.getSetMethod(baseModel.getClass(), fieldName);
						method.invoke(baseModel, amUser.getUserId());
					}
					continue;
				} catch (Exception ex) {
					log.error("set operatorId value fail:" + ex.getMessage());
				}
			} else if ("operatorName".equalsIgnoreCase(fieldName)) {
				AmUser amUser = (AmUser)request.getSession().getAttribute(GOneConst.KEY_USER);
				try {
					if (amUser!=null) {
						Method method = ReflectUtil.getSetMethod(baseModel.getClass(), fieldName);
						method.invoke(baseModel, amUser.getUserName());
					}
					continue;
				} catch (Exception ex) {
					log.error("set operatorName value fail:" + ex.getMessage());
				}
			} else if ("lang".equalsIgnoreCase(fieldName)) {
				try {
					Method method = ReflectUtil.getSetMethod(baseModel.getClass(), fieldName);
					method.invoke(baseModel, ServletUtil.getLanguage(request));
					continue;
				} catch (Exception ex) {
					log.error("set operatorId value fail:" + ex.getMessage());
				}
			} 
			String value = "" ;
			if (StringUtil.hasLength(suffix)) {
				value = request.getParameter(fieldName + suffix);
			} else {
				value = request.getParameter(fieldName);
			}
			if (StringUtil.isNotEmpty(value)) {
				try {
					Method method = ReflectUtil.getSetMethod(baseModel.getClass(), fieldName);
					Class<?>[] types = method.getParameterTypes();
					if (types[0].getSimpleName().equalsIgnoreCase("Integer")) {
						method.invoke(baseModel, new Integer(value));
					} else if(types[0].getSimpleName().equalsIgnoreCase("BigDecimal")) {
						method.invoke(baseModel, new BigDecimal(value));
					} else if(types[0].getSimpleName().equalsIgnoreCase("Date")) {
						method.invoke(baseModel, DateUtil.parseDate(value, "dd/MM/yyyy"));
					} else if(types[0].getSimpleName().equalsIgnoreCase("Time")) {
						method.invoke(baseModel, DateUtil.parseTime(value));
					} else if(types[0].getSimpleName().equalsIgnoreCase("Timestamp")) {
						Date date = DateUtil.parseDate(value, "dd/MM/yyyy HH:mm");
						method.invoke(baseModel, DateUtil.convertDateToTimestamp(date));
					} else {
						method.invoke(baseModel, value);
					}
				} catch (Exception ex) {
					log.error("set method " + fieldName + " value fail:" + ex.getMessage());
				}
			}
		}
	}

	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies!=null) {
			for(int i=0; i<cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookieName.equals(cookie.getName())) {
					return(cookie.getValue());
				}
			}
		}
		return null;
	}
	
	public static String getLanguage(HttpServletRequest request) {
		String lang = null;
		try {
			if (request.getParameter(Const.LANGUAGE)!=null) {
				lang = request.getParameter(Const.LANGUAGE);
			} else if (request.getSession().getAttribute(Const.LANGUAGE) != null) {
				lang = StringUtil.toString(request.getSession().getAttribute(Const.LANGUAGE));
			} else if (ServletUtil.getCookieValue(request, Const.LANGUAGE)!=null) {
				lang = ServletUtil.getCookieValue(request, Const.LANGUAGE);
			} else {
				lang = Config.getDefaultLanguage();
			}
		} catch (Exception ex) {
			lang = Config.getDefaultLanguage();
		}
		return lang;
	}
	
	public static String getClientIpAddr(HttpServletRequest request) {  
        String ip = request.getHeader("X-Forwarded-For");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }  
	
	public static String getJqmTheme(HttpServletRequest request) {
		String jqmTheme = null;
		try {
			if (request.getParameter(GOneConst.JQM_THEME)!=null) {
				jqmTheme = request.getParameter(GOneConst.JQM_THEME);
			} else if (request.getSession()!=null && request.getSession().getAttribute(GOneConst.JQM_THEME) != null) {
				jqmTheme = StringUtil.toString(request.getSession().getAttribute(GOneConst.JQM_THEME));
			} else if (ServletUtil.getCookieValue(request, GOneConst.JQM_THEME)!=null) {
				jqmTheme = ServletUtil.getCookieValue(request, GOneConst.JQM_THEME);
			} else {
				jqmTheme = Config.getDefaultJqmTheme();
			}
		} catch (Exception ex) {
			jqmTheme = Config.getDefaultJqmTheme();
		}
		return jqmTheme;
	}
	
	public static void loginOut(ServletContext context, HttpSession session) {  
        if (session.getAttribute(GOneConst.KEY_USER) == null) {  
            return;  
        } else {
        	session.invalidate();
        }
  
        /**
        ConcurrentHashMap<String, Object> map = (ConcurrentHashMap<String, Object>) context.getAttribute(Const.ACTIVE_SESSIONS);  
  
        if (null != map) {  
              
            if (map.containsKey(session.getId())) {  
                map.remove(session.getId());  
                context.setAttribute(Const.ACTIVE_SESSIONS, map);  
            }  
        }  
        */
    }  
	private static Logger log = LoggerFactory.getLogger(ServletUtil.class);
		
}
