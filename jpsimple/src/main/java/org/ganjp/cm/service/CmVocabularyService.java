/*
 * $Id: CmVocabularyService.java,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.cm.service;

import org.ganjp.cm.model.CmVocabulary;
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
public interface CmVocabularyService {
	/**
	 * add CmVocabulary
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addCmVocabulary(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * edit CmVocabulary
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editCmVocabulary(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * delete CmVocabulary records by primary key
	 * 
	 * @param vocabularyId
	 */
	public void deleteCmVocabularyByPk(String vocabularyId);
	
	/**
	 * batch delete CmVocabulary records by primary keys
	 * 
	 * @param pks
	 */
	public CmVocabulary getCmVocabularyByPk(final String pk);
	
	/**
	 * get CmVocabulary by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public void deleteCmVocabularysByPks(String pks);
	
	/**
	 * get all CmVocabulary records 
	 * 
	 * @return
	 */
	public List getAll();
	
	/**
	 * get all CmVocabulary record Page 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, final Map searchSqlAndValuesMap);
}