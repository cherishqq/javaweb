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
	private Component comp;// ����һ��Component����
	// ������ʾλ�õ�X��Y�����
	private int initX;
	private int initY;
	// ����ƫ��λ�õ�X��Y�����
	private int offsetX;
	private int offsetY;
	// ��־�Ƿ��ǵ�һ��ִ��
	private boolean firstTime;
	// ����һ��ʵ��Runnable�ӿڵ������ڲ������
	private Runnable runable;
	// ����һ���̶߳���
	private Thread thread;
	// ��ʶ�Ƿ�ֹͣ����
	private volatile boolean flag;
	// ���췽����Ϊ���Ա������ʼ��
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
		Runnable r = new Runnable() {// ʵ��Runnable�ӿڵ������ڲ���
			public void run() {
				try {
					floatING();
				} catch (Exception x) {
					// in case ANY exception slips through
					x.printStackTrace();
				}
			}
		};
		thread = new Thread(r);// �߳�ʵ����
		thread.start();// �����߳�
	}
	private void floatING() {// ִ��Ư��
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
	private void newPosition() {// �����µ�����ֵ
		if (!comp.isVisible()) {// �жϴ�������丸�������Ƿ�ɼ�
			return;
		}
		Component parent = comp.getParent();// ��ô�����ĸ���
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
			// �Ƶ���һ��
			newX += parentSize.width;
		}
		if (newY < 0) {
			newY += parentSize.height;
		}
		comp.setLocation(newX, newY);
		parent.repaint();
	}
	public static void main(String[] args) {// ִ�иó����������
		Component[] comp = new Component[6];// ����һ��Component�������
		comp[0] = new CryptoService("�Ҹ�");
		comp[1] = new CryptoService("����");
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
		JFrame f = new JFrame("Ư��Ч��");
		f.setContentPane(p);
		f.setSize(400, 300);
		f.setVisible(true);
	}
}
class ImageShow extends JComponent {// �������
	private Dimension size;// ��ʾͼ��ĳߴ�
	private volatile int imgLength;// ����ͼ��Ĵ�С
	private Thread thread;// ����һ���̶߳���
	private Image im;// ����һ��ͼ�����
	private static String imgUrl = "";// ��ʾͼƬ��ʾ���̵�λ��
	public ImageShow(String image, int n) {
		imgUrl = image;
		imgLength = 0;
		size = new Dimension(n, n);
		creatImage();
		setMinimumSize(size);// ���ô��������С�ߴ�
		setPreferredSize(size);// ���ô���������ȳߴ�
		setMaximumSize(size);// ���ô���������ߴ�
		setSize(size);// ��������Ĵ�С
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
	private void creatImage() {// ��ȡͼƬ����
		ImageIcon ic = new ImageIcon(imgUrl);
		im = ic.getImage();
	}
	public void paint(Graphics g) {// ����ͼ��
		g.drawImage(im, imgLength, imgLength + 2, this);
	}
	private void showING() {// ��ʾͼ��
		while (true) {
			try {
				Thread.sleep(300); // ����3����
				imgLength = imgLength + 1;
				if (imgLength > 30) {// ���ͼ��߿�ĳ��ȴ���30
					imgLength = 0;// �߿���Ϊ0
				}
				repaint();// ���»���ͼ��
			} catch (InterruptedException x) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
