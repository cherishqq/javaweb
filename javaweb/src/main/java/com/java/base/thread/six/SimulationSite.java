package com.java.base.thread.six;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SimulationSite extends Frame implements ActionListener {//模拟现场场景
	//定义全局成员常量
	protected static final int num_agents=10;
	protected static final int num_initial_agents=6;
	protected static final int max_customer_delay=9000;
	protected static final int max_teller_break=1000;
	protected static final int max_no_customers=2000;
	//创建Button组件
	private Button open = new Button("开门");
	private Button close = new Button("关门");
	private Button add = new Button("欢迎光临");
	private Button del = new Button("请慢走");
	private Bank bank = new Bank();
	private Finance supermarket = new Finance("");
	//添加窗口关闭事件
	private class WindowCloser extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			bank.stop();
			supermarket.stop();
			System.exit(0);
		}
	}
	public SimulationSite(){//构造方法，进行组件初始化和布局
		super("SimulationSite");
		Panel buttons = new Panel();
		buttons.setLayout(new FlowLayout());
		buttons.add(open);
		open.addActionListener(this);
		buttons.add(close);
		close.addActionListener(this);
		buttons.add(add);
		add.addActionListener(this);
		buttons.add(del);
		del.addActionListener(this);
		this.addWindowListener(new WindowCloser());
		this.setLayout(new BorderLayout());
		add("West",bank);
		add("East",supermarket);
		add("South",buttons);
		validate();
		pack();
		show();
		bank.start();
		supermarket.start();
	}
	public void actionPerformed(ActionEvent e) {//为单击按扭做事件监听
		// TODO Auto-generated method stub
		if(e.getSource()==open){
			bank.openDoor();
			supermarket.openDoor();
		}else if(e.getSource()==close){
			bank.closeDoor();
			supermarket.closeDoor();
		}else if(e.getSource()==add){
			bank.addAgent();
			supermarket.addAgent();
		}else if(e.getSource()==del){
			bank.retireAgent();
			supermarket.retireAgent();
		}
	}
	public static void main(String[] args){//本程序的入口处
		SimulationSite sl = new SimulationSite();
	}
}
 class Finance extends Panel implements Runnable {
	protected Penson[] person = new Penson[SimulationSite.num_agents];
	protected Label[] labelAgent = new Label[SimulationSite.num_agents];
	protected Label labelQueue = new Label("已服务的顾客数:0");
	protected Label labelServed = new Label("Customers servers:0");
	protected Label labelWait = new Label("Customers wait:0");
	protected int numAgents = SimulationSite.num_initial_agents;
	protected int numCustomer = 0;// 顾客数
	protected long totalWait = 0L;// 等待顾客数
	private Thread thread = null;
	private boolean doorIsOpen = false;
	public Finance(String title) {
		super();
		setup(title);
	}
	public  void updateDisplay() {
	}
	public  void generateCustomer() {
	}
	public  Customer requestCustomerFor(int id) {
		return null;
	}
	public void checkoutCustomer(int handled, long waitTime) {// 更新顾客的数量，和正在等待的总人数
		numCustomer++;
		totalWait += waitTime;
	}
	public void addAgent() {// 增加人员
		if (numAgents < SimulationSite.num_agents) {
			person[numAgents] = new Penson(this, numAgents);
			person[numAgents].start();
			numAgents++;
		}
	}
	public void retireAgent() {// 减少人员
		if (numAgents > 1) {
			person[numAgents - 1].setRunING();
			numAgents--;
		}
	}
	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			doorIsOpen = true;
			thread.start();// 启动当前线程
			for (int i = 0; i < numAgents; i++) {
				person[i].start();// 启动Person类中的线程
			}
		}
	}
	public void stop() {
		doorIsOpen = false;
		thread = null;
		for (int i = 0; i < numAgents; i++) {
			person[i].setRunING();
		}
	}
	public void openDoor() {// 营业
		doorIsOpen = true;
	}
	public void closeDoor() {// 打烊
		doorIsOpen = false;
	}
	public void run() {// 重写Runnable的run方法
		while (thread == Thread.currentThread()) {
			try {
				thread.sleep((int) Math.random()
						* SimulationSite.max_no_customers);
				if (doorIsOpen) {
					generateCustomer();
					updateDisplay();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void setup(String title) {// 设置状态
		Panel agentPanel = new Panel();
		agentPanel.setLayout(new GridLayout(SimulationSite.num_agents + 3, 1));
		for (int i = 0; i < SimulationSite.num_agents; i++) {
			labelAgent[i] = new Label("Penson" + i + ":served 0");
			agentPanel.add(labelAgent[i]);
			person[i] = new Penson(this, i);
		}
		for (int i = numAgents; i < SimulationSite.num_agents; i++) {
			labelAgent[i].setText("Penson" + i + ":inactive");
		}
		agentPanel.add(labelQueue);
		agentPanel.add(labelServed);
		agentPanel.add(labelWait);
		setLayout(new BorderLayout());
		add("Center", agentPanel);
		add("North", new Label(title));
	}
}
class Penson extends Thread {// 创建一个Thread类的子类Penson类
	private boolean running = false;// 是否停止运行的标志
	private Finance bn = null;
	private int id = -1;// 客户id
	private int numCustomers = 0;// 顾客数量
	public Penson(Finance _bn, int _id) {
		this.bn = _bn;
		this.id = _id;
	}
	public void start() {
		running = true;
		super.start();// 启动线程
	}
	public void setRunING() {// 设置执行状态
		running = false;
	}
	public int getNum() {// 获取顾客数量
		return numCustomers;
	}
	private void releaseCustomer(Customer customer) {// 释放顾客对象
		numCustomers++;
		bn.checkoutCustomer(numCustomers, customer.getWaitTime(new Date()));
	}
	public void run() {// 重写Thread的run方法
		while (running) {
			try {
				sleep((int) (Math.random() * SimulationSite.max_teller_break) + 1000);// 随面休眠
				Customer customer = bn.requestCustomerFor(id);
				if (customer != null) {
					sleep(customer.getDelayTime());
					releaseCustomer(customer);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
class Bank extends Finance implements Runnable {
	private ObjectQueue queue = null;
	public Bank() {
		super("中国北京银行");// 调用父类的带参构造方法
		queue = new ObjectQueue();// 创建ObjectQueue对象
	}
	public void updateDisplay() {// 更新显示
		labelServed.setText("已服务的顾客数:" + numCustomer);// 设置标签的显示内容
		if (numCustomer != 0) {
			labelWait.setText("平均等待:" + (totalWait / numCustomer));
			for (int i = 0; i < numAgents; i++) {
				labelAgent[i].setText("顾客:" + i + ": 已服务" + person[i].getNum());
			}
			for (int i = numAgents; i < SimulationSite.num_agents; i++) {
				labelAgent[i].setText("顾客:" + i + ": 未服务");
				labelQueue.setText("正在等待的顾客数:" + queue.getSize());
			}
		}
	}
	public void generateCustomer() {// 增加新的顾客，表示又有新的顾客光临
		queue.insert(new Customer());
	}
	public Customer requestCustomerFor(int id) {// 表示有顾客发出想进行服务的需求
		return queue.requestCustomer();
	}
}
//class SimulationSite extends Frame implements ActionListener {// 模拟现场场景
//	// 定义全局成员常量
//	protected static final int num_agents = 10;
//	protected static final int num_initial_agents = 6;
//	protected static final int max_customer_delay = 9000;
//	protected static final int max_teller_break = 1000;
//	protected static final int max_no_customers = 2000;
//	// 创建Button组件
//	private Button open = new Button("开门");
//	private Button close = new Button("关门");
//	private Button add = new Button("欢迎光临");
//	private Button del = new Button("请慢走");
//	private Bank bank = new Bank();
//	private Hypermarket supermarket = new Hypermarket();
//	// 添加窗口关闭事件
//	private class WindowCloser extends WindowAdapter {
//		public void windowClosing(WindowEvent e) {
//			bank.stop();
//			supermarket.stop();
//			System.exit(0);
//		}
//	}
//	public SimulationSite() {// 构造方法，进行组件初始化和布局
//		super("SimulationSite");
//		Panel buttons = new Panel();
//		buttons.setLayout(new FlowLayout());
//		buttons.add(open);
//		open.addActionListener(this);
//		buttons.add(close);
//		close.addActionListener(this);
//		buttons.add(add);
//		add.addActionListener(this);
//		buttons.add(del);
//		del.addActionListener(this);
//		this.addWindowListener(new WindowCloser());
//		this.setLayout(new BorderLayout());
//		add("West", bank);
//		add("East", supermarket);
//		add("South", buttons);
//		validate();
//		pack();
//		show();
//		bank.start();
//		supermarket.start();
//	}
//	public void actionPerformed(ActionEvent e) {// 为单击按扭做事件监听
//		// TODO Auto-generated method stub
//		if (e.getSource() == open) {
//			bank.openDoor();
//			supermarket.openDoor();
//		} else if (e.getSource() == close) {
//			bank.closeDoor();
//			supermarket.closeDoor();
//		} else if (e.getSource() == add) {
//			bank.addAgent();
//			supermarket.addAgent();
//		} else if (e.getSource() == del) {
//			bank.retireAgent();
//			supermarket.retireAgent();
//		}
//	}
//	public static void main(String[] args) {// 本程序的入口处
//		SimulationSite sl = new SimulationSite();
//	}
//}
class ObjectQueue {// 线程队列
	private List customers = new ArrayList();
	private synchronized Object performAction(String cmd, Object obj) {// 获取对象
		if (cmd.equals("insert")) {// 增加方法的操作流程
			if (customers.isEmpty())
				customers.add(obj);
			notify();
			return null;
		} else if (cmd.equals("size")) {// 获取容量
			return new Integer(customers.size());
		} else if (cmd.equals("retrieve")) {
			while (customers.size() == 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Customer c = (Customer) customers.get(0);
			customers.remove(0);
			return c;
		}
		return null;
	}
	public void insert(Customer c) {// 增加操作
		performAction("insert", c);
	}
	public int getSize() {// 获取容量
		return (((Integer) performAction("size", null)).intValue());
	}
	public Customer requestCustomer() {// 请求服务
		return (Customer) performAction("retrieve", null);
	}
}
class Customer {// 顾客类
	private Date date;// 声明一个日期类的对象
	public Customer() {// 构造方法，为date对象初始化
		date = new Date();
	}
	public int getDelayTime() {// 获取超时的时间
		return (int) (Math.random() * SimulationSite.max_customer_delay);
	}
	public long getWaitTime(Date now) {// 获取等待的时间
		return now.getTime() - date.getTime();
	}
}
