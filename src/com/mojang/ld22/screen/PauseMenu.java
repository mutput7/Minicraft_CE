package com.mojang.ld22.screen;

import com.mojang.ld22.Game;
import com.mojang.ld22.entity.Player;
import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.gfx.Font;
import com.mojang.ld22.gfx.Screen;

public class PauseMenu extends Menu {
	private Game game;
	private boolean saved = false;
	private boolean loaded = false;

	public PauseMenu(Game game, Player player) {
		this.game = game;
	}

	public void tick() {
		if (input.menu.clicked && !loaded) {
			game.loadGame();
			loaded = true;
		}
		else if (input.attack.clicked && !saved) {
			game.saveGame();
			saved = true;
		}
		else if (input.pause.clicked) {
			game.setMenu(null);
		}
		else if (input.drop.clicked) {
			game.setMenu(null);
			game.setMenu(new TitleMenu());
		}
	}

	public void render(Screen screen) {
		Font.renderFrame(screen, "Pause", 8, 5, 28, 9+(123-75)/8);
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
		Font.draw("Time:", screen, 80, 60, Color.get(-1, 555, 555, 555));
		Font.draw(timeString, screen, 118, 60, Color.get(-1, 550, 550, 550));
		Font.draw("Score:", screen, 80, 71, Color.get(-1, 555, 555, 555));
		Font.draw("" + game.player.score, screen, 128, 71, Color.get(-1, 550, 550, 550));
		if (saved == false)
			Font.draw("Press C to save", screen, 80, 85, Color.get(-1, 333, 333, 333));
		if (loaded == false)
			Font.draw("Press X to load", screen, 80, 95, Color.get(-1, 333, 333, 333));
		Font.draw("Press Z to resume", screen, 80, 105, Color.get(-1, 333, 333, 333));
		Font.draw("Press Q to quit", screen, 80, 115, Color.get(-1, 333, 333, 333));
	}
}
