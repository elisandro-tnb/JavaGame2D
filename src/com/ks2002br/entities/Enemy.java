package com.ks2002br.entities;
/*
 * By Elisandro
 */
import java.awt.*;
import java.util.LinkedList;

import com.ks2002br.frameworks.Animation;
import com.ks2002br.frameworks.GameObject;
import com.ks2002br.frameworks.ObjectId;
import com.ks2002br.game.Game;
import com.ks2002br.graficos.Texturas;

public class Enemy extends GameObject {

	private int tipo;
	private Texturas tex = Game.getInstance();
	
	private Animation animIdle;	
	
	private int posInit = 0;
	private int dir = 1;
	private int pixelsTemp = 64;	
	
	public Enemy(float x, float y, int t, ObjectId id) {
		super(x, y, id);
		this.tipo = t;
		
		posInit = (int) x;
		
		animIdle = new Animation(10,tex.bat_idle);
		
	}

	public void tick(LinkedList<GameObject> obj) {
		x += spdX;
		y += spdY;
		
		animIdle.runAnimation();
		
		iaBurra();
		
	}

	private void iaBurra() {
		
		if(dir == 1) {
			if(x <= posInit + pixelsTemp) spdX = 1;  //  50 <=  50+64=114
			else dir = -1;
		}else {
			if(x >= posInit) spdX = -1;
			else dir = 1;
		}
		
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
	//	g.fillRect((int) x, (int) y, 64, 64);
		g.drawString("X =  "+x+ "  dir = "+dir, (int) x,(int) y);
		//if(tipo== 0) g.drawImage(tex.enemy[0],(int) x,(int) y,null);
		//if(tipo== 1) g.drawImage(tex.enemy[1],(int) x,(int) y, null);
		
		//Animacao idle teste
		animIdle.renderAnimation(g,(int) x, (int) y);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 64);
	}

}
