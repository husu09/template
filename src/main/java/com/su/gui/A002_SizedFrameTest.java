package com.su.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


/**
 * 窗口
 * @author husu
 * 2017/10/16
 * */
public class A002_SizedFrameTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new SizedFrame();
			frame.setTitle("SizedFrame");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// 隐藏标题栏
			frame.setUndecorated(true);
			frame.setVisible(true);
		});
	}
}
class SizedFrame extends JFrame {
	public SizedFrame() {
		// 获取屏幕分辨率
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		// 设置窗口大小并且由系统决定窗口位置
		setSize(screenWidth / 2, screenHeight / 2);
		setLocationByPlatform(true);
		
		// 设置窗口图标
		Image img = new ImageIcon("src/main/resources/computer.png").getImage();
		setIconImage(img);
	}
}
