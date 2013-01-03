package com.java.base.generic.one;

import java.util.Date;

//���ͷ�����ʹ��ʾ��
public class GenericMethod {
	// ���巺�ͷ�������һ����ʽ���������Ͳ���T������
	public static <T> void genericMethods(T t, char n) {
		T t1 = t; // �ֲ�����Ҳ���������Ͳ���T������
		System.out.println("[1] " + n + "�Ķ�������Ϊ��" + t1.getClass().getName());
	}

	public static <T> void genericMethods(T t) {
		System.out.println("\n[2] " + t + "�Ķ�������Ϊ��" + t.getClass().getName());
	}

	public static void main(String args[]) {
		Date date = new Date();
		Character k = new Character('A');
		// �����ֲ�ͬ�ķ������÷��ͷ���
		GenericMethod.<Character> genericMethods(k, 'B');
		genericMethods(date);
	}

}
