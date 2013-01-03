package com.java.base.thread.six;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoveBall extends Frame implements ActionListener {
	// ����3����Ť������ֱ����ʼ��ֹͣ���˳�
	private Button quit = new Button("�˳�");
	private Button start = new Button("��ʼ");
	private Button stop = new Button("ֹͣ");
	private DrawBall balls[] = new DrawBall[20];
	// ���췽�����Ը�������г�ʼ��
	public MoveBall() {
		super();
		setLayout(new FlowLayout());
		add(quit);
		quit.addActionListener(this);
		add(start);
		start.addActionListener(this);
		add(stop);
		stop.addActionListener(this);
		validate();
		this.setBackground(Color.black);
		this.setSize(300, 300);
		this.setVisible(true);
		for (int i = 0; i < balls.length; i++) {
			int x = (int) (getSize().width * Math.random());
			int y = (int) (getSize().height * Math.random());
			balls[i] = new DrawBall(this, x, y);
		}
	}
	public void actionPerformed(ActionEvent e) {// ΪButton����¼�����
		if (e.getSource() == stop) {// ����ֹͣ��Ť
			for (int i = 0; i < balls.length; i++) {
				balls[i].setRun();
			}
		}
		if (e.getSource() == start) {// ������ʼ��Ť
			for (int i = 0; i < balls.length; i++) {
				balls[i].setRun();
				balls[i] = new DrawBall(this, balls[i].x, balls[i].y);
			}
		}
		if (e.getSource() == quit) {// �����˳���Ť
			System.exit(0);
		}
	}
	public void paint(Graphics g) {// �������
		for (int i = 0; i < balls.length; i++)
			if (balls[i] != null)
				balls[i].paintBall(g);
	}
	public static void main(String[] args) {// �������ڴ�
		MoveBall t = new MoveBall();
	}
}
class DrawBall extends Thread {// �滭����
	// �����������ȡ�������ӵ�λ��
	private int a = 2 * (1 - 2 * (int) Math.round(Math.random()));
	private int b = 2 * (1 - 2 * (int) Math.round(Math.random()));
	private boolean running = false;// �����Ƿ�������ӵı�־
	private MoveBall table = null;// ����һ��MoveBall����
	protected int x, y;// ����XY�����
	public DrawBall(MoveBall _table, int _x, int _y) {// ���췽���������Ա��������ֵ
		table = _table;
		x = _x;
		y = _y;
		start();
	}
	public void start() {
		running = true;
		super.start();// �����߳�
	}
	public void setRun() {
		running = false;
	}
	public void run() {// ��дThread���run����
		while (running) {
			move();
			try {
				sleep(50);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			table.repaint();
		}
	}
	public void paintBall(Graphics g) {// �Զ���������ӵķ���
		g.setColor(Color.red);
		g.fillOval(x, y, 20, 20);
	}
	private void move() {// �����ڹ���
		x += a;
		y += b;
		if ((x > table.getSize().width) || (x < 0)) {
			a *= (-1);
		}
		if ((y > table.getSize().height) || (y < 0)) {
			b *= (-1);
		}
	}
}
