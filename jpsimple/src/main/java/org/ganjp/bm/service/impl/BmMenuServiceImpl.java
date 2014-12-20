/*
 * $Id: BmMenuServiceImpl,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.bm.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ganjp.bm.dao.BmMenuDao;
import org.ganjp.bm.model.BmMenu;
import org.ganjp.bm.service.BmMenuService;
import org.ganjp.core.Constants;
import org.ganjp.core.db.base.Page;
import org.ganjp.core.service.BaseService;
import org.ganjp.core.util.JsonUtil;
import org.ganjp.core.util.StringUtil;

/**
 * {annotation}
 * 
 * @author ganjp
 * @version 1.0
 * @since 1.0
 */
public class BmMenuServiceImpl extends BaseService implements BmMenuService {
	
	/**
	 * add BmMenu
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addBmMenu(final String[] columnNames, final Object[] paramObjects) {
		bmMenuDao().addBmMenu(columnNames, paramObjects);
	}
	
	/**
	 * edit BmMenu
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editBmMenu(final String[] columnNames, final Object[] paramObjects) {
		bmMenuDao().editBmMenu(columnNames, paramObjects);
	}
	
	/**
	 * delete BmMenu records by primary key
	 * 
	 * @param menuId
	 */
	public void deleteBmMenuByPk(String menuId) {
		bmMenuDao().deleteBmMenuByPk(menuId);
	}
	
	/**
	 * batch delete BmMenu records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteBmMenusByPks(String pks) {
		bmMenuDao().deleteBmMenusByPks(pks);
	}
	
	/**
	 * get BmMenu by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public BmMenu getBmMenuByPk(final String pk) {
		return bmMenuDao().getBmMenuByPk(pk);
	}
	
	/**
	 * get all BmMenu records 
	 * 
	 * @return
	 */
	public List getAll() {
		return bmMenuDao().getAll();
	}
	
	/**
	 * get all BmMenu record Page 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, final Map searchSqlAndValuesMap) {
		return bmMenuDao().getAllPage(pageNo, pageSize, searchSqlAndValuesMap);
	}
	
	/**
	 * 
	 * @param newRecordArrStr []
	 * @param modifyRecordArrStr
	 * @param delNodeIds
	 */
	public void crudMenuTree(String newRecordArrStr, String modifyRecordArrStr, String delNodeIds) {
		try {
			bmMenuDao().openTransaction();
			
			Map replaceValueMap = new HashMap();
			replaceValueMap.put("isParent", "true:" + Constants.END_FLAG_NO + ",false:" + Constants.END_FLAG_YES);
			List newParamList = JsonUtil.getParamArrList(newRecordArrStr,"id,pid,cd,name,order,level,isParent",replaceValueMap);
			bmMenuDao().batchAddBmMenu("menu_id,menu_pid,menu_cd,menu_name,display_no,display_level,end_flag",newParamList,false);
			
			List editParamArrList = JsonUtil.getParamArrList(modifyRecordArrStr,"pid,cd,name,order,level,id");
			bmMenuDao().batchEditBmMenu("menu_pid,menu_cd,menu_name,display_no,display_level,menu_id",editParamArrList,false);

			this.deleteBmMenusByPks(delNodeIds);
			bmMenuDao().commit();
		} catch (Exception e) {
			bmMenuDao().rollback();
			log.error("crudMenuTree() error:" + e.getMessage());
		} finally {
			bmMenuDao().closeConnection();
		}
	}
	
	/**
	 * get menu tree json data
	 * 
	 * @param menuPid
	 * @return
	 */
	public String getChildTreeJsonNodes(String menuPid) {
		StringBuffer menuJsonStrBuff= new StringBuffer(JsonUtil.EMPTY_JSON_ARR_STR);
		List bmMenuList =  bmMenuDao().getBmMenusByPid(menuPid);
		for (int i = 0; i < bmMenuList.size(); i++) {
			BmMenu bmMenu = (BmMenu)bmMenuList.get(i);
			String data = "menuId:id,menuPid:pid,menuCd:cd,menuName:name,url:url,displayNo:order,displayLevel:level";
			StringBuffer jsonObjectStrBuffer = bmMenu.toJsonStrBuffer(data);
			JsonUtil.addJsonObjectItem(jsonObjectStrBuffer, "\"isParent\":" + !bmMenu.isEnd());
			JsonUtil.addJsonObject(menuJsonStrBuff, jsonObjectStrBuffer);
		}
		log.info("get menu tree nodes json data success: " + menuJsonStrBuff.toString());
		return menuJsonStrBuff.toString();
	}
	
	/**
	 * get menu tree json data
	 * 
	 * @param menuPid
	 * @return
	 */
	public String getChildTreeJsonNodes(String menuPid,String menuPids) {
		StringBuffer menuJsonStrBuff= new StringBuffer(JsonUtil.EMPTY_JSON_ARR_STR);
		List bmMenuList =  bmMenuDao().getBmMenusByPid(menuPid);
		for (int i = 0; i < bmMenuList.size(); i++) {
			BmMenu bmMenu = (BmMenu)bmMenuList.get(i);
			bmMenu.setMenuPids(menuPids);
			String data = "menuId:id,menuPid:pid,menuCd:cd,menuName:name,url:url,displayNo:order,displayLevel:level,menuPids:pids";
			StringBuffer jsonObjectStrBuffer = bmMenu.toJsonStrBuffer(data);
			JsonUtil.addJsonObjectItem(jsonObjectStrBuffer, "\"isParent\":" + !bmMenu.isEnd());
			JsonUtil.addJsonObject(menuJsonStrBuff, jsonObjectStrBuffer);
		}
		log.info("get menu tree nodes json data success: " + menuJsonStrBuff.toString());
		return menuJsonStrBuff.toString();
	}
	
	/**
	 * get html menu, such as: <ul><li class="top"><a href="#nogo1" class="top_link"><span>Home</span></a></li><ul>
	 * 
	 * @param menuPid
	 * @return
	 */
	public Map getHtmlMenuAndContentMap(String menuPid, String basePath) {
		List bmMenuList1 =  bmMenuDao().getBmMenusByPid(menuPid);
		StringBuffer htmlMenuSB = new StringBuffer(); 
		StringBuffer htmlHomeContentSB = new StringBuffer(); 
		htmlMenuSB.append("<ul id='nav'>");
		Map htmlMap = new HashMap();
		for (int i = 0; i < bmMenuList1.size(); i++) {
			BmMenu bmMenu1 = (BmMenu)bmMenuList1.get(i);
			htmlMenuSB.append("<li class='top'><a href='").append(basePath).append(bmMenu1.getUrl()).append("' class='top_link'><span>");
			htmlMenuSB.append(bmMenu1.getMenuName());
			htmlMenuSB.append("</span></a>");
			if (!bmMenu1.isEnd()) {
				List bmMenuList2 =  bmMenuDao().getBmMenusByPid(bmMenu1.getMenuId());
				//start home content html
				if (Constants.HOME_MENU_ID.equals(bmMenu1.getMenuId())) {
					for (int k = 0; k < bmMenuList2.size(); k++) {
						BmMenu bmMenu2 = (BmMenu) bmMenuList2.get(k);
						htmlHomeContentSB.append("<div class='articleTitlesFrame ui-widget-content ");
						if (k%2==0) {
							htmlHomeContentSB.append(" mr1'>");
						} else {
							htmlHomeContentSB.append(" ml1' style='float:right'>");
						}
						htmlHomeContentSB.append("<div class='articleCategory' style='width:100%'>")
						  .append("<div class='articleCategoryName'>").append(bmMenu2.getMenuName()).append("</div>")
						     .append("<img class='icon' onClick='hide()' src='").append(basePath)
						       .append("/resource/style/base/image/icon/wclose.gif'/>")
						  .append("</div>")
						.append("<div class='articleTitle'><table style='width:100%;*width:99.9%;border-collapse: collapse;'>");
						List bmMenuList3 =  bmMenuDao().getBmMenusByPid(bmMenu2.getMenuId());
						int size = bmMenuList3.size();
						for (int j = 0; j < size; j++) {
							BmMenu bmMenu3 = (BmMenu) bmMenuList3.get(j);
							if (j%4 == 0){
								if (j!=0) htmlHomeContentSB.append("</tr>");
								htmlHomeContentSB.append("<tr>");
							}
							if (j==size-1 && j%4 != 3) {
								htmlHomeContentSB.append("<td style='text-align:left;height:25px' colspan=" + (4-(j%4)) + ">");
							} else {
								htmlHomeContentSB.append("<td style='text-align:left;width:25%;height:25px'>");
							}
							htmlHomeContentSB.append("<a target='_blank' href='").append(bmMenu3.getUrl()).append("'>")
											.append(bmMenu3.getMenuName()).append("</a></td>");
						}
						htmlHomeContentSB.append("</tr>");
						htmlHomeContentSB.append("</table></div></div>");
					}
					continue;
				}
				//end get home content html
				if (bmMenuList2==null || bmMenuList2.isEmpty()) {
					htmlMenuSB.append("</li>");
					continue;
				}
				htmlMenuSB.append("<ul class='sub'>");
				for (int j = 0; j < bmMenuList2.size(); j++) {
					BmMenu bmMenu2 = (BmMenu)bmMenuList2.get(j);
					htmlMenuSB.append("<li><a href='").append(basePath).append(bmMenu2.getUrl()).append("' class='fly'>");
					htmlMenuSB.append(bmMenu2.getMenuName());
					htmlMenuSB.append("</a>");
					if (!bmMenu2.isEnd()) {
						List bmMenuList3=  bmMenuDao().getBmMenusByPid(bmMenu2.getMenuId());
						if (bmMenuList3==null || bmMenuList3.isEmpty()) {
							htmlMenuSB.replace(htmlMenuSB.lastIndexOf("class='fly'")-1, htmlMenuSB.lastIndexOf("class='fly'") + 11, "");
							htmlMenuSB.append("</li>");
							continue;
						}
						htmlMenuSB.append("<ul>");
						for (int k = 0; k < bmMenuList3.size(); k++) {
							BmMenu bmMenu3 = (BmMenu)bmMenuList3.get(k);
							htmlMenuSB.append("<li><a href='").append(basePath).append(bmMenu3.getUrl()).append("' class='fly'>");
							htmlMenuSB.append(bmMenu3.getMenuName());
							htmlMenuSB.append("</a>");
							if (!bmMenu3.isEnd()) {
								List bmMenuList4=  bmMenuDao().getBmMenusByPid(bmMenu3.getMenuId());
								if (bmMenuList4==null || bmMenuList4.isEmpty()) {
									htmlMenuSB.replace(htmlMenuSB.lastIndexOf("class='fly'")-1, htmlMenuSB.lastIndexOf("class='fly'") + 11, "");
									htmlMenuSB.append("</li>");
									continue;
								}
							} else {
								htmlMenuSB.replace(htmlMenuSB.lastIndexOf("class='fly'")-1, htmlMenuSB.lastIndexOf("class='fly'") + 11, "");
								htmlMenuSB.append("</li>");
							}
							htmlMenuSB.append("</li>");
						}
						htmlMenuSB.append("</ul>");
					} else {
						htmlMenuSB.replace(htmlMenuSB.lastIndexOf("class='fly'")-1, htmlMenuSB.lastIndexOf("class='fly'") + 11, "");
					}
					htmlMenuSB.append("</li>");
				}
				htmlMenuSB.append("</ul>");
			}
			htmlMenuSB.append("</li>");
		}
		htmlMenuSB.append("</ul>");
		log.info("get menu html Menu data success: " + htmlMenuSB.toString());
		log.info("get coment html Menu data success: " + htmlHomeContentSB.toString());
		htmlMap.put("htmlMenu", htmlMenuSB.toString());
		htmlMap.put("htmlContent", htmlHomeContentSB.toString());
		return htmlMap;
	}
	
	/**
	 * <h3><a href="#">Section 1</a></h3>
	 * <div><p>Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. Integer ut neque.</p></div>
	 * <h3><a href="#">Section 2</a></h3>
	 * <div><p> Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. </p></div>
	 * 
	 * @return
	 */
	public String getAccordionHtml(String pid, String basePath) {
		StringBuffer htmlEncyclopediaSB = new StringBuffer();
		List bmMenuList1 =  bmMenuDao().getBmMenusByPid(pid);
		for (Iterator iterator1 = bmMenuList1.iterator(); iterator1.hasNext();) {
			BmMenu bmMenu1 = (BmMenu) iterator1.next();
			htmlEncyclopediaSB.append("<h3><a href='#'>").append(bmMenu1.getMenuName()).append("</a></h3>");
			List bmMenuList2 =  bmMenuDao().getBmMenusByPid(bmMenu1.getMenuId());
			if (bmMenuList2!=null && !bmMenuList2.isEmpty()) {
				htmlEncyclopediaSB.append("<div>");
				for (Iterator iterator2 = bmMenuList2.iterator(); iterator2.hasNext();) {
					BmMenu bmMenu2 = (BmMenu) iterator2.next();
					//word start
					if (Constants.WORD_MENU_ID.equals(bmMenu1.getMenuId()) || Constants.PHRASE_WORD_MENU_ID.equals(bmMenu1.getMenuId())) {
						htmlEncyclopediaSB.append("<p><a target='iframeContent' href='").append(basePath)
						.append("/servlet/cmVocabulary?action=toThumbnail&vocabularyCatagoryIds=")
						.append(bmMenu2.getMenuId()).append("&navMenuIdsWithComma=").append(pid).append(",").append(bmMenu1.getMenuId()).append(",")
						.append(bmMenu2.getMenuId())
						.append("'>").append(bmMenu2.getMenuName()).append("</a></p>");	
					//word end
					} else if(StringUtil.hasText(bmMenu2.getUrl())) {
						htmlEncyclopediaSB.append("<p><a target='iframeContent' href='").append(basePath)
						.append(bmMenu2.getUrl()).append("'>").append(bmMenu2.getMenuName()).append("</a></p>");
					} else {
						htmlEncyclopediaSB.append("<p><a target='iframeContent' href='").append(basePath)
						.append("/servlet/cmArticle?action=toSimpleList&articleCategoryIds=")
						.append(bmMenu2.getMenuId()).append("'>").append(bmMenu2.getMenuName()).append("</a></p>");
					}
					
				}
				htmlEncyclopediaSB.append("</div>");
			}
		}
		log.info("get accordion html Menu data success: " + htmlEncyclopediaSB.toString());
		return htmlEncyclopediaSB.toString();
	}
	
	/**
	 * get BmMenu records by pid
	 * 
	 * @param pid
	 * @return
	 */
	public List getBmMenusByPid(String menuPid) {
		return bmMenuDao().getBmMenusByPid(menuPid);
	}
	
	/**
	 * <p>get the first level menu html</p>
	 * <pre>
	 * <div id='ddbnav'><ul>
	 * </pre>
	 * 
	 * @param basePath
	 * @return
	 */
	public String getOneLevelMenuHtml(String menuPid, String basePath) {
		List bmMenuList1 =  bmMenuDao().getBmMenusByPid(menuPid);
		StringBuffer htmlMenuSB = new StringBuffer(); 
		htmlMenuSB.append("<ul class='kwicks horizontal'>");//<div id='ddbnav'><ul>
		for (int i = 0; i < bmMenuList1.size(); i++) {
			BmMenu bmMenu1 = (BmMenu)bmMenuList1.get(i);
			htmlMenuSB.append("<li id='kwick" + (i+1) + "'><a href='").append(basePath).append(bmMenu1.getUrl()).append("'>");
			htmlMenuSB.append(bmMenu1.getMenuName());
			htmlMenuSB.append("</a></li>");
		}
		htmlMenuSB.append("</ul>");//</ul></div>
		log.info("get menu html Menu data success: " + htmlMenuSB.toString());
		return htmlMenuSB.toString();
	}
	
	public BmMenuDao bmMenuDao() {
		return new BmMenuDao();
	}
}

/**
 //word start
	if (Constants.WORD_MENU_ID.equals(bmMenu1.getMenuId())) {
		htmlEncyclopediaSB.append("<h3><a href='#'>").append(bmMenu1.getMenuName()).append("</a></h3>");
		List bmMenuList2 =  bmMenuDao().getBmMenuByPid(bmMenu1.getMenuId());
		htmlEncyclopediaSB.append("<div>");
		for (Iterator iterator2 = bmMenuList2.iterator(); iterator2.hasNext();) {
			BmMenu bmMenu2 = (BmMenu) iterator2.next();
			htmlEncyclopediaSB.append("<p>").append(bmMenu2.getMenuName()).append("</p><ul>");
			List bmMenuList3 =  bmMenuDao().getBmMenuByPid(bmMenu2.getMenuId());
			for (Iterator iterator3 = bmMenuList3.iterator(); iterator3.hasNext();) {
				BmMenu bmMenu3 = (BmMenu) iterator3.next();
				htmlEncyclopediaSB.append("<li>").append(bmMenu3.getMenuName()).append("</li>");
			}
			htmlEncyclopediaSB.append("</ul>");
		}
		htmlEncyclopediaSB.append("</div>");
//word end
 */
