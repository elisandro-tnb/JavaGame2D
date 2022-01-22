package com.ks2002br.entities;

/*
 * By Elisandro 12/2021 revisao geral
 */
import java.awt.*;
import java.util.LinkedList;

import com.ks2002br.frameworks.*;

public class Bullet extends GameObject {

	private GameController gc;

	public Bullet(float x, float y, int velX, long timer, GameController gc, ObjectId id) {
		super(x, y, id);
		this.spdX = velX;
		this.timer = timer;
		this.gc = gc;
	}

	@Override
	public void tick(LinkedList<GameObject> obj) {
		x += spdX;
		isCollision();
	}

	private void isCollision() {

		for (int i = 0; i < gc.obj.size(); i++) {
			GameObject objTemp = gc.obj.get(i);

			if (objTemp.getId() == ObjectId.BLOCO) { 
				if (getBounds().intersects(objTemp.getBounds())) { 	
					gc.removeObj(this);
				}
			}

			if (objTemp.getId() == ObjectId.ENEMY) {
				if (getBounds().intersects(objTemp.getBounds())) {
					gc.removeObj(gc.obj.get(i));
					gc.removeObj(this);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(255, 0, 0));
		g.fillOval((int) x, (int) y, 16, 16);
		
		if(isDebug()) {
			g.setColor(Color.black);
			g.drawRect((int) x, (int) y, 16, 16);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

}
