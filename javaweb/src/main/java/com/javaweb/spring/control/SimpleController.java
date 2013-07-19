package com.javaweb.spring.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

	@RequestMapping("/simple")
	public @ResponseBody String simple() {
		
		System.out.println("hhe");
		return "Hello world!";
	}
}
