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
	private BufferedImage image;// 声明一个BufferedImage类对象
	private Dimension imageOfSize;// 声明一个Dimension类对象
	private volatile int currOffset;// 声明一个用于表示偏移量的变量
	private Thread thread;// 声明一个线程
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
				RenderingHints.VALUE_ANTIALIAS_ON);// 根据抗锯齿提示键和抗锯齿提示值来创建一个RenderingHints类对象
		renderHints.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);// 将指定的呈现提示键映射到此RenderingHints对象中的指定呈现指示值。
		BufferedImage scratchImage = new BufferedImage(1, 1,
				BufferedImage.TYPE_INT_RGB);// 构造一个类型为预定义的图像
		Graphics2D scratchG2 = scratchImage.createGraphics();// 创建一个Graphics2D类对象
		scratchG2.setRenderingHints(renderHints);// 设置呈现算法
		Font font = new Font("Serif", Font.BOLD | Font.ITALIC, 24);// 创建一个Font字体对象
		FontRenderContext frc = scratchG2.getFontRenderContext();// 创建一个FontRenderContext对象
		TextLayout tl = new TextLayout(text, font, frc);// 创建一个TextLayout对象
		Rectangle2D textBounds = tl.getBounds();// 创建一个Rectangle2D对象
		int textWidth = (int) Math.ceil(textBounds.getWidth());// 设置文字的显示宽度
		int textHeight = (int) Math.ceil(textBounds.getHeight());// 设置文字的显示长度
		int horizontalPad = 10;// 设置水平间距为10象素
		int verticalPad = 6;// 设置垂直间距为6象素
		imageOfSize = new Dimension(// 创建Dimension对象
				textWidth + horizontalPad, textHeight + verticalPad);
		image = new BufferedImage(
				// 创建BufferedImage对象
				imageOfSize.width, imageOfSize.height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHints(renderHints);
		int baselineOffset = (verticalPad / 2) - ((int) textBounds.getY());// 基线的偏移量
		g2.setColor(Color.black);// 设为黑色背影
		g2.fillRect(0, 0, imageOfSize.width, imageOfSize.height);
		g2.setColor(Color.WHITE);// 白色字体
		tl.draw(g2, 0, baselineOffset);
		scratchG2.dispose();
		scratchImage.flush();
		g2.dispose();
	}
	public void paint(Graphics g) {
		// 将当前的剪贴区设置为由给定坐标指定的矩形。
		g.setClip(0, 0, imageOfSize.width, imageOfSize.height);
		int localOffset = currOffset; // 本地偏移量是不断的变化的
		g.drawImage(image, -localOffset, 0, this);
		g.drawImage(image, imageOfSize.width - localOffset, 0, this);
		// 绘画出边框
		g.setColor(Color.red);
		g.drawRect(0, 0, imageOfSize.width - 1, imageOfSize.height - 1);
	}
	private void ScrollING() {// 执行滚动操作
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
		CryptoService st = new CryptoService("教师节快乐");
		JPanel p = new JPanel(new FlowLayout());
		p.add(st);
		JFrame f = new JFrame("滚动的文字");
		f.setContentPane(p);
		f.setSize(400, 100);
		f.setVisible(true);
	}
}
