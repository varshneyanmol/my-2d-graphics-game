package com.rain.entity.mob;

import com.rain.Game;
import com.rain.entity.Projectiles.WizardProjectile;
import com.rain.graphics.AnimatedSprite;
import com.rain.graphics.Screen;
import com.rain.graphics.Sprite;
import com.rain.graphics.SpriteSheet;
import com.rain.input.Keyboard;
import com.rain.input.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int animation = 0;
	private boolean walking = false;
	private int fireRate = 0;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);

	private AnimatedSprite anim = down;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_up;
		anim = down;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		sprite = Sprite.player_up;
		this.input = input;
		fireRate = WizardProjectile.FIRE_RATE;
	}

	public void update() {
		if (walking) anim.update();
		else anim.setFrame(0);

		if (fireRate > 0) fireRate--;
		int xDirection = 0, yDirection = 0;

		if (animation < 8000) animation++;
		else animation = 0;

		if (input.up) {
			anim = up;
			yDirection--;
		} else if (input.down) {
			anim = down;
			yDirection++;
		}
		if (input.left) {
			anim = left;
			xDirection--;
		} else if (input.right) {
			anim = right;
			xDirection++;
		}

		if (xDirection != 0 || yDirection != 0) {
			walking = true;
			move(xDirection, yDirection);
		} else {
			walking = false;
		}

		// clear();
		updateShooting();

	}

	/*
	 * private void clear() { for (int i = 0; i < level.getProjectiles().size(); i++) { Projectile p = level.getProjectiles().get(i); if
	 * (p.isRemoved()) level.getProjectiles().remove(i); } }
	 */

	private void updateShooting() {
		if (Mouse.getButton() == 1 && fireRate == 0) {
			double dx = Mouse.getX() - (Game.getWindowWidth() / 2);
			double dy = Mouse.getY() - (Game.getWindowHeight() / 2);
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE;

		}
	}

	public void render(Screen screen) {
		int flip = 0;
		// if (dir == 0) {
		// sprite = Sprite.player_up;
		// if (walking) {
		// if (animation % 20 > 10) {
		// sprite = Sprite.player_up_1;
		// } else sprite = Sprite.player_up_2;
		// }
		// }
		//
		// if (dir == 2) {
		// sprite = Sprite.player_down;
		// if (walking) {
		// if (animation % 20 > 10) {
		// sprite = Sprite.player_down_1;
		// } else sprite = Sprite.player_down_2;
		// }
		// }
		//
		// if (dir == 1) {
		// sprite = Sprite.player_side;
		// if (walking) {
		// if (animation % 20 > 10) {
		// sprite = Sprite.player_side_1;
		// } else sprite = Sprite.player_side_2;
		// }
		// }
		//
		// if (dir == 3) {
		// flip = 1;
		// sprite = Sprite.player_side;
		// if (walking) {
		// if (animation % 20 > 10) {
		// sprite = Sprite.player_side_1;
		// } else sprite = Sprite.player_side_2;
		// }
		// }

		sprite = anim.getSprite();
		screen.renderPlayer(x - 16, y - 16, sprite, flip);
	}
}
