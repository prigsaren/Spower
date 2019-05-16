package towerDefence;

import java.awt.event.*;
import java.util.LinkedList;

import objects.*;
import objects.GameObject.*;


public class Mouse implements MouseListener{
	
	
	private Hud hud;
	private Map map;
	private Handler handler;
	private Window window;
	private ID id;
	
	private int x, y, selected;
	
//	private int hudWidth = Window.WIDTH*2/17 + Window.WIDTH%17;
	private int blockSize = Window.WIDTH/17;
	
	
	private LinkedList<Button> buttonList = new LinkedList<>();
	private int pressedButtonNr = -1, chosenButton = -1;
		
	public Mouse(Hud hud, Map map) {
		this.hud = hud;
		this.map = map;
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
		System.out.println(e.getX() +"   " + e.getY());
		
		for(int i = 0; i < buttonList.size(); i++) {
			if(buttonList.get(i).isOnButton(e.getX(), e.getY())) {
				pressedButtonNr = i;
				break;
			}
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(pressedButtonNr != -1 && buttonList.get(pressedButtonNr).isOnButton(e.getX(), e.getY()))
				chosenButton = pressedButtonNr;
		pressedButtonNr = -1;
		
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
	
	public void resetButtonList() {
		chosenButton = -1;
		buttonList.clear();
	}
	public void addButton(Button button) {
		buttonList.add(button);
	}
	public int getPressedButtonNr() {
		return pressedButtonNr;
	}
	public int getChosenButton() {
		return chosenButton;
	}
}
