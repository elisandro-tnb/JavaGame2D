package com.ks2002br.frameworks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import com.ks2002br.game.Game;
import com.ks2002br.world.World;

public class InforDebug {

	private int xx, yy;
	private int shoots, enemy;
	private Point map_size, player_world;

	private GameController gc;

	public InforDebug(GameController gc) {
		this.gc = gc;
		init();
	}

	private void init() {
		xx = Game.LARGURA / 2;
		yy = Game.ALTURA / 2;

		map_size = new Point();
		player_world = new Point();	

	}

	public void update() {

		if (gc.isDebug()) {
			
			enemy  = 0;
			shoots  = 0;

			map_size.x = World.w * 32;
			map_size.y = World.h * 32;

			for (int i = 0; i < gc.obj.size(); i++) {

				GameObject tempObj = gc.obj.get(i);
				 
				tempObj.setDebug(true);

				if (tempObj.getId() == ObjectId.PLAYER) {
					
					player_world.x = (int) tempObj.getX();
					player_world.y = (int) tempObj.getY();						
					
				}

				if (tempObj.getId() == ObjectId.ENEMY) {
					enemy++;
				}

				if (tempObj.getId() == ObjectId.BULLET) {
					shoots++;
				}

				if (tempObj.getId() == ObjectId.ENEMY) {
					//enemy++;
				}

			}

		} else {
			for (int i = 0; i < gc.obj.size(); i++) {

				GameObject obj = gc.obj.get(i);
				obj.setDebug(false);
			}
		}

	}

	public void render(Graphics g) {

		if (gc.isDebug()) {

			Color c = new Color(255, 0, 0);
			g.setColor(c);
			g.setFont(new Font("Serif", Font.BOLD, 24));
			g.drawString("MAPA      : " + (int) map_size.getX() + " / " + (int) map_size.getY(), xx - 100, yy - 100);
			g.drawString("PLAYER  : " + (int) player_world.getX() + " / " + (int) player_world.getY(), xx - 100, yy - 80);
			g.drawString("Shoots  : " + shoots   , xx - 100, yy - 60);
			g.drawString("Inimigos  : " + enemy   , xx - 100, yy - 40);

			// g.drawString("Player :: " + x + " / " + y + " " + this.id, (int) x, (int) y);

		}

	}

}
