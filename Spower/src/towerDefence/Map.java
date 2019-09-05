package towerDefence;

import java.io.*;
import java.util.*;

import javax.swing.*;

import java.awt.*;

public class Map {
	
	private int[][] map = new int[15][9];
	private Point startPoint;
	private Point path[];
	private Point[] defencePoint;
	
	Random rand = new Random();

	private Image back = new ImageIcon("res/back2.png").getImage();
	private Image pathImage = new ImageIcon("res/path.png").getImage();

	private int blockSize = Window.WIDTH/17;
	
	public static final int NOTHING = 0;
	public static final int PATH = 1;
	public static final int START = 2;
	
	
	public void makeMap(String mapName) {
		int x = 0;
		Point[] tempPoint = new Point[285];
		
		
		try {
			FileReader fr = new FileReader("res/Maps\\" + mapName);
			Scanner scan = new Scanner(fr);
			
			for(int i = 0; i < 9; i++) {
				for(int i2 = 0; i2 < 15; i2++) {
					map[i2][i] = scan.nextInt();
					
					if(map[i2][i] == START)
						startPoint = new Point(i2*blockSize, i*blockSize);
					
					else if(map[i2][i] ==  PATH) {
						tempPoint[x] = new Point(i2*blockSize, i*blockSize);
						x++;
					}
						
						
				}
				
			}	
			scan.close();
			
			Point[] Positions = new Point[x];
			defencePoint = new Point[x+1];
			
			for(int i = 0; i < Positions.length; i++) {
				Positions[i] = tempPoint[i];
				defencePoint[i] = tempPoint[i];
			}
			
			defencePoint[x] = startPoint;
			
			int y = 0;
			Point[] tempPath = new Point[Positions.length];
			boolean contiune = true;
			Point lastPoint = startPoint;
			
			while(contiune) {
				
				boolean contiune2 = true;
				
				for(int i = 0; i < Positions.length && contiune2; i++){
					if(Positions[i] != null && Positions[i].getX() == lastPoint.getX() + blockSize && Positions[i].getY() == lastPoint.getY()) {
						tempPath[y] = Positions[i];
						lastPoint = tempPath[y];
						Positions[i] = null;
						y++;
						contiune2 = false;
					}
				}
				
				for(int i = 0; i < Positions.length && contiune2; i++){
					if(Positions[i] != null && Positions[i].getY() == lastPoint.getY() + blockSize && Positions[i].getX() == lastPoint.getX()) {
						tempPath[y] = Positions[i];
						lastPoint = tempPath[y];
						Positions[i] = null;
						y++;
						contiune2 = false;
					}
				}
				for(int i = 0; i < Positions.length && contiune2; i++){
					if(Positions[i] != null && Positions[i].getX() == lastPoint.getX() - blockSize && Positions[i].getY() == lastPoint.getY()) {
						tempPath[y] = Positions[i];
						lastPoint = tempPath[y];
						Positions[i] = null;
						y++;
						contiune2 = false;
					}
				}
				for(int i = 0; i< Positions.length && contiune2; i++){
					if(Positions[i] != null && Positions[i].getY() == lastPoint.getY() - blockSize && Positions[i].getX() == lastPoint.getX()) {
						tempPath[y] = Positions[i];
						lastPoint = tempPath[y];
						Positions[i] = null;
						y++;
						contiune2 = false;
						}
					}
				if(contiune2)
					contiune = false;
					
						
				
			}
			path = new Point[y];
			for(int i = 0; i < path.length; i++) {
				path[i] = tempPath[i];
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("not found");
		}
	}
	

	public void render(Graphics g) {
		for(int i = 0; i < 9; i++) {
			for(int i2 = 0; i2 < 15; i2++) {
				if(map[i2][i] == PATH || map[i2][i] == START) {
					g.drawImage(pathImage, i2*blockSize, i*blockSize, blockSize, blockSize, null);
										
				}
				else if(map[i2][i] == NOTHING) {
						g.drawImage(back, i2*blockSize, i*blockSize, blockSize, blockSize, null);
					}
				}
			}
			
		}
	
	public Point getStartPos() {
			return startPoint;

	}
	public Point[] getPath() {
		return path;
		
	}
	public Point[] getDefencePoint() {
		return defencePoint;
	}

}
