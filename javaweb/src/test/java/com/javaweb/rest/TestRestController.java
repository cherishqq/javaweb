package com.javaweb.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.junit.Test;

import com.javaweb.model.Photo;
import com.javaweb.utils.HttpClientFactory;
import com.javaweb.utils.HttpClientHelp;

public class TestRestController {
	
	//@Test
	public void show(){
		String url = "http://localhost:8080/javaweb/user/show";
		HttpResponse response = HttpClientHelp.makeRequest(url,"");
		InputStream is = null;
		try {
			if( response != null){
				is = response.getEntity().getContent();
				String s = streamToString(is);
				System.out.println(s);
			} else {
				System.out.println("response is null");
			}

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 *  right: {"id":0,"username":"derek","password":"derek","email":"haijinme@qq.com"}
	 *  wrong: 
	 */
	
	//@Test
	public void getUser() {
		String url = "http://localhost:8080/javaweb/user/derek/derek";
		HttpResponse response = HttpClientHelp.makeRequest(url,"");
		InputStream is = null;
		try {
			if( response != null){
				is = response.getEntity().getContent();
				String s = streamToString(is);
				System.out.println("response:" + s);
			} else {
				System.out.println("response is null");
			}

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//@Test
	public void getPhoto(){
		String url = "http://localhost:8080/javaweb/photo/getPictureList?recordCount=0&pictureId=1";
//		String url = "http://10.32.50.101:8080/javaweb/photo/getPictureList?recordCount=0&pictureId=1";
		HttpResponse response = HttpClientHelp.makeRequest(url,"");
		InputStream is = null;
		try {
			if( response != null){
				is = response.getEntity().getContent();
				String s = streamToString(is);
				System.out.println("response:" + s);
			} else {
				System.out.println("response is null");
			}

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testObject() {
		Photo p1 = new Photo(1l,"美女1","美女1","jpg",null,
				"http://api-smsdev.lab.rcch.ringcentral.com/restapi/v1.0/account/254844008/extension/254931008/message-store/324795560008/content/1",
				110l); 
		
		System.out.println(p1.toString());
	}
	
	
	
	 private static String streamToString(InputStream in) {
	        if (in == null) {
	            return null;
	        }

	        try {
	            BufferedReader rd = new BufferedReader(new InputStreamReader(in), 8192);

	            StringBuffer res = new StringBuffer();
	            try {
	                String line;
	                while ((line = rd.readLine()) != null) {
	                    res.append(line);
	                }
	            } catch (IOException e) {
	            }
	            return res.toString();
	        } catch (NullPointerException e) {
	            return null;
	        }
	    }
	
	
}
