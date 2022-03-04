/**
 * Elisandro
 */
package com.ks2002br.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import com.ks2002br.frameworks.GameController;
import com.ks2002br.frameworks.GameObject;
import com.ks2002br.frameworks.ObjectId;
import com.ks2002br.game.Game;

public class UI {

	GameController gc;
	private Texturas tex = Game.getInstance();

	private Color cor_branco = new Color(255, 255, 255);
	private Color cor_vermelho = new Color(255, 0, 0);

	private int life;
	private int ammo;
	private String msg;
	private boolean msgOn;

	private int count = 0;

	private Font minhaFonteArcade_I, minhaFonteArcade_N, minhaFonteArcade_R;

	public UI(GameController gc) {
		this.gc = gc;

		try {

			minhaFonteArcade_I = Font.createFont(Font.TRUETYPE_FONT, new File("res/ARCADE_I.TTF"));
			minhaFonteArcade_N = Font.createFont(Font.TRUETYPE_FONT, new File("res/ARCADE_N.TTF"));
			minhaFonteArcade_R = Font.createFont(Font.TRUETYPE_FONT, new File("res/ARCADE_R.TTF"));

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsEnvironment ge1 = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();

			ge.registerFont(minhaFonteArcade_I);
			ge1.registerFont(minhaFonteArcade_N);
			ge2.registerFont(minhaFonteArcade_R);

		} catch (IOException | FontFormatException e) {
			System.out.println("[Debug UI ] Deu ruim ao carregar a fonte");
		}

	}

	public void tick() {

		if (msgOn) {
			count++;
		}

		for (int i = 0; i < gc.obj.size(); i++) {
			GameObject obj = gc.obj.get(i);
			if (obj.getId() == ObjectId.PLAYER) {

				life = obj.getLife();
				
				if(life <=0) {
					gc.obj.remove(i);
				}				
				
				
				ammo = obj.getAmmo();
				msg = obj.getMsg();
				msgOn = obj.isMsgOn();

				if (count >= 200) {
					msgOn = false;
					count = 0;
					gc.obj.get(i).setMsgOn(false);
				}

			}

		}

	}

	public void render(Graphics g) {

		// BARRA DE VIDA
		g.setColor(Color.green); // SETA A COR
		g.fillRect(52, 20, life * 2, 16); // DESENHA A BARRA CONFORME O LIFE

		g.drawImage(tex.ui_item[0], 10, 14, null); // SPRITE HEALT BAR
		g.drawImage(tex.ui_item[1], 270, 16, null); // GUN
		g.drawImage(tex.ui_item[2], 280, 24, null); // Bullet

		g.setColor(cor_branco);
		g.setFont(minhaFonteArcade_R.deriveFont(14f));
		g.drawString(" x" + ammo, 290, 42);

		g.drawImage(tex.ui_item[3], 380, 20, null); // KeyCardDisable

		if (msgOn) {
			g.setColor(cor_vermelho);
			g.setFont(minhaFonteArcade_I.deriveFont(18f));
			g.drawString(msg, 100, 250);
		}
	}
}
