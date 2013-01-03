package com.java.base.thread.five;

public class InterNIC extends Object {
	private Object[] queue;// ����һ������
	private int capacity;// ��Ϣ����
	private int size;// ��С
	private int head;// ��Ϣͷ
	private int tail;// ��Ϣβ
	public InterNIC(int cap) {
		capacity = (cap > 0) ? cap : 1; // ���ʼ����Ϊ1
		queue = new Object[capacity];// ������������һ��Object����
		head = 0;
		tail = 0;
		size = 0;
	}
	public int getCapacity() {// ��ȡ����
		return capacity;
	}
	public synchronized int getSize() {// ��ȡ��Ϣ�Ĵ�С
		return size;
	}
	public synchronized boolean isEmpty() {// �ж���Ϣ�Ƿ��
		return (size == 0);
	}
	public synchronized boolean isFull() {// �ж���Ϣ�Ƿ�
		return (size == capacity);
	}
	public synchronized void add(Object obj) throws InterruptedException {//�����Ϣ
		waitWhileFull();
		queue[head] = obj;
		head = (head + 1) % capacity;
		size++;
		notifyAll();
	}
	public synchronized void addEach(Object[] list) throws InterruptedException {//�����Ϣβ��
		for (int i = 0; i < list.length; i++) {
			add(list[i]);
		}
	}
	public synchronized Object remove() throws InterruptedException {//��Ϣ���Ƴ�
		waitWhileEmpty();
		Object obj = queue[tail];
		queue[tail] = null;
		tail = (tail + 1) % capacity;
		size--;
		notifyAll();
		return obj;
	}
	public synchronized Object[] removeAll() throws InterruptedException {//��Ϣȫ���Ƴ�
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
	public synchronized boolean waitUntilEmpty(long msTimeout)//�𽥽���Ϣȫ�����
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
