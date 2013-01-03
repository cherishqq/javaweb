package com.java.base.generic.two;

//继承非泛型类示例
public class NonGenericcClass {
	public static void main(String args[]) {
		System.out.println("继承非泛型类的实现方法如下：");
		doNonGeneric<String> oa = new doNonGeneric<String>(
				"doNonGeneric类的值为: ", 125);
		System.out.print("\t" + oa.getOb());
		System.out.println(oa.getNum());
	}

}

class NonGeneric {// 创建父类对象，此类并不是泛型类
	double num;

	public NonGeneric(double n) {// 设置变量num的值等于传入的参数值
		num = n;
	}

	public NonGeneric() {// 设置变量num的默认值为0.0
		num = 0.0;
	}

	public double getNum() {// 返回变量num的当前值
		return num;
	}
}

class doNonGeneric<T> extends NonGeneric {// 定义一个继承于NonGeneric的子类。该类被声明为泛型类
	T ob;

	public doNonGeneric(T ob, double n) {
		super(n);// 将传入的参数值赋给父类
		this.ob = ob;// 将对数类型给自己的变量赋值
	}

	public T getOb() {
		return ob;
	}
}
