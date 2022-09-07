package com.ks2002br.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ks2002br.frameworks.Animation;
import com.ks2002br.frameworks.GameController;
import com.ks2002br.frameworks.GameObject;
import com.ks2002br.frameworks.ObjectId;
import com.ks2002br.game.Game;
import com.ks2002br.graficos.Texturas;

public class Boss extends GameObject{
	
	private int bossW,bossH;
	
	private Texturas tex = Game.getInstance();
	private Animation animIdle;
	private GameController gc;

	public Boss(float x, float y, int w, int h, GameController gc, ObjectId id) {
		super(x, y, id);
		this.bossW = w;
		this.bossH = h;
		this.gc = gc;
	
		animIdle = new Animation(15, tex.bat_idle);
		
	}


	public void tick(LinkedList<GameObject> obj) {
		x += spdX;
		y+= spdY;
		
		isCollision(obj);
		
		animIdle.runAnimation();
		
	}
	
	
	
	private void isCollision(LinkedList<GameObject> obj) {
		for (int i = 0; i < gc.obj.size(); i++) {
			GameObject tempObj = gc.obj.get(i);
			if (tempObj.getId() == ObjectId.BLOCO) {
				if (getBoundsUp().intersects(tempObj.getBounds())) {
					y = tempObj.getY() + 32;
					
						//System.out.println("COLISAO BASICA TOPO");
					}
				
				if (getBoundsDown().intersects(tempObj.getBounds())) {
				//	System.out.println("COLISAO BASICA PES");
					y = tempObj.getY() - bossH +2;
				}
				
				if (getBoundsLeft().intersects(tempObj.getBounds())) {
					x = tempObj.getX()  + 32;
				}
				
				if (getBoundsRight().intersects(tempObj.getBounds())) {
					x = tempObj.getX()  -bossH;
				}
				
				
				}
			}
	}
	
	
	
	


	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		animIdle.renderAnimation(g, (int) x, (int) y, bossW, bossH);
		
		if(debug) {
			g.setColor(Color.YELLOW);
			g2d.draw(getBounds());		
			
			g.setColor(Color.RED);
			g2d.draw(getBoundsUp());			
			
			g.setColor(Color.GREEN);
			g2d.draw(getBoundsDown());			
			
			g.setColor(Color.CYAN);
			g2d.draw(getBoundsLeft());			
			
			g.setColor(Color.MAGENTA);
			g2d.draw(getBoundsRight());			
			
		}
		
	}


	public Rectangle getBounds() {
		return new Rectangle( (int) x + 10, (int) y +20, bossW -20 , bossH -40);
	}
	
	public Rectangle getBoundsUp() {
		return new Rectangle( (int) x + 10, (int) y, bossW -20, 15);
	}
	
	public Rectangle getBoundsDown() {
		return new Rectangle( (int) x + 10 , (int) y + 110, bossW -20, 15);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle( (int) x , (int) y + 5, 5, bossH -10);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle( (int) x + bossW - 6, (int) y + 5,5, bossH - 10);
	}
	
	

}
