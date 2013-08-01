package com.java.base.thread.five;

public class CallCenter extends Object {//客服中心
	private static int nextServiceID = 0;//下一个客服的ID
	private InterNIC iNIC;//声明网际网络信息中心对象
	private int ServiceID;//客服ID
	private InterNIC handoffBox;//声明网际网络信息中心对象
	private Thread thread;//声明一个线程对象
	private volatile boolean isStop;//停止服务的标志
	public CallCenter(InterNIC iNIC) {//构造函数
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
	public static synchronized int getNextWorkerID() { //获取下个客服ID号
		int id = nextServiceID;
		nextServiceID++;
		return id;
	}
	public void process(Runnable target) throws InterruptedException {
		handoffBox.add(target);
	}
	private void runWork() {//正在工作中
		while ( isStop ) {
			try {
				System.out.println("客服" + (ServiceID+1) + 
						"号, 正在通话中，请稍候…");
				iNIC.add(this);
				Runnable r = (Runnable) handoffBox.remove();
				System.out.println("客服" + (ServiceID+1) + 
						"号, 正在与另一位新用户" + r+"建立通话连接…");
				runFailed(r); 
			} catch ( InterruptedException x ) {
				Thread.currentThread().interrupt(); 
			}
		}
	}
	private void runFailed(Runnable r) {//连接失败
		try {
			r.run();
		} catch ( Exception runex ) {
			System.err.println("连接失败，暂时无法接通…");
			runex.printStackTrace();
		} finally {
			Thread.interrupted();
		}
	}
	public void stopRequest() {//停止服务
		System.out.println("客服" + (ServiceID+1)+ 
				"号, 停止服务");
		isStop = false;
		thread.interrupt();
	}
	public boolean isAlive() {
		return thread.isAlive();//判断该线程是否处于活动状态
	}
}