/*
 * $Id: CmVocabularyDao,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.cm.dao;

import java.util.List;
import java.util.Map;

import org.ganjp.core.dao.BaseDAO;
import org.ganjp.cm.dao.CmVocabularyDao;
import org.ganjp.cm.model.CmVocabulary;
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
public class CmVocabularyDao extends BaseDAO {
	/**
	 * add CmVocabulary
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addCmVocabulary(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildAddFullSql(columnNames, "cm_vocabulary", "vocabulary_id");
		Object[] fullParamObjects = SqlUtil.buildAddFullParamValues(paramObjects,!columnNames[0].equalsIgnoreCase("vocabulary_id"));
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, fullParamObjects));
		this.update(fullSqlWithParam, fullParamObjects, true);
		log.info("add CmVocabulary record success");
	}
	
	/**
	 * edit CmVocabulary
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editCmVocabulary(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildEditFullSql(columnNames, "cm_vocabulary");
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, paramObjects));
		this.update(fullSqlWithParam, paramObjects, true);
		log.info("add CmVocabulary record success");
	}
	
	/**
	 * delete CmVocabulary records by primary key
	 * 
	 * @param vocabularyId
	 */
	public void deleteCmVocabularyByPk(String vocabularyId) {
		String sqlWithParam = "delete from cm_vocabulary WHERE vocabulary_id=?";
		Object[] paramObjects = new Object[] {vocabularyId};
		this.update(sqlWithParam, paramObjects, true);
		log.info("delete CmVocabulary record success");
	}
	
	/**
	 * batch delete CmVocabulary records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteCmVocabularysByPks(String pks) {
		if (StringUtil.isNotBlank(pks)) {
			String deleteSql = "delete from cm_vocabulary WHERE vocabulary_id=?";
			this.batchDeleteByIdArr(deleteSql, pks.split(","), true);
			log.info("delete CmVocabulary records success");
		}
	}
	
	/**
	 * get CmVocabulary by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public CmVocabulary getCmVocabularyByPk(final String pk) {
		String sqlWithParam = "SELECT * FROM cm_vocabulary WHERE vocabulary_id=?";
		Object object = (Object)this.getObject(sqlWithParam, pk, CmVocabulary.class, true);
		if (object!=null) {
			CmVocabulary cmVocabulary = (CmVocabulary) object;
			log.debug("select CmVocabulary by pk = " + pk);
			return cmVocabulary;
		} else {
			log.error("select CmVocabulary by pk = " + pk + " is null" );
			return null;
		}
	}
	
	/**
	 * get all CmVocabulary records where data_state = 0 ORDER BY modify_timestamp DESC
	 * 
	 * @return
	 */
	public List getAll() {
		String sql = "SELECT * FROM cm_vocabulary WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
		List cmVocabularys = this.getObjectList(sql, CmVocabulary.class, false);
		log.info("select All CmVocabulary record success");
		return cmVocabularys;
	}
	
	/**
	 * get all CmVocabulary record Page where data_state = 0 ORDER BY modify_timestamp DESC
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
			String sql = "SELECT * FROM cm_vocabulary WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
			page = this.getObjectPage(sql, CmVocabulary.class, page, true);
			log.debug("select CmVocabulary page records success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
	
	/**
	 * get all CmVocabulary record Page where data_state = 0 with search condition ORDER BY modify_timestamp DESC
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
			String sql = "SELECT * FROM cm_vocabulary WHERE data_state = " + Constants.DATA_STATE_NORMAL + 
					searchSqlAndValuesMap.get(SqlUtil.SQL_SUFFIX).toString() + " ORDER BY display_no, modify_timestamp DESC";
			Object[] objectValues = (Object[])searchSqlAndValuesMap.get(SqlUtil.VALUES);
			page = this.getObjectPage(sql, objectValues, CmVocabulary.class, page, true);
			log.debug("select CmVocabulary page records with search condition success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
}
/**
  * vocabularyId - vocabulary_id
 * foreignLanguage - foreign_language
 * phonogram - phonogram
 * chinese - chinese
 * propertyCatagoryIds - property_catagory_ids
 * vocabularyCatagoryIds - vocabulary_catagory_ids
 * levelId - level_id
 * recommendState - recommend_state
 * displayNo - display_no
 * description - description
 * operatorId - operator_id
 * operatorName - operator_name
 * createDateTime - create_date_time
 * modifyTimestamp - modify_timestamp
 * dataState - data_state
 */