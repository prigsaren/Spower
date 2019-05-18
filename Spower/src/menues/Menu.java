package menues;
import tools.Button;
import towerDefence.Window;

public class Menu {
	protected static final int NONE = -1;
	protected static final int LEFT = 0;
	protected static final int RIGHT = 1;
	protected static final int BACK = 2;
	protected static final int NEW = 3;
	protected static final int FIRST_BUTTON = 4;
	protected static final int SECOND_BUTTON = 5;
	protected static final int THIRD_BUTTON = 6;
	
	
	protected static final int WINDOW_WIDTH = Window.WINDOW_WIDTH;
	protected static final int WINDOW_HEIGHT = Window.WINDOW_HEIGHT;
	
	protected int pressed = NONE;
	protected int releasedNR = NONE;
	
	private Button[] buttons;
	
	
	protected void setButtons(Button[] buttons) {
		this.buttons = buttons;
	}
	
	protected int mouseReleased(int mouseX, int mouseY) {
		
		for(int i = 0; i < buttons.length && pressed != NONE && releasedNR == NONE; i++) {
			if(buttons[i].isOnButton(mouseX, mouseY) && pressed == buttons[i].getusageNr()) {
				releasedNR = buttons[i].getusageNr();
			}
		}
		
		pressed = NONE;
		return releasedNR;
		
	}
	
	protected void mousePressed(int mouseX, int mouseY) {
		releasedNR = NONE;
		
		for(int i = 0; i < buttons.length; i++) {
			if(buttons[i].isOnButton(mouseX, mouseY)) {
				pressed = buttons[i].getusageNr();
				break;
			}
		}
	}
}
