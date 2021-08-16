package com.ks2002br.world;

import com.ks2002br.frameworks.GameObject;
import com.ks2002br.game.Game;

public class Camera {

	private float camX,camY;

	public Camera(float camX, float camY) {

		this.camX = camX;
		this.camY = camY;
	}
	
	public void tick(GameObject player) {
		camX = -player.getX() + Game.LARGURA/2;
//		camY++;
	}

	public float getCamX() {
		return camX;
	}

	public void setCamX(float camX) {
		this.camX = camX;
	}

	public float getCamY() {
		return camY;
	}

	public void setCamY(float camY) {
		this.camY = camY;
	}
	
	
	
}
