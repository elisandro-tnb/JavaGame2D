package com.ks2002br.frameworks;

/*
 * By Elisandro 12/2021 revisao geral
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

	private int speed, frames;
	private int index = 0, count = 0;

	private BufferedImage img[], curImg;

	public Animation(int spd, BufferedImage... args) {
		this.speed = spd;
		this.img = new BufferedImage[args.length];

		for (int i = 0; i < args.length; i++) {
			img[i] = args[i];
		}
		frames = args.length;
	}

	public void runAnimation() {
		index++;
		if (index > speed) {
			index = 0;
			nextFrame();
		}
	}

	private void nextFrame() {
		for (int i = 0; i < frames; i++) {
			if (count == i)
				curImg = img[i];
		}
		count++;
		if (count > frames)
			count = 0;
	}

	public void renderAnimation(Graphics g, int x, int y) {
		g.drawImage(curImg, x, y, null);
	}

	public void renderAnimation(Graphics g, int x, int y, int scaleX, int scaleY) {
		g.drawImage(curImg, x, y, scaleX, scaleY, null);
	}

}
