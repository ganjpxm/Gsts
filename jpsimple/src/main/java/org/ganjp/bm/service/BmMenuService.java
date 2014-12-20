/*
 * $Id: BmMenuService.java,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.bm.service;

import org.ganjp.bm.model.BmMenu;
import org.ganjp.core.db.base.Page;

import java.util.List;
import java.util.Map;

/**
 * {annotation}
 * 
 * @author ganjp
 * @version 1.0
 * @since 1.0
 */
public interface BmMenuService {
	/**
	 * add BmMenu
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addBmMenu(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * edit BmMenu
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editBmMenu(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * delete BmMenu records by primary key
	 * 
	 * @param menuId
	 */
	public void deleteBmMenuByPk(String menuId);
	
	/**
	 * batch delete BmMenu records by primary keys
	 * 
	 * @param pks
	 */
	public BmMenu getBmMenuByPk(final String pk);
	
	/**
	 * get BmMenu by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public void deleteBmMenusByPks(String pks);
	
	/**
	 * get all BmMenu records 
	 * 
	 * @return
	 */
	public List getAll();
	
	/**
	 * get all BmMenu record Page 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, final Map searchSqlAndValuesMap);
	
	/**
	 * get menu tree json data
	 * 
	 * @param menuPid
	 * @return
	 */
	public String getChildTreeJsonNodes(String menuPid);
	
	/**
	 * 
	 * @param newRecordArrStr []
	 * @param modifyRecordArrStr
	 * @param delNodeIds
	 */
	public void crudMenuTree(String newRecordArrStr, String modifyRecordArrStr, String delNodeIds);
	
	/**
	 * get menu tree json data
	 * 
	 * @param menuPid
	 * @return
	 */
	public String getChildTreeJsonNodes(String menuPid,String menuPids);
	
	/**
	 * get html menu, such as: <ul><li class="top"><a href="#nogo1" class="top_link"><span>Home</span></a></li><ul>
	 * 
	 * @param menuPid
	 * @return
	 */
	public Map getHtmlMenuAndContentMap(String menuPid,String basePath);
	
	/**
	 * <h3><a href="#">Section 1</a></h3>
	 * <div><p>Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. Integer ut neque.</p></div>
	 * <h3><a href="#">Section 2</a></h3>
	 * <div><p> Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. </p></div>
	 * 
	 * @return
	 */
	public String getAccordionHtml(String pid, String basePath);
	
	/**
	 * get BmMenu records by pid
	 * 
	 * @param pid
	 * @return
	 */
	public List getBmMenusByPid(String menuPid);
	
	/**
	 * <p>get the first level menu html and Content</p>
	 * 
	 * @param basePath
	 * @return
	 */
	public String getOneLevelMenuHtml(String menuPid, String basePath);
}