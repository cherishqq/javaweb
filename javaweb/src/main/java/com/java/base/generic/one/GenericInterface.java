package com.java.base.generic.one;

//���ͽӿڵ�ʹ��ʾ��
public class GenericInterface {
	public static void main(String args[]) {
		Double doubleArrays[] = { 56.5, 58.127, 56.2, 5.569, 825.0, 12.36,
				510.89 };// ����һ��Double���͵����鲢��ʼ��
		Character charArrays[] = { 'A', 'w', 'z', 'Z', 'b', 'u', 'x' };// ����һ��Character���͵����鲢��ʼ��
		ComparableElement<Double> iob = new ComparableElement<Double>(
				doubleArrays);// ����Double���͵ķ��Ͷ���
		ComparableElement<Character> cob = new ComparableElement<Character>(
				charArrays);// ����Character���͵ķ��Ͷ���
		// ����MaxOrMin�ӿ��е�min()��max()
		System.out.println("��Integer�����У���Ԫ�����ֵ��max= " + iob.max());
		System.out.println("��Integer�����У���Ԫ����СֵΪ��min= " + iob.min());
		System.out.println("��Character�����У���Ԫ�����ֵ��max= " + cob.max());
		System.out.println("��Character�����У���Ԫ����СֵΪ��min=  " + cob.min());
	}

}

interface MaxOrMin<T extends Comparable<T>> {// ����һ�����ͽӿ�
	// �����������ͷ�����min()��max()
	T min();

	T max();
}

// ����һ��ComparableElement��̳���Comparable�ಢʵ��MaxOrMin�ӿ�
class ComparableElement<T extends Comparable<T>> implements MaxOrMin<T> {
	T[] mm;

	ComparableElement(T[] ob) {
		mm = ob;
	}

	public T min() {// ��дMaxOrMin�ӿ��е�min()��������������е���Сֵ
		T t = mm[0];
		for (int i = 1; i < mm.length; ++i)
			if (mm[i].compareTo(t) < 0)
				t = mm[i];
		return t;
	}

	public T max() {// ��дMaxOrMin�ӿ��е�max()��������������е����ֵ
		T t = mm[0];
		for (int i = 1; i < mm.length; ++i)
			if (mm[i].compareTo(t) > 0)
				t = mm[i];
		return t;
	}
}
