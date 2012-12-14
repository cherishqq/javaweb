package com.javaweb.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user")
public class RestController {
	
	/**
	 * @PathVariable Long id
	 * @return
	 */
	
	@RequestMapping(value="/show",method=RequestMethod.POST)
	@ResponseBody
	public String show(){
		System.out.println("show");
		return "pan";
	}
	
	

}
