package com.java.base.generic.one;

/**
 * Created by IntelliJ IDEA. User: leizhimin Date: 2007-9-18 Time: 16:09:37
 * 三种坐标，用泛型实现坐标打印
 */
public class CoorGeneric {
	static void showXY(GenericCoords<?> c) {// 定义泛型方法，利用通配符设置参数C的类型为XL并显示类XL中的变量X和Y的值
		System.out.println("X Y 坐标轴:");
		for (int i = 0; i < c.gcs.length; i++) {
			System.out.println(c.gcs[i].x + "  " + c.gcs[i].y);
		}
	}

	static void showXYZ(GenericCoords<? extends XYZ> c) {
		System.out.println("X Y Z 坐标轴:");
		// 定义泛型方法，利用通配符设置参数C的类型为XYZ并显示类XYZ中的变量X、Y和Z的值
		for (int i = 0; i < c.gcs.length; i++) {
			System.out.println(c.gcs[i].x + "  " + c.gcs[i].y + "  "
					+ c.gcs[i].z);
		}
	}

	static void showAll(GenericCoords<? extends XYZT> c) {
		System.out.println("X Y Z T 坐标轴:");
		// 定义泛型方法，利用通配符设置参数C的类型为XYZT并显示类XYZT中的变量X、Y、Z和T的值
		for (int i = 0; i < c.gcs.length; i++) {
			System.out.println(c.gcs[i].x + "  " + c.gcs[i].y + "  "
					+ c.gcs[i].z + "  " + c.gcs[i].t);
		}
	}

	public static void main(String args[]) {
		XY td[] = { new XY(0, 0), new XY(7, 9), new XY(18, 4), new XY(-1, -23) };
		GenericCoords<XY> gcd1 = new GenericCoords<XY>(td);
		System.out.println("GenericCoords类对象gcd2中的内容：");
		showXY(gcd1);
		XYZT fd[] = { new XYZT(1, 2, 3, 4), new XYZT(6, 8, 14, 8),
				new XYZT(22, 9, 4, 9), new XYZT(3, -2, -23, 17) };
		GenericCoords<XYZT> gcd2 = new GenericCoords<XYZT>(fd);
		System.out.println("GenericCoords类对象gcd2中的内容：");
		showXY(gcd2);
		showXYZ(gcd2);
		showAll(gcd2);
	}
}

class XY {// 表示只有XY坐标的类
	int x, y;

	public XY(int x, int y) {// 为变量X、Y赋值
		this.x = x;
		this.y = y;
	}
}

class XYZ extends XY {// 表示只有XYZ坐标的类
	int z;

	public XYZ(int x, int y, int z) {
		super(x, y);// 调用父类的构造方法
		this.z = z;
	}
}

class XYZT extends XYZ {// 表示只有XYZT坐标的类，其中X：横坐标，Y：纵坐标 Z：垂直坐标，T：空间
	int t;

	public XYZT(int x, int y, int z, int t) {
		super(x, y, z);// 调用父类的构造方法
		this.t = t;
	}
}

/**
 * 存放泛型坐标的（数据结构）类
 */
class GenericCoords<T extends XY> {// 定义泛型类，设置其参数类型为XY
	T[] gcs;

	public GenericCoords(T[] gcs) {
		this.gcs = gcs;
	}
}
