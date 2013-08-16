package com.platform.sys;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


@Component
public class SystemInitializer  implements ApplicationContextAware{

	
	private static ApplicationContext ctx;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.ctx = applicationContext;
	}
	
	
	/**
	 * 一个系统初始化的时候有很多的入口,可以在这里统一做,这样比较好。。
	 * 想想一个系统初始化的时候都需要干什么呢...需要整合什么资源?
	 */
	
	@PostConstruct
	public void init(){
		System.err.println("系统初始化开始.....");
		
		Assert.notNull(ctx, "平台初始化错误");
		
		SpringBeanInvoke.setApplicationContext(ctx);
	}

}
