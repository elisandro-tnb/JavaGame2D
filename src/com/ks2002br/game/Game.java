package com.ks2002br.game;
/**
 * by Elisandro
 */
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.ks2002br.frameworks.Cobaia;
import com.ks2002br.frameworks.GameController;
import com.ks2002br.frameworks.ObjectId;
import com.ks2002br.graficos.CarregarImagem;
import com.ks2002br.graficos.FolhaSprites;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static JFrame frame;   // INSTANCIAMOS UMA VARIAVEL 
	public static final int LARGURA = 320, ALTURA = 320;  //VARIAVEL STATIC DEVE SER SEMPRE EM MAIUSCULA
	public static final int ESCALA = 2;	
	
	private Thread thread;
	private boolean isRunning = true;
	private int frames = 0;
	
	private final BufferedImage image ;
	
	private Image img;
    private FolhaSprites spriteSheet; //Passo 01  - instanciando ou referenciando
    
    private GameController gc;
	

	//CONTRUTOR DA CLASSE
	public Game() {
		this.setPreferredSize(new Dimension(LARGURA * ESCALA, ALTURA * ESCALA)); // DEFINIMOS O TAMANHO DA JANELA
		initFrame(); 
		image = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_RGB);
		startGame();
	}

	private void startGame() {
		img = new CarregarImagem().pegarImagem("/coordenadas.png");
		spriteSheet = new FolhaSprites("/spritesheet-01.png");  //passo 02 / inicializando
		gc = new GameController();
		
		//OBJETOS AQUI
		gc.addObj(new Cobaia(20, 50,ObjectId.COBAIA));
		gc.addObj(new Cobaia(120, 450,ObjectId.COBAIA));
		
	}

	private void initFrame() {
			frame  = new JFrame("JAVA GAME2D - MEU JOGO"); // INICIALIZANDO O FRAME, E PASSANDO NOME COM PARAMETRO
			frame.add(this); // ADICIONAR A SI MESMO
			frame.setResizable(false); //PROIBIDO REDIMENCIONAR
			frame.pack(); // AJUSTA TUDO NO CANVAS
			frame.setLocationRelativeTo(null); // ABRIR JANELA NO MEIO DA TELA
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // AO FECHAR A JANELA ECERRA TODO A APP
			frame.setVisible(true);		
	}

	@Override
	public void run() {
		requestFocus();  // chamar a aten��o do windows
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		double timer = System.currentTimeMillis();
		
		//LOOP GAME 
		while(isRunning) {
			long now = System.nanoTime();
			delta +=(now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				frames++;  
				delta--;
				}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				frame.setTitle("MEU JOGO JAVA GAME2D - rodando a "+frames+" FPS");
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
		BufferStrategy  bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
	    Graphics g = image.getGraphics();
	    // RENDER DO GAME - PINTANDO A TELA DE FUNCO
	    g.setColor(new Color(0,0,0));
	    g.fillRect(0, 0, LARGURA, ALTURA);
	    g = bs.getDrawGraphics();
	    g.drawImage(image,0,0,LARGURA * ESCALA, ALTURA * ESCALA, null);
	    //A PARTIR DAQUI TUDO SERA REDERIZADO EM CIMA DA COR DA TELA DE FUNDO
	    
	    //REDERIZANDO UM TEXTO
//	    g.setColor(new Color(255,0,0));
//	    g.setFont(new Font("arial", Font.BOLD, 30));
//	    g.drawString("Meu primeiro texto render  ",25, 450);
	    
	    Graphics2D g2d = (Graphics2D)  g;
	    
	  //  g2d.drawImage(img,5,5,625,625,null);    
	    
	//    g2d.drawImage(spriteSheet.pegarSprite(0, 0, 16, 16),180,50,128,128,null);    //passo 03 - usando
	    
	    gc.draw(g2d);
	    
	    // FINAL DO OBJETOS A SEREM DESENHADOS
	    bs.show(); //MOSTRAR TUDO QUE O PINTOR DESENHOU
	    g2d.dispose();
		
	}


	
	
}
