package com.platform.sys;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.Transient;

import org.hibernate.annotations.Persister;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.javaweb.web.UserService;


@Component
public class SystemInitializer  implements ApplicationContextAware , Serializable{

	
	private static ApplicationContext ctx;
	
	
	@Autowired
//	@Qualifier("userService")
	private UserService userService;
	
	public UserService getUserService(){
		return userService;
	}
	
	
	
	public static Map<String,String> map = new HashMap<String,String>();
	
	public  Map<String,String> map2 = new HashMap<String,String>();
	
	static {
		map.put("1", "2");
		map.put("2", "3");
	}
	
	public Map<String,String> getMap(){
		return map2;
	}
	
	
	
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
		
		map2.put("1", "2");
		map2.put("2", "3");
		
		SpringBeanInvoke.setApplicationContext(ctx);
	}
	
	
	public Object getMessage(){
		return  new Object();
	}
	
	
	public ApplicationContext getApp(){
		return this.ctx;
	}
	
	

}
