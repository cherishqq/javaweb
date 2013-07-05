package com.java.base.generic.one;


/**
 * 
 * desc: 
 * @author Administrator
 * @createtime: 2013-7-4 上午9:19:14 
 * @version  
 *
 */

public class BoundedType {
	public static void main(String args[]) {
		System.out.println("�н����ͳ���ʾ�����£�");
		Integer inums[] = { 1, 2, 3, 4, 5 };//����һ��Integer���͵�����
		MyTypes<Integer> iobj = new MyTypes<Integer>(inums);//ʹ�÷��Ͷ���
		System.out.println("\tint����ݵ���ͽ��Ϊ��" + iobj.summation());
		Double dnums[] = { 1.1, 2.2, 3.3, 4.4, 5.5 };
		MyTypes<Double> dobj = new MyTypes<Double>(dnums);//����һ��Double���͵���
		System.out.println("\n\tdouble����ݵ���ͽ��Ϊ��" + dobj.summation());
	}
}
class MyTypes<T extends Number> {
	T[] nums;//����һ��Number�������
	MyTypes(T[] obj) {//Ϊ�����鸳ֵ
		nums = obj;
	}
	double summation() {//�Բ�������������
		double sum = 0.0;
		for (int i = 0; i < nums.length; ++i)
			sum += nums[i].doubleValue(); //��Number�������еĶ���ת����double���Ͳ�����������
		return sum ;
	}
}