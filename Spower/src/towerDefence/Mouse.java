package towerDefence;

import java.awt.event.*;
import java.util.LinkedList;
import menues.MenuDirector;
import objects.*;
import objects.GameObject.*;
import tools.Button;
import towerDefence.Game.gameState;


public class Mouse implements MouseListener{
	
	private MenuDirector menuDirector;
	private Hud hud;
	private Map map;
	private Handler handler;
	private Window window;
	private Game game;
	private Editor editor;
	
	private ID id;
	
	private int x, y, selected;
	
//	private int hudWidth = Window.WIDTH*2/17 + Window.WIDTH%17;
	private int blockSize = Window.WIDTH/17;
	
	private int startX, endX, startY, endY;
		
	public Mouse(Hud hud, Map map, MenuDirector menuDirector, Game game, Editor editor) {
		this.hud = hud;
		this.map = map;
		this.menuDirector = menuDirector;
		this.game = game;
		this.editor = editor;
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
		
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(game.getGameState() == gameState.editor)
				editor.pressed(e.getX(), e.getY());
				
				System.out.println(e.getX() +"   " + e.getY());
			
			if(game.getGameState() == gameState.menu)
				menuDirector.buttonPressed(e.getX(), e.getY());
		}
		else if(e.getButton() == MouseEvent.BUTTON2) {
			startX = e.getX();
			startY = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(game.getGameState() == gameState.menu)
				menuDirector.buttonReleased(e.getX(), e.getY());
		}
		else if(e.getButton() == MouseEvent.BUTTON2) {
			endX = e.getX();
			endY = e.getY();
			
			System.out.println("(int)(" + startX + "/1920.0 * WINDOW_WIDTH), (int)(" + startY + "/1080.0 * WINDOW_HEIGHT), (int)(" + endX + "/1920.0 * WINDOW_WIDTH), (int)(" + endY + "/1080.0 * WINDOW_HEIGHT)");
		}
	}
	
	public boolean canPlace() {
		boolean can = true;
		
		if(id != null) {
			LinkedList<GameObject> list = new LinkedList<>(handler.getGameObject());
			int posX =(int) Math.round(WindowMethods.getMouseX(window) - hud.getRealWidth(selected) * (double) (x - hud.getIconX(selected))/hud.getIconSize()); 
			int posY = (int) Math.round(WindowMethods.getMouseY(window) - hud.getRealHeight(selected) * (double) (y - hud.getIconY(selected))/hud.getIconSize());
			
			for(int i = 0; i < map.getDefencePoint().length && can; i++) {
				if(posX + hud.getRealWidth(selected)> map.getDefencePoint()[i].getX() && posX < map.getDefencePoint()[i].getX() + blockSize && posY + hud.getRealHeight(selected)> map.getDefencePoint()[i].getY() && posY < map.getDefencePoint()[i].getY() + blockSize) {
					can = false;
				}	
			}
			for(int i = 0; i < list.size(); i++) {
				if(posX < list.get(i).getX() + list.get(i).getWidth() && posX + hud.getRealWidth(selected) > list.get(i).getX() && posY < list.get(i).getY() + list.get(i).getHeight() && posY + hud.getRealHeight(selected) >list.get(i).getY()) {
					can = false;
			}
			
		}
		}
		hud.canPlace(can);
		return can;
	}
	
	public void setWindow(Window window) {
		this.window = window;
	}


}
