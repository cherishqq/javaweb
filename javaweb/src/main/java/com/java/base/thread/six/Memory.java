package com.java.base.thread.six;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

//监控内存
public class Memory extends JFrame {
	private JPanel panel;
	private BorderLayout layout = new BorderLayout();
	// 创建JProgressBar对象并实例化
	private JProgressBar bar_1 = new JProgressBar();
	private JLabel label_1 = new JLabel();
	private JLabel label_2 = new JLabel();
	private void Initial() throws Exception {
		panel = (JPanel) this.getContentPane();
		panel.setLayout(layout);
		this.setSize(new Dimension(305, 215));
		this.setTitle("内存的使用情况");
		label_1.setFont(new java.awt.Font("Dialog", 0, 14));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setText("自定义任务管理器");
		bar_1.setOrientation(JProgressBar.VERTICAL);
		bar_1.setFont(new java.awt.Font("Dialog", 0, 14));
		bar_1.setToolTipText("");
		bar_1.setStringPainted(true);
		label_2.setFont(new java.awt.Font("Dialog", 0, 14));
		label_2.setText("");
		panel.add(bar_1, BorderLayout.CENTER);
		panel.add(label_1, BorderLayout.NORTH);
		panel.add(label_2, BorderLayout.SOUTH);
		ProgressThread pt = new ProgressThread(this.bar_1, this.label_2);
		pt.start();
		this.setVisible(true);
	}
	public static void main(String[] args) {
		try {
			new Memory().Initial();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class ProgressThread extends Thread {
	JProgressBar jpb;
	JLabel jl;
	public ProgressThread(JProgressBar jpb, JLabel jl) {
		this.jpb = jpb;
		this.jl = jl;
	}
	public void run() {
		int min = 0;
		int max = 100;
		int free = 0;
		int totle = 0;
		int status = 0;
		jpb.setMinimum(min);
		jpb.setMaximum(max);
		jpb.setValue(status);
		while (true) {
			totle = (int) (Runtime.getRuntime().totalMemory() / 1024);
			free = (int) (Runtime.getRuntime().freeMemory() / 1024);
			jl.setText("可用内存"
					+ (int) (Runtime.getRuntime().freeMemory() / 1024) + "K"
					+ " 总共分配的内存："
					+ (int) (Runtime.getRuntime().totalMemory() / 1024) + "K");
			status = (int) (free * 100 / totle);
			jpb.setValue(status);
			jpb.setString("可用内存：" + status + "%");
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
