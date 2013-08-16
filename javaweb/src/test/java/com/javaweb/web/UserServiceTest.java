package com.javaweb.web;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javaweb.dao.UserDao;
import com.javaweb.model.User;
import com.platform.sys.SpringBeanInvoke;


/**
 * 
 * @author derek
 * @createtime: 2013-8-16 上午9:17:46 
 * @version  
 *  desc:
 *  在这里碰到一个问题:
 *  	访问不到application.xml
 *     直接用application.xml 访问不到.. 
 *     classpath*:/application.xml 可以访问的到,但是bean没有被初始化,不知道为何
 *     classpath:/application.xml  也访问不到
 *     
 *     源代码配置文件和源代码在classes文件夹下，而测试代码配置文件和测试代码在testclasses文件夹下，所以你想进行测试，那么你需要拥有独立的一份测试文件，这样的 
 *	好处是测试环境与开发环境有时候可能不同，需要做一些特殊的配置，这样你就需要自己的独立的一份配置文件，放在test/resources目录下即可，这样在test/java下编写测试类，只需要这样写即可。 
 *		new ClassPathXmlApplicationContext("testApplicationContext.xml") 
 *		"testApplicationContext.xml" 这样书写的前提是你的测试配置文件是直接放在test/resource目录下，如果这个配置文件上还存在其他父目录，只需要直接书写上即可。 
*		如果你不想自己拥有独立的一份配置文件，而是使用源代码中配置的配置文件，可以使用 
*		FileSystemXmlApplicationContext，书写配置文件的绝对路径或相对与项目的相对路径也可以。
 *     
 *      可以更改下 test/java 的 output folder
 *   
 *     所以采用 新建 test/resources 在下面配置
 */

public class UserServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	
	
	
//	private UserService userService;
	
	private UserDao userDao;
	
	
	//@Before
	public void setUp() {
		
		String s = System.getProperty("java.class.path");

			
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:/applicationContext-test.xml");		
	   String [] ss = ctx.getBeanDefinitionNames();
		System.out.println(" length:" + ss.length);
		
		userDao = (UserDao) ctx.getBean("userDao");
	}
	
	//@Test
	public void save() {
		User user = new User("derek","123","haijinme@qq.com");
		System.out.println("hehe");
		userDao.save(user);
	}
	
	@Test
	public void dd(){
		System.out.println("hehe");
	}
	
	
	//@Test
	public void queryUserByName(){
		
		UserService o = (UserService)SpringBeanInvoke.getBean("userService");
		System.out.println(o);
		System.out.println(o.getString());
	}
	

}
