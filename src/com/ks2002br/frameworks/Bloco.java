package com.ks2002br.frameworks;

/*
 * By Elisandro
 */
import java.awt.*;
import java.util.LinkedList;

public class Bloco extends GameObject {

	public Bloco(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> obj) {
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int) x, (int) y, 32, 32);
		// Caixas de colisao do bloco
		g.setColor(Color.RED);
		g.drawRect((int) x, (int) y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
