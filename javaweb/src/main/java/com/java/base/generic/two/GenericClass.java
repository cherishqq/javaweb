package com.java.base.generic.two;

import java.util.Date;

//继承泛型类
public class GenericClass {
	public static void main(String args[]) {
		System.out.println("以泛型类为父类的实现方法如下：");
		// 创建子类的对象，它需要传递两个参数，Date类型给父类，自己使用String类型
		Child<Date, String> cd = new Child<Date, String>(new Date(),
				"当前系统的时间为: ");
		System.out.print("\t" + cd.getDob());
		System.out.println(cd.getOb());
	}

}

class Child<T, U> extends Father<T> {
	U u;

	public Child(T t1, U u1) {
		super(t1); // 传递参数给父类
		u = u1; // 为自己的成员赋值
	}

	public U getDob() {
		return u;
	}
}

class Father<T> { // 定义一个泛型类
	T t;

	public Father(T t) {
		this.t = t;
	}

	public Father() {
		t = null;
	}

	public T getOb() {
		return t;
	}
}