package com.java.base.generic.two;

//强制类型转换
public class CastType {
	public static void main(String args[]) {
		Father<Double> father = new Father<Double>(1.0);
		Child<Double, String> child = new Child<Double, String>(200.0, "中国您好");
		// 试图将子类对象转换成父类，正确
		if ((Father<Double>) child instanceof Father)
			System.out.println("子类对象转换成父亲对象.");
		// 试图将父类对象转换成子类，错误
		try {
			if ((Child<Double, String>) father instanceof Child)
				System.out.println("父类对象转换成子亲对象.");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("发生异常的原因：父类对象不能强制转换成子亲对象.");

		}
	}
}