package com.java.base.thread.six;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CryptoService extends JComponent {
	private BufferedImage image;// ����һ��BufferedImage�����
	private Dimension imageOfSize;// ����һ��Dimension�����
	private volatile int currOffset;// ����һ�����ڱ�ʾƫ�����ı���
	private Thread thread;// ����һ���߳�
	private volatile boolean flag;
	public CryptoService(String text) {
		currOffset = 0;
		buildImage(text);
		setMinimumSize(imageOfSize);
		setPreferredSize(imageOfSize);
		setMaximumSize(imageOfSize);
		setSize(imageOfSize);
		flag = true;
		Runnable r = new Runnable() {
			public void run() {
				try {
					ScrollING();
				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		};
		thread = new Thread(r, "ScrollText");
		thread.start();
	}
	private void buildImage(String text) {
		RenderingHints renderHints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);// ���ݿ������ʾ���Ϳ������ʾֵ������һ��RenderingHints�����
		renderHints.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);// ��ָ���ĳ�����ʾ��ӳ�䵽��RenderingHints�����е�ָ������ָʾֵ��
		BufferedImage scratchImage = new BufferedImage(1, 1,
				BufferedImage.TYPE_INT_RGB);// ����һ������ΪԤ�����ͼ��
		Graphics2D scratchG2 = scratchImage.createGraphics();// ����һ��Graphics2D�����
		scratchG2.setRenderingHints(renderHints);// ���ó����㷨
		Font font = new Font("Serif", Font.BOLD | Font.ITALIC, 24);// ����һ��Font�������
		FontRenderContext frc = scratchG2.getFontRenderContext();// ����һ��FontRenderContext����
		TextLayout tl = new TextLayout(text, font, frc);// ����һ��TextLayout����
		Rectangle2D textBounds = tl.getBounds();// ����һ��Rectangle2D����
		int textWidth = (int) Math.ceil(textBounds.getWidth());// �������ֵ���ʾ���
		int textHeight = (int) Math.ceil(textBounds.getHeight());// �������ֵ���ʾ����
		int horizontalPad = 10;// ����ˮƽ���Ϊ10����
		int verticalPad = 6;// ���ô�ֱ���Ϊ6����
		imageOfSize = new Dimension(// ����Dimension����
				textWidth + horizontalPad, textHeight + verticalPad);
		image = new BufferedImage(
				// ����BufferedImage����
				imageOfSize.width, imageOfSize.height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHints(renderHints);
		int baselineOffset = (verticalPad / 2) - ((int) textBounds.getY());// ���ߵ�ƫ����
		g2.setColor(Color.black);// ��Ϊ��ɫ��Ӱ
		g2.fillRect(0, 0, imageOfSize.width, imageOfSize.height);
		g2.setColor(Color.WHITE);// ��ɫ����
		tl.draw(g2, 0, baselineOffset);
		scratchG2.dispose();
		scratchImage.flush();
		g2.dispose();
	}
	public void paint(Graphics g) {
		// ����ǰ�ļ���������Ϊ�ɸ�������ָ���ľ��Ρ�
		g.setClip(0, 0, imageOfSize.width, imageOfSize.height);
		int localOffset = currOffset; // ����ƫ�����ǲ��ϵı仯��
		g.drawImage(image, -localOffset, 0, this);
		g.drawImage(image, imageOfSize.width - localOffset, 0, this);
		// �滭���߿�
		g.setColor(Color.red);
		g.drawRect(0, 0, imageOfSize.width - 1, imageOfSize.height - 1);
	}
	private void ScrollING() {// ִ�й�������
		while (flag) {
			try {
				Thread.sleep(100); 
				currOffset = (currOffset + 1) % imageOfSize.width;
				repaint();
			} catch (InterruptedException x) {
				Thread.currentThread().interrupt();
			}
		}
	}
	public static void main(String[] args) {
		CryptoService st = new CryptoService("��ʦ�ڿ���");
		JPanel p = new JPanel(new FlowLayout());
		p.add(st);
		JFrame f = new JFrame("����������");
		f.setContentPane(p);
		f.setSize(400, 100);
		f.setVisible(true);
	}
}
