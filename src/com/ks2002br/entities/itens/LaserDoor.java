package com.ks2002br.entities.itens;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ks2002br.frameworks.GameController;
import com.ks2002br.frameworks.GameObject;
import com.ks2002br.frameworks.ObjectId;
import com.ks2002br.game.Game;
import com.ks2002br.graficos.Texturas;

public class LaserDoor extends GameObject {

	private Texturas tex = Game.getInstance();
	private GameController gc;

	public LaserDoor(float x, float y, int type, GameController gc, ObjectId id) {
		super(x, y, type, id);
		this.gc = gc;
	}

	@Override
	public void tick(LinkedList<GameObject> obj) {

		if (gc.isLaser_off()) {

			for (int i = 0; i < gc.obj.size(); i++) {
				GameObject tempObj = gc.obj.get(i);
				if (tempObj.getId() == ObjectId.BLOCO && tempObj.getType() == 7) {
					gc.removeObj(gc.obj.get(i));
				}
			}
		}

	}

	@Override
	public void render(Graphics g) {
		if (type == 6)
			g.drawImage(tex.laser_door[0], (int) x, (int) y, null);
		if (type == 8)
			g.drawImage(tex.laser_door[1], (int) x, (int) y, null);
		if (type == 7)
			g.drawImage(tex.laser_door[2], (int) x, (int) y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
