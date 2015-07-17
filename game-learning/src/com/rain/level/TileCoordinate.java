package com.rain.level;

public class TileCoordinate {

	private int x, y;
	private final int TILE_SIZE = 16;

	public TileCoordinate(int x, int y) {
		this.x = x * TILE_SIZE;
		this.y = y * TILE_SIZE;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public int[] xy() {
		int[] xy = new int[2];
		xy[0] = x;
		xy[1] = y;
		return xy;
	}
}