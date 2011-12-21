package com.mojang.ld22.entity;

import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.screen.ContainerMenu;

public class AutoChest extends Furniture {
	public static Inventory inventory = new Inventory();

	public AutoChest() {
		super("AutoChest");
		col = Color.get(-1, 100, 555, 100);
		sprite = 1;
	}

	public boolean use(Player player, int attackDir) {
		player.inventory.items = inventory.items;
		player.game.setMenu(new ContainerMenu(player, "AutoChest", inventory));
		return true;
	}
}