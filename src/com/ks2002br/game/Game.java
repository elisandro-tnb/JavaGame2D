package com.ks2002br.game;

/*
 * By Elisandro 12/2021 revisao geral
 */
import java.awt.*;
import java.awt.image.*;
import javax.swing.JFrame;

import com.ks2002br.frameworks.*;
import com.ks2002br.graficos.*;
import com.ks2002br.input.Mouse;
import com.ks2002br.input.Teclado;
import com.ks2002br.sound.LoadSound;
import com.ks2002br.world.*;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static JFrame frame;
	public static final int LARGURA = 320, ALTURA = 200;
	public static final int ESCALA = 2;

	private Thread thread;
	private boolean isRunning = true;
	private int frames = 0;
	
	//GAME OVER - RESTART
	private boolean showMessageGameOver;
	private int framesGameOver;
	public static boolean restartGame;

	private final BufferedImage image;
	public static GameController gc;
	private static BufferedImage level = null;
	private static World world;
	private static Camera cam;
	private static Texturas tex;

	// private TestAnim testAnim;
	private InforDebug dbg;
	private UI ui;
	
	private Menu mn;
	private Mouse mouse;

	private static CarregarImagem mapa;

	private static int CUR_LEVEL = 3, MAX_LEVEL = 4;

	public static String gameState = "MENU";

	// CONTRUTOR DA CLASSE
	public Game() {
		this.setPreferredSize(new Dimension(LARGURA * ESCALA, ALTURA * ESCALA));
		initFrame();
		image = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_RGB);
		startGame();
	}

	private void startGame() {
		gc = new GameController();
		mouse = new Mouse();
		addKeyListener(new Teclado(gc));
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		// OBJETOS AQUI
		mapa = new CarregarImagem();
		tex = new Texturas();

		// testAnim = new TestAnim(40, 60, null);
		dbg = new InforDebug(gc);
		

		loadLevel();

		/*
		 * level = mapa.pegarImagem("/mapa-01.png"); world = new World(level, gc);
		 * world.carregarLevel(); cam = new Camera(0, 0); LoadSound.bgm.playLoop();
		 */
		ui = new UI(gc);
		mn = new Menu();

	}

	private static void loadLevel() {

		CUR_LEVEL++;

		if (CUR_LEVEL > MAX_LEVEL)
			CUR_LEVEL = 1;

		String newWorld = "/mapa-0" + CUR_LEVEL + ".png";

		level = mapa.pegarImagem(newWorld);
		world = new World(level, gc);
		world.carregarLevel();
		cam = new Camera(0, 0);
		LoadSound.bgm.playLoop();

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

		if (gameState == "MENU") {			
			mn.tick();
			
		} else if (gameState == "NORMAL") {
			
			gc.update();

			for (int i = 0; i < gc.obj.size(); i++) {
				if (gc.obj.get(i).getId() == ObjectId.PLAYER) {
					cam.tick(gc.obj.get(i));
				}

				if (gc.obj.get(i).getId() == ObjectId.BULLET) {
					long now = System.nanoTime();

					GameObject objTemp = gc.obj.get(i);

					long last = objTemp.isTimer();
					long live = (now - last) / 1000000;

					if (live >= 4000) {
						gc.removeObj(gc.obj.get(i));
					}
				}
			}
			// testAnim.tick(null);
			dbg.update();
			ui.tick();
		}
		
		
		//GAMEOVER
		else if (gameState == "GAME_OVER") {
			framesGameOver++;
			if(framesGameOver >= 35) {
				framesGameOver=0;
				if(showMessageGameOver) {
					showMessageGameOver = false;
				}else {
					showMessageGameOver=true;
				}
			}
			
			if(restartGame) {
				restartGame = false;
				Game.gameState = "NORMAL";
				CUR_LEVEL = 0;
				clearLevel();
			}
			
			
		}
		
		

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = image.getGraphics();
		// RENDER DO GAME - PINTANDO A TELA DE FUNCO
		g.setColor(new Color(80, 158, 216));
		g.fillRect(0, 0, LARGURA, ALTURA);
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, LARGURA * ESCALA, ALTURA * ESCALA, null);
		// A PARTIR DAQUI TUDO SERA REDERIZADO EM CIMA DA COR DA TELA DE FUNDO
		Graphics2D g2d = (Graphics2D) g;

		g2d.translate(cam.getCamX(), cam.getCamY()); // CAMERA INICIO

		gc.draw(g2d);

		g2d.translate(-cam.getCamX(), -cam.getCamY()); // CAMERA FIM

		// testAnim.render(g2d);
		dbg.render(g2d);
		ui.render(g2d);
		
		
		if(gameState == "MENU") mn.render(g2d);	
		
		else if (gameState == "GAME_OVER") {
			g2d.setColor(new Color(0,0,0,100));
			g2d.fillRect(0, 0, LARGURA * ESCALA, ALTURA*ESCALA);
			g.setFont(new Font("arial",Font.BOLD, 36));
			g.setColor(Color.RED);
			g.drawString("GAME OVER ",(LARGURA * ESCALA) /2 -100 ,(ALTURA * ESCALA) /2 -10 );
			
			if(showMessageGameOver) {
				g.setFont(new Font("arial",Font.BOLD, 20));
				g.setColor(Color.WHITE);
				g.drawString(">> APERTE ENTER PARA RE-INICIAR ",(LARGURA * ESCALA) /2 -170 ,(ALTURA * ESCALA) /2 +20 );
			}
		}
		
		
		//System.out.println("Mouse : "+mouse.getMouseX() +" / "+mouse.getMouseY()+"   Button :  "+mouse.getMouseButton());
		
		//g2d.drawString("Mouse : "+Mouse.getMouseX() +" / "+Mouse.getMouseY(), 70,370);

		// FINAL DO OBJETOS A SEREM DESENHADOS
		bs.show(); // MOSTRAR TUDO QUE O PINTOR DESENHOU
		g2d.dispose();
	}

	public static Texturas getInstance() {
		return tex;
	}

	public static void clearLevel() {

		LoadSound.bgm.stop();
		gc.obj.clear();
		gc.setKey_card(false);
		gc.setLaser_off(false);
		loadLevel();

	}
}
