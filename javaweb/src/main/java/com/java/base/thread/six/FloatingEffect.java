package com.java.base.thread.six;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class FloatingEffect extends Object {
	private Component comp;// 创建一个Component对象
	// 定义显示位置的X、Y坐标点
	private int initX;
	private int initY;
	// 定义偏移位置的X、Y坐标点
	private int offsetX;
	private int offsetY;
	// 标志是否是第一次执行
	private boolean firstTime;
	// 声明一个实现Runnable接口的匿名内部类对象
	private Runnable runable;
	// 声明一个线程对象
	private Thread thread;
	// 标识是否停止浮动
	private volatile boolean flag;
	// 构造方法，为其成员变量初始化
	public FloatingEffect(Component comp, int initX, int initY, int offsetX,
			int offsetY) {
		this.comp = comp;
		this.initX = initX;
		this.initY = initY;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		firstTime = true;
		runable = new Runnable() {
			public void run() {
				newPosition();
			}
		};
		flag = true;
		Runnable r = new Runnable() {// 实现Runnable接口的匿名内部类
			public void run() {
				try {
					floatING();
				} catch (Exception x) {
					// in case ANY exception slips through
					x.printStackTrace();
				}
			}
		};
		thread = new Thread(r);// 线程实例化
		thread.start();// 启动线程
	}
	private void floatING() {// 执行漂浮
		while (flag) {
			try {
				Thread.sleep(200);
				SwingUtilities.invokeAndWait(runable);
			} catch (InterruptedException ix) {
				// ignore
			} catch (Exception x) {
				x.printStackTrace();
			}
		}
	}
	private void newPosition() {// 更新新的坐标值
		if (!comp.isVisible()) {// 判断此组件在其父容器内是否可见
			return;
		}
		Component parent = comp.getParent();// 获得此组件的父级
		if (parent == null) {
			return;
		}
		Dimension parentSize = parent.getSize();
		if ((parentSize == null) && (parentSize.width < 1)
				&& (parentSize.height < 1)) {
			return;
		}
		int newX = 0;
		int newY = 0;
		if (firstTime) {
			firstTime = false;
			newX = initX;
			newY = initY;
		} else {
			Point loc = comp.getLocation();
			newX = loc.x + offsetX;
			newY = loc.y + offsetY;
		}
		newX = newX % parentSize.width;
		newY = newY % parentSize.height;
		if (newX < 0) {
			// 绕到另一侧
			newX += parentSize.width;
		}
		if (newY < 0) {
			newY += parentSize.height;
		}
		comp.setLocation(newX, newY);
		parent.repaint();
	}
	public static void main(String[] args) {// 执行该程序的主入中
		Component[] comp = new Component[6];// 创建一个Component组件数组
		comp[0] = new CryptoService("幸福");
		comp[1] = new CryptoService("快乐");
		comp[2] = new ImageShow("E:\\tupian\\1.jpg", 30);
		comp[3] = new ImageShow("E:\\tupian\\1.jpg", 30);
		comp[4] = new ImageShow("E:\\tupian\\2.jpg", 100);
		comp[5] = new ImageShow("E:\\tupian\\2.jpg", 100);
		JPanel p = new JPanel();
		p.setBackground(Color.white);
		p.setLayout(null);
		for (int i = 0; i < comp.length; i++) {
			p.add(comp[i]);
			int x = (int) (300 * Math.random());
			int y = (int) (200 * Math.random());
			int xOff = 2 - (int) (5 * Math.random());
			int yOff = 2 - (int) (5 * Math.random());
			new FloatingEffect(comp[i], x, y, xOff, yOff);
		}
		JFrame f = new JFrame("漂浮效果");
		f.setContentPane(p);
		f.setSize(400, 300);
		f.setVisible(true);
	}
}
class ImageShow extends JComponent {// 其类的主
	private Dimension size;// 显示图像的尺寸
	private volatile int imgLength;// 绘制图像的大小
	private Thread thread;// 声明一个线程对象
	private Image im;// 声明一个图像对象
	private static String imgUrl = "";// 表示图片所示磁盘的位置
	public ImageShow(String image, int n) {
		imgUrl = image;
		imgLength = 0;
		size = new Dimension(n, n);
		creatImage();
		setMinimumSize(size);// 设置此组件的最小尺寸
		setPreferredSize(size);// 设置此组件的首先尺寸
		setMaximumSize(size);// 设置此组件的最大尺寸
		setSize(size);// 调整组件的大小
		Runnable r = new Runnable() {
			public void run() {
				try {
					showING();
				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		};
		thread = new Thread(r, "ImageShow");
		thread.start();
	}
	private void creatImage() {// 获取图片对象
		ImageIcon ic = new ImageIcon(imgUrl);
		im = ic.getImage();
	}
	public void paint(Graphics g) {// 绘制图像
		g.drawImage(im, imgLength, imgLength + 2, this);
	}
	private void showING() {// 显示图象
		while (true) {
			try {
				Thread.sleep(300); // 休眠3毫秒
				imgLength = imgLength + 1;
				if (imgLength > 30) {// 如果图象边框的长度大于30
					imgLength = 0;// 边框设为0
				}
				repaint();// 重新绘制图像
			} catch (InterruptedException x) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
