package com.java.base.thread.one;

import java.util.Random;

public class OnlineStore {
	public static void main(String[] args) {
		Alipay alipay = new Alipay(2); // ����2��֧����
		// ����ʵ���������߳�
		new Seller("����-nieqing13", alipay, 5).start();
		new Seller("����-С����88", alipay, 7).start();
		new Buyer("���-��֮����", alipay, 101).start();
		new Buyer("���-��������", alipay, 102).start();

	}
}

class Alipay { // ֧���� ����������������
	private final String[] goods;//��ʶ������Ʒ������ĳ����Ƕ������ʾ��Ʒ�������Ƕ���

	private int n; // ��ʶ����֧�����Ľ�������

	private int m; // ��ʶ֧��֧�����Ľ�������

	private int count; // �����ڵĽ�������

	public Alipay(int count) { // ���췽�����г�ʼ��
		this.goods = new String[count]; // �����ַ�������
		this.m = 0;
		this.n = 0;
		this.count = 0;
	}

	public synchronized void storage(String alipay) { // ��֧��������
		System.out.println("�Ա��û�ID=" + Thread.currentThread().getName()
				+ "\t֧��������" + alipay);
		try {
			while (count >= goods.length) {
				wait(); // �̵߳ȴ�
			}
			goods[n] = alipay; // ����֧�����˺�������
			n = (n + 1) % goods.length;
			count++;
			notifyAll();
		} catch (Exception e) { // �����쳣
			System.out.println("֧�������빦�ܳ��ִ���" + e.getMessage());
		}
	}

	public synchronized String outlay() { // ��֧������֧��
		String alipay = null;
		try {
			while (count <= 0) {
				wait(); // �̵߳ȴ�
			}
			alipay = goods[m]; // ȡ��ָ����֧�����˺�
			m = (m + 1) % goods.length;
			count--; // ���������һ
			notifyAll();
		} catch (Exception e) { // �����쳣
			System.out.println("֧����֧�����ܳ��ִ���" + e.getMessage());
		}
		System.out.println("�Ա��û�ID=" + Thread.currentThread().getName()
				+ "\t֧����֧��" + alipay);
		return alipay;
	}
}

class Buyer extends Thread { // ����߳���
	private final Random random;

	private final Alipay alipay;

	private static int id = 0; // ���׵���ˮ��

	public Buyer(String name, Alipay alipay, long seed) {// ���췽�����г�ʼ��
		super(name);
		this.alipay = alipay;
		this.random = new Random(seed);
	}

	public void run() { // ʵ��Thread��ķ����������߳�
		try {
			while (true) {
				Thread.sleep(random.nextInt(1000));// �������
				String flowerID = "������ˮ�˺ţ�" + nextId();
				alipay.storage(flowerID); // ����֧������
			}
		} catch (Exception e) { // �����쳣
		}
	}

	private static synchronized int nextId() {
		return id++;
	}
}

class Seller extends Thread { // �����߳���
	private final Random random;

	private final Alipay alipay;

	// ���췽�����г�ʼ��
	public Seller(String name, Alipay alipay, long seed) {
		super(name);
		this.alipay = alipay;
		this.random = new Random(seed); // �����������
	}

	public void run() { // ʵ��Thread��ķ����������߳�
		try {
			while (true) {
				String alipay = this.alipay.outlay();
				Thread.sleep(random.nextInt(1000));
			}
		} catch (Exception e) { // �����쳣
			System.out.println("���֧��Ԥ�������" + e.getMessage());
		}
	}
}
