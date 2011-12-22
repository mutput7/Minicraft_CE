package com.mojang.ld22.entity;

import java.util.*;

import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.screen.ContainerMenu;

public class Chest extends Furniture {
	public Inventory inventory = new Inventory();

	public Chest() {
		super("Chest");
		col = Color.get(-1, 110, 331, 552);
		sprite = 1;
	}

	public boolean use(Player player, int attackDir) {
		player.game.setMenu(new ContainerMenu(player, "Chest", inventory));
		return true;
	}

	public void loadFrom(StringTokenizer st) {
		super.loadFrom(st);
		inventory.loadFrom(st);
	}

	public void saveTo(StringBuffer str) {
		super.saveTo(str);
		inventory.saveTo(str);
	}

}
