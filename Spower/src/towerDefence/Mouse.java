package towerDefence;

import java.awt.event.*;

public class Mouse implements MouseListener{
	
	Button[] button;
	ButtonListener buttonListener;
	
	public interface ButtonListener {
		public void buttonPressed(int id);
		public void buttonReleased(int id);
	}
	
	
	public Mouse(Button[] button, ButtonListener buttonListener) {
		this.button = button;
		this.buttonListener = buttonListener;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(int i = 0; i < button.length; i++) {
			if(button[i].isPressed(e.getX(), e.getY())) {
				buttonListener.buttonPressed(button[i].getId());
				i = button.length;
			}
				
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(int i = 0; i < button.length; i++) {
			
			if(button[i].isPressed(e.getX(), e.getY())) {
				buttonListener.buttonReleased(button[i].getId());
				i = button.length;
			}
				
		}
		
	}

}
