package com.platform.outerservice.rmi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.sys.SystemInitializer;



public class RemoteCacheServiceImpl  implements RemoteCacheService{

	@Autowired
	private SystemInitializer sysInit;
	
    @Override
	public String getMessage() {
		return "xxoo";
	}

	@Override
	public SystemInitializer getSystemInitializer() {
		// TODO Auto-generated method stub
		return sysInit;
	}

}
