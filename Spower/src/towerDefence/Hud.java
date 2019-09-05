package towerDefence;

import java.awt.*;

import javax.swing.*;

import objects.*;
import objects.GameObject.ID;

public class Hud {
	private static final int DEAFULT_HP = 10;
	private int hp = DEAFULT_HP;
	
	private static final int DEAFULT_COINS = 750;
	private int coins = DEAFULT_COINS;
	
	private int iconSize = (int)Math.round(Window.HEIGHT * (double)58/1080);
	private int hudWidth = Window.WIDTH *2/17  + Window.WIDTH%17;
	private int hudX = Window.WIDTH -hudWidth;
	
	private int height = Window.HEIGHT;
	
	private Window window;
	
	int selected = -1;
	private int missX, missY;
	
	private Image hud = new ImageIcon("res/hud.png").getImage();
	private Image hudStart = new ImageIcon("res/hudStart.png").getImage();
	private Image hudOverlay = new ImageIcon("res/hudOverlay.png").getImage();
	private Image health = new ImageIcon("res/health.png").getImage();
	
	private Image cirkle = new ImageIcon("res/redCirkle.png").getImage();
	
	private Color blue = new Color(8,94,126);
	
	private HudIcon[] hudIcons = { new HudIcon(BasicDefence.image, ID.BasicDefence,(int)Math.round(hudX + hudWidth*(double)33/225), (int)Math.round(Window.HEIGHT*(double)196/1080), BasicDefence.width, BasicDefence.height, BasicDefence.getPrice(), BasicDefence.getAimDiameter(), BasicDefence.getShootDelay(), BasicDefence.getDamage())};
	private boolean canPlace = true;
	
	private Font coinFont = new Font("Serif", Font.BOLD, 20);
	
	private boolean startPressed = false;
	public Hud() {
	}
	
	
	public void render(Graphics g) {
		
		
		if(startPressed)
			g.drawImage(hudStart, hudX, 0, hudWidth, height, null);
		else
			g.drawImage(hud, hudX, 0, hudWidth, height, null);
		
		g.setColor(Color.green);
		g.drawImage(health,(int)Math.round(hudX + hudWidth*(double) 35/225), (int)Math.round(height * (double)136/1080), (int) Math.round((double) hp/DEAFULT_HP * hudWidth * (double) 155/225),(int)Math.round(height * (double) 23/1080), null);
		g.drawImage(hudOverlay, hudX, 0, hudWidth, (int)Math.round(height* (double)164/1080), null);
		
		
		g.setColor(Color.DARK_GRAY);
//		g.fillRect(hudX + (hudWidth- hp*15)/2 , height/20, hp*15, 20);
//		g.setColor(Color.GREEN);
//		g.fillRect(hudX + (width - hp*15)/2, height/20, hp*15, 20);
		
		
		g.setColor(blue);
		String coin = "" + coins;
		g.setFont(coinFont);
		g.drawString(coin, (int)Math.round(hudX + hudWidth*(double) 98/225), (int)Math.round(height * (double) 85/1080));
		
		for(int i = 0; i < hudIcons.length; i++) {
			g.drawImage(hudIcons[i].getImage(), hudIcons[i].getX(), hudIcons[i].getY(),iconSize , iconSize, null);
		}
		
		if(selected >= 0) {
			int posX =(int) Math.round(WindowMethods.getMouseX(window) - hudIcons[selected].getRealWidth() * (double) (missX - hudIcons[selected].getX())/iconSize);
			int posY = (int) Math.round(WindowMethods.getMouseY(window) - hudIcons[selected].getRealHeight() * (double) (missY - hudIcons[selected].getY())/iconSize);
			
			if(!canPlace) {
				System.out.println((hudIcons[selected].getAimDiameter()-hudIcons[selected].getRealWidth())/2);
				g.drawImage(cirkle, posX - (hudIcons[selected].getAimDiameter()-hudIcons[selected].getRealWidth())/2,
						posY - (hudIcons[selected].getAimDiameter()-hudIcons[selected].getRealHeight())/2,
						hudIcons[selected].getAimDiameter(),  hudIcons[selected].getAimDiameter(), null);
			}
			else {
				g.setColor(Color.RED);
				g.drawOval(posX - (hudIcons[selected].getAimDiameter()-hudIcons[selected].getRealWidth())/2,
						posY - (hudIcons[selected].getAimDiameter()-hudIcons[selected].getRealHeight())/2,
						hudIcons[selected].getAimDiameter(),  hudIcons[selected].getAimDiameter());
			}
			g.drawImage(hudIcons[selected].getImage(), posX, posY, hudIcons[selected].getRealWidth(), hudIcons[selected].getRealHeight(), null);
			
			g.setFont(coinFont);
			g.setColor(blue);
			g.drawString("Price: " + hudIcons[selected].getPrice(), (int)Math.round(hudX + hudWidth * (double) 40/225), (int)Math.round(height * (double)915/1080));
			g.drawString("Range: " + hudIcons[selected].getAimDiameter()/2, (int)Math.round(hudX + hudWidth * (double) 40/225), (int)Math.round(height * (double)955/1080));
			g.drawString("Shoots per second: " + hudIcons[selected].getSpeed(), (int)Math.round(hudX + hudWidth * (double) 40/225), (int)Math.round(height * (double)935/1080));
			g.drawString("Damage: " + hudIcons[selected].getDamage(), (int)Math.round(hudX + hudWidth * (double) 40/225), (int)Math.round(height * (double)975/1080));
		}
	}
	public void setSelected(int selected, int missX, int missY, Window window) {
		
		this.selected = selected;
		this.missX = missX;
		this.missY = missY;
		this.window = window;
	}
	
	public void damage(int damage) {
		hp-= damage;
		
		if(hp <= 0)
			end();
	}
	
	public void end() {
		System.exit(0);
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public void reset() {
		coins = DEAFULT_COINS;
		hp = DEAFULT_HP;
	}
	
	public void canPlace(boolean canPlace) {
		this.canPlace = canPlace;
	}
	public void startPressed(boolean startPressed) {
		this.startPressed = startPressed;;
	}
	
	public int getCoins() {
		return coins;
	}
	public int getIconSize(){
		return iconSize;
	}
	
	public int getIconX(int i) { 
		return hudIcons[i].getX();
	}
	public int getIconY(int i) {
		return hudIcons[i].getY();
	}
	public int getNumberOfIcons() {
		return hudIcons.length;
	}
	public ID getIconID(int i) {
		return hudIcons[i].getId();
	}
	public int getRealWidth(int i) {
		return hudIcons[i].getRealWidth();
	}
	public int getRealHeight(int i) {
		return hudIcons[i].getRealHeight();
	}
	public int getPrice(int i) {
		return hudIcons[i].getPrice();
	}

}
