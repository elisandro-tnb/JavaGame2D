package com.ks2002br.graficos;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CarregarImagem {
	
	private Image img;
	
	public Image pegarImagem(String caminho) {
		try {
			img = ImageIO.read(getClass().getResource(caminho));
			System.out.println("Loading image...  Done!");
			return img;
		} catch (IOException |  IllegalArgumentException e) {		
			System.err.println("[DEBUG CarregarImagem] Não foi localizado o arquivo pedido! Sistema sera encerrado! \n");
			e.printStackTrace();
			System.exit(1);
		}
				
		return null;		
		
	}

}
