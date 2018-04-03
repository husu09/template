package com.su.path;

import java.util.ArrayList;
import java.util.List;

/**
 * Breadth First Search 广度优先搜索
 */
public class BFS001 {

	private DrawData data = DrawData.getInstance();

	// 要搜索的节点
	private List<Integer> frontier = new ArrayList<>();
	// 已搜索的节点
	private List<Integer> visited = new ArrayList<>();

	/**
	 * 搜索全图
	 */
	public void doSearch(int startPosition) {
		data.setStartPosition(startPosition);
		frontier.add(startPosition);
		visited.add(startPosition);

		while (!frontier.isEmpty()) {
			// 处理游戏数据
			Integer current = frontier.remove(0);
			if (current != startPosition)
				data.setTargetPosition(current);
			for (int next : data.getChildNode(current)) {
				if (!visited.contains(next)) {
					frontier.add(next);
					visited.add(next);
					// 处理显示数据
					data.getVisiteds().add(next);
					// 重绘画面
					data.draw(20);
				}
			}

		}
		data.setTargetPosition(-1);
		data.draw(20);
	}

	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		System.out.println(list.remove(0));
		System.out.println(list.remove(0));
	}

}
