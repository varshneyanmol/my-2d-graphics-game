package com.rain.level.tile;

import com.rain.graphics.Screen;
import com.rain.graphics.Sprite;
import com.rain.level.tile.spawn_level.SpawnGrassTile;
import com.rain.level.tile.spawn_level.SpawnHedgeTile;
import com.rain.level.tile.spawn_level.SpawnRockTile;
import com.rain.level.tile.spawn_level.SpawnWallTile;
import com.rain.level.tile.spawn_level.SpawnWaterTile;
import com.rain.level.tile.spawn_level.SpawnFloorTile;

public class Tile {

	public int x, y;
	public Sprite sprite;

	public static Tile grassTile = new GrassTile(Sprite.grass);
	public static Tile flowerTile = new FlowerTile(Sprite.flower);
	public static Tile rockTile = new RockTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);

	public static Tile spawn_grass_tile = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_hedge_tile = new SpawnHedgeTile(Sprite.spawn_hedge);
	public static Tile spawn_rock_tile = new SpawnRockTile(Sprite.spawn_rock);
	public static Tile spawn_wall1_tile = new SpawnWallTile(Sprite.spawn_wall1);
	public static Tile spawn_wall2_tile = new SpawnWallTile(Sprite.spawn_wall2);
	public static Tile spawn_wall3_tile = new SpawnWallTile(Sprite.spawn_wall3);
	public static Tile spawn_water_tile = new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_floor_tile = new SpawnFloorTile(Sprite.spawn_floor);

	public static final int col_spawn_grass_tile = 0xff267F00;
	public static final int col_spawn_hedge_tile = 0; // unused
	public static final int col_spawn_rock_tile = 0xff9CA697;
	public static final int col_spawn_wall1_tile = 0xffF2D8B6;
	public static final int col_spawn_wall2_tile = 0xff544430;
	public static final int col_spawn_wall3_tile = 0xff45413D;
	public static final int col_spawn_water_tile = 0; // unused
	public static final int col_spawn_floor_tile = 0xffB5A94A;

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {

	}

	public boolean solid() {
		return false;
	}
}
