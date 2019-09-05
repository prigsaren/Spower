package objects;

import java.awt.*;
import javax.swing.*;
import towerDefence.Handler;
import towerDefence.Window;

public class BasicDefence extends Aim{
	
//	public static final Image image = new ImageIcon("basicTower.png").getImage();
	public static final Image image = new ImageIcon("res//ship.png").getImage();
	public static final int height = Window.WIDTH/17;
	public static final int width = height;
	private static final int price = 250;
	private static final int aimDiameter = 1000;
	private static final int shootDelay = 1000;
	private static final int damage = 0;
	
	public BasicDefence(int x, int y, Handler handler) {
		super(x, y, width, height, aimDiameter, handler, ID.BasicDefence, shootDelay, damage, image);
	}

	
	public static int getPrice() {
		return price;
	}
	public static int getAimDiameter() {
		return aimDiameter;
	}
	
	public static int getShootDelay() {
		return shootDelay;
	}
	public static int getDamage() {
		return damage;
	}

	


}
