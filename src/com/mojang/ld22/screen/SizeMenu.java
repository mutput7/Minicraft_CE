package com.mojang.ld22.screen;

import java.io.IOException;

import com.mojang.ld22.Game;
import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.gfx.Font;
import com.mojang.ld22.gfx.Screen;
import com.mojang.ld22.sound.Sound;

public class SizeMenu extends SelectionMenu {

	public SizeMenu(Menu parent) {
		super(parent, "Small", "Big", "Back");
	}

	@Override
	protected void selectElement(int e) {
		if (e == 0) {
			game.resizeScreen(120, 160);
			game.setMenu(new TitleMenu());
		}
		if (e == 1) {
			game.resizeScreen(160, 287);
			game.setMenu(new TitleMenu());
		}
		if (e == 2) game.setMenu(parent);
	}

}
