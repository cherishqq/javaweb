package com.java.base.thread.one;

import java.util.Random;

public class OnlineStore {
	public static void main(String[] args) {
		Alipay alipay = new Alipay(2); // 创建2个支付宝
		// 创建实例并启动线程
		new Seller("卖家-nieqing13", alipay, 5).start();
		new Seller("卖家-小不不88", alipay, 7).start();
		new Buyer("买家-淘之妖妖", alipay, 101).start();
		new Buyer("买家-相信美丽", alipay, 102).start();

	}
}

class Alipay { // 支付宝 用于网上买卖交易
	private final String[] goods;//标识卖家商品，数组的长度是多少则表示商品的数量是多少

	private int n; // 标识存入支付宝的交易数量

	private int m; // 标识支出支付宝的交易数量

	private int count; // 缓存内的交易数量

	public Alipay(int count) { // 构造方法进行初始化
		this.goods = new String[count]; // 创建字符串数组
		this.m = 0;
		this.n = 0;
		this.count = 0;
	}

	public synchronized void storage(String alipay) { // 往支付宝里存款
		System.out.println("淘宝用户ID=" + Thread.currentThread().getName()
				+ "\t支付宝存入" + alipay);
		try {
			while (count >= goods.length) {
				wait(); // 线程等待
			}
			goods[n] = alipay; // 放置支付宝账号于数组
			n = (n + 1) % goods.length;
			count++;
			notifyAll();
		} catch (Exception e) { // 捕获异常
			System.out.println("支付宝存入功能出现错误：" + e.getMessage());
		}
	}

	public synchronized String outlay() { // 从支付宝中支出
		String alipay = null;
		try {
			while (count <= 0) {
				wait(); // 线程等待
			}
			alipay = goods[m]; // 取出指定的支付宝账号
			m = (m + 1) % goods.length;
			count--; // 数组个数减一
			notifyAll();
		} catch (Exception e) { // 捕获异常
			System.out.println("支付宝支付功能出现错误：" + e.getMessage());
		}
		System.out.println("淘宝用户ID=" + Thread.currentThread().getName()
				+ "\t支付宝支出" + alipay);
		return alipay;
	}
}

class Buyer extends Thread { // 买家线程类
	private final Random random;

	private final Alipay alipay;

	private static int id = 0; // 交易的流水号

	public Buyer(String name, Alipay alipay, long seed) {// 构造方法进行初始化
		super(name);
		this.alipay = alipay;
		this.random = new Random(seed);
	}

	public void run() { // 实现Thread类的方法，启动线程
		try {
			while (true) {
				Thread.sleep(random.nextInt(1000));// 随机休眠
				String flowerID = "交易流水账号：" + nextId();
				alipay.storage(flowerID); // 存入支付宝中
			}
		} catch (Exception e) { // 捕获异常
		}
	}

	private static synchronized int nextId() {
		return id++;
	}
}

class Seller extends Thread { // 卖家线程类
	private final Random random;

	private final Alipay alipay;

	// 构造方法进行初始化
	public Seller(String name, Alipay alipay, long seed) {
		super(name);
		this.alipay = alipay;
		this.random = new Random(seed); // 创建随机对象
	}

	public void run() { // 实现Thread类的方法，启动线程
		try {
			while (true) {
				String alipay = this.alipay.outlay();
				Thread.sleep(random.nextInt(1000));
			}
		} catch (Exception e) { // 捕获异常
			System.out.println("买家支付预付款出错：" + e.getMessage());
		}
	}
}
