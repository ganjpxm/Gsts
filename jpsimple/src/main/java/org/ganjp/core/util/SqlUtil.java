package org.ganjp.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ganjp.core.Configuration;
import org.ganjp.core.Constants;
import org.ganjp.core.db.id.UUIDHexGenerator;
import org.ganjp.core.exception.JpException;
import org.ganjp.core.web.util.QueryFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SqlUtil {
	private static Logger log = LoggerFactory.getLogger(SqlUtil.class);
	public static final String SQL_SUFFIX = "sqlSuffix";
	public static final String VALUES = "values";
	public static final String PRIMARY_KEY_NAME = "pkName";
	public static final String TABLE_NAME = "tableName";
	public static final String COLUMN_NAMES = "columnNames";
	
	public static Map getSqlAndValuesMap(List queryFilters, String className) {
		if (queryFilters==null || queryFilters.isEmpty()) {
			return null;
		}
		Map map = new HashMap();
		StringBuffer sql = new StringBuffer();
		Object [] objects = new Object[queryFilters.size()];
		for (int i = 0; i < queryFilters.size(); i++) {
			QueryFilter queryFilter = (QueryFilter) queryFilters.get(i);
			String fieldName = queryFilter.getFieldName();
			String columnName = null;
			if(fieldName.indexOf(".")!=-1){
				columnName = Configuration.getColumnName(fieldName.substring(0,fieldName.indexOf(".")),
						fieldName.substring(fieldName.indexOf(".")+1,fieldName.length()));
			} else {
				columnName = Configuration.getColumnName(className, fieldName);
			}
			
			if (StringUtil.isBlank(columnName)) {
				throw new JpException(className + queryFilter.getFieldName() + "is null, please set value in Configuration");
			}
			sql.append(" and ").append(columnName).append(" ").append(queryFilter.getMatchType()).append(" ?");
			objects[i] = QueryFilter.getDealFieldValue(queryFilter);
			map.put(queryFilter.getFieldName(), queryFilter.getFieldValue().toString().replaceAll("%", ""));
		}
		map.put("sqlSuffix", sql.toString());
		map.put("values", objects);
		log.debug("sql : " + sql.toString());
		return map;
	}
	
	/**
	 * columnNames:new String[]{"config_key","config_value"}, tableName:"bm_config", primaryKeyName:"config_id"
	 * return "insert into bm_config (config_id, config_key, config_value, create_date_time, data_state) values(?,?,?,?,?)"
	 * 
	 * columnNames:new String[]{"menu_id, menu_cd","menu_name"}, tableName:"bm_menu", primaryKeyName:"menu_id"
	 * return "insert into bm_config (menu_id, menu_cd, menu_name, create_date_time, data_state) values(?,?,?,?,?)"
	 * 
	 * @param columnNames
	 * @param tableName
	 * @param primaryKeyName
	 * @return
	 */
	public static String buildAddFullSql(final String[] columnNames, final String tableName, final String primaryKeyName) {
		StringBuffer fullSql = new StringBuffer();
		fullSql.append("insert into ").append(tableName).append(" (").append(primaryKeyName);
		int columnSize = columnNames.length;
		boolean columnNamesConstainPk = false;
		for (int i = 0; i < columnSize; i++) {
			String columnName = columnNames[i];
			if (!columnName.equalsIgnoreCase(primaryKeyName)) {
				fullSql.append(", ").append(columnName);
			} else {
				columnNamesConstainPk = true;
			}
		}
		fullSql.append(", create_date_time, data_state) values(?,?");
		if (!columnNamesConstainPk) {
			fullSql.append(",?");
		}
		for (int i = 0; i < columnSize; i++) {
			fullSql.append(",?");
		}
		fullSql.append(")");
		return fullSql.toString();
	}
	
	/**
	 * columnNames:new String[]{"config_key","config_value","config_id"}, tableName:"bm_config"
	 * return: update bm_config set config_key = ?, config_value = ? where config_id = ?
	 * 
	 * @param columnNames
	 * @param tableName
	 * @return
	 */
	public static String buildEditFullSql(final String[] columnNames, final String tableName) {
		StringBuffer fullSql = new StringBuffer();
		fullSql.append("update ").append(tableName).append(" set ");
		int columnSize = columnNames.length;
		for (int i = 0; i < columnSize-1; i++) {
			String columnName = columnNames[i];
			fullSql.append( columnName ).append("= ?");
			if (i < columnSize-2) {
				fullSql.append(",");
			}
		}
		fullSql.append(" where ").append(columnNames[columnSize-1]).append(" = ?");
		return fullSql.toString();
	}
	
	/**
	 * paramObjects:Object[]{'1','2'}
	 * return:Object[]{'1234567890123456789012345679012','1','2', new Date(), '0'}
	 * 
	 * @param paramObjects
	 * @return
	 */
	public static Object[] buildAddFullParamValues(final Object[] paramObjects,final boolean isAddPk) {
		int paramSize = paramObjects.length;
		Object[] fullParamValues = null;
		
		if (isAddPk) {
			fullParamValues = new Object[paramSize+3];
			fullParamValues[0] = UUIDHexGenerator.getUuid();
			for (int i = 1; i <= paramSize; i++) {
				fullParamValues[i] = paramObjects[i-1];
			}
			paramSize++;
		} else {
			fullParamValues = new Object[paramSize+2];
			for (int i = 0; i < paramSize; i++) {
				fullParamValues[i] = paramObjects[i];
			}
		}
		fullParamValues[paramSize]=DateUtil.getNowDateTime();
		fullParamValues[paramSize+1]=Constants.DATA_STATE_NORMAL;
		return fullParamValues;
	}
	
	
	/**
	 * sql:"insert into bm_config (config_key, config_value) values(?,?)"
	 * paramObjects: new Object[]{'isStartupH2','true'}
	 * return: "insert into bm_config (config_key, config_value) values('isStartupH2','true')"
	 * 
	 * @param sql
	 * @param paramObjects
	 * @return
	 */
	public static String getFullSql(String sql, Object[] paramObjects) {
		StringBuffer fullSql = new StringBuffer("");
		String tmpSql = sql;
		for (int i = 0; i < paramObjects.length; i++) {
			if (i<paramObjects.length-1) {
				fullSql.append(tmpSql.substring(0, tmpSql.indexOf("?")));
				tmpSql = tmpSql.substring(tmpSql.indexOf("?")+1);
			} else {
				fullSql.append(tmpSql.substring(1, tmpSql.length()-1));
			}
			fullSql.append("'" + paramObjects[i].toString() + "'");
		}
		return fullSql.toString();
	}
	public static void main(String[] args) {
		
	}
}
