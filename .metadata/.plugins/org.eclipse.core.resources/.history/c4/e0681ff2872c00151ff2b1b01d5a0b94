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
		this.zz = 2;
		this.life = life + random.nextInt(30) - 15;
		sprite = Sprite.particle;

		xDirection = random.nextGaussian();
		yDirection = random.nextGaussian();
	}

	public void update() {
		time++;
		if (time > life) remove();
		xx += xDirection;
		yy += yDirection;
		zDirection -= 0.1;
		zz += zDirection;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) (yy - zz), sprite, true);
	}
}
