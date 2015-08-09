package com.rain.level;

import java.util.ArrayList;
import java.util.List;

import com.rain.entity.Entity;
import com.rain.entity.Projectiles.Projectile;
import com.rain.entity.mob.Player;
import com.rain.entity.particle.Particle;
import com.rain.graphics.Screen;
import com.rain.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesRandomLevel;
	protected int[] tilesLoadedLevel;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	private List<Player> players = new ArrayList<Player>();

	public static Level spawn_level = new SpawnLevel("/levels/spawn_level.png");

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesRandomLevel = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void loadLevel(String path) {

	}

	protected void generateLevel() {

	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}

		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}

		for (int i = 0; i < players.size(); i++) {
			players.get(i).update();
		}

		removeDeadEntities();
	}

	private void removeDeadEntities() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}

		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}

		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isRemoved()) players.remove(i);
		}

	}

	public void Time() {

	}

	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int corner = 0; corner < 4; corner++) {
			int xt = (x - corner % 2 * size + xOffset) >> 4;
			int yt = (y - corner / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).solid()) {
				solid = true;
			}
		}
		return solid;
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);

			}
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}

		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}

		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}

	}

	public void addEntity(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
		} else {
			entities.add(e);
		}
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Player getPlayerAt(int index) {
		return players.get(index);
	}

	public Player getClientPlayer() {
		return players.get(0);
	}

	public List<Entity> getEntitiesInRadius(Entity e, int radius) {
		List<Entity> entitiesInRadius = new ArrayList<Entity>();
		int xe = (int) e.getX();
		int ye = (int) e.getY();

		for (Entity en : entities) {
			int x = (int) en.getX();
			int y = (int) en.getY();
			double dx = Math.abs(xe - x);
			double dy = Math.abs(ye - y);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) {
				entitiesInRadius.add(en);
			}
		}

		return entitiesInRadius;
	}

	public List<Player> getPlayersInRadius(Entity e, int radius) {
		List<Player> playersInRadius = new ArrayList<Player>();
		int xe = (int) e.getX();
		int ye = (int) e.getY();

		for (Player p : players) {
			int x = (int) p.getX();
			int y = (int) p.getY();
			double dx = Math.abs(xe - x);
			double dy = Math.abs(ye - y);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) {
				playersInRadius.add(p);
			}
		}

		return playersInRadius;
	}

	public Tile getTile(int x, int y) {

		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;

		if (tilesLoadedLevel[x + y * width] == Tile.col_spawn_grass_tile) return Tile.spawn_grass_tile;
		if (tilesLoadedLevel[x + y * width] == Tile.col_spawn_floor_tile) return Tile.spawn_floor_tile;
		if (tilesLoadedLevel[x + y * width] == Tile.col_spawn_hedge_tile) return Tile.spawn_hedge_tile;
		if (tilesLoadedLevel[x + y * width] == Tile.col_spawn_rock_tile) return Tile.spawn_rock_tile;
		if (tilesLoadedLevel[x + y * width] == Tile.col_spawn_wall1_tile) return Tile.spawn_wall1_tile;
		if (tilesLoadedLevel[x + y * width] == Tile.col_spawn_wall2_tile) return Tile.spawn_wall2_tile;
		if (tilesLoadedLevel[x + y * width] == Tile.col_spawn_wall3_tile) return Tile.spawn_wall3_tile;
		if (tilesLoadedLevel[x + y * width] == Tile.col_spawn_water_tile) return Tile.spawn_water_tile;

		return Tile.voidTile;
	}
}
