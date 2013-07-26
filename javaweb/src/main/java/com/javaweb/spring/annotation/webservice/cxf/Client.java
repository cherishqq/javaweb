package com.javaweb.spring.annotation.webservice.cxf;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javaweb.spring.annotation.webservice.cxf.bean.Person;
import com.javaweb.spring.annotation.webservice.cxf.server.HelloWorld;

public class Client {
	
	public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-cxf-client.xml");
        HelloWorld helloService = (HelloWorld) context.getBean("client");
        String response = helloService.sayHi("Peter");
        System.out.println(response);
        
        List<Person> person = helloService.getListPersons();
        
        System.out.println( person.size());
    }

}
