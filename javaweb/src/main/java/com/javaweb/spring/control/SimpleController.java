package com.javaweb.spring.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {
	
	private Logger logger = LoggerFactory.getLogger(SimpleController.class);

	@RequestMapping("/simple" )
	public @ResponseBody String simple() {
		
    	logger.debug("begin SimpleController");
		return "Hello world!";
	}
}
