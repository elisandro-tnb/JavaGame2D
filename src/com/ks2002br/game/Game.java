package com.ks2002br.game;
/**
 * by Elisandro
 */
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Game extends Canvas {

	private static final long serialVersionUID = 1L;
	
	public static JFrame frame;   // INSTANCIAMOS UMA VARIAVEL 
	public static final int LARGURA = 400, ALTURA = 300;  //VARIAVEL STATIC DEVE SER SEMPRE EM MAIUSCULA
	public static final int ESCALA = 2;	

	//CONTRUTOR DA CLASSE
	public Game() {
		this.setPreferredSize(new Dimension(LARGURA * ESCALA, ALTURA * ESCALA)); // DEFINIMOS O TAMANHO DA JANELA
		initFrame();  //CHAMADA AO METODO 
	}

	private void initFrame() {
		System.out.println("INICIALIZANDO O FRAME...");
			frame  = new JFrame("JAVA GAME2D - MEU JOGO"); // INICIALIZANDO O FRAME, E PASSANDO NOME COM PARAMETRO
			frame.add(this); // ADICIONAR A SI MESMO
			frame.setResizable(false); //PROIBIDO REDIMENCIONAR
			frame.pack(); // AJUSTA TUDO NO CANVAS
			frame.setLocationRelativeTo(null); // ABRIR JANELA NO MEIO DA TELA
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // AO FECHAR A JANELA ECERRA TODO A APP
			frame.setVisible(true);			
			System.out.println("LEGAL JANELA CRIADA...");
	}
	
}
