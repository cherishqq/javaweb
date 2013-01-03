package com.java.base.thread.five;

import java.util.*;

public class SurgeryThreadPool {
	private static Runnable createTask(final int taskID) { // �������񷽷�
		return new Runnable() {
			public void run() { // ������������
				System.out.println("������ʼ,���Ϊ" + taskID);
				System.out.println("���������С���");
				System.out.println("��������,���Ϊ" + taskID);
			}
		};
	}

	public static void main(String[] args) { // java��������ڴ�
		ThreadTask threadPool = new ThreadTask(3); // ����һ���и�3�����̵߳��̳߳�
		try {// ����600����,���̳߳��е������߳�ȫ������
			Thread.sleep(600);
		} catch (InterruptedException e) { // ���������쳣
			System.out.println("�߳����߳���" + e.getMessage());
		}
		for (int i = 0; i < 3; i++) { // ѭ��������ִ������
			threadPool.addTask(createTask(i));
		}
		threadPool.waitTaskColsed(); // �ȴ���������ִ�����
		threadPool.closePool(); // �ر��̳߳�
	}

}

class ThreadTask extends ThreadGroup { // �̳��߳���ʵ���̳߳ع���
	private boolean isStop = false; // �̳߳��Ƿ�ر�

	private LinkedList queue; // �����������

	private static int poolID = 1; // �̳߳صı��

	private class SurgeryTask extends Thread { // ����ӹ���������ȡ������ִ�е��ڲ���
		private int id; // ������

		public SurgeryTask(int id) { // ���췽�����г�ʼ��
			super(ThreadTask.this, id + ""); // ���̼߳��뵽��ǰ�߳�����
			this.id = id;
		}

		public void run() {
			while (!isInterrupted()) { // �ж��߳��Ƿ��ж�
				Runnable task = null;
				task = getTask(id); // ȡ������

				// ���getTask()����null�����߳�ִ��getTask()ʱ���жϣ���������߳�
				if (task == null)
					return;

				try {
					task.run(); // ��������
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
	}

	public ThreadTask(int size) { // ���췽�������̳߳��еĹ����̵߳�����
		super(poolID + ""); // ָ���߳�������
		setDaemon(true); // �̳��߳���ķ������������Ƿ��ػ��̳߳�
		queue = new LinkedList(); // ���������������
		for (int i = 0; i < size; i++) { // ѭ�����������߳�
			new SurgeryTask(i).start(); // �����̳߳����ݴ��������̲߳������߳�,
		}
	}

	public synchronized void addTask(Runnable task) {// ���������ִ������
		if (isStop) { // �жϱ�ʶ
			throw new IllegalStateException(); // �׳�������״̬�쳣
		}
		if (task != null) {
			queue.add(task); // ����������м���һ������
			notify(); // ���Ѵ�����Ĺ��������߳�
		}
	}

	private synchronized Runnable getTask(int id) { // ȡ������
		try {
			while (queue.size() == 0) { // ѭ��ʹ�̵߳ȴ�����
				if (isStop)
					return null;
				System.out.println("����" + id + "���ڵȴ�����...");
				wait(); // ������������û������,�͵ȴ�����
			}
		} catch (InterruptedException e) { // ���������쳣
			System.out.println("�ȴ����Ƴ��ִ���" + e.getMessage());
		}
		System.out.println("����" + id + "��ʼִ������...");
		return (Runnable) queue.removeFirst();// ���ص�һ�����񲢴Ӷ�����ɾ��
	}

	public synchronized void closePool() { // �ر��̳߳�
		if (!isStop) { // �жϱ�ʶ
			waitTaskColsed(); // �ȴ������߳�ִ�����
			isStop = true; // ��ʶΪ��
			queue.clear(); // ����������
			interrupt(); // �����̳߳��е����еĹ����߳�
		}
	}

	public void waitTaskColsed() { // �ȴ������̰߳���������ִ�����
		synchronized (this) {
			isStop = true; // ��ʶΪ��
			notifyAll(); // ���Ѵ�����Ĺ��������߳�
		}
		Thread[] threads = new Thread[activeCount()];// �����߳����л���߳���
		int count = enumerate(threads); // ����߳����е�ǰ���л�Ĺ����߳�
		for (int i = 0; i < count; i++) { // ѭ���ȴ����й����߳̽���
			try {
				threads[i].join(); // �ȴ������߳̽���
			} catch (InterruptedException e) { // ���������쳣
				System.out.println("����ʧ�ܣ�" + e.getMessage());
			}
		}
	}
}
