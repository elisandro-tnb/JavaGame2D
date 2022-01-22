package com.ks2002br.entities.itens;

/*
 * By Elisandro 12/2021 revisao geral
 */
import java.awt.*;
import java.util.LinkedList;

import com.ks2002br.frameworks.*;
import com.ks2002br.game.Game;
import com.ks2002br.graficos.Texturas;

public class KeyCard extends GameObject {

	private Texturas tex = Game.getInstance();

	public KeyCard(float x, float y, int type, ObjectId id) {
		super(x, y, type, id);
	}

	@Override
	public void tick(LinkedList<GameObject> obj) {
	}

	@Override
	public void render(Graphics g) {
		if (type == 1) 	g.drawImage(tex.item[8], (int) x, (int) y, null);
		if (type == 2)	g.drawImage(tex.item[9], (int) x, (int) y, null);
		
		if(isDebug()) {
			g.setColor(Color.black);
			g.drawRect((int) x, (int) y, 32, 32);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
}
