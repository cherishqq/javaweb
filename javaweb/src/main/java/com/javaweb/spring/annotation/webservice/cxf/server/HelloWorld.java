package com.javaweb.spring.annotation.webservice.cxf.server;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.javaweb.spring.annotation.webservice.cxf.bean.Person;
import com.javaweb.spring.annotation.webservice.cxf.util.MapAdapter;


@WebService
public interface HelloWorld {
	
	
	/**
	 * 基本数据类型
	 * @param text
	 * @return
	 */
	public String sayHi(String text);
	
	
	
	/**
	 * List JavaBean test
	 * @return
	 */
	public List<Person> getListPersons();
	
	
	/**
	 * Map test .. cxf not support map. need define 转换器
	 * @return
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	public Map<String,Person> getMapPersons();
	

}
