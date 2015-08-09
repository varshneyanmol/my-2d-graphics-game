package com.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.rain.entity.mob.Chaser;
import com.rain.entity.mob.Dummy;

public class SpawnLevel extends Level {

	public SpawnLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tilesLoadedLevel = new int[w * h];
			image.getRGB(0, 0, w, h, tilesLoadedLevel, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("could not load level!");
		}
		addEntity(new Chaser(40, 40));
		for (int i = 0; i < 5; i++) {
			addEntity(new Dummy(25, 30));
		}
	}

	protected void generateLevel() {

	}
}
