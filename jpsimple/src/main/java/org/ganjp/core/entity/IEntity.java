/*
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 *
 */
package org.ganjp.core.entity;

import java.io.Serializable;

/**
 * <p>
 * 数据实体接口
 * </p>
 * 
 * @author ganjp
 * @version 1.0
 * @since 4.2
 * $Revision: 1.2 $
 */
public interface IEntity extends Serializable, Cloneable {

	/**
	 * 返回实体全部属性的json格式字符串数据
	 * 
	 * @return String(json格式)
	 */
	public String toJsonStr();
	
	/**
	 * 返回实体全部属性的json格式字符串数据
	 * 
	 * @param columns: "name,id"
	 * @return
	 */
	public String toJsonStr(String columns);

	/**
	 * 返回实体的查询语句，包含select和from，不包含where
	 * 
	 * @return
	 */
	public String getSelectSql();
}
