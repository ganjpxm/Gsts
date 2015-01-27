/**
 * $Id: AmUserController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.gcore.Const;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.am.model.AmUser;
import org.ganjp.gone.am.service.AmUserService;
import org.ganjp.gone.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
public class AmUserController extends BaseController {

	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ModelAndView goToUserPage() {
		ModelAndView modelAndView = new ModelAndView("am/amUser");
		return modelAndView;
	}
	
    @RequestMapping(value="/users", method=RequestMethod.GET)
    public @ResponseBody List<AmUser> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<AmUser> amUsers = amUserService.findAll();
        return amUsers;
    }
    
    @RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
    public @ResponseBody AmUser getAmUser(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) {
        AmUser amUser = amUserService.findOne(userId);
        return amUser;
    }
    
    @RequestMapping(value="/user", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmUser amUser = new AmUser();
    		super.setValue(request, amUser);
    		amUserService.create(amUser);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    @RequestMapping(value="/user/{userId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmUser amUser = amUserService.findOne(userId);
    		super.setValue(request, amUser);
    		amUserService.update(amUser);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    @RequestMapping(value="/user/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String userIds = request.getParameter("userIds");
    		if (StringUtil.hasText(userIds)) {
    			amUserService.batchDelete(userIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private AmUserService amUserService;
}