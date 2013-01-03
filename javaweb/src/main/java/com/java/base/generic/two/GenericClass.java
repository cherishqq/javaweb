package com.java.base.generic.two;

import java.util.Date;

//�̳з�����
public class GenericClass {
	public static void main(String args[]) {
		System.out.println("�Է�����Ϊ�����ʵ�ַ������£�");
		// ��������Ķ�������Ҫ��������������Date���͸����࣬�Լ�ʹ��String����
		Child<Date, String> cd = new Child<Date, String>(new Date(),
				"��ǰϵͳ��ʱ��Ϊ: ");
		System.out.print("\t" + cd.getDob());
		System.out.println(cd.getOb());
	}

}

class Child<T, U> extends Father<T> {
	U u;

	public Child(T t1, U u1) {
		super(t1); // ���ݲ���������
		u = u1; // Ϊ�Լ��ĳ�Ա��ֵ
	}

	public U getDob() {
		return u;
	}
}

class Father<T> { // ����һ��������
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