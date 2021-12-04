package com.ks2002br.graficos;

import java.awt.image.BufferedImage;

public class Texturas {

	private FolhaSprites  blockSheet, enemySheet, playerSheet;  //Instancia/referencia
	
	private BufferedImage bloco_tex,  enemy_tex, player_tex;
		
	public BufferedImage[] block   = new BufferedImage[4]; //SPRITES DOS BLOCOS
	public BufferedImage[] enemy = new BufferedImage[2]; //SPRITES DOS BLOCOS
	public BufferedImage[] player_idle = new BufferedImage[2]; //SPRITES DO PLAYER PARADO
	
	public BufferedImage[] playerUp      = new BufferedImage[5]; // SPRITES DO PLAYER SUBINDO
	public BufferedImage[] playerDown = new BufferedImage[5]; // SPRITES DO PLAYER DESCENDO
	public BufferedImage[] playerLeft    = new BufferedImage[8]; // SPRITES DO PLAYER ESQUERDA
	public BufferedImage[] playerRight  = new BufferedImage[8]; // SPRITES DO PLAYER DIREITA
	
	
	public Texturas() {
		CarregarImagem loader = new CarregarImagem();
		try {
			
			bloco_tex   =  loader.pegarImagem("/bloco_tex.png ");
			enemy_tex =  loader.pegarImagem("/enemy_tex.png ");
			player_tex  =  loader.pegarImagem("/player_tex.png ");			
			
			System.out.println("[DEBUG TEXTURA] TEXTURAS CARREGADAS COM SUCESSO!");
		} catch (Exception e) {
			System.err.println("[DEBUG TEXTURA] DEU RUIM AO CARREGAR AS TEXTURAS...!");
		}
		
		blockSheet   = new FolhaSprites(bloco_tex);
		enemySheet = new FolhaSprites(enemy_tex);
		playerSheet  = new FolhaSprites(player_tex);		
		
		getTextures();				
		
	}

	private void getTextures() {
		//TERRAS GRAMAS
		block[0] = blockSheet.pegarSpriteColRow(1, 1, 32,32);  //GRAMA 		
		block[1] = blockSheet.pegarSpriteColRow(6, 1, 32,32); //  TERRA ESQ		
		block[2] = blockSheet.pegarSpriteColRow(5, 1, 32,32);  // TERRA CENTRAL
		block[3] = blockSheet.pegarSpriteColRow(4, 1, 32,32); //  TERRA DIR
		//ENEMY
		enemy[0] = enemySheet.pegarSpriteColRow(1, 1, 32,64);  //Lado 1
		enemy[1] = enemySheet.pegarSpriteColRow(2, 1, 32,64); //  Lado 2
		
		//PLAYER
		
		for (int i = 0; i < playerUp.length; i++) {
			playerUp[i] = playerSheet.pegarSpriteColRow(i+1, 1, 32, 64);
		}

		// PLAYER DOWN
		for (int i = 0; i <playerDown.length; i++) {
			playerDown[i] = playerSheet.pegarSpriteColRow(i+1, 2, 32, 64);

		}
				

		// PLAYER LEFT
		for (int i = 0; i < playerLeft.length; i++) {
			playerLeft[i] = playerSheet.pegarSpriteColRow(i+1, 4, 32, 64);
		}		

		// PLAYER RIGHT
		for (int i = 0; i < playerRight.length; i++) {
			playerRight[i] = playerSheet.pegarSpriteColRow(i+1, 3, 32, 64);
		}	
		
		player_idle[0] = playerSheet.pegarSpriteColRow(7, 2, 32,64); //FRAME 01
		player_idle[1] = playerSheet.pegarSpriteColRow(8, 2, 32,64); //FRAME 02
		
	}
	
	//94
}
