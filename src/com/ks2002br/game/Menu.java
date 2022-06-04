package com.ks2002br.game;
/*
 * By Elisandro 04/2022
 * 
 */

import java.awt.*;
import java.io.*;

import com.ks2002br.graficos.Texturas;
import com.ks2002br.input.Mouse;

public class Menu {
	
	public static boolean up, down, ok, pause=false;
	private String option[] = {"Jogar","Carregar","Sair"};
	private int maxOpt = option.length -1;
	private int curOpt = 0;
	
	private Texturas tex = Game.getInstance();
	private Font  minhaFonteArcade_N;
	
	private int mx,my,mb,wt;
		
	public Menu() {	
		try {
			minhaFonteArcade_N = Font.createFont(Font.TRUETYPE_FONT, new File("res/ARCADE_N.TTF"));
		} catch (FontFormatException | IOException e) {			
			System.out.println("[Debug MENU ] Deu ruim ao carregar a fonte");
		}
		
		wt = Game.LARGURA / 2 ;
		
	}
	
	public void tick() {
		if(up) {
			up=false;
			curOpt--;
			if(curOpt < 0)	curOpt = maxOpt;			
		}
			
		if(down) {
			down=false;
			curOpt++;
			if(curOpt > maxOpt) 	curOpt = 0;		
		}
		
		if(ok) {
			ok=false;
			if(option[curOpt] == "Jogar" || option[curOpt]=="Continuar" ) {
				Game.gameState="NORMAL";
				pause = false;
			}else if (option[curOpt] == "Sair")		System.exit(0);				
		}		
		
		//MOUSE
		mx = Mouse.getMouseX();
		my = Mouse.getMouseY();
		mb = Mouse.getMouseButton();		
		
		
		//LOGICA DO MOUSE /MENU
		
		if(mx >= wt + 70 && mx <= wt+235 &&
				        my >= 145 && my <= 145 + 35) {
			curOpt=0;
			  if(mb==1)  Game.gameState = "NORMAL";
			
			//System.out.println("MOUSE ESTA NA AREA DA OPCAO JOGAR");
		}
		
		else if(mx >= wt + 70 && mx <= wt+235 &&
		        my >= 185 && my <= 185 + 35) {
			curOpt=1;
			//System.out.println("MOUSE ESTA NA AREA DA OPCAO CARREGAR");
		}
		
		else if(mx >= wt + 70 && mx <= wt+235 &&
		        my >= 225 && my <= 225 + 35) {
			    curOpt=2	;
			    if(mb==1) System.exit(0);
			//System.out.println("MOUSE ESTA NA AREA DA OPCAO SAIR");
		}
		
		
		
		
		
		
	}
	
	public void render(Graphics2D  g2d) {
		//FUNDO DO MENU
		g2d.setColor(new Color(0,0,0,220));
		g2d.fillRoundRect(50,80, (Game.LARGURA * Game.ESCALA)-80 ,Game.ALTURA+22, 32,32); // FILL COM CANTOS "ARREDONDADOS"
	   	//BORDA DO MENU	
		g2d.setColor(new Color(255,255,255));
		g2d.setStroke(new BasicStroke(5));  // ESTILO DA BORDA
		g2d.drawRoundRect(50,80,(Game.LARGURA * Game.ESCALA)-85 ,Game.ALTURA + 15, 32,32); // DESENHA A "BORDA"
		// TITULO DO MEMU 
		g2d.setColor(new Color(255,0,120));
		g2d.setFont(minhaFonteArcade_N.deriveFont(18f));
		g2d.drawString(" MEU JOGO DE PLATAFORMA  ", (Game.LARGURA / 2) -60, (Game.ALTURA / 2)+20);
		//OPCOES DO MENU
		g2d.setColor(new Color(255,255,255));
		g2d.setFont(minhaFonteArcade_N.deriveFont(14f));

		if(!pause)	g2d.drawString("Jogar          " , (Game.LARGURA / 2) + 80, 170);  // SE NAO ESTIVER EM PAUSA 
		else		       g2d.drawString("Continuar     " , (Game.LARGURA / 2) + 80, 170); // SE NAO
				
		g2d.drawString("Carregar    " , (Game.LARGURA / 2) + 80,  210);
		g2d.drawString("Sair            " , (Game.LARGURA / 2) + 80,  250);
		
		//ICONE DO MENU
		if(curOpt == 0) g2d.drawImage(tex.item[4] , (Game.LARGURA / 2) + 40, 155,  null); 
		if(curOpt == 1) g2d.drawImage(tex.item[4] , (Game.LARGURA / 2) + 40, 195,  null);
		if(curOpt == 2) g2d.drawImage(tex.item[4] , (Game.LARGURA / 2) + 40,  235, null);		
		
		
		//BORDAS BOTAO MOUSE
	//	g2d.setColor(new Color(0,255,255));
	//	g2d.drawRect(wt + 70,145,155,35);
	//	g2d.drawRect(wt + 70,185,155,35);
	//	g2d.drawRect(wt + 70,225,155,35);			
		
	}
	
}
