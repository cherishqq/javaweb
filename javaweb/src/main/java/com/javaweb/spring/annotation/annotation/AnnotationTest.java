package com.javaweb.spring.annotation.annotation;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AnnotationTest implements ApplicationContextAware{
	
	
	@PostConstruct
	public void pp(){
		
		car.setName("bb");
		System.err.println("name:" + car.getName());
		
		
		System.out.println("*********begin111**************");
		System.out.println("*********init``11**************");
		System.out.println("*********end11**************");
		
	
	}
	
	
	/**
	 * car must use @Component tag
	 */
	@Autowired
	private Car car;
	
	
	@Autowired
	public void setCC(List<IBase > bases){
		
		System.out.println(" setCC init ");
		
		for( IBase  base: bases){
			base.init();
		}
		
		
		
	}
	
	

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		
	}

}
