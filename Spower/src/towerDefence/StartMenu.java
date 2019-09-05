package towerDefence;

import java.awt.*;

import javax.swing.ImageIcon;

import towerDefence.Game.gameState;

public class StartMenu{
	
	public static final int NOTHING = 0;
	public static final int PLAY = 1;
	public static final int EDITOR = 2;
	public static final int CREDITS = 3;
	
	
	private Image deafult = new ImageIcon("res/titleScreen_deafult.png").getImage();
	private Image playPressed = new ImageIcon("res/titleScreen_play.png").getImage();
	private Image editorPressed = new ImageIcon("res/titleScreen_Editor.png").getImage();
	private Image creditsPressed = new ImageIcon("res/titleScreen_Credits.png").getImage();
	private Image stars = new ImageIcon("res/stars.png").getImage();
	
	private double xDouble = 0;
	private int x;
	private double addX = 0.2;
	
	private int pressed = NOTHING;
	
	private Window window;
	private Game game;
	private MapSelector mapSelector;
	
	public StartMenu(Game game) {
		this.game = game;
		mapSelector = new MapSelector();
	}
	
	public void setWindow(Window window) {
		this.window = window;
	}
	
	public void render(Graphics g) {
		g.drawImage(stars, x, 0, window.getWidth(), window.getHeight(), null);
		g.drawImage(stars, x + window.getWidth(), 0, window.getWidth(), window.getHeight(), null);
		
		if(game.getGameState() == gameState.startMenu) {
			if(pressed == NOTHING)
				g.drawImage(deafult, 0, 0, window.getWidth(), window.getHeight(), null);
			
			else if(pressed == PLAY)
				g.drawImage(playPressed, 0, 0, window.getWidth(), window.getHeight(), null);
			
			else if(pressed == EDITOR)
				g.drawImage(editorPressed, 0, 0, window.getWidth(), window.getHeight(), null);
			
			else if(pressed == CREDITS)
				g.drawImage(creditsPressed, 0, 0, window.getWidth(), window.getHeight(), null);
			;
		}
		else if(game.getGameState() == gameState.mapSelector) {
			mapSelector.render(g, (int)(Math.round(Window.WIDTH- Window.WIDTH*(double)1026/1920)/2), (int)(Math.round(Window.HEIGHT- Window.HEIGHT*(double)604/1080)/2), (int)(Math.round(Window.WIDTH*(double)1026/1920)), (int)(Math.round(Window.HEIGHT*(double)604/1080)));
			
		}
		}
	public void tick() {
		if( x<= - window.getWidth())
			x = 0;
		
		xDouble += addX;
		
		if(xDouble >= 1) {
			x -= (int) xDouble;
			xDouble -= (int)xDouble;
		}
	}
	public void setPressed(int pressed) {
		this.pressed = pressed;
	}
	public void forward() {
			mapSelector.forward();
	}
	public void backward() {
			mapSelector.backward();
	}
	public void setSelectorPressed(int pressed) {
		mapSelector.setPressed(pressed);
	}
	public void addComponentToSelector(String component) {
		mapSelector.addComponents(component);
	}
	public void clearMapSelectorComponentList() {
		mapSelector.clearList();
	}
	public String getMap(int nr){
		return mapSelector.getMap(nr);
	}

}
