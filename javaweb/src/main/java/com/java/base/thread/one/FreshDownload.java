package com.java.base.thread.one;



import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FreshDownload extends JFrame {
	private JPanel mainPanel; // 网络快车的主面板
	private JTextField webField = new JTextField(); // 下载地址的文本框
	private JTextField localFile = new JTextField(); // 下载到本地的文本框
	private JTextField fileNameField = new JTextField(); // 文件名对应的文本框
	private JTextField categoryField = new JTextField(); // 分类对应的文本框
	private JButton button = new JButton(); // 下载按钮
	private JButton button1 = new JButton(); // 取消下载按钮
	private JLabel targetLabel = new JLabel(); // 目标标签
	private JLabel localLabel = new JLabel(); // 下载到本地标签
	private JLabel fileName = new JLabel(); // 下载的文件名
	private JLabel category = new JLabel(); // 分类
	private JLabel content = new JLabel(); // 信息内容
	private JLabel tips = new JLabel(); // 信息提示
	private JTextArea textArea = new JTextArea(); // 显示下载记录的文本域
	private String urlPath = new String(); // 下载地址
	private String saveFileAs = new String(); // 另存为
	public FreshDownload() { // 构造方法进行初始化
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			initPanel(); // 调用方法初始化面板
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	private void initPanel() throws Exception { // 初始化面板
		mainPanel = (JPanel) this.getContentPane(); // 创建面板
		mainPanel.setLayout(null); // 没有设置面板布局
		this.setSize(new Dimension(480, 420)); // 面板的大小
		this.setLocation(100, 100); // 面板位置
		this.setTitle("模仿网络快车多线程下载"); // 面板标题
		targetLabel.setBounds(new Rectangle(20, 20, 120, 20)); // 标签的位置
		targetLabel.setText("下载网址： ");
		webField.setBounds(new Rectangle(100, 20, 250, 20)); // 设置文本框的位置
		// 设置默认下载路径
	webField.setText("http://zhanjia.javaeye.com/topics/download/d3682cdf-04be-3a57-89a4-93cbef5ae038");
		fileName.setText("文件名：");
		fileName.setBounds(20, 50, 120, 20);// 文件名标签的位置
		fileNameField.setText("java实例.jar");// 文件文本框中默认的内容
		fileNameField.setBounds(100, 50, 120, 20);// 文件文本框中的位置
		category.setText("分类：");
		category.setBounds(20, 80, 120, 20);// 分类标签的位置
		categoryField.setText("其他");
		categoryField.setBounds(100, 80, 120, 20);// 分类文本框中的位置
		localLabel.setBounds(new Rectangle(20, 110, 120, 20)); // 标签的位置
		localLabel.setText("下载到： ");
		localFile.setBounds(new Rectangle(100, 110, 120, 20)); // 设置文本框的位置
		localFile.setText("F:\\Downloads");// 设置默认另存为
		button.setBounds(new Rectangle(230, 110, 60, 20)); // 按钮的位置
		button.setText("下载");
		button1.setBounds(new Rectangle(290, 110, 60, 20)); // 按钮的位置
		button1.setText("取消");
		tips.setBounds(new Rectangle(20, 130, 120, 20)); // 标签的位置
		tips.setText("信息提示： ");
		content.setText("F盘可用空间71.7GB，任务所需空间24.3MB");
		content.setBounds(100, 130, 250, 20);
		button.addActionListener(new ActionListener() { // 按钮添加监听事件
					public void actionPerformed(ActionEvent e) {
						getActionPerformed(e); // 调用事件
					}
				});
		// 创建有滑动条的面板将文本域放在上面
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(new Rectangle(20, 160, 400, 200)); // 面板的位置
		textArea.setEditable(false); // 不可编辑
		mainPanel.add(webField, null); // 将文本框添加到面板中
		mainPanel.add(localFile, null); // 将文本框添加到面板中
		mainPanel.add(fileName, null);// 将文件名标签添加到面板中
		mainPanel.add(fileNameField, null);// 将文件名文本框添加到面板中
		mainPanel.add(category, null);// 将分类标签添加到面板中
		mainPanel.add(categoryField, null);// 将分类文本框添加到面板中
		mainPanel.add(targetLabel, null); // 将标签添加到面板中
		mainPanel.add(localLabel, null); // 将标签添加到面板中
		mainPanel.add(button, null); // 将下载按钮添加到面板中
		mainPanel.add(button1, null); // 将取消按钮添加到面板中
		mainPanel.add(tips, null); // 将标签添加到面板中
		mainPanel.add(content, null); // 将标签添加到面板中
		mainPanel.add(scrollPane, null); // 将滑动条添加到面板中
		urlPath = webField.getText(); // 获得文本框中的文本
		saveFileAs = localFile.getText();// 获得文本框中的文本
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置默认关闭操作
	}
	// 点击事件触发方法，启动分析下载文件的进程
	public void getActionPerformed(ActionEvent e) {
		urlPath = webField.getText(); // 获得目标文件的网址
		saveFileAs = localFile.getText(); // 获得另存为的地址
		if (urlPath.compareTo("") == 0)
			textArea.setText("请输入要下载的文件完整地址");
		else if (saveFileAs.compareTo("") == 0) {
			textArea.setText("请输入保存文件完整地址");
		} else {
			try {
				SearchAndDown downFile = new SearchAndDown(urlPath, saveFileAs,
						5, textArea); // 传参数实例化下载文件对象
				downFile.start(); // 启动下载文件的线程
				textArea.append("主线程启动...");
			} catch (Exception ec) { // 捕获异常
				System.out.println("下载文件出错：" + ec.getMessage());
			}
		}
	}
	public static void main(String[] args) { // java程序主入口处
		FreshDownload frame = new FreshDownload(); // 实例化对象进行初始化
		frame.setVisible(true); // 设置窗口可视
	}
}
class SearchAndDown extends Thread { // 分析下载的文件并启动下载进程
	String urlPath; // 下载文件的地址
	String saveFileAs; // 文件另存为
	int threadCount; // 线程总数
	String log = new String(); // 下载过程的日志记录
	JTextArea textArea = new JTextArea(); // 创建文本域
	long[] position;
	long[] start; // 每个线程开始位置
	long[] end; // 每个线程结束位置
	BatchFile[] file; // 子线程对象
	long fileLength; // 下载的文件的长度
	public SearchAndDown(String urlPath, String saveFileAs, int threadCount,
			JTextArea textArea) { // 构造方法进行初始化
		this.urlPath = urlPath;
		this.saveFileAs = saveFileAs;
		this.threadCount = threadCount;
		this.textArea = textArea;
		start = new long[threadCount];
		end = new long[threadCount];
	}
	public void run() { // 实现Thread类的方法
		log = "目标文件： " + urlPath;
		textArea.append("\n" + log); // 日志写入文本域
		log = "\n 线程总数： " + threadCount;
		textArea.append("\n" + log);
		try {
			fileLength = getSize(); // 获得文件长度
			if (fileLength == -1) { // 不可获取文件长度或没找到资源
				textArea.append("\n 不可知的文件长度！请重试！！");
			} else {
				if (fileLength == -2) { // 无法获取文件或没有找到资源
					textArea.append("\n 文件无法获取,没有找到指定资源,请重试！！");
				} else { // 循环对每个线程的开始位置赋值
					for (int i = 0; i < start.length; i++) {
						start[i] = (long) (i * (fileLength / start.length));
					}
					for (int i = 0; i < end.length - 1; i++)
						// 循环对每个线程的结束位置赋值
						end[i] = start[i + 1];
					// 最后一线程结束位置是文件长度
					end[end.length - 1] = fileLength;
					for (int i = 0; i < start.length; i++) { // 循环显示每线程开始和结束位置
						log = "线程：" + i + "下载范围：" + start[i] + "--" + end[i];
						textArea.append("\n" + log);
					}
					file = new BatchFile[start.length];
					for (int i = 0; i < start.length; i++) { // 启动一组子线程
						file[i] = new BatchFile(urlPath, saveFileAs, start[i],
								end[i], i, textArea);
						log = "线程 " + i + "启动";
						textArea.append("\n" + log);
						file[i].start(); // 启动线程
					}
					boolean breakWhile = true;
					while (breakWhile) { // 当条件始终为true时进行循环
						Thread.sleep(500); // 线程休眠
						breakWhile = false;
						for (int i = 0; i < file.length; i++) {
							if (!file[i].isDone) { // 循环判断每个线程是否结束
								breakWhile = true;
								break;
							}
						}
					}
					textArea.append("\n文件传输结束！");// 文件传输结束
				}
			}
		} catch (Exception ex) { // 捕获异常
			ex.printStackTrace();
		}
	}
	public long getSize() { // 获得文件的长度的方法
		int fileLength = -1;
		try {
			URL url = new URL(urlPath); // 根据网址创建URL对象
			HttpURLConnection httpConnection = (HttpURLConnection) (url
					.openConnection()); // 创建远程对象连接对象
			int responseCode = httpConnection.getResponseCode();
			if (responseCode >= 400) { // 没有获得响应信息
				System.out.println("Web服务器响应错误");
				return -2; // Web服务器响应错误
			}
			String sHeader;
			for (int i = 1;; i++) { // 查标识文件长度文件头获文件长度
				sHeader = httpConnection.getHeaderFieldKey(i);
				if (sHeader != null) {
					if (sHeader.equals("Content-Length")) {// 查找标识文件长度的文件头
						fileLength = Integer.parseInt(httpConnection
								.getHeaderField(sHeader));
						break;
					}
				} else {
					break;
				}
			}
		} catch (Exception e) { // 捕获异常
			System.out.println("无法获得文件长度:" + e.getMessage());
		}
		return fileLength;
	}
}
class BatchFile extends Thread {
	String urlPath; // 下载文件的地址
	long start; // 线程的开始位置
	long end; // 线程的结束位置
	int threadID;
	JTextArea textArea = new JTextArea(); // 创建文本域
	boolean isDone = false; // 是否下载完毕
	RandomAccessFile random;
	public BatchFile(String urlPath, String saveAs, long nStart, long nEnd,
			int id, JTextArea textArea) {
		this.urlPath = urlPath;
		this.start = nStart;
		this.end = nEnd;
		this.threadID = id;
		this.textArea = textArea;
		try {
			random = new RandomAccessFile(saveAs, "rw"); // 创建随机访问对象，以读/写方式
			random.seek(start); // 定位文件指针到startPosition位置
		} catch (Exception e) { // 捕获异常
			System.out.println("创建随机访问对象出错：" + e.getMessage());
		}
	}
	public void run() { // 实现Thread类的方法
		try {
			URL url = new URL(urlPath); // 根据网址创建URL对象
			HttpURLConnection httpConnection = (HttpURLConnection) url
					.openConnection(); // 创建远程对象连接对象
			String sProperty = "bytes=" + start + "-";
			httpConnection.setRequestProperty("RANGE", sProperty);
			textArea.append("\n 线程" + threadID + "下载文件!  请等待...");
			InputStream input = httpConnection.getInputStream();// 获得输入流对象
			byte[] buf = new byte[1024]; // 创建字节数据存储文件的数据
			int splitSpace;
			splitSpace = (int) end - (int) start; // 获得每个线程的间隔
			if (splitSpace > 1024)
				splitSpace = 1024;
			// 读取文件信息
			while (input.read(buf, 0, splitSpace) > 0 && start < end) {
				splitSpace = (int) end - (int) start;
				if (splitSpace > 1024)
					splitSpace = 1024;
				textArea.append("\n线程: " + threadID + " 开始位置: " + start
						+ "，  间隔长度: " + splitSpace);
				random.write(buf, 0, splitSpace); // 写入文件
				start += splitSpace; // 开始位置改变
			}
			textArea.append("\n 线程" + threadID + "下载完毕！！");
			random.close(); // 释放资源
			input.close();
			isDone = true;
		} catch (Exception e) { // 捕获异常
			System.out.println("多线程下载文件出错：" + e.getMessage());
		}
	}
}
