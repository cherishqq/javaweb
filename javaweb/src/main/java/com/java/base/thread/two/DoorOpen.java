package com.java.base.thread.two;

public class DoorOpen {
	static String[] keys = new String[] { "第1把钥匙", "第2把钥匙" };

	static class DoorKey1 extends Thread { // 静态内部类
		public void run() {
			synchronized (keys[0]) { // 在同一时间只能有一个类访问
				System.out.println("我拿起了" + keys[0] + ",在等着朋友用" + keys[1]
						+ "开防盗门");
				try {
					Thread.sleep(100); // 线程休眠
				} catch (Exception e) { // 捕获异常
					System.out.println("线程休眠出错：" + e.getMessage());
				}
				synchronized (keys[1]) {
					System.out.println("我又拿出来" + keys[1] + "打开了防盗门");
				}
			}
		}
	}

	static class DoorKey2 extends Thread { // 静态内部类
		public void run() {
			synchronized (keys[0]) {
				System.out.println("\n朋友拿出了" + keys[0] + ",在等待我用" + keys[1]
						+ "开防盗门");
				try {
					Thread.sleep(100); // 线程休眠
				} catch (Exception e) { // 捕获异常
					System.out.println("线程休眠出错：" + e.getMessage());
				}
				synchronized (keys[1]) {
					System.out.println("朋友又拿出了" + keys[1] + "打开了防盗门");
				}
			}
		}
	}

	static class GoWrong extends Thread { // 静态守护线程类
		public GoWrong() {
			this.setDaemon(true); // 线程设置守护
		}

		public void run() {
			while (true) {
				try {
					Thread.sleep(1000); // 线程休眠
				} catch (Exception e) { // 捕获异常
					System.out.println("线程休眠出错：" + e.getMessage());
				}
				System.out.println("守护线程：程序正在运行...");
			}
		}
	}

	public static void main(String[] args) { // java程序主入口处
		DoorKey1 one = new DoorKey1(); // 实例化对象
		DoorKey2 two = new DoorKey2();
		GoWrong daemon = new GoWrong();
		one.start(); // 启动线程
		two.start();
		daemon.start();
	}

}
