package com.mojang.ld22;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.mojang.ld22.entity.Player;
import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.gfx.Font;
import com.mojang.ld22.gfx.Screen;
import com.mojang.ld22.gfx.SpriteSheet;
import com.mojang.ld22.level.Level;
import com.mojang.ld22.level.tile.Tile;
import com.mojang.ld22.screen.DeadMenu;
import com.mojang.ld22.screen.GUIMenu;
import com.mojang.ld22.screen.LevelTransitionMenu;
import com.mojang.ld22.screen.Menu;
import com.mojang.ld22.screen.TitleMenu;
import com.mojang.ld22.screen.WonMenu;
import com.mojang.ld22.Dumpable;

public class Game extends Canvas implements Runnable, ComponentListener {
	private static final long serialVersionUID = 1L;
	private Random random = new Random();
	public static final String NAME = "Minicraft : Community Edition";
	public int WIDTH = 287;
	public int HEIGHT = 150;
	private static final int SCALE = 3;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private boolean running = false;
	private Screen screen;
	private Screen lightScreen;
	private InputHandler input = new InputHandler(this);

	private int[] colors = new int[256];
	private int tickCount = 0;
	public int gameTime = 0;

	private Level level;
	private Level[] levels = new Level[5];
	private int currentLevel = 3;
	public Player player;

	public Menu menu;
	private int playerDeadTime;
	private int pendingLevelChange;
	private int wonTimer = 0;
	public boolean hasWon = false;
	public boolean hasBar = true;

	public Game() {
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addComponentListener(this);
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
		if (menu != null) menu.init(this, input);
	}

	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void newGame() {
		resetGame();

		playerDeadTime = 0;
		wonTimer = 0;
		gameTime = 0;
		hasWon = false;

		player.findStartPos(level);
		setCurrentLevel(3);
		for (int i = 0; i < 5; i++) {
			levels[i].trySpawn(50000);
		}
	}

	public void loadGame() {
		resetGame();
		// load Game
		String filename = "saves/game.dat";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			StringTokenizer st = new StringTokenizer(reader.readLine());
			gameTime = Integer.parseInt(st.nextToken());
			hasBar = Integer.parseInt(st.nextToken()) == 1;
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found : " + filename);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// load levels
		for (int i = 0; i < 5; i++)
		{
			levels[i].load("saves/level" + i + ".dat");
		}
		// load player
		player.load("saves/player.dat");
		System.out.println("Loaded !");
	}

	public void saveGame() {
		File f = new File("saves");
		try {
			f.mkdir();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// save Game
		String filename = "saves/game.dat";
		try {
			FileOutputStream writer = new FileOutputStream(filename);
			StringBuffer str = new StringBuffer();
			str.append(gameTime + " " + (hasBar ? 1 : 0) + " ");
			writer.write(str.toString().getBytes());
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found : " + filename);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// save levels
		for (int i = 0; i < 5; i++)
		{
			levels[i].save("saves/level" + i + ".dat");
		}
		// save player
		player.save("saves/player.dat");
		System.out.println("Saved !");
	}

	public void stop() {
		running = false;
	}

	public void resetGame() {

		levels = new Level[5];
		currentLevel = 3;

		levels[4] = new Level(128, 128, 1, null);
		levels[3] = new Level(128, 128, 0, levels[4]);
		levels[2] = new Level(128, 128, -1, levels[3]);
		levels[1] = new Level(128, 128, -2, levels[2]);
		levels[0] = new Level(128, 128, -3, levels[1]);

		level = levels[currentLevel];
		player = new Player(this, input);
	}

	private void init() {
		int pp = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);
					int mid = (rr * 30 + gg * 59 + bb * 11) / 100;

					int r1 = ((rr + mid * 1) / 2) * 230 / 255 + 10;
					int g1 = ((gg + mid * 1) / 2) * 230 / 255 + 10;
					int b1 = ((bb + mid * 1) / 2) * 230 / 255 + 10;
					colors[pp++] = r1 << 16 | g1 << 8 | b1;

				}
			}
		}
		try {
			screen = new Screen(WIDTH, HEIGHT, new SpriteSheet(ImageIO.read(Game.class.getResourceAsStream("/icons.png"))));
			lightScreen = new Screen(WIDTH, HEIGHT, new SpriteSheet(ImageIO.read(Game.class.getResourceAsStream("/icons.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setMenu(new TitleMenu());
		resetGame();
	}

	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();

		init();

		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			while (unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
		}
		System.exit(0);
	}

	public void tick() {
		tickCount++;
		if (!hasFocus()) {
			input.releaseAll();
		} else {
			input.tick();
			if (menu != null) {
				menu.tick();
			} else {
				if (!player.removed && !hasWon) gameTime++;
				if (player.removed) {
					playerDeadTime++;
					if (playerDeadTime > 60) {
						setMenu(new DeadMenu());
					}
				} else {
					if (pendingLevelChange != 0) {
						setMenu(new LevelTransitionMenu(pendingLevelChange));
						pendingLevelChange = 0;
					}
				}
				if (wonTimer > 0) {
					if (--wonTimer == 0) {
						setMenu(new WonMenu());
					}
				}
				level.tick();
				Tile.tickCount++;
			}
		}
	}

	public void changeLevel(int dir) {
		setCurrentLevel(currentLevel + dir);
		// fix player position
		player.x = (player.x >> 4) * 16 + 8;
		player.y = (player.y >> 4) * 16 + 8;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int l) {
		level.remove(player);
		currentLevel = l;
		level = levels[currentLevel];
		level.add(player);
	}

	public void respawnPlayer() {
		player.removed = false;
		player.health = player.maxHealth / 2;
		player.stamina = 0;
		playerDeadTime = 0;
		player.respawn();
		setCurrentLevel(3);
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}

		if (screen != null) screen.tick();
		if (screen != null && screen.isFrozen()) return;

		int xScroll = player.x - screen.w / 2;
		int yScroll = player.y - (screen.h - 8) / 2;
		if (xScroll < 16) xScroll = 16;
		if (yScroll < 16) yScroll = 16;
		if (xScroll > level.w * 16 - screen.w - 16) xScroll = level.w * 16 - screen.w - 16;
		if (yScroll > level.h * 16 - screen.h - 16) yScroll = level.h * 16 - screen.h - 16;
		if (currentLevel > 3) {
			int col = Color.get(20, 20, 121, 121);
			for (int y = 0; y < 14; y++)
				for (int x = 0; x < 24; x++) {
					screen.render(x * 8 - ((xScroll / 4) & 7), y * 8 - ((yScroll / 4) & 7), 0, col, 0);
				}
		}

		level.renderBackground(screen, xScroll, yScroll);
		level.renderSprites(screen, xScroll, yScroll);

		if (currentLevel < 3) {
			lightScreen.clear(0);
			level.renderLight(lightScreen, xScroll, yScroll);
			screen.overlay(lightScreen, xScroll, yScroll);
		}

		renderGui();

		if (!hasFocus()) renderFocusNagger();

		for (int y = 0; y < screen.h; y++) {
			for (int x = 0; x < screen.w; x++) {
				int cc = screen.pixels[x + y * screen.w];
				if (cc < 255) pixels[x + y * WIDTH] = colors[cc];
			}
		}

		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());

		int ww = WIDTH * 3;
		int hh = HEIGHT * 3;
		int xo = (getWidth() - ww) / 2;
		int yo = (getHeight() - hh) / 2;
		g.drawImage(image, xo, yo, ww, hh, null);
		g.dispose();
		bs.show();
	}

	private void renderGui() {
		int bgCol = getBarColor();
		// draw gui background
		if (hasBar) {
			for (int y = 0; y < 2; y++) {
				for (int x = 0; x < WIDTH/8 + 1; x++) {
					screen.render(x * 8, screen.h - 16 + y * 8, 0 + 12 * 32, Color.get(bgCol, bgCol, bgCol, bgCol), 0);
				}
			}
		}

		// display health
		for (int i = 0; i < 10; i++) {
			if (i < player.health*10/player.maxHealth)
				screen.render(i * 8, screen.h - 16, 0 + 12 * 32, Color.get(bgCol, 200, 500, 533), 0);
			else
				screen.render(i * 8, screen.h - 16, 0 + 12 * 32, Color.get(bgCol, 100, 000, 000), 0);
		}

		// display stamina
		if (player.staminaRechargeDelay > 0) {
			for (int i = 0; i < 10; i++) {
				if (player.staminaRechargeDelay / 4 % 2 == 0)
					screen.render(i * 8, screen.h - 8, 1 + 12 * 32, Color.get(bgCol, 555, 000, 000), 0);
				else
					screen.render(i * 8, screen.h - 8, 1 + 12 * 32, Color.get(bgCol, 110, 000, 000), 0);
			}
		} else {
			for (int i = 0; i < 10; i++) {
				if (i <= player.stamina*10/player.maxStamina)
					screen.render(i * 8, screen.h - 8, 1 + 12 * 32, Color.get(bgCol, 220, 550, 553), 0);
				else
					screen.render(i * 8, screen.h - 8, 1 + 12 * 32, Color.get(bgCol, 110, 000, 000), 0);
			}
		}
		// draw active item
		if (player.activeItem != null) {
			player.activeItem.renderInventory(screen, 10 * 8, screen.h - 16);
		}

		// draw lvl
		Font.draw("Level: "+player.lvl, screen, screen.w-75, screen.h-9, Color.get(bgCol, 333, 333, 333));

		if (menu != null) {
			menu.render(screen);
		}
	}

	private void renderFocusNagger() {
		String msg = "Click to focus!";
		int xx = (WIDTH - msg.length() * 8) / 2;
		int yy = (HEIGHT - 8) / 2;
		int w = msg.length();
		int h = 1;

		screen.render(xx - 8, yy - 8, 0 + 13 * 32, Color.get(-1, 1, 5, 445), 0);
		screen.render(xx + w * 8, yy - 8, 0 + 13 * 32, Color.get(-1, 1, 5, 445), 1);
		screen.render(xx - 8, yy + 8, 0 + 13 * 32, Color.get(-1, 1, 5, 445), 2);
		screen.render(xx + w * 8, yy + 8, 0 + 13 * 32, Color.get(-1, 1, 5, 445), 3);
		for (int x = 0; x < w; x++) {
			screen.render(xx + x * 8, yy - 8, 1 + 13 * 32, Color.get(-1, 1, 5, 445), 0);
			screen.render(xx + x * 8, yy + 8, 1 + 13 * 32, Color.get(-1, 1, 5, 445), 2);
		}
		for (int y = 0; y < h; y++) {
			screen.render(xx - 8, yy + y * 8, 2 + 13 * 32, Color.get(-1, 1, 5, 445), 0);
			screen.render(xx + w * 8, yy + y * 8, 2 + 13 * 32, Color.get(-1, 1, 5, 445), 1);
		}

		if ((tickCount / 20) % 2 == 0) {
			Font.draw(msg, screen, xx, yy, Color.get(5, 333, 333, 333));
		} else {
			Font.draw(msg, screen, xx, yy, Color.get(5, 555, 555, 555));
		}
	}

	public void scheduleLevelChange(int dir) {
		pendingLevelChange = dir;
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	public void won() {
		wonTimer = 60 * 3;
		hasWon = true;
	}

	public void resizeScreen(int w, int h) {
		if (screen != null)
			screen.freeze();
		WIDTH = w;
		HEIGHT = h;
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		if (screen != null)
			screen.resize(WIDTH, HEIGHT);
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	public void componentResized(ComponentEvent e) {
		resizeScreen(getSize().width/SCALE, getSize().height/SCALE);
	}
	public void componentHidden(ComponentEvent e) {
	}
	public void componentShown(ComponentEvent e) {
	}
	public void componentMoved(ComponentEvent e) {
	}

	public void setBar(boolean bar) {
		hasBar = bar;
	}

	public int getBarColor() {
		if (hasBar)
			return 000;
		else
			return -1;
	}

}
