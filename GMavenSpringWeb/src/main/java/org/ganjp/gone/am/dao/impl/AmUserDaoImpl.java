/**
 * $Id: AmUserDaoImpl.java, v1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.dao.impl;

import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.am.model.AmUser;
import org.ganjp.gone.am.dao.AmUserDao;
import org.ganjp.gone.common.dao.impl.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * <p>AmUserDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Repository
public class AmUserDaoImpl extends AbstractHibernateDao<AmUser> implements AmUserDao {

    public AmUserDaoImpl() {
        super();

        setClazz(AmUser.class);
    }

    /**
   	 * <p>batchDelete</p>
   	 * 
   	 * @param pks
   	 */
    public void batchDelete(final String pks) {
    	String hql = "delete from AmUser where userId in (" + StringUtil.getStrWithQuote(pks) + ")";
		batchExecute(hql);
    }

}