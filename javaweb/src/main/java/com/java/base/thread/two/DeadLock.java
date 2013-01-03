package com.java.base.thread.two;

import java.awt.*;
import java.awt.event.*;

public class DeadLock extends Frame {
	protected static final String[] names = { "One", "Two" };// ����һ���ַ������飬���ڴ���̵߳�����

	private int accounts[] = { 1000, 1000 };// �����˺�

	// ����TextArea���
	private TextArea info = new TextArea(5, 40);

	private TextArea status = new TextArea(5, 40);

	public DeadLock() {// ���췽��
		super("������������");// ���ø���Frame�Ĵ��ι��췽��
		this.setLayout(new GridLayout(2, 1));
		add(makePanel(info, "�˺�"));
		add(makePanel(status, "�߳�"));
		validate();
		pack();
		show();
		// ����DeadLockThread����
		DeadLockThread A = new DeadLockThread(0, this, status);
		DeadLockThread B = new DeadLockThread(1, this, status);
		this.addWindowListener(new WindowAdapter() {// ��ӵ����¼�����
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
	}

	public synchronized void transfer(int from, int into, int amount) {// ת��
		info.append("\n�ʻ� One:$" + accounts[0]);// �������ı�׷�ӵ��ı����ĵ�ǰ�ı�
		info.append("\n�ʻ� Two:$" + accounts[1]);
		info.append("\n>=$" + amount + "��" + names[from] + "��" + names[into]);
		while (accounts[from] < amount) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			accounts[from] = amount;
			accounts[into] = amount;
			notify();
		}
	}

	private Panel makePanel(TextArea ta, String title) {// ������壬��ѡ��Ĳ��ַ�ʽ��������в���
		Panel p = new Panel();
		p.setLayout(new BorderLayout());
		p.add("North", new Label(title));
		p.add("Center", ta);
		return p;
	}

	public static void main(String[] args) {// �������������
		DeadLock dl = new DeadLock();
	}
}

class DeadLockThread extends Thread {// �����߳�
	private DeadLock dl;

	private int id;

	private TextArea display;

	public DeadLockThread(int _id, DeadLock _dl, TextArea _display) {
		dl = _dl;
		id = _id;
		display = _display;
		start();
	}

	public void run() {
		while (true) {
			int amount = (int) (1500 * Math.random());
			display.append("\nThread" + DeadLock.names[id] + "�� $" + amount
					+ "����" + DeadLock.names[(1 - id)]);
			try {
				sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dl.transfer(id, 1 - id, amount);
		}
	}
}
