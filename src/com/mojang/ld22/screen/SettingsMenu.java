package com.mojang.ld22.screen;

import java.io.IOException;

import com.mojang.ld22.Game;
import com.mojang.ld22.screen.*;
import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.gfx.Font;
import com.mojang.ld22.gfx.Screen;
import com.mojang.ld22.sound.Sound;

public class SettingsMenu extends SelectionMenu {

	public SettingsMenu(Menu parent) {
		super(parent, "Size (buggy)", "GUI Options", "Back");
	}

	@Override
	protected void selectElement(int e) {
		if (e == 0) {
			game.setMenu(new SizeMenu(this));
		} else if (e == 1) {
			game.setMenu(new GUIMenu(this));
		} else {
			game.setMenu(parent);
		}
	}

}
