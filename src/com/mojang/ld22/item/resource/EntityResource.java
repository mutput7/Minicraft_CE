package com.mojang.ld22.item.resource;

import com.mojang.ld22.item.*;
import com.mojang.ld22.item.resource.*;
import com.mojang.ld22.level.Level;
import com.mojang.ld22.entity.*;
import com.mojang.ld22.level.tile.Tile;

public class EntityResource extends Resource {
	private Class<? extends Entity> clazz;

	public EntityResource(String name, int sprite, int color, Class<? extends Entity> clazz) throws InstantiationException, IllegalAccessException {
		super(name, sprite, color);
		this.clazz = clazz;
	}

	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
		if (tile != Tile.grass && tile != Tile.dirt && tile != Tile.sand)
			return false;
		try {
			Entity e = clazz.newInstance();
			e.x = xt*16+8;
			e.y = yt*16+8;
			level.add(e);
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

