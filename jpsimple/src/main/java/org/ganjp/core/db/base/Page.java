package org.ganjp.core.db.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.ganjp.core.entity.Entity;
import org.ganjp.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装. 注意所有序号从1开始.
 *  
 * @author ResourceOne BizFoundation Team: ganjp
 * @version 1.0
 * @since 4.2
 * @modify by zhaoqy 2010-07-19 使之支持JDK1.4
 */
public class Page implements Serializable{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final long serialVersionUID = 5192357666324600054L;

	// --------------------------- 分页参数变量 -----------------------
	protected int pageNo = 1; // 当前页的页号
	protected int pageSize = 15; // 每页的记录数量
	protected String orderBy = null; // 多个排序字段时用','分隔
	protected String orderType = null; // 排序方向,对应的orderFields个数,多个时用','分隔
	protected boolean autoCount = true; // 查询对象时是否自动另外执行count查询获取总记录数
	protected int start = 0;

	// --------------------------- 返回结果变量 -----------------------
	protected List resultList = new ArrayList();//Lists.newArrayList();// 取得页内的记录列表
	protected long totalCount = -1; // 取得总记录数

	// --------------------------- 构造函数 ---------------------------
	
	
	public Page() {
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	public Page(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	// --------------------------- 访问查询参数函数 ---------------------
	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public Page pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	/**
	 * 获得每页的记录数量,默认为1.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量,低于1时自动调整为1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	public Page pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 */
	public int getFirst() {
		if (start != 0)
			return start + 1;
		return ((pageNo - 1) * pageSize) + 1;
	}

	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * 获得排序字段,无默认值.多个排序字段时用','分隔.
	 */
	public String getOrderFields() {
		return orderBy;
	}

	/**
	 * 设置排序字段,多个排序字段时用','分隔.
	 */
	public void setOrderFields(final String orderFields) {
		this.orderBy = orderFields;
	}

	public Page orderFields(final String theOrderFields) {
		setOrderFields(theOrderFields);
		return this;
	}

	/**
	 * 获得排序方向.
	 */
	public String getOrder() {
		return orderType;
	}

	/**
	 * 设置排序方式向.
	 * 
	 * @param order
	 *            可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) {
		// 检查order字符串的合法值
		String[] orders = StringUtil.split(StringUtil.toLowerCase(order), ",");
		
		for(int i=0;i<orders.length;i++){
			String orderStr = orders[i];
			if (!"desc".equalsIgnoreCase(orderStr) && !"asc".equalsIgnoreCase(orderStr)) {
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
			}
		}

		this.orderType = StringUtil.toLowerCase(order);
	}

	public Page order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}

	/**
	 * 是否已设置排序字段,无默认值.
	 */
	public boolean isOrderFieldsSetted() {
		return (StringUtil.isNotBlank(orderBy) && StringUtil
				.isNotBlank(orderType));
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数, 默认为false.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	public Page autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	// --------------------------- 访问查询结果函数 ---------------------
	/**
	 * 取得页内的记录列表.
	 */
	public List getResult() {
		return resultList;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setResult(final List result) {
		this.resultList = result;
	}

	/**
	 * 取得总记录数, 默认值为-1.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */
	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}

	public int getStart() {
		return start;
	}
	
	/**
	 * 获取记录Page信息的cache key
	 * @param key
	 * @return
	 */
	public static String getPageCacheKey(String key){
		String result = "";
		if(key.startsWith("/")){
			result = key.substring(1,key.length());
		}
		
		if(result.indexOf("/")!=-1){
			result = result.substring(0,result.indexOf("/"));
		}
		
		return result;
	}
	
	public String toJsonStr() {
		StringBuffer json = new StringBuffer();
		json.append("{'pageNo':").append(this.pageNo).append(",'pageSize':").append(this.pageSize)
			.append(",'start':").append(this.start).append(",'totalCount':").append(this.totalCount)
			.append(",'orderBy':'").append(this.orderBy).append("','orderType':'").append(orderType)
			.append("','result':[");
		try {
			if (null != this.resultList  && !this.resultList.isEmpty()) {
				int size = this.resultList.size();
				for (int i = 0; i < size; i++) {
					Entity entity = (Entity)this.resultList.get(i);
					json.append(entity.toJsonStr()).append(",");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		String jsonStr = json.toString();
		if (jsonStr.endsWith(",")) {
			jsonStr = jsonStr.substring(0, jsonStr.length() - 1) + "]}";
		} else {
			jsonStr += "]}";
		}
		return jsonStr;
	}
}
