package com.ks2002br.graficos;
/*
 * By Elisandro
 */

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CarregarImagem {
	
	private BufferedImage img;
	
	public BufferedImage pegarImagem(String caminho) {
		try {
			img = ImageIO.read(getClass().getResource(caminho));
			System.out.println("[DEBUG CarregarImagem] CARREGANDO IMAGEM - COMPLETO!");
			return img;
		} catch (IOException |  IllegalArgumentException e) {		
			System.err.println("[DEBUG CarregarImagem] Não foi localizado o arquivo pedido! Sistema sera encerrado! \n");
			e.printStackTrace();
			System.exit(1);
		}				
		return null;			
	}

}
