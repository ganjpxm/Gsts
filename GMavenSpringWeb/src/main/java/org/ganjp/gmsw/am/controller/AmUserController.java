/**
 * $Id: AmUserController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gmsw.am.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.gcore.Const;
import org.ganjp.gmsw.am.model.AmUser;
import org.ganjp.gmsw.am.service.AmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>AmUserController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/am")
public class AmUserController {

	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ModelAndView goToUserPage() {
		ModelAndView modelAndView = new ModelAndView("am/amUser");
		return modelAndView;
	}
	
    @RequestMapping(value="/users", method=RequestMethod.GET)
    public @ResponseBody List<AmUser> findAll(HttpServletRequest request, HttpServletResponse response, AmUser amUser) {
        List<AmUser> AmUsers = amUserService.findAll();
        return AmUsers;
    }
    
    @RequestMapping(value="/user", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response, AmUser amUser) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		amUserService.create(amUser);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    
	@Autowired
	private AmUserService amUserService;
}