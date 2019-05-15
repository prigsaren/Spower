package towerDefence;

import java.awt.*;

public class WindowMethods {
	public static int getMouseX(Window window) {
		
		return (int)MouseInfo.getPointerInfo().getLocation().getX() - (int)window.getLocation().getX();
}
	public static int getMouseY(Window window) {
		
		return (int)MouseInfo.getPointerInfo().getLocation().getY() - (int)window.getLocation().getY();
}

}
