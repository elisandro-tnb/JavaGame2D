package com.ks2002br.input;

 /*
 * By Elisandro 12/2021 revisao geral
 */
import java.awt.event.*;
import com.ks2002br.frameworks.*;

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
					objTemp.setSpdY(-15);
				}
		    	
				if (tecla == KeyEvent.VK_SPACE)	objTemp.setShooting(true);
				if (tecla == KeyEvent.VK_F3) {					
					if(!objTemp.isDebug())  	objTemp.setDebug(true);
					else 	objTemp.setDebug(false);			
				}
			}
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
