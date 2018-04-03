package com.su.path2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import com.su.path.Direction;

public class DrawComponent extends JComponent {

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		DrawData drawData = DrawContext.getDrawData();

		for (int node : drawData.getAll_nodes().keySet()) {
			int[] xys = drawData.decodePosition(node);
			// 绘制格子边框
			g.setColor(new Color(0xFF, 0xFF, 0xFF));
			g.drawRect(xys[0] * DrawSetting.GRID_SIZE + DrawSetting.LEFT_SPACE,
					xys[1] * DrawSetting.GRID_SIZE + DrawSetting.TOP_SPACE, DrawSetting.GRID_SIZE,
					DrawSetting.GRID_SIZE);
			// 绘制格子
			g.setColor(new Color(0xD3, 0xD3, 0xD3));

			if (drawData.getStartPosition() != -1 && node == drawData.getStartPosition()) { // 玩家位置
				g.setColor(new Color(0xDC, 0x14, 0x3C));
			} else if (drawData.getTargetPosition() != -1 && node == drawData.getTargetPosition()) { // 目标
				g.setColor(new Color(0x8A, 0x2B, 0xE2));
			} else if (drawData.getPaths().contains(node)) { // 路径
				g.setColor(new Color(0x46, 0x82, 0xB4));
			} else if (drawData.getBarriers().contains(node)) { // 障碍
				g.setColor(new Color(0x66, 0xCD, 0x00));
			} else if (drawData.getUnavailable().contains(node)) { // 不可用位置
				g.setColor(new Color(0x28, 0x28, 0x28));
			} else if (drawData.getVisited().contains(node)) { // 已搜索
				g.setColor(new Color(0x8B, 0x83, 0x78));
			} else { // 未搜索
				g.setColor(new Color(0xD3, 0xD3, 0xD3));
			}
			// 填充格子颜色
			g.fillRect(xys[0] * DrawSetting.GRID_SIZE + DrawSetting.LEFT_SPACE,
					xys[1] * DrawSetting.GRID_SIZE + DrawSetting.TOP_SPACE, DrawSetting.GRID_SIZE - 1,
					DrawSetting.GRID_SIZE - 1);

			// 绘制格子中的箭头
			if (drawData.getPointTos().containsKey(node)) {
				g.setColor(new Color(0x00, 0x00, 0x00));
				Direction direction = drawData.getPointTos().get(node);
				g2.drawString(direction.getCh(), xys[0] * DrawSetting.GRID_SIZE + DrawSetting.LEFT_SPACE + 10,
						xys[1] * DrawSetting.GRID_SIZE + DrawSetting.TOP_SPACE + 15);
			}
		}

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(DrawSetting.WINDOW_WIDTH, DrawSetting.WINDOW_HEIGHT);
	}

	public void draw(int intervalTime) {
		try {
			repaint();
			Thread.sleep(intervalTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
