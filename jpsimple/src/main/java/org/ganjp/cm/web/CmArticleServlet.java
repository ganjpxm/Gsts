/*
 * $Id: CmArticleServlet,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.cm.web;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ganjp.bm.service.impl.BmMenuServiceImpl;
import org.ganjp.cm.model.CmArticle;
import org.ganjp.cm.service.CmArticleService;
import org.ganjp.cm.service.impl.CmArticleServiceImpl;
import org.ganjp.core.Constants;
import org.ganjp.core.util.SqlUtil;
import org.ganjp.core.util.StringUtil;
import org.ganjp.core.web.action.BaseServlet;
import org.ganjp.core.web.util.QueryFilterUtil;


public class CmArticleServlet extends BaseServlet  {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if ("toList".equalsIgnoreCase(action)) {
			setCmArticlePage(request);
			pageUrlEntity.setJspUrl("cm/cmArticle/cmArticleList");
		} else if ("toSimpleList".equalsIgnoreCase(action)) {
			setCmArticlePage(request);
			pageUrlEntity.setJspUrl("cm/cmArticle/cmArticleSimpleList");
		} else if ("toAdd".equalsIgnoreCase(action)) {
			request.setAttribute("articleCategoryIds", request.getParameter("articleCategoryIds"));
			pageUrlEntity.setJspUrl("cm/cmArticle/cmArticleAdd");
		} else if ("toEdit".equalsIgnoreCase(action)) {
			String articleId = request.getParameter("articleId").toString();
			CmArticle cmArticle = cmArticleService().getCmArticleByPk(articleId);
			request.setAttribute("cmArticle", cmArticle);
			pageUrlEntity.setJspUrl("cm/cmArticle/cmArticleEdit");
		} else if ("toView".equalsIgnoreCase(action)) {
			String articleId = request.getParameter("articleId").toString();
			CmArticle cmArticle = cmArticleService().getCmArticleByPk(articleId);
			//cmArticle.setArticleContent(StringUtil.convertForHtml(cmArticle.getArticleContent()));
			request.setAttribute("cmArticle", cmArticle);
			pageUrlEntity.setJspUrl("cm/cmArticle/cmArticleView");
		}  else if ("toSimpleView".equalsIgnoreCase(action)) {
			String articleId = request.getParameter("articleId").toString();
			CmArticle cmArticle = cmArticleService().getCmArticleByPk(articleId);
			request.setAttribute("cmArticle", cmArticle);
			request.setAttribute("articleCategoryIds", request.getParameter("articleCategoryIds"));
			pageUrlEntity.setJspUrl("cm/cmArticle/cmArticleSimpleView");
		} else if ("add".equalsIgnoreCase(action)) {
			//articleId,articleCategoryIds,articleCd,articleTitle,articleContent,commentAuthorId,commentAuthorName,articleRecommendLevelId,displayNo,remark,auditFlag,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,			
			String fieldNamesWithComma = "articleCd,articleCategoryIds,articleTitle,articleContent,displayNo";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "CmArticle");
			cmArticleService().addCmArticle((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/cmArticle?action=toList");
		} else if ("edit".equalsIgnoreCase(action)) {
			String fieldNamesWithComma = "articleCd,articleCategoryIds,articleTitle,articleContent,displayNo,articleId";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "CmArticle");
			cmArticleService().editCmArticle((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/cmArticle?action=toList");
		} else if ("delete".equalsIgnoreCase(action)) {
			cmArticleService().deleteCmArticlesByPks(request.getParameter("pks").toString());
			pageUrlEntity.setServletUrl("/servlet/cmArticle?action=toList");
		} else if ("toArticleMenuAndTitle".equalsIgnoreCase(action)) {
			HttpSession session = request.getSession();
			if (session.getAttribute("htmlMenu")==null) {
				String htmlMenu = new BmMenuServiceImpl().getOneLevelMenuHtml(Constants.WEBSITE_MENU_ROOT_ID,super.getBasePath(request));
				session.setAttribute("htmlMenu", htmlMenu);
			}
			if (session.getAttribute("accordionHtml")==null) {
				request.setAttribute("accordionHtml", new BmMenuServiceImpl().getAccordionHtml(request.getParameter("menuId"),super.getBasePath(request)));
			}
			request.setAttribute("articleCategoryId",request.getParameter("menuId"));
			pageUrlEntity.setJspUrl("ddb/articleMenuAndTitle");
		} else if ("toArticleTitle".equalsIgnoreCase(action)) {
			setCmArticlePage(request);
			pageUrlEntity.setJspUrl("ddb/articleTitle");
		}  else if ("toArticleContent".equalsIgnoreCase(action)) {
			String articleId = request.getParameter("articleId").toString();
			CmArticle cmArticle = cmArticleService().getCmArticleByPk(articleId);
			request.setAttribute("cmArticle", cmArticle);
			request.setAttribute("articleCategoryIds", request.getParameter("articleCategoryIds"));
			pageUrlEntity.setJspUrl("ddb/articleContent");
		}  else if ("toHeaderArticleContentFooter".equalsIgnoreCase(action)) {
			String articleId = request.getParameter("articleId").toString();
			CmArticle cmArticle = cmArticleService().getCmArticleByPk(articleId);
			request.setAttribute("cmArticle", cmArticle);
			pageUrlEntity.setJspUrl("ddb/headerArticleContentFooter");
		} else if ("toArticlePictureTitle".equalsIgnoreCase(action)) {
			setCmArticlePage(request);
			pageUrlEntity.setJspUrl("ddb/articlePictureTitle");
		} 
	}
	
	/**
	 * 1、get queryFilters and convert sql 
	 * 2、set queryFilters's field to request
	 * 3、get search result page and set to request
	 * 
	 * @param request
	 */
	public void setCmArticlePage(HttpServletRequest request) {
		String queryFilters = request.getParameter("queryFilters");
		String articleCategoryIds = request.getParameter("articleCategoryIds");
		if (StringUtil.isNotBlank(articleCategoryIds)) {
			String articleCategoryId = "";
			if (articleCategoryIds.indexOf(",")==-1) {
				articleCategoryId = articleCategoryIds;
			} else {
				articleCategoryId = articleCategoryIds.substring(articleCategoryIds.lastIndexOf(",")+1);
			}
			if (StringUtil.isNotBlank(queryFilters)) {
				queryFilters += ",LIKES_articleCategoryIds:"+articleCategoryId;
			} else {
				queryFilters = "LIKES_articleCategoryIds:"+articleCategoryId;
			}
		}
		Map sqlAndValuesMap = null;
		if (StringUtil.isNotBlank(queryFilters)) {
			sqlAndValuesMap = SqlUtil.getSqlAndValuesMap(QueryFilterUtil.getQueryFilterList(queryFilters),"CmArticle");
		}
		if (sqlAndValuesMap!=null) {
			for (Iterator iterator = sqlAndValuesMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (!SqlUtil.SQL_SUFFIX.equals(key) && !SqlUtil.VALUES.equals(key)) {
					request.setAttribute(key, sqlAndValuesMap.get(key));
				}
			}
		}
		request.setAttribute("articleCategoryIds", articleCategoryIds);
		request.setAttribute("page", cmArticleService().getAllPage(getPageNo(request), getPageSize(request), sqlAndValuesMap));
	}
	
	private CmArticleService cmArticleService() {
		return new CmArticleServiceImpl();
	}
		
}