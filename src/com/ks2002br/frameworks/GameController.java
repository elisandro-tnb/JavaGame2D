package com.ks2002br.frameworks;

import java.awt.Graphics;
import java.util.LinkedList;

import com.ks2002br.game.Game;

public class GameController {
	
	public LinkedList<GameObject> obj = new LinkedList<>();
	
	private GameObject tempObj;
	
	public void update() {
		for (int i = 0; i < obj.size(); i++) {
			tempObj = obj.get(i);
			tempObj.tick();
		}
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < obj.size(); i++) {
			tempObj = obj.get(i);
			tempObj.render(g);
		}
	}
	
	public void addObj(GameObject obj) {
		this.obj.add(obj);
		System.out.println("[DEBUG GameController] UM OBJETO FOI CRIADO :" +obj.getId());
	}

	public void removeObj(GameObject obj) {
		this.obj.remove(obj);
		System.out.println("[DEBUG GameController] UM OBJETO FOI REMOVIDO :"+obj.getId());
	}
	

	public void criarMundo() {
		//Mundo de blocos de 32x32
		for (int xx = 0; xx < Game.LARGURA*2+32; xx+=32) {
			//TOP
			addObj(new Bloco(xx, 0, ObjectId.BLOCO));
			
			//CHAO
			addObj(new Bloco(xx, Game.ALTURA*2-32,ObjectId.BLOCO));
			
			//ESQUERDA
			addObj(new Bloco(0, xx+32,ObjectId.BLOCO));
			
			//DIREITA
			addObj(new Bloco(Game.ALTURA*2-32, xx,ObjectId.BLOCO));			
			
		}
		
		
	}	
	
}
