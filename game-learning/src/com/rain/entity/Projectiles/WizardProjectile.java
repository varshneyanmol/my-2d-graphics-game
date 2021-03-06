package com.rain.entity.Projectiles;

import com.rain.entity.spawner.ParticleSpawner;
import com.rain.entity.spawner.Spawner;
import com.rain.graphics.Screen;
import com.rain.graphics.Sprite;

public class WizardProjectile extends Projectile {

	public static final int FIRE_RATE = 4; // think of this number as the time interval in between two projectiles. So, the higher the fire
											// rate, the slower player is going to fire them.

	public WizardProjectile(double x, double y, double angle) {
		super(x, y, angle);
		range = 150;
		sprite = Sprite.wizard_projectile;
		damage = 20;
		speed = 4;

		nx = Math.cos(angle) * speed;
		ny = Math.sin(angle) * speed;
	}

	public void update() {
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 9, 4, 4)) {
			level.addEntity(new ParticleSpawner((int) x, (int) y, 400, 50, level));
			remove();
		}
		move();
	}

	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range) remove();
	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x - 12, (int) y - 2, this);
	}

}
