/**
 * $Id: AmUserServiceImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.gmsw.am.service.impl;

import org.ganjp.gmsw.am.model.AmUser;
import org.ganjp.gmsw.am.service.AmUserService;
import org.ganjp.gmsw.am.dao.AmUserDao;
import org.ganjp.gmsw.common.dao.Operations;
import org.ganjp.gmsw.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>AmUserServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class AmUserServiceImpl extends AbstractService<AmUser> implements AmUserService {

    @Autowired
    private AmUserDao dao;

    public AmUserServiceImpl() {
        super();
    }

    // API

    @Override
    protected Operations<AmUser> getDao() {
        return dao;
    }

}