package towerDefence;

import java.awt.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import tools.Painter;
import tools.Button;

public class Editor {
	
	
//	(int)(x/1920.0 * Window.WINDOW_WIDTH), (int)(y/1080.0) * Window.WINDOW_HEIGHT,(int)(x2/1920.0)* Window.WINDOW_WIDTH,(int)(y2/1080.0)* Window.WINDOW_HEIGHT
	Button path = new Button((int)(1743/1920.0 * Window.WINDOW_WIDTH), (int)(200/1080.0 * Window.WINDOW_HEIGHT),(int)(1854/1920.0* Window.WINDOW_WIDTH),(int)(311/1080.0* Window.WINDOW_HEIGHT), Map.PATH);
	Button startPath = new Button((int)(1743/1920.0 * Window.WINDOW_WIDTH), (int)(351/1080.0 * Window.WINDOW_HEIGHT),(int)(1854/1920.0* Window.WINDOW_WIDTH),(int)(461/1080.0* Window.WINDOW_HEIGHT), Map.NOTHING);
	Button nothing = new Button((int)(1743/1920.0 * Window.WINDOW_WIDTH), (int)(500/1080.0 * Window.WINDOW_HEIGHT),(int)(1854/1920.0* Window.WINDOW_WIDTH),(int)(611/1080.0* Window.WINDOW_HEIGHT), Map.START_PATH);

	Button[] buttons = {path, startPath, nothing};
	
	private Image hud = new ImageIcon("Graphics\\editor.png").getImage();
	private Image hudDone = new ImageIcon("Graphics\\editorDone.png").getImage();
	private Image pathImage = new ImageIcon("Graphics\\path.png").getImage();
	private Image startPathImage = new ImageIcon("Graphics\\startPath.png").getImage();
	
	private final int blockSize = Window.WINDOW_WIDTH/17;
	private final int hudWidth = Window.WINDOW_WIDTH*2/17 + Window.WINDOW_WIDTH%17;
	
	private int selectedType = -1;
	private int[][] map = new int[15][9];
	
	private boolean needStart = true;
	private boolean donePressed = false;
	private boolean needName = false;
	
	
	private String mapName = "";

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
				
				else if(map[i][i2] == Map.START_PATH)
					g.drawImage(startPathImage, i*blockSize, i2 * blockSize, blockSize, blockSize,null);
			}
		}
		
		if(donePressed)
			g.drawImage(hudDone, Window.WINDOW_WIDTH-hudWidth, 0, hudWidth, Window.WINDOW_HEIGHT, null);
		else
			g.drawImage(hud, Window.WINDOW_WIDTH-hudWidth, 0, hudWidth, Window.WINDOW_HEIGHT, null);
		
		g.drawImage(pathImage, Window.WINDOW_WIDTH-hudWidth + (hudWidth-blockSize)/2, (int)Math.round(Window.WINDOW_HEIGHT * (double)200/1080), blockSize, blockSize, null);
		
		if(needStart == true)
			g.drawImage(startPathImage, Window.WINDOW_WIDTH-hudWidth + (hudWidth-blockSize)/2, (int)Math.round(Window.WINDOW_HEIGHT * (double)500/1080), blockSize, blockSize, null);
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(Window.WINDOW_WIDTH-hudWidth + (hudWidth-blockSize)/2, (int)Math.round(Window.WINDOW_HEIGHT * (double)350/1080), blockSize, blockSize);
		
		if(selectedType == Map.PATH)
			g.drawImage(pathImage, Window.WINDOW_WIDTH-hudWidth + (hudWidth-blockSize)/2, (int)Math.round(Window.WINDOW_HEIGHT * (double)800/1080), blockSize, blockSize, null);
		
		if(selectedType == Map.NOTHING) 
			g.fillRect(Window.WINDOW_WIDTH-hudWidth + (hudWidth-blockSize)/2, (int)Math.round(Window.WINDOW_HEIGHT * (double)800/1080), blockSize, blockSize);
		
		if(selectedType == Map.START_PATH)
			g.drawImage(startPathImage, Window.WINDOW_WIDTH-hudWidth + (hudWidth-blockSize)/2, (int)Math.round(Window.WINDOW_HEIGHT * (double)800/1080), blockSize, blockSize, null);
		
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
	
	public void setSelectedType(int selectedType) {
		if((selectedType != Map.START_PATH || needStart) && !needName)
			this.selectedType = selectedType;
	}
	
	public void pressed(int mouseX, int mouseY) {
		
		for(int i = 0; i < buttons.length; i++) {
			if(buttons[i].isOnButton(mouseX, mouseY)) {
				System.out.println("pressed");
				selectedType = buttons[i].getusageNr();
			break;
		}
		}
		
		
		if(selectedType != -1 && !needName && mouseX < 15/17.0 * Window.WINDOW_WIDTH) {
			if(selectedType == Map.START_PATH) {
				needStart = false;
				map[mouseX/blockSize][mouseY/blockSize] = selectedType;
				selectedType = -1;
			}
			
			else if(map[mouseX/blockSize][mouseY/blockSize] == Map.START_PATH) {
				needStart = true;
				map[mouseX/blockSize][mouseY/blockSize] = selectedType;
			}
			else
				map[mouseX/blockSize][mouseY/blockSize] = selectedType;
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
					fw = new FileWriter("Maps\\" + mapName);
				else
					fw = new FileWriter("Maps\\" + mapName + ".txt");
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
			FileReader fr = new FileReader("Maps\\" + mapName);
			Scanner scan = new Scanner(fr);
			
			for(int y = 0; y < 9; y++) {
				for(int x = 0; x < 15; x++) {
					map[x][y] = scan.nextInt();
					if(map[x][y] == Map.START_PATH)
						needStart = false;
				}
			}
			scan.close();
			
		} catch (FileNotFoundException e) {}
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
