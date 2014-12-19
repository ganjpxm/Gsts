/**
 * $Id: HbmParser.java,v 1.10 2011/02/25 08:28:08 ganjp Exp $
 *
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 *
 */
package org.ganjp.extend.xml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gcore.util.XmlUtil;
import org.w3c.dom.Element;

/**
 * <p>解析hbm文件</p>
 * 
 * @author ResourceOne BizFoundation Team: ganjp
 * @since 1.0
 */
public class HbmParser {

	private String id; 

	private String idColumnName;
	
	private String idType;
	
	private String tableName;
	
	private String className;
	
	private String schema;
	
	private String catalog;
	
	private String generator;

	private List columnList;
	
	private List importTypeList;

	public HbmParser(InputStream inputStream) throws Exception {
		init(inputStream);
	}

	public HbmParser(String fileName) throws Exception {
		InputStream inputStream = new FileInputStream(fileName);
		init(inputStream);
	}
	
	/**
	 * 从hbm文件读取相关信息
	 * <hibernate-mapping>
	 * 	 <class name="com.icss.base.model.BaseDemo" table="BASE_DEMO">
			<id name="baseDemoId" type="java.lang.String" column="BASE_DEMO_ID">
				<generator class="uuid" />
			</id>
			<property name="byteValue" type="byte" column="BYTE_VALUE"/>
		</class>		 
	 * <hibernate-mapping>
	 * 
	 * @param inputStream
	 * @throws Exception
	 */
	protected void init(InputStream inputStream) throws Exception {
		this.importTypeList = new ArrayList();//初始化需要import的类型对应的importTypeList集合对象
		this.columnList = new ArrayList(); //初始化Column集合对象
		
		Element parentElement = XmlUtil.loadAsElement(inputStream, "hibernate-mapping");
		//获取类信息
		Element classElement = XmlUtil.getChildElement(parentElement, "class");
		this.tableName =  XmlUtil.getAttribute(classElement, "table");
		this.className = XmlUtil.getAttribute(classElement, "name");
		this.schema = XmlUtil.getAttribute(classElement, "schema");
		this.catalog = XmlUtil.getAttribute(classElement, "catalog");
		
		//获取主键信息
		Element idElement = XmlUtil.getChildElement(classElement, "id");
		this.id = XmlUtil.getAttribute(idElement, "name");
		this.idColumnName = XmlUtil.getAttribute(idElement, "column");
		this.idType = XmlUtil.getAttribute(idElement, "type");
		Element generatorE = XmlUtil.getChildElement(idElement, "generator");
		this.generator = XmlUtil.getAttribute(generatorE, "class");
		if (!this.importTypeList.contains(idType) && !idType.startsWith("java.lang")) {
			this.importTypeList.add(idType);
		}
		
		//获取类的字段信息
		List propertyList = XmlUtil.getChildElements(classElement, "property");
		for (Iterator itr = propertyList.iterator(); itr.hasNext();) {
			Element propertyElement = (Element) itr.next();
			Column column = new Column();
			column.setFieldName(XmlUtil.getAttribute(propertyElement, "name"));
			column.setUpperFieldName(StringUtil.upperFirst(column.getFieldName()));
			column.setColumnName(XmlUtil.getAttribute(propertyElement, "column"));
			
			//对字段类型的特殊处理
			String type = XmlUtil.getAttribute(propertyElement, "type");
			column.setFieldType(type);
			column.setFieldShowType(convertShowType(type));
			column.setSimpleFieldType(convertSimpleType(type));
			if (!this.importTypeList.contains(type) && !type.startsWith("java.lang") && !"binary".equalsIgnoreCase(type)) {
				this.importTypeList.add(type);
			} 
			if ("binary".equalsIgnoreCase(type)) {
				if (!this.importTypeList.contains("java.io.InputStream")) {
					this.importTypeList.add("java.io.InputStream");
				}
				column.setFieldType("java.io.InputStream");
				column.setFieldShowType("file");
				column.setSimpleFieldType("InputStream");
			}
			this.columnList.add(column);
		}
	}
	
	/**
	 * 分为三大展示类型:字符串,日期,数字
	 * @param fieldType
	 * @return
	 */
	public static String convertShowType(String fieldType) {
		String fieldSimpleType = "string";
		if (fieldType.equalsIgnoreCase("java.sql.Date") || fieldType.equalsIgnoreCase("java.util.Date") ||
			fieldType.equalsIgnoreCase("java.sql.Timestamp") || fieldType.equalsIgnoreCase("java.util.Timer")) {
			fieldSimpleType = "date";
		}
		if (fieldType.equalsIgnoreCase("java.math.BigDecimal") || fieldType.equalsIgnoreCase("java.lang.Integer") ||
			fieldType.equalsIgnoreCase("java.math.BigInteger") || fieldType.equalsIgnoreCase("java.lang.Double")
			|| fieldType.equalsIgnoreCase("java.lang.Long") || fieldType.equalsIgnoreCase("java.lang.Float")) {
			fieldSimpleType = "number";
		}
		return fieldSimpleType;
	}
	
	/**
	 * 把完整类(java.lang.String)转化简单类(String) 
	 * @param fieldType
	 * @return
	 */
	public static String convertSimpleType(String fieldType) {
		if (StringUtil.isNotBlank(fieldType) && fieldType.indexOf(".")!=-1) {
			int index = fieldType.lastIndexOf(".");
			fieldType = fieldType.substring(index+1);
		}
		return fieldType;
	}
	
	/**
	 * 通过列名获得字段名
	 * @param columnName
	 * @return
	 */
	public String getFieldNameByColumnName(String columnName) {
		for (Iterator itr = this.columnList.iterator(); itr.hasNext();) {
			Column column = (Column) itr.next();
			if (column.getColumnName().equalsIgnoreCase(columnName)) {
				return column.getFieldName();
			}
		}
		throw new RuntimeException("Cannot location Column Name:" + columnName);
	}

	/**
	 * 通过字段名获得列名
	 * @param fieldName
	 * @return
	 */
	public String getColumnNameByFieldName(String fieldName) {
		for (Iterator itr = this.columnList.iterator(); itr.hasNext();) {
			Column column = (Column) itr.next();
			if (column.getFieldName().equalsIgnoreCase(fieldName)) {
				return column.getColumnName();
			}
		}
		throw new RuntimeException("Cannot location Filed Name:" + fieldName);
	}
	
	/**
	 * 获得所有的字段名(包括主键)
	 * 
	 * @return
	 */
	public List getAllFieldNames() {
		List temp = new ArrayList();
		temp.add(this.id);
		for (Iterator itr = this.columnList.iterator(); itr.hasNext();) {
			Column c = (Column) itr.next();
			temp.add(c.getFieldName());
		}
		return temp;
	}

	/**
	 * 获得所有的列名(不包括主键)
	 * @return
	 */
	public List getColumnNames() {
		List temp = new ArrayList();
		for (Iterator itr = this.columnList.iterator(); itr.hasNext();) {
			Column c = (Column) itr.next();
			temp.add(c.getColumnName());
		}
		return temp;
	}
	
	/**
	 * 获得所有的列名(包括主键)
	 * 
	 * @return
	 */
	public List getAllColumnNames() {
		List temp = new ArrayList();
		temp.add(this.idColumnName);
		for (Iterator itr = this.columnList.iterator(); itr.hasNext();) {
			Column c = (Column) itr.next();
			temp.add(c.getColumnName());
		}
		return temp;
	}
	
	/**
	 * 获得所有的字段名(不包括主键)
	 * 
	 * @return
	 */
	public List getFieldNames() {
		List temp = new ArrayList();
		// temp.add(this.id);
		for (Iterator itr = this.columnList.iterator(); itr.hasNext();) {
			Column c = (Column) itr.next();
			temp.add(c.getFieldName());
		}
		return temp;
	}
	
	/**
	 * 获得所有的字段类型(不包括主键)
	 * 
	 * @return
	 */
	public List getFieldTypes() {
		List temp = new ArrayList();
		for (Iterator itr = this.columnList.iterator(); itr.hasNext();) {
			Column c = (Column) itr.next();
			temp.add(c.getFieldType());
		}
		return temp;
	}
	
	/**
	 * 获得所有的字段显示类型(不包括主键)
	 * 
	 * @return
	 */
	public List getFieldShowTypes() {
		List temp = new ArrayList();
		for (Iterator itr = this.columnList.iterator(); itr.hasNext();) {
			Column c = (Column) itr.next();
			temp.add(c.getFieldShowType());
		}
		return temp;
	}
	
	/**
	 * 获得所有的字段类型(包括主键)
	 * 
	 * @return
	 */
	public List getAllFieldTypes() {
		List temp = new ArrayList();
		temp.add(this.idType);
		for (Iterator itr = this.columnList.iterator(); itr.hasNext();) {
			Column c = (Column) itr.next();
			temp.add(c.getFieldType());
		}
		return temp;
	}
	
	public List getColumns() {
		return this.columnList;
	}

	/**
	 * 获得所有的字段名(第一字母大写，包括主键)
	 * 
	 * @return
	 */
	public List getAllFirstUpperFieldNames() {
		List tmpList = new ArrayList();
		tmpList.add(StringUtil.upperFirst(this.id));
		for (Iterator itr = this.columnList.iterator(); itr.hasNext();) {
			Column column = (Column) itr.next();
			tmpList.add(column.getUpperFieldName());
		}
		return tmpList;
	}
	
	/**
	 * 获得所有的字段简单类型(包括主键)
	 * 
	 * @return
	 */
	public List getAllSimpleFieldTypes() {
		List temp = new ArrayList();
		temp.add(HbmParser.convertSimpleType(this.idType));
		for (Iterator itr = this.columnList.iterator(); itr.hasNext();) {
			Column c = (Column) itr.next();
			temp.add(c.getSimpleFieldType());
		}
		return temp;
	}
	
	public String getId() {
		return id;
	}

	public String getIdColumnName() {
		return idColumnName;
	}

	public String getIdType() {
		return idType;
	}

	public List getImportTypeList() {
		return importTypeList;
	}
	
	public class Column {
		private String fieldName; 

		private String columnName;
		
		private String fieldType;
		
		private String simpleFieldType;//把前缀去除掉的字段类型,如java.lang.String-->String
		
		private String fieldShowType; //把字段类型归类(日期类型date,数字类型number,字符串类型string)
		
		private String upperFieldName; //第一个字母是大写的属性名

		public String getColumnName() {
			return columnName;
		}
		
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
		
		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldType() {
			return fieldType;
		}

		public void setFieldType(String fieldType) {
			this.fieldType = fieldType;
		}

		public String getFieldShowType() {
			return fieldShowType;
		}

		public void setFieldShowType(String fieldShowType) {
			this.fieldShowType = fieldShowType;
		}

		public String getUpperFieldName() {
			return upperFieldName;
		}

		public void setUpperFieldName(String upperFieldName) {
			this.upperFieldName = upperFieldName;
		}

		public String getSimpleFieldType() {
			return simpleFieldType;
		}

		public void setSimpleFieldType(String simpleFieldType) {
			this.simpleFieldType = simpleFieldType;
		}
	}

	public String getTableName() {
		return tableName;
	}

	public String getClassName() {
		return className;
	}

	public String getGenerator() {
		return generator;
	}

	public String getSchema() {
		return schema;
	}

	public String getCatalog() {
		return catalog;
	}

}

