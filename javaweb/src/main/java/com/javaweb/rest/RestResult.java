package com.javaweb.rest;

import java.io.Serializable;

public class RestResult<T extends Object> implements Serializable{
	
	
	public RestResult(){
	}
	
	private boolean success = true;

	private int errorCode = RestErrorCodes.OK;
	private String message;
	private T object;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
	
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
	
	

}
