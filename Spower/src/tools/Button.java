package tools;


public class Button {
	
	private Line[] line;
	private int x;
	private int y;
	private int endX;
	private int endY;
	private int usageNr;
	
	public Button(Line[] line, int x, int usageNr) {
		this.line = line;
		this.x = x;
		this.usageNr = usageNr;
	}
	
	public Button(int x, int y, int endX, int endY, int usageNr) {
		this.x = x;
		this.y = y;
		this.endX = endX;
		this.endY = endY;
		this.usageNr = usageNr;
		
		
	}
	
	public boolean isOnButton(int mousePosX, int mousePosY) {
		if(line != null && mousePosY > line[mousePosX-x].getStartY() && mousePosY < line[mousePosX-x].getEndY())
			return true;
		else if(mousePosX > x && mousePosX < endX && mousePosY > y && mousePosY < endY)
			return true;
		return false;
	}
	
	public int getusageNr() {
		return usageNr;
	}
}
