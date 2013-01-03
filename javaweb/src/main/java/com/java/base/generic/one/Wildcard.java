package com.java.base.generic.one;

import java.util.*;

//通配符参数使用示例
public class Wildcard {
	public static void main(String args[]) {
		Integer Ints[] = { 1, 2, 3, 4, 5 };// 定义Integer对象数组并初始化
		Symbol<Integer> integer = new Symbol<Integer>(Ints);// 创建Integer泛型对象
		Double Dous[] = { 1.1, 2.2, 3.3, 4.4, 5.5 };// 定义Double对象数组并初始化
		Symbol<Double> douObject = new Symbol<Double>(Dous);// 创建Double泛型对象
		douObject.printMessage(integer); // integer和douObject的类型不相同
		List<String> list1 = new ArrayList<String>();// 定义一个List泛型对象，添加的元素是String类型的
		// 向List对象中添加元素
		list1.add("String");
		list1.add("你好");
		list1.add("世界真大啊");
		List<?> list2 = list1;// 声明一个List列表中元素为任何类型的泛型对象，并将list1赋给list2
		System.out.println("列表List对象list2中的元素如下：");
		for (int i = 0; i < list2.size(); i++) {
			System.out.println("\t" + list2.get(i));// 将list2中的元素输出
		}
	}

}

class Symbol<T extends Number> {
	T[] nums;

	Symbol(T[] obj) {
		nums = obj;
	}

	void printMessage(Symbol<?> sb) { // 这里使用了类型通配符
		System.out.println("对象型参数sb的参数类型是：" + sb.getClass().getName());
	}

}