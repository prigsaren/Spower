package towerDefence;

import java.awt.event.*;
import towerDefence.Game.*;

public class KeyInput implements KeyListener{

	Game game;
	Editor editor;
	
	private String name;
	
	public KeyInput(Game game, Editor editor) {
		this.game = game;
		this.editor = editor;
	}
	
	public void keyPressed(KeyEvent e) {
		if(game.getGameState() == gameState.game || game.getGameState() == gameState.editor) {
			
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if(game.getGameState() == gameState.game)
					game.reset();
				game.setGameState(gameState.startMenu);
			}
			
			if(game.getGameState() == gameState.editor && editor.needMapName()) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(editor.addLetter(null))
						game.setGameState(gameState.startMenu);
				}
				else 
					editor.addLetter(e.getKeyChar() + "");
				
			}
		}
		else if(game.getGameState() == gameState.startMenu) {
			
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	

}
