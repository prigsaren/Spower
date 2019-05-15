package towerDefence;

import java.util.*;
import objects.*;
import java.awt.*;

public class Handler {
	LinkedList<GameObject> gameObject = new LinkedList<>();
	
	public void tick() {
		for(int i = 0; i<gameObject.size(); i++) {
			gameObject.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i<gameObject.size(); i++) {
			gameObject.get(i).render(g);
		}
	}
	public void removeObject(GameObject gameObject) {
		this.gameObject.remove(gameObject);
	}
	public void addObject(GameObject gameObject) {
		this.gameObject.add(gameObject);
	}
	public LinkedList<GameObject> getGameObject(){
		return gameObject;
	}
	public void reset() {
		gameObject.clear();
	}
}
