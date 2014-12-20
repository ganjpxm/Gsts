/*
 * $Id: CmArticleService.java,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.cm.service;

import org.ganjp.cm.model.CmArticle;
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
public interface CmArticleService {
	/**
	 * add CmArticle
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addCmArticle(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * edit CmArticle
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editCmArticle(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * delete CmArticle records by primary key
	 * 
	 * @param articleId
	 */
	public void deleteCmArticleByPk(String articleId);
	
	/**
	 * batch delete CmArticle records by primary keys
	 * 
	 * @param pks
	 */
	public CmArticle getCmArticleByPk(final String pk);
	
	/**
	 * get CmArticle by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public void deleteCmArticlesByPks(String pks);
	
	/**
	 * get all CmArticle records 
	 * 
	 * @return
	 */
	public List getAll();
	
	/**
	 * get all CmArticle record Page 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, final Map searchSqlAndValuesMap);
}