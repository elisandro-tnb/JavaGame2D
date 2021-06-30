package com.ks2002br.frameworks;

import java.awt.Graphics;
import java.util.LinkedList;

public class GameController {
	
	private LinkedList<GameObject> obj = new LinkedList<>();
	
	private GameObject tempObj;
	
	public void update() {
		for (int i = 0; i < obj.size(); i++) {
			tempObj = obj.get(i);
			tempObj.tick();
		}
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < obj.size(); i++) {
			tempObj = obj.get(i);
			tempObj.render(g);
		}
	}
	
	public void addObj(GameObject obj) {
		this.obj.add(obj);
		System.out.println("[DEBUG GameController] UM OBJETO FOI CRIADO");
	}

	public void removeObj(GameObject obj) {
		this.obj.remove(obj);
		System.out.println("[DEBUG GameController] UM OBJETO FOI REMOVIDO");
	}		
	
}
