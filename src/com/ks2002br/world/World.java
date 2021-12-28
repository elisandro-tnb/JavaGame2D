package com.ks2002br.world;

/*
 * By Elisandro 12/2021 revisao geral
 */
import java.awt.image.BufferedImage;

import com.ks2002br.entities.Enemy;
import com.ks2002br.entities.Player;
import com.ks2002br.entities.itens.Ammo;
import com.ks2002br.entities.itens.Gun;
import com.ks2002br.entities.itens.Key;
import com.ks2002br.entities.itens.KeyCard;
import com.ks2002br.entities.itens.Medkit;
import com.ks2002br.entities.itens.Potion;
import com.ks2002br.frameworks.Bloco;
import com.ks2002br.frameworks.GameController;
import com.ks2002br.frameworks.ObjectId;
import com.ks2002br.game.Game;

public class World {

	private BufferedImage level;
	private GameController gc;
	public static int w, h;

	public World(BufferedImage level, GameController gc) {
		this.level = level;
		this.gc = gc;
		w = level.getWidth();
		h = level.getHeight();

		//System.out.println("[DEBUG WORLD] MAPA x,y = " + w * 32 + " , " + h * 32);
	}

	public void carregarLevel() {
		for (int xx = 0; xx < h; xx++) {
			for (int yy = 0; yy < w; yy++) {
				int pixel = level.getRGB(xx, yy);
				if (pixel == 0xFFFFFFFF)
					Game.gc.addObj(new Bloco(xx * 32, yy * 32, 0, ObjectId.BLOCO)); // GRAMA
				else if (pixel == 0xFF7F3300)
					Game.gc.addObj(new Bloco(xx * 32, yy * 32, 1, ObjectId.BLOCO)); // TERRA ESQ
				else if (pixel == 0xFFFFD800)
					Game.gc.addObj(new Bloco(xx * 32, yy * 32, 2, ObjectId.BLOCO)); // TERRA CENTRAL
				else if (pixel == 0xFF7C3C11)
					Game.gc.addObj(new Bloco(xx * 32, yy * 32, 3, ObjectId.BLOCO)); // TERRA DIR

				else if (pixel == 0xFFFF0000)
					Game.gc.addObj(new Player(xx * 32, yy * 32, ObjectId.PLAYER, gc));
				else if (pixel == 0xFFFF6A00)
					Game.gc.addObj(new Enemy(xx * 32, yy * 32, ObjectId.ENEMY));
				else if (pixel == 0xFF0026FF)
					Game.gc.addObj(new Enemy(xx * 32, yy * 32, ObjectId.ENEMY));  //EM DECISAO

				else if (pixel == 0xFF0028FF)
					Game.gc.addObj(new Medkit(xx * 32, yy * 32, 1, ObjectId.MEDKIT));
				else if (pixel == 0xFF002CFF)
					Game.gc.addObj(new Medkit(xx * 32, yy * 32, 2, ObjectId.MEDKIT));
				else if (pixel == 0xFF0032FF)
					Game.gc.addObj(new Potion(xx * 32, yy * 32, 1, ObjectId.POTION));
				else if (pixel == 0xFF003AFF)
					Game.gc.addObj(new Potion(xx * 32, yy * 32, 2, ObjectId.POTION));
				else if (pixel == 0xFF0040FF)
					Game.gc.addObj(new Gun(xx * 32, yy * 32, 1, ObjectId.GUN));
				else if (pixel == 0xFF0046FF)
					Game.gc.addObj(new Ammo(xx * 32, yy * 32, 2, ObjectId.BOX_AMMO));
				else if (pixel == 0xFF0050FF)
					Game.gc.addObj(new Ammo(xx * 32, yy * 32, 1, ObjectId.AMMO));
				else if (pixel == 0xFF005AFF)
					Game.gc.addObj(new Key(xx * 32, yy * 32, ObjectId.KEY));
				else if (pixel == 0xFF0062FF)
					Game.gc.addObj(new KeyCard(xx * 32, yy * 32, 1, ObjectId.KEY_CARD));
				else if (pixel == 0xFF006EFF)
					Game.gc.addObj(new KeyCard(xx * 32, yy * 32, 2, ObjectId.KEY_CARD));
			}
		}
	}
}
