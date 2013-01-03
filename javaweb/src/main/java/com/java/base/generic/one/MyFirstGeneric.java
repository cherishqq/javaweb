package com.java.base.generic.one;

import java.util.Date;

public class MyFirstGeneric<T> {
	T t; //t的类型是T,现在不能具体确定它的类型，需要到创建对象时才能确定

	public MyFirstGeneric(T t) {// 创建该类的构造方法为T对象赋值
		this.t = t;
	}

	// 这个方法的返回类型也是T
	T getT() {
		return t;
	}

	// 显示T的类型
	void printType() {
		System.out.println("Type of T is:" + t.getClass().getName());
	}

	public static void main(String args[]) {
		// 声明一个String类型的Generic变量
		MyFirstGeneric<String> str;
		// 创建一个String类型的Generic对象
		str = new MyFirstGeneric<String>("这是一个简单的泛型实例");
		// 输出它的一些信息
		str.printType();
		String string = str.getT();
		System.out.println("\tstring=" + string);
		// 声明一个Date类型的Generic变量
		MyFirstGeneric<Date> sobj;
		// 创建一个Date类型的Generic对象
		sobj = new MyFirstGeneric<Date>(new Date());
		// 输出它的一些信息
		sobj.printType();
		String time = sobj.getT().toGMTString();
		System.out.println("\ttime=" + time);
	}

}
