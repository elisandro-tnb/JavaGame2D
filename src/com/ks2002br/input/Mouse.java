/**
 * Elisandro
 */
package com.ks2002br.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author Elisandro
 *
 */
public class Mouse implements MouseListener,MouseMotionListener {
 
	//VARIAVEIS
	private static int  mouseX = -1;
	private static int  mouseY = -1;
	private static int  mouseButton =-1;

	//METODOS DO MOUSE USADOS
	public void mouseMoved(MouseEvent e) {	
		mouseX = e.getX();
		mouseY= e.getY();
	}
	
	public void mousePressed(MouseEvent e) { mouseButton = e.getButton();	}
	
	public void mouseReleased(MouseEvent e) { mouseButton = -1;	}

	//METODOS NAO UTILIZADOS
	public void mouseEntered(MouseEvent e) {	}
	
	public void mouseExited(MouseEvent e) {	}
	
	public void mouseDragged(MouseEvent e) {	}
	
	public void mouseClicked(MouseEvent e) {	}

	
	//METODOS GETS E SETS
	public static int getMouseX() {
		return mouseX;
	}

	public static void setMouseX(int mouseX) {
		Mouse.mouseX = mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}

	public static void setMouseY(int mouseY) {
		Mouse.mouseY = mouseY;
	}

	public static int getMouseButton() {
		return mouseButton;
	}

	public static void setMouseButton(int mouseButton) {
		Mouse.mouseButton = mouseButton;
	}

}
