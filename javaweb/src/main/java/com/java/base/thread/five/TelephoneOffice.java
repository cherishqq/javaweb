package com.java.base.thread.five;

public class TelephoneOffice extends Object {
	public static Runnable makeRunnable(
				final String name, 
				final long firstDelay
			) {
		return new Runnable() {
				public void run() {
					try {
						System.out.println(name +": ���󲦺ţ����������С�");
						Thread.sleep(firstDelay);
						System.out.println(name + ": ����ͨ���С�");
						Thread.sleep(2000);
						System.out.println(name + ": ͨ�����");
					} catch ( InterruptedException ix ) {
						System.out.println(name + ": �����쳣");
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
			Runnable zs = makeRunnable("����", 3000);
			pool.execute(zs);
			Runnable ls = makeRunnable("����", 1000);
			pool.execute(ls);
			Runnable nql = makeRunnable("������", 2000);
			pool.execute(nql);
			Runnable zxh = makeRunnable("��С��", 60000);
			pool.execute(zxh);
			Runnable yjp = makeRunnable("����ƽ", 1000);
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
