package com.java.base.thread.two;

public class SyncExample {
	public static void main(String[] args) {
		SyncThread t1 = new SyncThread(); // 创建SyncThread类的实例对象
		new Thread(t1, "线程1").start(); // 创建线程并启动它
		new Thread(t1, "线程2").start(); // 创建线程并启动它
		System.out.println(t1.Perform()); // 调用SyncThread类的同步方法call()
	}

}

class SyncThread implements Runnable {
	private int x = 5;

	private int y = 5;

	// 定义SyncThread的同步方法
	public synchronized void run() { // 重写Runnable接口的run()，并声明成synchronized
		for (int i = 0; i < 4; i++) {
			x++;
			y++;
			try {
				Thread.sleep(200); // 当前运行的线程休眠200毫秒
			} catch (InterruptedException e) {
				System.out.println("线程出错了");
			}
			System.out.println(Thread.currentThread().getName() + " x=" + x
					+ ",y=" + y);
		}
	}

	public synchronized String Perform() { // 自定义方法，并声明成synchronized
		String name = Thread.currentThread().getName();
		return "当前正在运行的线程： " + name;
	}
}
