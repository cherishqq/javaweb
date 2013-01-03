package com.java.base.generic.one;

import java.util.Date;

public class MyFirstGeneric<T> {
	T t; //t��������T,���ڲ��ܾ���ȷ���������ͣ���Ҫ����������ʱ����ȷ��

	public MyFirstGeneric(T t) {// ��������Ĺ��췽��ΪT����ֵ
		this.t = t;
	}

	// ��������ķ�������Ҳ��T
	T getT() {
		return t;
	}

	// ��ʾT������
	void printType() {
		System.out.println("Type of T is:" + t.getClass().getName());
	}

	public static void main(String args[]) {
		// ����һ��String���͵�Generic����
		MyFirstGeneric<String> str;
		// ����һ��String���͵�Generic����
		str = new MyFirstGeneric<String>("����һ���򵥵ķ���ʵ��");
		// �������һЩ��Ϣ
		str.printType();
		String string = str.getT();
		System.out.println("\tstring=" + string);
		// ����һ��Date���͵�Generic����
		MyFirstGeneric<Date> sobj;
		// ����һ��Date���͵�Generic����
		sobj = new MyFirstGeneric<Date>(new Date());
		// �������һЩ��Ϣ
		sobj.printType();
		String time = sobj.getT().toGMTString();
		System.out.println("\ttime=" + time);
	}

}
