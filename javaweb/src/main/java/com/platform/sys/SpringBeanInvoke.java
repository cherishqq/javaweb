package com.platform.sys;

import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

public class SpringBeanInvoke {
	
	private static ApplicationContext ctx;

	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

	public static void setApplicationContext(ApplicationContext ctx) {
		SpringBeanInvoke.ctx = ctx;
	}
	
	
	public static Object getBean(String beanName){
		Assert.notNull(ctx, "平台初始化出错");
		return ctx.getBean(beanName);
	}
	
	
	
	
	
	
	

}
