package com.ks2002br.frameworks;

/*
 * By Elisandro
 */
import java.awt.Graphics;
import java.util.LinkedList;

import com.ks2002br.game.Game;

public class GameController {
/*
 * By Elisandro
 */
	private boolean debug = false;

	public LinkedList<GameObject> obj = new LinkedList<>();

	private GameObject tempObj;

	public void update() {
		for (int i = 0; i < obj.size(); i++) {
			tempObj = obj.get(i);
			tempObj.tick(obj);
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
	}

	public void removeObj(GameObject obj) {
		this.obj.remove(obj);
	}

	/*
	public void criarMundo() {

		for (int xx = 0; xx < Game.LARGURA * 2 + 32; xx += 32) {

			addObj(new Bloco(xx, 0, ObjectId.BLOCO));
			addObj(new Bloco(xx, Game.ALTURA * 2 - 32, ObjectId.BLOCO));
			addObj(new Bloco(0, xx + 32, ObjectId.BLOCO));
			addObj(new Bloco(Game.ALTURA * 2 - 32, xx, ObjectId.BLOCO));

			for (int i = 0; i < 10; i++) {
				addObj(new Bloco(160 + i * 32, 350, ObjectId.BLOCO));
			}

			for (int i = 0; i < 5; i++) {
				addObj(new Bloco(96 + i * 32, 190, ObjectId.BLOCO));
			}

		}

	}
	*/
//Gets e Sets
	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

}
