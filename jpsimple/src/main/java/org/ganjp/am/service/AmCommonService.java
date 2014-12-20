/*
 * $Id: AmUserService.java,v 1.1 2012/03/18 14:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.am.service;

import org.ganjp.am.model.AmUser;
import org.ganjp.core.db.base.Page;

import java.util.List;
import java.util.Map;

/**
 * {annotation}
 * 
 * @author ganjp
 * @version 1.0
 * @since 1.0
 */
public interface AmCommonService {
	/**
	 * edit AmUser, add AmUserRole and delete AmUserRole
	 * 
	 * @param columnNames new String[]{"config_key","config_value","config_id"}
	 * @param paramObjects  Object[]{'isStartupH2','true','1234567890123456789012345679012'}
	 * @param roleIdsWithComma '1234567890123456789012345679012,1234567890123456789012345679013'
	 */
	public void editAmUserAndAmUserRole(final String[] columnNames, final Object[] paramObjects,final String roleIdsWithComma);
	
	/**
	 * get amUser exist in AmUserRole
	 * 
	 * @param userCd
	 * @param password
	 * @return
	 */
	public AmUser getAmUserInAmUserRole(String userCd, String password);
}