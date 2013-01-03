package com.java.base.generic.one;

import java.util.*;

//ͨ�������ʹ��ʾ��
public class Wildcard {
	public static void main(String args[]) {
		Integer Ints[] = { 1, 2, 3, 4, 5 };// ����Integer�������鲢��ʼ��
		Symbol<Integer> integer = new Symbol<Integer>(Ints);// ����Integer���Ͷ���
		Double Dous[] = { 1.1, 2.2, 3.3, 4.4, 5.5 };// ����Double�������鲢��ʼ��
		Symbol<Double> douObject = new Symbol<Double>(Dous);// ����Double���Ͷ���
		douObject.printMessage(integer); // integer��douObject�����Ͳ���ͬ
		List<String> list1 = new ArrayList<String>();// ����һ��List���Ͷ�����ӵ�Ԫ����String���͵�
		// ��List���������Ԫ��
		list1.add("String");
		list1.add("���");
		list1.add("�������");
		List<?> list2 = list1;// ����һ��List�б���Ԫ��Ϊ�κ����͵ķ��Ͷ��󣬲���list1����list2
		System.out.println("�б�List����list2�е�Ԫ�����£�");
		for (int i = 0; i < list2.size(); i++) {
			System.out.println("\t" + list2.get(i));// ��list2�е�Ԫ�����
		}
	}

}

class Symbol<T extends Number> {
	T[] nums;

	Symbol(T[] obj) {
		nums = obj;
	}

	void printMessage(Symbol<?> sb) { // ����ʹ��������ͨ���
		System.out.println("�����Ͳ���sb�Ĳ��������ǣ�" + sb.getClass().getName());
	}

}