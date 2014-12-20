/*
 * $Id: AmRoleServlet,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.web;

import org.ganjp.am.model.AmRole;
import org.ganjp.am.service.AmRoleService;
import org.ganjp.am.service.impl.AmRoleServiceImpl;
import org.ganjp.core.util.SqlUtil;
import org.ganjp.core.util.StringUtil;
import org.ganjp.core.web.action.BaseServlet;
import org.ganjp.core.web.util.QueryFilterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;


public class AmRoleServlet extends BaseServlet  {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if ("toList".equalsIgnoreCase(action)) {
			setAmRolePage(request);
			pageUrlEntity.setJspUrl("am/amRole/amRoleList");
		} else if ("toAdd".equalsIgnoreCase(action)) {
			pageUrlEntity.setJspUrl("am/amRole/amRoleAdd");
		} else if ("toEdit".equalsIgnoreCase(action)) {
			String roleId = request.getParameter("roleId").toString();
			AmRole amRole = amRoleService().getAmRoleByPk(roleId);
			request.setAttribute("amRole", amRole);
			pageUrlEntity.setJspUrl("am/amRole/amRoleEdit");
		} else if ("toView".equalsIgnoreCase(action)) {
			String roleId = request.getParameter("roleId").toString();
			AmRole amRole = amRoleService().getAmRoleByPk(roleId);
			request.setAttribute("amRole", amRole);
			pageUrlEntity.setJspUrl("am/amRole/amRoleView");
		} else if ("add".equalsIgnoreCase(action)) {
			//roleId,roleCd,roleName,description,createDateTime,modifyTimestamp,dataState,			
			String fieldNamesWithComma = "roleCd,roleName,description";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "AmRole");
			amRoleService().addAmRole((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/amRole?action=toList");
		} else if ("edit".equalsIgnoreCase(action)) {
			String fieldNamesWithComma = "roleCd,roleName,description,roleId";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "AmRole");
			amRoleService().editAmRole((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/amRole?action=toList");
		} else if ("delete".equalsIgnoreCase(action)) {
			amRoleService().deleteAmRolesByPks(request.getParameter("pks").toString());
			pageUrlEntity.setServletUrl("/servlet/amRole?action=toList");
		}  else if ("getZtreeJsonNodes".equalsIgnoreCase(action)) {
			pageUrlEntity.setJsonData(amRoleService().getRoleTreeJsonNodes());
		}  else if ("saveZtree".equalsIgnoreCase(action)) {
			String newRecordArrStr = request.getParameter("newRecordArr");
			String modifyRecordArrStr = request.getParameter("modifyRecordArr");
			String delNodeIds = request.getParameter("delNodeIds");
			try {
				amRoleService().crudRoleTree(newRecordArrStr, modifyRecordArrStr, delNodeIds);
				pageUrlEntity.setStringData(super.getMessage("save.success"));
			} catch (Exception e) {
				pageUrlEntity.setStringData(super.getMessage("save.fail")+ ":" + e.getMessage());
			}
		}
	}
	
	/**
	 * 1、get queryFilters and convert sql 
	 * 2、set queryFilters's field to request
	 * 3、get search result page and set to request
	 * 
	 * @param request
	 */
	public void setAmRolePage(HttpServletRequest request) {
		AmRoleService amRoleService = amRoleService();
		String queryFilters = request.getParameter("queryFilters");
		Map sqlAndValuesMap = null;
		if (StringUtil.isNotBlank(queryFilters)) {
			sqlAndValuesMap = SqlUtil.getSqlAndValuesMap(QueryFilterUtil.getQueryFilterList(queryFilters),"AmRole");
		}
		if (sqlAndValuesMap!=null) {
			for (Iterator iterator = sqlAndValuesMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (!SqlUtil.SQL_SUFFIX.equals(key) && !SqlUtil.VALUES.equals(key)) {
					request.setAttribute(key, sqlAndValuesMap.get(key));
				}
			}
		}
		request.setAttribute("page", amRoleService.getAllPage(getPageNo(request), getPageSize(request), sqlAndValuesMap));
	}
	
	private AmRoleService amRoleService() {
		return new AmRoleServiceImpl();
	}
		
}