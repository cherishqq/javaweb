package com.java.base.thread.five;

public class InterNIC extends Object {
	private Object[] queue;// 声明一个队列
	private int capacity;// 信息容量
	private int size;// 大小
	private int head;// 信息头
	private int tail;// 信息尾
	public InterNIC(int cap) {
		capacity = (cap > 0) ? cap : 1; // 设初始容量为1
		queue = new Object[capacity];// 根据容量创建一个Object数组
		head = 0;
		tail = 0;
		size = 0;
	}
	public int getCapacity() {// 获取容量
		return capacity;
	}
	public synchronized int getSize() {// 获取信息的大小
		return size;
	}
	public synchronized boolean isEmpty() {// 判断信息是否空
		return (size == 0);
	}
	public synchronized boolean isFull() {// 判断信息是否
		return (size == capacity);
	}
	public synchronized void add(Object obj) throws InterruptedException {//添加信息
		waitWhileFull();
		queue[head] = obj;
		head = (head + 1) % capacity;
		size++;
		notifyAll();
	}
	public synchronized void addEach(Object[] list) throws InterruptedException {//添加信息尾部
		for (int i = 0; i < list.length; i++) {
			add(list[i]);
		}
	}
	public synchronized Object remove() throws InterruptedException {//信息的移出
		waitWhileEmpty();
		Object obj = queue[tail];
		queue[tail] = null;
		tail = (tail + 1) % capacity;
		size--;
		notifyAll();
		return obj;
	}
	public synchronized Object[] removeAll() throws InterruptedException {//信息全部移出
		Object[] list = new Object[size]; // use the current size
		for (int i = 0; i < list.length; i++) {
			list[i] = remove();
		}
		return list;
	}
	public synchronized Object[] removeAtLeastOne() throws InterruptedException {
		waitWhileEmpty();
		return removeAll();
	}
	public synchronized boolean waitUntilEmpty(long msTimeout)//逐渐将信息全部清除
			throws InterruptedException {
		if (msTimeout == 0L) {
			waitUntilEmpty();
			return true;
		}
		long endTime = System.currentTimeMillis() + msTimeout;
		long msRemaining = msTimeout;
		while (!isEmpty() && (msRemaining > 0L)) {
			wait(msRemaining);
			msRemaining = endTime - System.currentTimeMillis();
		}
		return isEmpty();
	}
	public synchronized void waitUntilEmpty() throws InterruptedException {
		while (!isEmpty()) {
			wait();
		}
	}
	public synchronized void waitWhileEmpty() throws InterruptedException {
		while (isEmpty()) {
			wait();
		}
	}
	public synchronized void waitUntilFull() throws InterruptedException {
		while (!isFull()) {
			wait();
		}
	}
	public synchronized void waitWhileFull() throws InterruptedException {
		while (isFull()) {
			wait();
		}
	}
}
