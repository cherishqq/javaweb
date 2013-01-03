package com.java.base.thread.one;
public class Transformer {						// 本程序的测试类
	public static void main(String[] args) { 		// java程序执行入口处
		Defensor defensor = new Defensor(); 	// 默认情况下父类Defensor是普通线程
		defensor.start(); 					// 启动Defensor类线程
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) { 		// 捕获被唤醒异常
			System.out.println("唤醒异常:" + e.getMessage());
		}
	}
}
class Defensor extends Thread { 				// 操作守护神线程的类
	public void run() {
		System.out.print("当霸天虎在袭击地球的危难关头，守护神是否采取自我保护? ");
		System.out.println(this.isDaemon() ? "是" : "没有");// 测试该线程是否为守护线程。
		System.out.println("守护神Defensor是机器卫兵的组合战士，个性善良，愿意牺牲自己去保护人类");
		Human people = new Human();
		people.setDaemon(true); // 设置守护线程，在本程序中将其子类people设置为守护线程也就是被保护的对象
		people.start();			// 启动守护线程
		try {
			Thread.sleep(1000); // 休眠1秒
		} catch (InterruptedException e) { 	// 捕获唤醒异常
			System.out.println("唤醒异常:" + e.getMessage());
		} finally { 						// 内容总执行
			System.out.println("在面对危险的时候，守候神舍身捍卫地球，保护人类的安全");
		}
		System.out.println("守候神太伟大了...");
	}
}
class Human extends Thread {
	public void run() {
		System.out.print("人类的安全是否被保护？");
		System.out.println(this.isDaemon() ? "是" : "没有");// 判断此线程是否是守护线程
		System.out.println("现在有5处场所的人们正处于危险之中！！！");
		int i = 0;
		try {
			while (i < 5) { 			// 进行5次循环
				System.out.println("第" + (1 + i++) + "处场所的人类");
				Thread.sleep(200); 	// 休眠0.2秒
			}
		} catch (InterruptedException e) { // 捕获唤醒异常
			System.out.println("唤醒异常:" + e.getMessage());
		} finally { 						// 内容总执行
			System.out.println("守候神尽心尽力，帮助人类安全的躲过霸天虎的疯狂袭击");
		}
		System.out.println("终于脱离危险了～～～");
	}
}
