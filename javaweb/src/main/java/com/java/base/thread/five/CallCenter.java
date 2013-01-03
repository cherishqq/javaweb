package com.java.base.thread.five;

public class CallCenter extends Object {//�ͷ�����
	private static int nextServiceID = 0;//��һ���ͷ���ID
	private InterNIC iNIC;//��������������Ϣ���Ķ���
	private int ServiceID;//�ͷ�ID
	private InterNIC handoffBox;//��������������Ϣ���Ķ���
	private Thread thread;//����һ���̶߳���
	private volatile boolean isStop;//ֹͣ����ı�־
	public CallCenter(InterNIC iNIC) {//���캯��
		this.iNIC = iNIC;
		ServiceID = getNextWorkerID();
		handoffBox = new InterNIC(1); 
		isStop = true;
		Runnable r = new Runnable() {
				public void run() {
					try {
						runWork();
					} catch ( Exception x ) {
						x.printStackTrace(); 
					}
				}
			};
		thread = new Thread(r);
		thread.start();
	}
	public static synchronized int getNextWorkerID() { //��ȡ�¸��ͷ�ID��
		int id = nextServiceID;
		nextServiceID++;
		return id;
	}
	public void process(Runnable target) throws InterruptedException {
		handoffBox.add(target);
	}
	private void runWork() {//���ڹ�����
		while ( isStop ) {
			try {
				System.out.println("�ͷ�" + (ServiceID+1) + 
						"��, ����ͨ���У����Ժ�");
				iNIC.add(this);
				Runnable r = (Runnable) handoffBox.remove();
				System.out.println("�ͷ�" + (ServiceID+1) + 
						"��, ��������һλ���û�" + r+"����ͨ�����ӡ�");
				runFailed(r); 
			} catch ( InterruptedException x ) {
				Thread.currentThread().interrupt(); 
			}
		}
	}
	private void runFailed(Runnable r) {//����ʧ��
		try {
			r.run();
		} catch ( Exception runex ) {
			System.err.println("����ʧ�ܣ���ʱ�޷���ͨ��");
			runex.printStackTrace();
		} finally {
			Thread.interrupted();
		}
	}
	public void stopRequest() {//ֹͣ����
		System.out.println("�ͷ�" + (ServiceID+1)+ 
				"��, ֹͣ����");
		isStop = false;
		thread.interrupt();
	}
	public boolean isAlive() {
		return thread.isAlive();//�жϸ��߳��Ƿ��ڻ״̬
	}
}