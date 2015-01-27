/**
 * $Id: AmUserService.java,v1.0 2014/12/17 12:00:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.service;

import org.ganjp.gone.am.model.AmUser;
import org.ganjp.gone.common.dao.Operations;

/**
 * <p>AmUserService</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
public interface AmUserService extends Operations<AmUser> {
	/**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
   	public void batchDelete(final String pks);
   	
}