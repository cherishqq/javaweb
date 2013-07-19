package com.javaweb.spring.annotation;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AnnotationTest implements ApplicationContextAware{
	
	
	@PostConstruct
	public void pp(){
		
		System.out.println("*********begin**************");
		System.out.println("*********init**************");
		System.out.println("*********end**************");
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		
	}

}
