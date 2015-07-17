package com.rain.entity.mob;

import com.rain.entity.Entity;
import com.rain.entity.Projectiles.Projectile;
import com.rain.entity.Projectiles.WizardProjectile;
import com.rain.entity.particle.Particle;
import com.rain.graphics.Screen;
import com.rain.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;

	public void move(int xDirection, int yDirection) { // these 'xa' and 'ya' variables will be passed three values i.e; 1,0,-1;
		if (xDirection > 0) dir = 1; // 1 is for east direction or moving right.
		if (xDirection < 0) dir = 3; // 3 is for west direction or moving left.
		if (yDirection > 0) dir = 2; // 2 is for south direction or moving down.
		if (yDirection < 0) dir = 0; // 0 is for north direction or moving up.

		if (!collision(0, yDirection)) {
			y += yDirection;
		}

		if (!collision(xDirection, 0)) {
			x += xDirection;
		}
	}

	public void update() {

	}

	protected void shoot(int x, int y, double angle) {
		Projectile p = new WizardProjectile(x, y, angle);
		level.addEntity(p);
	}

	public boolean collision(int xDirection, int yDirection) {
		boolean solid = false;
		for (int corner = 0; corner < 4; corner++) {
			int xt = ((x + xDirection) + corner % 2 * 16 - 10) / 16;
			int yt = ((y + yDirection) + corner / 2 * 12 + 3) / 16;
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}

	public void render(Screen screen) {

	}
}