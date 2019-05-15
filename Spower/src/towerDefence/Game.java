package towerDefence;

import java.awt.*;
import java.io.File;
import java.util.LinkedList;

import javax.swing.*;
import objects.*;

public class Game extends JPanel implements Runnable{
	private static final long serialVersionUID = -3861007013616873859L;

	StartMenu startMenu = new StartMenu(this);
	Map map = new Map();
	Hud hud = new Hud();
	Handler handler = new Handler();
	Editor editor = new Editor();
	KeyInput keyInput = new KeyInput(this, editor);
	Mouse mouse = new Mouse(hud, map, handler, this, startMenu, editor);
	Window window = new Window(this, mouse, keyInput);
	
	Enemy[] Enemy = new Enemy[1000];
	
	private int round = 1;
	private int spawnRate = 1000;
	private long lastTime = 0;;
	private int basicEnemies = 0;
	
	private boolean spawn = false;
	
	public enum gameState{
		startMenu(),
		game(),
		pauseMenu(),
		editor(),
		mapSelector();
	}
	
	private gameState state = gameState.startMenu;
	private gameState nextState;
	
	public static void main(String[]args) {
		Game game = new Game();
		game.game();
	}
	
	private void game() {
		mouse.setWindow(window);
		startMenu.setWindow(window);
		
		Thread graphics = new Thread(this);
		graphics.start();
		
		loop();
	}
	
	public void findMaps() {

		startMenu.clearMapSelectorComponentList();
		
		File folder = new File("Maps");
		File[] listOfFiles = folder.listFiles();
		LinkedList<String> tempList = new LinkedList<>();
		
		for(File file : listOfFiles) {
			if(file.isFile() && file.getName().contains(".txt")) 
				tempList.add(file.getName());
		}
		
		for(int i2 = 0; i2 < tempList.size(); i2++) {
			long time = 0;
			int nr = 0;
			
			for(int i = 0; i < tempList.size(); i++) {
				File file = new File("Maps\\" + tempList.get(i));
				
				if(file.lastModified() > time || time == 0) {
					nr = i;
					time = file.lastModified();
				}
			}
			startMenu.addComponentToSelector(tempList.get(nr));
			tempList.remove(nr);
			i2--;
		
		}
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);	
		if(state == gameState.game||state == gameState.pauseMenu) {
			map.render(g);
			handler.render(g);
			hud.render(g);
		}
		else if(state == gameState.startMenu || state == gameState.mapSelector) {
			startMenu.render(g);
		}
		else if(state == gameState.editor)
			editor.render(g);
	}
	
	private void tick() {
		if(state == gameState.game) {
			spawn();
			mouse.canPlace();
			handler.tick();
		}
		else if(state == gameState.startMenu||state == gameState.mapSelector) {
			startMenu.tick();
		}
		
		else if(state == gameState.editor) {
			editor.tick();
		}
	}
	public void setGameState(gameState state){
		this.state = state;
		if (state == gameState.mapSelector) {
			findMaps();
		}
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
