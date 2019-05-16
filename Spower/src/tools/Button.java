package tools;

import towerDefence.Line;

public class Button {
	
	private Line[] line;
	private int x;
	private int y;
	private int lastX;
	private int lastY;
	private int usageNr;
	
	public Button(Line[] line, int x, int usageNr) {
		this.line = line;
		this.x = x;
		this.usageNr = usageNr;
	}
	
	public Button(int x, int y, int lastX, int lastY, int usageNr) {
		this.x = x;
		this.y = y;
		this.lastX = lastX;
		this.lastY = lastY;
		this.usageNr = usageNr;
	}
	
	public boolean isOnButton(int mousePosX, int mousePosY) {
		if(line != null && mousePosY > line[mousePosX-x].getStartY() && mousePosY < line[mousePosX-x].getEndY())
			return true;
		else if(mousePosX > x && mousePosX < lastX && mousePosY > y && mousePosY < lastY)
			return true;
		return false;
	}
	
	public int getusageNr() {
		return usageNr;
	}
}
