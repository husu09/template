package com.su.path2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.su.path.Direction;

public class DrawData {
	private int width = 15;
	private int height = 15;

	private int startPosition = -1;
	private int targetPosition = -1;
	private List<Integer> visited = new ArrayList<>();
	private List<Integer> paths = new ArrayList<>();
	private List<Integer> unavailable = new ArrayList<>();
	private Map<Integer, Direction> pointTos = new HashMap<>();
	private List<Integer> barriers = new ArrayList<>();

	private Map<Integer, List<Integer>> all_nodes = new HashMap<>();
	private int[] dirs = new int[4];

	public DrawData() {
		dirs[0] = encodePosition(1, 0);
		dirs[1] = encodePosition(0, -1);
		dirs[2] = encodePosition(-1, 0);
		dirs[3] = encodePosition(0, 1);
	}

	public int[] decodePosition(int position) {
		int[] xy = new int[2];
		xy[0] = position / width;
		xy[1] = position % width;
		return xy;
	}

	public int encodePosition(int x, int y) {
		return x * width + y;
	}

	/**
	 * 生成网格图
	 */
	public void generateGridGraph() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				List<Integer> neighbors = new ArrayList<>();
				for (int dir : dirs) {
					if (unavailable.contains(encodePosition(x, y)))
						break;
					int[] arr = decodePosition(dir);
					int nextX = x + arr[0];
					int nextY = y + arr[1];

					if (nextX < 0 || nextX >= width || nextY < 0 || nextY >= height
							|| unavailable.contains(encodePosition(nextX, nextY)))
						continue;
					int neighbor = encodePosition(nextX, nextY);
					neighbors.add(neighbor);
				}
				if ((x + y) % 2 == 0) {
					Collections.reverse(neighbors);
				}
				all_nodes.put(encodePosition(x, y), neighbors);
			}
		}
	}

	public int cost(int current, int next) {
		return barriers.contains(next) ? 5 : 1;
	}

	public int heuristic(int a, int b) {

		return Math.abs(decodePosition(a)[0] - decodePosition(b)[0])
				+ Math.abs(decodePosition(a)[1] - decodePosition(b)[1]);
	}

	public static void main(String[] args) {
		DrawData drawData = new DrawData();

		for (Entry<Integer, List<Integer>> entry : drawData.getAll_nodes().entrySet()) {
			int[] xys = drawData.decodePosition(entry.getKey());
			System.out.printf("%02d,%02d\t", xys[0], xys[1]);
			for (int position : entry.getValue()) {
				xys = drawData.decodePosition(position);
				System.out.printf("%02d,%02d\t", xys[0], xys[1]);
			}
			System.out.println();
		}

	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Map<Integer, List<Integer>> getAll_nodes() {
		return all_nodes;
	}

	public void setAll_nodes(Map<Integer, List<Integer>> all_nodes) {
		this.all_nodes = all_nodes;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public int getTargetPosition() {
		return targetPosition;
	}

	public void setTargetPosition(int targetPosition) {
		this.targetPosition = targetPosition;
	}

	public List<Integer> getVisited() {
		return visited;
	}

	public void setVisited(List<Integer> visited) {
		this.visited = visited;
	}

	public List<Integer> getPaths() {
		return paths;
	}

	public void setPaths(List<Integer> paths) {
		this.paths = paths;
	}

	public List<Integer> getUnavailable() {
		return unavailable;
	}

	public void setUnavailable(List<Integer> unavailable) {
		this.unavailable = unavailable;
	}

	public Map<Integer, Direction> getPointTos() {
		return pointTos;
	}

	public void setPointTos(Map<Integer, Direction> pointTos) {
		this.pointTos = pointTos;
	}

	public List<Integer> getBarriers() {
		return barriers;
	}

	public void setBarriers(List<Integer> barriers) {
		this.barriers = barriers;
	}

}
