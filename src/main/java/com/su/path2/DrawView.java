package com.su.path2;

import javax.swing.JFrame;

public class DrawView {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("DrawView");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(DrawContext.getComponent());
		frame.pack();
		frame.setVisible(true);

		DrawData drawData = DrawContext.getDrawData();
		
		drawData.getUnavailable().add(drawData.encodePosition(5, 2));
		drawData.getUnavailable().add(drawData.encodePosition(6, 2));
		drawData.getUnavailable().add(drawData.encodePosition(7, 2));
		drawData.getUnavailable().add(drawData.encodePosition(8, 2));
		drawData.getUnavailable().add(drawData.encodePosition(9, 2));
		drawData.getUnavailable().add(drawData.encodePosition(10, 2));
		drawData.getUnavailable().add(drawData.encodePosition(11, 2));
		drawData.getUnavailable().add(drawData.encodePosition(12, 2));
		drawData.getUnavailable().add(drawData.encodePosition(12, 3));
		drawData.getUnavailable().add(drawData.encodePosition(12, 4));
		drawData.getUnavailable().add(drawData.encodePosition(12, 5));
		drawData.getUnavailable().add(drawData.encodePosition(12, 6));
		drawData.getUnavailable().add(drawData.encodePosition(12, 7));
		drawData.getUnavailable().add(drawData.encodePosition(12, 8));
		drawData.getUnavailable().add(drawData.encodePosition(12, 9));
		drawData.getUnavailable().add(drawData.encodePosition(12, 10));
		drawData.getUnavailable().add(drawData.encodePosition(12, 11));
		drawData.getUnavailable().add(drawData.encodePosition(2, 12));
		drawData.getUnavailable().add(drawData.encodePosition(3, 12));
		drawData.getUnavailable().add(drawData.encodePosition(4, 12));
		drawData.getUnavailable().add(drawData.encodePosition(5, 12));
		drawData.getUnavailable().add(drawData.encodePosition(6, 12));
		drawData.getUnavailable().add(drawData.encodePosition(7, 12));
		drawData.getUnavailable().add(drawData.encodePosition(8, 12));
		drawData.getUnavailable().add(drawData.encodePosition(9, 12));
		drawData.getUnavailable().add(drawData.encodePosition(10, 12));
		drawData.getUnavailable().add(drawData.encodePosition(11, 12));
		drawData.getUnavailable().add(drawData.encodePosition(12, 12));
		
		drawData.generateGridGraph();
		
		//BFS001 bfs001 = new BFS001();
		//bfs001.doSearch(drawData.encodePosition(9, 4));
		
		//BFS002 bfs002 = new BFS002();
		//bfs002.doSearch(drawData.encodePosition(0, 12), drawData.encodePosition(14, 1));
		
		//Dijkstra dijkstra = new Dijkstra();
		//dijkstra.doSearch(drawData.encodePosition(0, 12), drawData.encodePosition(14, 1));
	
		Heuristic heuristic = new Heuristic();
		heuristic.doSearch(drawData.encodePosition(0, 12), drawData.encodePosition(14, 1));
		
		//AStar astar = new AStar();
		//astar.doSearch(drawData.encodePosition(0, 12), drawData.encodePosition(14, 1));
	}
}
