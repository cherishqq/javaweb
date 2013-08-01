package com.java.base.thread.one;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class TextThreadYield {					// 操作线程让步的类
	private Vector vector = new Vector();			// 创建向量集合
										// 创建日期格式
	private DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss:SSSS"); 
	private boolean isFlag = false;
	private class Yield extends Thread {			//让步接收文件类
		public Yield() {
			this.setName("接收文件");			//设置线程名称
			this.setDaemon(true);			// 如果SendFile线程结束，则该线程自动结束
		}
		public void run() {
			while (!isFlag) {					// 标识为真进行循环
				try {
					Thread.sleep(100);		// 休眠
				}catch(InterruptedException e){	//捕获唤醒异常
					System.out.println("唤醒异常:"+e.getMessage());
				}
				if (vector.size() == 0) {		//判断向量集合大小
					System.out.println(dateFormat.format(new Date())+ "\t向量集合中没有文件，执行yield操作");
					Thread.yield();			//调用线程让步
				} else {					//移队文件获得对象
					String ss = (String) vector.remove(0); 
					System.out.println(dateFormat.format(new Date())+"\t取到文件，名为" + ss);
				}
			}
		}
	}
	private class SendFile extends Thread {		//发送文件类
		private String[] files = new String[] { "新闻文件", "国内旅游向导", "山水名画欣赏", "发家致富说明" };
		public SendFile() {
			this.setName("发送文件");
		}
		public void run() {
			try {
				for (int i=0;i < files.length;i++){	//循环使线程休眠
					Thread.sleep(201);		//线程休眠
					vector.add(files[i]);		//添加文件
				}
				Thread.sleep(100);			//线程休眠
			} catch (InterruptedException e) {	//捕获唤醒异常
				System.out.println("唤醒异常:"+e.getMessage());
			}
		}
	}
	public static void main(String []args){			//java程序主入口处
		TextThreadYield text=new TextThreadYield();//实例化对象
		text.new Yield().start();				//实例对象启动线程
		text.new SendFile().start();
	}
}
