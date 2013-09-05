package com.java.base.extend;

public class Test1 {
	
	
  /**
   * 
   * @author derek
   * @createtime: 2013-9-2 下午5:58:41 
   * @version  
   *  desc:
   */
	
	/**
	 * class 必须定义成static.. 否则在main 不能使用
	 */

	public static class A{
		
		public String name;
		
		public void print(){
			System.out.println("呵呵");
		}
		
	}
	
	public static class B extends A {
		
		public String age;
	}
	
	
	
	public static void main(String [] args){
		
		A	a = new Test1.A();
		B b = (B)a;
		// 不能向上转换,这是错的
		// throws java.lang.ClassCastException
		b.print();
		
		
	}

}
