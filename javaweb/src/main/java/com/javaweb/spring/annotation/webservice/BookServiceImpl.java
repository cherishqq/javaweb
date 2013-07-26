package com.javaweb.spring.annotation.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;


/**
 * http://localhost:8080/javaweb/service/BookService.wsdl
 * 
 * 这样定义了之后,不需要在 xml或者其他地方增加其他配置
 */


import org.springframework.stereotype.Component;


@Component
@WebService(endpointInterface="com.javaweb.spring.annotation.webservice.IBookService",
		serviceName="BookService")
public class BookServiceImpl  implements IBookService{
	
	
	public String getBook() {
			return "derek's book";
	}
}
