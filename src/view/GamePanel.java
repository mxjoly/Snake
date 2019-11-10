package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameController;

public class GamePanel extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private static final String INIT_MSG = "Press enter to start";
	private static final String GAME_OVER_MSG = "Game Over";
	
	private Font fontMsg = new Font("Cambria", Font.BOLD, 40);
	private JLabel msg = new JLabel("Press enter to start"); // initial text
	
	private GameController controler;
	private int score = 0;
	
	public GamePanel(GameController controler) {
		this.controler = controler;
		setLayout(new BorderLayout());
		setBackground(Color.DARK_GRAY);
		msg.setFont(fontMsg);
		msg.setForeground(Color.WHITE);
		msg.setHorizontalAlignment(JLabel.CENTER);
		add(msg, BorderLayout.CENTER);
	}

	@Override
	protected void paintComponent(Graphics g) {
		controler.render(g);
		g.setColor(Color.WHITE);
		g.drawString("Score : " + score, 15, 20);
	}

	@Override
	public void update(Observable o, Object arg) {
		int code = (int) arg;
		updateMessage(code);
		switch(code) {
			case GameController.SCORE : score++; break;
			case GameController.REPAINT : repaint(); break;
			case GameController.START : hideMessage(); break;
			case GameController.STOP : showMessage(); break;
			default : break;
		}
	}
	
	public void updateMessage(int code) {
		switch(code) {
			case GameController.START : msg.setText(INIT_MSG); break;
			case GameController.STOP : msg.setText(GAME_OVER_MSG);; break;
			default : break;
		}
	}
	
	public void showMessage() {
		msg.setVisible(true);
		repaint();
	}
	
	public void hideMessage() {
		msg.setVisible(false);
		repaint();
	}

}
