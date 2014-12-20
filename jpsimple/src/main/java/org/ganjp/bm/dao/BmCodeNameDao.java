package org.ganjp.bm.dao;

import java.util.List;
import java.util.Map;

import org.ganjp.core.dao.BaseDAO;
import org.ganjp.bm.dao.BmCodeNameDao;
import org.ganjp.bm.model.BmCodeName;
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
public class BmCodeNameDao extends BaseDAO {
	/**
	 * add BmCodeName
	 * 
	 * @param columnNames new String[]{"config_key","config_value"}
	 * @param paramObjects Object[]{'isStartupH2','true'}
	 */
	public void addBmCodeName(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildAddFullSql(columnNames, "bm_code_name", "code_name_id");
		Object[] fullParamObjects = SqlUtil.buildAddFullParamValues(paramObjects,!columnNames[0].equalsIgnoreCase("menu_id"));
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, fullParamObjects));
		this.update(fullSqlWithParam, fullParamObjects, true);
		log.info("add BmCodeName record success");
	}
	
	/**
	 * edit BmCodeName
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 */
	public void editBmCodeName(final String[] columnNames, final Object[] paramObjects) {
		String fullSqlWithParam = SqlUtil.buildEditFullSql(columnNames, "bm_code_name");
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, paramObjects));
		log.debug(SqlUtil.getFullSql(fullSqlWithParam, paramObjects));
		this.update(fullSqlWithParam, paramObjects, true);
		log.info("add BmCodeName record success");
	}
	
	/**
	 * delete BmCodeName records by primary key
	 * 
	 * @param codeNameId
	 */
	public void deleteBmCodeNameByPk(String codeNameId) {
		String sqlWithParam = "delete from bm_code_name WHERE code_name_id=?";
		Object[] paramObjects = new Object[] {codeNameId};
		this.update(sqlWithParam, paramObjects, true);
		log.info("delete BmCodeName record success");
	}
	
	/**
	 * batch delete BmCodeName records by primary keys
	 * 
	 * @param pks
	 */
	public void deleteBmCodeNamesByPks(String pks) {
		if (StringUtil.isNotBlank(pks)) {
			String deleteSql = "delete from bm_code_name WHERE code_name_id=?";
			this.batchDeleteByIdArr(deleteSql, pks.split(","), true);
			log.info("delete BmCodeName records success");
		}
	}
	
	/**
	 * get BmCodeName by by primary key
	 * 
	 * @param pk
	 * @return
	 */
	public BmCodeName getBmCodeNameByPk(final String pk) {
		String sqlWithParam = "SELECT * FROM bm_code_name WHERE code_name_id=?";
		Object object = (Object)this.getObject(sqlWithParam, pk, BmCodeName.class, true);
		if (object!=null) {
			BmCodeName bmCodeName = (BmCodeName) object;
			log.debug("select BmCodeName by pk = " + pk);
			return bmCodeName;
		} else {
			log.error("select BmCodeName by pk = " + pk + " is null" );
			return null;
		}
	}
	
	/**
	 * get all BmCodeName records where data_state = 0 ORDER BY modify_timestamp DESC
	 * 
	 * @return
	 */
	public List getAll() {
		String sql = "SELECT * FROM bm_code_name WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
		List bmCodeNames = this.getObjectList(sql, BmCodeName.class, false);
		log.info("select All BmCodeName record success");
		return bmCodeNames;
	}
	
	/**
	 * get all BmCodeName record Page where data_state = 0 ORDER BY modify_timestamp DESC
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
			String sql = "SELECT * FROM bm_code_name WHERE data_state = " + Constants.DATA_STATE_NORMAL + " ORDER BY modify_timestamp DESC";
			page = this.getObjectPage(sql, BmCodeName.class, page, true);
			log.debug("select BmCodeName page records success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
	
	/**
	 * get all BmCodeName record Page where data_state = 0 with search condition ORDER BY modify_timestamp DESC
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
			String sql = "SELECT * FROM bm_code_name WHERE data_state = " + Constants.DATA_STATE_NORMAL + 
					searchSqlAndValuesMap.get(SqlUtil.SQL_SUFFIX).toString() + " ORDER BY modify_timestamp DESC";
			Object[] objectValues = (Object[])searchSqlAndValuesMap.get(SqlUtil.VALUES);
			page = this.getObjectPage(sql, objectValues, BmCodeName.class, page, true);
			log.debug("select BmCodeName page records with search condition success");
		} catch(Exception ex) {
			log.error("getAllPage error:" + ex.getMessage());
		}
		return page;
	}
}
/**
  * codeNameId - code_name_id
 * codeNamePid - code_name_pid
 * code - code
 * name - name
 * displayNo - display_no
 * displayLevel - display_level
 * endFlag - end_flag
 * createDateTime - create_date_time
 * modifyTimestamp - modify_timestamp
 * dataState - data_state
 */