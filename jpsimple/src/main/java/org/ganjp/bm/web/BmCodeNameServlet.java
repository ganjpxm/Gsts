/*
 * $Id: BmCodeNameServlet,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.bm.web;

import org.ganjp.bm.model.BmCodeName;
import org.ganjp.bm.service.BmCodeNameService;
import org.ganjp.bm.service.impl.BmCodeNameServiceImpl;
import org.ganjp.core.util.SqlUtil;
import org.ganjp.core.util.StringUtil;
import org.ganjp.core.web.action.BaseServlet;
import org.ganjp.core.web.util.QueryFilterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;


public class BmCodeNameServlet extends BaseServlet  {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if ("toList".equalsIgnoreCase(action)) {
			setBmCodeNamePage(request);
			pageUrlEntity.setJspUrl("bm/bmCodeName/bmCodeNameList");
		} else if ("toAdd".equalsIgnoreCase(action)) {
			pageUrlEntity.setJspUrl("bm/bmCodeName/bmCodeNameAdd");
		} else if ("toEdit".equalsIgnoreCase(action)) {
			String codeNameId = request.getParameter("codeNameId").toString();
			BmCodeName bmCodeName = bmCodeNameService().getBmCodeNameByPk(codeNameId);
			request.setAttribute("bmCodeName", bmCodeName);
			pageUrlEntity.setJspUrl("bm/bmCodeName/bmCodeNameEdit");
		} else if ("toView".equalsIgnoreCase(action)) {
			String codeNameId = request.getParameter("codeNameId").toString();
			BmCodeName bmCodeName = bmCodeNameService().getBmCodeNameByPk(codeNameId);
			request.setAttribute("bmCodeName", bmCodeName);
			pageUrlEntity.setJspUrl("bm/bmCodeName/bmCodeNameView");
		} else if ("add".equalsIgnoreCase(action)) {
			//codeNameId,codeNamePid,code,name,displayNo,displayLevel,endFlag,createDateTime,modifyTimestamp,dataState,			
			String fieldNamesWithComma = "code,name,displayNo";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "BmCodeName");
			bmCodeNameService().addBmCodeName((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/bmCodeName?action=toList");
		} else if ("edit".equalsIgnoreCase(action)) {
			String fieldNamesWithComma = "code,name,displayNo,codeNameId";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "BmCodeName");
			bmCodeNameService().editBmCodeName((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/bmCodeName?action=toList");
		} else if ("delete".equalsIgnoreCase(action)) {
			BmCodeNameService bmCodeNameService = bmCodeNameService();
			bmCodeNameService.deleteBmCodeNamesByPks(request.getParameter("pks").toString());
			pageUrlEntity.setServletUrl("/servlet/bmCodeName?action=toList");
		}
	}
	
	/**
	 * 1、get queryFilters and convert sql 
	 * 2、set queryFilters's field to request
	 * 3、get search result page and set to request
	 * 
	 * @param request
	 */
	public void setBmCodeNamePage(HttpServletRequest request) {
		BmCodeNameService bmCodeNameService = bmCodeNameService();
		String queryFilters = request.getParameter("queryFilters");
		Map sqlAndValuesMap = null;
		if (StringUtil.isNotBlank(queryFilters)) {
			sqlAndValuesMap = SqlUtil.getSqlAndValuesMap(QueryFilterUtil.getQueryFilterList(queryFilters),"BmCodeName");
		}
		if (sqlAndValuesMap!=null) {
			for (Iterator iterator = sqlAndValuesMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (!SqlUtil.SQL_SUFFIX.equals(key) && !SqlUtil.VALUES.equals(key)) {
					request.setAttribute(key, sqlAndValuesMap.get(key));
				}
			}
		}
		request.setAttribute("page", bmCodeNameService.getAllPage(getPageNo(request), getPageSize(request), sqlAndValuesMap));
	}
	
	private BmCodeNameService bmCodeNameService() {
		return new BmCodeNameServiceImpl();
	}
		
}