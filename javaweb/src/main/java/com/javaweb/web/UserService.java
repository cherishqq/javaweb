package com.javaweb.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.dao.UserDao;
import com.javaweb.model.User;


@Service("userService")
@Transactional
public class UserService implements Serializable {
	
	@Autowired
	private UserDao userDao;
	
	public static Map<String,String> map = new HashMap<String,String>();
	
	static {
		map.put("1", "2");
		map.put("2", "3");
	}

	
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
