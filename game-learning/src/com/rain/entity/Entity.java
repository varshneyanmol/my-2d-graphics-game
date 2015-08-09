package com.rain.entity;

import java.util.Random;

import com.rain.graphics.Screen;
import com.rain.graphics.Sprite;
import com.rain.level.Level;

public abstract class Entity {

	protected Sprite sprite;
	protected double x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();

	public void update() {
	}

	public void render(Screen screen) {
		if (sprite != null) screen.renderSprite((int) x, (int) y, sprite, true);
	}

	public void remove() {
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void init(Level level) {
		this.level = level;
	}
}
