package com.javaweb.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaweb.model.Photo;


@Controller
@RequestMapping("/photo")
public class PhotoRestController {
	
	@RequestMapping(value="/getPictureList",method=RequestMethod.GET )
	@ResponseBody
	public RestResult getPictureList(){
		
		Photo p1 = new Photo(1l,"美女1","美女1","jpg",null,
				"http://api-smsdev.lab.rcch.ringcentral.com/restapi/v1.0/account/254844008/extension/254931008/message-store/324795560008/content/1",
				110l);
		
		Photo p2 = new Photo(1l,"美女1","美女1","jpg",null,
				"http://api-smsdev.lab.rcch.ringcentral.com/restapi/v1.0/account/254844008/extension/254931008/message-store/324795560008/content/1",
				110l);
		
		Photo p3 = new Photo(1l,"美女1","美女1","jpg",null,
				"http://api-smsdev.lab.rcch.ringcentral.com/restapi/v1.0/account/254844008/extension/254931008/message-store/324795560008/content/1",
				110l);
		
		Photo p4 = new Photo(1l,"美女1","美女1","jpg",null,
				"http://api-smsdev.lab.rcch.ringcentral.com/restapi/v1.0/account/254844008/extension/254931008/message-store/324795560008/content/1",
				110l);
		
		Photo p5 = new Photo(1l,"美女1","美女1","jpg",null,
				"http://api-smsdev.lab.rcch.ringcentral.com/restapi/v1.0/account/254844008/extension/254931008/message-store/324795560008/content/1",
				110l);
		
		List pList = new ArrayList();
				
		pList.add(p1);
		pList.add(p2);
		pList.add(p3);
		pList.add(p4);
		pList.add(p5);
		
		RestResult rr = new RestResult();
		rr.setSuccess(true);
		rr.setMessage("");
		rr.setObject(pList);
		return rr;
	}

}
