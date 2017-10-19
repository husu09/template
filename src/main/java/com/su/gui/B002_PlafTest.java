package com.su.gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 切换观感
 * @author husu
 * 2017/10/18
 * */
public class B002_PlafTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new PlafFrame();
			frame.setTitle("PlafFrame");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
class PlafFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private JPanel buttonPanel;
	
	public PlafFrame() {
		buttonPanel = new JPanel();
		// 获取所有观感
		UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		for (UIManager.LookAndFeelInfo info : infos) 
			makeButton(info.getName(), info.getClassName());
		
		add(buttonPanel);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		//pack();
	}
	
	private void makeButton(String name, String className) {
		
		JButton button = new JButton(name);
		buttonPanel.add(button);
		
		button.addActionListener(event -> {
			try {
				// 切换观感
				UIManager.setLookAndFeel(className);
				SwingUtilities.updateComponentTreeUI(this);
				//pack();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
	}
}
