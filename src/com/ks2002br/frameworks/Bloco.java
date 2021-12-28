package com.ks2002br.frameworks;

/*
 * By Elisandro 12/2021 revisao geral
 */
import java.awt.*;
import java.util.LinkedList;

import com.ks2002br.game.Game;
import com.ks2002br.graficos.Texturas;

public class Bloco extends GameObject {

	private Texturas tex = Game.getInstance();

	public Bloco(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	public void tick(LinkedList<GameObject> obj) {	}

	public void render(Graphics g) {
		if(type== 0) g.drawImage(tex.block[0],(int) x,(int) y, null);
		if(type== 1) g.drawImage(tex.block[1],(int) x,(int) y, null);
		if(type== 2) g.drawImage(tex.block[2],(int) x,(int) y, null);
		if(type== 3) g.drawImage(tex.block[3],(int) x,(int) y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
}
