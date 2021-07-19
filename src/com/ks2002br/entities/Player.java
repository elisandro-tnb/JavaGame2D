package com.ks2002br.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.ks2002br.frameworks.GameObject;
import com.ks2002br.frameworks.ObjectId;

public class Player extends GameObject {

	public Player(float x, float y, ObjectId id) {
		super(x, y, id);	
	}

	public void tick() {
		x+=spdX;
		y+=spdY;
	}


	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y,64, 64);   
	}	
	
}
