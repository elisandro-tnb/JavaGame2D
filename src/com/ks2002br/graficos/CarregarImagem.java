package com.ks2002br.graficos;
/*
 * By Elisandro
 */
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CarregarImagem {
	
	private Image img;
	
	public Image pegarImagem(String caminho) {
		try {
			img = ImageIO.read(getClass().getResource(caminho));
			System.out.println("[DEBUG CarregarImagem] CARREGANDO IMAGEM - COMPLETO!");
			return img;
		} catch (IOException |  IllegalArgumentException e) {		
			System.err.println("[DEBUG CarregarImagem] N�o foi localizado o arquivo pedido! Sistema sera encerrado! \n");
			e.printStackTrace();
			System.exit(1);
		}				
		return null;			
	}

}
