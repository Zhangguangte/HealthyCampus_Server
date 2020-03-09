package com.muyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.muyou.common.pojo.DataTablesResult;
import com.muyou.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired(required = false)
	private UserService userService;
	
	@RequestMapping(value = "/count",method = RequestMethod.GET)
	@ResponseBody
	public DataTablesResult getUserCount(){
        return userService.getUserCount();
    }
	
	
}
