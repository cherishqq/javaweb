package com.java.base.generic.two;

import com.java.base.generic.one.MyFirstGeneric;

//泛型运行时类型识别 1
public class GenericRTTI {
	public static void main(String args[]) {
		MyFirstGeneric<Double> dou = new MyFirstGeneric<Double>(100.0);
		MyFirstGeneric<String> str = new MyFirstGeneric<String>("hellow");
		if (dou instanceof MyFirstGeneric)//判断dou是否是MyFirstGeneric类的实例
			System.out
					.println("MyFirstGeneric<Integer> object is instance of MyFirstGeneric");
		if (dou instanceof MyFirstGeneric<?>)//判断dou是否是MyFirstGeneric<?>泛型类的实例
			System.out
					.println("MyFirstGeneric<Integer> object is instance of MyFirstGeneric<?>");
		if (dou.getClass() == str.getClass())//判断这两个对象运行时的类是否相等
			System.out
					.println("MyFirstGeneric<Integer> class equals MyFirstGeneric<String> class");
	}

}
