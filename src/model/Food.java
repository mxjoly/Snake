package model;

import java.awt.Color;
import java.awt.Graphics;

import view.GameFrame;

public class Food {
	
	private Color FOOD_COLOR = Color.RED;
	
	private Block block;
	private int x;
	private int y;
	
	public Food() {
		pickNewLocation();
		block = new Block(x, y);
	}
	
	/**
	 * Give a new random location to the food when it has been eaten by the snake
	 */
	public void pickNewLocation() {
		x = (int) (Math.random() * GameFrame.BLOCK_WIDTH) * Block.SIZE;
		y = (int) (Math.random() * GameFrame.BLOCK_HEIGHT) * Block.SIZE;
		block = new Block(x, y);
	}

	/**
	 * Draw the food on the frame
	 * @param g
	 */
	public void render(Graphics g) {
		g.setColor(FOOD_COLOR);
		block.render(g);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
