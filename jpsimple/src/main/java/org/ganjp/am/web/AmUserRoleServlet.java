/*
 * $Id: AmUserRoleServlet,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.web;

import org.ganjp.am.model.AmUserRole;
import org.ganjp.am.service.AmUserRoleService;
import org.ganjp.am.service.impl.AmUserRoleServiceImpl;
import org.ganjp.core.util.SqlUtil;
import org.ganjp.core.util.StringUtil;
import org.ganjp.core.web.action.BaseServlet;
import org.ganjp.core.web.util.QueryFilterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;


public class AmUserRoleServlet extends BaseServlet  {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if ("toList".equalsIgnoreCase(action)) {
			setAmUserRolePage(request);
			pageUrlEntity.setJspUrl("am/amUserRole/amUserRoleList");
		} else if ("toAdd".equalsIgnoreCase(action)) {
			pageUrlEntity.setJspUrl("am/amUserRole/amUserRoleAdd");
		} else if ("toEdit".equalsIgnoreCase(action)) {
			String userRoleId = request.getParameter("userRoleId").toString();
			AmUserRole amUserRole = amUserRoleService().getAmUserRoleByPk(userRoleId);
			request.setAttribute("amUserRole", amUserRole);
			pageUrlEntity.setJspUrl("am/amUserRole/amUserRoleEdit");
		} else if ("toView".equalsIgnoreCase(action)) {
			String userRoleId = request.getParameter("userRoleId").toString();
			AmUserRole amUserRole = amUserRoleService().getAmUserRoleByPk(userRoleId);
			request.setAttribute("amUserRole", amUserRole);
			pageUrlEntity.setJspUrl("am/amUserRole/amUserRoleView");
		} else if ("add".equalsIgnoreCase(action)) {
			//userRoleId,roleId,userId,createDateTime,modifyTimestamp,dataState,			
			String fieldNamesWithComma = "add fields";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "AmUserRole");
			amUserRoleService().addAmUserRole((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/amUserRole?action=toList");
		} else if ("edit".equalsIgnoreCase(action)) {
			String fieldNamesWithComma = "edit fields, pk";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "AmUserRole");
			amUserRoleService().editAmUserRole((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/amUserRole?action=toList");
		} else if ("delete".equalsIgnoreCase(action)) {
			amUserRoleService().deleteAmUserRolesByPks(request.getParameter("pks").toString());
			pageUrlEntity.setServletUrl("/servlet/amUserRole?action=toList");
		}  else if ("toAmRoleZtreeUser".equalsIgnoreCase(action)) {
			pageUrlEntity.setJspUrl("am/amUserRole/amRoleZtreeUser");
		}
	}
	
	/**
	 * 1、get queryFilters and convert sql 
	 * 2、set queryFilters's field to request
	 * 3、get search result page and set to request
	 * 
	 * @param request
	 */
	public void setAmUserRolePage(HttpServletRequest request) {
		AmUserRoleService amUserRoleService = amUserRoleService();
		String queryFilters = request.getParameter("queryFilters");
		Map sqlAndValuesMap = null;
		if (StringUtil.isNotBlank(queryFilters)) {
			sqlAndValuesMap = SqlUtil.getSqlAndValuesMap(QueryFilterUtil.getQueryFilterList(queryFilters),"AmUserRole");
		}
		if (sqlAndValuesMap!=null) {
			for (Iterator iterator = sqlAndValuesMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (!SqlUtil.SQL_SUFFIX.equals(key) && !SqlUtil.VALUES.equals(key)) {
					request.setAttribute(key, sqlAndValuesMap.get(key));
				}
			}
		}
		request.setAttribute("page", amUserRoleService.getAllPage(getPageNo(request), getPageSize(request), sqlAndValuesMap));
	}
	
	private AmUserRoleService amUserRoleService() {
		return new AmUserRoleServiceImpl();
	}
		
}