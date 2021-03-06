package com.mojang.ld22.item.resource;

import java.util.*;

import com.mojang.ld22.entity.Player;
import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.entity.*;
import com.mojang.ld22.level.Level;
import com.mojang.ld22.level.tile.Tile;
import com.mojang.ld22.Dumpable;

public class Resource extends Dumpable {
	public static Resource wood = new Resource("Wood", 1 + 4 * 32, Color.get(-1, 200, 531, 430));
	public static Resource stone = new PlantableResource("Stone", 2 + 4 * 32, Color.get(-1, 111, 333, 555), Tile.rock, Tile.grass, Tile.dirt);
	public static Resource flower = new PlantableResource("Flower", 0 + 4 * 32, Color.get(-1, 10, 444, 330), Tile.flower, Tile.grass);
	public static Resource acorn = new PlantableResource("Acorn", 3 + 4 * 32, Color.get(-1, 100, 531, 320), Tile.treeSapling, Tile.grass);
	public static Resource dirt = new PlantableResource("Dirt", 2 + 4 * 32, Color.get(-1, 100, 322, 432), Tile.dirt, Tile.hole, Tile.water, Tile.lava);
	public static Resource sand = new PlantableResource("Sand", 2 + 4 * 32, Color.get(-1, 110, 440, 550), Tile.sand, Tile.grass, Tile.dirt);
	public static Resource cactusFlower = new PlantableResource("Cactus", 4 + 4 * 32, Color.get(-1, 10, 40, 50), Tile.cactusSapling, Tile.sand);
	public static Resource seeds = new PlantableResource("Seeds", 5 + 4 * 32, Color.get(-1, 10, 40, 50), Tile.wheat, Tile.farmland);
	public static Resource wheat = new Resource("Wheat", 6 + 4 * 32, Color.get(-1, 110, 330, 550));
	public static Resource bread = new FoodResource("Bread", 8 + 4 * 32, Color.get(-1, 110, 330, 550), 2, 5);
	public static Resource apple = new FoodResource("Apple", 9 + 4 * 32, Color.get(-1, 100, 300, 500), 1, 5);

	public static Resource coal = new Resource("Coal", 10 + 4 * 32, Color.get(-1, 000, 111, 111));
	public static Resource ironOre = new Resource("I.Ore", 10 + 4 * 32, Color.get(-1, 100, 322, 544));
	public static Resource goldOre = new Resource("G.Ore", 10 + 4 * 32, Color.get(-1, 110, 440, 553));
	public static Resource ironIngot = new Resource("Iron", 11 + 4 * 32, Color.get(-1, 100, 322, 544));
	public static Resource goldIngot = new Resource("Gold", 11 + 4 * 32, Color.get(-1, 110, 330, 553));

	public static Resource slime = new Resource("Slime", 10 + 4 * 32, Color.get(-1, 10, 30, 50));
	public static Resource glass = new Resource("Glass", 12 + 4 * 32, Color.get(-1, 555, 555, 555));
	public static Resource cloth = new Resource("Cloth", 1 + 4 * 32, Color.get(-1, 25, 252, 141));
	public static Resource cloud = new PlantableResource("Cloud", 2 + 4 * 32, Color.get(-1, 222, 555, 444), Tile.cloud, Tile.infiniteFall);
	public static Resource gem = new Resource("Gem", 13 + 4 * 32, Color.get(-1, 101, 404, 545));
	public static Resource feather = new Resource("Feathr", 9 + 10 * 32, Color.get(-1, 332, 443, 554));
	public static Resource torch;
	static {
		try {
			torch = new EntityResource("Torch", 14 + 4 * 32, Color.get(-1, 210, 410, 550), Torch.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String name;
	public int sprite;
	public int color;

	protected Resource(String name, int sprite, int color) {
		if (name != null && name.length() > 6)
			throw new RuntimeException("Name cannot be longer than six characters!");
		this.name = name;
		this.sprite = sprite;
		this.color = color;
	}

	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
		return false;
	}

	static public Resource get(String name) {
		if (name.equals("Wood")) return wood;
		if (name.equals("Stone")) return stone;
		if (name.equals("Flower")) return flower;
		if (name.equals("Acorn")) return acorn;
		if (name.equals("Dirt")) return dirt;
		if (name.equals("Sand")) return sand;
		if (name.equals("Cactus")) return cactusFlower;
		if (name.equals("Seeds")) return seeds;
		if (name.equals("Wheat")) return wheat;
		if (name.equals("Bread")) return bread;
		if (name.equals("Apple")) return apple;
		if (name.equals("Coal")) return coal;
		if (name.equals("I.Ore")) return ironOre;
		if (name.equals("G.Ore")) return goldOre;
		if (name.equals("Iron")) return ironIngot;
		if (name.equals("Gold")) return goldIngot;
		if (name.equals("Slime")) return slime;
		if (name.equals("Glass")) return glass;
		if (name.equals("Cloth")) return cloth;
		if (name.equals("Cloud")) return cloud;
		if (name.equals("Gem")) return gem;
		if (name.equals("Torch")) return torch;
		if (name.equals("Feathr")) return feather;
		return null;
	}

}

