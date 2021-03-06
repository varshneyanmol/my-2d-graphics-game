package com.rain.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	private int width, height;
	public int pixels[];
	protected SpriteSheet sheet;

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);
	public static Sprite dummy = new Sprite(32, 0, 0, SpriteSheet.dummy_down);

	// spawn level sprites
	public static Sprite spawn_grass = new Sprite(16, 0, 0, SpriteSheet.spawn_level_tiles);
	public static Sprite spawn_hedge = new Sprite(16, 0, 1, SpriteSheet.spawn_level_tiles);
	public static Sprite spawn_rock = new Sprite(16, 2, 0, SpriteSheet.spawn_level_tiles);
	public static Sprite spawn_wall1 = new Sprite(16, 1, 0, SpriteSheet.spawn_level_tiles);
	public static Sprite spawn_wall2 = new Sprite(16, 1, 1, SpriteSheet.spawn_level_tiles);
	public static Sprite spawn_wall3 = new Sprite(16, 1, 2, SpriteSheet.spawn_level_tiles);
	public static Sprite spawn_water = new Sprite(16, 3, 0, SpriteSheet.spawn_level_tiles);
	public static Sprite spawn_floor = new Sprite(16, 2, 1, SpriteSheet.spawn_level_tiles);

	// player sprites
	public static Sprite player_up = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_down = new Sprite(32, 2, 5, SpriteSheet.tiles);
	public static Sprite player_side = new Sprite(32, 1, 5, SpriteSheet.tiles);

	public static Sprite player_up_1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_up_2 = new Sprite(32, 0, 7, SpriteSheet.tiles);

	public static Sprite player_down_1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_down_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);

	public static Sprite player_side_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_side_2 = new Sprite(32, 1, 7, SpriteSheet.tiles);

	// projectiles sprites
	public static Sprite wizard_projectile = new Sprite(16, 0, 0, SpriteSheet.projectiles_wizard);

	// Particle sprites
	public static Sprite particle = new Sprite(3, 0xAAAAAA);

	protected Sprite(SpriteSheet sheet, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		this.pixels = new int[SIZE * SIZE];
		load();
	}

	public Sprite(int size, int color) {
		this.SIZE = size;
		this.width = size;
		this.height = size;
		this.pixels = new int[SIZE * SIZE];
		setColor(color);
		/*
		 * for (int i = 0; i < SIZE * SIZE; i++) { pixels[i] = color; }
		 */
	}

	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}

	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	public void load() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
			}
		}
	}

	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
