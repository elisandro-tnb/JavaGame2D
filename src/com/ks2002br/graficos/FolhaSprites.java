package com.ks2002br.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FolhaSprites {

	private BufferedImage img;

	public FolhaSprites(String caminho) {
		try {
			img = ImageIO.read(getClass().getResource(caminho));
			System.out.println("Loading spritesheet...  Done!");
		} catch (IOException | IllegalArgumentException e) {
			System.err.println("[DEBUG FolhaSprites] Não foi localizado o arquivo pedido! Sistema sera encerrado! \n");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public BufferedImage pegarSprite(int x, int y, int w, int h) {
		return img.getSubimage(x, y, w, h);
	}

}
