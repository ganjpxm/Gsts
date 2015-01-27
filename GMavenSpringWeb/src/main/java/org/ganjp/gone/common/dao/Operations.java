package org.ganjp.gone.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.model.PropertyFilter;

public interface Operations<T extends Serializable> {

    T findOne(final String id);

    List<T> findAll();

    void create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final String entityId);
    
    /**
	 * <p>get all records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<T>
	 */
	public List<T> findAllWithOrder(Class<T> entityClass, String orderBy, boolean isAsc);
	
	/**
	 * <p>get match records by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<T>
	 */
	public List<T> findByField(Class<T> entityClass, final String fieldName, final Object value);
	
	/**
	 * <p>get match records by fieldName with order</p>
	 * 
	 * @param entityClass
	 * @param fieldName
	 * @param value
	 * @param orderBy
	 * @param isAsc
	 * @return List<T>
	 */
	public List<T> findByField(Class<T> entityClass, final String fieldName, final Object value, 
			   final String orderBy, final boolean isAsc);
	
	/**
	 * <p>find By PropertyFilter List</p>
	 * 
	 * @param List<PropertyFilter> new PropertyFilter("EQS_loginName", "admin")
	 * @return List<T>
	 */
	public List<T> findByPropertyFilterList(Class<T> entityClass, List<PropertyFilter> filters);
	
	/**
	 * <p>findByPropertyFilterList</p>
	 * 
	 * @param entityClass
	 * @param filters
	 * @param orderBy
	 * @param isAsc
	 * @return
	 */
	public List<T> findByPropertyFilterList(Class<T> entityClass, List<PropertyFilter> filters, String orderBy, boolean isAsc);
	
	
	//------------ find by hql
	/**
	 * <p>search unique object by hql</p>
	 * 
	 * @param hql eg: from User u where loginName=? and userPassword=?
	 * @param Object... "ganjp","123"
	 * @return X
	 */
	public <X> X findUniqueByHql(final String hql, final Object... values);
	
	/**
	 * <p>search unique object by hql</p>
	 * 
	 * @param hql eg:from AmUser where userName=:userName
	 * @param Map eg:map.put("userName", "ganjp");
	 * @return X
	 */
	public <X> X findUniqueByHql(final String hql, final Map<String, ?> values);
	
	/**
	 * <p>search objects by hql</p>
	 * 
	 * @param hql eg:from User u where loginName=? and userPassword=?
	 * @param values eg:"ganjp","123"
	 * @return List<X>
	 */
	public <X> List<X> findByHql(final String hql, final Object... values);
	
	/**
	 * <p>search objects by hql</p>
	 * 
	 * @param hql eg: from AmUser where userName=:userName
	 * @param values map.put("userName", "ganjp");
	 * @return
	 */
	public <X> List<X> findByHql(final String hql, final Map<String, ?> values);
	
	/**
	 * <p>search objects by hql and page</p>
	 * 
	 * @param pageNo eg:1
	 * @param pageSize eg:5
	 * @param hql eg:from AmUser where email like ?
	 * @param Object... eg:"%illume%"
	 * @return Page<T>
	 */
	public Page<T> fetchPageByHql(final int pageNo, final int pageSize, final String hql, final Object... values);
	/**
	 * <p>search objects by hql and page</p>
	 * 
	 * @param Page<T> eg:new Page<AmUser>(5).
	 * @param hql eg:from AmUser where email like ?
	 * @param Object... eg:"%illume%"
	 * @return Page<T> 
	 */
	public Page<T> fetchPageByHql(final Page<T> page, final String hql, final Object... values);
	
	/**
	 * <p>execute hql</p>
	 * 
	 * @param hql eg:"update User set status='disabled' where id=?"
	 * @param Object... eg:'122222222222323435345443'
	 */
	public void batchExecute(final String hql, final Object... values);
}
