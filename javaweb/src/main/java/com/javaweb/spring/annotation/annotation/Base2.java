package com.javaweb.spring.annotation.annotation;

import org.springframework.stereotype.Component;


@Component
public class Base2  implements IBase{

	@Override
	public void init() {
		System.out.println(" **** init 2..");
	}

}
