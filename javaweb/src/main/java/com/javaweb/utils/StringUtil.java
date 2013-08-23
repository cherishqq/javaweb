package com.javaweb.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 
 * @author derek.pan
 *
 */
public class StringUtil {
	
	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);
	
	
	public static boolean isEmpty(String str){
		return str == null || str.equals("");
	}
	
	// 表达式替换。。比如经常有个场景就是在数据库配置 {xxx} .. 然后要具体的值去替换
	
	/**
	 * Str replace
	 * @param url
	 * @param args
	 * @return
	 */
	public static String urlReplace(String url,Map<String,String> args){
		if (isEmpty(url)) {
			return url;
		}
		
		 for (String key : args.keySet()) { 
			 url = url.replaceAll("\\{"+key+"\\}", args.get(key).toString()); 
		 } 
		
		return url;
	}
	
	
	/**
	 * 
	 * @param url
	 * @param values
	 */
	public static String urlReplace(String url,String ... values){
		
		if(StringUtils.isEmpty(url)){
			return null;
		}
		
		if(values==null||values.length<=0){
			logger.info("the parameter is empty");
			return url;
		}
		
		
		Pattern p = Pattern.compile("\\{[^}]*\\}");
		Matcher m = p.matcher(url);
		int count=0;
		String temp = url;
		while(m.find()){
			if(values.length<(count+1)){
				logger.warn("the parameter is not match");
				return temp;
			}
			url = m.replaceFirst(values[count++]);
			m = p.matcher(url);
		}
		if(values.length != count){
			logger.warn("the parameter is not match");
			return temp;
		}
		return url;
	}
	
	
	
	public static void main(String [] args){
		//System.out.println(urlReplace("/restapi/v1.0/developer/{developerId}/application/{application}/{developerId}","1231312",""));
	
		String str = "/restapi/v1.0/developer/{developerId}/application/{application}/{developerId}";
		//System.out.println(getStrNumber(str,"\\{"));
		
		System.out.println(urlReplace(str,"aa","bb","cc"));
	}
	

}
