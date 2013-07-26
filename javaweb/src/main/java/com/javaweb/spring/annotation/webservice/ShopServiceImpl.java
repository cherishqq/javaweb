package com.javaweb.spring.annotation.webservice;

import javax.jws.WebService;



/**
 * 
 * @author derek
 * @createtime: 2013-7-26 下午3:36:25 
 * @version  
 *  desc: 
 *  http://localhost:8080/javaweb/service/ShopServiceImpl?wsdl
 *  need config in spring-xfire.xml
 */


@WebService(endpointInterface = "com.javaweb.spring.annotation.webservice.IShopService")
public class ShopServiceImpl implements IShopService{
	@Override
	public String sell() {
		return "fruit";
	}

}
