package com.ks2002br.entities;
/*
 * By Elisandro
 */
import java.awt.*;
import java.util.LinkedList;

import com.ks2002br.frameworks.GameObject;
import com.ks2002br.frameworks.ObjectId;
import com.ks2002br.game.Game;
import com.ks2002br.graficos.Texturas;

public class Enemy extends GameObject {

	private int tipo;
	private Texturas tex = Game.getInstance();
	
	public Enemy(float x, float y, int t, ObjectId id) {
		super(x, y, id);
		this.tipo = t;
	}

	public void tick(LinkedList<GameObject> obj) {
		x += spdX;
		y += spdY;
	}

	public void render(Graphics g) {
	//	g.setColor(Color.blue);
	//	g.fillRect((int) x, (int) y, 64, 64);
		
		if(tipo== 0) g.drawImage(tex.enemy[0],(int) x,(int) y,null);
		if(tipo== 1) g.drawImage(tex.enemy[1],(int) x,(int) y, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 64);
	}

}
