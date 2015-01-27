package org.ganjp.gone.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.model.PropertyFilter;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractService<T extends Serializable> implements Operations<T> {

    @Override
    public T findOne(final String id) {
        return getDao().findOne(id);
    }

    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    public void create(final T entity) {
        getDao().create(entity);
    }

    @Override
    public T update(final T entity) {
        return getDao().update(entity);
    }

    @Override
    public void delete(final T entity) {
        getDao().delete(entity);
    }

    @Override
    public void deleteById(final String entityId) {
        getDao().deleteById(entityId);
    }

    /**
	 * <p>get all records and support order</p>
	 *
	 * @param String
	 * @param boolean
	 * @return List<T>
	 */
	public List<T> findAllWithOrder(Class<T> entityClass, String orderBy, boolean isAsc) {
		return getDao().findAllWithOrder(entityClass, orderBy, isAsc);
	}
	
	/**
	 * <p>get match records by fieldName</p>
	 * 
	 * @param String
	 * @param Object
	 * @return List<T>
	 */
	public List<T> findByField(Class<T> entityClass, final String fieldName, final Object value) {
		return getDao().findByField(entityClass, fieldName, value);
	}
	
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
	public List<T> findByField(Class<T> entityClass, final String fieldName, final Object value, final String orderBy, final boolean isAsc) {
		return getDao().findByField(entityClass, fieldName, value, orderBy, isAsc);
	}
	
	/**
	 * <p>find By PropertyFilter List</p>
	 * 
	 * @param List<PropertyFilter> new PropertyFilter("EQS_loginName", "admin")
	 * @return List<T>
	 */
	public List<T> findByPropertyFilterList(Class<T> entityClass, List<PropertyFilter> filters) {
		return getDao().findByPropertyFilterList(entityClass, filters);
	}
	
	/**
	 * <p>findByPropertyFilterList</p>
	 * 
	 * @param entityClass
	 * @param filters
	 * @param orderBy
	 * @param isAsc
	 * @return
	 */
	public List<T> findByPropertyFilterList(Class<T> entityClass, List<PropertyFilter> filters, String orderBy, boolean isAsc) {
		return getDao().findByPropertyFilterList(entityClass, filters, orderBy, isAsc);
	}
	
	
	//------------ find by hql
	/**
	 * <p>search unique object by hql</p>
	 * 
	 * @param hql eg: from User u where loginName=? and userPassword=?
	 * @param Object... "ganjp","123"
	 * @return X
	 */
	public <X> X findUniqueByHql(final String hql, final Object... values) {
		return getDao().findUniqueByHql(hql, values);
	}
	
	/**
	 * <p>search unique object by hql</p>
	 * 
	 * @param hql eg:from AmUser where userName=:userName
	 * @param Map eg:map.put("userName", "ganjp");
	 * @return X
	 */
	public <X> X findUniqueByHql(final String hql, final Map<String, ?> values) {
		return getDao().findUniqueByHql(hql, values);
	}
	
	/**
	 * <p>search objects by hql</p>
	 * 
	 * @param hql eg:from User u where loginName=? and userPassword=?
	 * @param values eg:"ganjp","123"
	 * @return List<X>
	 */
	public <X> List<X> findByHql(final String hql, final Object... values) {
		return getDao().findByHql(hql, values);
	}
	
	/**
	 * <p>search objects by hql</p>
	 * 
	 * @param hql eg: from AmUser where userName=:userName
	 * @param values map.put("userName", "ganjp");
	 * @return
	 */
	public <X> List<X> findByHql(final String hql, final Map<String, ?> values) {
		return getDao().findByHql(hql, values);
	}
	
	/**
	 * <p>search objects by hql and page</p>
	 * 
	 * @param pageNo eg:1
	 * @param pageSize eg:5
	 * @param hql eg:from AmUser where email like ?
	 * @param Object... eg:"%illume%"
	 * @return Page<T>
	 */
	public Page<T> fetchPageByHql(final int pageNo, final int pageSize, final String hql, final Object... values) {
		return getDao().fetchPageByHql(pageNo, pageSize, hql, values);
	}
	/**
	 * <p>search objects by hql and page</p>
	 * 
	 * @param Page<T> eg:new Page<AmUser>(5).
	 * @param hql eg:from AmUser where email like ?
	 * @param Object... eg:"%illume%"
	 * @return Page<T> 
	 */
	public Page<T> fetchPageByHql(final Page<T> page, final String hql, final Object... values) {
		return getDao().fetchPageByHql(page, hql, values);
	}
	
	/**
	 * <p>execute hql</p>
	 * 
	 * @param hql eg:"update User set status='disabled' where id=?"
	 * @param Object... eg:'122222222222323435345443'
	 */
	public void batchExecute(final String hql, final Object... values) {
		getDao().batchExecute(hql, values);
	}
	
    protected abstract Operations<T> getDao();
}
