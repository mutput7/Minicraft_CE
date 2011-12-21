package com.mojang.ld22.screen;

import com.mojang.ld22.Game;
import com.mojang.ld22.entity.Player;
import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.gfx.Font;
import com.mojang.ld22.gfx.Screen;

public class SaveLoadMenu extends Menu {
	private int inputDelay = 60;

	public SaveLoadMenu(Player player) {
	}

	public void tick() {
		if (inputDelay > 0)
			inputDelay--;
		else if (input.menu.clicked ) {
			Game.load();
		}
		else if (input.attack.clicked ) {
			Game.save();
		}
		else if (input.dropitems.clicked) {
			game.setMenu(null);
		}
	}
	
	public void render(Screen screen) {
		Font.renderFrame(screen, "", 10, 8, 28, 18);
		Font.draw("PAUSE", screen, 85, 70, Color.get(-1, 555, 555, 555));
		int seconds = game.gameTime / 60;
		int minutes = seconds / 60;
		int hours = minutes / 60;
		minutes %= 60;
		seconds %= 60;

		String timeString = "";
		if (hours > 0) {
			timeString = hours + "h " + (minutes < 10 ? "0" : "") + minutes + "m";
		} else {
			timeString = minutes + "m " + (seconds < 10 ? "0" : "") + seconds + "s";
		}
		Font.draw("Time:", screen, 85, 80, Color.get(-1, 555, 555, 555));
		Font.draw(timeString, screen, 123, 80, Color.get(-1, 550, 550, 550));
		Font.draw("Score:", screen, 85, 96, Color.get(-1, 555, 555, 555));
		Font.draw("" + Game.player.score, screen, 133, 96, Color.get(-1, 550, 550, 550));
		Font.draw("Press C to save", screen, 85, 114, Color.get(-1, 333, 333, 333));
		Font.draw("Press X to load", screen, 85, 128, Color.get(-1, 333, 333, 333));
		Font.draw("Press K to resume", screen, 85, 140, Color.get(-1, 333, 333, 333));
	}
}
