package com.java.base.thread.five;

public class TelephoneOffice extends Object {
	public static Runnable makeRunnable(
				final String name, 
				final long firstDelay
			) {
		return new Runnable() {
				public void run() {
					try {
						System.out.println(name +": 请求拨号，正在连接中…");
						Thread.sleep(firstDelay);
						System.out.println(name + ": 正在通话中…");
						Thread.sleep(2000);
						System.out.println(name + ": 通语结束");
					} catch ( InterruptedException ix ) {
						System.out.println(name + ": 网络异常");
						return;
					} catch ( Exception x ) {
						x.printStackTrace();
					}
				}
				public String toString() {
					return name;
				}
			};
	}
	public static void main(String[] args) {
		try {
			TelephoneLine pool = new TelephoneLine(3);
			Runnable zs = makeRunnable("张三", 3000);
			pool.execute(zs);
			Runnable ls = makeRunnable("李四", 1000);
			pool.execute(ls);
			Runnable nql = makeRunnable("聂庆亮", 2000);
			pool.execute(nql);
			Runnable zxh = makeRunnable("张小红", 60000);
			pool.execute(zxh);
			Runnable yjp = makeRunnable("尹继平", 1000);
			pool.execute(yjp);
			pool.StopServiceOfID();
			Thread.sleep(2000);
			pool.StopServiceOfID();
			Thread.sleep(5000);
			pool.StopServiceAll();
		} catch ( InterruptedException ix ) {
			ix.printStackTrace();
		}
	}
}
