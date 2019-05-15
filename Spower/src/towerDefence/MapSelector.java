package towerDefence;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MapSelector {
	
	private int slide = 0;
	
	private LinkedList<String> componentList = new LinkedList<>();
	
	private Image chooseMap = new ImageIcon("chooseMap.png").getImage();
	private Image chooseMapLeft = new ImageIcon("chooseMapLeft.png").getImage();
	private Image chooseMapRight = new ImageIcon("chooseMapRight.png").getImage();
	private Image chooseMapBack = new ImageIcon("chooseMapBack.png").getImage();
	private Image chooseMapNew = new ImageIcon("chooseMapNew.png").getImage();
	
	private Painter painter = new Painter();
	
	public static final int NONE = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int BACK = 3;
	public static final int NEW = 4;
	
	private int pressed;
	
	public void render(Graphics g, int x, int y, int width, int height) {
		
		if(pressed == NONE)
			g.drawImage(chooseMap, x, y, width, height, null);
		else if(pressed == LEFT)
			g.drawImage(chooseMapLeft, x, y, width, height, null);
		else if(pressed == RIGHT)
			g.drawImage(chooseMapRight, x, y, width, height, null);
		else if(pressed == BACK)
			g.drawImage(chooseMapBack, x, y, width, height, null);
		else if(pressed == NEW)
			g.drawImage(chooseMapNew, x, y, width, height, null);
		
		
		if(slide*3 < componentList.size()) 
			painter.drawString(g, componentList.get(slide*3).replaceAll(".txt", ""), (int) Math.round(x+width * (double)150/1060), (int) Math.round(y+height * (double)210/640), (int)Math.round(width * (double)35/1060), (int)Math.round(height * (double)50/640));
			if(slide*3+1 < componentList.size())
			painter.drawString(g, componentList.get(slide*3+1).replaceAll(".txt", ""), (int) Math.round(x+width * (double)150/1060), (int) Math.round(y+height * (double)339/640), (int)Math.round(width * (double)35/1060), (int)Math.round(height * (double)50/640));
		if(slide*3+2 < componentList.size())
			painter.drawString(g, componentList.get(slide*3+2).replaceAll(".txt", ""), (int) Math.round(x+width * (double)150/1060), (int) Math.round(y+height * (double)470/640), (int)Math.round(width * (double)35/1060), (int)Math.round(height * (double)50/640));
	}
	
	public void forward() {
		if(slide < componentList.size()/3)
			slide ++;
	}
	public void backward() {
		if(slide > 0)
			slide --;
	}
	public void setPressed(int pressed) {
		this.pressed = pressed;
	}
	
	public void addComponents(String component) {
		componentList.add(component);
	}
	public void clearList() {
		componentList.clear();
	}
	public String getMap(int nr) {
		if(slide*3 + nr<componentList.size())
			return componentList.get(slide+nr);
		return null;
	}
}
