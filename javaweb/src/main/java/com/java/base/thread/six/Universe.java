package com.java.base.thread.six;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Universe extends java.applet.Applet implements Runnable {
	int Width, Height;// 定义小程序的长和宽
	Thread thread = null;// 声明一个线程对象
	boolean suspend = false;// 是否暂停
	Image im;// 声明一个图象对象
	Graphics graphics;// 声明一个Graphics对象
	double rot, dx, ddx;// 声明double型变量
	int speed, stars, type;// 声明int型变量
	double defddx, max;// 声明double型变量
	Star pol[]; // 星光
	public void init() {// 初始化Applet小程序
		rot = 0;
		dx = 0;
		ddx = 0;
		Width = 300;
		Height = 300;
		String theSpeed = "25";
		Show("speed", theSpeed);
		speed = (theSpeed == null) ? 50 : Integer.valueOf(theSpeed).intValue();
		String theStars = "250";
		Show("stars", theStars);
		stars = (theStars == null) ? 30 : Integer.valueOf(theStars).intValue();
		try {
			im = createImage(Width, Height);
			graphics = im.getGraphics();
		} catch (Exception e) {
			graphics = null;
		}
		pol = new Star[stars];
		for (int i = 0; i < stars; i++)
			pol[i] = new Star(Width, Height, 150, type);
		System.out.println(Width + "," + Height);
	}
	public void paint(Graphics g) {// 绘制组件
		if (graphics != null) {
			paintStart(graphics);
			g.drawImage(im, 0, 0, this);
		} else {
			paintStart(g);
		}
	}
	public void paintStart(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Width, Height);
		for (int i = 0; i < stars; i++)
			pol[i].DrawSelf(g, rot);
	}
	public void start() {// 启动Applet小程序
		if (thread == null) {
			thread = new Thread(this);
			thread.start();// 启动线程
		}
	}
	public void stop() {// 停止运行Applet小程序
		if (thread != null) {
			thread.stop();
			thread = null;
		}
	}
	public void run() {// 运行线程
		while (thread != null) {
			rot += dx;
			dx += ddx;
			if (dx > max)
				ddx = -defddx;
			if (dx < -max)
				ddx = defddx;
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
			}
			repaint();
		}
	}
	public void update(Graphics g) {// 重新绘制组伯
		paint(g);
	}
	public void Show(String theString, String theValue) {
		if (theValue == null) {
			System.out.println(theString + " : null");
		} else {
			System.out.println(theString + " : " + theValue);
		}
	}
}
class Star {// 代表星星类
	int H, V;
	int x, y, z;
	int type;
	Star(int width, int height, int depth, int type) {// 构造函数为各变量初始化
		this.type = type;
		H = width / 2;
		V = height / 2;
		x = (int) (Math.random() * width) - H;
		y = (int) (Math.random() * height) - V;
		if ((x == 0) && (y == 0))
			x = 10;
		z = (int) (Math.random() * depth);
	}
	public void DrawSelf(Graphics g, double rot) {// 根据坐标绘制星星
		double X, Y;
		int h, v, hh, vv;
		int d;
		z -= 2;
		if (z < -63)
			z = 100;
		hh = (x * 64) / (64 + z);
		vv = (y * 64) / (64 + z);
		X = (hh * Math.cos(rot)) - (vv * Math.sin(rot));
		Y = (hh * Math.sin(rot)) + (vv * Math.cos(rot));
		h = (int) X + H;
		v = (int) Y + V;
		if ((h < 0) || (h > (2 * H)))
			z = 100;
		if ((v < 0) || (v > (2 * H)))
			z = 100;
		setColor(g);
		if (type == 0) {
			d = (100 - z) / 50;
			if (d == 0)
				d = 1;
			g.fillRect(h, v, d, d);
		} else {
			d = (100 - z) / 20;
			g.drawLine(h - d, v, h + d, v);
			g.drawLine(h, v - d, h, v + d);
			if (z < 50) {
				d /= 2;
				g.drawLine(h - d, v - d, h + d, v + d);
				g.drawLine(h + d, v - d, h - d, v + d);
			}
		}
	}
	public void setColor(Graphics g) {// 给绘制的对象设置颜色
		if (z > 50) {
			g.setColor(Color.gray);
		} else if (z > 25) {
			g.setColor(Color.lightGray);
		} else {
			g.setColor(Color.white);
		}
	}
}
