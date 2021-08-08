package com.ks2002br.frameworks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Bloco extends GameObject{

	public Bloco(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> obj) {		
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int)x,(int)y, 32, 32);
		//divisas do bloco
		g.setColor(Color.BLACK); //Sera a marca da caixa se colisao
		g.drawRect((int)x,(int)y, 32, 32);		
		
	}

	@Override
	public Rectangle getBounds() {		
		return new Rectangle((int) x , (int) y,32,32);
	}
	
	

}
