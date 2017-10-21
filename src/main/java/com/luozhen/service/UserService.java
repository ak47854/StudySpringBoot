package com.luozhen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luozhen.dao.UserMapper;
import com.luozhen.entity.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public User selectTest(Integer id) {
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}
}
