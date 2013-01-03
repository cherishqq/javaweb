package com.java.base.thread.four;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerThread {
	public static void main(String[] args) { // java��������ڴ�
		Timer timer = new Timer(); // ������ʱ����
		TimerTask tt1 = new MyTask(1);
		timer.schedule(tt1, 200); // 0.2���ִ������
		TimerTask tt2 = new MyTask(2);
		timer.schedule(tt2, 500, 1000); // 0.5���ִ������ÿ��1��ִ��һ��
		TimerTask tt3 = new MyTask(3);
		Date date = new Date(System.currentTimeMillis() + 1000);
		timer.schedule(tt3, date); // ��ָ��ʱ��1���ִ������
		try {
			Thread.sleep(3000); // ����3����
		} catch (InterruptedException e) { // ���������쳣
			System.out.println("���ִ���" + e.getMessage());
		}
		timer.cancel(); // ��ֹ��ʱ��ȡ����ʱ���е�����
		System.out.println("����ʱ���Ѿ���ȡ��");
	}
}
class MyTask extends TimerTask { // �̳�ʱ��������ִ������
	private int taskID = 0; // ������
	public MyTask(int id) { // �������Ĺ��췽�����г�ʼ��
		this.taskID = id;
	}
	public void run() { // ʵ��TimerTask�еķ���
		System.out.println("��ʼִ���ҵĵ�" + this.taskID + "������ ��ִ��ʱ��Ϊ"
				+ new Date().toLocaleString());
	}
}
