/*
 * $Id: BmCodeNameService.java,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.bm.service;

import org.ganjp.bm.model.BmCodeName;
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
public interface BmCodeNameService {
	/**
	 * add BmCodeName
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addBmCodeName(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * edit BmCodeName
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editBmCodeName(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * delete BmCodeName records by primary key
	 * 
	 * @param codeNameId
	 */
	public void deleteBmCodeNameByPk(String codeNameId);
	
	/**
	 * batch delete BmCodeName records by primary keys
	 * 
	 * @param pks
	 */
	public BmCodeName getBmCodeNameByPk(final String pk);
	
	/**
	 * get BmCodeName by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public void deleteBmCodeNamesByPks(String pks);
	
	/**
	 * get all BmCodeName records 
	 * 
	 * @return
	 */
	public List getAll();
	
	/**
	 * get all BmCodeName record Page 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, final Map searchSqlAndValuesMap);
}