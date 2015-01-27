/**
 * $Id: BaseController.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.common.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.am.model.AmUser;
import org.ganjp.gone.common.GOneConst;
import org.ganjp.gone.common.model.BaseModel;
import org.ganjp.gone.common.util.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Base Controller</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public class BaseController {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * <p>set baseModel object's field value from request</p>
	 * 
	 * @param request
	 * @param baseModel
	 */
	protected void setValue(HttpServletRequest request, BaseModel baseModel) {
		ServletUtil.setValue(request, baseModel, null);
	}
	
	/**
	 * <p>set baseModel object's field value from request</p>
	 * 
	 * @param request
	 * @param baseModel
	 */
	protected void setValue(HttpServletRequest request, BaseModel baseModel, String suffix) {
		ServletUtil.setValue(request, baseModel, suffix);
	}
	/**
	 * <p>get base path</p>
	 * 
	 * @param request
	 * @return
	 */
	public String getBasePath(HttpServletRequest request) {
		if ( ( request.getServerPort() == 80 ) || ( request.getServerPort() == 443 ) ) {
			return request.getScheme() + "://" + request.getServerName() + request.getContextPath();
		} else {
		    return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		}
	}

	protected AmUser getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		synchronized (session) {
			return (AmUser)session.getAttribute(GOneConst.KEY_USER);
		}
	}
	
	protected String getLoginUserId(HttpServletRequest request) {
		AmUser amUser = this.getLoginUser(request);
		if (amUser!=null) {
			return amUser.getUserId();
		} else {
			return null;
		}
		
	}
	
	protected String getLoginUserName(HttpServletRequest request) {
		AmUser amUser = this.getLoginUser(request);
		if (amUser!=null) {
			return amUser.getUserName();
		} else {
		    return "";
		}
	}
	
	
	protected void accessProject(HttpServletRequest request, String project) {
		request.getSession().setAttribute("project", project);
	}
	
	/**
	 * <p>getOptionsHtml</p>
	 * 
	 * @param idNames "01,Country"
	 * @param selectedIdOrText
	 * @return
	 */
	protected String getOptionsHtml(List<String> idNameValues, String selectedIdOrText) {
		String optionHtml="";
		if (idNameValues!=null) {
			int index = 0;
			for (String idNameWithComma : idNameValues) {
				String[] idNameArr = idNameWithComma.split(",");
				String name = idNameArr[1];
				if (name.indexOf(":")==-1) {
					if (idNameArr.length==3) {
						if (idNameArr[2].contains("hideValue=")) {
							optionHtml += "<option value='" + idNameArr[0] + "' ";
							String hideValue = idNameArr[2].replace("hideValue=", "");
							optionHtml += " id='" + idNameArr[0] + "' ";
							optionHtml += " hideValue='" + hideValue + "' ";
						} else {
							if (idNameArr[2].equalsIgnoreCase("default")) {
								optionHtml += "<option value='" + idNameArr[0] + "' selected='selected' ";
							} else {
								optionHtml += "<option value='" + idNameArr[2] + "' ";
								if (idNameArr[2].equalsIgnoreCase(selectedIdOrText)) {
									optionHtml += " selected='selected' ";
								}
							}
						}
					} else {
						optionHtml += "<option value='" + idNameArr[0] + "' ";
					}
					if (idNameArr[0].equalsIgnoreCase(selectedIdOrText) || idNameArr[1].equalsIgnoreCase(selectedIdOrText)) {
						optionHtml += " selected='selected' ";
					}
					optionHtml += ">" + idNameArr[1] + "</option>";
				} else {
					String[] names = name.split(":");
					if (optionHtml.indexOf("'" + names[0] + "'")==-1) {
						if (index++>0) {
							optionHtml += "</optgroup>";
						}
						optionHtml += "<optgroup label='" + names[0] + "'>";
					}	
					if (idNameArr.length==3) {
						if (idNameArr[2].contains("hideValue=")) {
							String hideValue = idNameArr[2].replace("hideValue=", "");
							optionHtml += " id='" + idNameArr[0] + "' ";
							optionHtml += " hideValue='" + hideValue + "'";
						} else {
							if (idNameArr[2].equalsIgnoreCase("default")) {
								optionHtml += "<option value='" + idNameArr[0] + "' selected='selected' ";
							} else {
								if (idNameArr[2].equalsIgnoreCase("popular")) {
									optionHtml += "<option value='" + idNameArr[0] + "' ";
								} else {
									optionHtml += "<option value='" + idNameArr[2] + "' ";
								}
							}
						}
					} else {
						optionHtml += "<option value='" + idNameArr[0] + "' ";
					}
					if (idNameArr[0].equalsIgnoreCase(selectedIdOrText)) {
						optionHtml += "selected='selected'";
					}
					optionHtml += ">" + names[1] + "</option>";
				}
			}
			if (index>0) {
				optionHtml += "<optgroup/>";
			}
		}
		return optionHtml;
	}
	
	/**
	 * <p>getOptionsHtml</p>
	 * 
	 * @param keyAndIdNames
	 * @param keyAndSelectedId
	 * @return
	 */
	protected Map<String,String> getKeyAndOptionsHtml(Map<String, List<String>> keyAndIdNames, Map<String,String> keyAndSelectedId) {
		Map<String,String> map = new HashMap<String,String>();
		if (keyAndIdNames!=null && !keyAndIdNames.isEmpty()) { 
			for (String key : keyAndIdNames.keySet()) {
				List<String> idNames = keyAndIdNames.get(key);
				if (idNames!=null && idNames.size()>0) {
					String optionHtml="";
					for (String idNameWithComma : idNames) {
						String[] orgIdNameArr = idNameWithComma.split(",");
						optionHtml += "<option value='" + orgIdNameArr[0] + "' ";
						if (keyAndSelectedId!=null && orgIdNameArr[0].equalsIgnoreCase(keyAndSelectedId.get(key))) {
							optionHtml += "selected='selected'";
						}
						optionHtml += ">" + orgIdNameArr[1] + "</option>";
					}
					map.put(key, optionHtml);
				}
			}
		}
		return map;
	}
	
	/**
	 * <p>getCheckboxsHtml</p>
	 * 
	 * @param idNames
	 * @param selectedIds
	 * @param fieldName
	 * @return
	 */
	protected String getCheckboxsHtml(List<String> idNames, String selectedIds, String fieldName) {
		String checkboxsHtml="";
		if (idNames!=null) {
			for (String idNameWithComma : idNames) {
				String[] orgIdNameArr = idNameWithComma.split(",");
				String id = fieldName + orgIdNameArr[0];
				checkboxsHtml += "<input type='checkbox' id='" + id + "' name='" + fieldName + "' value='" + orgIdNameArr[0] + "' ";
				if (StringUtil.isNotEmpty(selectedIds) && selectedIds.indexOf(orgIdNameArr[0])!=-1) {
					checkboxsHtml += "checked";
				}
				checkboxsHtml += "/>";
				checkboxsHtml += "<label for='" + id + "'>" + orgIdNameArr[1] + "</label>";
			}
		}
		return checkboxsHtml;
	}
	
	/**
	 * <p>getItemsWithCommaAndSemicolon</p>
	 * 
	 * @param request
	 * @param itemNamesWithCommas
	 * @return
	 */
	protected String getItemsWithCommaAndSemicolon(HttpServletRequest request, String itemNamesWithCommas) {
		String itemsWithCommaAndSemicolon = "";
		if (StringUtil.isNotEmpty(itemNamesWithCommas)) {
			String[] itemNames = itemNamesWithCommas.split(",");
			for (String itemName : itemNames) {
				String itemValue = request.getParameter(itemName);
				if (StringUtil.isNotEmpty(itemValue)) {
					itemsWithCommaAndSemicolon += itemName + ":" + itemValue + ";";
				}
			}
		}
		if (StringUtil.isNotEmpty(itemsWithCommaAndSemicolon)) {
			itemsWithCommaAndSemicolon = itemsWithCommaAndSemicolon.substring(0, itemsWithCommaAndSemicolon.length()-1);
		}
		return itemsWithCommaAndSemicolon;
	}
	
	/**
	 * <p>setItemsWithCommaAndSemicolon</p>
	 * 
	 * @param request
	 * @param itemsWithCommaAndSemicolon
	 */
	protected void setItemsWithCommaAndSemicolon(HttpServletRequest request, String itemsWithCommaAndSemicolon) {
		if (StringUtil.isNotEmpty(itemsWithCommaAndSemicolon)) {
			String[] items = itemsWithCommaAndSemicolon.split(";");
			for (String itemNameValueWithComma : items) {
				String[] itemNameValues = itemNameValueWithComma.split(":");
				if (itemNameValues.length==2) {
					request.setAttribute(itemNameValues[0], itemNameValues[1]);
				}
			}
		}
	}
	
	/**
	 * <p>getIpAddr</p>
	 * 
	 * @param request
	 * @return
	 */
	public  String getIpAddr(HttpServletRequest request)  {
		String ip  =  request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		} 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		} 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))  {
			ip = request.getRemoteAddr();
		}
		if (StringUtil.isEmpty(ip)) {
			ip = "ip";
		}
		return  ip;
	}
	
	/**
	 * <p>getSortedCity</p>
	 * 
	 * @param cityCdCountyNameCityNameValues
	 * @param isAddPopularCity
	 * @return
	 */
	public List<String> getSortedCity(List<String> cityCdCountyNameCityNameValues, boolean isAddPopularCity) {
		try {
			List<String> popularCitys = new ArrayList<String>();
			
			Map<String,List<String>> countryNameAndCityCdNameValues = new LinkedHashMap<String, List<String>>();
			for (String cityCdCountyNameCityNameValue : cityCdCountyNameCityNameValues) {
				String[] arr = cityCdCountyNameCityNameValue.split(",");
				String[] countyNameCitys = arr[1].split(":");
				if (cityCdCountyNameCityNameValue.indexOf("default")!=-1 || cityCdCountyNameCityNameValue.indexOf("popular")!=-1) {
					popularCitys.add(arr[0] + ",Popular:" + countyNameCitys[1]);
				}
				if (countryNameAndCityCdNameValues.containsKey(countyNameCitys[0])) {
					countryNameAndCityCdNameValues.get(countyNameCitys[0]).add(cityCdCountyNameCityNameValue);
				} else {
					List<String> list = new ArrayList<String>();
					list.add(cityCdCountyNameCityNameValue);
					countryNameAndCityCdNameValues.put(countyNameCitys[0], list);
				}
			}
			List<String> citys = new ArrayList<String>();
			if (!popularCitys.isEmpty() && isAddPopularCity) {
				Collections.sort(popularCitys, new Comparator<String>(){
	                public int compare(String s1,String s2){
	                	s1 = s1.split(":")[1];
	                	s2 = s2.split(":")[1];
	                	return s1.compareTo(s2);
	                }
	            });
				citys.addAll(popularCitys);
			}
			for (String key  : countryNameAndCityCdNameValues.keySet()) {
				List<String> list = countryNameAndCityCdNameValues.get(key);
				Collections.sort(list, new Comparator<String>(){
	                public int compare(String s1,String s2){
	                	s1 = s1.split(":")[1];
	                	s2 = s2.split(":")[1];
	                	return s1.compareTo(s2);
	                }
	            });
				citys.addAll(list);
			}
			return citys;
		} catch(Exception ex) {
			log.error(ex.getMessage());
		}
		return cityCdCountyNameCityNameValues;
	}
	
	public String getUserCd(String mobileNumber) {
		return mobileNumber.replaceAll("\\+", "").replaceAll("-", "").replaceAll(" ", "");
	}
	
}
