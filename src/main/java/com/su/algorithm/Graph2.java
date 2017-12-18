package com.su.algorithm;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
/**
 * 加权图：狄克斯特拉算法，找出最快路径
 * 狄克斯特拉算法只适用于有向无环图（directed acyclic graph， DAG）。
 * 如果有负权边，就不能使用狄克斯特拉算法。
 * */
public class Graph2 {
	private Map<String, Map<String, Integer>> graph = new HashMap<>();
	private Map<String, Integer> costs = new HashMap<>();
	private Map<String, String> parents = new HashMap<>();
	private List<String> processed = new ArrayList<>();
	{
		// 图
		Map<String, Integer> temp = new HashMap<>();
		temp.put("a", 6);
		temp.put("b", 2);
		graph.put("start", temp);

		temp = new HashMap<>();
		temp.put("fin", 1);
		graph.put("a", temp);

		temp = new HashMap<>();
		temp.put("a", 3);
		temp.put("fin", 5);
		graph.put("b", temp);

		temp = new HashMap<>();
		graph.put("fin", temp);

		// 最短路径
		costs.put("a", 6);
		costs.put("b", 2);
		costs.put("fin", Integer.MAX_VALUE);

		// 父节点
		parents.put("a", "start");
		parents.put("b", "start");
		parents.put("fin", null);

	}
	
	@Test
	public void doSomething() {
		while (true) {
			String node = findLowestCostNode(costs);
			if (node == null)
				break;
			// 开销
			int cost = costs.get(node);
			// 邻居
			for (Entry<String, Integer> e : graph.get(node).entrySet()) {
				int newCost = cost + e.getValue();
				if (newCost < costs.get(e.getKey())) {
					costs.put(e.getKey(), newCost);
					parents.put(e.getKey(), node);
				}
			}
			processed.add(node);
		}
		System.out.println(costs);
		System.out.println(parents);
	}

	/**
	 * 在未处理的节点中找出开销最小的节点
	 */
	public String findLowestCostNode(Map<String, Integer> costs) {
		int cost = Integer.MAX_VALUE;
		String node = null;
		for (Entry<String, Integer> e : costs.entrySet()) {
			if (processed.contains(e.getKey()))
				continue;
			if (e.getValue() < cost) {
				cost = e.getValue();
				node = e.getKey();
			}
		}
		return node;
	}
}
