package com.su.path;
/**
 * Breadth First Search 广度优先搜索
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BFS002 {

	// 要搜索的节点
	List<Integer> frontier = new ArrayList<>();
	// 记录每个节点的父节点
	Map<Integer, Integer> cameFrom = new HashMap<>();

	private DrawData data = DrawData.getInstance();

	public void doSearch(int startPosition, int targetPosition) {
		data.setStartPosition(startPosition);
		data.setTargetPosition(targetPosition);
		
		frontier.add(startPosition);
		cameFrom.put(startPosition, null);

		while (!frontier.isEmpty()) {
			int current = frontier.remove(0);
			for (int next : data.getChildNode(current)) {
				if (!cameFrom.containsKey(next)) {
					frontier.add(next);
					cameFrom.put(next, current);
					
					// 判断箭头方向
					Direction direction = Direction.TOP;
					int[] xys = data.decodePosition(next);
					int nextX = xys[0];
					int nextY = xys[1];
					xys = data.decodePosition(current);
					int currentX = xys[0];
					int currentY = xys[1];
					if (nextY < currentY) {
						direction = Direction.DOWN;
					} else if (nextX < currentX) {
						direction = Direction.RIGHT;
					} else if (nextX > currentX) {
						direction = Direction.LEFT;
					}
					data.getPointTos().put(next, direction);
					data.getVisiteds().add(next);
					data.draw(20);
				}

			}
		}

		// 规划路径
		Integer current = targetPosition;
		while (current != startPosition) {
			data.getPaths().add(current);
			current = cameFrom.get(current);
			data.draw(20);
		}
		data.getPaths().add(startPosition);
		data.draw(20);
	}
}
