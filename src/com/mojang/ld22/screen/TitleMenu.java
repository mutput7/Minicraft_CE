package com.mojang.ld22.screen;

import com.mojang.ld22.Game;
import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.gfx.Font;
import com.mojang.ld22.gfx.Screen;
import com.mojang.ld22.sound.Sound;

public class TitleMenu extends Menu {
	private int selected = 0;

	private static final String[] options = { "Begin the adventure!", "Load Game", "How to play", "About", "Note from Notch", "Settings", "Quit" };


	public void tick() {
		if (input.up.clicked) selected--;
		if (input.down.clicked) selected++;

		int len = options.length;
		if (selected < 0) selected += len;
		if (selected >= len) selected -= len;

		if (input.attack.clicked || input.menu.clicked) {
			if (selected == 0) {
				Sound.test.play();
				game.setMenu(null);
				game.newGame();
			}
			if (selected == 1) {
				Sound.test.play();
				game.setMenu(null);
				game.loadGame();
			}
			if (selected == 2) game.setMenu(new InstructionsMenu(this));
			if (selected == 3) game.setMenu(new AboutMenu(this));
			if (selected == 4) game.setMenu(new NotchMenu(this));
			if (selected == 5) game.setMenu(new SettingsMenu(this));
			if (selected == 6) game.stop();
		}
	}

	public void render(Screen screen) {
		screen.clear(0);

		int h = 4;
		int w = 13;
		int titleColor = Color.get(0, 010, 131, 551);
		int xo = (screen.w - w * 8) / 2;
		int yo = 24;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				screen.render(xo + x * 8, yo + y * 8, x + (y + 6) * 32, titleColor, 0);
			}
		}

		for (int i = 0; i < options.length; i++) {
			String msg = options[i];
			int col = Color.get(0, 222, 222, 222);
			if (i == selected) {
				msg = "> " + msg + " <";
				col = Color.get(0, 555, 555, 555);
			}
			Font.draw(msg, screen, (screen.w - msg.length() * 8) / 2, (8 + i) * 8, col);
		}

		Font.draw("(Arrows/WASD,and X/C or ENTER/Space)", screen, 0, screen.h - 8, Color.get(0, 111, 111, 111));
	}
}
