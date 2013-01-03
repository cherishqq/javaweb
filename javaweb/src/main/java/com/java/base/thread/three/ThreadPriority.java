package com.java.base.thread.three;

import java.util.*;

public class ThreadPriority {
	public static void main(String[] args) { // java��������ڴ�
		List list = new ArrayList();// ����һ��List����
		Student stu1 = new Student("����", 5, 98, 130);// ����һ��Student����
		list.add(stu1);// ��Student������ӵ�List�б���
		Student stu2 = new Student("���", 3, 95, 150);
		list.add(stu2);
		Student stu3 = new Student("����", 4, 100, 120);
		list.add(stu3);
		Student stu4 = new Student("ŷ��ѩ", 6, 91, 140);
		list.add(stu4);
		Student stu5 = new Student("ФФ", 7, 93, 150);
		list.add(stu5);
		System.out.println("���ɼ������ĸߵ�����");
		new Score().RowSeat(list);
		System.out.println("�����ӵĸߵ�����");
		new Height().RowSeat(list);
	}

}

class Height implements Runnable { // �߳�ʵ�ֽӿ�Runnable
	Thread thread; // ����һ���߳�

	Height() {
	} // Ĭ�Ϲ��췽��

	Height(String name) { // ���췽����ʼһ���߳�
		thread = new Thread(this, name);
	}

	public void run() {
		System.out.println("һ��һ���ѧ����Ա��" + thread.getName()); // ����̵߳�����
	}

	public void RowSeat(List list) { // �����ȼ�ִ���߳�
		Student st = (Student) list.get(0);
		Student st1 = (Student) list.get(1);
		Student st2 = (Student) list.get(2);
		Student st3 = (Student) list.get(3);
		Student st4 = (Student) list.get(4);
		Height o1 = new Height(st.name);// �����̶߳�������
		Height o2 = new Height(st1.name);
		Height o3 = new Height(st2.name);
		Height o4 = new Height(st3.name);
		Height o5 = new Height(st4.name);
		o1.thread.setPriority(st.height / 10 - 10); // MAX_PRIORITY���ȼ����
		o2.thread.setPriority(st1.height / 10 - 10); // �ϴ�֮
		o3.thread.setPriority(st2.height / 10 - 10); // NORM_PRIORITY�����м�λ��
		o4.thread.setPriority(st3.height / 10 - 10);
		o5.thread.setPriority(st4.height / 10 - 10); // MIN_PRIORITY���ȼ���С
		o1.thread.start(); // �����߳�
		o2.thread.start();
		o3.thread.start();
		o4.thread.start();
		o5.thread.start();
		try {
			o5.thread.join(); // �ȴ��߳����н���
		} catch (InterruptedException e) { // ���������쳣
			System.out.println("�ȴ��߳̽�������" + e.getMessage());
		}
	}
}

class Score extends Thread {
	Thread thread;

	public Score() {
	} // Ĭ�Ϲ��췽��

	public Score(String name) { // ���������췽����ʼһ���߳�
		thread = new Thread(this, name);
	}

	public void run() {
		System.out.println("һ��һ���ѧ����Ա��" + thread.getName()); // ����̵߳�����
	}

	public void RowSeat(List list) { // �����ȼ�ִ���߳�
		Student st = (Student) list.get(0);
		Student st1 = (Student) list.get(1);
		Student st2 = (Student) list.get(2);
		Student st3 = (Student) list.get(3);
		Student st4 = (Student) list.get(4);
		Score e1 = new Score(st.name);// �����̶߳�������
		Score e2 = new Score(st1.name);
		Score e3 = new Score(st2.name);
		Score e4 = new Score(st3.name);
		Score e5 = new Score(st4.name);
		e1.thread.setPriority(st.score / 10); // MAX_PRIORITY���ȼ����
		e2.thread.setPriority(st1.score / 10); // �ϴ�֮
		e3.thread.setPriority(st2.score / 10); // NORM_PRIORITY�����м�λ��
		e4.thread.setPriority(st3.score / 10);
		e5.thread.setPriority(st4.score / 10); // MIN_PRIORITY���ȼ���С
		e1.thread.start(); // �����߳�
		e2.thread.start();
		e3.thread.start();
		e4.thread.start();
		e5.thread.start();
		try {
			e5.thread.join(); // �ȴ��߳����н���
		} catch (InterruptedException e) { // ���������쳣
			System.out.println("�ȴ��߳̽�������" + e.getMessage());
		}
	}
}

class Student {// ѧ����
	public String name;// ����

	public int age;// ����

	public int score;// �ɼ�

	public int height;// ���

	public Student(String name, int age, int score, int height) {// ���캯��Ϊ����Ա����
		this.name = name;
		this.age = age;
		this.height = height;
		this.score = score;
	}

}
