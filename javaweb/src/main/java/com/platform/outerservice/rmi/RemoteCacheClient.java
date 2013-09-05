package com.platform.outerservice.rmi;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component
public class RemoteCacheClient {
	

	
	@Autowired
	@Qualifier("cachePoolBean")
	private RemoteCacheService remoteCacheService;
	
	@PostConstruct
	public void run(){
		String message = remoteCacheService.getMessage();
		System.out.println("*******************" + message);
	}
	

	

}
