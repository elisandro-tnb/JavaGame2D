package com.ks2002br.frameworks;

/*
 * By Elisandro 12/2021 revisao geral
 */
import java.awt.Graphics;
import java.util.LinkedList;

public class GameController {

	private boolean debug;
	
	public LinkedList<GameObject> obj = new LinkedList<>();
	private GameObject tempObj;
	
	private boolean key_card;
	private boolean laser_off;
	

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
		//System.out.println("[DEBUG GC - OBJ IN GAME  = " + obj.size());
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
	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public boolean isKey_card() {
		return key_card;
	}

	public void setKey_card(boolean key_card) {
		this.key_card = key_card;
	}

	public boolean isLaser_off() {
		return laser_off;
	}

	public void setLaser_off(boolean laser_off) {
		this.laser_off = laser_off;
	}
}
