package com.java.base.thread.six;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Universe extends java.applet.Applet implements Runnable {
	int Width, Height;// ����С����ĳ��Ϳ�
	Thread thread = null;// ����һ���̶߳���
	boolean suspend = false;// �Ƿ���ͣ
	Image im;// ����һ��ͼ�����
	Graphics graphics;// ����һ��Graphics����
	double rot, dx, ddx;// ����double�ͱ���
	int speed, stars, type;// ����int�ͱ���
	double defddx, max;// ����double�ͱ���
	Star pol[]; // �ǹ�
	public void init() {// ��ʼ��AppletС����
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
	public void paint(Graphics g) {// �������
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
	public void start() {// ����AppletС����
		if (thread == null) {
			thread = new Thread(this);
			thread.start();// �����߳�
		}
	}
	public void stop() {// ֹͣ����AppletС����
		if (thread != null) {
			thread.stop();
			thread = null;
		}
	}
	public void run() {// �����߳�
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
	public void update(Graphics g) {// ���»����鲮
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
class Star {// ����������
	int H, V;
	int x, y, z;
	int type;
	Star(int width, int height, int depth, int type) {// ���캯��Ϊ��������ʼ��
		this.type = type;
		H = width / 2;
		V = height / 2;
		x = (int) (Math.random() * width) - H;
		y = (int) (Math.random() * height) - V;
		if ((x == 0) && (y == 0))
			x = 10;
		z = (int) (Math.random() * depth);
	}
	public void DrawSelf(Graphics g, double rot) {// ���������������
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
	public void setColor(Graphics g) {// �����ƵĶ���������ɫ
		if (z > 50) {
			g.setColor(Color.gray);
		} else if (z > 25) {
			g.setColor(Color.lightGray);
		} else {
			g.setColor(Color.white);
		}
	}
}
