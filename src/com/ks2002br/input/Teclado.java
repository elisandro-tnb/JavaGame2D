package com.ks2002br.input;

 /*
 * By Elisandro 12/2021 revisao geral
 */
import java.awt.event.*;
import com.ks2002br.frameworks.*;
import com.ks2002br.game.Game;
import com.ks2002br.game.Menu;

public class Teclado extends KeyAdapter {

	GameController gc;

	public Teclado(GameController gc) {
		this.gc = gc;
	}

	public void keyPressed(KeyEvent e) {
		
		int tecla = e.getKeyCode();

		for (int i = 0; i < gc.obj.size(); i++) {

			GameObject objTemp = gc.obj.get(i);

			if (objTemp.getId() == ObjectId.PLAYER) {
				if (tecla == KeyEvent.VK_D) 	objTemp.setSpdX(5);
				if (tecla == KeyEvent.VK_A) 	objTemp.setSpdX(-5);
				if (tecla == KeyEvent.VK_W  && !objTemp.isJumping())	{
					objTemp.setJumping(true);
					objTemp.setSpdY(-10);
				}
		    	
				if (tecla == KeyEvent.VK_SPACE)	objTemp.setShooting(true);
				if (tecla == KeyEvent.VK_M) {					
					if(!gc.isDebug())  	gc.setDebug(true);
					else 	gc.setDebug(false);			
				}
			}
		} //Fechando o for
		
		if (tecla == KeyEvent.VK_UP)           Menu.up = true;
		if (tecla == KeyEvent.VK_DOWN)     Menu.down = true;
		if (tecla == KeyEvent.VK_ENTER)     Menu.ok = true;
		if (tecla == KeyEvent.VK_ESCAPE) {
			if(Game.gameState !="MENU") Game.gameState = "MENU";
			Menu.pause=true;
		}
		
	}

	public void keyReleased(KeyEvent e) {
		int tecla = e.getKeyCode();

		for (int i = 0; i < gc.obj.size(); i++) {
			GameObject objTemp = gc.obj.get(i);
			if (objTemp.getId() == ObjectId.PLAYER) {
				if (tecla == KeyEvent.VK_D) 	objTemp.setSpdX(0);
				if (tecla == KeyEvent.VK_A)		objTemp.setSpdX(0);
				if (tecla == KeyEvent.VK_SPACE)	objTemp.setShooting(false);
			}
		}
	}
}
