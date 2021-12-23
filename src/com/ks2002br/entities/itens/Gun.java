package com.ks2002br.entities.itens;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ks2002br.frameworks.GameObject;
import com.ks2002br.frameworks.ObjectId;
import com.ks2002br.game.Game;
import com.ks2002br.graficos.Texturas;

public class Gun extends GameObject {
	
	private Texturas tex = Game.getInstance();

	public Gun(float x, float y, int type, ObjectId id) {
		super(x, y, type, id);	
	}

	@Override
	public void tick(LinkedList<GameObject> obj) {	
	}

	@Override
	public void render(Graphics g) {	
		
		if(type == 1) g.drawImage(tex.item[4], (int) x, (int) y,null);

	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
