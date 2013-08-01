package com.java.base.thread.six;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

//Java实现网络文件传输,在客户端请求Web服务器传输指定文件，并将文件保存。
public class ResumeUpload {
	private String downSource = "http://kent.dl.sourceforge.net/sourceforge/jamper/Sample.zip"; // 定义Web地址和文件名
	private String savePath = "d:\\temp"; // 定义存文件路径
	private String saveName = "汉仪YY字体.zip"; // 定义文件名
	public ResumeUpload() {
		try {
			FileInfo bean = new FileInfo(downSource, savePath, saveName, 5);
			FTPthread fileFetch = new FTPthread(bean);
			fileFetch.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ResumeUpload();
	}
}
class FTPthread extends Thread { // 传输文件线程类
	FileInfo siteInfoBean = null; // 文件信息Bean
	long[] nPos;
	long[] startPos; // 开始位置
	long[] endPos; // 结束位置
	FilePart[] fileSplitterFetch; // 子线程对象
	long nFileLength; // 文件长度
	boolean bFirst = true; // 是否第一次取文件
	boolean bStop = false; // 停止标志
	File tmpFile; // 文件传输临时信息
	DataOutputStream output; // 输出到文件的输出流
	public FTPthread(FileInfo bean) throws IOException {
		siteInfoBean = bean;
		tmpFile = new File(bean.getSFilePath() + File.separator
				+ bean.getSFileName() + ".info");
		if (tmpFile.exists()) {
			bFirst = false;
			readInform();
		} else {
			startPos = new long[bean.getNSplitter()];
			endPos = new long[bean.getNSplitter()];
		}
	}
	public void run() {
		// 获得文件长度
		// 分割文件
		// 实例PartCacth
		// 启动PartCacth线程
		// 等待子线程返回
		try {
			if (bFirst) {
				nFileLength = getFileSize();
				if (nFileLength == -1) {
					System.err.println("File Length is not known!");
				} else if (nFileLength == -2) {
					System.err.println("File is not access!");
				} else {
					for (int i = 0; i < startPos.length; i++) {
						startPos[i] = (long) (i * (nFileLength / startPos.length));
					}
					for (int i = 0; i < endPos.length - 1; i++) {
						endPos[i] = startPos[i + 1];
					}
					endPos[endPos.length - 1] = nFileLength;
				}
			}
			// 启动子线程
			fileSplitterFetch = new FilePart[startPos.length];
			for (int i = 0; i < startPos.length; i++) {
				fileSplitterFetch[i] = new FilePart(siteInfoBean.getSSiteURL(),
						siteInfoBean.getSFilePath() + File.separator
								+ siteInfoBean.getSFileName(), startPos[i],
						endPos[i], i);
				AddInform.log("Thread " + i + " , 开始位置 = " + startPos[i]
						+ ", 结束位置 = " + endPos[i]);
				fileSplitterFetch[i].start();
			}
			// 等待子线程结束
			// int count = 0;
			// 是否结束while循环
			boolean breakWhile = false;
			while (!bStop) {
				writeInform();
				AddInform.sleep(500);
				breakWhile = true;
				for (int i = 0; i < startPos.length; i++) {
					if (!fileSplitterFetch[i].bDownOver) {
						breakWhile = false;
						break;
					}
				}
				if (breakWhile)
					break;
			}
			System.out.println("文件传输结束！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 获得文件长度
	public long getFileSize() {
		int nFileLength = -1;
		try {
			URL url = new URL(siteInfoBean.getSSiteURL());
			HttpURLConnection httpConnection = (HttpURLConnection) url
					.openConnection();
			httpConnection.setRequestProperty("User-Agent", "NetFox");
			int responseCode = httpConnection.getResponseCode();
			if (responseCode >= 400) {
				processErrorCode(responseCode);
				return -2; // -2 为Web服务器响应错误
			}
			String sHeader;
			for (int i = 1;; i++) {
				sHeader = httpConnection.getHeaderFieldKey(i);
				if (sHeader != null) {
					if (sHeader.equals("Content-Length")) {
						nFileLength = Integer.parseInt(httpConnection
								.getHeaderField(sHeader));
						break;
					}
				} else
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		AddInform.log(nFileLength);
		return nFileLength;
	}
	// 保存传输信息（文件指针位置）
	private void writeInform() {
		try {
			output = new DataOutputStream(new FileOutputStream(tmpFile));
			output.writeInt(startPos.length);
			for (int i = 0; i < startPos.length; i++) {
				output.writeLong(fileSplitterFetch[i].startPos);
				output.writeLong(fileSplitterFetch[i].endPos);
			}
			output.close();
		} catch (Exception e) {
			System.out.println("保存传输信息失败");
		}
	}
	// 读取保存的下载信息（文件指针位置）
	private void readInform() {
		try {
			DataInputStream input = new DataInputStream(new FileInputStream(
					tmpFile));
			int nCount = input.readInt();
			startPos = new long[nCount];
			endPos = new long[nCount];
			for (int i = 0; i < startPos.length; i++) {
				startPos[i] = input.readLong();
				endPos[i] = input.readLong();
			}
			input.close();
			// 判断每块的文件开始位置是否大于结束位置
			for (int i = 0; i < startPos.length; i++) {
				if (startPos[i] > endPos[i]) {
					startPos[i] = endPos[i];
				}
			}
		} catch (Exception e) {
			System.out.println("读取保存的下载信息失败");
		}
	}
	private void processErrorCode(int nErrorCode) {
		System.err.println("Error Code : " + nErrorCode);
	}
	// 停止文件传输
	public void doStop() {
		bStop = true;
		for (int i = 0; i < startPos.length; i++)
			fileSplitterFetch[i].splitterStop();
	}
}
class FileInfo { // 定义获取和设置相关文件信息类
	private String sSiteURL; // 定义URL变量
	private String sFilePath; // 定义存文件路径变量
	private String sFileName; // 定义文件名变量
	private int nSplitter; // 定义传输文件计数值
	public FileInfo() {
		this("", "", "", 5); // 设置传输文件计数值
	}
	public FileInfo(String sURL, String sPath, String sName, int nSpiltter) {
		sSiteURL = sURL;
		sFilePath = sPath;
		sFileName = sName;
		this.nSplitter = nSpiltter;
	}
	public String getSSiteURL() {
		return sSiteURL;
	}
	public void setSSiteURL(String value) {
		sSiteURL = value;
	}
	public String getSFilePath() {
		return sFilePath;
	}
	public void setSFilePath(String value) {
		sFilePath = value;
	}
	public String getSFileName() {
		return sFileName;
	}
	public void setSFileName(String value) {
		sFileName = value;
	}
	public int getNSplitter() {
		return nSplitter;
	}
	public void setNSplitter(int nCount) {
		nSplitter = nCount;
	}
}
class FilePart extends Thread {
	String sURL; // 定义文件传输时使用的变量
	long startPos; // 分段文件传输开始位置
	long endPos; // 分段文件传输结束位置
	int nThreadID; // 子线程ID
	boolean bDownOver = false; // 完成文件传输
	boolean bStop = false; // 停止文件传输
	SaveFile fileAccess = null;
	public FilePart(String sURL, String sName, long nStart, long nEnd, int id)
			throws IOException {
		this.sURL = sURL;
		this.startPos = nStart;
		this.endPos = nEnd;
		nThreadID = id;
		fileAccess = new SaveFile(sName, startPos);
	}
	public void run() {
		while (startPos < endPos && !bStop) {
			try {
				URL url = new URL(sURL);
				HttpURLConnection httpConnection = (HttpURLConnection) url
						.openConnection();
				httpConnection.setRequestProperty("User-Agent", "NetFox");
				String sProperty = "bytes=" + startPos + "-";
				httpConnection.setRequestProperty("RANGE", sProperty);
				AddInform.log(sProperty);
				InputStream input = httpConnection.getInputStream();
				byte[] b = new byte[1024];
				int nRead;
				while ((nRead = input.read(b, 0, 1024)) > 0
						&& startPos < endPos && !bStop) {
					startPos += fileAccess.write(b, 0, nRead);
				}
				AddInform.log("Thread " + nThreadID + " is over!");
				bDownOver = true;
			} catch (Exception e) {
				System.out.println(getName() + " 线程运行异常");
			}
		}
		bDownOver = true;
	}
	public void logResponseHead(HttpURLConnection con) {
		for (int i = 1;; i++) {
			String header = con.getHeaderFieldKey(i);
			if (header != null)
				AddInform.log(header + " : " + con.getHeaderField(header));
			else
				break;
		}
	}
	public void splitterStop() {
		bStop = true;
	}
}
class SaveFile implements Serializable { // 定义访问文件类
	RandomAccessFile oSavedFile;
	long nPos;
	public SaveFile() throws IOException {
		this("", 0);
	}
	public SaveFile(String sName, long nPos) throws IOException {
		oSavedFile = new RandomAccessFile(sName, "rw");
		this.nPos = nPos;
		oSavedFile.seek(nPos);
	}
	public synchronized int write(byte[] b, int nStart, int nLen) {
		int n = -1;
		try {
			oSavedFile.write(b, nStart, nLen);
			n = nLen;
		} catch (IOException e) {
			System.out.println("同步存储信息异常");
		}
		return n;
	}
}
 class AddInform { // 定义输出提示信息及线程sleep类
	public AddInform() {
	}
	public static void sleep(int nSecond) {
		try {
			Thread.sleep(nSecond);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void log(String sMsg) {
		System.out.println(sMsg);
	}
	public static void log(int sMsg) {
		System.out.println(sMsg);
	}
}
