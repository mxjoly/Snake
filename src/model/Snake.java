package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import controller.GameController;
import view.GameFrame;

public class Snake {
	
	private Color SNAKE_COLOR = Color.GREEN;
	
	private LinkedList<Block> body = new LinkedList<Block>();
	private int size;
	
	// Position of the head of the snake
	private int x;
	private int y;
	private int xSpeed = 0;
	private int ySpeed = -1;
	
	public Snake(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		// To adjust the size of snake at the beginning
		for (int i = 0 ; i < size ; i ++) {
			body.add(new Block(x, y + i * Block.SIZE));
		}
	}
	
	/**
	 * Move according to the blocks
	 */
	public void move(GameController controler) {
		// Move according to the scale of block size
		x += xSpeed * Block.SIZE;
		y += ySpeed * Block.SIZE;
		
		// The map is a globe
		if (x < 0) 						
			x = GameFrame.PXL_WIDTH - Block.SIZE;
		if (x >= GameFrame.PXL_WIDTH) 	
			x = 0;		
		if (y < 0) 						
			y = GameFrame.PXL_HEIGHT - Block.SIZE;
		if (y >= GameFrame.PXL_HEIGHT) 	
			y = 0;
		
		// Verify if the snake don't collide himself
		for (Block b : body) {
			if (x == b.getX() && y == b.getRow())
				controler.stop();
		}
		
		// Add a new block where the snake goes on
		body.addFirst(new Block(x, y));
		
		// If the snake has grown, we keep the last block of the body
		if (body.size() > size) 
			body.removeLast();
	}
	
	/**
	 * Increment the size
	 */
	public void grow() {
		size++;
	}
	
	/**
	 * Draw the snake on the frame
	 * @param g
	 */
	public void render(Graphics g) {
		for (Block b : body) {
			g.setColor(SNAKE_COLOR);
			b.render(g);
		}
	}
	
	public void setSpeed(int xSpeed, int ySpeed) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
