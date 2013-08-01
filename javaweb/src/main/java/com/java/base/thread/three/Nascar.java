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
	// 声明布局格式
	private BorderLayout borderLayout1 = new BorderLayout();
	private JLabel label = new JLabel();
	// 创建JProgressBar对象并实例化
	private JProgressBar bar_1 = new JProgressBar();
	private JProgressBar bar_2 = new JProgressBar();
	private JButton button_1 = new JButton();
	// 创建Priority类对象并实例化
	private Priority thread_1 = new Priority(bar_1, 10);
	private Priority thread_2 = new Priority(bar_2, 5);
	private void Initial() throws Exception {// 创建初始化程序，为其成员变量赋初值
		panel = (JPanel) this.getContentPane();
		label.setFont(new java.awt.Font("Dialog", 0, 14));
		label.setToolTipText("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("赛车-线程的优先级(右边的优先级>左边的优先级)");
		panel.setLayout(borderLayout1);
		this.setSize(new Dimension(333, 232));
		this.setTitle("赛车比赛");
		bar_1.setOrientation(JProgressBar.VERTICAL);// 设置进度条的垂直方式
		bar_1.setFont(new java.awt.Font("Dialog", 0, 14));// 设置进度条上显示字体的大小
		bar_1.setMaximumSize(new Dimension(84, 32764));// 设置该进度条的最大值
		bar_1.setPreferredSize(new Dimension(126, 148));// 设置此组件的首选大小
		bar_1.setString("1号参赛者");// 给进度条设置显示文字
		bar_1.setStringPainted(true);
		bar_2.setOrientation(JProgressBar.VERTICAL);
		bar_2.setFont(new java.awt.Font("Dialog", 0, 14));
		bar_2.setPreferredSize(new Dimension(126, 148));
		bar_2.setString("2号参赛者");
		bar_2.setStringPainted(true);
		button_1.setFont(new java.awt.Font("Dialog", 0, 14));
		button_1.setToolTipText("");
		button_1.setText("开始");
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
	void AddEvent(ActionEvent e) {// 为按扭添加事件监听
		if (((JButton) e.getSource()).getText().equals("开始")) {
			// 当单击按钮文字是"Start"，则单击后设为"stop",并且创建两个Priority的实例，但这两个线程的优先级不同，然后才可以运行线程。
			this.button_1.setText("停止");
			Priority thread_1 = new Priority(this.bar_1, Thread.MAX_PRIORITY);
			thread_1.start();
			Priority thread_2 = new Priority(this.bar_2, Thread.MIN_PRIORITY);
			thread_2.start();
		} else {
			this.button_1.setText("开始");
			this.thread_1.stopped = true;
		}
	}
	public static void main(String[] args) {// 该程序的入口处
		try {
			new Nascar().Initial();// 调用该程序的Initial方法
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class Priority extends Thread {// 设置线程优先级的类
	JProgressBar bar_3;// 声明一个进度条对象
	static boolean stopped;// 声明一个是否停止的标识
	public Priority(JProgressBar bar_3, int priority) {// 构造方法为成员变量赋初值
		this.bar_3 = bar_3;
		this.stopped = false;
		this.setPriority(priority);
	}
	public void run() {// 启动线程
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
