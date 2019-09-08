package towerDefence;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame implements MouseListener{
	private static final long serialVersionUID = 6377519624842151441L;
	
	public static final int WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	private Button[] button;
	private int buttonNrPressed = -1;
	
//	private Color backGround = new Color(86,84,91);
	
	public Window(Game game, KeyInput keyInput) {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Tower Defence game");
		setExtendedState(MAXIMIZED_BOTH);
		setSize(WIDTH, HEIGHT);
		addMouseListener(this);
		addKeyListener(keyInput);
		setContentPane(game);
		setUndecorated(true);
		getContentPane().setBackground(Color.DARK_GRAY);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(int i = 0; button != null && i < button.length; i++) {
			if(button[i].isPressed(e.getX(), e.getY())) {
				buttonNrPressed = i;
				i = button.length;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(buttonNrPressed != -1 && !button[buttonNrPressed].isReleasedOn(e.getX(), e.getY())) {
			buttonNrPressed = -1;
		}
		
	}
	
	public void setButtons(Button[] button) {
		this.button = button;
	}

	
}
