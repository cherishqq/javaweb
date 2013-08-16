package com.javaweb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.dao.UserDao;
import com.javaweb.model.User;


@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	
	@Transactional(readOnly = true)
	public User queryUserByName(String userName) {
		return userDao.findUniqueBy("userName", userName);
	}
	
	public String getString(){
		return "XXOO";
	}
	
	public void saveUser(User u){
		userDao.save(u);
	}
	
}
