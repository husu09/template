package com.su.path;

import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class DrawView {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new JFrame();
			frame.setTitle("DrawView");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JComponent component = new DrawComponent();
			frame.add(component);
			frame.pack();

			frame.setVisible(true);

			DrawData data = DrawData.getInstance();
			data.setComponent(component);
			
			// 不可用区域
			data.getUnmovables().add(data.encodePosition(3, 3));
			data.getUnmovables().add(data.encodePosition(3, 4));
			data.getUnmovables().add(data.encodePosition(3, 5));
			data.getUnmovables().add(data.encodePosition(3, 6));
			data.getUnmovables().add(data.encodePosition(3, 7));
			data.getUnmovables().add(data.encodePosition(3, 8));
			data.getUnmovables().add(data.encodePosition(3, 9));
			
			data.getUnmovables().add(data.encodePosition(10, 5));
			data.getUnmovables().add(data.encodePosition(10, 6));
			data.getUnmovables().add(data.encodePosition(10, 7));
			data.getUnmovables().add(data.encodePosition(10, 8));
			data.getUnmovables().add(data.encodePosition(10, 9));
			
			data.getUnmovables().add(data.encodePosition(14, 0));
			data.getUnmovables().add(data.encodePosition(14, 1));
			data.getUnmovables().add(data.encodePosition(14, 2));
			data.getUnmovables().add(data.encodePosition(14, 3));
			data.getUnmovables().add(data.encodePosition(14, 4));
			data.getUnmovables().add(data.encodePosition(14, 5));
			data.getUnmovables().add(data.encodePosition(14, 6));
			data.getUnmovables().add(data.encodePosition(15, 6));
			data.getUnmovables().add(data.encodePosition(16, 6));
			data.getUnmovables().add(data.encodePosition(17, 6));
			
			// 广度优先搜索
			BFS001 bfs001 = new BFS001();
			BFS002 bfs002 = new BFS002();

			new Thread(new Runnable() {

				@Override
				public void run() {
					//bfs001.doSearch(data.encodePosition(7, 7));
					//bfs002.doSearch(data.encodePosition(7, 7), data.encodePosition(17, 3));
					bfs002.doSearch(data.encodePosition(7, 7), data.encodePosition(13, 0));
				}
			}).start();
		});
	}
}
