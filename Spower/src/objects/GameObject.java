package objects;

import java.awt.*;

public abstract class GameObject {
	
	int x, y, width, height;
	ID id;
	
	public enum ID{
		BasicDefence(),
		Bullet(),
		BasicEnemy();
	}
	
	public GameObject(int x, int y, int width, int height, ID id) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
	}
	
	public ID getId() {
		return id;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public abstract void tick();
	public abstract void render(Graphics g);
}
