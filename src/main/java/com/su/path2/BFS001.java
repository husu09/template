package com.su.path2;

import java.util.ArrayList;
import java.util.List;

/**
 * Breadth First Search 广度优先搜索
 */
public class BFS001 {

	// 要搜索的节点
	private List<Integer> frontier = new ArrayList<>();
	// 已搜索的节点
	private List<Integer> visited = new ArrayList<>();

	/**
	 * 搜索全图
	 */
	public void doSearch(int startPosition) {
		DrawData drawData = DrawContext.getDrawData();
		DrawComponent component = DrawContext.getComponent();
		drawData.setStartPosition(startPosition);
		frontier.add(startPosition);
		visited.add(startPosition);

		while (!frontier.isEmpty()) {
			// 处理游戏数据
			Integer current = frontier.remove(0);
			for (int next : drawData.getAll_nodes().get(current)) {
				if (!visited.contains(next)) {
					frontier.add(next);
					visited.add(next);
					drawData.getPaths().add(next);
				}
			}
			// 处理显示数据
			drawData.getPaths().remove(current);
			drawData.getVisited().add(current);
			// 重绘画面
			component.draw(50);
		}
	}

	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
	}

}
