package org.ganjp.core.entity;

import java.lang.reflect.Field;

import org.ganjp.core.util.JsonUtil;
import org.ganjp.core.util.ReflectUtil;
import org.ganjp.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Entity implements IEntity {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	// 查询时拼查询条件用的字段
	private String queryFilters = null;

	/**
	 * <p>
	 * 克隆
	 * </p>
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getQueryFilters() {
		return queryFilters;
	}

	public void setQueryFilters(String queryFilters) {
		this.queryFilters = queryFilters;
	}

	/**
	 * 返回实体全部属性的json格式字符串数据
	 * @return
	 */
	public String toJsonStr() {
		return this.toJsonStr(null);
	}
	
	/**
	 * 返回实体全部属性的json格式字符串数据
	 * @return
	 */
	public StringBuffer toJsonStrBuffer() {
		return this.toJsonStrBuffer(null);
	}
	
	/**
	 * 以对象形式返回指定属性的json格式数据
	 */
	public String toJsonStr(String columns) {
		return this.toJsonStrBuffer(columns).toString();
	}
	
	/**
	 * 以对象形式返回指定属性的json格式数据
	 * @return
	 */
	public StringBuffer toJsonStrBuffer(String columns) {
		Class clas = this.getClass();
		Field[] fieldArr = clas.getDeclaredFields();// 获取所有的字段
		StringBuffer jsonStrBuffer = new StringBuffer(JsonUtil.EMPTY_JSON_OBJECT_STR);
		try {
			for (int i = 0; i < fieldArr.length; i++) {
				Field field = fieldArr[i];
				String key = field.getName();
				if (StringUtil.isNotBlank(columns)) {
					String replaceKey = StringUtil.getAfterColonValue(columns, field.getName());
					if (StringUtil.isBlank(replaceKey)) {
						continue;
					} else {
						key = replaceKey;
					}
				}
				field.setAccessible(true);
				Object value;
				try{
					value = ReflectUtil.getPropertityValue(this, field.getName());
				} catch (Exception e) {
					value = field.get(null);
					log.error("获取"+ field.getName() + "映射值发生!");
				}
				JsonUtil.addJsonObjectItem(jsonStrBuffer, "\"" + key + "\":" + JsonUtil.getPrimitiveValue(value));
			}
		} catch (Exception e) {
			log.error("获取类" + clas.getName() + "的json值出错：" + e.getMessage());
		}
		return jsonStrBuffer;
	}
	
	/**
	 * hashCode算法
	 */
	public int hashCode() {
		int result = 17;
		Class c = this.getClass();
		Field[] fields = c.getDeclaredFields();// 获取所有的字段
		try {
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				f.setAccessible(true);
				result = 37 * result + (f.get(this) == null ? 0 : f.get(this).hashCode());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 
	 */
	public String toString() {
		Class c = this.getClass();
		Field[] fields = c.getDeclaredFields();// 获取所有的字段
		StringBuffer buffer = new StringBuffer();
		buffer.append(c.getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		try {
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				f.setAccessible(true);
				buffer.append(f.getName()).append("='").append(f.get(this)).append("' ");
			}
			buffer.append("]");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return buffer.toString();
	}
	
	public String getSelectSql(){
		return "";
	}
	
}
