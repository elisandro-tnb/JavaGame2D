package com.ks2002br.frameworks;
/*
 * By Elisandro
 */
import java.awt.*;
import java.util.LinkedList;

public class Cobaia extends GameObject {

	public Cobaia(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> obj) {
		x += spdX;
		y += spdY;
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int) x, (int) y, 64, 64);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 64);
	}

}
