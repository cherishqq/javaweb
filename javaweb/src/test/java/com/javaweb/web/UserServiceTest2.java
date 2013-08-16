package com.javaweb.web;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 
 * @author derek
 * @createtime: 2013-8-15 下午4:40:35 
 * @version  
 *  desc: use spring test
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest2  extends  AbstractJUnit4SpringContextTests  {
	
	
	

}
