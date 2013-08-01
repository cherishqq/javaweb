package com.java.base.thread.four;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class DigitalTimer extends JLabel implements ActionListener {// 数字计时器
	private volatile String value;// 要在文本框中显示的内容
	private Thread thread;// 声明一个线程
	private volatile boolean isStop;// 标识是否停止计时
	Runnable r = null;//声明一个Runnable子类的对象
	public DigitalTimer() {// 构造方法为Swing组件初始化
		setBorder(BorderFactory.createLineBorder(Color.blue));
		setHorizontalAlignment(SwingConstants.RIGHT);
		this.setForeground(Color.red);
		setFont(new Font("SansSerif", Font.BOLD, 16));
		setText("00000.0"); // use to size component
		setMinimumSize(getPreferredSize());
		setPreferredSize(getPreferredSize());
		setSize(getPreferredSize());
		value = "0.0";
		setText(value);
		isStop = true;
		r = new Runnable() {// 创建一个实现Runnable接口内部类对象
			public void run() {
				try {
					startTime();
				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		};
		thread = new Thread(r, "Stopwatch");// 根据Runnable对象来创建一个线程
		thread.start();// 启动线程
	}
	private void startTime() {// 开始计时
		long startTime = System.currentTimeMillis();// 线程开始运行的时间
		int n = 0;// 一个时间量
		long sleepTime = 100;// 线程正在休眠的时间
		long nextSleepTime = 100;// 接下来准备休眠的时间
		DecimalFormat format = new DecimalFormat("0.0");
		Runnable updateText = new Runnable() {
			public void run() {
				setText(value);
			}
		};
		while (isStop) {
			try {
				Thread.sleep(nextSleepTime);
				n++;
				long nowTime = System.currentTimeMillis();// 获取系统的当前时间
				long elapsedTime = nowTime - startTime;// 经过的时间=当前时间-开始时间
				nextSleepTime = sleepTime + ((n * 100) - elapsedTime);
				if (nextSleepTime < 0) {
					nextSleepTime = 0;
				}
				value = format.format(elapsedTime / 1000.0);
				System.out.println(value);
				SwingUtilities.invokeAndWait(updateText);// 使该线程在Swing组件上同步执行
			} catch (InterruptedException ix) {
				return;// 停止运行
			} catch (InvocationTargetException x) {
				x.printStackTrace();
			}
		}
	}
	public void endTime() {// 结束计时
		isStop = false;
		thread.interrupt();// 中断线程
	}
	public boolean isAlive() {
		return thread.isAlive();// 判断该线程是否是活动的
	}
	public static void main(String[] args) {
		DigitalTimer dt = new DigitalTimer();// 创建一个本类的实例化对象
		JPanel p = new JPanel(new FlowLayout());// 创建一个面板对象，其布局方式为流布局
		p.add(dt);// 将类对象添加到面板中
		JFrame frame = new JFrame("数字计时器");// 创建一个框架
		// 创建2个JButton按扭，其作用是开始计时和停止计时。并为其添加相应的事件处理
		JButton b1 = new JButton();
		b1.setText("开始");// 为接扭设置文本文字
		b1.setBounds(20, 40, 60, 20);// 设置按扭的大小
		b1.setActionCommand("start");// 设置激发此按扭的事件命令名称
		b1.addActionListener(dt);// 添加监听事件
		JButton b2 = new JButton();
		b2.setText("停止");
		b2.setBounds(80, 40, 60, 20);
		b2.setActionCommand("end");// 设置激发此按扭的事件命令名称
		b2.addActionListener(dt);// 添加监听事件
		frame.setContentPane(p);// 在该框架中添加面板
		frame.add(b1);// 在该框架中添加按扭组件
		frame.add(b2);
		frame.setSize(250, 100);// 设置框架的大小
		frame.setVisible(true);// 设置框架为可见
	}
	public void actionPerformed(ActionEvent e) {// 事件处理
		if (e.getActionCommand().equals("start")) {// 如果激发开始按扭
			if (!isAlive()) {// 判断如果当前的线程不处于可活动状态
				isStop = true;// 将状态设置为true
				thread = new Thread(r, "Stopwatch");// 重新根据Runnable对象来创建一个新的线程
				thread.start();// 启动新线程
			} else {
				startTime();
			}
		} else if (e.getActionCommand().equals("end")) {// 如果激发停止按扭
			endTime();// 则调用endTime方法，中断当前正在运行的线程
		}
	}
}
