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

//Javaʵ�������ļ�����,�ڿͻ�������Web����������ָ���ļ��������ļ����档
public class ResumeUpload {
	private String downSource = "http://kent.dl.sourceforge.net/sourceforge/jamper/Sample.zip"; // ����Web��ַ���ļ���
	private String savePath = "d:\\temp"; // ������ļ�·��
	private String saveName = "����YY����.zip"; // �����ļ���
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
class FTPthread extends Thread { // �����ļ��߳���
	FileInfo siteInfoBean = null; // �ļ���ϢBean
	long[] nPos;
	long[] startPos; // ��ʼλ��
	long[] endPos; // ����λ��
	FilePart[] fileSplitterFetch; // ���̶߳���
	long nFileLength; // �ļ�����
	boolean bFirst = true; // �Ƿ��һ��ȡ�ļ�
	boolean bStop = false; // ֹͣ��־
	File tmpFile; // �ļ�������ʱ��Ϣ
	DataOutputStream output; // ������ļ��������
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
		// ����ļ�����
		// �ָ��ļ�
		// ʵ��PartCacth
		// ����PartCacth�߳�
		// �ȴ����̷߳���
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
			// �������߳�
			fileSplitterFetch = new FilePart[startPos.length];
			for (int i = 0; i < startPos.length; i++) {
				fileSplitterFetch[i] = new FilePart(siteInfoBean.getSSiteURL(),
						siteInfoBean.getSFilePath() + File.separator
								+ siteInfoBean.getSFileName(), startPos[i],
						endPos[i], i);
				AddInform.log("Thread " + i + " , ��ʼλ�� = " + startPos[i]
						+ ", ����λ�� = " + endPos[i]);
				fileSplitterFetch[i].start();
			}
			// �ȴ����߳̽���
			// int count = 0;
			// �Ƿ����whileѭ��
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
			System.out.println("�ļ����������");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// ����ļ�����
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
				return -2; // -2 ΪWeb��������Ӧ����
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
	// ���洫����Ϣ���ļ�ָ��λ�ã�
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
			System.out.println("���洫����Ϣʧ��");
		}
	}
	// ��ȡ�����������Ϣ���ļ�ָ��λ�ã�
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
			// �ж�ÿ����ļ���ʼλ���Ƿ���ڽ���λ��
			for (int i = 0; i < startPos.length; i++) {
				if (startPos[i] > endPos[i]) {
					startPos[i] = endPos[i];
				}
			}
		} catch (Exception e) {
			System.out.println("��ȡ�����������Ϣʧ��");
		}
	}
	private void processErrorCode(int nErrorCode) {
		System.err.println("Error Code : " + nErrorCode);
	}
	// ֹͣ�ļ�����
	public void doStop() {
		bStop = true;
		for (int i = 0; i < startPos.length; i++)
			fileSplitterFetch[i].splitterStop();
	}
}
class FileInfo { // �����ȡ����������ļ���Ϣ��
	private String sSiteURL; // ����URL����
	private String sFilePath; // ������ļ�·������
	private String sFileName; // �����ļ�������
	private int nSplitter; // ���崫���ļ�����ֵ
	public FileInfo() {
		this("", "", "", 5); // ���ô����ļ�����ֵ
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
	String sURL; // �����ļ�����ʱʹ�õı���
	long startPos; // �ֶ��ļ����俪ʼλ��
	long endPos; // �ֶ��ļ��������λ��
	int nThreadID; // ���߳�ID
	boolean bDownOver = false; // ����ļ�����
	boolean bStop = false; // ֹͣ�ļ�����
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
				System.out.println(getName() + " �߳������쳣");
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
class SaveFile implements Serializable { // ��������ļ���
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
			System.out.println("ͬ���洢��Ϣ�쳣");
		}
		return n;
	}
}
 class AddInform { // ���������ʾ��Ϣ���߳�sleep��
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
