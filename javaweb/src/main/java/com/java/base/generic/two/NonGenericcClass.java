package com.java.base.generic.two;

//�̳зǷ�����ʾ��
public class NonGenericcClass {
	public static void main(String args[]) {
		System.out.println("�̳зǷ������ʵ�ַ������£�");
		doNonGeneric<String> oa = new doNonGeneric<String>(
				"doNonGeneric���ֵΪ: ", 125);
		System.out.print("\t" + oa.getOb());
		System.out.println(oa.getNum());
	}

}

class NonGeneric {// ����������󣬴��ಢ���Ƿ�����
	double num;

	public NonGeneric(double n) {// ���ñ���num��ֵ���ڴ���Ĳ���ֵ
		num = n;
	}

	public NonGeneric() {// ���ñ���num��Ĭ��ֵΪ0.0
		num = 0.0;
	}

	public double getNum() {// ���ر���num�ĵ�ǰֵ
		return num;
	}
}

class doNonGeneric<T> extends NonGeneric {// ����һ���̳���NonGeneric�����ࡣ���౻����Ϊ������
	T ob;

	public doNonGeneric(T ob, double n) {
		super(n);// ������Ĳ���ֵ��������
		this.ob = ob;// ���������͸��Լ��ı�����ֵ
	}

	public T getOb() {
		return ob;
	}
}
