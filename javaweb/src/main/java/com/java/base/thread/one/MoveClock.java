package com.java.base.thread.one;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Date;

public class MoveClock extends Applet implements Runnable {
	Thread hourThread = null; 		// ʱ���߳�
	Thread minuteThread = null; 		// �����߳�
	Thread secondThread = null; 		// �����߳�
	int hourX, hourY, minuteX, minuteY, secondX, secondY;// ��ʾʱ,��,����˵��XY�����
	int currentHour = 0; 				// ��ȡ��ǰʱ���д���Сʱ������ֵ
	int currentMinute = 0; 			// ��ȡ��ǰʱ���д�����ӵ�����ֵ
	int currentSecond = 0; 			// ��ȡ��ǰʱ���д����������ֵ
	Graphics secondGraphics = null; 	// ���������Graphics����
	Graphics minuteGraphics = null; 	// ���Ʒ����Graphics����
	Graphics hourGraphics = null; 		// ����ʱ���Graphics����
	Graphics2D minuteg2D = null;		// ����Graphics2D����
	Graphics2D hourg2D = null;		// ����Graphics2D����
	Graphics2D secondg2D = null;		// ����Graphics2D����
	double drawX[] = new double[61]; 	// ��ű��̶̿ȵ�X������
	double drawY[] = new double[61]; 	// ��ű��̶̿ȵ�Y������
	double dial_x[] = new double[61]; 	// �����Ʊ���ʹ��x������ֵ
	double dial_y[] = new double[61];	// �����Ʊ���ʹ��Y������ֵ
	int isRestart = 0; 	// �ж��Ƿ����¿�ʼ
	public void init() { 	// ���ݳ�ʼ��
		hourGraphics = this.getGraphics(); 		// ʵ����ʱ��Graphics����
		hourGraphics.setColor(Color.white); 	// ����ʱ�����ɫ
		hourg2D = (Graphics2D) hourGraphics;	// ʵ����ʱ��Graphics2D����
		hourGraphics.translate(200, 200); 		// ��������ϵͳ�任ԭ������(200,200)��
		minuteGraphics = this.getGraphics(); 	// ʵ��������Graphics����
		minuteg2D = (Graphics2D) minuteGraphics;	// ʵ��������Graphics2D����
		minuteGraphics.setColor(Color.green); 		// ���÷������ɫ
		minuteGraphics.translate(200, 200); 		// ��������ϵͳ�任��ԭ������(200,200)��
		secondGraphics = this.getGraphics(); 		// ʵ��������Graphics����
		secondg2D = (Graphics2D) secondGraphics;	// ʵ��������Graphics2D����
		secondGraphics.setColor(Color.blue); 		// �����������ɫ
		secondGraphics.translate(200, 200); 		// ��������ϵͳ�任��ԭ������(200,200)��
		drawX[0] = 0;
		// ����ʱ��12�㴦��λ�����꣨��������ϵ�����꣩
		drawY[0] = -120;
		dial_x[0] = 0;
		// 12�㴦�Ŀ̶�λ�����꣨��������ϵ�����꣩
		dial_y[0] = -140;
		double jiaodu = 6 * Math.PI / 180;
		// ���̷ָ��60�֣����ָ�����������������
		for (int i = 0; i < 60; i++) {
			drawX[i + 1] = drawX[i] * Math.cos(jiaodu) - Math.sin(jiaodu)
					* drawY[i];
			drawY[i + 1] = drawY[i] * Math.cos(jiaodu) + drawX[i]
					* Math.sin(jiaodu);
		}
		drawX[60] = 0;
		drawY[60] = -120;
		// ���̷ָ��60�֣����ָ����������ڻ���������
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
			secondThread.interrupt(); 	// �����߳�
			minuteThread.interrupt();
			hourThread.interrupt();
		}
		hourThread = new Thread(this); 	// ����ʱ���߳�
		minuteThread = new Thread(this); 	// ���������߳�
		secondThread = new Thread(this); 	// ���������߳�
		secondThread.start(); 			// ���������߳�
		minuteThread.start(); 			// ���������߳�
		hourThread.start(); 				// ����ʱ���߳�
		isRestart++;
		if (isRestart >= 2)
			isRestart = 1;
	}
	public void stop() {
		secondThread.interrupt(); // �����߳�
		minuteThread.interrupt();
		hourThread.interrupt();
	}
	public void paint(Graphics g) { // ����ͼ��
		this.setBackground(Color.black);
		this.start();
		g.drawOval(50, 50, 300, 300);	// ���̵���Ȧ
		g.translate(200, 200); 		// ��������ϵͳ�任
		for (int i = 0; i < 60; i++) { 		// ���Ʊ��̵�С�̶Ⱥʹ�̶�
			if (i % 5 == 0) {
				g.setColor(Color.red); // ������ɫ
				g.fillOval((int) dial_x[i], (int) dial_y[i], 10, 10);
			} else
				g.fillOval((int) dial_x[i], (int) dial_y[i], 5, 5);
		}
	}
	public void run() { 			// ʵ��Thread�ķ���,��ʼ�߳�
		Date date = new Date(); 	// ��ȡ����ʱ��
		String string = date.toString();
		currentHour = Integer.parseInt(string.substring(11, 13)); 	// ��õ�ǰʱ���Сʱ
		currentMinute = Integer.parseInt(string.substring(14, 16)); // ��ȡ��ǰʱ��ķ���
		currentSecond = Integer.parseInt(string.substring(17, 19));// ��ȡ��ǰʱ�������
		if (Thread.currentThread() == secondThread) { 		// �����ǰ�߳������߳�
			secondX = (int) drawX[currentSecond]; 			// �����ʼ��
			secondY = (int) drawX[currentSecond];
			// �ñ���ɫ���ǰһ�������
			secondGraphics.drawLine(0, 0, secondX, secondY);
			secondg2D.setStroke(new BasicStroke(2.0f));		// ��������������Ŀ��
			int i = currentSecond;
			while (true) {
				try {
					secondThread.sleep(1000); 			// ÿ��һ������
					Color c = getBackground(); 			// ��ȡ������ɫ
					secondGraphics.setColor(c); 			// �����������ɫ
					// �ñ���ɫ���ǰһ�������
					secondGraphics.drawLine(0, 0, secondX, secondY);
					secondg2D.setStroke(new BasicStroke(2.0f));
					// ����������غ�,�ָ�������ʾ
					if ((secondX == minuteX) && (secondY == minuteY)) {
						// �ñ���ɫ���ǰһ�ֵķ���
						minuteGraphics.drawLine(0, 0, minuteX, minuteY);
					}
					// ������ʱ���غϣ��ָ�ʱ�����ʾ
					if ((secondX == hourX) && (secondY == hourY)) {
						// �ñ���ɫ���ǰһʱ��ʱ��
						hourGraphics.drawLine(0, 0, hourX, hourY);
						hourg2D.setStroke(new BasicStroke(4.0f));// ����������ʱ��Ŀ��
					}
				} catch (InterruptedException e) { 	// �����쳣
					Color c = getBackground(); 	// ��ȡ������ɫ
					secondGraphics.setColor(c);	// �����������ɫ
					// �ñ���ɫ�������
					secondGraphics.drawLine(0, 0, secondX, secondY);
					secondg2D.setStroke(new BasicStroke(2.0f));
					return;
				}
				secondX = (int) drawX[(i + 1) % 60]; 	// ������ǰ��һ����λ
				secondY = (int) drawY[(i + 1) % 60]; 	// ÿһ����6�ȣ�һ����λ��
				secondGraphics.setColor(Color.blue);	// �����������ɫ
				// �ñ���ɫ���ǰһ�������
				secondGraphics.drawLine(0, 0, secondX, secondY);
				secondg2D.setStroke(new BasicStroke(2.0f));
				i++;
			}
		}
		if (Thread.currentThread() == minuteThread) { 		// �����ǰ�߳��Ƿ��߳�
			minuteX = (int) drawX[currentMinute];
			minuteY = (int) drawY[currentMinute];
			minuteGraphics.drawLine(0, 0, minuteX, minuteY);
			minuteg2D.setStroke(new BasicStroke(3.0f));
			int i = currentMinute; // ��ȡ��ǰ����
			while (true) {
				try { // ��һ�ι�60-second���ǰ��һ���ӣ��Ժ�ÿ��60��ǰ��һ����
					minuteThread.sleep(1000 * 60 - currentSecond * 1000);
					currentSecond = 0;
					Color c = getBackground(); 		// ��ȡ������ɫ
					minuteGraphics.setColor(c); 		// ���÷������ɫ
					minuteg2D.setStroke(new BasicStroke(3.0f));// ���������Ʒ���Ŀ��
					minuteGraphics.drawLine(0, 0, minuteX, minuteY);
					// ���ʱ��ͷ����غ�
					if ((hourX == minuteX) && (hourY == minuteY)) {
						hourGraphics.drawLine(0, 0, minuteX, minuteY);
						hourg2D.setStroke(new BasicStroke(4.0f));
					}
				} catch (InterruptedException e) {
					return;
				}
				minuteX = (int) drawX[(i + 1) % 60]; 		// ������ǰ��һ����λ
				minuteY = (int) drawY[(i + 1) % 60]; 		// ÿһ����6�ȣ�һ����λ��
				minuteGraphics.setColor(Color.BLUE); 	// ���Ʒ������ɫ
				minuteg2D.setStroke(new BasicStroke(3.0f));
				minuteGraphics.drawLine(0, 0, minuteX, minuteY);
				i++;
				currentSecond = 0;
			}
		}
		if (Thread.currentThread() == hourThread) { // �����ǰ�߳���ʱ�߳�
			int h = currentHour % 12;
			hourX = (int) drawX[h * 5 + currentMinute / 12];
			hourY = (int) drawY[h * 5 + currentMinute / 12];
			int i = h * 5 + currentMinute / 12;
			hourGraphics.drawLine(0, 0, hourX, hourY);
			hourg2D.setStroke(new BasicStroke(4.0f));
			while (true) {
				try {
					// ��һ�ι�12-minute%12���Ӿ�ǰ��һ���̶�,�Ժ�ÿ��12����ǰ��һ�̶�
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
