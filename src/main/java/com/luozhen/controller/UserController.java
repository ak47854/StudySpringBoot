package com.luozhen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luozhen.entity.User;
import com.luozhen.service.UserService;

@Controller
@RequestMapping(value="/user/*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/selectTest",method=RequestMethod.GET)
	@ResponseBody
	public User selectTest() {
		Integer id = 1;
		User user = userService.selectTest(id);
		return user;
	}
}
