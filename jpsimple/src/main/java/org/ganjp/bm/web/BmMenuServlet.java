/*
 * $Id: BmMenuServlet,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.bm.web;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.bm.model.BmMenu;
import org.ganjp.bm.service.BmMenuService;
import org.ganjp.bm.service.impl.BmMenuServiceImpl;
import org.ganjp.core.util.SqlUtil;
import org.ganjp.core.util.StringUtil;
import org.ganjp.core.web.action.BaseServlet;
import org.ganjp.core.web.util.QueryFilterUtil;


public class BmMenuServlet extends BaseServlet  {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if ("toList".equalsIgnoreCase(action)) {
			setBmMenuPage(request);
			pageUrlEntity.setJspUrl("bm/bmMenu/bmMenuList");
		} else if ("toAdd".equalsIgnoreCase(action)) {
			pageUrlEntity.setJspUrl("bm/bmMenu/bmMenuAdd");
		} else if ("toEdit".equalsIgnoreCase(action)) {
			String menuId = request.getParameter("menuId").toString();
			BmMenu bmMenu = bmMenuService().getBmMenuByPk(menuId);
			request.setAttribute("bmMenu", bmMenu);
			pageUrlEntity.setJspUrl("bm/bmMenu/bmMenuEdit");
		} else if ("toView".equalsIgnoreCase(action)) {
			String menuId = request.getParameter("menuId").toString();
			BmMenu bmMenu = bmMenuService().getBmMenuByPk(menuId);
			request.setAttribute("bmMenu", bmMenu);
			pageUrlEntity.setJspUrl("bm/bmMenu/bmMenuView");
		} else if ("toZtree".equalsIgnoreCase(action)) {
			pageUrlEntity.setJspUrl("bm/bmMenu/bmMenuZtree");
		} else if ("add".equalsIgnoreCase(action)) {
			//menuId,menuPid,menuCd,menuName,menuType,url,belongUserId,displayLevel,displayNo,endFlag,modifyTimestamp,createDateTime,dataState,			
			String fieldNamesWithComma = "menuPid,menuCd,menuName,url,displayLevel,displayNo,endFlag";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "BmMenu");
			bmMenuService().addBmMenu((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/bmMenu?action=toList");
		} else if ("edit".equalsIgnoreCase(action)) {
			String fieldNamesWithComma = "menuPid,menuCd,menuName,url,displayLevel,displayNo,endFlag,menuId";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "BmMenu");
			bmMenuService().editBmMenu((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/bmMenu?action=toList");
		} else if ("delete".equalsIgnoreCase(action)) {
			bmMenuService().deleteBmMenusByPks(request.getParameter("pks").toString());
			pageUrlEntity.setServletUrl("/servlet/bmMenu?action=toList");
		} else if ("getZtreeJsonNodes".equalsIgnoreCase(action)) {
			String id = request.getParameter("id");
			pageUrlEntity.setJsonData(bmMenuService().getChildTreeJsonNodes(id));
		} else if ("saveZtree".equalsIgnoreCase(action)) {
			String newRecordArrStr = request.getParameter("newRecordArr");
			String modifyRecordArrStr = request.getParameter("modifyRecordArr");
			String delNodeIds = request.getParameter("delNodeIds");
			try {
				bmMenuService().crudMenuTree(newRecordArrStr, modifyRecordArrStr, delNodeIds);
				pageUrlEntity.setStringData(super.getMessage("save.success"));
			} catch (Exception e) {
				pageUrlEntity.setStringData(super.getMessage("save.fail")+ ":" + e.getMessage());
			}
		} else if("toWebsiteMenuZtree".equalsIgnoreCase(action)) {
			pageUrlEntity.setJspUrl("bm/bmMenu/bmWebsiteMenuZtree");
		} else if ("getWebsiteZtreeJsonNodes".equalsIgnoreCase(action)) {
			String id = request.getParameter("id");
			String pidsWithComma = request.getParameter("pids");
			if (StringUtil.isBlank(pidsWithComma)) {
				pidsWithComma = "0";
			} else if ("0".equalsIgnoreCase(pidsWithComma)) {
				pidsWithComma = id;
			} else {
				pidsWithComma += "," + id;
			}
			pageUrlEntity.setJsonData(bmMenuService().getChildTreeJsonNodes(id,pidsWithComma));
		}  
	}
	
	/**
	 * 1、get queryFilters and convert sql 
	 * 2、set queryFilters's field to request
	 * 3、get search result page and set to request
	 * 
	 * @param request
	 */
	public void setBmMenuPage(HttpServletRequest request) {
		BmMenuService bmMenuService = bmMenuService();
		String queryFilters = request.getParameter("queryFilters");
		Map sqlAndValuesMap = null;
		if (StringUtil.isNotBlank(queryFilters)) {
			sqlAndValuesMap = SqlUtil.getSqlAndValuesMap(QueryFilterUtil.getQueryFilterList(queryFilters),"BmMenu");
		}
		if (sqlAndValuesMap!=null) {
			for (Iterator iterator = sqlAndValuesMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (!SqlUtil.SQL_SUFFIX.equals(key) && !SqlUtil.VALUES.equals(key)) {
					request.setAttribute(key, sqlAndValuesMap.get(key));
				}
			}
		}
		request.setAttribute("page", bmMenuService.getAllPage(getPageNo(request), getPageSize(request), sqlAndValuesMap));
	}
	
	private BmMenuService bmMenuService() {
		return new BmMenuServiceImpl();
	}
		
}