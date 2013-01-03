package com.java.base.thread.two;

public class SyncExample {
	public static void main(String[] args) {
		SyncThread t1 = new SyncThread(); // ����SyncThread���ʵ������
		new Thread(t1, "�߳�1").start(); // �����̲߳�������
		new Thread(t1, "�߳�2").start(); // �����̲߳�������
		System.out.println(t1.Perform()); // ����SyncThread���ͬ������call()
	}

}

class SyncThread implements Runnable {
	private int x = 5;

	private int y = 5;

	// ����SyncThread��ͬ������
	public synchronized void run() { // ��дRunnable�ӿڵ�run()����������synchronized
		for (int i = 0; i < 4; i++) {
			x++;
			y++;
			try {
				Thread.sleep(200); // ��ǰ���е��߳�����200����
			} catch (InterruptedException e) {
				System.out.println("�̳߳�����");
			}
			System.out.println(Thread.currentThread().getName() + " x=" + x
					+ ",y=" + y);
		}
	}

	public synchronized String Perform() { // �Զ��巽������������synchronized
		String name = Thread.currentThread().getName();
		return "��ǰ�������е��̣߳� " + name;
	}
}
