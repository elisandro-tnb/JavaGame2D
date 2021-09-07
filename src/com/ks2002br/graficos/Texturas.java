package com.ks2002br.graficos;

import java.awt.image.BufferedImage;

public class Texturas {

	private FolhaSprites  blockSheet;
	
	private BufferedImage bloco_tex;
	
	public BufferedImage[] block = new BufferedImage[2]; //  posicao 0 grama ,1 terra
	
	public Texturas() {
		CarregarImagem loader = new CarregarImagem();
		try {
			bloco_tex = loader.pegarImagem("/bloco_tex.png ");
			System.out.println("[DEBUG TEXTURA] TEXTURAS CARREGADAS COM SUCESSO!");
		} catch (Exception e) {
			System.err.println("[DEBUG TEXTURA] DEU RUIM AO CARREGAR AS TEXTURAS...!");
		}
		
		blockSheet = new FolhaSprites(bloco_tex);
		
		
		getTextures();		
		
		
	}

	private void getTextures() {
		
		block[0] = blockSheet.pegarSpriteColRow(1, 1, 32,32);  //Grama 1
		block[1] = blockSheet.pegarSpriteColRow(4, 1, 32,32); // terra 1		
		
	}
	
	
}
