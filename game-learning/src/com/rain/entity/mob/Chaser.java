package com.rain.entity.mob;

import java.util.List;

import com.rain.graphics.AnimatedSprite;
import com.rain.graphics.Screen;
import com.rain.graphics.Sprite;
import com.rain.graphics.SpriteSheet;

public class Chaser extends Mob {

	private int xDirection = 0;
	private int yDirection = 0;

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	public Chaser(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}

	private void move() {
		xDirection = 0;
		yDirection = 0;

		List<Player> playersInRadius = level.getPlayersInRadius(this, 50);
		if (playersInRadius.size() > 0) {
			Player player = playersInRadius.get(0);

			if (x < player.getX()) xDirection++;
			if (x > player.getX()) xDirection--;
			if (y < player.getY()) yDirection++;
			if (y > player.getY()) yDirection--;
		}

		if (xDirection != 0 || yDirection != 0) {
			move(xDirection, yDirection);
			walking = true;
		} else {
			walking = false;
		}
	}

	public void update() {
		move();
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

	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int) (y - 16), this);
	}

}
