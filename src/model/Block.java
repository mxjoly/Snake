package model;

import java.awt.Color;
import java.awt.Graphics;

public class Block {
	
	// Width and height
	public static final int SIZE = 20;
	
	private int x;
	private int y;
	
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g) {
		Color c = g.getColor();
		g.fillRect(x, y, SIZE, SIZE);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE, SIZE);
		g.setColor(c);
	}
	
	public int getX() {
		return x;
	}

	public int getRow() {
		return y;
	}
	
}
