package com.su.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * 动作、键盘事件
 * @author husu
 * 2017/10/19
 * */
public class B003_ActionTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new ActionFrame();
			frame.setVisible(true);
		});
	}
}
class ActionFrame extends JFrame {
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	public ActionFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		buttonPanel = new JPanel();
		
		Action yellowAction = new ColorAction("Yellow", new ImageIcon("src/main/resources/computer.png"), Color.YELLOW);
		Action blueAction = new ColorAction("Blue", new ImageIcon("src/main/resources/computer.png"), Color.BLUE);
		Action redAction = new ColorAction("Red", new ImageIcon("src/main/resources/computer.png"), Color.RED);
		
		buttonPanel.add(new JButton(yellowAction));
		buttonPanel.add(new JButton(blueAction));
		buttonPanel.add(new JButton(redAction));
		add(buttonPanel);
		
		// 绑定快捷键
		InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
		imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
		imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");
		
		ActionMap amap = buttonPanel.getActionMap();
		amap.put("panel.yellow", yellowAction);
		amap.put("panel.blue", blueAction);
		amap.put("panel.red", redAction);
	}
	
	public class ColorAction extends AbstractAction {
		
		public ColorAction(String name, Icon icon, Color c) {
			putValue(Action.NAME, name);
			putValue(Action.SMALL_ICON, icon);
			putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
			putValue("color", c);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Color c = (Color) getValue("color");
			buttonPanel.setBackground(c);
		}
		
	}
}
