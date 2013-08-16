package com.javaweb.web;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.dao.UserDao;
import com.javaweb.model.User;

public class UserServiceTest3  {
	
	
	private Logger logger = LoggerFactory.getLogger(UserServiceTest3.class);
	
	/**
	 *  2013-8-16
	 * 使用 userDao saveUser 会报错.. No Hibernate Session bound to thread, and configuration does not allow creation of non-transactional one here
	 * 后面找到原因了,是因为 SimpleHibernateDao 中使用了 getCurrentSession,应该用openSession
	 *  
	 */
	
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
		User u = new User("derek","1235","haijinme@qq.com");
		userDao.save(u);
//		userService.saveUser(u);
	}

}
