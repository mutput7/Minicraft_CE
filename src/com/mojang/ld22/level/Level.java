package com.mojang.ld22.level;

import java.util.*;

import com.mojang.ld22.entity.*;
import com.mojang.ld22.entity.particle.*;
import com.mojang.ld22.gfx.Screen;
import com.mojang.ld22.level.levelgen.LevelGen;
import com.mojang.ld22.level.tile.Tile;
import com.mojang.ld22.Dumpable;

public class Level extends Dumpable {
	private Random random = new Random();

	public int w, h;

	public byte[] tiles;
	public byte[] data;
	public List<Entity>[] entitiesInTiles;

	public int grassColor = 141;
	public int dirtColor = 322;
	public int sandColor = 550;
	private int depth;
	public int monsterDensity = 8;

	public List<Entity> entities = new ArrayList<Entity>();
	private Comparator<Entity> spriteSorter = new Comparator<Entity>() {
		public int compare(Entity e0, Entity e1) {
			if (e1.y < e0.y) return +1;
			if (e1.y > e0.y) return -1;
			return 0;
		}

	};

	@SuppressWarnings("unchecked")
	public Level(int w, int h, int level, Level parentLevel) {
		if (level < 0) {
			dirtColor = 222;
		}
		this.depth = level;
		this.w = w;
		this.h = h;
		byte[][] maps;

		if (level == 1) {
			dirtColor = 444;
		}
		if (level == 0)
			maps = LevelGen.createAndValidateTopMap(w, h);
		else if (level < 0) {
			maps = LevelGen.createAndValidateUndergroundMap(w, h, -level);
			monsterDensity = 4;
		} else {
			maps = LevelGen.createAndValidateSkyMap(w, h); // Sky level
			monsterDensity = 4;
		}

		tiles = maps[0];
		data = maps[1];

		if (parentLevel != null) {
			for (int y = 0; y < h; y++)
				for (int x = 0; x < w; x++) {
					if (parentLevel.getTile(x, y) == Tile.stairsDown) {

						setTile(x, y, Tile.stairsUp, 0);
						if (level == 0) {
							setTile(x - 1, y, Tile.hardRock, 0);
							setTile(x + 1, y, Tile.hardRock, 0);
							setTile(x, y - 1, Tile.hardRock, 0);
							setTile(x, y + 1, Tile.hardRock, 0);
							setTile(x - 1, y - 1, Tile.hardRock, 0);
							setTile(x - 1, y + 1, Tile.hardRock, 0);
							setTile(x + 1, y - 1, Tile.hardRock, 0);
							setTile(x + 1, y + 1, Tile.hardRock, 0);
						} else {
							setTile(x - 1, y, Tile.dirt, 0);
							setTile(x + 1, y, Tile.dirt, 0);
							setTile(x, y - 1, Tile.dirt, 0);
							setTile(x, y + 1, Tile.dirt, 0);
							setTile(x - 1, y - 1, Tile.dirt, 0);
							setTile(x - 1, y + 1, Tile.dirt, 0);
							setTile(x + 1, y - 1, Tile.dirt, 0);
							setTile(x + 1, y + 1, Tile.dirt, 0);
						}
					}

				}
		}

		entitiesInTiles = new ArrayList[w * h];
		for (int i = 0; i < w * h; i++) {
			entitiesInTiles[i] = new ArrayList<Entity>();
		}

		if (level==1) {
			AirWizard aw = new AirWizard(1);
			aw.x = w*8;
			aw.y = h*8;
			add(aw);
		}
	}

	public void renderBackground(Screen screen, int xScroll, int yScroll) {
		int xo = xScroll >> 4;
		int yo = yScroll >> 4;
		int w = (screen.w + 15) >> 4;
		int h = (screen.h + 15) >> 4;
		screen.setOffset(xScroll, yScroll);
		for (int y = yo; y <= h + yo; y++) {
			for (int x = xo; x <= w + xo; x++) {
				getTile(x, y).render(screen, this, x, y);
			}
		}
		screen.setOffset(0, 0);
	}

	private List<Entity> rowSprites = new ArrayList<Entity>();

	public Player player;

	public void renderSprites(Screen screen, int xScroll, int yScroll) {
		int xo = xScroll >> 4;
		int yo = yScroll >> 4;
		int w = (screen.w + 15) >> 4;
		int h = (screen.h + 15) >> 4;

		screen.setOffset(xScroll, yScroll);
		for (int y = yo; y <= h + yo; y++) {
			for (int x = xo; x <= w + xo; x++) {
				if (x < 0 || y < 0 || x >= this.w || y >= this.h) continue;
				rowSprites.addAll(entitiesInTiles[x + y * this.w]);
			}
			if (rowSprites.size() > 0) {
				sortAndRender(screen, rowSprites);
			}
			rowSprites.clear();
		}
		screen.setOffset(0, 0);
	}

	public void renderLight(Screen screen, int xScroll, int yScroll) {
		int xo = xScroll >> 4;
		int yo = yScroll >> 4;
		int w = (screen.w + 15) >> 4;
		int h = (screen.h + 15) >> 4;

		screen.setOffset(xScroll, yScroll);
		int r = 4;
		for (int y = yo - r; y <= h + yo + r; y++) {
			for (int x = xo - r; x <= w + xo + r; x++) {
				if (x < 0 || y < 0 || x >= this.w || y >= this.h) continue;
				List<Entity> entities = entitiesInTiles[x + y * this.w];
				for (int i = 0; i < entities.size(); i++) {
					Entity e = entities.get(i);
					// e.render(screen);
					int lr = e.getLightRadius();
					if (lr > 0) screen.renderLight(e.x - 1, e.y - 4, lr * 8);
				}
				int lr = getTile(x, y).getLightRadius(this, x, y);
				if (lr > 0) screen.renderLight(x * 16 + 8, y * 16 + 8, lr * 8);
			}
		}
		screen.setOffset(0, 0);
	}

	private void sortAndRender(Screen screen, List<Entity> list) {
		Collections.sort(list, spriteSorter);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).render(screen);
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= w || y >= h) return Tile.rock;
		return Tile.tiles[tiles[x + y * w]];
	}

	public void setTile(int x, int y, Tile t, int dataVal) {
		if (x < 0 || y < 0 || x >= w || y >= h) return;
		tiles[x + y * w] = t.id;
		data[x + y * w] = (byte) dataVal;
	}

	public int getData(int x, int y) {
		if (x < 0 || y < 0 || x >= w || y >= h) return 0;
		return data[x + y * w] & 0xff;
	}

	public void setData(int x, int y, int val) {
		if (x < 0 || y < 0 || x >= w || y >= h) return;
		data[x + y * w] = (byte) val;
	}

	public void add(Entity entity) {
		if (entity instanceof Player) {
			player = (Player) entity;
		}
		entity.removed = false;
		entities.add(entity);
		entity.init(this);

		insertEntity(entity.x >> 4, entity.y >> 4, entity);
	}

	public void remove(Entity e) {
		entities.remove(e);
		int xto = e.x >> 4;
		int yto = e.y >> 4;
		removeEntity(xto, yto, e);
	}

	private void insertEntity(int x, int y, Entity e) {
		if (x < 0 || y < 0 || x >= w || y >= h) return;
		entitiesInTiles[x + y * w].add(e);
	}

	private void removeEntity(int x, int y, Entity e) {
		if (x < 0 || y < 0 || x >= w || y >= h) return;
		entitiesInTiles[x + y * w].remove(e);
	}

	public void trySpawn(int count) {
		for (int i = 0; i < count; i++) {
			Mob mob;

			int minLevel = 1;
			int maxLevel = 1;
			if (depth < 0) {
				maxLevel = (-depth) + 1;
			}
			if (depth > 0) {
				minLevel = maxLevel = 4;
			}

			int lvl = random.nextInt(maxLevel - minLevel + 1) + minLevel;
			if (random.nextInt(2) == 0)
				mob = new Slime(lvl);
			else
				mob = new Zombie(lvl);

			if (mob.findStartPos(this)) {
				this.add(mob);
			}
		}
	}

	public void tick() {
		trySpawn(1);

		for (int i = 0; i < w * h / 50; i++) {
			int xt = random.nextInt(w);
			int yt = random.nextInt(h);
			getTile(xt, yt).tick(this, xt, yt);
		}
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			int xto = e.x >> 4;
			int yto = e.y >> 4;

			e.tick();

			if (e.removed) {
				entities.remove(i--);
				removeEntity(xto, yto, e);
			} else {
				int xt = e.x >> 4;
				int yt = e.y >> 4;

				if (xto != xt || yto != yt) {
					removeEntity(xto, yto, e);
					insertEntity(xt, yt, e);
				}
			}
		}
	}

	public List<Entity> getEntities(int x0, int y0, int x1, int y1) {
		List<Entity> result = new ArrayList<Entity>();
		int xt0 = (x0 >> 4) - 1;
		int yt0 = (y0 >> 4) - 1;
		int xt1 = (x1 >> 4) + 1;
		int yt1 = (y1 >> 4) + 1;
		for (int y = yt0; y <= yt1; y++) {
			for (int x = xt0; x <= xt1; x++) {
				if (x < 0 || y < 0 || x >= w || y >= h) continue;
				List<Entity> entities = entitiesInTiles[x + y * this.w];
				for (int i = 0; i < entities.size(); i++) {
					Entity e = entities.get(i);
					if (e.intersects(x0, y0, x1, y1)) result.add(e);
				}
			}
		}
		return result;
	}

	public void saveTo(StringBuffer str) {
		super.saveTo(str);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				str.append((int)tiles[x+y*w] + " ");
			}
		}
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				str.append((int)data[x+y*w] + " ");
			}
		}
		int size = entities.size();
		if (entities.contains(player)) size--;
		str.append(size + " ");
		for (Entity e : entities) {
			if (!(e instanceof Player))
			{
				str.append(e.getClass().getSimpleName() + " ");
				e.saveTo(str);
			}
		}
	}

	public void loadFrom(StringTokenizer st) {
		super.loadFrom(st);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				tiles[x+y*w] = (byte)Integer.parseInt(st.nextToken());
			}
		}
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				data[x+y*w] = (byte)Integer.parseInt(st.nextToken());
			}
		}
		int size = Integer.parseInt(st.nextToken());
		System.out.println("Loading " + size + " entities");
		for (int i = 0; i < size; i++) {
			String cl = null;
			try {
				cl = nextString(st);
			} catch (Exception ex) {
				System.out.println("[Level] Error at: " + i);
				ex.printStackTrace();
				continue;
			}
			Entity e = null;
			if(cl.equals("Zombie")) {
				e = new Zombie(0);
			} else if (cl.equals("Slime")) {
				e = new Slime(0);
			} else if (cl.equals("AirWizard")) {
				e = new AirWizard(0);
			} else if (cl.equals("Mob")) {
				e = new Mob(0);
			} else if (cl.equals("Entity")) {
				e = new Entity();
			} else if (cl.equals("Oven")) {
				e = new Oven();
			} else if (cl.equals("Furnace")) {
				e = new Furnace();
			} else if (cl.equals("Anvil")) {
				e = new Anvil();
			} else if (cl.equals("Chest")) {
				e = new Chest();
			} else if (cl.equals("Spark")) {
				e = new Spark(null, 0, 0); // !!!
			} else if (cl.equals("Workbench")) {
				e = new Workbench();
			} else if (cl.equals("Furniture")) {
				e = new Furniture("name");
			} else if (cl.equals("Lantern")) {
				e = new Lantern();
			} else if (cl.equals("Spark")) {
				//e = new Spark(null, 0, 0); // don't load spark
			} else if (cl.equals("ItemEntity")) {
				e = new ItemEntity(null, 0, 0); // !!!
			} else if (cl.equals("TextParticle")) {
				e = new TextParticle("test", 0, 0, 0); // !!!
			} else if (cl.equals("SmashParticle")) {
				e = new SmashParticle(0, 0);
			} else if (cl.equals("Particle")) {
				e = new Particle();
			} else if (cl.equals("ItemEntity")) {
				e = new ItemEntity(null, 0, 0);
			} else {
				System.out.println("[Level] Unknow entity : "+cl);
			}
			if (e != null)
			{
				try {
					e.loadFrom(st);
				} catch (Exception ex) {
					System.out.println("[Level] Error loading entity: "+cl);
					ex.printStackTrace();
				}
				add(e);
			}
		}
	}

}
