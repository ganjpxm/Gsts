/**
 * $Id: AmUserServiceImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.gone.am.service.impl;

import javax.transaction.Transactional;

import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.am.dao.AmUserDao;
import org.ganjp.gone.am.model.AmUser;
import org.ganjp.gone.am.service.AmUserService;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.service.AbstractService;
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
	
    public AmUserServiceImpl() {
        super();
    }
    
    /**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
    @Transactional
   	public void batchDelete(final String pks) {
    	String hql = "delete from AmUser where userId in (" + StringUtil.getStrWithQuote(pks) + ")";
		batchExecute(hql);
    }

    @Override
    protected Operations<AmUser> getDao() {
        return dao;
    }
    
    @Autowired
    private AmUserDao dao;
}