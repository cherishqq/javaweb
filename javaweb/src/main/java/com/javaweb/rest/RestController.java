package com.javaweb.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.model.User;
import com.javaweb.utils.StringUtil;


@Controller
@RequestMapping("/user")
public class RestController {
	
	/**
	 * @PathVariable Long id
	 * @return
	 */
	
	@RequestMapping(value="/show")
	@ResponseBody
	public String show(){
		System.out.println("show");
		return "pan";
	}
	
	
	@RequestMapping(value="/{userName}/{password}",method=RequestMethod.POST)
	@ResponseBody
	public RestResult findUserByNameAndPwd(@PathVariable String userName,@PathVariable String password){
		
		RestResult result = new RestResult<User>();
		User u = null;
		if(!StringUtil.isEmpty(userName) && !StringUtil.isEmpty(password)){
			if(userName.equals("derek") && password.equals("derek")){
				u = new User(userName,password,"haijinme@qq.com");
				result.setObject(u);
				return result;
			}else {
				result.setErrorCode(2);
				result.setMessage("userName or password is wrong");
				
			}
		}
		return result;
		
	}
	
	

}
