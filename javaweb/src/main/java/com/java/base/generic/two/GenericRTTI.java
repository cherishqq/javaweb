package com.java.base.generic.two;

import com.java.base.generic.one.MyFirstGeneric;

//��������ʱ����ʶ�� 1
public class GenericRTTI {
	public static void main(String args[]) {
		MyFirstGeneric<Double> dou = new MyFirstGeneric<Double>(100.0);
		MyFirstGeneric<String> str = new MyFirstGeneric<String>("hellow");
		if (dou instanceof MyFirstGeneric)//�ж�dou�Ƿ���MyFirstGeneric���ʵ��
			System.out
					.println("MyFirstGeneric<Integer> object is instance of MyFirstGeneric");
		if (dou instanceof MyFirstGeneric<?>)//�ж�dou�Ƿ���MyFirstGeneric<?>�������ʵ��
			System.out
					.println("MyFirstGeneric<Integer> object is instance of MyFirstGeneric<?>");
		if (dou.getClass() == str.getClass())//�ж���������������ʱ�����Ƿ����
			System.out
					.println("MyFirstGeneric<Integer> class equals MyFirstGeneric<String> class");
	}

}
