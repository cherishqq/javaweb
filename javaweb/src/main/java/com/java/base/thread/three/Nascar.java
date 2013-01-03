package com.java.base.thread.three;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class Nascar extends JFrame {
	private JPanel panel;
	// �������ָ�ʽ
	private BorderLayout borderLayout1 = new BorderLayout();
	private JLabel label = new JLabel();
	// ����JProgressBar����ʵ����
	private JProgressBar bar_1 = new JProgressBar();
	private JProgressBar bar_2 = new JProgressBar();
	private JButton button_1 = new JButton();
	// ����Priority�����ʵ����
	private Priority thread_1 = new Priority(bar_1, 10);
	private Priority thread_2 = new Priority(bar_2, 5);
	private void Initial() throws Exception {// ������ʼ������Ϊ���Ա��������ֵ
		panel = (JPanel) this.getContentPane();
		label.setFont(new java.awt.Font("Dialog", 0, 14));
		label.setToolTipText("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("����-�̵߳����ȼ�(�ұߵ����ȼ�>��ߵ����ȼ�)");
		panel.setLayout(borderLayout1);
		this.setSize(new Dimension(333, 232));
		this.setTitle("��������");
		bar_1.setOrientation(JProgressBar.VERTICAL);// ���ý������Ĵ�ֱ��ʽ
		bar_1.setFont(new java.awt.Font("Dialog", 0, 14));// ���ý���������ʾ����Ĵ�С
		bar_1.setMaximumSize(new Dimension(84, 32764));// ���øý����������ֵ
		bar_1.setPreferredSize(new Dimension(126, 148));// ���ô��������ѡ��С
		bar_1.setString("1�Ų�����");// ��������������ʾ����
		bar_1.setStringPainted(true);
		bar_2.setOrientation(JProgressBar.VERTICAL);
		bar_2.setFont(new java.awt.Font("Dialog", 0, 14));
		bar_2.setPreferredSize(new Dimension(126, 148));
		bar_2.setString("2�Ų�����");
		bar_2.setStringPainted(true);
		button_1.setFont(new java.awt.Font("Dialog", 0, 14));
		button_1.setToolTipText("");
		button_1.setText("��ʼ");
		setSize(500, 500);
		setVisible(true);
		button_1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEvent(e);
			}
		});
		panel.add(label, BorderLayout.NORTH);
		panel.add(bar_1, BorderLayout.WEST);
		panel.add(bar_2, BorderLayout.EAST);
		panel.add(button_1, BorderLayout.SOUTH);
	}
	void AddEvent(ActionEvent e) {// Ϊ��Ť����¼�����
		if (((JButton) e.getSource()).getText().equals("��ʼ")) {
			// ��������ť������"Start"���򵥻�����Ϊ"stop",���Ҵ�������Priority��ʵ�������������̵߳����ȼ���ͬ��Ȼ��ſ��������̡߳�
			this.button_1.setText("ֹͣ");
			Priority thread_1 = new Priority(this.bar_1, Thread.MAX_PRIORITY);
			thread_1.start();
			Priority thread_2 = new Priority(this.bar_2, Thread.MIN_PRIORITY);
			thread_2.start();
		} else {
			this.button_1.setText("��ʼ");
			this.thread_1.stopped = true;
		}
	}
	public static void main(String[] args) {// �ó������ڴ�
		try {
			new Nascar().Initial();// ���øó����Initial����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class Priority extends Thread {// �����߳����ȼ�����
	JProgressBar bar_3;// ����һ������������
	static boolean stopped;// ����һ���Ƿ�ֹͣ�ı�ʶ
	public Priority(JProgressBar bar_3, int priority) {// ���췽��Ϊ��Ա��������ֵ
		this.bar_3 = bar_3;
		this.stopped = false;
		this.setPriority(priority);
	}
	public void run() {// �����߳�
		int min = 0;
		int max = 1000;
		this.bar_3.setMinimum(min);
		this.bar_3.setMaximum(max / 10);
		this.bar_3.setValue(min);
		for (int i = min; i <= max; i++) {
			if (stopped) {
				break;
			} else {
				this.bar_3.setValue((int) (i / 10));
				this.bar_3.setString(String.valueOf(i));
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
