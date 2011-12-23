package com.mojang.ld22.screen;

import com.mojang.ld22.Game;
import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.gfx.Font;
import com.mojang.ld22.gfx.Screen;
import com.mojang.ld22.sound.Sound;

public class TitleMenu extends SelectionMenu {

	public TitleMenu() {
		super("New game", "Load game", "How to play", "About CE edition", "Note from Notch", "Settings", "Quit");
	}

	public void tick() {
		if (input.drop.clicked || input.pause.clicked) {
			game.stop();
			return;
		}
		super.tick();
	}

	protected void selectElement(int e) {
		if (e == 0) {
			Sound.test.play();
			game.setMenu(null);
			game.newGame();
		}
		if (e == 1) {
			Sound.test.play();
			game.setMenu(null);
			game.loadGame();
		}
		if (e == 2) game.setMenu(new InstructionsMenu(this));
		if (e == 3) game.setMenu(new AboutMenu(this));
		if (e == 4) game.setMenu(new NotchMenu(this));
		if (e == 5) game.setMenu(new SettingsMenu(this));
		if (e == 6) game.stop();
	}

}
