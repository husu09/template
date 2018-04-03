package com.su.path;

public enum Direction {
	TOP("↑"), DOWN("↓"), LEFT("←"), RIGHT("→");
	private String ch;

	private Direction(String ch) {
		this.ch = ch;
	}

	public String getCh() {
		return ch;
	}
}
