package com.rain.entity.mob;

import com.rain.entity.Entity;
import com.rain.entity.Projectiles.Projectile;
import com.rain.entity.Projectiles.WizardProjectile;
import com.rain.entity.particle.Particle;
import com.rain.graphics.Screen;
import com.rain.graphics.Sprite;

public abstract class Mob extends Entity {

	protected boolean moving = false;
	protected boolean walking = false;

	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	protected Direction dir;

	public void move(double xDirection, double yDirection) {
		if (xDirection != 0 && yDirection != 0) {
			move(xDirection, 0);
			move(0, yDirection);
			return;
		}

		if (xDirection > 0) dir = Direction.UP;
		if (xDirection < 0) dir = Direction.LEFT;
		if (yDirection > 0) dir = Direction.DOWN;
		if (yDirection < 0) dir = Direction.UP;

		while (xDirection != 0) {
			if (Math.abs(xDirection) > 1) {
				if (!collision(abs(xDirection), yDirection)) {
					this.x += abs(xDirection);
				}
				xDirection -= abs(xDirection);
			} else {
				if (!collision(abs(xDirection), yDirection)) {
					this.x += xDirection;
				}
				xDirection = 0;
			}
		}

		while (yDirection != 0) {
			if (Math.abs(yDirection) > 1) {
				if (!collision(xDirection, abs(yDirection))) {
					this.y += abs(yDirection);
				}
				yDirection -= abs(yDirection);
			} else {
				if (!collision(xDirection, abs(yDirection))) {
					this.y += yDirection;
				}
				yDirection = 0;
			}
		}

	}

	public int abs(double value) {
		if (value < 0) return -1;
		return 1;
	}

	public abstract void update();

	public abstract void render(Screen screen);

	protected void shoot(double x, double y, double angle) {
		Projectile p = new WizardProjectile(x, y, angle);
		level.addEntity(p);
	}

	public boolean collision(double xDirection, double yDirection) {
		boolean solid = false;
		for (int corner = 0; corner < 4; corner++) {
			double xt = ((x + xDirection) - corner % 2 * 16) / 16;
			double yt = ((y + yDirection) - corner / 2 * 16) / 16;

			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (corner % 2 == 0) ix = (int) Math.floor(xt);
			if (corner / 2 == 0) iy = (int) Math.floor(yt);

			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}

}
