package com.su.map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * @version 1.33 2007-05-12
 * @author Cay Horstmann
 */
public class MapTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new DrawFrame();
			frame.setTitle("DrawTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

/**
 * A frame that contains a panel with drawings
 */
class DrawFrame extends JFrame {
	public DrawFrame() {
		add(new DrawComponent());
		pack();
	}
}

/**
 * A component that displays rectangles and ellipses.
 */
class DrawComponent extends JComponent {
	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HEIGHT = 1000;

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// 地图大小
		int size = 100;
		// 绘制地图
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Rectangle2D rect = new Rectangle2D.Double(x * 10, y * 10, 10, 10);
				g2.draw(rect);

			}
		}
		// 环
		int[] rings = { 0, 20, 40, 60, 80, size };
		int radius = size / 2;
		// 绘制环
		for (int i = 0; i < rings.length - 1; i++) {
			// 内环坐标
			int iLeftTop = radius - rings[i];
			int iRightTop = radius + rings[i];
			int iLeftDown = radius - rings[i];
			int iRightDown = radius + rings[i];
			
			// 外环坐标
			int oLeftTop = radius - rings[i + 1];
			int oRightTop = radius + rings[i + 1];
			int oLeftDown = radius - rings[i + 1];
			int oRightDown = radius + rings[i + 1];
			
			
			
			
		}

	}

	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
