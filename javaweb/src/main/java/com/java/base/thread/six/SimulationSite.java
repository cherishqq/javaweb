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


public class SimulationSite extends Frame implements ActionListener {//ģ���ֳ�����
	//����ȫ�ֳ�Ա����
	protected static final int num_agents=10;
	protected static final int num_initial_agents=6;
	protected static final int max_customer_delay=9000;
	protected static final int max_teller_break=1000;
	protected static final int max_no_customers=2000;
	//����Button���
	private Button open = new Button("����");
	private Button close = new Button("����");
	private Button add = new Button("��ӭ����");
	private Button del = new Button("������");
	private Bank bank = new Bank();
	private Finance supermarket = new Finance("");
	//��Ӵ��ڹر��¼�
	private class WindowCloser extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			bank.stop();
			supermarket.stop();
			System.exit(0);
		}
	}
	public SimulationSite(){//���췽�������������ʼ���Ͳ���
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
	public void actionPerformed(ActionEvent e) {//Ϊ������Ť���¼�����
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
	public static void main(String[] args){//���������ڴ�
		SimulationSite sl = new SimulationSite();
	}
}
 class Finance extends Panel implements Runnable {
	protected Penson[] person = new Penson[SimulationSite.num_agents];
	protected Label[] labelAgent = new Label[SimulationSite.num_agents];
	protected Label labelQueue = new Label("�ѷ���Ĺ˿���:0");
	protected Label labelServed = new Label("Customers servers:0");
	protected Label labelWait = new Label("Customers wait:0");
	protected int numAgents = SimulationSite.num_initial_agents;
	protected int numCustomer = 0;// �˿���
	protected long totalWait = 0L;// �ȴ��˿���
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
	public void checkoutCustomer(int handled, long waitTime) {// ���¹˿͵������������ڵȴ���������
		numCustomer++;
		totalWait += waitTime;
	}
	public void addAgent() {// ������Ա
		if (numAgents < SimulationSite.num_agents) {
			person[numAgents] = new Penson(this, numAgents);
			person[numAgents].start();
			numAgents++;
		}
	}
	public void retireAgent() {// ������Ա
		if (numAgents > 1) {
			person[numAgents - 1].setRunING();
			numAgents--;
		}
	}
	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			doorIsOpen = true;
			thread.start();// ������ǰ�߳�
			for (int i = 0; i < numAgents; i++) {
				person[i].start();// ����Person���е��߳�
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
	public void openDoor() {// Ӫҵ
		doorIsOpen = true;
	}
	public void closeDoor() {// ����
		doorIsOpen = false;
	}
	public void run() {// ��дRunnable��run����
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
	private void setup(String title) {// ����״̬
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
class Penson extends Thread {// ����һ��Thread�������Penson��
	private boolean running = false;// �Ƿ�ֹͣ���еı�־
	private Finance bn = null;
	private int id = -1;// �ͻ�id
	private int numCustomers = 0;// �˿�����
	public Penson(Finance _bn, int _id) {
		this.bn = _bn;
		this.id = _id;
	}
	public void start() {
		running = true;
		super.start();// �����߳�
	}
	public void setRunING() {// ����ִ��״̬
		running = false;
	}
	public int getNum() {// ��ȡ�˿�����
		return numCustomers;
	}
	private void releaseCustomer(Customer customer) {// �ͷŹ˿Ͷ���
		numCustomers++;
		bn.checkoutCustomer(numCustomers, customer.getWaitTime(new Date()));
	}
	public void run() {// ��дThread��run����
		while (running) {
			try {
				sleep((int) (Math.random() * SimulationSite.max_teller_break) + 1000);// ��������
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
		super("�й���������");// ���ø���Ĵ��ι��췽��
		queue = new ObjectQueue();// ����ObjectQueue����
	}
	public void updateDisplay() {// ������ʾ
		labelServed.setText("�ѷ���Ĺ˿���:" + numCustomer);// ���ñ�ǩ����ʾ����
		if (numCustomer != 0) {
			labelWait.setText("ƽ���ȴ�:" + (totalWait / numCustomer));
			for (int i = 0; i < numAgents; i++) {
				labelAgent[i].setText("�˿�:" + i + ": �ѷ���" + person[i].getNum());
			}
			for (int i = numAgents; i < SimulationSite.num_agents; i++) {
				labelAgent[i].setText("�˿�:" + i + ": δ����");
				labelQueue.setText("���ڵȴ��Ĺ˿���:" + queue.getSize());
			}
		}
	}
	public void generateCustomer() {// �����µĹ˿ͣ���ʾ�����µĹ˿͹���
		queue.insert(new Customer());
	}
	public Customer requestCustomerFor(int id) {// ��ʾ�й˿ͷ�������з��������
		return queue.requestCustomer();
	}
}
//class SimulationSite extends Frame implements ActionListener {// ģ���ֳ�����
//	// ����ȫ�ֳ�Ա����
//	protected static final int num_agents = 10;
//	protected static final int num_initial_agents = 6;
//	protected static final int max_customer_delay = 9000;
//	protected static final int max_teller_break = 1000;
//	protected static final int max_no_customers = 2000;
//	// ����Button���
//	private Button open = new Button("����");
//	private Button close = new Button("����");
//	private Button add = new Button("��ӭ����");
//	private Button del = new Button("������");
//	private Bank bank = new Bank();
//	private Hypermarket supermarket = new Hypermarket();
//	// ��Ӵ��ڹر��¼�
//	private class WindowCloser extends WindowAdapter {
//		public void windowClosing(WindowEvent e) {
//			bank.stop();
//			supermarket.stop();
//			System.exit(0);
//		}
//	}
//	public SimulationSite() {// ���췽�������������ʼ���Ͳ���
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
//	public void actionPerformed(ActionEvent e) {// Ϊ������Ť���¼�����
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
//	public static void main(String[] args) {// ���������ڴ�
//		SimulationSite sl = new SimulationSite();
//	}
//}
class ObjectQueue {// �̶߳���
	private List customers = new ArrayList();
	private synchronized Object performAction(String cmd, Object obj) {// ��ȡ����
		if (cmd.equals("insert")) {// ���ӷ����Ĳ�������
			if (customers.isEmpty())
				customers.add(obj);
			notify();
			return null;
		} else if (cmd.equals("size")) {// ��ȡ����
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
	public void insert(Customer c) {// ���Ӳ���
		performAction("insert", c);
	}
	public int getSize() {// ��ȡ����
		return (((Integer) performAction("size", null)).intValue());
	}
	public Customer requestCustomer() {// �������
		return (Customer) performAction("retrieve", null);
	}
}
class Customer {// �˿���
	private Date date;// ����һ��������Ķ���
	public Customer() {// ���췽����Ϊdate�����ʼ��
		date = new Date();
	}
	public int getDelayTime() {// ��ȡ��ʱ��ʱ��
		return (int) (Math.random() * SimulationSite.max_customer_delay);
	}
	public long getWaitTime(Date now) {// ��ȡ�ȴ���ʱ��
		return now.getTime() - date.getTime();
	}
}
