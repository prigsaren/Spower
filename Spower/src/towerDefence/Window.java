package towerDefence;

import java.awt.*;
import javax.swing.*;

public class Window extends JFrame{
	private static final long serialVersionUID = 6377519624842151441L;
	
	public static final int WINDOW_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int WINDOW_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
//	private Color backGround = new Color(86,84,91);
	
	public Window(Game game, Mouse mouse, KeyInput keyInput) {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Tower Defence game");
		setExtendedState(MAXIMIZED_BOTH);
		setSize(WIDTH, HEIGHT);
		addMouseListener(mouse);
		addKeyListener(keyInput);
		setContentPane(game);
		setUndecorated(true);
		getContentPane().setBackground(Color.DARK_GRAY);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	
}
