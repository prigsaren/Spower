package towerDefence;

import java.awt.*;
import javax.swing.*;

public class Painter {

// (9) 104 pixels: W
// (7) 82 pixels: M
// (6) 71 pixels: S
// (5) 60 pixels: A, B, F, H, L, N, O, P, Q, T, U, V, X, Y, Z
// (4) 49 pixels: C, D, E, G, J, K
// (3) 38 pixels: I
	
	int x;
	
	public void drawString(Graphics g, String s, int x, int y, int width, int height) {
		if(s != null) {
			this.x = x;
			
			for(int i = 0; i < s.length(); i++) {
				
				String string = s.charAt(i) + "";
				
				if(!string.equals(" ")) {
					Image image = new ImageIcon("Graphics\\Letters\\" + s.toUpperCase().charAt(i)+ ".png").getImage();
					g.drawImage(image, x, y, (int)Math.round(width * image.getWidth(null)/60.0), height, null);
					x += (int)Math.round(width * image.getWidth(null)/60.0 + width/5.0);
				}
				else {
					x += width;
				}
				}
			
		}
		}
}
