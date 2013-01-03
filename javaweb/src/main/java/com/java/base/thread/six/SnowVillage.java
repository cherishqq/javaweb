package com.java.base.thread.six;

import java.applet.Applet;
import java.awt.*;
import java.util.Random;

public class SnowVillage extends Applet implements Runnable {

	Thread thread;// �������߳�

	Image off, images[];// ����ͼƬ�����ͼƬ����

	Random random;// ����һ�����������

	int flag, sonwNum, wind, thread_1, size;// ����һЩint�ͱ���

	int[] X, Y;// ��������int�����飬�ֱ��ʾX��Y�����

	long time = 0;// ��ʾʱ��

	Dimension ds;// ����һ��Dimension����

	MediaTracker mt;// ����һ��MediaTracker����

	int getValue(String s1, int s2, int max, int min) {// ��ȡHTML���������������ֵ

		String s = getParameter(s1);
		if (s != null) {

			if ((s2 = Integer.parseInt(s)) > max)
				return max;
			else if (s2 < min)
				return min;
			else
				return s2;

		} else
			return s2;

	}

	public void init() {// AppletС�����ʼ��
		this.setSize(300, 200);
		random = new Random();
		ds = getSize();
		off = createImage(ds.width, ds.height);// ����һ��ͼ��
		sonwNum = getValue("sonwNum", 100, 500, 0);// ��ȡѩƬ�ĸ���
		size = getValue("size", 3, 10, 3);// ��ȡѩƬ�Ĵ�С
		thread_1 = getValue("threadsleep", 80, 1000, 10);// ��ȡ���ߵ�ʱ��
		// ��ȡ����ѩƬ��XY����ֵ
		X = new int[sonwNum];
		Y = new int[sonwNum];
		for (int i = 0; i < sonwNum; i++) {

			X[i] = random.nextInt() % (ds.width / 2) + ds.width / 2;
			Y[i] = random.nextInt() % (ds.height / 2) + ds.height / 2;

		}

		mt = new MediaTracker(this);
		images = new Image[1];
		images[0] = getImage(getDocumentBase(), "xue.jpg");
		mt.addImage(images[0], 0);
		try {

			mt.waitForID(0);

		} catch (InterruptedException _ex) {
			return;
		}
		flag = 0;

	}

	public void start() {// ����С����

		if (thread == null) {

			thread = new Thread(this);
			thread.start();// �����߳�

		}

	}

	public void stop() {// ֹͣ����С����

		thread = null;

	}

	public void run() {// �����߳�

		while (thread != null) {

			try {

				Thread.sleep(thread_1);

			} catch (InterruptedException _ex) {
				return;
			}
			repaint();

		}

	}

	public void snow(Graphics g) {// ����ѩƬ

		g.setColor(Color.white);
		for (int i = 0; i < sonwNum; i++) {

			g.fillOval(X[i], Y[i], size, size);
			X[i] += random.nextInt() % 2 + wind;
			Y[i] += (random.nextInt() % 6 + 5) / 5 + 1;
			if (X[i] >= ds.width)
				X[i] = 0;
			if (X[i] < 0)
				X[i] = ds.width - 1;
			if (Y[i] >= ds.height || Y[i] < 0) {

				X[i] = Math.abs(random.nextInt() % ds.width);
				Y[i] = 0;

			}

		}

		wind = random.nextInt() % 5 - 2;

	}

	public void paint(Graphics g) {// �������

		off.getGraphics().setColor(Color.black);
		off.getGraphics().fillRect(0, 0, ds.width, ds.height);
		off.getGraphics().drawImage(images[0], 0, 0, this);
		snow(off.getGraphics());
		g.drawImage(off, 0, 0, null);

	}

	public void update(Graphics g) {// ���»������

		paint(g);

	}

}