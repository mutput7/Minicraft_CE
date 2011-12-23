package com.mojang.ld22.screen;

import java.io.IOException;

import com.mojang.ld22.Game;
import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.gfx.Font;
import com.mojang.ld22.gfx.Screen;
import com.mojang.ld22.sound.Sound;

public class SelectionMenu extends Menu {
	protected Menu parent;
	private int selected = 0;

	protected String[] options;

	public SelectionMenu(String... options) {
		this.options = options;
	}

	public SelectionMenu(Menu parent, String... options) {
		this(options);
		this.parent = parent;
	}

	public void tick() {
		super.tick();
		if (input.up.clicked) selected--;
		if (input.down.clicked) selected++;
		if (input.pause.clicked && parent != null) { game.setMenu(parent); return; }

		int len = options.length;
		if (selected < 0) selected += len;
		if (selected >= len) selected -= len;

		if (input.attack.clicked || input.menu.clicked) {
			selectElement(selected);
		}
	}

	protected void selectElement(int selected) {
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
