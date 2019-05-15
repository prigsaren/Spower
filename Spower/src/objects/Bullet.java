package objects;

import java.awt.*;
import javax.swing.*;
import towerDefence.*;

public class Bullet extends GameObject{
	private double velX;
	private double velY;
	private int x;
	private int y;
	private int damage;
	
	private static final int speed = 1;
	private static final int size = 10;
	
	private double toMoveX = 0;
	private double toMoveY = 0;
	
	private Image star = new ImageIcon("star.png").getImage();
	
	Handler handler;
	private Enemy enemy;
	
	public Bullet (int x, int y, int destinationX, int destinationY, int damage, Handler handler) {
		super(x, y, size, size, ID.Bullet);
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.damage = damage;
//		
//		if(x-destinationX > 0)
//			velX = -speed;
//		else
//			velX = speed;
		
//		velY = (y-destinationY)*velX/(x-destinationX);
		
		int deltaX = x - destinationX;
		int deltaY = y - destinationY;
		
		
	}
	public void resized(int width, int height, Point[] path) {
		
	}

	@Override
	public void tick() {
		toMoveX += velX;
		toMoveY += velY;
		
		x += (int)toMoveX;
		y += (int)toMoveY;
		
		toMoveX -= (int)toMoveX;
		toMoveY -= (int)toMoveY;
		
		if(x >= 1920 || x <= 0 || y >= 1080 || y <= 0)
			handler.removeObject(this);
		
		for(int i = 0; i < handler.getGameObject().size(); i++) {
			GameObject gm = handler.getGameObject().get(i);
			if(gm.getId() == ID.BasicEnemy && x <= gm.getX() + gm.getWidth() && x + size >= gm.getX() && y <= gm.getHeight() + gm.getY() && y + size >= gm.getY() /*&& 1==2*/) {
				enemy = (Enemy) gm;
				enemy.damage(damage);
				handler.removeObject(this);
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(star, x, y , size, size, null);
	}

}
