package com.java.base.thread.five;

public class TelephoneLine extends Object {
	private InterNIC iNIC;// ��������������Ϣ���Ķ���
	private CallCenter[] serviceList;// ����һ���ͷ���������
	public TelephoneLine(int numOfLine) {// ���췽��������numOfLine��ʾ��·�ĸ���
		numOfLine = Math.max(1, numOfLine);
		iNIC = new InterNIC(numOfLine);
		serviceList = new CallCenter[numOfLine];
		for (int i = 0; i < serviceList.length; i++) {
			serviceList[i] = new CallCenter(iNIC);
		}
	}
	public void execute(Runnable target) throws InterruptedException {// ִ������
		CallCenter Service = (CallCenter) iNIC.remove();
		Service.process(target);
	}
	public void StopServiceOfID() {// ĳ��ID�Ŀͷ�ֹͣ����
		try {
			Object[] idle = iNIC.removeAll();
			for (int i = 0; i < idle.length; i++) {
				((CallCenter) idle[i]).stopRequest();
			}
		} catch (InterruptedException x) {
			Thread.currentThread().interrupt();
		}
	}
	public void StopServiceAll() {// ȫ���ͷ�ֹͣ����
		StopServiceOfID();
		try {
			Thread.sleep(250);
		} catch (InterruptedException x) {
		}
		for (int i = 0; i < serviceList.length; i++) {
			if (serviceList[i].isAlive()) {
				serviceList[i].stopRequest();
			}
		}
	}
}
