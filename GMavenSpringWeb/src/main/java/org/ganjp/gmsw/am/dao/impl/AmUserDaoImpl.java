/**
 * $Id: AmUserDaoImpl.java, v1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gmsw.am.dao.impl;

import org.ganjp.gmsw.am.model.AmUser;
import org.ganjp.gmsw.am.dao.AmUserDao;
import org.ganjp.gmsw.common.dao.impl.AbstractHibernateDao;
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

    // API

}