/**
 * Copyright 2012 ganjp
 */
package org.ganjp.bm.web;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.bm.model.BmConfig;
import org.ganjp.bm.service.BmConfigService;
import org.ganjp.bm.service.impl.BmConfigServiceImpl;
import org.ganjp.core.Constants;
import org.ganjp.core.util.SqlUtil;
import org.ganjp.core.util.StringUtil;
import org.ganjp.core.web.action.BaseServlet;
import org.ganjp.core.web.util.QueryFilterUtil;

public class BmConfigServlet extends BaseServlet  {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if ("toList".equalsIgnoreCase(action)) {
			setBmConfigPage(request);
			pageUrlEntity.setJspUrl("bm/bmConfig/bmConfigList");
		} else if ("toAdd".equalsIgnoreCase(action)) {
			pageUrlEntity.setJspUrl("bm/bmConfig/bmConfigAdd");
		} else if ("toEdit".equalsIgnoreCase(action)) {
			String configId = request.getParameter("configId").toString();
			BmConfig bmConfig = bmConfigService().getBmConfigByConfigByPk(configId);
			request.setAttribute("bmConfig", bmConfig);
			pageUrlEntity.setJspUrl("bm/bmConfig/bmConfigEdit");
		} else if ("toView".equalsIgnoreCase(action)) {
			String configId = request.getParameter("configId").toString();
			BmConfig bmConfig = bmConfigService().getBmConfigByConfigByPk(configId);
			request.setAttribute("bmConfig", bmConfig);
			pageUrlEntity.setJspUrl("bm/bmConfig/bmConfigView");
		} else if ("add".equalsIgnoreCase(action)) {
			String fieldNamesWithComma = "configKey,configKeyDisplayName,configValue,description";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "BmConfig");
			bmConfigService().addBmConfig((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/bmConfig?action=toList");
		} else if ("edit".equalsIgnoreCase(action)) {
			String fieldNamesWithComma = "configKey,configKeyDisplayName,configValue,description,configId";
			Map sqlInfoMap = this.getSqlInfoMap(request, fieldNamesWithComma, "BmConfig");
			bmConfigService().editBmConfig((String[])sqlInfoMap.get(SqlUtil.COLUMN_NAMES), (Object[])sqlInfoMap.get(SqlUtil.VALUES));
			pageUrlEntity.setServletUrl("/servlet/bmConfig?action=toList");
		} else if ("delete".equalsIgnoreCase(action)) {
			BmConfigService bmConfigService = bmConfigService();
			bmConfigService.deleteBmConfigsByPks(request.getParameter("pks").toString());
			pageUrlEntity.setServletUrl("/servlet/bmConfig?action=toList");
		}
		
	}
	
	/**
	 * 1、get queryFilters and convert sql 
	 * 2、set queryFilters's field to request
	 * 3、get search result page and set to request
	 * 
	 * @param request
	 */
	public void setBmConfigPage(HttpServletRequest request) {
		BmConfigService bmConfigService = bmConfigService();
		String queryFilters = request.getParameter("queryFilters");
		Map sqlAndValuesMap = null;
		if (StringUtil.isNotBlank(queryFilters)) {
			sqlAndValuesMap = SqlUtil.getSqlAndValuesMap(QueryFilterUtil.getQueryFilterList(queryFilters),"BmConfig");
		}
		if (sqlAndValuesMap!=null) {
			for (Iterator iterator = sqlAndValuesMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (!SqlUtil.SQL_SUFFIX.equals(key) && !SqlUtil.VALUES.equals(key)) {
					request.setAttribute(key, sqlAndValuesMap.get(key));
				}
			}
		}
		request.setAttribute("page", bmConfigService.getAllPage(getPageNo(request), getPageSize(request), sqlAndValuesMap));
	}
	
	private BmConfigService bmConfigService() {
		return new BmConfigServiceImpl();
	}
}