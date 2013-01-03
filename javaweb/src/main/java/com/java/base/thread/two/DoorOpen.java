package com.java.base.thread.two;

public class DoorOpen {
	static String[] keys = new String[] { "��1��Կ��", "��2��Կ��" };

	static class DoorKey1 extends Thread { // ��̬�ڲ���
		public void run() {
			synchronized (keys[0]) { // ��ͬһʱ��ֻ����һ�������
				System.out.println("��������" + keys[0] + ",�ڵ���������" + keys[1]
						+ "��������");
				try {
					Thread.sleep(100); // �߳�����
				} catch (Exception e) { // �����쳣
					System.out.println("�߳����߳���" + e.getMessage());
				}
				synchronized (keys[1]) {
					System.out.println("�����ó���" + keys[1] + "���˷�����");
				}
			}
		}
	}

	static class DoorKey2 extends Thread { // ��̬�ڲ���
		public void run() {
			synchronized (keys[0]) {
				System.out.println("\n�����ó���" + keys[0] + ",�ڵȴ�����" + keys[1]
						+ "��������");
				try {
					Thread.sleep(100); // �߳�����
				} catch (Exception e) { // �����쳣
					System.out.println("�߳����߳���" + e.getMessage());
				}
				synchronized (keys[1]) {
					System.out.println("�������ó���" + keys[1] + "���˷�����");
				}
			}
		}
	}

	static class GoWrong extends Thread { // ��̬�ػ��߳���
		public GoWrong() {
			this.setDaemon(true); // �߳������ػ�
		}

		public void run() {
			while (true) {
				try {
					Thread.sleep(1000); // �߳�����
				} catch (Exception e) { // �����쳣
					System.out.println("�߳����߳���" + e.getMessage());
				}
				System.out.println("�ػ��̣߳�������������...");
			}
		}
	}

	public static void main(String[] args) { // java��������ڴ�
		DoorKey1 one = new DoorKey1(); // ʵ��������
		DoorKey2 two = new DoorKey2();
		GoWrong daemon = new GoWrong();
		one.start(); // �����߳�
		two.start();
		daemon.start();
	}

}
