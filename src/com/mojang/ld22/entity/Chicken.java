package com.mojang.ld22.entity;

import com.mojang.ld22.gfx.Color;
import com.mojang.ld22.gfx.Screen;
import com.mojang.ld22.item.ResourceItem;
import com.mojang.ld22.item.resource.Resource;

public class Chicken extends Mob {
	private int xa, ya;
	private int fleeTime = 0;

	public Chicken(int lvl) {
		super(lvl);
		x = random.nextInt(64 * 16);
		y = random.nextInt(64 * 16);
		health = maxHealth = lvl * lvl * 10;
	}

	public void tick() {
		super.tick();

		if (fleeTime > 0) {
			int speed = random.nextInt(1) + 1;
			if (fleeTime < 20 && random.nextInt(50) == 0) {
				xa = xa * (random.nextInt(3)-1);
				ya = ya * (random.nextInt(3)-1);
			}
			move(xa * speed, ya * speed);
			fleeTime--;
		} else {
			int speed = tickTime & 1;
			if (!move(xa * speed, ya * speed) || random.nextInt(100) == 0) {
				xa = (random.nextInt(3) - 1) * random.nextInt(2);
				ya = (random.nextInt(3) - 1) * random.nextInt(2);
			}
		}
	}

	public void render(Screen screen) {
		int xt = 0;
		int yt = 20;

		int flip1 = (walkDist >> 3) & 1;
		int flip2 = (walkDist >> 3) & 1;

		if (dir == 1) {
			xt += 2;
		}
		if (dir > 1) {
			flip1 = 0;
			flip2 = ((walkDist >> 4) & 1);
			if (dir == 2) {
				flip1 = 1;
			}
			xt += 4;
			if ((walkDist / 8) % 2 == 1)
				xt += 2;
		}

		int xo = x - 8;
		int yo = y - 11;

		int col = Color.get(-1, 210, 540, 554);
		if (hurtTime > 0) {
			col = Color.get(-1, 555, 555, 555);
		}

		screen.render(xo + 8 * flip1, yo + 0, xt + yt * 32, col, flip1);
		screen.render(xo + 8 - 8 * flip1, yo + 0, xt + 1 + yt * 32, col, flip1);
		screen.render(xo + 8 * flip2, yo + 8, xt + (yt + 1) * 32, col, flip2);
		screen.render(xo + 8 - 8 * flip2, yo + 8, xt + 1 + (yt + 1) * 32, col, flip2);
	}

	protected void touchedBy(Entity entity) {
		flee(entity.x, entity.y);
	}

	public void hurt(Mob mob, int damage, int attackDir) {
		super.hurt(mob, damage, attackDir);
		flee(mob.x, mob.y);
	}

	private void flee(int sx, int sy) {
		int dx = x - sx;
		int dy = y - sy;
		xa = ya = 0;
		if (dx < 0)
			xa = -1;
		if (dx > 0)
			xa = 1;
		if (dy < 0)
			ya = -1;
		if (dy > 0)
			ya = 1;
		fleeTime = 30*(1+random.nextInt(1));
	}

	protected void die() {
		super.die();

		int count = random.nextInt(2) + 1;
		for (int i = 0; i < count; i++) {
			level.add(new ItemEntity(new ResourceItem(Resource.feather), x + random.nextInt(11) - 5, y + random.nextInt(11) - 5));
		}

		if (level.player != null) {
			level.player.score += 10 * lvl;
		}

	}

}

