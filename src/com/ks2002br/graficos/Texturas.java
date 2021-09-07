package com.ks2002br.graficos;

import java.awt.image.BufferedImage;

public class Texturas {

	private FolhaSprites  blockSheet, enemySheet;  //Instancia/referencia
	
	private BufferedImage bloco_tex;
	private BufferedImage enemy_tex;
	
	public BufferedImage[] block = new BufferedImage[4]; //SPRITES DOS BLOCOS
	public BufferedImage[] enemy = new BufferedImage[2]; //SPRITES DOS BLOCOS
	
	public Texturas() {
		CarregarImagem loader = new CarregarImagem();
		try {
			bloco_tex = loader.pegarImagem("/bloco_tex.png ");
			enemy_tex =  loader.pegarImagem("/enemy_tex.png ");
			System.out.println("[DEBUG TEXTURA] TEXTURAS CARREGADAS COM SUCESSO!");
		} catch (Exception e) {
			System.err.println("[DEBUG TEXTURA] DEU RUIM AO CARREGAR AS TEXTURAS...!");
		}
		
		blockSheet = new FolhaSprites(bloco_tex);
		enemySheet = new FolhaSprites(enemy_tex);
		
		
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
		
	}
	
	
}
