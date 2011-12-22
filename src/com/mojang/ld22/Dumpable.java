package com.mojang.ld22;

import java.io.*;
import java.util.*;


public class Dumpable {
	public int nextInt(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}

	public String nextString(StringTokenizer st) {
		return st.nextToken().replace("_", " ");
	}

	public void load(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			StringTokenizer st = new StringTokenizer(reader.readLine());
			loadFrom(st);
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found : " + filename);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save(String filename) {
		try {
			FileOutputStream writer = new FileOutputStream(filename);
			StringBuffer str = new StringBuffer();
			saveTo(str);
			writer.write(str.toString().getBytes());
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found : " + filename);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//import java.util.*;
	public void loadFrom(StringTokenizer st) {
		//super.loadFrom(st);
	}

	public void saveTo(StringBuffer str) {
		//super.saveTo(str);
	}

}
