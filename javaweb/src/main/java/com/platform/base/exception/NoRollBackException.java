package com.platform.base.exception;



/**
 * 
 * 
 * @author derek
 * @createtime: 2013-8-16 上午11:09:35 
 * @version  
 *  desc: 
 *  不回滚的异常,即在一个业务方法中,如果抛出异常, 那么是不回滚的。
 */
public class NoRollBackException  extends RuntimeException {
	
	
	public NoRollBackException() {
		super();
	}
	
	public NoRollBackException(String messages){
		super(messages);
	}

}
