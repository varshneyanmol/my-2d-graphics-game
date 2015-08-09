package com.rain.entity.mob;

import com.rain.graphics.AnimatedSprite;
import com.rain.graphics.Screen;
import com.rain.graphics.Sprite;
import com.rain.graphics.SpriteSheet;

public class Dummy extends Mob {

	private int xDirection = 0;
	private int yDirection = 0;
	private int time = 0;

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	public Dummy(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}

	public void update() {
		time++;
		if (time % (random.nextInt(50) + 30) == 0) {
			xDirection = random.nextInt(3) - 1;
			yDirection = random.nextInt(3) - 1;
			if (random.nextInt(4) == 0) {
				xDirection = 0;
				yDirection = 0;
			}
		}

		if (walking) animSprite.update();
		else animSprite.setFrame(0);

		if (yDirection > 0) {
			animSprite = down;
			dir = Direction.DOWN;

		} else if (yDirection < 0) {
			animSprite = up;
			dir = Direction.UP;
		}

		if (xDirection > 0) {
			animSprite = right;
			dir = Direction.RIGHT;

		} else if (xDirection < 0) {
			animSprite = left;
			dir = Direction.LEFT;
		}

		if (xDirection != 0 || yDirection != 0) {
			walking = true;
			move(xDirection, yDirection);
		} else {
			walking = false;
		}
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) x, (int) y, sprite, 0);
	}

}
