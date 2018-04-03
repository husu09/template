package com.su.path;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class DrawComponent extends JComponent {
	private DrawData data = DrawData.getInstance();

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (int y = 0; y < DrawData.getInstance().getMapHeight(); y++) {
			for (int x = 0; x < data.getMapWidth(); x++) {

				g.setColor(new Color(0xFF, 0xFF, 0xFF));
				int rectX = x * data.getRectSide() + data.getLeftSpace();
				int rectY = y * data.getRectSide() + data.getTopSpace();
				// 绘制格子边框
				g2.drawRect(rectX, rectY, data.getRectSide(), data.getRectSide());
				int position = data.encodePosition(x, y);
				
				// 填充格子的颜色
				if (data.getStartPosition() != -1 && position == data.getStartPosition()) {
					// 玩家位置
					g.setColor(new Color(0xDC, 0x14, 0x3C));

				} else if (data.getTargetPosition() != -1 && position == data.getTargetPosition()) {
					// 目标
					g.setColor(new Color(0x8A, 0x2B, 0xE2));
				} else if (data.getUnmovables() != null && data.getUnmovables().contains(position)) {
					// 不可用位置
					g.setColor(new Color(0x28, 0x28, 0x28));
				} else if (data.getPaths() != null && data.getPaths().contains(position)) {
					// 路径
					g.setColor(new Color(0x46, 0x82, 0xB4));
				} else if (data.getVisiteds() != null && data.getVisiteds().contains(position)) {
					// 已搜索
					g.setColor(new Color(0x8B, 0x83, 0x78));
				} else {
					g.setColor(new Color(0xD3, 0xD3, 0xD3));
				}
				g2.fillRect(rectX, rectY, data.getRectSide() - 1, data.getRectSide() - 1);
				
				// 绘制格子中的箭头
				if (data.getPointTos().containsKey(position)) {
					g.setColor(new Color(0x00, 0x00, 0x00));
					Direction direction = data.getPointTos().get(position);
					g2.drawString(direction.getCh(), rectX + 10, rectY + 15);
				} 
			}
		}

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(data.getDefaultWidth(), data.getDefaultHeight());
	}

}