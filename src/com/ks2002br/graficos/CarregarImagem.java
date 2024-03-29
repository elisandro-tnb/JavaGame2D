package com.ks2002br.graficos;

/*
 * By Elisandro 12/2021 revisao geral
 */
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CarregarImagem {

	private BufferedImage img;

	public BufferedImage pegarImagem(String path) {
		try {
			img = ImageIO.read(getClass().getResource(path));
			System.out.println("[DEBUG CarregarImagem] CARREGANDO IMAGEM: " + path);
			return img;
		} catch (IOException | IllegalArgumentException e) {
			System.err
					.println("[DEBUG CarregarImagem] N�o foi localizado o arquivo pedido! Sistema sera encerrado! \n");
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
