package menues;

import java.awt.*;
import java.io.File;
import java.util.*;
import javax.swing.*;

import tools.Button;
import tools.Painter;

public class MapSelector extends Menu{
	
	private int slide = 0;
	
	private LinkedList<String> componentList = new LinkedList<>();
	
//	private Button forwardButton = new Button();
//	private Button backwardButton = new Button();
//	private Button exitButton= new Button();
//	private Button newButton = new Button();
	private Button map1Button = new Button((int)(547.0/1920*WINDOW_WIDTH), (int)(372.0/1080*WINDOW_HEIGHT), (int)(1338.0/1920*WINDOW_WIDTH), (int)(411.0/1080*WINDOW_HEIGHT), FIRST_BUTTON);
	private Button map2Button = new Button((int)(547.0/1920*WINDOW_WIDTH), (int)(501.0/1080*WINDOW_HEIGHT), (int)(1338.0/1920*WINDOW_WIDTH), (int)(599.0/1080*WINDOW_HEIGHT), SECOND_BUTTON);
	private Button map3Button = new Button((int)(547.0/1920*WINDOW_WIDTH), (int)(631.0/1080*WINDOW_HEIGHT), (int)(1338.0/1920*WINDOW_WIDTH), (int)(729.0/1080*WINDOW_HEIGHT), THIRD_BUTTON);
	
	private Button[] buttons = {map1Button, map2Button, map3Button};
	
	private Image chooseMap = new ImageIcon("Graphics\\chooseMap.png").getImage();
	private Image chooseMapLeft = new ImageIcon("Graphics\\chooseMapLeft.png").getImage();
	private Image chooseMapRight = new ImageIcon("Graphics\\chooseMapRight.png").getImage();
	private Image chooseMapBack = new ImageIcon("Graphics\\chooseMapBack.png").getImage();
	private Image chooseMapNew = new ImageIcon("Graphics\\chooseMapNew.png").getImage();
	
	private Painter painter = new Painter();
	
	public MapSelector() {
		super.setButtons(buttons);
	}
	
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
	protected void increaseSlide(){
		slide++;
	}
	protected void decreaseSlide(){
		slide--;
	}
	
	protected void findMaps() {
		File folder = new File("Maps");
		File[] listOfFiles = folder.listFiles();
		LinkedList<String> tempList = new LinkedList<>();
		
		for(File file : listOfFiles) {
			if(file.isFile() && file.getName().contains(".txt")) 
				tempList.add(file.getName());
		}
		
		for(int i2 = 0; i2 < tempList.size(); i2++) {
			long time = 0;
			int nr = 0;
			
			for(int i = 0; i < tempList.size(); i++) {
				File file = new File("Maps\\" + tempList.get(i));
				
				if(file.lastModified() > time || time == 0) {
					nr = i;
					time = file.lastModified();
				}
			}
			componentList.add(tempList.get(nr));
			tempList.remove(nr);
			i2--;
		
		}
		
	}
	
	
}
