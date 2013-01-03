package com.java.base.generic.one;

public class MoreParameters<T, V> {
	T t;

	V v;

	// ���췽��Ҳ����ʹ�����������Ͳ���
	MoreParameters(T t1, V v1) {
		t = t1;
		v = v1;
	}

	// ��ʾT��V������
	void printTypes() {
		System.out.println("����T�Ķ�������Ϊ�� " + t.getClass().getName());
		System.out.println("����V�Ķ�������Ϊ�� " + v.getClass().getName());
	}

	T getT() {
		return t;
	}

	V getV() {
		return v;
	}

	public static void main(String args[]) {
		MoreParameters<Integer, Double> tv; // ָ�����Ͳ�����ʵ������
		// ���췽������Ҫ�ٴ�ָ�����Ͳ�����ͬʱ��Ҫ����ʵ�ʲ���
		tv = new MoreParameters<Integer, Double>(100, 12.56);
		tv.printTypes();
		int num = tv.getT();
		System.out.println("num�����е�ֵΪ: " + num);
		double dou = tv.getV();
		System.out.println("dou�����е�ֵΪ:" + dou);
	}

}
