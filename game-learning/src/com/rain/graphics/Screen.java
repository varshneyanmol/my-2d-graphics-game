package com.rain.graphics;

import java.util.Random;

import com.rain.entity.Projectiles.Projectile;
import com.rain.entity.mob.Chaser;
import com.rain.entity.mob.Mob;
import com.rain.level.tile.Tile;

public class Screen {

	public int width;
	public int height;
	public int[] pixels;
	public int xOffset, yOffset;
	private final int MAP_SIZE = 64;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];

		for (int i = 0; i < (MAP_SIZE * MAP_SIZE); i++) {
			tiles[i] = random.nextInt(0xFFFFFF);
		}

		tiles[0] = 0x000000;
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	/*
	 * public void render(int xOffSet, int yOffSet) { for (int y = 0; y < height; y++) { int yp = y + yOffSet; if (yp < 0 || yp >= height)
	 * continue; for (int x = 0; x < width; x++) { int xp = x + xOffSet; if (xp < 0 || xp >= width) continue; pixels[xp + yp * width] =
	 * Sprite.grass.pixels[(x & (Sprite.grass.SIZE - 1)) + (y & (Sprite.grass.SIZE - 1)) * Sprite.grass.SIZE]; } } }
	 */

	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}

		for (int y = 0; y < sheet.HEIGHT; y++) {
			int ya = y + yp;
			for (int x = 0; x < sheet.WIDTH; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = sheet.pixels[x + y * sheet.WIDTH];
			}
		}
	}

	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}

		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}

	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}

	public void renderProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = p.getSprite().pixels[x + y * p.getSpriteSize()];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderMob(int xp, int yp, Mob mob) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			int ys = y;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				int xs = x;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = mob.getSprite().pixels[xs + ys * 32];

				if (mob instanceof Chaser) {
					if (col == 0xff472BBF) col = 0xff000000;
					if (col == 0xffFEFF60) col = 0xffD90022;
				}
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderMob(int xp, int yp, Sprite sprite, int flip) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			int ys = y;
			if (flip == 2 || flip == 3) ys = 31 - y;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				int xs = x;
				if (flip == 1 || flip == 3) xs = 31 - x;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[xs + ys * 32];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}

// What is function <h2> renderTile() <h2> doing?
// This function simply renders a tile at position 'xp' and 'yp'.
// <h2> xp/yp -= xOffset/yOffset <h2> these lines are basically moving the tile position by the offset int the opposite direction of the
// button pressed.
// And 'xa' and 'ya' are the absolute positions(coordinates) of the every pixel in that tile, that is, where that pixel is exactly going to
// render on the screen.
// Now what is the line <h2> if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break; <h2> doing in this function?
// This line is basically is making sure that we do not render any tile on the canvas which goes out of bounds of the canvas, that is, we
// need not to further keep rendering the tile which is now not visible on the screen.
