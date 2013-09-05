package com.javaweb.platform.outerservice.rmi;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.platform.outerservice.rmi.RemoteCacheService;
import com.platform.sys.SystemInitializer;

public class ClientTest {
	private static Log log = LogFactory.getLog(ClientTest.class);

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-rmi-client.xml");
		RemoteCacheService hms = context.getBean("rmiClient", RemoteCacheService.class);
		log.info(hms.getMessage());
		
		
		SystemInitializer  si = hms.getSystemInitializer();
		
		HashMap<String,String> map = (HashMap<String,String>)si.map;
		
		System.out.println("size:" + map.size());
		
		Map<String,String> map2 =  si.getMap();
		
		System.out.println("size:" + map2.size());
		
		Map<String,String> map3 = si.getUserService().map;
		
		
		System.out.println("size:" + map3.size());
		
	//	ApplicationContext app = si.getApp();
		
	//	UserService us = (UserService)app.getBean("userService",UserService.class);
		
		
	//	us.queryUserByName("derek");
		
	}

}