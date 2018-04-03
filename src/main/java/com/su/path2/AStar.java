package com.su.path2;
/**
 * Dijkstra’s 广度优先搜索
 */

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AStar {

	Map<Integer, Integer> frontier = new LinkedHashMap<>();
	Map<Integer, Integer> cameFrom = new HashMap<>();
	Map<Integer, Integer> costSoFar = new HashMap<>();

	public void doSearch(int startPosition, int targetPosition) {
		DrawData drawData = DrawContext.getDrawData();
		DrawComponent component = DrawContext.getComponent();

		frontier.put(startPosition, 0);
		cameFrom.put(startPosition, null);
		costSoFar.put(startPosition, 0);

		drawData.setStartPosition(startPosition);
		drawData.setTargetPosition(targetPosition);

		while (!frontier.isEmpty()) {
			int current = frontier.keySet().iterator().next();
			int miniPriority = frontier.values().iterator().next();
			for (Entry<Integer, Integer> entry : frontier.entrySet()) {
				if (entry.getValue() < miniPriority) {
					current = entry.getKey();
				}
			}
			frontier.remove(current);

			if (current == targetPosition)
				break;
			for (int next : drawData.getAll_nodes().get(current)) {
				int newCost = costSoFar.get(current) + drawData.cost(current, next);

				if (!costSoFar.containsKey(next) || newCost < costSoFar.get(next)) {
					costSoFar.put(next, newCost);
					int priority = newCost + drawData.heuristic( targetPosition,next);
					frontier.put(next, priority);
					cameFrom.put(next, current);
					drawData.getVisited().add(next);
					component.draw(50);
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
