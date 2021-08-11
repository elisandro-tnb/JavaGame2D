package com.ks2002br.game;

/**
 * by Elisandro
 */
import java.awt.*;
import java.awt.image.*;
import javax.swing.JFrame;

import com.ks2002br.entities.Player;
import com.ks2002br.frameworks.GameController;
import com.ks2002br.frameworks.ObjectId;
import com.ks2002br.input.Teclado;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static JFrame frame;
	public static final int LARGURA = 320, ALTURA = 320;
	public static final int ESCALA = 2;

	private Thread thread;
	private boolean isRunning = true;
	private int frames = 0;

	private final BufferedImage image;
	private GameController gc;

	// CONTRUTOR DA CLASSE
	public Game() {
		this.setPreferredSize(new Dimension(LARGURA * ESCALA, ALTURA * ESCALA));
		initFrame();
		image = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_RGB);
		startGame();
	}

	private void startGame() {
		gc = new GameController();
		addKeyListener(new Teclado(gc));
		// OBJETOS AQUI
		gc.criarMundo();
		gc.addObj(new Player(120, 450, ObjectId.PLAYER, gc));
	}

	private void initFrame() {
		frame = new JFrame("JAVA GAME2D - MEU JOGO");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void run() {
		requestFocus();

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		double timer = System.currentTimeMillis();

		// LOOP GAME
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				frame.setTitle("MEU JOGO JAVA GAME2D - rodando a " + frames + " FPS");
				frames = 0;
				timer += 1000;
			}

		}

		stop();
	}

	private void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	private void tick() {
		gc.update();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = image.getGraphics();
		// RENDER DO GAME - PINTANDO A TELA DE FUNCO
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, LARGURA, ALTURA);
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, LARGURA * ESCALA, ALTURA * ESCALA, null);
		// A PARTIR DAQUI TUDO SERA REDERIZADO EM CIMA DA COR DA TELA DE FUNDO

		Graphics2D g2d = (Graphics2D) g;

		gc.draw(g2d);

		// FINAL DO OBJETOS A SEREM DESENHADOS
		bs.show(); // MOSTRAR TUDO QUE O PINTOR DESENHOU
		g2d.dispose();
	}

}
