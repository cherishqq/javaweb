package com.java.base.thread.two;

public class ATMCashMachines {
	public static void main(String[] args) { // java程序主入口处
		Bank bank = new Bank();// 实例化Bank对象
		SyncBank sbank = new SyncBank();// 实例化SyncBank对象
		System.out.println("1.存、取线程没有采取同步时，执行存取操作时，其工作流程如下：");
		// 存钱没有采用同步机制
		Thread putThread = new CashMachines(bank, "saveMoney");
		// 取钱没有同步机制
		Thread takeThread = new CashMachines(bank, "withdrawMoney");
		putThread.start(); // 启动putThread线程
		takeThread.start(); // 启动takeThread线程
		try {
			putThread.join(); // 等待两线程运行结束
			takeThread.join();
		} catch (Exception e) { // 捕获异常
			System.out.println("两线程运行出错：" + e.getMessage());
		}
		System.out.println();
		bank = new Bank();
		System.out.println("2.存、取线程设置为同步时，执行存取操作时，其工作流程如下：");
		putThread = new CashMachines(sbank, "sync_SaveMoney"); // 创建CashMachines对象，存钱有同步机制
		takeThread = new CashMachines(sbank, "sync_WithdrawMoney"); // 取钱有同步机制
		putThread.start(); // 启动线程
		takeThread.start(); // 启动线程
	}

}

class Bank {
	private double curveMoney = 174.85; // 存入银行的钱数

	public void saveMoney(double putThread) { // 存钱没有采用同步机制
		System.out.println("当前账户中所剩余额为" + this.curveMoney + "; 存入金额为: "
				+ putThread);
		System.out.println("正在操作，请稍候......"); // 存钱时先等待300毫秒
		try {
			Thread.sleep(300); // 线程休眠
		} catch (Exception e) { // 捕获异常
			e.printStackTrace();
		}
		System.out.println("操作成功，存入金额：" + putThread);
		this.curveMoney = this.curveMoney + putThread;
		System.out.println("当前余额为：" + this.curveMoney + "元");
	}

	public void withdrawMoney(double takeThread) { // 取钱没有同步机制
		System.out.println("查询余额显示，当前可用余额为：" + this.curveMoney + "; 取出金额为: "
				+ takeThread);
		System.out.println("正在操作，请稍候......"); // 取钱时先等待500毫秒
		try {
			Thread.sleep(500); // 线程休眠
		} catch (Exception e) { // 捕获异常
			e.printStackTrace();
		}
		System.out.println("操作成功，取出金额：" + takeThread);
		this.curveMoney = this.curveMoney - takeThread;
		System.out.println("当前余额为：" + this.curveMoney + "元");
	}

}

class SyncBank {
	private double curveMoney = 174.85; // 存入银行的钱数

	public synchronized void sync_SaveMoney(double putThread) { // 存钱有同步机制
		System.out.println("当前账户中所剩余额为" + this.curveMoney + "; 存入金额为: "
				+ putThread);
		System.out.println("正在操作，请稍候......"); // 存钱时先等待300毫秒
		try {
			Thread.sleep(300); // 线程休眠
		} catch (Exception e) { // 捕获异常
			e.printStackTrace();
		}
		System.out.println("操作成功，存入金额：" + putThread);
		this.curveMoney = this.curveMoney + putThread;
		System.out.println("当前余额为：" + this.curveMoney + "元");
	}

	public synchronized void sync_WithdrawMoney(double takeThread) {// 取钱有同步机制
		System.out.println("查询余额显示，当前可用余额为：" + this.curveMoney + "; 取出金额为: "
				+ takeThread);
		System.out.println("正在操作，请稍候......"); // 取钱时先等待500毫秒
		try {
			Thread.sleep(500); // 线程休眠
		} catch (Exception e) { // 捕获异常
			e.printStackTrace();
		}
		System.out.println("操作成功，取出金额：" + takeThread);
		this.curveMoney = this.curveMoney - takeThread;
		System.out.println("当前余额为：" + this.curveMoney + "元");
	}
}

class CashMachines extends Thread { // 继承Thread类实现线程方法
	private Bank bank = null; // 待访问的帐号对象

	private SyncBank sbank = null; // 待访问的帐号对象

	private String account = ""; // 访问帐号的方法

	public CashMachines(Bank bank, String account) { // 构造方法进行初始化
		this.account = account;
		this.bank = bank;
	}

	public CashMachines(SyncBank sbank, String account) { // 构造方法进行初始化
		this.account = account;
		this.sbank = sbank;
	}

	public void run() { // 实现Thread的方法
		if (account.equals("saveMoney")) { // 不同参数调用不同的方法
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
