package menues;

import java.awt.*;
import javax.swing.*;
import menues.MenuHandler.menuState;
import towerDefence.Button;
import towerDefence.C;
import towerDefence.Button.*;

public class StartMenu implements ButtonListener{
	private static Image SpowerText = new ImageIcon("res/spowerText.png").getImage();
	private static Image playButtonImage = new ImageIcon("res/playButton.png").getImage();
	private static Image editorButtonImage = new ImageIcon("res/editorButton.png").getImage();
	private static Image creditsButtonImage = new ImageIcon("res/creditsButton.png").getImage();
	
	private static final int xOnPress = C.xScreenPos(5), yOnPress = C.yScreenPos(-5);
	public static final int PLAY_BUTTON_ID = 1, EDITOR_BUTTON_ID = 2, CREDITS_BUTTON_ID = 3;
	
	private Button playButton = new Button(C.xScreenPos(548), C.xScreenPos(1372), C.yScreenPos(520), C.yScreenPos(615),	playButtonImage, xOnPress, yOnPress, PLAY_BUTTON_ID, this);
	private Button editorButton = new Button(C.xScreenPos(548), C.xScreenPos(1372), C.yScreenPos(646), C.yScreenPos(741),	editorButtonImage, xOnPress, yOnPress, EDITOR_BUTTON_ID, this);
	private Button creditsButton = new Button(C.xScreenPos(548), C.xScreenPos(1372), C.yScreenPos(771), C.yScreenPos(866),	creditsButtonImage, xOnPress, yOnPress, CREDITS_BUTTON_ID, this);
	
	private Button[] buttonList = {playButton, editorButton, creditsButton};
	
	MenuHandler menuHandler;
	
	public StartMenu(MenuHandler menuHandler) {
		this.menuHandler = menuHandler;
	}
	
	protected void render(Graphics g) {
		g.drawImage(SpowerText, 0, -100, 1920, 1080, null);
		
		for(int i = 0; i<buttonList.length; i++) {
			buttonList[i].render(g);
		}
	}
	
	
	protected Button[] getButtonList() {
		return buttonList;
	}
	
	@Override
	public void buttonReleased(int id) {
			if(id == PLAY_BUTTON_ID) 
				menuHandler.setMenuState(menuState.mapSelectorGame);
			if(id == EDITOR_BUTTON_ID) 
				menuHandler.setMenuState(menuState.mapSelectorEditor);
			
				
				
	}
	
	
	
	
}
