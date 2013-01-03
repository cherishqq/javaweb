package com.java.base.thread.one;

import java.util.Date;

public class ThreadLife {
	public void startY() {
		ThreadY ty = new ThreadY();// ����ʵ��
		ty.startThreadY(); // ����ThreadY�߳�
		try {
			Thread.sleep(1000); // ��ǰ�߳�����һ����
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ty.stopThreadY(); // ֹͣThreadY�߳�
	}

	public void startX() { // ��ʼ�ڶ���
		Runnable runnX = new ThreadX(); // ����ʵ��
		Thread threadX = new Thread(runnX); // ��ʵ�������߳���
		threadX.start(); // start���������߳�
	}

	public static void main(String[] args) { // java��������ڴ�
		ThreadLife test = new ThreadLife(); // ʵ��������
		test.startY(); // ���÷���
		test.startX();
	}

}

class ThreadY extends Thread { // �̳�java.lang.Thread�ඨ���߳�
	private boolean isRunState = false; // ����߳��Ƿ���Ҫ����

	public void start() { // �����˸����start������
		this.isRunState = true; // ��isRunState��Ϊture����ʾ�߳���Ҫ����
		super.start();
	}

	public void run() {
		int i = 0;
		try {
			while (isRunState) { // ���isRunStateΪ�棬˵���̻߳����Լ�������
				this.setName("Thread-" + i++);
				System.out.println("�߳�Y��" + this.getName() + " ��������");
				Thread.sleep(200); // sleep��������ǰ�߳����ߡ�
			}
		} catch (Exception e) { // �����쳣
		}

		System.out.println(this.getName() + "���н���...");
	}

	public void setRunning(boolean isRunState) { // �����߳�
		this.isRunState = isRunState;
	}

	public void startThreadY() { // ����ThreadY�߳�
		System.out.println("�����߳�Y...");
		this.start();
	}

	public void stopThreadY() { // ֹͣThreadY�߳�
		System.out.println("�����߳�Y...");
		this.setRunning(false);
	}

}

class ThreadX implements Runnable { // ʵ��java.lang.Runnable�ӿڶ����߳�
	private Date runDate; // �̱߳����е�ʱ��

	public void run() {
		System.out.println("�߳�X�Ѿ�����...");
		this.runDate = new Date();
		System.out.println("����ʱ��:" + runDate.toLocaleString());
	}
}
