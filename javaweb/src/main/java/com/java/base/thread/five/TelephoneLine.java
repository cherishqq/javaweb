package com.java.base.thread.five;

public class TelephoneLine extends Object {
	private InterNIC iNIC;// 声明网际网络信息中心对象
	private CallCenter[] serviceList;// 创建一个客服对象数组
	public TelephoneLine(int numOfLine) {// 构造方法，其中numOfLine表示线路的个数
		numOfLine = Math.max(1, numOfLine);
		iNIC = new InterNIC(numOfLine);
		serviceList = new CallCenter[numOfLine];
		for (int i = 0; i < serviceList.length; i++) {
			serviceList[i] = new CallCenter(iNIC);
		}
	}
	public void execute(Runnable target) throws InterruptedException {// 执行任务
		CallCenter Service = (CallCenter) iNIC.remove();
		Service.process(target);
	}
	public void StopServiceOfID() {// 某个ID的客服停止服务
		try {
			Object[] idle = iNIC.removeAll();
			for (int i = 0; i < idle.length; i++) {
				((CallCenter) idle[i]).stopRequest();
			}
		} catch (InterruptedException x) {
			Thread.currentThread().interrupt();
		}
	}
	public void StopServiceAll() {// 全部客服停止服务
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
