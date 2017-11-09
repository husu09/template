package com.su.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * 在组件中显示文字
 * @author husu
 * 2017/10/16
 * */
public class A003_NotHelloWorld {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new NotHelloWorldFrame();
			frame.setTitle("NotHelloWorld");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
// 窗口
class NotHelloWorldFrame extends JFrame {
	public NotHelloWorldFrame() {
		add(new NotHelloWorldComponent());
		pack();
	}
}
// 组件，在上面绘制文字
class NotHelloWorldComponent extends JComponent {
	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;
	
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	// 绘制组件
	@Override
	protected void paintComponent(Graphics g) {
		g.drawString("Not a Hello, World program", MESSAGE_X, MESSAGE_Y);
	}
	
	// 设置组件大小
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
}