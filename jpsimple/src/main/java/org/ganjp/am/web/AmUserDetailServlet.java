/*
 * $Id: AmUserDetailServlet,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.web;

import org.ganjp.am.model.AmUserDetail;
import org.ganjp.am.service.AmUserDetailService;
import org.ganjp.am.service.impl.AmUserDetailServiceImpl;
import org.ganjp.core.util.SqlUtil;
import org.ganjp.core.util.StringUtil;
import org.ganjp.core.web.action.BaseServlet;
import org.ganjp.core.web.util.QueryFilterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;


public class AmUserDetailServlet extends BaseServlet  {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if ("toList".equalsIgnoreCase(action)) {
			setAmUserDetailPage(request);
			pageUrlEntity.setJspUrl("am/amUserDetail/amUserDetailList");
		} else if ("toAdd".equalsIgnoreCase(action)) {
			pageUrlEntity.setJspUrl("am/amUserDetail/amUserDetailAdd");
		} else if ("toEdit".equalsIgnoreCase(action)) {
			String userDetailId = request.getParameter("userDetailId").toString();
			AmUserDetail amUserDetail = amUserDetailService().getAmUserDetailByPk(userDetailId);
			request.setAttribute("amUserDetail", amUserDetail);
			pageUrlEntity.setJspUrl("am/amUserDetail/amUserDetailEdit");
		} else if ("toView".equalsIgnoreCase(action)) {
			String userDetailId = request.getParameter("userDetailId").toString();
			AmUserDetail amUserDetail = amUserDetailService().getAmUserDetailByPk(userDetailId);
			request.setAttribute("amUserDetail", amUserDetail);
			pageUrlEntity.setJspUrl("am/amUserDetail/amUserDetailView");
		} else if ("add".equalsIgnoreCase(action)) {
			//userDetailId,userId,mobilePhone,homePhone,countryId,provinceId,cityId,birthPlace,livePlace,annualIncome,jobCategoryPid,jobCategoryId,jobPositionId,companyName,shoolName,educationId,interestIds,passwordTipCustom,passwordTipId,passwordTipAnswer,signature,attach,attachName,remark,createDateTime,modifyTimestamp,dataState,			
			String fieldNamesWithComma = "add fields";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "AmUserDetail");
			amUserDetailService().addAmUserDetail((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/amUserDetail?action=toList");
		} else if ("edit".equalsIgnoreCase(action)) {
			String fieldNamesWithComma = "edit fields, pk";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "AmUserDetail");
			amUserDetailService().editAmUserDetail((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/amUserDetail?action=toList");
		} else if ("delete".equalsIgnoreCase(action)) {
			amUserDetailService().deleteAmUserDetailsByPks(request.getParameter("pks").toString());
			pageUrlEntity.setServletUrl("/servlet/amUserDetail?action=toList");
		}
	}
	
	/**
	 * 1、get queryFilters and convert sql 
	 * 2、set queryFilters's field to request
	 * 3、get search result page and set to request
	 * 
	 * @param request
	 */
	public void setAmUserDetailPage(HttpServletRequest request) {
		AmUserDetailService amUserDetailService = amUserDetailService();
		String queryFilters = request.getParameter("queryFilters");
		Map sqlAndValuesMap = null;
		if (StringUtil.isNotBlank(queryFilters)) {
			sqlAndValuesMap = SqlUtil.getSqlAndValuesMap(QueryFilterUtil.getQueryFilterList(queryFilters),"AmUserDetail");
		}
		if (sqlAndValuesMap!=null) {
			for (Iterator iterator = sqlAndValuesMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (!SqlUtil.SQL_SUFFIX.equals(key) && !SqlUtil.VALUES.equals(key)) {
					request.setAttribute(key, sqlAndValuesMap.get(key));
				}
			}
		}
		request.setAttribute("page", amUserDetailService.getAllPage(getPageNo(request), getPageSize(request), sqlAndValuesMap));
	}
	
	private AmUserDetailService amUserDetailService() {
		return new AmUserDetailServiceImpl();
	}
		
}