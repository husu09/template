package com.su.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.junit.Test;
/**
 * 单向图：广度优先搜索，找出最短路径
 * */
public class Graph {
	private Map<String, String[]> graph = new HashMap<>();

	{
		graph.put("you", new String[] { "alice", "bob", "claire" });
		graph.put("bob", new String[] { "anuj", "peggy" });
		graph.put("alice", new String[] { "peggy" });
		graph.put("claire", new String[] { "thom", "jonny" });
		graph.put("anuj", new String[] {});
		graph.put("peggy", new String[] {});
		graph.put("thom", new String[] {});
		graph.put("jonny", new String[] {});
	}
	
	private boolean search() {
		Queue<String> searchQueue = new LinkedList<>();
		List<String> searched = new ArrayList<>(graph.size());
		for (String s : graph.get("you")) {
			searchQueue.offer(s);
		}
		while (searchQueue.size() > 0) {
			String person = searchQueue.poll();
			if (person.endsWith("m")) {
				System.out.println(person + " is a mango seller");
				return true;
			} else {
				for (String s : graph.get(person)) {
					searchQueue.offer(s);
				}
			}
			searched.add(person);
		}
		return false;
	}
	
	@Test
	public void t1() {
		search();
	}
}
