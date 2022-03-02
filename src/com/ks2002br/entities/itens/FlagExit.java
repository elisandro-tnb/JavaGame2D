package com.ks2002br.entities.itens;

import java.awt.*;
import java.util.LinkedList;

import com.ks2002br.frameworks.*;
import com.ks2002br.game.Game;
import com.ks2002br.graficos.Texturas;

public class FlagExit extends GameObject {
	
	private Texturas tex = Game.getInstance();

	public FlagExit(float x, float y,  ObjectId id) {
		super(x, y, id);	
	}

	@Override
	public void tick(LinkedList<GameObject> obj) {	
	}

	@Override
	public void render(Graphics g) {	
			g.drawImage(tex.item[11], (int) x, (int) y,null);
			
			if(isDebug()) {
				g.setColor(Color.yellow);
				g.drawRect((int) x, (int) y, 32, 16);
			}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 16);
	}
}
