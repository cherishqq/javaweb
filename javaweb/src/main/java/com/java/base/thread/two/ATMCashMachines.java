package com.java.base.thread.two;

public class ATMCashMachines {
	public static void main(String[] args) { // java��������ڴ�
		Bank bank = new Bank();// ʵ����Bank����
		SyncBank sbank = new SyncBank();// ʵ����SyncBank����
		System.out.println("1.�桢ȡ�߳�û�в�ȡͬ��ʱ��ִ�д�ȡ����ʱ���乤���������£�");
		// ��Ǯû�в���ͬ������
		Thread putThread = new CashMachines(bank, "saveMoney");
		// ȡǮû��ͬ������
		Thread takeThread = new CashMachines(bank, "withdrawMoney");
		putThread.start(); // ����putThread�߳�
		takeThread.start(); // ����takeThread�߳�
		try {
			putThread.join(); // �ȴ����߳����н���
			takeThread.join();
		} catch (Exception e) { // �����쳣
			System.out.println("���߳����г���" + e.getMessage());
		}
		System.out.println();
		bank = new Bank();
		System.out.println("2.�桢ȡ�߳�����Ϊͬ��ʱ��ִ�д�ȡ����ʱ���乤���������£�");
		putThread = new CashMachines(sbank, "sync_SaveMoney"); // ����CashMachines���󣬴�Ǯ��ͬ������
		takeThread = new CashMachines(sbank, "sync_WithdrawMoney"); // ȡǮ��ͬ������
		putThread.start(); // �����߳�
		takeThread.start(); // �����߳�
	}

}

class Bank {
	private double curveMoney = 174.85; // �������е�Ǯ��

	public void saveMoney(double putThread) { // ��Ǯû�в���ͬ������
		System.out.println("��ǰ�˻�����ʣ���Ϊ" + this.curveMoney + "; ������Ϊ: "
				+ putThread);
		System.out.println("���ڲ��������Ժ�......"); // ��Ǯʱ�ȵȴ�300����
		try {
			Thread.sleep(300); // �߳�����
		} catch (Exception e) { // �����쳣
			e.printStackTrace();
		}
		System.out.println("�����ɹ��������" + putThread);
		this.curveMoney = this.curveMoney + putThread;
		System.out.println("��ǰ���Ϊ��" + this.curveMoney + "Ԫ");
	}

	public void withdrawMoney(double takeThread) { // ȡǮû��ͬ������
		System.out.println("��ѯ�����ʾ����ǰ�������Ϊ��" + this.curveMoney + "; ȡ�����Ϊ: "
				+ takeThread);
		System.out.println("���ڲ��������Ժ�......"); // ȡǮʱ�ȵȴ�500����
		try {
			Thread.sleep(500); // �߳�����
		} catch (Exception e) { // �����쳣
			e.printStackTrace();
		}
		System.out.println("�����ɹ���ȡ����" + takeThread);
		this.curveMoney = this.curveMoney - takeThread;
		System.out.println("��ǰ���Ϊ��" + this.curveMoney + "Ԫ");
	}

}

class SyncBank {
	private double curveMoney = 174.85; // �������е�Ǯ��

	public synchronized void sync_SaveMoney(double putThread) { // ��Ǯ��ͬ������
		System.out.println("��ǰ�˻�����ʣ���Ϊ" + this.curveMoney + "; ������Ϊ: "
				+ putThread);
		System.out.println("���ڲ��������Ժ�......"); // ��Ǯʱ�ȵȴ�300����
		try {
			Thread.sleep(300); // �߳�����
		} catch (Exception e) { // �����쳣
			e.printStackTrace();
		}
		System.out.println("�����ɹ��������" + putThread);
		this.curveMoney = this.curveMoney + putThread;
		System.out.println("��ǰ���Ϊ��" + this.curveMoney + "Ԫ");
	}

	public synchronized void sync_WithdrawMoney(double takeThread) {// ȡǮ��ͬ������
		System.out.println("��ѯ�����ʾ����ǰ�������Ϊ��" + this.curveMoney + "; ȡ�����Ϊ: "
				+ takeThread);
		System.out.println("���ڲ��������Ժ�......"); // ȡǮʱ�ȵȴ�500����
		try {
			Thread.sleep(500); // �߳�����
		} catch (Exception e) { // �����쳣
			e.printStackTrace();
		}
		System.out.println("�����ɹ���ȡ����" + takeThread);
		this.curveMoney = this.curveMoney - takeThread;
		System.out.println("��ǰ���Ϊ��" + this.curveMoney + "Ԫ");
	}
}

class CashMachines extends Thread { // �̳�Thread��ʵ���̷߳���
	private Bank bank = null; // �����ʵ��ʺŶ���

	private SyncBank sbank = null; // �����ʵ��ʺŶ���

	private String account = ""; // �����ʺŵķ���

	public CashMachines(Bank bank, String account) { // ���췽�����г�ʼ��
		this.account = account;
		this.bank = bank;
	}

	public CashMachines(SyncBank sbank, String account) { // ���췽�����г�ʼ��
		this.account = account;
		this.sbank = sbank;
	}

	public void run() { // ʵ��Thread�ķ���
		if (account.equals("saveMoney")) { // ��ͬ�������ò�ͬ�ķ���
			bank.saveMoney(800.0);
		} else if (account.equals("withdrawMoney")) {
			bank.withdrawMoney(300.0);
		} else if (account.equals("sync_SaveMoney")) {
			sbank.sync_SaveMoney(800.0);
		} else if (account.equals("sync_WithdrawMoney")) {
			sbank.sync_WithdrawMoney(300.0);
		}
	}
}
