/*
 * $Id: CmVocabularyServlet,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.cm.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.bm.service.BmMenuService;
import org.ganjp.bm.service.impl.BmMenuServiceImpl;
import org.ganjp.cm.model.CmVocabulary;
import org.ganjp.cm.service.CmVocabularyService;
import org.ganjp.cm.service.impl.CmVocabularyServiceImpl;
import org.ganjp.core.Configuration;
import org.ganjp.core.Constants;
import org.ganjp.core.util.SqlUtil;
import org.ganjp.core.util.StringUtil;
import org.ganjp.core.web.action.BaseServlet;
import org.ganjp.core.web.util.QueryFilterUtil;


public class CmVocabularyServlet extends BaseServlet  {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if ("toList".equalsIgnoreCase(action)) {
			setCmVocabularyPage(request);
			pageUrlEntity.setJspUrl("cm/cmVocabulary/cmVocabularyList");
		} else if ("toAdd".equalsIgnoreCase(action)) {
			request.setAttribute("vocabularyCatagoryIds", request.getParameter("vocabularyCatagoryIds"));
			pageUrlEntity.setJspUrl("cm/cmVocabulary/cmVocabularyAdd");
		} else if ("toEdit".equalsIgnoreCase(action)) {
			String vocabularyId = request.getParameter("vocabularyId").toString();
			CmVocabulary cmVocabulary = cmVocabularyService().getCmVocabularyByPk(vocabularyId);
			request.setAttribute("cmVocabulary", cmVocabulary);
			pageUrlEntity.setJspUrl("cm/cmVocabulary/cmVocabularyEdit");
		} else if ("toView".equalsIgnoreCase(action)) {
			String vocabularyId = request.getParameter("vocabularyId").toString();
			CmVocabulary cmVocabulary = cmVocabularyService().getCmVocabularyByPk(vocabularyId);
			request.setAttribute("cmVocabulary", cmVocabulary);
			pageUrlEntity.setJspUrl("cm/cmVocabulary/cmVocabularyView");
		} else if ("add".equalsIgnoreCase(action)) {
			//vocabularyId,foreignLanguage,phonogram,chinese,propertyCatagoryIds,vocabularyCatagoryIds,levelId,recommendState,displayNo,description,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,			
			String fieldNamesWithComma = "foreignLanguage,phonogram,chinese,propertyCatagoryIds,vocabularyCatagoryIds,levelId,recommendState,displayNo,description";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "CmVocabulary");
			cmVocabularyService().addCmVocabulary((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/cmVocabulary?action=toList");
		} else if ("edit".equalsIgnoreCase(action)) {
			String fieldNamesWithComma = "foreignLanguage,phonogram,chinese,propertyCatagoryIds,vocabularyCatagoryIds,levelId,recommendState,displayNo,description,vocabularyId";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "CmVocabulary");
			cmVocabularyService().editCmVocabulary((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/cmVocabulary?action=toList");
		} else if ("delete".equalsIgnoreCase(action)) {
			cmVocabularyService().deleteCmVocabularysByPks(request.getParameter("pks").toString());
			pageUrlEntity.setServletUrl("/servlet/cmVocabulary?action=toList");
		} else if ("toThumbnail".equalsIgnoreCase(action)) {
			setCmVocabularyPage(request);
			pageUrlEntity.setJspUrl("cm/cmVocabulary/cmVocabularyThumbnail");
		} 
	}
	
	/**
	 * 1、get queryFilters and convert sql 
	 * 2、set queryFilters's field to request
	 * 3、get search result page and set to request
	 * 
	 * @param request
	 */
	public void setCmVocabularyPage(HttpServletRequest request) {
		CmVocabularyService cmVocabularyService = cmVocabularyService();
		String queryFilters = request.getParameter("queryFilters");
		String vocabularyCatagoryIds = request.getParameter("vocabularyCatagoryIds");
		String selectVocabularyCatagoryId = request.getParameter("selectVocabularyCatagoryId");
		if (StringUtil.isNotBlank(vocabularyCatagoryIds)) {
			String vocabularyCatagoryId = "";
			String pid = "";
			if (StringUtil.isNotBlank(selectVocabularyCatagoryId)) {
				vocabularyCatagoryId = selectVocabularyCatagoryId;
				pid = vocabularyCatagoryIds;
				request.setAttribute("selectVocabularyCatagoryId", selectVocabularyCatagoryId);
			} else {
				if (vocabularyCatagoryIds.indexOf(",")==-1) {
					vocabularyCatagoryId = vocabularyCatagoryIds;
				} else {
					vocabularyCatagoryId = vocabularyCatagoryIds.substring(vocabularyCatagoryIds.lastIndexOf(",")+1);
				}
				pid = vocabularyCatagoryId;
			}
			if (StringUtil.isNotBlank(queryFilters)) {
				queryFilters += ",LIKES_vocabularyCatagoryIds:"+vocabularyCatagoryId;
			} else {
				queryFilters = "LIKES_vocabularyCatagoryIds:"+vocabularyCatagoryId;
			}
			List bmMenus = bmMenuService().getBmMenusByPid(pid);
			request.setAttribute("bmMenus", bmMenus);
		}
		
		Map sqlAndValuesMap = null;
		if (StringUtil.isNotBlank(queryFilters)) {
			sqlAndValuesMap = SqlUtil.getSqlAndValuesMap(QueryFilterUtil.getQueryFilterList(queryFilters),"CmVocabulary");
		}
		if (sqlAndValuesMap!=null) {
			for (Iterator iterator = sqlAndValuesMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (!SqlUtil.SQL_SUFFIX.equals(key) && !SqlUtil.VALUES.equals(key)) {
					request.setAttribute(key, sqlAndValuesMap.get(key));
				}
			}
		} 
		
		String navMenuIdsWithComma = request.getParameter("navMenuIdsWithComma");
		if (StringUtil.isNotBlank(navMenuIdsWithComma)) {
			String[] navMenuId = navMenuIdsWithComma.split(",");
			StringBuffer navHtml = new StringBuffer();
			for (int i = 0; i < navMenuId.length; i++) {
				String nav = navMenuId[i];
				navHtml.append(Configuration.getNameFromDbId(Constants.ALL_MENUIDS_MENUNAMES, nav));
				if (i<navMenuId.length-1) {
					navHtml.append("&nbsp;&nbsp;>&nbsp;&nbsp;");
				}
			}
			if (StringUtil.isNotBlank(selectVocabularyCatagoryId)) {
				navHtml.append("&nbsp;&nbsp;>&nbsp;&nbsp;").append(Configuration.getNameFromDbId(Constants.ALL_MENUIDS_MENUNAMES, selectVocabularyCatagoryId));
			}
			request.setAttribute("navHtml", navHtml.toString());
			request.setAttribute("navMenuIdsWithComma", navMenuIdsWithComma);
			if (navMenuIdsWithComma.indexOf(Constants.PHRASE_WORD_MENU_ID)!=-1) {
				request.setAttribute("noShowPic", new Boolean(true));
			}
		}
		request.setAttribute("vocabularyCatagoryIds", vocabularyCatagoryIds);
		request.setAttribute("page", cmVocabularyService.getAllPage(getPageNo(request), getPageSize(request), sqlAndValuesMap));
	}
	
	private CmVocabularyService cmVocabularyService() {
		return new CmVocabularyServiceImpl();
	}
	
	private BmMenuService bmMenuService() {
		return new BmMenuServiceImpl();
	}
		
}