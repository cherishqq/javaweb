package com.java.base.generic.one;

//有界类型程序示例
public class BoundedType {
	public static void main(String args[]) {
		System.out.println("有界类型程序示例如下：");
		Integer inums[] = { 1, 2, 3, 4, 5 };//创建一个Integer类型的数组
		MyTypes<Integer> iobj = new MyTypes<Integer>(inums);//使用泛型对象
		System.out.println("\tint型数据的求和结果为：" + iobj.summation());
		Double dnums[] = { 1.1, 2.2, 3.3, 4.4, 5.5 };
		MyTypes<Double> dobj = new MyTypes<Double>(dnums);//创建一个Double类型的数
		System.out.println("\n\tdouble型数据的求和结果为：" + dobj.summation());
	}
}
class MyTypes<T extends Number> {
	T[] nums;//定义一个Number类的数组
	MyTypes(T[] obj) {//为该数组赋值ֵ
		nums = obj;
	}
	double summation() {//对参数进行求和运算
		double sum = 0.0;
		for (int i = 0; i < nums.length; ++i)
			sum += nums[i].doubleValue(); //将Number类数组中的对象转换成double类型并依次相加求和
		return sum ;
	}
}