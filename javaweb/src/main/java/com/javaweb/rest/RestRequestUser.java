package com.javaweb.rest;

import com.javaweb.common.Constants;

public class RestRequestUser extends RestRequest{
	
	
	
	private static final HttpMethod HTTP_METHOD = RestRequest.HttpMethod.GET;
	private static final String REQUEST_URL_PATH_TEMPLATE = Constants.REST_REQUEST_PATH_MAIN + "user/show";
	
	private String mRequestUrlPath;
	private long id;
	private String userName;
	private String password;
	private String email;

	public RestRequestUser(HttpMethod method) {
		super(method);
		// TODO Auto-generated constructor stub
	}
	
	public RestRequestUser(long id){
		super(HTTP_METHOD);
		this.id = id;
	}
	
	public RestRequestUser(String email){
		super(HTTP_METHOD);
		this.email = email;
	}
	
	

	@Override
	public void onCompletion() {
		// TODO Auto-generated method stub
		
	}

}
