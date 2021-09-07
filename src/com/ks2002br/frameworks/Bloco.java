package com.ks2002br.frameworks;

/*
 * By Elisandro
 */
import java.awt.*;
import java.util.LinkedList;

import com.ks2002br.game.Game;
import com.ks2002br.graficos.Texturas;

public class Bloco extends GameObject {
	
	private int tipo;
	private Texturas tex = Game.getInstance();

	public Bloco(float x, float y, int tipo, ObjectId id) {
		super(x, y, id);
		this.tipo = tipo;
	}

	public void tick(LinkedList<GameObject> obj) {
	}

	public void render(Graphics g) {
		//g.setColor(Color.GREEN);
		//g.fillRect((int) x, (int) y, 32, 32);
		// Caixas de colisao do bloco
	//	g.setColor(Color.RED);
	//	g.drawRect((int) x, (int) y, 32, 32);
		
		if(tipo== 0) g.drawImage(tex.block[0],(int) x,(int) y, null);
		if(tipo== 1) g.drawImage(tex.block[1],(int) x,(int) y, null);
		if(tipo== 2) g.drawImage(tex.block[2],(int) x,(int) y, null);
		if(tipo== 3) g.drawImage(tex.block[3],(int) x,(int) y, null);
		
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
