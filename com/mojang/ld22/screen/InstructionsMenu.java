package com.mojang.ld22.screen;

import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.gfx.Font;
import com.mojang.ld22.gfx.Screen;

public class InstructionsMenu extends Menu {
	private Menu parent;
	public static final int WIDTH = 287;

	public InstructionsMenu(Menu parent) {
		this.parent = parent;
	}

	public void tick() {
		if (input.attack.clicked || input.menu.clicked) {
			game.setMenu(parent);
		}
	}

	public void render(Screen screen) {
		screen.clear(0);

		Font.draw("HOW TO PLAY", screen, WIDTH / 3, 1 * 8, Color.get(0, 555, 555, 555));
		Font.draw("Move your character with the", screen, 0 * 8 + 4, 3 * 8, Color.get(0, 333, 333, 333));
		Font.draw("arrow keys or WASD keys.", screen, 0 * 8 + 4, 4 * 8, Color.get(0, 333, 333, 333));
		Font.draw("press C/CTRL to attack", screen, 0 * 8 + 4, 5 * 8, Color.get(0, 333, 333, 333));
		Font.draw("and X/ALT to open the inventory", screen, 0 * 8 + 4, 6 * 8, Color.get(0, 333, 333, 333));
		Font.draw("and to use items.", screen, 0 * 8 + 4, 7 * 8, Color.get(0, 333, 333, 333));
		Font.draw("Select an item in the inventory to", screen, 0 * 8 + 4, 8 * 8, Color.get(0, 333, 333, 333));
		Font.draw("Kill the air wizard to", screen, 0 * 8 + 4, 12 * 8, Color.get(0, 333, 333, 333));
		Font.draw("win the game!", screen, 0 * 8 + 4, 13 * 8, Color.get(0, 333, 333, 333));
	}
}
