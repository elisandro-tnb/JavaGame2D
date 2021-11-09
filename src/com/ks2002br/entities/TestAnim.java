package com.ks2002br.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.ks2002br.frameworks.Animation;
import com.ks2002br.frameworks.GameObject;
import com.ks2002br.frameworks.ObjectId;
import com.ks2002br.graficos.Texturas;

public class TestAnim extends GameObject{
	
	private Texturas tex = new Texturas();
	
	private Animation animIdle; // FRAMES IDLE
	private Animation animCima, animBaixo;  //FRAMES UP/DOWN
	private Animation animEsq, animDir;		//FRAMES LEFT/RIGHT

	public TestAnim(float x, float y, ObjectId id) {
		super(x, y, id);	
		
		animIdle    = new Animation(10,tex.player_idle);
		animCima  = new Animation(15, tex.playerUp);	 
		animBaixo = new Animation(15, tex.playerDown);	
		animEsq    = new Animation(15, tex.playerLeft);	
		animDir      = new Animation(10, tex.playerRight);
		
	}

	@Override
	public void tick(LinkedList<GameObject> obj) {
		animCima.runAnimation();  //EXECUTANDO ANIMACAO
		animBaixo.runAnimation();
		animEsq.runAnimation();
		animDir.runAnimation();
		animIdle.runAnimation();
	}

	@Override
	public void render(Graphics g) {
		animIdle.renderAnimation(g, (int) x, (int) y, 128, 256);
		animCima.renderAnimation(g, 160, 60,128,256); // REDERIZANDO ANIMACAO
		animBaixo.renderAnimation(g, 260, 60,128,256);
		animEsq.renderAnimation(g, 360, 60,128,256);
		animDir.renderAnimation(g, 460, 60,128,256);		
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
