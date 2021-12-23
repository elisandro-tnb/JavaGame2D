package com.ks2002br.entities.itens;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ks2002br.frameworks.GameObject;
import com.ks2002br.frameworks.ObjectId;
import com.ks2002br.game.Game;
import com.ks2002br.graficos.Texturas;

public class Medkit extends GameObject {
	
	private Texturas tex = Game.getInstance();

	public Medkit(float x, float y, int type, ObjectId id) {
		super(x, y, type, id);	
	}

	@Override
	public void tick(LinkedList<GameObject> obj) {	
	}

	@Override
	public void render(Graphics g) {		
		if(type == 1) g.drawImage(tex.item[0], (int) x, (int) y,null);
		if(type == 2) g.drawImage(tex.item[1], (int) x, (int) y,null);
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
