package com.java.base.thread.two;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SavingOffice extends Frame implements ActionListener {
	private Label label = new Label("ת�������:0");

	// ����AWT�����ı����
	private TextArea area = new TextArea();

	// ����AWT��Ť���
	private Button display = new Button("��ʾ�ʻ�");

	private Button start = new Button("��������");

	private Button stop = new Button("ֹͣ");

	// �����˺ŵĸ���
	protected final static int num_accounts = 8;

	// �����˷е�ʱ��
	private final static int waste_time = 1;

	// ����һ��int�����飬���ڴ����˺ŵĳ�ʼ���
	private int accounts[] = new int[num_accounts];

	// ����һ��Customer���飬���ڴ��Customerʵ��������
	private Customer customer[] = new Customer[num_accounts];

	// ��ת�˵Ľ��
	private int count = 0;

	public SavingOffice() {// ���췽����Ϊ��AWT��Ա�������г�ʼ��
		super("�ҵ�����С���");
		Panel btn_Panel = new Panel();
		btn_Panel.setLayout(new FlowLayout());
		btn_Panel.add(display);
		display.addActionListener(this);
		btn_Panel.add(start);
		start.addActionListener(this);
		btn_Panel.add(stop);
		stop.addActionListener(this);
		setLayout(new BorderLayout());
		add("North", label);
		add("South", btn_Panel);
		add("Center", area);
		for (int i = 0; i < accounts.length; i++)
			accounts[i] = 50000;// ÿ���˻��ϵĳ�ʼ���Ϊ5��Ԫ
		start();// ����ͨ�ķ����������̵߳�start����
		validate();
		setSize(300, 300);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	public void transfer(int from, int into, int amount) {// ����ת��
		if ((accounts[from] >= amount) && (from != into)) {
			int newAmountFrom = accounts[from] - amount;
			int newAmountTo = accounts[into] + amount;
			wastrSomeTime();
			accounts[from] = newAmountFrom;
			accounts[into] = newAmountTo;

		}
		label.setText("ת����ɣ�" + count++);
	}

	public void start() {// ��������
		stop();
		for (int i = 0; i < accounts.length; i++)
			customer[i] = new Customer(i, this);
	}

	private void stop() {// ֹͣ
		for (int i = 0; i < accounts.length; i++)
			if (customer[i] != null)
				customer[i].setFlagValue();
	}

	private void wastrSomeTime() {// ���ڵȴ�������
		try {
			Thread.sleep(waste_time);
		} catch (InterruptedException ie) {
			System.out.println(ie);
		}
	}

	private void showAccounts() {// ��ʾ���
		int sum = 0;
		for (int i = 0; i < accounts.length; i++) {
			sum += accounts[i];
			area.append("\n�ʻ� " + i + "��$" + accounts[i]);
		}
		area.append("\n�ܽ�$" + sum);
		area.append("\nת���ܴ�����" + count + "\n");
	}

	public void actionPerformed(ActionEvent ae) {// Ϊbutton�������¼�����
		if (ae.getSource() == display)
			showAccounts();
		else if (ae.getSource() == start)
			start();
		else if (ae.getSource() == stop)
			stop();
	}

	public static void main(String args[]) {
		SavingOffice bank = new SavingOffice();
	}
}

class Customer extends Thread {// �˿���
	private SavingOffice bank = null;

	private int id = -1;

	private boolean flag = false;

	public Customer(int _id, SavingOffice _bank) {
		bank = _bank;
		id = _id;
		start();
	}

	public void start() {
		flag = true;
		super.start();// �����߳�
	}

	public void setFlagValue() {// ����Ϊ��ת��״̬
		flag = false;
	}

	public void run() {// ���и��߳�
		while (flag) {
			int into = (int) (SavingOffice.num_accounts * Math.random());
			int amount = (int) (1000 * Math.random());
			bank.transfer(id, into, amount);
			yield();
		}
	}
}
