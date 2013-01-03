package com.java.base.thread.one;

public class Partner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MatchMarry group1 = new MatchMarry("TOM", "SUSAN"); // ʵ��������
		MatchMarry group2 = new MatchMarry("����", "��С��");
		MatchMarry group3 = new MatchMarry("����", "�޲��ء�������");
		MatchMarry group4 = new MatchMarry("TOM", "����");
		MatchMarry group5 = new MatchMarry("SUSAN", "����");
		System.out.println("**�����������������ּ��Ҫ��֤����\"Я��֮�� ��������\"�ľ���");
		group1.start(); // �����߳�
		try {
			group1.join(); // �ȴ��߳����н���
		} catch (InterruptedException e) { // �������쳣
			System.out.println("�����쳣:" + e.getMessage());
		}
		group2.start();
		try {
			group2.join(); // �ȴ��߳����н���
		} catch (InterruptedException e) { // �������쳣
			System.out.println("�����쳣:" + e.getMessage());
		}
		group3.start();
		try {
			group3.join(); // �ȴ��߳����н���
		} catch (InterruptedException e) { // �������쳣
			System.out.println("�����쳣:" + e.getMessage());
		}

		group4.start();
		try {
			group4.join(); // �ȴ��߳����н���
		} catch (InterruptedException e) { // �������쳣
			System.out.println("�����쳣:" + e.getMessage());
		}
		group5.start();
		try {
			group5.join(); // �ȴ��߳����н���
		} catch (InterruptedException e) { // �������쳣
			System.out.println("�����쳣:" + e.getMessage());
		}
		System.out.println("�������...");

	}

}

class MatchMarry extends Thread { // ����ƥ�������
	private String name; // ��Ա����

	private String otherName; // ������

	private boolean isMarry = false; // �Ƿ���

	public MatchMarry(String name, String otherName) { // ���������췽�����г�ʼ��
		this.name = name;
		this.otherName = otherName;
	}

	public void run() {
		try {
			int person = (int) Math.floor((Math.random() * 10 + 1));// ��������
			if (person % 2 == 0) {
				isMarry = true; // ���ñ�ʶ
			} else {
				isMarry = false;
			}
			if (!isMarry) {
				System.out.println(name + "������" + otherName + "��飬ף������");
			} else {
				System.out.println(otherName + "�ѻ飬��ɫ���棺�Դ�������Ҫ�ж���");
			}
			Thread.sleep(200); // �߳�����

		} catch (InterruptedException e) { // �������쳣
			System.out.println("�����쳣:" + e.getMessage());
		}
	}
}
