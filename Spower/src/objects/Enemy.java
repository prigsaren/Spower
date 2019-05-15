package objects;

import java.awt.*;

import towerDefence.*;
import towerDefence.Window;

public abstract class Enemy extends GameObject{

	private int health, rarity;
	private double speed;
	private Point[] path;
	private int step = 0;
	private int price;
	
	private boolean done = false;
	
	private double toMove = 0;
	
	private Handler handler;
	
	Hud hud;
	
	public Enemy(int x, int y, int health, double speed, int width, int height, int rarity, int price, Hud hud, Point[] path, Handler handler, ID id) {
		super(x + (Window.WIDTH/17-width)/2, y + (Window.WIDTH/17-height)/2 , width, height, id);
		this.health = health;
		this.speed = speed;
		this.rarity = rarity;
		this.path = path;
		this.handler = handler;
		this.price = price;
		this.hud = hud;
	}
	
	
	public int getRarity() {
		return rarity;
	}
	public boolean isDone() {
		return done;
	}
	public void damage(int damage) {
		health-= damage;
		if(health <= 0) {
			handler.removeObject(this);
			done = true;
			hud.setCoins(hud.getCoins() + price);
		}
	}
	
	@Override
	public void tick() {
		
		toMove += speed;
		
		if(step < path.length && toMove >= 1 && x == path[step].getX() + (Window.WIDTH/17-width)/2) {	
			
			if(y > path[step].getY() + (Window.WIDTH/17-height)/2){ // Y Behöver gå uppåt
				if(y - (int)toMove <= path[step].getY() + (Window.WIDTH/17-height)/2) {
					y = (int) path[step].getY() + (Window.WIDTH/17-height)/2;
					step ++;
					
				}
				else {
					y -= (int)toMove;
					toMove -= (int)toMove;
				}
			}
			else {//Y behöver gå nedåt
				if(y + (int)toMove >= path[step].getY() + (Window.WIDTH/17-height)/2) {
					y = (int)path[step].getY() + (Window.WIDTH/17-height)/2;
					step++;
				}
				else {
					y += (int)toMove;
					toMove -= (int)toMove;
				}
			}
			
	
				
		}
		else if(step < path.length && toMove >= 1 && y == path[step].getY() + (Window.WIDTH/17-height)/2) {
			
			
					if(x - path[step].getX() - (Window.WIDTH/17-width)/2 > 0){//x behöver gå vänser
						if(x - toMove <= path[step].getX() + (Window.WIDTH/17-width)/2) {
							x = (int)path[step].getX() + (Window.WIDTH/17-width)/2;
							step ++;
						}
						else {
							x -= (int)toMove;
							toMove -= Math.round(toMove-0.5);
						}	
					}
					else { //x behöver gå höger
						if(x + toMove >= path[step].getX() + (Window.WIDTH/17-width)/2) {
							x = (int)path[step].getX() + (Window.WIDTH/17-width)/2;
							step ++;
						}
						else {
							x += (int)toMove;
							toMove -= (int)toMove;
						}
					}
					
			
						
				}
		else if(step >= path.length){
			done = true;
			hud.damage(1);
			handler.removeObject(this);
		}
	}



}
