package towerDefence;

import java.awt.*;
import objects.GameObject.ID;

public class HudIcon {
	private  ID id;
	private Image image;
	private int x;
	private int y;
	private int realWidth;
	private int realHeight;
	private int price;
	private int aimDiameter;
	private int shotDelay;
	private int damage;
	
	public HudIcon(Image image, ID id, int x, int y, int realWidth, int realHeight, int price, int aimDiameter, int shotDelay, int damage) {
		this.image = image;
		this.id = id;
		this.x = x;
		this.y = y;
		this.realWidth = realWidth;
		this.realHeight = realHeight;
		this.price = price;
		this.aimDiameter = aimDiameter;
		this.shotDelay = shotDelay;
		this.damage = damage;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public ID getId() {
		return id;
	}
	public Image getImage() {
		return image;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getRealWidth() {
		return realWidth;
	}
	public int getRealHeight() {
		return realHeight;
	}
	public int getPrice() {
		return price;
	}
	public int getAimDiameter() {
		return aimDiameter;
	}
	
	public int getSpeed() {
		return 1000/shotDelay;
	}
	public int getDamage() {
		return damage;
	}

}
