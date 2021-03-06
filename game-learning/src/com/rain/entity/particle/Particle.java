package com.rain.entity.particle;

import com.rain.entity.Entity;
import com.rain.graphics.Screen;
import com.rain.graphics.Sprite;

public class Particle extends Entity {

	private int life;
	private Sprite sprite;
	private int time = 0;

	protected double xx, yy, zz;
	protected double xDirection, yDirection, zDirection;

	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + random.nextInt(20) - 10;
		sprite = Sprite.particle;

		this.zz = random.nextFloat() + 2.0;

		xDirection = random.nextGaussian();
		yDirection = random.nextGaussian();
	}

	public void update() {
		time++;
		if (time > 8000) time = 0;
		if (time > life) remove();
		zDirection -= 0.1;

		if (zz < 0) {
			zz = 0;
			zDirection *= -0.55;
			// xDirection *= 0.4;
			yDirection *= 0.4;
		}

		move((xx + xDirection), (yy + yDirection) + (zz + zDirection));

	}

	private void move(double x, double y) {

		if (collision(x, y)) {
			this.xDirection *= -0.6;
			this.yDirection *= -0.6;
			this.zDirection *= -0.6;
		}

		xx += xDirection;
		yy += yDirection;
		zz += zDirection;
	}

	public boolean collision(double x, double y) {
		boolean solid = false;
		for (int corner = 0; corner < 4; corner++) {
			double xt = (x - corner % 2 * 16) / 16;
			double yt = (y - corner / 2 * 16) / 16;

			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (corner % 2 == 0) ix = (int) Math.floor(xt);
			if (corner / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;

		}
		return solid;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy - (int) zz, sprite, true);
	}
}
