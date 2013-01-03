package com.java.base.thread.one;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Date;

public class MoveClock extends Applet implements Runnable {
	Thread hourThread = null; 		// 时针线程
	Thread minuteThread = null; 		// 分针线程
	Thread secondThread = null; 		// 秒针线程
	int hourX, hourY, minuteX, minuteY, secondX, secondY;// 表示时,分,秒针端点的XY坐标点
	int currentHour = 0; 				// 获取当前时间中代表小时的数字值
	int currentMinute = 0; 			// 获取当前时间中代表分钟的数字值
	int currentSecond = 0; 			// 获取当前时间中代表秒的数字值
	Graphics secondGraphics = null; 	// 绘制秒针的Graphics对象
	Graphics minuteGraphics = null; 	// 绘制分针的Graphics对象
	Graphics hourGraphics = null; 		// 绘制时针的Graphics对象
	Graphics2D minuteg2D = null;		// 创建Graphics2D对象
	Graphics2D hourg2D = null;		// 创建Graphics2D对象
	Graphics2D secondg2D = null;		// 创建Graphics2D对象
	double drawX[] = new double[61]; 	// 存放表盘刻度的X轴数组
	double drawY[] = new double[61]; 	// 存放表盘刻度的Y轴数组
	double dial_x[] = new double[61]; 	// 供绘制表盘使的x点坐标值
	double dial_y[] = new double[61];	// 供绘制表盘使的Y点坐标值
	int isRestart = 0; 	// 判断是否重新开始
	public void init() { 	// 数据初始化
		hourGraphics = this.getGraphics(); 		// 实例化时针Graphics对象
		hourGraphics.setColor(Color.white); 	// 设置时针的颜色
		hourg2D = (Graphics2D) hourGraphics;	// 实例化时针Graphics2D对象
		hourGraphics.translate(200, 200); 		// 进行坐标系统变换原点设在(200,200)处
		minuteGraphics = this.getGraphics(); 	// 实例化分针Graphics对象
		minuteg2D = (Graphics2D) minuteGraphics;	// 实例化分针Graphics2D对象
		minuteGraphics.setColor(Color.green); 		// 设置分针的颜色
		minuteGraphics.translate(200, 200); 		// 进行坐标系统变换，原点设在(200,200)处
		secondGraphics = this.getGraphics(); 		// 实例化秒针Graphics对象
		secondg2D = (Graphics2D) secondGraphics;	// 实例化秒针Graphics2D对象
		secondGraphics.setColor(Color.blue); 		// 设置秒针的颜色
		secondGraphics.translate(200, 200); 		// 进行坐标系统变换，原点设在(200,200)处
		drawX[0] = 0;
		// 各个时针12点处的位置坐标（按新坐标系的坐标）
		drawY[0] = -120;
		dial_x[0] = 0;
		// 12点处的刻度位置坐标（按新坐标系的坐标）
		dial_y[0] = -140;
		double jiaodu = 6 * Math.PI / 180;
		// 表盘分割成60分，将分割点的坐标存放在数组中
		for (int i = 0; i < 60; i++) {
			drawX[i + 1] = drawX[i] * Math.cos(jiaodu) - Math.sin(jiaodu)
					* drawY[i];
			drawY[i + 1] = drawY[i] * Math.cos(jiaodu) + drawX[i]
					* Math.sin(jiaodu);
		}
		drawX[60] = 0;
		drawY[60] = -120;
		// 表盘分割成60分，将分割点的坐标存放在绘制数组中
		for (int i = 0; i < 60; i++) {
			dial_x[i + 1] = dial_x[i] * Math.cos(jiaodu) - Math.sin(jiaodu)
					* dial_y[i];
			dial_y[i + 1] = dial_y[i] * Math.cos(jiaodu) + Math.sin(jiaodu)
					* dial_x[i];
		}
		dial_x[60] = 0;
		dial_y[60] = -140;
	}
	public void start() {
		if (isRestart >= 1) {
			secondThread.interrupt(); 	// 唤醒线程
			minuteThread.interrupt();
			hourThread.interrupt();
		}
		hourThread = new Thread(this); 	// 创建时针线程
		minuteThread = new Thread(this); 	// 创建分针线程
		secondThread = new Thread(this); 	// 创建秒针线程
		secondThread.start(); 			// 启动秒针线程
		minuteThread.start(); 			// 启动分针线程
		hourThread.start(); 				// 启动时针线程
		isRestart++;
		if (isRestart >= 2)
			isRestart = 1;
	}
	public void stop() {
		secondThread.interrupt(); // 唤醒线程
		minuteThread.interrupt();
		hourThread.interrupt();
	}
	public void paint(Graphics g) { // 绘制图形
		this.setBackground(Color.black);
		this.start();
		g.drawOval(50, 50, 300, 300);	// 表盘的外圈
		g.translate(200, 200); 		// 进行坐标系统变换
		for (int i = 0; i < 60; i++) { 		// 绘制表盘的小刻度和大刻度
			if (i % 5 == 0) {
				g.setColor(Color.red); // 设置颜色
				g.fillOval((int) dial_x[i], (int) dial_y[i], 10, 10);
			} else
				g.fillOval((int) dial_x[i], (int) dial_y[i], 5, 5);
		}
	}
	public void run() { 			// 实现Thread的方法,开始线程
		Date date = new Date(); 	// 获取本地时间
		String string = date.toString();
		currentHour = Integer.parseInt(string.substring(11, 13)); 	// 获得当前时间的小时
		currentMinute = Integer.parseInt(string.substring(14, 16)); // 获取当前时间的分钟
		currentSecond = Integer.parseInt(string.substring(17, 19));// 获取当前时间的秒钟
		if (Thread.currentThread() == secondThread) { 		// 如果当前线程是秒线程
			secondX = (int) drawX[currentSecond]; 			// 秒针初始化
			secondY = (int) drawX[currentSecond];
			// 用背景色清除前一秒的秒针
			secondGraphics.drawLine(0, 0, secondX, secondY);
			secondg2D.setStroke(new BasicStroke(2.0f));		// 设置所绘制秒针的宽度
			int i = currentSecond;
			while (true) {
				try {
					secondThread.sleep(1000); 			// 每隔一秒休眠
					Color c = getBackground(); 			// 获取背景颜色
					secondGraphics.setColor(c); 			// 设置秒针的颜色
					// 用背景色清除前一秒的秒针
					secondGraphics.drawLine(0, 0, secondX, secondY);
					secondg2D.setStroke(new BasicStroke(2.0f));
					// 秒针与分针重合,恢复分针显示
					if ((secondX == minuteX) && (secondY == minuteY)) {
						// 用背景色清除前一分的分针
						minuteGraphics.drawLine(0, 0, minuteX, minuteY);
					}
					// 秒针与时针重合，恢复时针的显示
					if ((secondX == hourX) && (secondY == hourY)) {
						// 用背景色清除前一时的时针
						hourGraphics.drawLine(0, 0, hourX, hourY);
						hourg2D.setStroke(new BasicStroke(4.0f));// 设置所绘制时针的宽度
					}
				} catch (InterruptedException e) { 	// 捕获异常
					Color c = getBackground(); 	// 获取背景颜色
					secondGraphics.setColor(c);	// 设置秒针的颜色
					// 用背景色清除秒针
					secondGraphics.drawLine(0, 0, secondX, secondY);
					secondg2D.setStroke(new BasicStroke(2.0f));
					return;
				}
				secondX = (int) drawX[(i + 1) % 60]; 	// 秒针向前走一个单位
				secondY = (int) drawY[(i + 1) % 60]; 	// 每一秒走6度（一个单位格）
				secondGraphics.setColor(Color.blue);	// 绘制秒针的颜色
				// 用背景色清除前一秒的秒针
				secondGraphics.drawLine(0, 0, secondX, secondY);
				secondg2D.setStroke(new BasicStroke(2.0f));
				i++;
			}
		}
		if (Thread.currentThread() == minuteThread) { 		// 如果当前线程是分线程
			minuteX = (int) drawX[currentMinute];
			minuteY = (int) drawY[currentMinute];
			minuteGraphics.drawLine(0, 0, minuteX, minuteY);
			minuteg2D.setStroke(new BasicStroke(3.0f));
			int i = currentMinute; // 获取当前分钟
			while (true) {
				try { // 第一次过60-second秒就前进一分钟，以后每过60秒前进一分钟
					minuteThread.sleep(1000 * 60 - currentSecond * 1000);
					currentSecond = 0;
					Color c = getBackground(); 		// 获取背景颜色
					minuteGraphics.setColor(c); 		// 设置分针的颜色
					minuteg2D.setStroke(new BasicStroke(3.0f));// 设置所绘制分针的宽度
					minuteGraphics.drawLine(0, 0, minuteX, minuteY);
					// 如果时针和分针重合
					if ((hourX == minuteX) && (hourY == minuteY)) {
						hourGraphics.drawLine(0, 0, minuteX, minuteY);
						hourg2D.setStroke(new BasicStroke(4.0f));
					}
				} catch (InterruptedException e) {
					return;
				}
				minuteX = (int) drawX[(i + 1) % 60]; 		// 分针向前走一个单位
				minuteY = (int) drawY[(i + 1) % 60]; 		// 每一分走6度（一个单位格）
				minuteGraphics.setColor(Color.BLUE); 	// 绘制分针的颜色
				minuteg2D.setStroke(new BasicStroke(3.0f));
				minuteGraphics.drawLine(0, 0, minuteX, minuteY);
				i++;
				currentSecond = 0;
			}
		}
		if (Thread.currentThread() == hourThread) { // 如果当前线程是时线程
			int h = currentHour % 12;
			hourX = (int) drawX[h * 5 + currentMinute / 12];
			hourY = (int) drawY[h * 5 + currentMinute / 12];
			int i = h * 5 + currentMinute / 12;
			hourGraphics.drawLine(0, 0, hourX, hourY);
			hourg2D.setStroke(new BasicStroke(4.0f));
			while (true) {
				try {
					// 第一次过12-minute%12分钟就前进一个刻度,以后每过12分钟前进一刻度
					hourThread.sleep(1000 * 60 * 12 - 1000 * 60
							* (currentMinute % 12) - currentSecond * 1000);
					currentMinute = 0;
					Color c = getBackground();
					hourGraphics.setColor(c);
					hourGraphics.drawLine(0, 0, hourX, hourY);
					hourg2D.setStroke(new BasicStroke(4.0f));
				} catch (InterruptedException e) {
					return;
				}
				hourX = (int) drawX[(i + 1) % 60];
				hourY = (int) drawY[(i + 1) % 60];
				hourGraphics.setColor(Color.BLACK);
				hourGraphics.drawLine(0, 0, hourX, hourY);
				hourg2D.setStroke(new BasicStroke(4.0f));
				i++;
				currentMinute = 0;
			}
		}
	}
}
