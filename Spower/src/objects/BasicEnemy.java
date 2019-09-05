package objects;

import java.awt.*;
import javax.swing.*;
import towerDefence.*;
import towerDefence.Window;

public class BasicEnemy extends Enemy{
	
	private static final int health = 1;
	private static final double speed = Window.WIDTH * 0.1/1920;
	private static final int size = Window.WIDTH/17/2;
	private static final int rarity = 100;
	private static final int price = 10;
	
	private static final Image image = new ImageIcon("res//WorstEnemy.png").getImage();
	
	public BasicEnemy(Point p, Point[] path, Handler handler, Hud hud) {
		super((int)p.getX(), (int)p.getY(), health, speed, size, size, rarity, price, hud, path, handler, ID.BasicEnemy);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
		
	}

}
