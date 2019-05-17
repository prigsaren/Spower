package menues;

import tools.Button;
import java.awt.*;
import javax.swing.*;


public class StartMenu extends Menu{
	
	
	private final Button playButton = new Button((int)(559.0/1920*WINDOW_WIDTH), (int)(545.0/1080*WINDOW_HEIGHT), (int)(1361.0/1920*WINDOW_WIDTH), (int)(615.0/1080*WINDOW_HEIGHT), FIRST_BUTTON);
	private final Button editorButton = new Button((int)(559.0/1920*WINDOW_WIDTH), (int)(671.0/1080*WINDOW_HEIGHT), (int)(1361.0/1920*WINDOW_WIDTH), (int)(741.0/1080*WINDOW_HEIGHT), SECOND_BUTTON);
	private final Button creditsButton = new Button((int)(559.0/1920*WINDOW_WIDTH), (int)(796.0/1080*WINDOW_HEIGHT), (int)(1361.0/1920*WINDOW_WIDTH), (int)(866.0/1080*WINDOW_HEIGHT), THIRD_BUTTON);
	
	
	private Button[] buttons = {playButton, editorButton, creditsButton};
	
	private Image deafult = new ImageIcon("Graphics\\titleScreen_deafult.png").getImage();
	private Image playPressed = new ImageIcon("Graphics\\titleScreen_play.png").getImage();
	private Image editorPressed = new ImageIcon("Graphics\\titleScreen_Editor.png").getImage();
	private Image creditsPressed = new ImageIcon("Graphics\\titleScreen_Credits.png").getImage();
	
	
	public StartMenu() {
//		System.out.println((559.0/1920);
		super.setButtons(buttons);
	}
	
	public void render(Graphics g) {
		
			if(pressed == NONE)
				g.drawImage(deafult, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);
			
			else if(pressed == FIRST_BUTTON)
				g.drawImage(playPressed, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);
			
			else if(pressed == SECOND_BUTTON)
				g.drawImage(editorPressed, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);
			
			else if(pressed == THIRD_BUTTON)
				g.drawImage(creditsPressed, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);
			
		}

}
