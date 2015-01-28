/**
 * $Id: AmUserServiceImpl.java,v 1.0 2012/08/19 17:09:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Coss Project
 *
 */
package org.ganjp.gone.am.service.impl;

import javax.transaction.Transactional;

import org.ganjp.gone.am.dao.AmUserDao;
import org.ganjp.gone.am.model.AmUser;
import org.ganjp.gone.am.service.AmUserService;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
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
    	dao.batchDelete(pks);
    }
    
    /**
     * <p>getAmUserPage</p>
     * 
     * @param search
     * @param startDate
     * @param endDate
     * @param dataStates
     * @param pageNo
     * @param pageSize
     * @param orderBy
     * @return
     */
	public Page<AmUser> getAmUserPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		return dao.getAmUserPage(search, startDate, endDate, dataStates, pageNo, pageSize, orderBy);
	}

    @Override
    protected Operations<AmUser> getDao() {
        return dao;
    }
    
    @Autowired
    private AmUserDao dao;
}