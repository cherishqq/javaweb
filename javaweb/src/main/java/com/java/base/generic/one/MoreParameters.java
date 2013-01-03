package com.java.base.generic.one;

public class MoreParameters<T, V> {
	T t;

	V v;

	// 构造方法也可以使用这两个类型参数
	MoreParameters(T t1, V v1) {
		t = t1;
		v = v1;
	}

	// 显示T和V的类型
	void printTypes() {
		System.out.println("参数T的对象类型为： " + t.getClass().getName());
		System.out.println("参数V的对象类型为： " + v.getClass().getName());
	}

	T getT() {
		return t;
	}

	V getV() {
		return v;
	}

	public static void main(String args[]) {
		MoreParameters<Integer, Double> tv; // 指定类型参数的实际类型
		// 构造方法中需要再次指定类型参数，同时还要传递实际参数
		tv = new MoreParameters<Integer, Double>(100, 12.56);
		tv.printTypes();
		int num = tv.getT();
		System.out.println("num变量中的值为: " + num);
		double dou = tv.getV();
		System.out.println("dou变量中的值为:" + dou);
	}

}
