package com.java.base.generic.one;

/**
 * Created by IntelliJ IDEA. User: leizhimin Date: 2007-9-18 Time: 16:09:37
 * �������꣬�÷���ʵ�������ӡ
 */
public class CoorGeneric {
	static void showXY(GenericCoords<?> c) {// ���巺�ͷ���������ͨ������ò���C������ΪXL����ʾ��XL�еı���X��Y��ֵ
		System.out.println("X Y ������:");
		for (int i = 0; i < c.gcs.length; i++) {
			System.out.println(c.gcs[i].x + "  " + c.gcs[i].y);
		}
	}

	static void showXYZ(GenericCoords<? extends XYZ> c) {
		System.out.println("X Y Z ������:");
		// ���巺�ͷ���������ͨ������ò���C������ΪXYZ����ʾ��XYZ�еı���X��Y��Z��ֵ
		for (int i = 0; i < c.gcs.length; i++) {
			System.out.println(c.gcs[i].x + "  " + c.gcs[i].y + "  "
					+ c.gcs[i].z);
		}
	}

	static void showAll(GenericCoords<? extends XYZT> c) {
		System.out.println("X Y Z T ������:");
		// ���巺�ͷ���������ͨ������ò���C������ΪXYZT����ʾ��XYZT�еı���X��Y��Z��T��ֵ
		for (int i = 0; i < c.gcs.length; i++) {
			System.out.println(c.gcs[i].x + "  " + c.gcs[i].y + "  "
					+ c.gcs[i].z + "  " + c.gcs[i].t);
		}
	}

	public static void main(String args[]) {
		XY td[] = { new XY(0, 0), new XY(7, 9), new XY(18, 4), new XY(-1, -23) };
		GenericCoords<XY> gcd1 = new GenericCoords<XY>(td);
		System.out.println("GenericCoords�����gcd2�е����ݣ�");
		showXY(gcd1);
		XYZT fd[] = { new XYZT(1, 2, 3, 4), new XYZT(6, 8, 14, 8),
				new XYZT(22, 9, 4, 9), new XYZT(3, -2, -23, 17) };
		GenericCoords<XYZT> gcd2 = new GenericCoords<XYZT>(fd);
		System.out.println("GenericCoords�����gcd2�е����ݣ�");
		showXY(gcd2);
		showXYZ(gcd2);
		showAll(gcd2);
	}
}

class XY {// ��ʾֻ��XY�������
	int x, y;

	public XY(int x, int y) {// Ϊ����X��Y��ֵ
		this.x = x;
		this.y = y;
	}
}

class XYZ extends XY {// ��ʾֻ��XYZ�������
	int z;

	public XYZ(int x, int y, int z) {
		super(x, y);// ���ø���Ĺ��췽��
		this.z = z;
	}
}

class XYZT extends XYZ {// ��ʾֻ��XYZT������࣬����X�������꣬Y�������� Z����ֱ���꣬T���ռ�
	int t;

	public XYZT(int x, int y, int z, int t) {
		super(x, y, z);// ���ø���Ĺ��췽��
		this.t = t;
	}
}

/**
 * ��ŷ�������ģ����ݽṹ����
 */
class GenericCoords<T extends XY> {// ���巺���࣬�������������ΪXY
	T[] gcs;

	public GenericCoords(T[] gcs) {
		this.gcs = gcs;
	}
}
