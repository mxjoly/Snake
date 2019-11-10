package controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

import model.Food;
import model.Snake;
import view.GameFrame;

public class GameController extends Observable implements Runnable, KeyListener {
	
	// To increment the score
	public static final int SCORE = 0;
	// To repaint the panel
	public static final int REPAINT = 1;
	// To start the game
	public static final int START = 2;
	// To display the end message
	public static final int STOP = 3;
	
	
	private boolean stop = true;
	
	private Food food;
	private Snake snake;
	
	public GameController() {}
	
	public void render(Graphics g) {
		if (food != null && snake != null) {
			food.render(g);
			snake.render(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) { }
	
	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP : 		snake.setSpeed(0, -1); break;
			case KeyEvent.VK_RIGHT : 	snake.setSpeed(1, 0); break;
			case KeyEvent.VK_DOWN :		snake.setSpeed(0, 1); break;
			case KeyEvent.VK_LEFT :		snake.setSpeed(-1, 0); break;
			case KeyEvent.VK_ENTER : 	start(); break;
			default : break;
		}
	}

	public void update() {
		snake.move(this);
		if (snake.getX() == food.getX() && snake.getY() == food.getY()) {
			food.pickNewLocation();
			snake.grow();
			// Send a message to the panel to increment the score
			setChanged();
			notifyObservers(SCORE);
		}
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				java.lang.Thread.sleep(100);
				while (!stop) {
					update();
					setChanged();
					notifyObservers(REPAINT);
					java.lang.Thread.sleep(50);
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void start() {
		stop = false;
		food = new Food();
		snake = new Snake(GameFrame.PXL_WIDTH/2, GameFrame.PXL_HEIGHT/2, 5);
		setChanged();
		notifyObservers(START);
	}
	
	public void stop() {
		stop = true;
		setChanged();
		notifyObservers(STOP);
	}
}
