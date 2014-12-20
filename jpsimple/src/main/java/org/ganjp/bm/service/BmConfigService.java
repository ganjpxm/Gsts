package org.ganjp.bm.service;

import java.util.List;
import java.util.Map;

import org.ganjp.bm.model.BmConfig;
import org.ganjp.core.db.base.Page;

/**
 * {annotation}
 * 
 * @author ganjp
 * @version 1.0
 * @since 1.0
 */
public interface BmConfigService {
	/**
	 * add BmConfig
	 * 
	 * @param bmConfig
	 */
	public void addBmConfig(BmConfig bmConfig);
	
	/**
	 * add BmConfig
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addBmConfig(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * edit BmConfig
	 * 
	 * @param bmConfig
	 */
	public void editBmConfig(BmConfig bmConfig);
	
	/**
	 * edit BmConfig
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editBmConfig(final String[] columnNames, final Object[] paramObjects);
	
	/**
	 * delete BmConfig records by primary key
	 * 
	 * @param configId
	 */
	public void deleteBmConfigByPk(String configId);
	
	/**
	 * batch delete BmConfig records by primary keys
	 * 
	 * @param pks
	 */
	public BmConfig getBmConfigByConfigByPk(final String pk);
	
	/**
	 * get BmConfig by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public void deleteBmConfigsByPks(String pks);
	
	/**
	 * get all BmConfig records 
	 * 
	 * @return
	 */
	public List getAll();
	
	/**
	 * get all BmConfig record Page 
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page getAllPage(final int pageNo, final int pageSize, final Map searchSqlAndValuesMap);
}