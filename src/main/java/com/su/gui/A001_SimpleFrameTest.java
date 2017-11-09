package com.su.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
/**
 * 简单窗口
 * @author husu
 * 2017/10/16
 * */
public class A001_SimpleFrameTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SimpleFrame frame = new SimpleFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationByPlatform(true);
			frame.setVisible(true);
			//frame.setUndecorated(true);
		}); 
	}
}
class SimpleFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;
	
	public SimpleFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}