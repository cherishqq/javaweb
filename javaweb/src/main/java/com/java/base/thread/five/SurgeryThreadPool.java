package com.java.base.thread.five;


import java.util.*;

public class SurgeryThreadPool {
	private static Runnable createTask(final int taskID) { // 创建任务方法
		return new Runnable() {
			public void run() { // 创建手术任务
				System.out.println("手术开始,编号为" + taskID);
				System.out.println("正在手术中～～");
				System.out.println("手术结束,编号为" + taskID);
			}
		};
	}

	public static void main(String[] args) { // java程序主入口处
		ThreadTask threadPool = new ThreadTask(3); // 创建一个有个3任务线程的线程池
		try {// 休眠600毫秒,让线程池中的任务线程全部运行
			Thread.sleep(600);
		} catch (InterruptedException e) { // 捕获拦截异常
			System.out.println("线程休眠出错：" + e.getMessage());
		}
		for (int i = 0; i < 3; i++) { // 循环创建并执行任务
			threadPool.addTask(createTask(i));
		}
		threadPool.waitTaskColsed(); // 等待所有任务执行完毕
		threadPool.closePool(); // 关闭线程池
	}

}

class ThreadTask extends ThreadGroup { // 继承线程组实现线程池功能
	private boolean isStop = false; // 线程池是否关闭

	private LinkedList queue; // 工作任务队列

	private static int poolID = 1; // 线程池的编号

	private class SurgeryTask extends Thread { // 负责从工作队列中取出任务并执行的内部类
		private int id; // 任务编号

		public SurgeryTask(int id) { // 构造方法进行初始化
			super(ThreadTask.this, id + ""); // 将线程加入到当前线程组中
			this.id = id;
		}

		public void run() {
			while (!isInterrupted()) { // 判断线程是否被中断
				Runnable task = null;
				task = getTask(id); // 取出任务

				// 如果getTask()返回null或者线程执行getTask()时被中断，则结束此线程
				if (task == null)
					return;

				try {
					task.run(); // 运行任务
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
	}

	public ThreadTask(int size) { // 构造方法传入线程池中的工作线程的数量
		super(poolID + ""); // 指定线程组名称
		setDaemon(true); // 继承线程组的方法用来设置是否守护线程池
		queue = new LinkedList(); // 创建工作任务队列
		for (int i = 0; i < size; i++) { // 循环创建任务线程
			new SurgeryTask(i).start(); // 根据线程池数据创建任务线程并启动线程,
		}
	}

	public synchronized void addTask(Runnable task) {// 添加新任务并执行任务
		if (isStop) { // 判断标识
			throw new IllegalStateException(); // 抛出不合理状态异常
		}
		if (task != null) {
			queue.add(task); // 向任务队列中加入一个任务
			notify(); // 唤醒待任务的工作任务线程
		}
	}

	private synchronized Runnable getTask(int id) { // 取出任务
		try {
			while (queue.size() == 0) { // 循环使线程等待任务
				if (isStop)
					return null;
				System.out.println("病人" + id + "正在等待手术...");
				wait(); // 如果任务队列中没有任务,就等待任务
			}
		} catch (InterruptedException e) { // 捕获拦截异常
			System.out.println("等待治疗出现错误：" + e.getMessage());
		}
		System.out.println("病人" + id + "开始执行手术...");
		return (Runnable) queue.removeFirst();// 返回第一个任务并从队列中删除
	}

	public synchronized void closePool() { // 关闭线程池
		if (!isStop) { // 判断标识
			waitTaskColsed(); // 等待任务线程执行完毕
			isStop = true; // 标识为真
			queue.clear(); // 任务队列清空
			interrupt(); // 唤醒线程池中的所有的工作线程
		}
	}

	public void waitTaskColsed() { // 等待任务线程把所有任务执行完毕
		synchronized (this) {
			isStop = true; // 标识为真
			notifyAll(); // 唤醒待任务的工作任务线程
		}
		Thread[] threads = new Thread[activeCount()];// 创建线程组中活动的线程组
		int count = enumerate(threads); // 获得线程组中当前所有活动的工作线程
		for (int i = 0; i < count; i++) { // 循环等待所有工作线程结束
			try {
				threads[i].join(); // 等待工作线程结束
			} catch (InterruptedException e) { // 捕获拦截异常
				System.out.println("手术失败：" + e.getMessage());
			}
		}
	}
}
