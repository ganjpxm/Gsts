/*
 * $Id: AmUserServlet,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.am.model.AmUser;
import org.ganjp.am.service.AmCommonService;
import org.ganjp.am.service.AmUserRoleService;
import org.ganjp.am.service.AmUserService;
import org.ganjp.am.service.impl.AmCommonServiceImpl;
import org.ganjp.am.service.impl.AmUserRoleServiceImpl;
import org.ganjp.am.service.impl.AmUserServiceImpl;
import org.ganjp.core.Configuration;
import org.ganjp.core.Constants;
import org.ganjp.core.db.id.UUIDHexGenerator;
import org.ganjp.core.util.JsonUtil;
import org.ganjp.core.util.SqlUtil;
import org.ganjp.core.util.StringUtil;
import org.ganjp.core.web.action.BaseServlet;
import org.ganjp.core.web.util.QueryFilterUtil;


public class AmUserServlet extends BaseServlet  {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if ("toList".equalsIgnoreCase(action)) {
			setAmUserPage(request);
			pageUrlEntity.setJspUrl("am/amUser/amUserList");
		} else if ("toAdd".equalsIgnoreCase(action)) {
			request.setAttribute("userId", UUIDHexGenerator.getUuid());
			request.setAttribute("roleIdAndNameMap", Configuration.getCashFromDb(Constants.ALL_ROLEIDS_ROLENAMES));
			request.setAttribute("treeRoleId", request.getParameter("treeRoleId"));
			pageUrlEntity.setJspUrl("am/amUser/amUserAdd");
		} else if ("toEdit".equalsIgnoreCase(action)) {
			String userId = request.getParameter("userId").toString();
			AmUser amUser = amUserService().getAmUserByPk(userId);
			request.setAttribute("amUser", amUser);
			request.setAttribute("roleIds", amUserRoleService().getRoleIdsWithCommaByUserId(userId));
			request.setAttribute("treeRoleId", request.getParameter("treeRoleId"));
			request.setAttribute("roleIdAndNameMap", Configuration.getCashFromDb(Constants.ALL_ROLEIDS_ROLENAMES));
			pageUrlEntity.setJspUrl("am/amUser/amUserEdit");
		} else if ("toView".equalsIgnoreCase(action)) {
			String userId = request.getParameter("userId").toString();
			AmUser amUser = amUserService().getAmUserByPk(userId);
			request.setAttribute("amUser", amUser);
			request.setAttribute("treeRoleId", request.getParameter("treeRoleId"));
			pageUrlEntity.setJspUrl("am/amUser/amUserView");
		} else if ("add".equalsIgnoreCase(action)) {
			//userId,userCd,userName,userAlias,userLevel,userScore,loginTimes,password,gender,qq,email,birthYear,birthMonth,birthDay,createDateTime,modifyTimestamp,dataState,			
			String fieldNamesWithComma = "userId,userCd,userName,userAlias,userLevel,userScore,password,gender,qq,email";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "AmUser");
			amUserService().addAmUser((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			amUserRoleService().batchAddUserRole(request.getParameter("roleIds"), request.getParameter("userId"));
			pageUrlEntity.setServletUrl("/servlet/amUser?action=toList&treeRoleId=" + request.getParameter("treeRoleId"));
		} else if ("edit".equalsIgnoreCase(action)) {
			String fieldNamesWithComma = "userCd,userName,userAlias,userLevel,userScore,password,gender,qq,email,userId";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "AmUser");
			amCommonService().editAmUserAndAmUserRole((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES),
					request.getParameter("roleIds"));
			pageUrlEntity.setServletUrl("/servlet/amUser?action=toList&treeRoleId="+request.getParameter("treeRoleId"));
		} else if ("delete".equalsIgnoreCase(action)) {
			amUserService().deleteAmUsersByPks(request.getParameter("pks").toString());
			pageUrlEntity.setServletUrl("/servlet/amUser?action=toList&treeRoleId="+request.getParameter("treeRoleId"));
		} else if ("signIn".equalsIgnoreCase(action)) {
			String userCd = request.getParameter("userCd");
			String password = request.getParameter("password");
			if (StringUtil.isBlank(userCd) || StringUtil.isBlank(password)) {
				pageUrlEntity.setJsonData("{\"result\":\"fail\",\"msg\":\"name and password are not empty\"}");
			} else {
				AmUser amUser = amCommonService().getAmUserInAmUserRole(userCd, password);
				if (amUser==null) {
					pageUrlEntity.setJsonData("{\"result\":\"fail\",\"msg\":\"user name or password is error\"}");
				} else {
					request.getSession().setAttribute("amUser", amUser);
					Map map = new HashMap();
					map.put("userCd", amUser.getUserCd());
					map.put("url", getUrl(amUser.getRoleIds(), request));
					pageUrlEntity.setJsonData(JsonUtil.toJson(map));
				}
			}
		} else if ("signUp".equalsIgnoreCase(action)) {
			try {
				String uuid = UUIDHexGenerator.getUuid();
				String fieldNamesWithComma = "userId,userCd,password,email";
				Map map = new HashMap();
				map.put("userId", uuid);
				Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "AmUser", map);
				amUserService().addAmUser((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
				amUserRoleService().batchAddUserRole(Constants.GUEST_ROLE_ID, uuid);
				AmUser amUser = amCommonService().getAmUserInAmUserRole(request.getParameter("userCd"), request.getParameter("password"));
				request.getSession().setAttribute("amUser", amUser);
			} catch (Exception ex) {
				pageUrlEntity.setJsonData("{\"result\":\"fail\",\"msg\":\""+ ex.getMessage() +"\"}");
			}
			pageUrlEntity.setJsonData("{\"result\":\"success\"}");
		}
	}
	
	/**
	 * 1、get queryFilters and convert sql 
	 * 2、set queryFilters's field to request
	 * 3、get search result page and set to request
	 * 
	 * @param request
	 */
	public void setAmUserPage(HttpServletRequest request) {
		String queryFilters = request.getParameter("queryFilters");
		String treeRoleId = request.getParameter("treeRoleId");
		if (StringUtil.isNotBlank(treeRoleId)) {
			if (StringUtil.isNotBlank(queryFilters)) {
				queryFilters += ",EQS_roleId:"+treeRoleId;
			} else {
				queryFilters = "EQS_AmUserRole.roleId:"+treeRoleId;
			}
		}
		Map sqlAndValuesMap = null;
		if (StringUtil.isNotBlank(queryFilters)) {
			sqlAndValuesMap = SqlUtil.getSqlAndValuesMap(QueryFilterUtil.getQueryFilterList(queryFilters),"AmUser");
		}
		if (sqlAndValuesMap!=null) {
			for (Iterator iterator = sqlAndValuesMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (!SqlUtil.SQL_SUFFIX.equals(key) && !SqlUtil.VALUES.equals(key)) {
					request.setAttribute(key, sqlAndValuesMap.get(key));
				}
			}
		}
		request.setAttribute("treeRoleId", treeRoleId);
		request.setAttribute("page", amUserService().getAllPage(getPageNo(request), getPageSize(request), sqlAndValuesMap));
	}
	
	private AmUserService amUserService() {
		return new AmUserServiceImpl();
	}
		
	private AmCommonService amCommonService() {
		return new AmCommonServiceImpl();
	}
	
	private AmUserRoleService amUserRoleService() {
		return new AmUserRoleServiceImpl();
	}
}