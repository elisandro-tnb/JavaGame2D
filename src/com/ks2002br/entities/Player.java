package com.ks2002br.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ks2002br.frameworks.GameController;
import com.ks2002br.frameworks.GameObject;
import com.ks2002br.frameworks.ObjectId;

public class Player extends GameObject {

	private int width = 64, height = 128; // Largura e altura do player ( obj)
	private int colW = width, colH = height; // Largura e altura da caixa de colisao

	private GameController gc;

	public Player(float x, float y, ObjectId id, GameController gc) {
		super(x, y, id);
		this.gc = gc;
	}

	public void tick(LinkedList<GameObject> obj) {
		x += spdX;
		y += spdY;

		verificarColisao(obj);

	}

	private void verificarColisao(LinkedList<GameObject> obj) {
		for (int i = 0; i < gc.obj.size(); i++) {
			GameObject tempObj = gc.obj.get(i);
			if (tempObj.getId() == ObjectId.BLOCO) {
				//topo
				if (getBounds().intersects(tempObj.getBounds())) {
					System.out.println("Caixa TOP BATEU NUM BLOCO QUALQUER");
				}
				//pes
				else if (getBoundsBaixo().intersects(tempObj.getBounds())) {
					System.out.println("Caixa BAIXO BATEU NUM BLOCO QUALQUER");
				} 
				//esq
				else if (getBoundsEsq().intersects(tempObj.getBounds())) {
					System.out.println("Caixa ESQ BATEU NUM BLOCO QUALQUER");
				} 
				//dir
				else if (getBoundsDir().intersects(tempObj.getBounds())) {
					System.out.println("Caixa DIR BATEU NUM BLOCO QUALQUER");
				}

			}
			
			//colisao cobaia
			else if(tempObj.getId() == ObjectId.COBAIA) {
				if (getBounds().intersects(tempObj.getBounds()))	System.out.println("CABECADA NO COBAIA");
						
				else if (getBoundsBaixo().intersects(tempObj.getBounds())) 	gc.removeObj(gc.obj.get(i));
			    else if (getBoundsEsq()  .intersects(tempObj.getBounds()))		gc.obj.get(i).setSpdX(-5);
				else if (getBoundsDir()    .intersects(tempObj.getBounds()))     gc.obj.get(i).setSpdX( 5);		
				
			}					

		}

	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, width, height);

		Graphics2D g2d = (Graphics2D) g;

		// REDERIZACAO DAS CAIXAS DE COLISOES
		// TOPO
		g.setColor(Color.RED);
		g2d.draw(getBounds());

		g.setColor(Color.GREEN);
		g2d.draw(getBoundsBaixo());

		g.setColor(Color.MAGENTA);
		g2d.draw(getBoundsDir());

		g.setColor(Color.CYAN);
		g2d.draw(getBoundsEsq());

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x + colW / 2 - (colW / 2) / 2, (int) y, colW / 2, colH / 2 - 1);
	}

	public Rectangle getBoundsBaixo() {
		return new Rectangle((int) x + colW / 2 - (colW / 2) / 2, (int) y + colH / 2, colW / 2, colH / 2 - 1);
	}

	public Rectangle getBoundsDir() {
		return new Rectangle((int) x + colW - 6, (int) y + 5, 5, colH - 10);
	}

	public Rectangle getBoundsEsq() {
		return new Rectangle((int) x, (int) y + 5, 5, colH - 10);
	}

}
