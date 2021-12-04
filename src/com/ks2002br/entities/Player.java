package com.ks2002br.entities;
/*
 * by Elisandro
 */
import java.awt.*;
import java.util.LinkedList;
import com.ks2002br.frameworks.*;
import com.ks2002br.game.Game;
import com.ks2002br.graficos.Texturas;

public class Player extends GameObject {

	private int width = 32, height = 64; // Largura e altura do player ( obj)
	private int colW = width, colH = height; // Largura e altura da caixa de colisao
	
	private float gravity = 0.5f;
	private final float MAX_SPD = 10;
	
	private int tipo;
	private Texturas tex = Game.getInstance();

	private GameController gc;
	
	private Animation animIdle, animEsq, animDir;	
	private int dir = 1;  //1 = direita  -1 = esquerda
	private boolean move = false;

	public Player(float x, float y, int tipo, ObjectId id, GameController gc) {
		super(x, y, id);
		this.gc = gc;
		this.tipo = tipo;
		
		startPlayer();
	}

	private void startPlayer() {
		
	animIdle   = new Animation(10,tex.player_idle);
	animEsq   = new Animation(5, tex.playerLeft);	
	animDir     = new Animation(5, tex.playerRight);
		
	}

	public void tick(LinkedList<GameObject> obj) {
		x += spdX;
		y += spdY;			

		//esq/dir
		if(spdX > 0 ) tipo=2;
		else if(spdX < 0 ) tipo=3;
		//up/down
		if(spdY < 0 ) tipo=0;
		else if(spdY > 0) tipo=1;
				
		if(falling || jumping) {
			spdY += gravity;			
			if(spdY > MAX_SPD) spdY = MAX_SPD;
		}
		
		if (spdX > 0 ) dir = 1;
		if (spdX < 0 ) dir = -1;		
		
		startAnim();
		verificarColisao(obj);	

	}


	private void startAnim() {

		animEsq.runAnimation();
		animDir.runAnimation();
		animIdle.runAnimation();
		
		if (spdX != 0 ) move = true;
		else move = false;		
		
	}

	private void verificarColisao(LinkedList<GameObject> obj) {
		for (int i = 0; i < gc.obj.size(); i++) {
			GameObject tempObj = gc.obj.get(i);
			if (tempObj.getId() == ObjectId.BLOCO) {

				if (getBounds().intersects(tempObj.getBounds())) {
					spdY=0;
					y = tempObj.getY() + 32;
				}

				else  if (getBoundsBaixo().intersects(tempObj.getBounds())) {
					spdY = 0;
					y = tempObj.getY() - height +2;
					falling = false;
					jumping = false;
				}else {
					falling=true;
				}

				 if (getBoundsEsq().intersects(tempObj.getBounds())) {
					x = tempObj.getX() + 32;
				}

				else if (getBoundsDir().intersects(tempObj.getBounds())) {
					x = tempObj.getX() - width;
				}

			}

			// colisao cobaia
			else if (tempObj.getId() == ObjectId.ENEMY) {
				 if (getBounds().intersects(tempObj.getBounds())) System.out.println("CABECADA NO COBAIA");
				 else if (getBoundsBaixo().intersects(tempObj.getBounds()))
				 gc.removeObj(gc.obj.get(i));
				 else if (getBoundsEsq() .intersects(tempObj.getBounds()))
				 gc.obj.get(i).setSpdX(-5);
				 else if (getBoundsDir() .intersects(tempObj.getBounds()))
				 gc.obj.get(i).setSpdX( 5);

			}

		}

	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if(move) {
			
			if(dir == 1) animDir.renderAnimation(g2d, (int) x, (int) y);
			else if(dir == -1) animEsq.renderAnimation(g2d, (int) x, (int) y);
			
			
		}else {
			animIdle.renderAnimation(g2d, (int) x, (int) y);			
		}
		
		
		

		// REDERIZACAO DAS CAIXAS DE COLISOES
		if (gc.isDebug()) {
			g.setColor(Color.RED);
			g2d.draw(getBounds());

			g.setColor(Color.GREEN);
			g2d.draw(getBoundsBaixo());

			g.setColor(Color.MAGENTA);
			g2d.draw(getBoundsDir());

			g.setColor(Color.CYAN);
			g2d.draw(getBoundsEsq());

			// Debug x,y
			g.setFont(new Font("arial", Font.BOLD, 16));
			g.setColor(new Color(255, 50, 20));
			g.drawString("x,y: " + x + " / " + y + " " + this.id, (int) x, (int) y);
		}

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
