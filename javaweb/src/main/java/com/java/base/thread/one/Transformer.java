package com.java.base.thread.one;
public class Transformer {						// ������Ĳ�����
	public static void main(String[] args) { 		// java����ִ����ڴ�
		Defensor defensor = new Defensor(); 	// Ĭ������¸���Defensor����ͨ�߳�
		defensor.start(); 					// ����Defensor���߳�
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) { 		// ���񱻻����쳣
			System.out.println("�����쳣:" + e.getMessage());
		}
	}
}
class Defensor extends Thread { 				// �����ػ����̵߳���
	public void run() {
		System.out.print("�����컢��Ϯ�������Σ�ѹ�ͷ���ػ����Ƿ��ȡ���ұ���? ");
		System.out.println(this.isDaemon() ? "��" : "û��");// ���Ը��߳��Ƿ�Ϊ�ػ��̡߳�
		System.out.println("�ػ���Defensor�ǻ������������սʿ������������Ը�������Լ�ȥ��������");
		Human people = new Human();
		people.setDaemon(true); // �����ػ��̣߳��ڱ������н�������people����Ϊ�ػ��߳�Ҳ���Ǳ������Ķ���
		people.start();			// �����ػ��߳�
		try {
			Thread.sleep(1000); // ����1��
		} catch (InterruptedException e) { 	// �������쳣
			System.out.println("�����쳣:" + e.getMessage());
		} finally { 						// ������ִ��
			System.out.println("�����Σ�յ�ʱ���غ������������򣬱�������İ�ȫ");
		}
		System.out.println("�غ���̫ΰ����...");
	}
}
class Human extends Thread {
	public void run() {
		System.out.print("����İ�ȫ�Ƿ񱻱�����");
		System.out.println(this.isDaemon() ? "��" : "û��");// �жϴ��߳��Ƿ����ػ��߳�
		System.out.println("������5������������������Σ��֮�У�����");
		int i = 0;
		try {
			while (i < 5) { 			// ����5��ѭ��
				System.out.println("��" + (1 + i++) + "������������");
				Thread.sleep(200); 	// ����0.2��
			}
		} catch (InterruptedException e) { // �������쳣
			System.out.println("�����쳣:" + e.getMessage());
		} finally { 						// ������ִ��
			System.out.println("�غ����ľ������������లȫ�Ķ�����컢�ķ��Ϯ��");
		}
		System.out.println("��������Σ���ˡ�����");
	}
}
