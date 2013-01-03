package com.java.base.thread.one;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class TextThreadYield {					// �����߳��ò�����
	private Vector vector = new Vector();			// ������������
										// �������ڸ�ʽ
	private DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss:SSSS"); 
	private boolean isFlag = false;
	private class Yield extends Thread {			//�ò������ļ���
		public Yield() {
			this.setName("�����ļ�");			//�����߳�����
			this.setDaemon(true);			// ���SendFile�߳̽���������߳��Զ�����
		}
		public void run() {
			while (!isFlag) {					// ��ʶΪ�����ѭ��
				try {
					Thread.sleep(100);		// ����
				}catch(InterruptedException e){	//�������쳣
					System.out.println("�����쳣:"+e.getMessage());
				}
				if (vector.size() == 0) {		//�ж��������ϴ�С
					System.out.println(dateFormat.format(new Date())+ "\t����������û���ļ���ִ��yield����");
					Thread.yield();			//�����߳��ò�
				} else {					//�ƶ��ļ���ö���
					String ss = (String) vector.remove(0); 
					System.out.println(dateFormat.format(new Date())+"\tȡ���ļ�����Ϊ" + ss);
				}
			}
		}
	}
	private class SendFile extends Thread {		//�����ļ���
		private String[] files = new String[] { "�����ļ�", "����������", "ɽˮ��������", "�����¸�˵��" };
		public SendFile() {
			this.setName("�����ļ�");
		}
		public void run() {
			try {
				for (int i=0;i < files.length;i++){	//ѭ��ʹ�߳�����
					Thread.sleep(201);		//�߳�����
					vector.add(files[i]);		//����ļ�
				}
				Thread.sleep(100);			//�߳�����
			} catch (InterruptedException e) {	//�������쳣
				System.out.println("�����쳣:"+e.getMessage());
			}
		}
	}
	public static void main(String []args){			//java��������ڴ�
		TextThreadYield text=new TextThreadYield();//ʵ��������
		text.new Yield().start();				//ʵ�����������߳�
		text.new SendFile().start();
	}
}
