package com.ks2002br.frameworks;

/*
 * By Elisandro 12/2021 revisao geral
 */
import java.awt.Graphics;
import java.util.LinkedList;

public class GameController {

	public LinkedList<GameObject> obj = new LinkedList<>();
	private GameObject tempObj;

	public void update() {
		int contador = 0;

		for (int i = 0; i < obj.size(); i++) {
			tempObj = obj.get(i);
			tempObj.tick(obj);
		}

		for (int i = 0; i < obj.size(); i++) {
			tempObj = obj.get(i);
			if (tempObj.getId() == ObjectId.BULLET) {
				contador++;
			}
		}

		//System.out.println("[DEBUG GC - Bullets in Game = " + contador);

	}

	public void draw(Graphics g) {
		for (int i = 0; i < obj.size(); i++) {
			tempObj = obj.get(i);
			tempObj.render(g);
		}
	}

	public void addObj(GameObject obj) {
		this.obj.add(obj);
	}

	public void removeObj(GameObject obj) {
		this.obj.remove(obj);
	}

//Gets e Sets
//	public boolean isDebug() {
//		return debug;
//	}
//
//	public void setDebug(boolean debug) {
//		this.debug = debug;
//	}
}
