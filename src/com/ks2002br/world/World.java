package com.ks2002br.world;

import java.awt.image.BufferedImage;

import com.ks2002br.entities.Enemy;
import com.ks2002br.entities.Player;
import com.ks2002br.frameworks.Bloco;
import com.ks2002br.frameworks.GameController;
import com.ks2002br.frameworks.ObjectId;
import com.ks2002br.game.Game;

public class World {
	
	private BufferedImage level;
	private GameController gc;
	public static int w,h;
	
	public World(BufferedImage level, GameController gc) {
		this.level = level;
		this.gc = gc;
		w=level.getWidth();
		h=level.getHeight();
		
		System.out.println("[DEBUG WORLD] MAPA x,y = "+w*32+ " , "+h*32);
	}

	public void carregarLevel() {
		for (int xx = 0; xx < h; xx++) {
			for (int yy = 0; yy < w; yy++) {
				int pixel = level.getRGB(xx, yy);
				if(pixel == 0xFFFFFFFF) Game.gc.addObj(new Bloco(xx*32, yy*32, 0,ObjectId.BLOCO));       //GRAMA
				else if(pixel == 0xFF7F3300) Game.gc.addObj(new Bloco(xx*32, yy*32, 1,ObjectId.BLOCO)); // TERRA ESQ
				else if(pixel == 0xFFFFD800) Game.gc.addObj(new Bloco(xx*32, yy*32, 2,ObjectId.BLOCO)); //TERRA CENTRAL
				else if(pixel == 0xFF7C3C11) Game.gc.addObj(new Bloco(xx*32, yy*32, 3,ObjectId.BLOCO)); //TERRA DIR
				
				else if(pixel == 0xFFFF0000) Game.gc.addObj(new Player(xx*32,yy*32,3,ObjectId.PLAYER, gc));
				
				else if(pixel == 0xFFFF6A00 )Game.gc.addObj(new Enemy(xx*32,yy*32,0,ObjectId.ENEMY)); //INIMIGO
				else if(pixel == 0xFF0026FF)Game.gc.addObj(new Enemy(xx*32,yy*32,1,ObjectId.ENEMY));
			}
			
		}
		
		System.out.println("[DEBUG WORLD] MAPA CARREGADO   OK!");
		
	}
	
	
	
}
