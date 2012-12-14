package com.javaweb.rest;

public abstract class RestRequest {
	
	public static enum HttpMethod {
		GET, POST, PUT, DELETE
	}
	
	private HttpMethod mMethod;
	
	public RestRequest(HttpMethod method){
		mMethod = method;
	}
	
	/**
	 * called on request completion
	 */
	public abstract void onCompletion();
	
	
	

}
