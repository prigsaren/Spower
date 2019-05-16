package towerDefence;

public class Button {
	
	private Line[] line;
	private int x;
	private int y;
	private int lastX;
	private int lastY;
	
	public Button(Line[] line, int x) {
		this.line = line;
		this.x = x;
	}
	
	public Button(int x, int y, int lastX, int lastY) {
		this.x = x;
		this.y = y;
		this.lastX = lastX;
		this.lastY = lastY;
	}
	
	public boolean isOnButton(int mousePosX, int mousePosY) {
		if(line != null && mousePosY > line[mousePosX-x].getStartY() && mousePosY < line[mousePosX-x].getEndY())
			return true;
		else if(mousePosX > x && mousePosX < lastX && mousePosY > y && mousePosY < lastY)
			return true;
		return false;
	}
}
