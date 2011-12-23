package com.mojang.ld22.entity;

import java.util.*;

import com.mojang.ld22.gfx.*;
import com.mojang.ld22.item.Item;
import com.mojang.ld22.level.tile.Tile;

public class Torch extends Entity {
	private Random random = new Random();

	public int col = Color.get(-1, 210, 410, 550);
	public int[] sprites = { 14 + 4 * 32, 15 + 4 * 32 };
	public int sprite = 0;
	public int time;

	public void tick() {
		time++;
		if (time % 15 == 0 || random.nextInt(100) < 3) sprite ^= 1;
	}

	public void render(Screen screen) {
		screen.render(x-4, y-4, sprites[sprite], col, 0);
	}

	public int getLightRadius() {
		return 6;
	}

	public boolean interact(Player player, Item item, int attackDir) {
		remove();
		return true;
	}

	//public void hurt(Mob mob, int dmg, int attackDir) {
	//remove();
	//}
	//
	//public void hurt(Tile tile, int x, int y, int dmg) {
	//remove();
	//}

	public void saveTo(StringBuffer str) {
		super.saveTo(str);
	}

	public void loadFrom(StringTokenizer st) {
		super.loadFrom(st);
	}

}

