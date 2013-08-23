package com.javaweb.easymock;

import javax.servlet.http.HttpServletRequest;

import org.easymock.EasyMock;

public class Test {
	
	public static void  main(String [] args){
		HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
		EasyMock.expect(request.getParameter("username")).andReturn("derek").anyTimes();
		EasyMock.replay(request);
		EasyMock.verify(request); 
		
		System.out.println(request.getParameter("username"));
		
	}
	

}
