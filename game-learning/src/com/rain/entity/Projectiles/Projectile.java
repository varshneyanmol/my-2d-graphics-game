package com.rain.entity.Projectiles;

import com.rain.entity.Entity;
import com.rain.graphics.Screen;
import com.rain.graphics.Sprite;

public class Projectile extends Entity {

	protected double xOrigin, yOrigin;
	protected double speed, range, damage;
	protected Sprite sprite;
	protected double distance;
	protected double x, y;
	protected double angle;
	protected double nx, ny;

	public Projectile(double x, double y, double angle) {
		xOrigin = x;
		yOrigin = y;
		this.angle = angle;
		this.x = x;
		this.y = y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getSpriteSize() {
		return sprite.SIZE;
	}

	protected void move() {
	}

}
