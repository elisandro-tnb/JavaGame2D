package com.ks2002br.frameworks;

import java.awt.Color;
import java.awt.Graphics;

public class Bloco extends GameObject{

	public Bloco(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void tick() {		
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int)x,(int)y, 32, 32);
		//divisas do bloco
		g.setColor(Color.BLACK);
		g.drawRect((int)x,(int)y, 32, 32);		
		
	}
	
	

}
