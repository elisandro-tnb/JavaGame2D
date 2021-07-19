package com.ks2002br.frameworks;

import java.awt.Color;
import java.awt.Graphics;

public class Cobaia extends GameObject {

	public Cobaia(float x, float y, ObjectId id) {
		super(x, y, id);	
	}

	public void tick() {
		x+=spdX;
		y+=spdY;
	}


	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y,64, 64);   
	}	
	
}
