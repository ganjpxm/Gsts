
package org.ganjp.core.web.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.ConvertUtils;
import org.ganjp.core.Configuration;
import org.ganjp.core.util.Assert;
import org.ganjp.core.util.StringUtil;

public class QueryFilter implements Serializable {

	private static final long serialVersionUID = 6838975339936792774L;
	private boolean isMultiValue = false;
	
	/** 多个属性间OR关系的分隔符. */
	public static final String OR_SEPARATOR = "_OR_";

	/** 属性数据类型. */
	static final Map fieldTypes = new HashMap();
	
	public static final String C = "C";
	public static final String I = "I";
	public static final String S = "S";
	public static final String L = "L";
	public static final String F = "F";
	public static final String N = "N";
	public static final String D = "D";
	public static final String B = "B";
	public static final String K = "K";
	public static final String T = "T";
	public static final String Y = "Y";
	public static final String H = "H";
	public static final String E = "E";
	public static final String O = "O";
	public static final String J = "J";
	
	static {
		fieldTypes.put(C, Character.class);
		fieldTypes.put(I, Integer.class);
		fieldTypes.put(S, String.class);
		fieldTypes.put(L, Long.class);
		fieldTypes.put(F, Float.class);
		fieldTypes.put(D, Date.class);
		fieldTypes.put(B, Boolean.class);
		fieldTypes.put(T, Timestamp.class);
		
		fieldTypes.put(N, Double.class);
		fieldTypes.put(K, BigDecimal.class);
		fieldTypes.put(Y, Byte.class);
		fieldTypes.put(H, Short.class);
		fieldTypes.put(E, Blob.class);
		fieldTypes.put(O, Clob.class);
		fieldTypes.put(J, Time.class);
	}

	/** 属性比较类型. */
	public static class MatchType {
		public static final String EQ = "=";
		public static final String LIKE = "like";
		public static final String LT = "<";
		public static final String GT = ">";
		public static final String LE = "<=";
		public static final String GE = ">=";
		public static final String UNEQ = "!=";

		static final Map matchType = new HashMap();
		static {
			matchType.put("EQ", "=");
			matchType.put("LIKE", "like");
			matchType.put("LT", "<");
			matchType.put("GT", ">");
			matchType.put("LE", "<=");
			matchType.put("GE", ">=");
			matchType.put("UNEQ", "!=");
		}

		public static String getValue(String type) {
			return (String) matchType.get(type);
		}

		public static String getKey(String value) {
			String key = null;
			if (matchType.containsValue(value)) {
				Iterator it = matchType.keySet().iterator();
				while (it.hasNext()) {
					String k = (String) it.next();
					if (matchType.get(k).equals(value)) {
						key = k;
						break;
					}
				}
			}
			return key;
		}
	}

	private String[] fieldNames = null;
	private Object fieldValue = null;
	private Class fieldType = null;
	private Object matchType = null;

	/**
	 * 根据属性数据类型获得相应的java类型，比如 I 对应 Integer.class
	 * @param key
	 * @return
	 */
	public static String getPropertyTypeValue(String key) {
		return (String) fieldTypes.get(key);
	}
	
	/**
	 * 根据java类型获得相应的属性数据类型，比如 Integer.class 对应 I
	 * @param value
	 * @return
	 */
	public static String getPropertyTypeKey(Class value) {
		String key = null;
		if (fieldTypes.containsValue(value)) {
			Iterator it = fieldTypes.keySet().iterator();
			while (it.hasNext()) {
				String k = (String) it.next();
				if (fieldTypes.get(k).equals(value)) {
					key = k;
					break;
				}
			}
		}
		return key;
	}
	
	/**
	 * @param filterName 比较属性字符串,含待比较的比较类型、属性值类型及属性列表. eg. LIKES_NAME_OR_LOGIN_NAME
	 * @param value 待比较的值.
	 * 
	 */
	public QueryFilter(final String filterName, String value) {
		String matchTypeStr = StringUtil.substringBefore(filterName, "_");
		String matchTypeCode = StringUtil.substring(matchTypeStr, 0, matchTypeStr.length() - 1);
		String fieldTypeCode = StringUtil.substring(matchTypeStr, matchTypeStr.length() - 1, matchTypeStr.length());
		this.fieldValue = value.split(QueryFilter.OR_SEPARATOR);
		try {
			matchType = MatchType.getValue(matchTypeCode);
			if (T.equalsIgnoreCase(fieldTypeCode) && matchTypeCode.indexOf(":") == -1 && value.indexOf(QueryFilter.OR_SEPARATOR)==-1) {
				value += " 00:00:00";
			}
			
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性比较类型.");
		}

		try {
			fieldType = (Class) fieldTypes.get(fieldTypeCode);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性值类型.");
		}
		
		String propertyNameStr = StringUtil.substringAfter(filterName, "_");
		fieldNames = propertyNameStr.split(QueryFilter.OR_SEPARATOR);

		Assert.isTrue(fieldNames.length > 0, "filter名称" + filterName + "没有按规则编写,无法得到属性名称.");
		if (value.indexOf(QueryFilter.OR_SEPARATOR)==-1) {
			// 按entity property中的类型将字符串转化为实际类型.
			this.fieldValue = ConvertUtils.convert(value, fieldType);
		} else {
			this.fieldValue = value;
			setMultiValue(true);
		}
	}

	/**
	 * 
	 * @param filterName 要查询的属性
	 * @param operation 比较操作符
	 * @param value 值
	 */
	public QueryFilter(final String filterName, final String operation, final String value) {
		this(MatchType.getKey(operation.toUpperCase()) + "S_" + filterName, value);
	}

	/**
	 * 
	 * @param filterName 要查询的属性
	 * @param operation 比较操作符
	 * @param value 值
	 * @param type 值的类型
	 */
	public QueryFilter(final String filterName, final String operation, final Object value, final String type) {
		this(MatchType.getKey(operation.toUpperCase()) + type + "_" + filterName, String.valueOf(value));
	}

	/**
	 * 是否比较多个属性.
	 */
	public boolean isMultiProperty() {
		return (fieldNames.length > 1);
	}

	/**
	 * 获取比较属性名称列表.
	 */
	public String[] getFieldNames() {
		return fieldNames;
	}

	/**
	 * 获取唯一的比较属性名称.
	 */
	public String getFieldName() {
		if (fieldNames.length > 1) {
			throw new IllegalArgumentException("There are not only one property");
		}
		return fieldNames[0];
	}

	/**
	 * 获取比较值.
	 */
	public Object getFieldValue() {
		if(fieldValue!=null){
			if(String.class.isInstance(fieldValue)){
				return String.valueOf(fieldValue).trim();
			}
		}
		return fieldValue;
	}

	/**
	 * 获取比较值的类型.
	 */
	public Class getFieldType() {
		return fieldType;
	}

	/**
	 * 获取比较方式类型.
	 */
	public Object getMatchType() {
		return this.matchType;
	}

	public boolean isMultiValue() {
		return isMultiValue;
	}

	public void setMultiValue(boolean isMultiValue) {
		this.isMultiValue = isMultiValue;
	}

	/**
	 * 对查询的条件进行特殊处理
	 * 
	 * @param matchType
	 * @param value
	 * @return
	 */
	public static Object getDealFieldValue(QueryFilter queryFilter) {

		// 如果是like 查询，则加上%
		Object objValue = queryFilter.getFieldValue();
		if (MatchType.LIKE.equals(queryFilter.getMatchType())) {
			if (objValue!=null && objValue.toString().indexOf("%") == -1) {
				return "%" + objValue + "%";
			}
			return objValue;
		}

		// 对日期字段进行特殊的处理
		// 对java.sql.Date和java.sql.Timestamp暂时都统一成java.sql.Timestamp处理
		String className = queryFilter.getFieldType().getName();
		String dClassName = ((Class) fieldTypes.get(D)).getName();
		String tClassName = ((Class) fieldTypes.get(T)).getName();
		if (className.equals(dClassName) || className.equals(tClassName)) {
			if(String.class.isInstance(objValue)){
				return parseLenient(null, (String)objValue);
			}
		}
		return objValue;
	}
	
	public static Timestamp parseLenient(DateFormat lastFormat, String source) {
		//	using formats defined in .properties
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		dateFormat.setLenient(false);//严格的方式验证日期格式，避免弱匹配模式下绑定到Timestamp值不正确的问题
		StringTokenizer st = new StringTokenizer(Configuration.getString("timestamp.format"), ";");
		while (st.hasMoreTokens()) {
			String format = st.nextToken();
			
			dateFormat.applyPattern(format);
			try {
				return new Timestamp(dateFormat.parse(source).getTime());
			}
			catch (ParseException e) {
			}
		}

		//	using default format
		try {
			return new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(source).getTime());
		}
		catch (ParseException e) {
			//	using lastFormat from caller
			if(lastFormat != null) {
				try {
					return new Timestamp(lastFormat.parse(source).getTime());
				}
				catch (ParseException e1) {
					return null;
//					throw new IllegalArgumentException("Could not parse date: " + e4.getMessage());
				}
			} else {
				return null;
//				throw new IllegalArgumentException("Could not parse date: " + e3.getMessage());
			}
		}
	}
}
