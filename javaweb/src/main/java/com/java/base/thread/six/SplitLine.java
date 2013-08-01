package com.java.base.thread.six;


import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;

public class SplitLine extends Applet implements Runnable {
	private Image moveleft__1, moveright_1, moveleft_2, moveright_2, temp;// 声明Image对象
	private Image image;//
	private Graphics graphics;
	private Thread thread = null;
	private MediaTracker img_tracker;
	private int height, width;
	public void init() {// 初始化Applet小程序
		// 创建Image对象
		moveright_1 = getImage(getDocumentBase(), "3.jpg");
		moveright_2 = getImage(getDocumentBase(), "4.jpg");
		moveleft__1 = getImage(getDocumentBase(), "1.jpg");
		moveleft_2 = getImage(getDocumentBase(), "2.jpg");
		// 创建MediaTracker对象
		img_tracker = new MediaTracker(this);
		// 将图片对象加载到媒体跟踪器中
		img_tracker.addImage(moveright_1, 0);
		img_tracker.addImage(moveleft__1, 0);
		img_tracker.addImage(moveright_2, 0);
		img_tracker.addImage(moveleft_2, 0);
		// 获取Applet的长和宽
		width = this.size().width;
		height = this.size().height;
		try {
			img_tracker.waitForID(0);// 跟踪指定ID的图象
		} catch (InterruptedException e) {
		}
		// 创建图象区
		image = createImage(width, height);
		// 创建Graphics对象
		graphics = image.getGraphics();
	}
	public void start() {// Applet小程序的start方法
		if (thread == null) {
			thread = new Thread(this);
			thread.start();// 开始运行线程
		}
	}
	public void run() {// 线程的run方法
		Color fg = this.getForeground();
		int imgWidth, imageHeight, x = 0, y = 0;
		boolean forward = true;
		imgWidth = moveright_1.getWidth(this);
		imageHeight = moveright_1.getHeight(this);
		y = (height - imageHeight) / 2;
		fg = Color.blue;// 设置分割线的颜色
		try {
			while (thread != null) {
				thread.sleep(200);
				if (forward) {
					x += 15;
					if ((x % 2) == 1) {
						temp = moveright_1;
					} else {
						temp = moveright_2;
					}
					if (x >= (width - imgWidth)) {
						forward = false;
					}
				} else {
					x -= 15;
					if ((x % 2) == 1) {
						temp = moveleft__1;
					} else {
						temp = moveleft_2;
					}
					if (x == 0) {
						forward = true;
					}
				}
				graphics.setColor(Color.white);// 设置小程序的背景颜色
				graphics.fillRect(0, 0, width, height);
				graphics.setColor(fg.brighter().darker());
				graphics.drawLine(0, (height - imageHeight) / 2 + imageHeight,
						width, (height - imageHeight) / 2 + imageHeight);
				graphics.setColor(fg.darker().brighter());
				graphics.drawLine(0, (height - imageHeight) / 2 + imageHeight,
						width, (height - imageHeight) / 2 + imageHeight);
				graphics.drawImage(temp, x, y, this);
				repaint();
			}
		} catch (InterruptedException e) {
		}
	}
	public void update(Graphics g) {
		paint(g);
	}
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}
}
