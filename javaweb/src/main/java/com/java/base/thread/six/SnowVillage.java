package com.java.base.thread.six;

import java.applet.Applet;
import java.awt.*;
import java.util.Random;

public class SnowVillage extends Applet implements Runnable {

	Thread thread;// 声明主线程

	Image off, images[];// 声明图片对象和图片数组

	Random random;// 声明一个随机数对象

	int flag, sonwNum, wind, thread_1, size;// 声明一些int型变量

	int[] X, Y;// 声明两个int型数组，分别表示X和Y坐标点

	long time = 0;// 表示时间

	Dimension ds;// 声明一个Dimension对象

	MediaTracker mt;// 声明一个MediaTracker对象

	int getValue(String s1, int s2, int max, int min) {// 获取HTML标记中命名参数的值

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

	public void init() {// Applet小程序初始化
		this.setSize(300, 200);
		random = new Random();
		ds = getSize();
		off = createImage(ds.width, ds.height);// 创建一个图象
		sonwNum = getValue("sonwNum", 100, 500, 0);// 获取雪片的个数
		size = getValue("size", 3, 10, 3);// 获取雪片的大小
		thread_1 = getValue("threadsleep", 80, 1000, 10);// 获取休眠的时间
		// 获取绘制雪片的XY坐标值
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

	public void start() {// 启动小程序

		if (thread == null) {

			thread = new Thread(this);
			thread.start();// 启动线程

		}

	}

	public void stop() {// 停止运行小程序

		thread = null;

	}

	public void run() {// 运行线程

		while (thread != null) {

			try {

				Thread.sleep(thread_1);

			} catch (InterruptedException _ex) {
				return;
			}
			repaint();

		}

	}

	public void snow(Graphics g) {// 绘制雪片

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

	public void paint(Graphics g) {// 绘制组件

		off.getGraphics().setColor(Color.black);
		off.getGraphics().fillRect(0, 0, ds.width, ds.height);
		off.getGraphics().drawImage(images[0], 0, 0, this);
		snow(off.getGraphics());
		g.drawImage(off, 0, 0, null);

	}

	public void update(Graphics g) {// 重新绘制组件

		paint(g);

	}

}