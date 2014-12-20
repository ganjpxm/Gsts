/*
 * $Id: CmArticleDao,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.cm.dao;

import java.util.List;
import java.util.Map;

import org.ganjp.core.dao.BaseDAO;
import org.ganjp.cm.dao.CmArticleDao;
import org.ganjp.cm.model.CmArticle;
import org.ganjp.core.Constants;
import org.ganjp.core.db.base.Page;
import org.ganjp.core.util.SqlUtil;
import org.ganjp.core.util.StringUtil;
/**
 * {annotation}
 * 
 * @author ganjp
 * @version 1.0
 * @since 1.0
 */
public class CmArticleDao extends BaseDAO {
	/**
	 * add CmArticle
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addCmArticle(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildAddFullSql(columnNames, "cm_article", "article_id");
		Object[] fullParamObjects = SqlUtil.buildAddFullParamValues(paramObjects,!columnNames[0].equalsIgnoreCase("article_id"));
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, fullParamObjects));
		this.update(fullSqlWithParam, fullParamObjects, true);
		log.info("add CmArticle record success");
	}
	
	/**
	 * edit CmArticle
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editCmArticle(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildEditFullSql(columnNames, "cm_article");
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, paramObjects));
		this.update(fullSqlWithParam, paramObjects, true);
		log.info("add CmArticle record success");
	}
	
	/**
	 * delete CmArticle records by primary key
	 * 
	 * @param articleId
	 */
	public void deleteCmArticleByPk(String articleId) {
		String sqlWithParam = "delete from cm_article WHERE article_id=?";
		Object[] paramObjects = new Object[] {articleId};
		this.update(sqlWithParam, paramObjects, true);
		log.info("delete CmArticle record success");
	}
	
	/**
	 * batch delete CmArticle records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteCmArticlesByPks(String pks) {
		if (StringUtil.isNotBlank(pks)) {
			String deleteSql = "delete from cm_article WHERE article_id=?";
			this.batchDeleteByIdArr(deleteSql, pks.split(","), true);
			log.info("delete CmArticle records success");
		}
	}
	
	/**
	 * get CmArticle by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public CmArticle getCmArticleByPk(final String pk) {
		String sqlWithParam = "SELECT * FROM cm_article WHERE article_id=?";
		Object object = (Object)this.getObject(sqlWithParam, pk, CmArticle.class, true);
		if (object!=null) {
			CmArticle cmArticle = (CmArticle) object;
			log.debug("select CmArticle by pk = " + pk);
			return cmArticle;
		} else {
			log.error("select CmArticle by pk = " + pk + " is null" );
			return null;
		}
	}
	
	/**
	 * get all CmArticle records where data_state = 0 ORDER BY modify_timestamp DESC
	 * 
	 * @return
	 */
	public List getAll() {
		String sql = "SELECT * FROM cm_article WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
		List cmArticles = this.getObjectList(sql, CmArticle.class, false);
		log.info("select All CmArticle record success");
		return cmArticles;
	}
	
	/**
	 * get all CmArticle record Page where data_state = 0 ORDER BY modify_timestamp DESC
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize) {
		Page page = new Page();
		try {
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			String sql = "SELECT * FROM cm_article WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
			page = this.getObjectPage(sql, CmArticle.class, page, true);
			log.debug("select CmArticle page records success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
	
	/**
	 * get all CmArticle record Page where data_state = 0 with search condition ORDER BY modify_timestamp DESC
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param searchSqlAndValuesMap
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, Map searchSqlAndValuesMap) {
		if (searchSqlAndValuesMap == null) {
			return this.getAllPage(pageNo, pageSize);
		}
		Page page = new Page();
		try {
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			String sql = "SELECT * FROM cm_article WHERE data_state = " + Constants.DATA_STATE_NORMAL + 
					searchSqlAndValuesMap.get(SqlUtil.SQL_SUFFIX).toString() + " ORDER BY display_no, modify_timestamp DESC";
			Object[] objectValues = (Object[])searchSqlAndValuesMap.get(SqlUtil.VALUES);
			page = this.getObjectPage(sql, objectValues, CmArticle.class, page, true);
			log.debug("select CmArticle page records with search condition success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
}
/**
  * articleId - article_id
 * articleCategoryIds - article_category_ids
 * articleCd - article_cd
 * articleTitle - article_title
 * articleContent - article_content
 * commentAuthorId - comment_author_id
 * commentAuthorName - comment_author_name
 * articleRecommendLevelId - article_recommend_level_id
 * displayNo - display_no
 * remark - remark
 * auditFlag - audit_flag
 * operatorId - operator_id
 * operatorName - operator_name
 * createDateTime - create_date_time
 * modifyTimestamp - modify_timestamp
 * dataState - data_state
 */