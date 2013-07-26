package com.javaweb.spring.annotation.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;


/**
 * http://localhost:8080/javaweb/service/BookService.wsdl
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
