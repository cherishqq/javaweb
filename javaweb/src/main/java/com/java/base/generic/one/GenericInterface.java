package com.java.base.generic.one;

//泛型接口的使用示例
public class GenericInterface {
	public static void main(String args[]) {
		Double doubleArrays[] = { 56.5, 58.127, 56.2, 5.569, 825.0, 12.36,
				510.89 };// 创建一个Double类型的数组并初始化
		Character charArrays[] = { 'A', 'w', 'z', 'Z', 'b', 'u', 'x' };// 创建一个Character类型的数组并初始化
		ComparableElement<Double> iob = new ComparableElement<Double>(
				doubleArrays);// 创建Double类型的泛型对象
		ComparableElement<Character> cob = new ComparableElement<Character>(
				charArrays);// 创建Character类型的泛型对象
		// 调用MaxOrMin接口中的min()和max()
		System.out.println("在Integer数组中，求元素最大值，max= " + iob.max());
		System.out.println("在Integer数组中，求元素最小值为，min= " + iob.min());
		System.out.println("在Character数组中，求元素最大值，max= " + cob.max());
		System.out.println("在Character数组中，求元素最小值为，min=  " + cob.min());
	}

}

interface MaxOrMin<T extends Comparable<T>> {// 创建一个泛型接口
	// 定义两个泛型方法：min()和max()
	T min();

	T max();
}

// 声明一个ComparableElement类继承于Comparable类并实现MaxOrMin接口
class ComparableElement<T extends Comparable<T>> implements MaxOrMin<T> {
	T[] mm;

	ComparableElement(T[] ob) {
		mm = ob;
	}

	public T min() {// 重写MaxOrMin接口中的min()方法，求出数组中的最小值
		T t = mm[0];
		for (int i = 1; i < mm.length; ++i)
			if (mm[i].compareTo(t) < 0)
				t = mm[i];
		return t;
	}

	public T max() {// 重写MaxOrMin接口中的max()方法，求出数组中的最大值
		T t = mm[0];
		for (int i = 1; i < mm.length; ++i)
			if (mm[i].compareTo(t) > 0)
				t = mm[i];
		return t;
	}
}
