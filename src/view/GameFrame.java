package view;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JFrame;

import controller.GameController;
import model.Block;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static final String TITLE = "Snake";
	public static final int BLOCK_WIDTH = 30; // Window width using block unity
	public static final int BLOCK_HEIGHT = 30; // Window height using block unity
	public static final int PXL_WIDTH = BLOCK_WIDTH * Block.SIZE; // Window width in pixels
	public static final int PXL_HEIGHT = BLOCK_HEIGHT * Block.SIZE; // Window height in pixels
	private static final int OFFSET = 23; // The height of the windows count the top bar
	
	private GameController controler;
	private GamePanel panel;
	
	public GameFrame() {
		super(TITLE);
		controler = new GameController();
		panel = new GamePanel(controler);
		controler.addObserver(panel);
		
		setSize(PXL_WIDTH, PXL_HEIGHT + OFFSET);	
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("snake.png"));
		setResizable(false);	
		setContentPane(panel);
		setBackground(Color.DARK_GRAY);
		setVisible(true);
		addKeyListener(controler);
		
		// Run the game controller
		controler.run();
	}
	
}
