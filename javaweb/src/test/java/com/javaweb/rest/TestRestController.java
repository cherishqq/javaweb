package com.javaweb.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.junit.Test;

import com.javaweb.utils.HttpClientFactory;
import com.javaweb.utils.HttpClientHelp;

public class TestRestController {
	
	@Test
	public void show(){
		String url = "http://localhost:8080/javaweb/user/show.do";
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
