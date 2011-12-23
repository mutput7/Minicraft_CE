package com.mojang.ld22.screen;

import java.io.IOException;

import com.mojang.ld22.Game;
import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.gfx.Font;
import com.mojang.ld22.gfx.Screen;
import com.mojang.ld22.sound.Sound;

@SuppressWarnings("unused")
public class GUIMenu extends SelectionMenu {

	public GUIMenu(Menu parent) {
		super(parent, "", "Back");
	}

	protected void postInit() {
		if (game.hasBar)
			options[0] = "Hide black bar";
		else
			options[0] = "Show black bar";
	}

	@Override
	protected void selectElement(int e) {
		if (e == 0) {
			toggleBar();
		} else {
			game.setMenu(parent);
		}
	}

	private void toggleBar() {
		boolean active = game.hasBar;
		if (active)
			options[0] = "Show black bar";
		else
			options[0] = "Hide black bar";
		game.setBar(!active);
	}

}
