/*
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 * 
 */
package org.ganjp.core.web.util;

import java.util.ArrayList;
import java.util.List;

import org.ganjp.core.util.StringUtil;

/**
 * 
 * @author BizFoundation Team: alex
 * 
 * @version 1.0
 * @since 4.2
 * 
 * $Revision: 1.2 $
 */
public class QueryFilterUtil {
	private String filterString = null;

	public String getFilterString() {
		return filterString;
	}

	public void setFilterString(String filterString) {
		this.filterString = filterString;
	}

	public List getfilters() {
		// LTD_foo:2046-01-01,GTI_foo:123,LIKES_foo_OR_bar:hello
		List filters = null;
		if (StringUtil.isNotBlank(filterString)) {
			filters = new ArrayList();
			String[] strArr = filterString.split(",");
			for (int i = 0; i < strArr.length; i++) {
				String str = strArr[i];
				String[] v = str.split(":");
				filters.add(new QueryFilter(v[0], v[1]));
			}
			return filters;
		}
		return null;
	}
	
	/**
	 * 通过字符串(LTD_foo:2046-01-01,GTI_foo:123)获得QueryFilter对象集
	 * @param searchFilters
	 * @return List(QueryFilter)
	 * @author ganjp
	 */
	public static List getQueryFilterList(String searchFilters) {
		List filters = null;
		if (StringUtil.isNotBlank(searchFilters)) {
			filters = new ArrayList();
			String[] strArr = searchFilters.split(",");
			for (int i = 0; i < strArr.length; i++) {
				String str = strArr[i];
				String[] v = str.split(":");
				if (v.length == 2 ) {
					filters.add(new QueryFilter(v[0], v[1]));
				}
			}
			return filters;
		}
		return null;
	}
}
