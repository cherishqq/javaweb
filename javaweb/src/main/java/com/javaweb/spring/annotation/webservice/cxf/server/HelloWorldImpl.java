package com.javaweb.spring.annotation.webservice.cxf.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.javaweb.spring.annotation.webservice.cxf.bean.Person;


/**
 * 
 * desc: 
 *     url: http://localhost:8080/javaweb/service/HelloWorldImpl?wsdl
 *     config: spring-cxf.xml
 * @author derek
 * @createtime: 2013-7-26 上午11:37:13 
 * @version  
 *
 */

@WebService( endpointInterface = "com.javaweb.spring.annotation.webservice.cxf.server.HelloWorld")
public class HelloWorldImpl  implements HelloWorld{

	@Override
	public String sayHi(String text) {
		System.out.println(" sayHi called");
		return "Hello" + text;
	}

	@Override
	public List<Person> getListPersons() {
		
		List<Person> persons = new ArrayList<Person>();
		
		persons.add(new Person("张三","杭州市", 24));
		persons.add(new Person("李四","杭州市", 24));
		persons.add(new Person("王五","杭州市", 24));
		
		return persons;
	}

	@Override
	public Map<String, Person> getMapPersons() {
		
		Map<String, Person> map = new HashMap<String, Person>();
		map.put("1", new Person("Li Lei", "杭州市", 14));
		map.put("2", new Person("Han Meimei", "武汉市", 13));
		map.put("3", new Person("Jim Green", "上海市", 12));
		
		return map;
	}
	

}
