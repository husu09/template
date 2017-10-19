package com.su.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 事件处理基础
 * @author husu
 * 2017/10/18
 * */
public class B001_ButtonTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new ButtonFrame();
			frame.setTitle("ButtonFrame");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
class ButtonFrame extends JFrame {
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	public ButtonFrame() {
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		// 创建按钮
		JButton yellowButton = new JButton("Yellow");
		JButton blueButton = new JButton("Blue");
		JButton redButton = new JButton("Red");
		// 添加按钮到面板
		buttonPanel = new JPanel();
		buttonPanel.add(yellowButton);
		buttonPanel.add(blueButton);
		buttonPanel.add(redButton);
		add(buttonPanel);
		
		// 创建监听器
		ColorAction yellowAction = new ColorAction(Color.YELLOW);
		ColorAction blueAction = new ColorAction(Color.BLUE);
		ColorAction redAction = new ColorAction(Color.RED);
		// 监听器注册
		yellowButton.addActionListener(yellowAction);
		blueButton.addActionListener(blueAction);
		redButton.addActionListener(redAction);
		
	}
	private class ColorAction implements ActionListener {
		private Color backgroundColor;
		
		public ColorAction(Color c) {
			backgroundColor = c;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			buttonPanel.setBackground(backgroundColor);
		}
		
	}
}
