package com.javaweb.platform.outerservice.rmi;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerTest {
	private static Log log = LogFactory.getLog(ServerTest.class);

	public static void main(String[] args) {
		//初始化工作只能运行一次;运行多次的话，会启动多个服务
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-rmi-server.xml");
		log.info("hello commons-logging!");
	}
}
