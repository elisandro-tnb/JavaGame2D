package com.ks2002br.entities;

/*
 * By Elisandro 12/2021 revisao geral
 */
import java.awt.*;
import java.util.LinkedList;
import com.ks2002br.frameworks.*;
import com.ks2002br.game.Game;
import com.ks2002br.graficos.Texturas;
import com.ks2002br.sound.LoadSound;

public class Player extends GameObject {

	private int width = 32, height = 64;
	private int colW = width, colH = height;
	private float gravity = 0.5f;
	private final float MAX_SPD = 10;
	private boolean move = false;
	private long firingTimer, firingDelay;

	private boolean isGun = false;
	
	private final int MAX_LIFE = 100;
	

	private Texturas tex = Game.getInstance();
	private GameController gc;
	private Animation animIdle, animEsq, animDir;

	public Player(float x, float y, ObjectId id, GameController gc) {
		super(x, y, id);
		this.gc = gc;

		startPlayer();
	}

	private void startPlayer() {
		firingDelay = System.nanoTime();
		firingDelay = 400;

		animIdle = new Animation(10, tex.player_idle);
		animEsq = new Animation(5, tex.playerLeft);
		animDir = new Animation(5, tex.playerRight);
	}

	public void tick(LinkedList<GameObject> obj) {
		x += spdX;
		y += spdY;

		if (falling || jumping) {
			spdY += gravity;
			if (spdY > MAX_SPD)
				spdY = MAX_SPD;
		}

		if (spdX > 0)
			dir = 1;
		if (spdX < 0)
			dir = -1;

		startAnim();
		isCollision(obj);

		if (shooting && isGun && ammo >=1) {
			long elapsed = (System.nanoTime() - firingTimer) / 1000000;
			if (elapsed > firingDelay) {
				gc.addObj(new Bullet(x + 8, y + 10, dir * 1, System.nanoTime(), gc, ObjectId.BULLET));
				firingTimer = System.nanoTime();
				LoadSound.shot.play();
				ammo--;
			}
		}
	}

	private void startAnim() {
		animEsq.runAnimation();
		animDir.runAnimation();
		animIdle.runAnimation();

		if (spdX != 0)
			move = true;
		else
			move = false;
	}

	private void isCollision(LinkedList<GameObject> obj) {
		for (int i = 0; i < gc.obj.size(); i++) {
			GameObject tempObj = gc.obj.get(i);
			if (tempObj.getId() == ObjectId.BLOCO) {
				if (getBounds().intersects(tempObj.getBounds())) {
					spdY = 0;
					y = tempObj.getY() + 32;
				}

				else if (getBoundsBaixo().intersects(tempObj.getBounds())) {
					spdY = 0;
					y = tempObj.getY() - height + 2;
					falling = false;
					jumping = false;
				} else {
					falling = true;
				}

				if (getBoundsEsq().intersects(tempObj.getBounds()))
					x = tempObj.getX() + 32;
				else if (getBoundsDir().intersects(tempObj.getBounds()))
					x = tempObj.getX() - width;
			}

			// colisao cobaia
			else if (tempObj.getId() == ObjectId.ENEMY) {
				if (getBounds().intersects(tempObj.getBounds()))
					System.out.println("CABECADA NO COBAIA");
				else if (getBoundsBaixo().intersects(tempObj.getBounds())) {
					gc.removeObj(gc.obj.get(i));
					LoadSound.morri.play();
				} else if (getBoundsEsq().intersects(tempObj.getBounds())) {
					gc.obj.get(i).setSpdX(-5);
					LoadSound.hurt.play();
					life-=5;
					verificaLife();
				} else if (getBoundsDir().intersects(tempObj.getBounds())) {
					gc.obj.get(i).setSpdX(5);
					LoadSound.hurt.play();
					life-=5;
					verificaLife();
				}
			}

			// COLETA DE ITENS

			else if (tempObj.getId() == ObjectId.AMMO) {
				if (getBounds().intersects(tempObj.getBounds())) {
					ammo += 5;
					LoadSound.collect.play();
					gc.removeObj(gc.obj.get(i));					
					criaMensagem("COLETOU 5 BALAS");					
				
				}

			}

			// CAIXA DE BALAS
			else if (tempObj.getId() == ObjectId.BOX_AMMO) {
				if (getBounds().intersects(tempObj.getBounds())) {
					ammo += 15;
					LoadSound.collect.play();
					gc.removeObj(gc.obj.get(i));
					criaMensagem("COLETOU 15 BALAS CX");	
					
				}

			}

			// MEDKIT
			else if (tempObj.getId() == ObjectId.MEDKIT) {
				if (getBounds().intersects(tempObj.getBounds())) {
					life += 10;
					gc.removeObj(gc.obj.get(i));
					criaMensagem("COLETOU MEDKIT");	
				}

			}

			// ARMA
			else if (tempObj.getId() == ObjectId.GUN) {
				if (getBounds().intersects(tempObj.getBounds())) {
					isGun = true;
					LoadSound.collect.play();
					gc.removeObj(gc.obj.get(i));
					criaMensagem("COLETOU ARMA");	
				}

			}

			// POTION
			else if (tempObj.getId() == ObjectId.POTION) {
				if (getBounds().intersects(tempObj.getBounds())) {
				//	potion += 10;
					gc.removeObj(gc.obj.get(i));
					criaMensagem("COLETOU POCAO");	
				}

			}

			// KEY
			else if (tempObj.getId() == ObjectId.KEY) {
				if (getBounds().intersects(tempObj.getBounds())) {
					//key++;
					gc.removeObj(gc.obj.get(i));
					criaMensagem("COLETOU CHAVE");	
				}

			}

			// KEYCARD
			else if (tempObj.getId() == ObjectId.KEY_CARD) {
				if (getBounds().intersects(tempObj.getBounds())) {
					gc.setKey_card(true);
					gc.removeObj(gc.obj.get(i));
					criaMensagem("COLETOU KEYCARD");	
				}

			}
			
			// CARD READER
			else if (tempObj.getId() == ObjectId.CARD_READER) {
				if (getBounds().intersects(tempObj.getBounds())) {
					if(!gc.isKey_card())	criaMensagem("ACESSO NEGADO - KEYCARD ?");	
					else {
						gc.setLaser_off(true);
						criaMensagem("KEY CARD OK");	
					}
				}

			}
			
			//FLAG EXIT
			else if (tempObj.getId() == ObjectId.FLAG_EXIT) {
				if (getBounds().intersects(tempObj.getBounds())) {
						Game.clearLevel();
				}

			}

		}
	}

	private void verificaLife() {
	
		if(life >=100) {
			life=MAX_LIFE;
		}
		else if(life <=0){
			//criaMensagem(" - GAME OVER - ");
			life = 0;
			Game.gameState = "GAME_OVER";
		}
		
		
	}

	private void criaMensagem(String message) {
		msgOn = true;
		msg = message;
		
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (move) {
			if (dir == 1)
				animDir.renderAnimation(g2d, (int) x, (int) y);
			else if (dir == -1)
				animEsq.renderAnimation(g2d, (int) x, (int) y);
		} else {
			animIdle.renderAnimation(g2d, (int) x, (int) y);
		}

		// REDERIZACAO DAS CAIXAS DE COLISOES
		if (isDebug()) {
			g.setColor(Color.RED);
			g2d.draw(getBounds());

			g.setColor(Color.GREEN);
			g2d.draw(getBoundsBaixo());

			g.setColor(Color.MAGENTA);
			g2d.draw(getBoundsDir());

			g.setColor(Color.CYAN);
			g2d.draw(getBoundsEsq());

			g.setFont(new Font("arial", Font.BOLD, 16));
			g.setColor(new Color(255, 50, 20));
			// g.drawString("x,y: " + x + " / " + y + " " + this.id, (int) x, (int) y);
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
