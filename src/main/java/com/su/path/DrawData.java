package com.su.path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

public class DrawData {
	private static final DrawData drawData = new DrawData();

	private DrawData() {

	}

	public static DrawData getInstance() {
		return drawData;
	}

	private JComponent component;
	// 显示参数
	private int defaultWidth = 550;
	private int defaultHeight = 400;
	private int rectSide = 25;
	private int topSpace = 20;
	private int leftSpace = 20;

	// 地图大小
	private int mapWidth = 20;
	private int mapHeight = 10;

	private int startPosition = -1;
	private int targetPosition = -1;
	private List<Integer> unmovables = new ArrayList<>();
	private List<Integer> paths = new ArrayList<>();
	private List<Integer> visiteds = new ArrayList<>();
	private Map<Integer, Direction> pointTos = new HashMap<>();

	public int[] decodePosition(int position) {
		int[] xy = new int[2];
		xy[0] = position / mapWidth;
		xy[1] = position % mapWidth;
		return xy;
	}

	public int encodePosition(int x, int y) {
		return x * mapWidth + y;
	}

	public static boolean isContains(int[] arr, int target) {
		if (arr == null) {
			return false;
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == target)
				return true;
		}
		return false;
	}

	/**
	 * 获取一个节点上右下左的子节点
	 */
	public int[] getChildNode(int position) {
		int[] xys = decodePosition(position);
		int x = xys[0];
		int y = xys[1];

		List<Integer> list = new ArrayList<>(8);
		if (y - 1 >= 0) {
			if (!unmovables.contains(encodePosition(x, y - 1)))
				list.add(encodePosition(x, y - 1));
		}
		if (x - 1 >= 0) {
			if (!unmovables.contains(encodePosition(x - 1, y)))
				list.add(encodePosition(x - 1, y));
		}
		if (y + 1 < mapHeight) {
			if (!unmovables.contains(encodePosition(x, y + 1)))
				list.add(encodePosition(x, y + 1));

		}
		if (x + 1 < mapWidth) {
			if (!unmovables.contains(encodePosition(x + 1, y)))
				list.add(encodePosition(x + 1, y));

		}
		
		
		

		int[] childNodes = new int[list.size()];
		for (int i = 0; i < childNodes.length; i++) {
			childNodes[i] = list.get(i);
		}

		return childNodes;
	}

	public static int[] list2Array(List<Integer> list) {
		if (list == null)
			return null;
		int[] arr = new int[list.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}

	public void draw(int intervalTime) {
		try {
			component.repaint();
			Thread.sleep(intervalTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public JComponent getComponent() {
		return component;
	}

	public void setComponent(JComponent component) {
		this.component = component;
	}

	public int getDefaultWidth() {
		return defaultWidth;
	}

	public void setDefaultWidth(int defaultWidth) {
		this.defaultWidth = defaultWidth;
	}

	public int getDefaultHeight() {
		return defaultHeight;
	}

	public void setDefaultHeight(int defaultHeight) {
		this.defaultHeight = defaultHeight;
	}

	public int getRectSide() {
		return rectSide;
	}

	public void setRectSide(int rectSide) {
		this.rectSide = rectSide;
	}

	public int getTopSpace() {
		return topSpace;
	}

	public void setTopSpace(int topSpace) {
		this.topSpace = topSpace;
	}

	public int getLeftSpace() {
		return leftSpace;
	}

	public void setLeftSpace(int leftSpace) {
		this.leftSpace = leftSpace;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
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

	public List<Integer> getUnmovables() {
		return unmovables;
	}

	public void setUnmovables(List<Integer> unmovables) {
		this.unmovables = unmovables;
	}

	public List<Integer> getPaths() {
		return paths;
	}

	public void setPaths(List<Integer> paths) {
		this.paths = paths;
	}

	public List<Integer> getVisiteds() {
		return visiteds;
	}

	public void setVisiteds(List<Integer> visiteds) {
		this.visiteds = visiteds;
	}

	public Map<Integer, Direction> getPointTos() {
		return pointTos;
	}

	public void setPointTos(Map<Integer, Direction> pointTos) {
		this.pointTos = pointTos;
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		for (int i = 0; i < list.size(); i++) {
			list.remove(i);
		}
		list.add(0);
		list.add(1);
		list.add(2);
		// 使用 foreach 循环时不能删除list中的元素
		for (int i : list) {
			list.remove(i);
		}

	}
}
