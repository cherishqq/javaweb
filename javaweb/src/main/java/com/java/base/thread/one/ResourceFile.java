package com.java.base.thread.one;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ResourceFile {
	private static boolean isStop = false;			// ��ʶ�߳��Ƿ�ֹͣ
	private static List taskList = new ArrayList();	// ���������б����
	private static DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private class SearchDownload extends Thread {// ����ģ��������Դ�������ļ���
		private String[] file = new String[] { "51Upload_Setup.exe",
				"eclipse-SDK-3.2.1-win32.zip", "EditPlus.rar" };
		// ����ģ������ʱ����������Դ����
		private String[] resource = new String[] { "��ʼ����......",
				"��ʼ������ѡ��Դ......", "û��������ѡ��Դ���Ժ���������",
				"ԭʼ��Դ���ӳɹ����õ����ļ����ȣ� 7725816", "��ʼ�����ļ�......",
				"�ļ������ɹ�����ʼ��������......", "�û�ȡ������" };
		int sum = 0;//����һ������ʱ�õ���ʱ��
		int time = 0;//����һ����Դʱ�õ�ʱ��
		public void run() {// ʵ���߳���ķ���
			for (int i = 0; i < file.length; i++) {
				System.out.println("��ʼ���ص�" + (i + 1) + "���ļ�");
				try {
					for (int j = 0; j < resource.length; j++) {
						System.out.println(dateFormat.format(new Date()) + " "
								+ resource[j]);
						time = new Random().nextInt(100);	// ���������ɵ�����
						Thread.sleep(time);				// �߳����ߵȴ�
						sum = sum + time;
					}
					System.out.println(dateFormat.format(new Date()) + " �����ļ�"
							+ file[i] + "��ʱ" + sum + " ����. ");
					Thread.sleep(time);	// �߳����ߵȴ�
				} catch (Exception e) {	// �����쳣
					System.out.println("�����ļ�����" + e.getMessage());
				}
				synchronized (taskList) {	// ʵ��ͬ��
					System.out.println(dateFormat.format(new Date()) + " "
							+ file[i] + "�ļ���������ϣ�");
					taskList.add(file[i]);	// ���ļ���ӵ������б���
					taskList.notify();	// ͨ�����еȴ���fileList���߳�
				}
				sum = 0;
			}
			isStop = true;				// �������ñ�ʶ
			System.out.println(dateFormat.format(new Date()) + " �����̡߳��˳�");
		}
	}
	public static void main(String[] args) {	// java��������ڴ�
		ResourceFile text = new ResourceFile();	// ʵ��������
		text.new SearchDownload().start();		// ʵ�����ڲ��ಢ�����߳�
	}
}
