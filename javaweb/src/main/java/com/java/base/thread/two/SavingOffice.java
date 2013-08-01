package com.java.base.thread.two;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SavingOffice extends Frame implements ActionListener {
	private Label label = new Label("转账已完成:0");

	// 创建AWT多行文本组件
	private TextArea area = new TextArea();

	// 创建AWT按扭组件
	private Button display = new Button("显示帐户");

	private Button start = new Button("重新启动");

	private Button stop = new Button("停止");

	// 声明账号的个数
	protected final static int num_accounts = 8;

	// 定义浪沸的时间
	private final static int waste_time = 1;

	// 创建一个int型数组，用于存入账号的初始金额
	private int accounts[] = new int[num_accounts];

	// 创建一个Customer数组，用于存放Customer实例化对象
	private Customer customer[] = new Customer[num_accounts];

	// 表法转账的金额
	private int count = 0;

	public SavingOffice() {// 构造方法，为其AWT成员变量进行初始化
		super("我的秘密小金库");
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
			accounts[i] = 50000;// 每个账户上的初始金额为5万元
		start();// 是普通的方法，并非线程的start方法
		validate();
		setSize(300, 300);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	public void transfer(int from, int into, int amount) {// 进行转账
		if ((accounts[from] >= amount) && (from != into)) {
			int newAmountFrom = accounts[from] - amount;
			int newAmountTo = accounts[into] + amount;
			wastrSomeTime();
			accounts[from] = newAmountFrom;
			accounts[into] = newAmountTo;

		}
		label.setText("转账完成：" + count++);
	}

	public void start() {// 启动程序
		stop();
		for (int i = 0; i < accounts.length; i++)
			customer[i] = new Customer(i, this);
	}

	private void stop() {// 停止
		for (int i = 0; i < accounts.length; i++)
			if (customer[i] != null)
				customer[i].setFlagValue();
	}

	private void wastrSomeTime() {// 处于等待休眠中
		try {
			Thread.sleep(waste_time);
		} catch (InterruptedException ie) {
			System.out.println(ie);
		}
	}

	private void showAccounts() {// 显示金额
		int sum = 0;
		for (int i = 0; i < accounts.length; i++) {
			sum += accounts[i];
			area.append("\n帐户 " + i + "：$" + accounts[i]);
		}
		area.append("\n总金额：$" + sum);
		area.append("\n转账总次数：" + count + "\n");
	}

	public void actionPerformed(ActionEvent ae) {// 为button组件添别事件监听
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

class Customer extends Thread {// 顾客类
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
		super.start();// 启动线程
	}

	public void setFlagValue() {// 设置为不转账状态
		flag = false;
	}

	public void run() {// 运行该线程
		while (flag) {
			int into = (int) (SavingOffice.num_accounts * Math.random());
			int amount = (int) (1000 * Math.random());
			bank.transfer(id, into, amount);
			yield();
		}
	}
}
