/**
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 *
 */
package org.ganjp.core.web.listen;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.ganjp.bm.dao.BmMenuDao;
import org.ganjp.bm.model.BmMenu;
import org.ganjp.core.Configuration;
import org.ganjp.core.Constants;
import org.ganjp.core.dao.DataSourceHolder;
import org.ganjp.core.db.h2.H2Handler;
import org.ganjp.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartupListener implements ServletContextListener {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void contextInitialized(ServletContextEvent event) {
		log.debug("contextInitialized() start");
		try{
			ServletContext context = event.getServletContext();
			setWebConfig(context);
			setDatabase();
			setCashFromDb();
			String pageSizes = Configuration.getString(Constants.PAGE_SIZES_CONFIG);
			context.setAttribute(Constants.PAGE_SIZES, pageSizes.split(","));
		} catch (Exception e){
			e.printStackTrace();
		}
		log.debug("contextInitialized() 1、set csstheme, csssuffix, jssuffix and language；" +
				" 2、init Configuration's RESOURCE_BUNDLE, MESSAGE_ZH, MESSAGE_EN, languageThreadLocal " +
				" 3、start up H2 by jp.properties(isStartupH2) end");
	}

	/**
	 * set csstheme, csssuffix, jssuffix and language to ServletContext's webConfig
	 * 
	 * @param context
	 */
	public void setWebConfig(ServletContext context) {
		Map configMap = (HashMap) context.getAttribute(Constants.WEB_CONFIG);
		if (configMap == null) {
			configMap = new HashMap();
		}
		String cssTheme = context.getInitParameter(Constants.CSS_THEME);
		String cssSuffix = context.getInitParameter(Constants.CSS_SUFFIX);
		String jsSuffix = context.getInitParameter(Constants.JS_SUFFIX);
		String language = context.getInitParameter(Constants.I18N_LANGUAGE);
		if (StringUtil.hasText(cssTheme)) {
			configMap.put(Constants.CSS_THEME, cssTheme);//webConfig[csstheme]
		}
		if (StringUtil.hasText(cssSuffix)) {
			configMap.put(Constants.CSS_SUFFIX, cssSuffix);
		}
		if (StringUtil.hasText(jsSuffix)) {
			configMap.put(Constants.JS_SUFFIX, jsSuffix);
		}
		if (StringUtil.hasText(language)) {
			configMap.put(Constants.I18N_LANGUAGE, language);
		}
		context.setAttribute(Constants.WEB_CONFIG, configMap);
		log.debug("setWebConfig(): set csstheme, csssuffix, jssuffix and language to ServletContext's webConfig");
	}
	
	
	public void setDatabase() {
		try {
			Context initialContext = new InitialContext();
			if (initialContext == null) {
		        log.error("JNDI problem. Cannot get InitialContext.");
		    } else {
		    	String dbJndiName = Configuration.getString("db.jndi.name");
		    	Object datasource = initialContext.lookup(dbJndiName);
		    	if (datasource != null) {
		    		DataSourceHolder.setDataSource((DataSource)datasource);
		    		log.debug("setDatabase() set DataSource to DataSourceHolder successfully");
			    }
		    }
		} catch(Exception ex) {
			log.error("setDatabase() don't set javax.sql.DataSource in server" + ex.getMessage());
			if (Configuration.getBoolean("isStartupH2")) {
	    		H2Handler.startH2();
	    	}	
		}
	}
	
	public void setCashFromDb() {
//		List amRoles = new AmRoleDao().getAll();
//		Map roleIdAndNames = new HashMap();
//		for (Iterator iterator = amRoles.iterator(); iterator.hasNext();) {
//			AmRole amRole = (AmRole) iterator.next();
//			roleIdAndNames.put(amRole.getRoleId(), amRole.getRoleName());
//		}
//		Configuration.setCashFromDb(Constants.ALL_ROLEIDS_ROLENAMES, roleIdAndNames);
		
		List bmMenus = new BmMenuDao().getAll();
		Map menuIdAndNames = new HashMap();
		for (Iterator iterator = bmMenus.iterator(); iterator.hasNext();) {
			BmMenu bmMenu = (BmMenu) iterator.next();
			menuIdAndNames.put(bmMenu.getMenuId(),bmMenu.getMenuName());
		}
		Configuration.setCashFromDb(Constants.ALL_MENUIDS_MENUNAMES, menuIdAndNames);
	}
	public void contextDestroyed(ServletContextEvent event) {
	    log.debug("context destroyed");
	}
	
}
