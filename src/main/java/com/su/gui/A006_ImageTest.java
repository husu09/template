package com.su.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * 绘制图形
 * @author husu
 * 2017/10/18
 * */
public class A006_ImageTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new ImageFrame();
			frame.setTitle("ImageFrame");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
		});
	}
}
class ImageFrame extends JFrame {
	public ImageFrame() {
		add(new ImageComponent());
		pack();
	}
}
class ImageComponent extends JComponent {
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	
	private Image image;
	
	public ImageComponent() {
		image = new ImageIcon("src/main/resources/computer.png").getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (image == null) return;
		int imageWidth = image.getWidth(this);
		int imageHeight = image.getHeight(this);
		
		g.drawImage(image, 0, 0, null);
		
		for (int i = 0; i * imageWidth <= getWidth() ; i++) {
			for (int j = 0; j * imageHeight <= getHeight(); j++) {
				if (i + j > 0)
					g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j * imageHeight);
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	
}
