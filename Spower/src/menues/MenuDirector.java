package menues;

import java.awt.*;
import javax.swing.ImageIcon;
import towerDefence.*;
import towerDefence.Window;

public class MenuDirector {
	
	int x = 0;
	double xDouble = 0;
	double addX = 0.2;
	
	private Image stars = new ImageIcon("Graphics\\stars.png").getImage();
	
	private enum gameState{
		startMenu(),
		editorMapSelector(),
		gameMapSelector(),	
	}
	
	private gameState stateOfGame = gameState.startMenu;
	
	private MapSelector mapSelector = new MapSelector();
	private StartMenu startMenu = new StartMenu();
	private Game game;
	
	public MenuDirector(Game game) {
		
		this.game = game;
	}
	
	public void render(Graphics g) {
		
		g.drawImage(stars, x, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT, null);
		g.drawImage(stars, x + Window.WINDOW_WIDTH, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT, null);
		
		
		if(stateOfGame == gameState.startMenu)
			startMenu.render(g);
			
		else
			mapSelector.render(g, (int)(Math.round(Window.WINDOW_WIDTH- Window.WINDOW_WIDTH*(double)1026/1920)/2), (int)(Math.round(Window.WINDOW_HEIGHT- Window.WINDOW_HEIGHT*(double)604/1080)/2), (int)(Math.round(Window.WINDOW_WIDTH*(double)1026/1920)), (int)(Math.round(Window.WINDOW_HEIGHT*(double)604/1080)));
		
	}
	
	public void tick() {
		
		if( x<= - Window.WINDOW_WIDTH)
			x = 0;
		
		xDouble += addX;
		
		if(xDouble >= 1) {
			x -= (int) xDouble;
			xDouble -= (int)xDouble;
		}
	}
	
	public void buttonPressed(int mouseX, int mouseY) {
		if(stateOfGame == gameState.startMenu)
			startMenu.mousePressed(mouseX, mouseY);
		
		else
			mapSelector.mousePressed(mouseX, mouseY);
	}
	
	public void buttonReleased(int mouseX, int mouseY) {
		if(stateOfGame == gameState.startMenu) {
			
			switch(startMenu.mouseReleased(mouseX, mouseY)) {
			
				case StartMenu.FIRST_BUTTON:
					stateOfGame = gameState.gameMapSelector;
					mapSelector.findMaps();
					System.out.println("first button");
					break;
					
				case StartMenu.SECOND_BUTTON:
					stateOfGame = gameState.editorMapSelector;
					mapSelector.findMaps();
					break;
					
//				case StartMenu.THIRD_BUTTON:
					
			}
		}
		else 
			switch(mapSelector.mouseReleased(mouseX, mouseY)) {

				case MapSelector.LEFT:
					mapSelector.decreaseSlide();
					break;
					
				case MapSelector.RIGHT:
					mapSelector.increaseSlide();
					break;
				
				case MapSelector.NONE:
					break;
			
				case MapSelector.NEW:
					game.setGameState(Game.gameState.editor);
					break;
					
				case MapSelector.BACK:
					stateOfGame = gameState.startMenu;
					break;
					
				default:
					if (mapSelector.getMap(mapSelector.mouseReleased(mouseX, mouseY) -4) != null) {
						if( stateOfGame == gameState.editorMapSelector) {
							stateOfGame = gameState.startMenu;
							game.setGameState(Game.gameState.editor);
							game.setEditorMap(mapSelector.getMap(mapSelector.mouseReleased(mouseX, mouseY) -4));
										
						}
						
						else if(stateOfGame == gameState.gameMapSelector) {
							game.setGameState(Game.gameState.game);
//							game.setG
						}
		}
	}
	
}
}
