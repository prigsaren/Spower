package towerDefence;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;
import menues.*;
import objects.*;

public class Game extends JPanel implements Runnable{
	private static final long serialVersionUID = -3861007013616873859L;

	MenuHandler menuHandler = new MenuHandler(this);
	Map map = new Map();
	Hud hud = new Hud();
	Handler handler = new Handler();
	Editor editor = new Editor();
	KeyInput keyInput = new KeyInput(this, editor);
	Window window = new Window(this, keyInput);
	
	Enemy[] Enemy = new Enemy[1000];
	
	private int round = 1;
	private int spawnRate = 1000;
	private long lastTime = 0;
	private int basicEnemies = 0;
	
	private boolean spawn = false;
	
	LinkedList<MapSelector> mapSelector;
	
	public enum gameState{
		Menu(),
		game(),
		pauseMenu(),
		editor(),
	}
	
	private gameState state = gameState.Menu;
	private gameState nextState;
	
	public static void main(String[]args) {
		new Game().game();
	}
	
	private void game() {
//		startMenu.setWindow(window);
		
		window.setButtons(menuHandler.getCurrentButtonList());
		Thread graphics = new Thread(this);
		graphics.start();
		
		loop();
	}


	
	public void paint(Graphics g) {
		super.paint(g);	
		if(state == gameState.game||state == gameState.pauseMenu) {
			map.render(g);
			handler.render(g);
			hud.render(g);
		}
		else if(state == gameState.Menu) {
			menuHandler.render(g);
		}
		
		else if(state == gameState.editor)
			editor.render(g);
	}
	
	private void tick() {
		if(state == gameState.game) {
			spawn();
//			mouse.canPlace();
			handler.tick();
		}
		else if(state == gameState.Menu) {
			menuHandler.tick();
		}
		
		else if(state == gameState.editor) {
			editor.tick();
		}
	}
	public void setButtons(Button[] buttons) {
		window.setButtons(buttons);
	}
	
	public void setGameState(gameState state){
		this.state = state;
	}
	public void setNextGameState(gameState nextState) {
		this.nextState = nextState;
		
	}
	
	public gameState getGameState() {
		return state;
	}
	public  gameState  getNextGameState() {
		return nextState;
	}
	
	public void setMap(String mapName) {
		map.makeMap(mapName);
	}
	
	public void reset() {
		hud.reset();
		handler.reset();
		round = 1;
	}
	
	private void spawn(){
		
		if(round <= 100 && basicEnemies < 1 * round * 2 && spawn) {
			if(System.currentTimeMillis() >=  lastTime + spawnRate) {
				Enemy[basicEnemies] = new BasicEnemy(map.getStartPos(), map.getPath(), handler, hud);
				handler.addObject(Enemy[basicEnemies]);
				basicEnemies ++;
				lastTime = System.currentTimeMillis();
			}
		}
		
		if(basicEnemies == round*2) {
			boolean restart = true;
			
			for(int i = 0; i<basicEnemies && restart; i++) {
				if(!Enemy[i].isDone()) {
					restart = false;
				}
			}
			if(restart) {
				basicEnemies = 0;
				round++;
				spawn = false;
			}
		}
		
	}
	public void startSpawn() {
		spawn = true;
	}
	
	public void run(){	
		double maxFps = 1000/100;
		long startTime = System.currentTimeMillis();

		while(true) {
			long now = System.currentTimeMillis();
			if(now - startTime >= maxFps) {
				startTime = System.currentTimeMillis();
				
				repaint();
			}
			else {
				try {
					Thread.sleep((long)maxFps + startTime - now);
				} catch (InterruptedException e) {}
			}
			
		}
	}
	
	
	private void loop() {
		double updates = 1000/1000;
		long startTime = System.currentTimeMillis();
		
		while(true) {
			long now = System.currentTimeMillis();
			if(now - startTime >= updates) {
				startTime = System.currentTimeMillis();
				
				tick();
			}
			else {
				try {
					Thread.sleep((long)updates + startTime - now);
				} catch (InterruptedException e) {}
			}

	}
	}
}
