package com.java.base.thread.three;

import java.util.*;

public class ThreadPriority {
	public static void main(String[] args) { // java程序主入口处
		List list = new ArrayList();// 创建一个List对象
		Student stu1 = new Student("张三", 5, 98, 130);// 创建一个Student对象
		list.add(stu1);// 将Student对象添加到List列表中
		Student stu2 = new Student("李国", 3, 95, 150);
		list.add(stu2);
		Student stu3 = new Student("聂亮", 4, 100, 120);
		list.add(stu3);
		Student stu4 = new Student("欧阳雪", 6, 91, 140);
		list.add(stu4);
		Student stu5 = new Student("肖肖", 7, 93, 150);
		list.add(stu5);
		System.out.println("按成绩分数的高低排坐");
		new Score().RowSeat(list);
		System.out.println("按个子的高低排坐");
		new Height().RowSeat(list);
	}

}

class Height implements Runnable { // 线程实现接口Runnable
	Thread thread; // 声明一个线程

	Height() {
	} // 默认构造方法

	Height(String name) { // 构造方法初始一个线程
		thread = new Thread(this, name);
	}

	public void run() {
		System.out.println("一年一班的学生成员：" + thread.getName()); // 获得线程的名称
	}

	public void RowSeat(List list) { // 按优先级执行线程
		Student st = (Student) list.get(0);
		Student st1 = (Student) list.get(1);
		Student st2 = (Student) list.get(2);
		Student st3 = (Student) list.get(3);
		Student st4 = (Student) list.get(4);
		Height o1 = new Height(st.name);// 创建线程对象并命名
		Height o2 = new Height(st1.name);
		Height o3 = new Height(st2.name);
		Height o4 = new Height(st3.name);
		Height o5 = new Height(st4.name);
		o1.thread.setPriority(st.height / 10 - 10); // MAX_PRIORITY优先级最大
		o2.thread.setPriority(st1.height / 10 - 10); // 较次之
		o3.thread.setPriority(st2.height / 10 - 10); // NORM_PRIORITY处于中间位置
		o4.thread.setPriority(st3.height / 10 - 10);
		o5.thread.setPriority(st4.height / 10 - 10); // MIN_PRIORITY优先级最小
		o1.thread.start(); // 启动线程
		o2.thread.start();
		o3.thread.start();
		o4.thread.start();
		o5.thread.start();
		try {
			o5.thread.join(); // 等待线程运行结束
		} catch (InterruptedException e) { // 捕获拦截异常
			System.out.println("等待线程结束出错：" + e.getMessage());
		}
	}
}

class Score extends Thread {
	Thread thread;

	public Score() {
	} // 默认构造方法

	public Score(String name) { // 带参数构造方法初始一个线程
		thread = new Thread(this, name);
	}

	public void run() {
		System.out.println("一年一班的学生成员：" + thread.getName()); // 获得线程的名称
	}

	public void RowSeat(List list) { // 按优先级执行线程
		Student st = (Student) list.get(0);
		Student st1 = (Student) list.get(1);
		Student st2 = (Student) list.get(2);
		Student st3 = (Student) list.get(3);
		Student st4 = (Student) list.get(4);
		Score e1 = new Score(st.name);// 创建线程对象并命名
		Score e2 = new Score(st1.name);
		Score e3 = new Score(st2.name);
		Score e4 = new Score(st3.name);
		Score e5 = new Score(st4.name);
		e1.thread.setPriority(st.score / 10); // MAX_PRIORITY优先级最大
		e2.thread.setPriority(st1.score / 10); // 较次之
		e3.thread.setPriority(st2.score / 10); // NORM_PRIORITY处于中间位置
		e4.thread.setPriority(st3.score / 10);
		e5.thread.setPriority(st4.score / 10); // MIN_PRIORITY优先级最小
		e1.thread.start(); // 启动线程
		e2.thread.start();
		e3.thread.start();
		e4.thread.start();
		e5.thread.start();
		try {
			e5.thread.join(); // 等待线程运行结束
		} catch (InterruptedException e) { // 捕获拦截异常
			System.out.println("等待线程结束出错：" + e.getMessage());
		}
	}
}

class Student {// 学生类
	public String name;// 姓名

	public int age;// 年龄

	public int score;// 成绩

	public int height;// 身高

	public Student(String name, int age, int score, int height) {// 构造函数为各成员变量
		this.name = name;
		this.age = age;
		this.height = height;
		this.score = score;
	}

}
