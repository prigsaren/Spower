package towerDefence;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;

public class Editor {
	
	private Image hud = new ImageIcon("res/editor.png").getImage();
	private Image hudDone = new ImageIcon("res/editorDone.png").getImage();
	private Image pathImage = new ImageIcon("res/path.png").getImage();
	private Image startPathImage = new ImageIcon("res/startPath.png").getImage();
	
	private final int blockSize = Window.WIDTH/17;
	private final int hudWidth = Window.WIDTH*2/17 + Window.WIDTH%17;
	
	private int[][] map = new int[15][9];
	
	private boolean needStart = true;
	
	private boolean donePressed = false;
	
	private int selectedType = -1;
	
	private String mapName = "";
	
	private boolean needName = false;
	
	private Painter painter = new Painter();
	
	public void render(Graphics g) {
		for(int i = 1; i <= 9 ; i++) {
			g.drawLine(0, i*blockSize, blockSize*15, i * blockSize);
		}
		for(int i = 1; i<=15; i++) {
			g.drawLine(i*blockSize, 0, i*blockSize, blockSize*9);
		}
		for(int i = 0; i < 15; i++) {
			for(int i2 = 0; i2 < 9; i2 ++) {
				if(map[i][i2] == Map.PATH)
					g.drawImage(pathImage, i*blockSize, i2 * blockSize, blockSize, blockSize,null);
				
				else if(map[i][i2] == Map.START)
					g.drawImage(startPathImage, i*blockSize, i2 * blockSize, blockSize, blockSize,null);
			}
		}
		
		if(donePressed)
			g.drawImage(hudDone, Window.WIDTH-hudWidth, 0, hudWidth, Window.HEIGHT, null);
		else
			g.drawImage(hud, Window.WIDTH-hudWidth, 0, hudWidth, Window.HEIGHT, null);
		
		g.drawImage(pathImage, Window.WIDTH-hudWidth + (hudWidth-blockSize)/2, (int)Math.round(Window.HEIGHT * (double)200/1080), blockSize, blockSize, null);
		
		if(needStart == true)
			g.drawImage(startPathImage, Window.WIDTH-hudWidth + (hudWidth-blockSize)/2, (int)Math.round(Window.HEIGHT * (double)500/1080), blockSize, blockSize, null);
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(Window.WIDTH-hudWidth + (hudWidth-blockSize)/2, (int)Math.round(Window.HEIGHT * (double)350/1080), blockSize, blockSize);
		
		if(selectedType == Map.PATH)
			g.drawImage(pathImage, Window.WIDTH-hudWidth + (hudWidth-blockSize)/2, (int)Math.round(Window.HEIGHT * (double)800/1080), blockSize, blockSize, null);
		
		if(selectedType == Map.NOTHING) 
			g.fillRect(Window.WIDTH-hudWidth + (hudWidth-blockSize)/2, (int)Math.round(Window.HEIGHT * (double)800/1080), blockSize, blockSize);
		
		if(selectedType == Map.START)
			g.drawImage(startPathImage, Window.WIDTH-hudWidth + (hudWidth-blockSize)/2, (int)Math.round(Window.HEIGHT * (double)800/1080), blockSize, blockSize, null);
		
		if(needName) {
			System.out.println(mapName);
			painter.drawString(g, mapName, 500, 500, 50, 50);
		}
		
	}
	public void reset() {
		map = new int[15][19];
		needStart = true;
		mapName = "";
		needName = false;
	}
	
	public void tick() {
	}
	public void setSelectedType(int selectedType) {
		if((selectedType != Map.START || needStart) && !needName)
			this.selectedType = selectedType;
	}
	
	public void pressed(int x, int y) {
		
		if(selectedType != -1 && !needName) {
			if(selectedType == Map.START) {
				needStart = false;
				map[x/blockSize][y/blockSize] = selectedType;
				selectedType = -1;
			}
			
			else if(map[x/blockSize][y/blockSize] == Map.START) {
				needStart = true;
				map[x/blockSize][y/blockSize] = selectedType;
			}
			else
				map[x/blockSize][y/blockSize] = selectedType;
			}
	}

	public void donePressed(boolean donePressed) {
		this.donePressed = donePressed;
	}
	
	public void done() {	
		if((mapName == null || mapName == "") && !needStart)
			needName = true;
		
	
		if(needName == false && !needStart) {
			FileWriter fw;
			try {
				if(mapName.contains(".txt"))
					fw = new FileWriter("res/Maps\\" + mapName);
				else
					fw = new FileWriter("res/Maps\\" + mapName + ".txt");
				BufferedWriter bw = new BufferedWriter(fw);
				
				for(int y = 0; y < 9; y++) {
					for (int x = 0; x < 15; x++){
						bw.write(map[x][y] + " ");
						}
					bw.newLine();
					}
				bw.close();
				
			} catch (IOException e) {}
		}
	}
	public void setMap(String mapName) {
		
		this.mapName = mapName;
		
		try {
			FileReader fr = new FileReader("res/Maps\\" + mapName);
			Scanner scan = new Scanner(fr);
			
			for(int y = 0; y < 9; y++) {
				for(int x = 0; x < 15; x++) {
					map[x][y] = scan.nextInt();
					if(map[x][y] == Map.START)
						needStart = false;
				}
			}
			scan.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	public boolean needMapName() {
			return needName;
	}
	public boolean canChange() {
		if(needName || needStart) {
			System.out.println(needName + "  " + needStart);
			return false;			
		}
		System.out.println("ready");
		
		return true;
	}
	
	public boolean addLetter(String letter) {
		if(letter == null && (mapName != "" || mapName != null)) {
			needName = false;
			done();
			return true;
		}
		
		else
			this.mapName += letter;
		return false;
	}
}
