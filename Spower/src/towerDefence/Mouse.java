package towerDefence;

import java.awt.event.*;
import java.util.LinkedList;

import objects.*;
import objects.GameObject.*;
import towerDefence.Game.gameState;


public class Mouse implements MouseListener{
	
	
	private Hud hud;
	private Map map;
	private Handler handler;
	private Window window;
	private Game game;
	private ID id;
	private StartMenu startMenu;
	private Editor editor;
	
	private int x, y, selected;
	
	private int hudWidth = Window.WIDTH*2/17 + Window.WIDTH%17;
	private int blockSize = Window.WIDTH/17;
	
	
	public Mouse(Hud hud, Map map, Handler handler, Game game, StartMenu startMenu, Editor editor) {
		this.hud = hud;
		this.map = map;
		this.handler = handler;
		this.game = game;
		this.startMenu = startMenu;
		this.editor = editor;
	}
	public void setWindow(Window window) {
		this.window = window;
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
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(game.getGameState() == gameState.game) {//--------------------------------------in game-----------------------------------------------------------------------------
			if(e.getButton() == MouseEvent.BUTTON1) {
					
					for(int i = 0; i < hud.getNumberOfIcons(); i++) {
						if(e.getX() >= hud.getIconX(i) && e.getX() <= hud.getIconX(i) + hud.getIconSize() && e.getY() >= hud.getIconY(i) && e.getY() <= hud.getIconY(i) + hud.getIconSize()) {
							selected = i;
							id = hud.getIconID(selected);
							x = e.getX();
							y = e.getY();
							hud.setSelected(selected, x, y, window);
							i = hud.getNumberOfIcons();
	
							
						}
					}
					
				}
				
		}
		else if(game.getGameState() == gameState.editor) {//-----------------------------------------------------------------editor--------------------------------------------------------------------------
			if(e.getButton() == MouseEvent.BUTTON1) {
				if(e.getX() > Window.WIDTH - hudWidth + (hudWidth-blockSize)/2 && e.getX() < Window.WIDTH-hudWidth + (hudWidth-blockSize)/2 + blockSize) {
					if(e.getY() > Window.HEIGHT * (double)200/1080 && e.getY() < Window.HEIGHT * (double)200/1080 + blockSize)
						editor.setSelectedType(Map.PATH);
					
					else if(e.getY() > Window.HEIGHT * (double)350/1080 && e.getY() < Window.HEIGHT * (double)350/1080 + blockSize)
						editor.setSelectedType(Map.NOTHING);
					
					else if(e.getY() > Window.HEIGHT * (double)500/1080 && e.getY() < Window.HEIGHT * (double)500/1080 + blockSize)
						editor.setSelectedType(Map.START);
				}
				
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(game.getGameState() == gameState.game) {//----------------------------------in game-----------------------------------------------------------
			
			if(e.getButton() == MouseEvent.BUTTON1) {
				if(e.getX() >= window.getWidth() - hudWidth + hudWidth*(double) 65/225 && e.getX() <= window.getWidth() + hudWidth * (-1 + (double)65/225 + (double)95/225) && e.getY() <= Window.HEIGHT - Window.HEIGHT*(double)20/1080 && e.getY() >= Window.HEIGHT - Window.HEIGHT*(double)82/1080) {
					hud.startPressed(true);
				}
			}
			
		}
		else if(game.getGameState() == gameState.startMenu) {//------------------------start menu---------------------------------------------------------
			if(e.getButton() == MouseEvent.BUTTON1 && e.getX() >= Window.WIDTH * (double)560/1920 && e.getX() < Window.WIDTH * ((double)560/1920 + (double)825/1920)) {
				System.out.println(e.getY());
				
				if(e.getY() >= Window.HEIGHT * (double)520/1080 && e.getY() <= Window.HEIGHT * (double)615/1080)
					startMenu.setPressed(StartMenu.PLAY);
				
				else if(e.getY() >= Window.HEIGHT * (double)645/1080 && e.getY() <= Window.HEIGHT * (double)741/1080)
					startMenu.setPressed(StartMenu.EDITOR);
				
				else if(e.getY() >= Window.HEIGHT * (double)770/1080 && e.getY() <= Window.HEIGHT * (double)866/1080)
					startMenu.setPressed(StartMenu.CREDITS);
			}
		}
		else if(game.getGameState() == gameState.editor) {//-------------------------------------editor---------------------------------------------
			if(e.getX() >= window.getWidth() - hudWidth + hudWidth*(double) 65/225 && e.getX() <= window.getWidth() + hudWidth * (-1 + (double)65/225 + (double)95/225) && e.getY() <= Window.HEIGHT - Window.HEIGHT*(double)20/1080 && e.getY() >= Window.HEIGHT - Window.HEIGHT*(double)82/1080) {
				editor.donePressed(true);
			}
		}
		else if(game.getGameState() == gameState.mapSelector) {//----------------------------------map selector---------------------------------------
			if(e.getButton() == MouseEvent.BUTTON1) {
				if(e.getY() >= (int)Math.round(Window.HEIGHT * 757/1080.0) && e.getY() <= (int)Math.round(Window.HEIGHT * 810/1080.0)){
					
					if(e.getX() >= (int)(Math.round(Window.WIDTH * 497/1920.0)) && e.getX() <= (int)(Math.round(Window.WIDTH * (591)/1920.0)))
						startMenu.setSelectorPressed(MapSelector.LEFT);
					
					else if(e.getX() >= (int)(Math.round(Window.WIDTH * (497 + 802)/1920.0)) && e.getX() <= (int)(Math.round(Window.WIDTH * (497 + 90 + 802)/1920.0))) {
						startMenu.setSelectorPressed(MapSelector.RIGHT);
						System.out.println(e.getX() + "  " + e.getY());
					}
				}
				else if(e.getY() >= (int)Math.round(Window.HEIGHT * 297/1080.0) && e.getY() <= (int)Math.round(Window.HEIGHT * 347/1080.0)){
					
					if(e.getX() >= (int)(Math.round(Window.WIDTH * 522/1920.0)) && e.getX() <= (int)(Math.round(Window.WIDTH * (522+65)/1920.0)))
						startMenu.setSelectorPressed(MapSelector.BACK);
					
					else if(e.getX() >= (int)(Math.round(Window.WIDTH * (522+65+730)/1920.0)) && e.getX() <= (int)(Math.round(Window.WIDTH * (522+65+730+58)/1920.0)) && game.getNextGameState() == gameState.editor)
						startMenu.setSelectorPressed(MapSelector.NEW);
					
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(game.getGameState() == gameState.game) {//----------------------------in game-------------------------------------------------------------------
			if(e.getButton() == MouseEvent.BUTTON1) {
				
				if(canPlace() && e.getX() < window.getWidth() - hudWidth && id != null) {
					if(hud.getPrice(selected) <= hud.getCoins()) {
						
						hud.setSelected(-1, 0, 0, window);
						
						int posX =(int) Math.round(WindowMethods.getMouseX(window) - hud.getRealWidth(selected) * (double) (x - hud.getIconX(selected))/hud.getIconSize()); 
						int posY = (int) Math.round(WindowMethods.getMouseY(window) - hud.getRealHeight(selected) * (double) (y - hud.getIconY(selected))/hud.getIconSize());
						
						if(id == ID.BasicDefence) 
							handler.addObject(new BasicDefence(posX, posY, handler));
						
						
						hud.setCoins(hud.getCoins()-hud.getPrice(selected));
						id = null;
					}
				}
				
				else if(e.getX() >= window.getWidth() - hudWidth + hudWidth*(double) 65/225 && e.getX() <= window.getWidth() + hudWidth * (-1 + (double)65/225 + (double)95/225) && e.getY() <= Window.HEIGHT - Window.HEIGHT*(double)20/1080 && e.getY() >= Window.HEIGHT - Window.HEIGHT*(double)82/1080) {
					game.startSpawn();
				}
				
				hud.startPressed(false);
			}
			else if(e.getButton() == MouseEvent.BUTTON3 && id != null) {
					hud.setSelected(-1,0,0, window);
					id = null;
			}
	}
		
		else if(game.getGameState() == gameState.startMenu) {//--------------------------------start Menu---------------------------------------------------------
			if(e.getButton() == MouseEvent.BUTTON1 && game.getGameState() == gameState.startMenu) {	
				if(e.getButton() == MouseEvent.BUTTON1 && e.getX() >= Window.WIDTH * (double)560/1920 && e.getX() < Window.WIDTH * ((double)560/1920 + (double)825/1920)) {
					System.out.println(e.getY());
					
					if(e.getY() >= Window.HEIGHT * (double)520/1080 && e.getY() <= Window.HEIGHT * (double)615/1080) {
						game.setGameState(gameState.mapSelector);
						game.setNextGameState(gameState.game);
					}
					
					else if(e.getY() >= Window.HEIGHT * (double)645/1080 && e.getY() <= Window.HEIGHT * (double)741/1080) {
						game.setGameState(gameState.mapSelector);
						game.setNextGameState(gameState.editor);
					}
					
					else if(e.getY() >= Window.HEIGHT * (double)770/1080 && e.getY() <= Window.HEIGHT * (double)866/1080);
					
				}
				startMenu.setPressed(StartMenu.NOTHING);
			}
		}
		else if(game.getGameState() == gameState.editor) {//--------------------------------------------editor-------------------------------------------
			
			if(e.getButton() == MouseEvent.BUTTON1) {
				if(e.getX() < Window.WIDTH - hudWidth && e.getY() < blockSize*9)
					editor.pressed(e.getX(), e.getY());
				
				else if(e.getX() >= window.getWidth() - hudWidth + hudWidth*(double) 65/225 && e.getX() <= window.getWidth() + hudWidth * (-1 + (double)65/225 + (double)95/225) && e.getY() <= Window.HEIGHT - Window.HEIGHT*(double)20/1080 && e.getY() >= Window.HEIGHT - Window.HEIGHT*(double)82/1080) {
					editor.done();
					if(editor.canChange()) {
						game.setGameState(gameState.startMenu);
						editor.reset();
					}
				}
				editor.donePressed(false);
			}
				
		}
		else if(game.getGameState() == gameState.mapSelector) {//-----------------------------------------map selector--------------------------------------------
			startMenu.setSelectorPressed(MapSelector.NONE);
			
			if(e.getButton() == MouseEvent.BUTTON1) {
				
				if(e.getY() >= (int)Math.round(Window.HEIGHT * 757/1080.0) && e.getY() <= (int)Math.round(Window.HEIGHT * 810/1080.0)){
					
					if(e.getX() >= (int)(Math.round(Window.WIDTH * 497/1920.0)) && e.getX() <= (int)(Math.round(Window.WIDTH * (591)/1920.0))) {
						startMenu.backward();
					}
					else if(e.getX() >= (int)(Math.round(Window.WIDTH * (497 + 802)/1920.0)) && e.getX() <= (int)(Math.round(Window.WIDTH * (497 + 90 + 802)/1920.0))) {
						startMenu.forward();
					}
				}
				else if(e.getY() >= (int)Math.round(Window.HEIGHT * 297/1080.0) && e.getY() <= (int)Math.round(Window.HEIGHT * 347/1080.0)){
										
						if(e.getX() >= (int)(Math.round(Window.WIDTH * 522/1920.0)) && e.getX() <= (int)(Math.round(Window.WIDTH * (522+65)/1920.0)))
								game.setGameState(gameState.startMenu);
						
						else if(e.getX() >= (int)(Math.round(Window.WIDTH * (522+65+730)/1920.0)) && e.getX() <= (int)(Math.round(Window.WIDTH * (522+65+730+58)/1920.0)) && game.getNextGameState() == gameState.editor)
							game.setGameState(gameState.editor);
										
				}
				else if(e.getX() >= (int)Math.round(Window.WIDTH*547/1920) && e.getX() <= (int)Math.round(Window.WIDTH*1338/1920)) { // map names
					if(e.getY() >= (int)Math.round(Window.HEIGHT*372/1080) && e.getY() <= (int)Math.round(Window.HEIGHT*470/1080) && startMenu.getMap(0) != null) { //nr 1
						if(game.getNextGameState() == gameState.editor)
							editor.setMap(startMenu.getMap(0));
						
						else 
							game.setMap(startMenu.getMap(0));
						
						game.setGameState(game.getNextGameState());
						game.setNextGameState(null);
						
					}
					else if(e.getY() >= (int)Math.round(Window.HEIGHT*502/1080) && e.getY() <= (int)Math.round(Window.HEIGHT*600/1080) && startMenu.getMap(1) != null) { //nr 2
						if(game.getNextGameState() == gameState.editor)
							editor.setMap(startMenu.getMap(1));
						
						else 
							game.setMap(startMenu.getMap(1));
						
						game.setGameState(game.getNextGameState());
						game.setNextGameState(null);
					}
					else if(e.getY() >= (int)Math.round(Window.HEIGHT*631/1080) && e.getY() <= (int)Math.round(Window.HEIGHT*729/1080) && startMenu.getMap(2) != null) { //nr 3
						if(game.getNextGameState() == gameState.editor)
							editor.setMap(startMenu.getMap(2));
						
						else 
							game.setMap(startMenu.getMap(2));
						
						game.setGameState(game.getNextGameState());
						game.setNextGameState(null);
						
				}
				System.out.println(e.getX() + "  " + e.getY());
			}
		}
		
	}
}
}
