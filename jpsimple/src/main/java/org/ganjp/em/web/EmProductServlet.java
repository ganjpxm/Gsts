/*
 * $Id: EmProductServlet,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.em.web;

import org.ganjp.em.model.EmProduct;
import org.ganjp.em.service.EmProductService;
import org.ganjp.em.service.impl.EmProductServiceImpl;
import org.ganjp.core.util.SqlUtil;
import org.ganjp.core.util.StringUtil;
import org.ganjp.core.web.action.BaseServlet;
import org.ganjp.core.web.util.QueryFilterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;


public class EmProductServlet extends BaseServlet  {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if ("toList".equalsIgnoreCase(action)) {
			setEmProductPage(request);
			pageUrlEntity.setJspUrl("em/emProduct/emProductList");
		} else if ("toAdd".equalsIgnoreCase(action)) {
			pageUrlEntity.setJspUrl("em/emProduct/emProductAdd");
		} else if ("toEdit".equalsIgnoreCase(action)) {
			String productId = request.getParameter("productId").toString();
			EmProduct emProduct = emProductService().getEmProductByPk(productId);
			request.setAttribute("emProduct", emProduct);
			pageUrlEntity.setJspUrl("em/emProduct/emProductEdit");
		} else if ("toView".equalsIgnoreCase(action)) {
			String productId = request.getParameter("productId").toString();
			EmProduct emProduct = emProductService().getEmProductByPk(productId);
			request.setAttribute("emProduct", emProduct);
			pageUrlEntity.setJspUrl("em/emProduct/emProductView");
		} else if ("add".equalsIgnoreCase(action)) {
			//productId,productCd,productNameCn,productNameEn,price,imagepath,description,createDateTime,modifyTimestamp,dataState,			
			String fieldNamesWithComma = "productCd,productNameCn,productNameEn,price,imagepath,description";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "EmProduct");
			emProductService().addEmProduct((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/emProduct?action=toList");
		} else if ("edit".equalsIgnoreCase(action)) {
			String fieldNamesWithComma = "productCd,productNameCn,productNameEn,price,imagepath,description,productId";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "EmProduct");
			emProductService().editEmProduct((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/emProduct?action=toList");
		} else if ("delete".equalsIgnoreCase(action)) {
			emProductService().deleteEmProductsByPks(request.getParameter("pks").toString());
			pageUrlEntity.setServletUrl("/servlet/emProduct?action=toList");
		}
	}
	
	/**
	 * 1、get queryFilters and convert sql 
	 * 2、set queryFilters's field to request
	 * 3、get search result page and set to request
	 * 
	 * @param request
	 */
	public void setEmProductPage(HttpServletRequest request) {
		EmProductService emProductService = emProductService();
		String queryFilters = request.getParameter("queryFilters");
		Map sqlAndValuesMap = null;
		if (StringUtil.isNotBlank(queryFilters)) {
			sqlAndValuesMap = SqlUtil.getSqlAndValuesMap(QueryFilterUtil.getQueryFilterList(queryFilters),"EmProduct");
		}
		if (sqlAndValuesMap!=null) {
			for (Iterator iterator = sqlAndValuesMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (!SqlUtil.SQL_SUFFIX.equals(key) && !SqlUtil.VALUES.equals(key)) {
					request.setAttribute(key, sqlAndValuesMap.get(key));
				}
			}
		}
		request.setAttribute("page", emProductService.getAllPage(getPageNo(request), getPageSize(request), sqlAndValuesMap));
	}
	
	private EmProductService emProductService() {
		return new EmProductServiceImpl();
	}
		
}