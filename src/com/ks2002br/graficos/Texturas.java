package com.ks2002br.graficos;

import java.awt.image.BufferedImage;

public class Texturas {

	private FolhaSprites  blockSheet, enemySheet, playerSheet;  //Instancia/referencia
	private FolhaSprites batSheet, itemSheet;
	
	private BufferedImage bloco_tex,  enemy_tex, player_tex;
	private BufferedImage bat_tex, item_tex;
		
	public BufferedImage[] block   = new BufferedImage[4]; //SPRITES DOS BLOCOS
	public BufferedImage[] enemy = new BufferedImage[2]; //SPRITES DOS BLOCOS
	public BufferedImage[] player_idle = new BufferedImage[2]; //SPRITES DO PLAYER PARADO
	
	public BufferedImage[] playerUp      = new BufferedImage[5]; // SPRITES DO PLAYER SUBINDO
	public BufferedImage[] playerDown = new BufferedImage[5]; // SPRITES DO PLAYER DESCENDO
	public BufferedImage[] playerLeft    = new BufferedImage[8]; // SPRITES DO PLAYER ESQUERDA
	public BufferedImage[] playerRight  = new BufferedImage[8]; // SPRITES DO PLAYER DIREITA
	
	public BufferedImage[] bat_idle  = new BufferedImage[4];
	public BufferedImage[] item  = new BufferedImage[10];
	
	public Texturas() {
		CarregarImagem loader = new CarregarImagem();
		try {
			
			bloco_tex   =  loader.pegarImagem("/bloco_tex.png ");
			enemy_tex =  loader.pegarImagem("/enemy_tex.png ");
			player_tex  =  loader.pegarImagem("/player_tex.png ");	
			bat_tex       =  loader.pegarImagem("/bat_tex.png ");	
			item_tex       =  loader.pegarImagem("/itens_tex.png ");	
			
			System.out.println("[DEBUG TEXTURA] TEXTURAS CARREGADAS COM SUCESSO!");
		} catch (Exception e) {
			System.err.println("[DEBUG TEXTURA] DEU RUIM AO CARREGAR AS TEXTURAS...!");
		}
		
		blockSheet   = new FolhaSprites(bloco_tex);
		enemySheet = new FolhaSprites(enemy_tex);
		playerSheet  = new FolhaSprites(player_tex);		
		batSheet       = new FolhaSprites(bat_tex);
		itemSheet       = new FolhaSprites(item_tex);
		
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
		
		
		for (int i = 0; i < bat_idle.length; i++) {
			bat_idle[i] = batSheet.pegarSpriteColRow(i+1, 1, 32, 32);
		}
		
		// ITENS COLETAVEIS
				item[0] = itemSheet.pegarSpritePosXPosY(   0,   0, 32, 32);  // MEDKIT GRANDE
				item[1] = itemSheet.pegarSpritePosXPosY( 32,   0, 32, 32);  // MEDKIT PEQUENO
				item[2] = itemSheet.pegarSpritePosXPosY( 64,   0, 32, 32);  // POCAO VERMELHA
				item[3] = itemSheet.pegarSpritePosXPosY( 96,   0, 32, 32);  // POCAO AZUL
				item[4] = itemSheet.pegarSpritePosXPosY(   0, 32, 32, 32);  // ARMA
				item[5] = itemSheet.pegarSpritePosXPosY( 32, 32, 32, 32);  // CAIXA MUNICAO
				item[6] = itemSheet.pegarSpritePosXPosY( 64, 32, 32, 32);  // MUNICAO
				item[7] = itemSheet.pegarSpritePosXPosY( 96, 32, 32, 32);  // CHAVE
				item[8] = itemSheet.pegarSpritePosXPosY(   0, 64, 32, 32);  // KEYCARD RED
				item[9] = itemSheet.pegarSpritePosXPosY( 32, 64, 32, 32);  // KEYCARD YELLOW
		
	}
	
	//94
}
