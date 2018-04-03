package com.su.path2;

import javax.swing.JComponent;

public class DrawContext {
	private static DrawData drawData = new DrawData();
	private static DrawComponent component = new DrawComponent();

	public static DrawData getDrawData() {
		return drawData;
	}

	public static DrawComponent getComponent() {
		return component;
	}

}
