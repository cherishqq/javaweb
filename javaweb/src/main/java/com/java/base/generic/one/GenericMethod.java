package com.java.base.generic.one;

import java.util.Date;

//泛型方法的使用示例
public class GenericMethod {
	// 定义泛型方法，有一个形式参数用类型参数T来定义
	public static <T> void genericMethods(T t, char n) {
		T t1 = t; // 局部变量也可以用类型参数T来定义
		System.out.println("[1] " + n + "的对象类型为：" + t1.getClass().getName());
	}

	public static <T> void genericMethods(T t) {
		System.out.println("\n[2] " + t + "的对象类型为：" + t.getClass().getName());
	}

	public static void main(String args[]) {
		Date date = new Date();
		Character k = new Character('A');
		// 用两种不同的方法调用泛型方法
		GenericMethod.<Character> genericMethods(k, 'B');
		genericMethods(date);
	}

}
