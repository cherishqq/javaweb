package com.java.base.thread.six;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class QueryBalance extends JPanel {
	private JTextField acctext;// 声明一个录入帐号的输入框
	private JTextField pass;// 声明一个录入帐号密码的输入框
	private JButton button1;// 声明一个查询按扭
	private JButton button2;// 声明一个取消按扭
	private JLabel balanceL;// 声明一个录入帐号的输入框
	private volatile Thread lookupThread;// 创建一个线程
	public QueryBalance() {// 构造方法
		mainFrame();
		searchEvents();
	}
	private void mainFrame() {
		// 创建组件
		JLabel Lacct = new JLabel("账户编号:");
		JLabel Lpass = new JLabel("密码:");
		acctext = new JTextField(12);
		pass = new JTextField(4);
		JPanel mainPanel = new JPanel();// 创建一个面板对象
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		// 将组件添加到面板中
		mainPanel.add(Lacct);
		mainPanel.add(acctext);
		mainPanel.add(Lpass);
		mainPanel.add(pass);
		// 创建Button按扭
		button1 = new JButton("查询");
		button2 = new JButton("取消查询");
		button2.setEnabled(false);
		// 创建装载Button组件的面板
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, -1, 5, 5));
		// 将Button组件添加到buttonPanel面板中
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		JPanel addPanel = new JPanel();
		addPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		addPanel.add(buttonPanel);// 将buttonPanel面板添加到addPanel面板中
		JLabel balancePrefixL = new JLabel("账户余额:");
		balanceL = new JLabel("查询未知");
		// 将用于显示查询结果的组件放到searchPanel面板中
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		searchPanel.add(balancePrefixL);
		searchPanel.add(balanceL);
		JPanel showPanel = new JPanel();
		showPanel.setLayout(new GridLayout(-1, 1, 5, 5));
		showPanel.add(mainPanel);
		showPanel.add(addPanel);
		showPanel.add(searchPanel);
		setLayout(new BorderLayout());
		add(showPanel, BorderLayout.NORTH);
	}
	private void searchEvents() {// 为查询按扭和取消按扭
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchING();
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelSearch();
			}
		});
	}
	private void searchING() {// 正在查询的过程中
		ensureEventThread();
		button1.setEnabled(false);
		button2.setEnabled(true);
		balanceL.setText("正在查询 请稍候 ...");
		// 获取录入的账号和密码
		String acct = acctext.getText();
		String pin = pass.getText();
		lookupMessage(acct, pin);
	}
	private void lookupMessage(String acct, String pin) {// 设置查询信息
		final String acctNum = acct;
		final String pinNum = pin;
		Runnable lookupRun = new Runnable() {
			public void run() {
				String bal = searchAndCheck(acctNum, pinNum);
				setSafe(bal);
			}
		};
		lookupThread = new Thread(lookupRun, "lookupThread");
		lookupThread.start();
	}
	private String searchAndCheck(String acct, String pin) {// 检查账号和密码是否输入正确
		try {
			Thread.sleep(5000);
			if (!acct.equals("220302113325") && pin.equals("198713")) {
				return "您输入的账号错误！";
			} else if (acct.equals("220302113325") && !pin.equals("198713")) {
				return "您输入的密码错误！";
			}
			return "1,234.56";
		} catch (InterruptedException x) {
			return "取消查询";
		}
	}
	private void setSafe(String newBal) {// 进行安全设置
		final String newBalance = newBal;
		Runnable r = new Runnable() {
			public void run() {
				try {
					setValue(newBalance);
				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		};
		SwingUtilities.invokeLater(r);
	}
	private void setValue(String newBalance) {// 获取查询结果
		ensureEventThread();
		balanceL.setText(newBalance);
		button2.setEnabled(false);// 正在查询的过程中，取消查询按扭是不用的
		button1.setEnabled(true);
	}
	private void cancelSearch() {// 取消查询
		ensureEventThread();
		button2.setEnabled(false); // prevent additional requests
		if (lookupThread != null) {
			lookupThread.interrupt();
		}
	}
	private void ensureEventThread() {
		if (SwingUtilities.isEventDispatchThread()) {
			return;
		}
		throw new RuntimeException("只有线程可以调用此方法");
	}
	public static void main(String[] args) {
		QueryBalance qb = new QueryBalance();
		JFrame f = new JFrame("Balance Lookup");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setContentPane(qb);
		f.setSize(400, 150);
		f.setVisible(true);
	}
}
