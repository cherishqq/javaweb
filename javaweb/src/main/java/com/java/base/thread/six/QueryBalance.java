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
	private JTextField acctext;// ����һ��¼���ʺŵ������
	private JTextField pass;// ����һ��¼���ʺ�����������
	private JButton button1;// ����һ����ѯ��Ť
	private JButton button2;// ����һ��ȡ����Ť
	private JLabel balanceL;// ����һ��¼���ʺŵ������
	private volatile Thread lookupThread;// ����һ���߳�
	public QueryBalance() {// ���췽��
		mainFrame();
		searchEvents();
	}
	private void mainFrame() {
		// �������
		JLabel Lacct = new JLabel("�˻����:");
		JLabel Lpass = new JLabel("����:");
		acctext = new JTextField(12);
		pass = new JTextField(4);
		JPanel mainPanel = new JPanel();// ����һ��������
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		// �������ӵ������
		mainPanel.add(Lacct);
		mainPanel.add(acctext);
		mainPanel.add(Lpass);
		mainPanel.add(pass);
		// ����Button��Ť
		button1 = new JButton("��ѯ");
		button2 = new JButton("ȡ����ѯ");
		button2.setEnabled(false);
		// ����װ��Button��������
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, -1, 5, 5));
		// ��Button�����ӵ�buttonPanel�����
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		JPanel addPanel = new JPanel();
		addPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		addPanel.add(buttonPanel);// ��buttonPanel�����ӵ�addPanel�����
		JLabel balancePrefixL = new JLabel("�˻����:");
		balanceL = new JLabel("��ѯδ֪");
		// ��������ʾ��ѯ���������ŵ�searchPanel�����
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
	private void searchEvents() {// Ϊ��ѯ��Ť��ȡ����Ť
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
	private void searchING() {// ���ڲ�ѯ�Ĺ�����
		ensureEventThread();
		button1.setEnabled(false);
		button2.setEnabled(true);
		balanceL.setText("���ڲ�ѯ ���Ժ� ...");
		// ��ȡ¼����˺ź�����
		String acct = acctext.getText();
		String pin = pass.getText();
		lookupMessage(acct, pin);
	}
	private void lookupMessage(String acct, String pin) {// ���ò�ѯ��Ϣ
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
	private String searchAndCheck(String acct, String pin) {// ����˺ź������Ƿ�������ȷ
		try {
			Thread.sleep(5000);
			if (!acct.equals("220302113325") && pin.equals("198713")) {
				return "��������˺Ŵ���";
			} else if (acct.equals("220302113325") && !pin.equals("198713")) {
				return "��������������";
			}
			return "1,234.56";
		} catch (InterruptedException x) {
			return "ȡ����ѯ";
		}
	}
	private void setSafe(String newBal) {// ���а�ȫ����
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
	private void setValue(String newBalance) {// ��ȡ��ѯ���
		ensureEventThread();
		balanceL.setText(newBalance);
		button2.setEnabled(false);// ���ڲ�ѯ�Ĺ����У�ȡ����ѯ��Ť�ǲ��õ�
		button1.setEnabled(true);
	}
	private void cancelSearch() {// ȡ����ѯ
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
		throw new RuntimeException("ֻ���߳̿��Ե��ô˷���");
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
