package com.javaweb.spring.annotation.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public interface IBookService {
	
	@WebMethod(operationName = "getBook")
	public String getBook();

}
