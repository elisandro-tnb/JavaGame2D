package com.ks2002br.graficos;

/*
 * By Elisandro 12/2021 revisao geral
 */
import java.awt.image.BufferedImage;

public class FolhaSprites {

	private BufferedImage img;

	public FolhaSprites(BufferedImage image) {
		this.img = image;
	}

	public BufferedImage pegarSpritePosXPosY(int x, int y, int w, int h) {
		return img.getSubimage(x, y, w, h);
	}

	public BufferedImage pegarSpriteColRow(int col, int row, int w, int h) {
		return img.getSubimage((col * w) - w, (row * h) - h, w, h);
	}
}
