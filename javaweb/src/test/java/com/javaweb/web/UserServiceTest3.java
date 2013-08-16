package com.javaweb.web;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javaweb.dao.UserDao;
import com.javaweb.model.User;

public class UserServiceTest3  {
	
	
	private Logger logger = LoggerFactory.getLogger(UserServiceTest3.class);
	private UserDao userDao;
	
	private UserService userService;
	
	
	@Before
		public void setUp() {
			
//			String s = System.getProperty("java.class.path");
			
//			logger.debug("classpath:" + s);
		
		
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-test.xml");		
		    String [] ss = ctx.getBeanDefinitionNames();
			System.out.println(" length:" + ss.length);
			
			userDao = (UserDao) ctx.getBean("userDao");
			userService = (UserService)ctx.getBean("userService");
		}
	
	@Test
	public void save(){
		System.out.println("hehe");
		System.out.println("hehe");
		User u = new User("derek","123","haijinme@qq.com");
//		userDao.save(user);
		userService.saveUser(u);
	}

}
