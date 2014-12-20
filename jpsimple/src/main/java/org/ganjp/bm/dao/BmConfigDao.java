package org.ganjp.bm.dao;

import java.util.List;
import java.util.Map;

import org.ganjp.bm.model.BmConfig;
import org.ganjp.core.Constants;
import org.ganjp.core.dao.BaseDAO;
import org.ganjp.core.db.base.Page;
import org.ganjp.core.db.h2.H2Handler;
import org.ganjp.core.db.id.UUIDHexGenerator;
import org.ganjp.core.util.DateUtil;
import org.ganjp.core.util.SqlUtil;
import org.ganjp.core.util.StringUtil;
/**
 * {annotation}
 * 
 * @author ganjp
 * @version 1.0
 * @since 1.0
 */
public class BmConfigDao extends BaseDAO {
	
	/**
	 * add BmConfig
	 * 
	 * @param bmConfig
	 */
	public void addBmConfig(BmConfig bmConfig) {
		String sqlWithParam = "insert into bm_config (config_id, config_key, config_key_display_name, config_value, " +
				"description, create_date_time, data_state) values(?,?,?,?,?,?,?)";
		Object[] paramObjects = new Object[] { UUIDHexGenerator.getUuid(), bmConfig.getConfigKey(), bmConfig.getConfigKeyDisplayName(),
				bmConfig.getConfigValue(), bmConfig.getDescription(), DateUtil.getNowDateTime(), Constants.DATA_STATE_NORMAL};
		this.update(sqlWithParam, paramObjects, true);
		log.info("add BmConfig record success");
	}
	
	/**
	 * add BmConfig
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addBmConfig(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildAddFullSql(columnNames, "bm_config", "config_id");
		Object[] fullParamObjects = SqlUtil.buildAddFullParamValues(paramObjects,!columnNames[0].equalsIgnoreCase("config_id"));
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, fullParamObjects));
		this.update(fullSqlWithParam, fullParamObjects, true);
		log.info("add BmConfig record success");
	}
	
	/**
	 * edit BmConfig
	 * 
	 * @param bmConfig
	 */
	public void editBmConfig(BmConfig bmConfig) {
		String sqlWithParam = "update bm_config set config_key=?, config_key_display_name=?, config_value=?, " +
				"description=? WHERE config_id=?";
		Object[] paramObjects = new Object[] {bmConfig.getConfigKey(), bmConfig.getConfigKeyDisplayName(),
				bmConfig.getConfigValue(), bmConfig.getDescription(), bmConfig.getConfigId()};
		log.debug(SqlUtil.getFullSql(sqlWithParam, paramObjects));
		this.update(sqlWithParam, paramObjects, true);
		log.info("add BmConfig record success");
	}
	
	/**
	 * edit BmConfig
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editBmConfig(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildEditFullSql(columnNames, "bm_config");
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, paramObjects));
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, paramObjects));
		this.update(fullSqlWithParam, paramObjects, true);
		log.info("add BmConfig record success");
	}
	
	/**
	 * delete BmConfig records by primary key
	 * 
	 * @param configId
	 */
	public void deleteBmConfigByPk(String configId) {
		String sqlWithParam = "delete from bm_config WHERE config_id=?";
		Object[] paramObjects = new Object[] {configId};
		this.update(sqlWithParam, paramObjects, true);
		log.info("delete BmConfig record success");
	}
	
	/**
	 * batch delete BmConfig records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteBmConfigsByPks(String pks) {
		if (StringUtil.isNotBlank(pks)) {
			String deleteSql = "delete from bm_config WHERE config_id=?";
			this.batchDeleteByIdArr(deleteSql, pks.split(","), true);
			log.info("delete BmConfig records success");
		}
	}
	
	/**
	 * get BmConfig by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public BmConfig getBmConfigByConfigByPk(final String pk) {
		String sqlWithParam = "SELECT * FROM bm_config WHERE config_id=?";
		Object object = (Object)this.getObject(sqlWithParam, pk, BmConfig.class, true);
		if (object!=null) {
			BmConfig bmConfig = (BmConfig) object;
			log.debug("select BmConfig by pk = " + pk);
			return bmConfig;
		} else {
			log.error("select BmConfig by pk = " + pk + " is null" );
			return null;
		}
	}
	
	/**
	 * get all BmConfig records where data_state = 0 ORDER BY modify_timestamp DESC
	 * 
	 * @return
	 */
	public List getAll() {
		String sql = "SELECT * FROM bm_config WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
		List bmConfigs = this.getObjectList(sql, BmConfig.class, false);
		log.info("select All BmConfig record success");
		return bmConfigs;
	}
	
	/**
	 * get all BmConfig record Page where data_state = 0 ORDER BY modify_timestamp DESC
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
			String sql = "SELECT * FROM bm_config WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
			page = this.getObjectPage(sql, BmConfig.class, page, true);
			log.debug("select BmConfig page records success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
	
	/**
	 * get all BmConfig record Page where data_state = 0 with search condition ORDER BY modify_timestamp DESC
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
			String sql = "SELECT * FROM bm_config WHERE data_state = " + Constants.DATA_STATE_NORMAL + 
					searchSqlAndValuesMap.get(SqlUtil.SQL_SUFFIX).toString() + " ORDER BY modify_timestamp DESC";
			Object[] objectValues = (Object[])searchSqlAndValuesMap.get(SqlUtil.VALUES);
			page = this.getObjectPage(sql, objectValues, BmConfig.class, page, true);
			log.debug("select BmConfig page records with search condition success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
	
	public static void main(String[] args) throws Exception {
		//启动H2数据库
		H2Handler.startH2();
		BmConfigDao bmConfigDao = new BmConfigDao();
		BmConfig bmConfig = new BmConfig();
		bmConfig.setConfigKey("isStartupH2");
		bmConfig.setConfigKeyDisplayName("是否启动H2");
		bmConfig.setConfigValue("true");
		bmConfigDao.addBmConfig(bmConfig);
	}
}