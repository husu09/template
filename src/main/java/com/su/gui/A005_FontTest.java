package com.su.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * 绘制字体
 * @author husu
 * 2017/10/18
 * 字符从基线定位
 * */
public class A005_FontTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new FontFrame();
			frame.setTitle("FontFrame");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
class FontFrame extends JFrame {
	public FontFrame() {
		add(new FontComponent());
		pack();
	}
}
class FontComponent extends JComponent {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		String message = "Hello, World!";
		
		Font f = new Font("Serif", Font.PLAIN, 36);
		g2.setFont(f);
		
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(message, context);
		
		double x = (getWidth() - bounds.getWidth()) / 2;
		double y = (getHeight() - bounds.getHeight()) / 2;
		
		double ascent = -bounds.getY();
		double baseY = y + ascent;
		// 绘制文字
		g2.drawString(message, (int)x, (int)baseY);
		// 绘制基线
		g2.draw(new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY));
		// 绘制矩形
		Rectangle2D rect = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());
		g2.draw(rect);
		
		
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
}
