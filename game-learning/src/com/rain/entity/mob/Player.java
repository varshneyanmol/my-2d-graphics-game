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

	private AnimatedSprite animSprite = down;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_up;
		animSprite = down;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		sprite = Sprite.player_up;
		this.input = input;
		fireRate = WizardProjectile.FIRE_RATE;
	}

	public void update() {
		// List<Entity> en = level.getEntitiesInRadius(this, 50);
		// System.out.println(en.size());
		// for (Entity e : en) {
		// System.out.println(e);
		// }
		if (walking) animSprite.update();
		else animSprite.setFrame(0);

		if (fireRate > 0) fireRate--;
		double xDirection = 0.0, yDirection = 0.0;
		double speed = 1.5;

		// if (animation < 8000) animation++;
		// else animation = 0;

		if (input.up) {
			animSprite = up;
			yDirection -= speed;
		} else if (input.down) {
			animSprite = down;
			yDirection += speed;
		}
		if (input.left) {
			animSprite = left;
			xDirection -= speed;
		} else if (input.right) {
			animSprite = right;
			xDirection += speed;
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

		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int) (y - 16), sprite, flip);
	}
}
