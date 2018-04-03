package com.su.path2;
/**
 * Breadth First Search 广度优先搜索
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.su.path.Direction;

public class BFS002 {

	// 要搜索的节点
	List<Integer> frontier = new ArrayList<>();
	// 记录每个节点的父节点
	Map<Integer, Integer> cameFrom = new HashMap<>();

	public void doSearch(int startPosition, int targetPosition) {
		DrawData drawData = DrawContext.getDrawData();
		DrawComponent component = DrawContext.getComponent();

		drawData.setStartPosition(startPosition);
		drawData.setTargetPosition(targetPosition);

		frontier.add(startPosition);
		cameFrom.put(startPosition, null);

		while (!frontier.isEmpty()) {
			int current = frontier.remove(0);
			if (current == targetPosition)
				break;
			for (int next : drawData.getAll_nodes().get(current)) {
				if (!cameFrom.containsKey(next)) {
					frontier.add(next);
					cameFrom.put(next, current);

					// 判断箭头方向
					Direction direction = Direction.TOP;
					int[] xys = drawData.decodePosition(next);
					int nextX = xys[0];
					int nextY = xys[1];
					xys = drawData.decodePosition(current);
					int currentX = xys[0];
					int currentY = xys[1];
					if (nextY < currentY) {
						direction = Direction.DOWN;
					} else if (nextX < currentX) {
						direction = Direction.RIGHT;
					} else if (nextX > currentX) {
						direction = Direction.LEFT;
					}
					drawData.getPointTos().put(next, direction);
					drawData.getVisited().add(next);
					component.draw(20);
				}

			}
		}

		// 规划路径
		Integer current = targetPosition;
		while (current != startPosition) {
			drawData.getPaths().add(current);
			current = cameFrom.get(current);
			component.draw(20);
		}
		drawData.getPaths().add(startPosition);
		component.draw(20);
	}
}
