package com.java.base.thread.one;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Relay extends Thread {
	private DateFormat df = new SimpleDateFormat("ss:SS");
	public static void main(String[] args) { 	// java��������ڴ�
		Relay relay = new Relay();		// ʵ��������
		relay.start(); 					// �����߳�
		try {
			relay.join(); 				// �ȴ��߳����н���
		} catch (InterruptedException e) {	// �������쳣
			System.out.println(" �յ����׼���ϳ���" + e.getMessage());
		}
		relay.incident(); 				// ���÷����ж��Ƿ���
	}
	public void incident() {
		Thread.currentThread().interrupt(); 	// ���ѵ�ǰ�߳�
		while (true) {
			if (Thread.currentThread().isInterrupted()) { // �жϵ�ǰ�߳��Ƿ񱻻���
				System.out.print(df.format(new Date()) + "	�����Ƿ�����׼���ϳ�? ");
				System.out.println(Thread.currentThread().isInterrupted() ? "��"
						: "û��");
				try {
					Thread.currentThread().sleep(3000); // �߳�����3��
				} catch (InterruptedException e) {		// �������쳣
					System.out.println(df.format(new Date()) + " �յ����ֹͣ��Ϣ��"
							+ e.getMessage());
				}
				System.out.print(df.format(new Date()) + "	�����������Ƿ�μ���һ�ֱ���? ");
				System.out.println(Thread.currentThread().isInterrupted() ? "��"
						: "���μ�");
			}
		}
	}
	public void run() {
		System.out.println("��һ������������ʱ��Ϊ��" + df.format(new Date()));
		System.out.println("��Ϣ5Сʱ");
		try {
			sleep(2000); 				// �߳�����2�� �ڳ������1����=1Сʱ
		} catch (InterruptedException e) { 	// �������쳣
			System.out.println(df.format(new Date()) + "�յ����׼���ϳ���"
					+ e.getMessage());
		}
		System.out.print(df.format(new Date()) + "	����Ϣ�Ĺ������Ƿ��ֲμ������ı���? ");
		try {
			sleep(2000); 				// �߳�����2��
		} catch (InterruptedException e) { 	// �������쳣
			System.out.println(df.format(new Date()) + "�յ����׼���ϳ���"
					+ e.getMessage());
		}
		System.out.println(!isAlive() ? "�μӱ���" : "û�вμ������ı���"); // �߳��Ƿ񼤻�,false���Ǽ����
		interrupt();// �����߳�
		System.out.print(df.format(new Date()) + " ��Ϣ�У��油��Ա���ˣ��Ƿ�μӱ���? ");
		System.out.println(isAlive() ? "�μӱ���" : "���μӱ���"); // �߳��Ƿ񼤻�
	}
	
}
