package com.rain.entity.mob;

import com.rain.Game;
import com.rain.graphics.Screen;
import com.rain.graphics.Sprite;
import com.rain.input.Keyboard;
import com.rain.input.Mouse;
import com.rain.entity.Projectiles.Projectile;
import com.rain.entity.Projectiles.WizardProjectile;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int animation = 0;
	private boolean walking = false;
	private int fireRate = 0;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_up;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		sprite = Sprite.player_up;
		this.input = input;
		fireRate = WizardProjectile.FIRE_RATE;
	}

	public void update() {
		if (fireRate > 0) fireRate--;
		int xDirection = 0, yDirection = 0;

		if (animation < 8000)
			animation++;
		else
			animation = 0;

		if (input.up) yDirection--;
		if (input.down) yDirection++;
		if (input.left) xDirection--;
		if (input.right) xDirection++;

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
		if (dir == 0) {
			sprite = Sprite.player_up;
			if (walking) {
				if (animation % 20 > 10) {
					sprite = Sprite.player_up_1;
				} else
					sprite = Sprite.player_up_2;
			}
		}

		if (dir == 2) {
			sprite = Sprite.player_down;
			if (walking) {
				if (animation % 20 > 10) {
					sprite = Sprite.player_down_1;
				} else
					sprite = Sprite.player_down_2;
			}
		}

		if (dir == 1) {
			sprite = Sprite.player_side;
			if (walking) {
				if (animation % 20 > 10) {
					sprite = Sprite.player_side_1;
				} else
					sprite = Sprite.player_side_2;
			}
		}

		if (dir == 3) {
			flip = 1;
			sprite = Sprite.player_side;
			if (walking) {
				if (animation % 20 > 10) {
					sprite = Sprite.player_side_1;
				} else
					sprite = Sprite.player_side_2;
			}
		}

		screen.renderPlayer(x - 16, y - 16, sprite, flip);
	}
}