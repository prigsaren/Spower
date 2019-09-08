package menues;

import java.awt.*;
import javax.swing.*;

import menues.MenuHandler.menuState;
import towerDefence.Painter;
import towerDefence.Button;
import towerDefence.Button.ButtonListener;
import towerDefence.Window;
import towerDefence.C;

public class MapSelector implements ButtonListener {
	private static Image Board = new ImageIcon("res/mapBoard.png").getImage();
	private String[] names = {"","",""};
	private int x, lastX, nr = 0;
	
	private static int count = 0, moveX;
	
	private Painter painter = new Painter();
	
	private static final int BACK_BUTTON_ID = 1, NEW_BUTTON_ID = 2, LEFT_BUTTON_ID = 3, RIGHT_BUTTON_ID = 4;
	
	private static Image backButtonImage = new ImageIcon("res/backButton.png").getImage();
	private static Image newButtonImage = new ImageIcon("res/newButton.png").getImage();
	private static Image leftButtonImage = new ImageIcon("res/leftButton.png").getImage();
	private static Image rightButtonImage = new ImageIcon("res/rightButton.png").getImage();
	
	private static Image pressedBackButtonImage = new ImageIcon("res/pressedBackButton.png").getImage();
	private static Image pressedNewButtonImage = new ImageIcon("res/pressedNewButton.png").getImage();
	private static Image pressedLeftButtonImage = new ImageIcon("res/pressedLeftButton.png").getImage();
	private static Image pressedRightButtonImage = new ImageIcon("res/pressedRightButton.png").getImage();
	
	private Button backButton = new Button(C.xScreenPos(522), C.xScreenPos(586), C.yScreenPos(296), C.yScreenPos(345),	backButtonImage, 0, C.yScreenPos(2),pressedBackButtonImage, BACK_BUTTON_ID, this);
	private Button newButton = new Button(C.xScreenPos(1317), C.xScreenPos(1374), C.yScreenPos(291), C.yScreenPos(348),	newButtonImage, 0, C.yScreenPos(2), pressedNewButtonImage, NEW_BUTTON_ID, this);
	private Button leftButton  = new Button(C.xScreenPos(497), C.xScreenPos(590), C.yScreenPos(759), C.yScreenPos(811),	leftButtonImage, 0, C.yScreenPos(2), pressedLeftButtonImage,LEFT_BUTTON_ID, this);
	private Button rightButton = new Button(C.xScreenPos(1299), C.xScreenPos(1388), C.yScreenPos(759), C.yScreenPos(811),	rightButtonImage, 0, C.yScreenPos(2), pressedRightButtonImage, RIGHT_BUTTON_ID, this);
	
	private Button[] buttonList = {backButton, newButton, leftButton, rightButton};
	
	MenuHandler menuHandler;
	
	public MapSelector(MenuHandler menuHandler) {
		this.menuHandler = menuHandler;
		x = count*Window.WIDTH;
		lastX = x;
		count ++;
	}
	
	public void tick() {
		if(x + moveX >= lastX + Window.WIDTH || x + moveX <= lastX - Window.WIDTH) {
			moveX = 0;
			lastX = x;
		}
		if(x == 0)
			menuHandler.setButtons(buttonList);
			
		
		x += moveX;
		
		for(int i = 0; i < buttonList.length; i++) {
			buttonList[i].changeX(moveX);
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(Board, x, 0, C.xScreenPos(1920), C.yScreenPos(1080), null);
		for(int i = 0; i < buttonList.length; i++) {
			buttonList[i].render(g);
		}
		painter.drawString(g, names[0], C.xScreenPos(x + 568), C.yScreenPos(402), C.yScreenPos(60));
		painter.drawString(g, names[1], C.xScreenPos(x + 568), C.yScreenPos(402+130), C.yScreenPos(60));
		painter.drawString(g, names[2], C.xScreenPos(x + 568), C.yScreenPos(402 +130*2), C.yScreenPos(60));
	}
	
	@Override
	public void buttonReleased(int id) {
		if(id == BACK_BUTTON_ID)
			menuHandler.setMenuState(menuState.startMenu);
		
		if(id == RIGHT_BUTTON_ID) {
			moveX = -20;
		}
			
	}
	
	public boolean addString(String string) {
		if(nr < 3) {
			names[nr] = string;
			nr++;
			return true;
		}
		return false;
	}
	
	public Button[] getButtonList() {
		return buttonList;
	}
	
	public static void resetCount() {
		count = 0;
	}
	

}
