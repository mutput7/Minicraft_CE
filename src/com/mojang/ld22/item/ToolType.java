package com.mojang.ld22.item;

import java.util.*;

import com.mojang.ld22.Dumpable;

public class ToolType {
	public static ToolType shovel = new ToolType("Shvl", 0);
	public static ToolType hoe = new ToolType("Hoe", 1);
	public static ToolType sword = new ToolType("Sword", 2);
	public static ToolType pickaxe = new ToolType("Pick", 3);
	public static ToolType axe = new ToolType("Axe", 4);
	public static ToolType wand = new ToolType("Wand", 4);

	public String name;
	public int sprite;

	protected ToolType(String name, int sprite) {
		this.name = name;
		this.sprite = sprite;
	}

	static public ToolType get(String name) {
		if (name.equals("Shvl")) return shovel;
		if (name.equals("Hoe")) return hoe;
		if (name.equals("Sword")) return sword;
		if (name.equals("Pick")) return pickaxe;
		if (name.equals("Axe")) return axe;
		if (name.equals("Wand")) return wand;
		System.out.println("Fail to give a tooltype: "+name);
		return null;
	}

}
