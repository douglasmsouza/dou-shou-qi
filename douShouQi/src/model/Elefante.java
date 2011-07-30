package model;

import java.io.Serializable;
import javax.swing.ImageIcon;

public class Elefante implements Peca, Serializable{

	@Override
	public boolean estaNaArmadilha() {
		return estaNaArmadilha;		
	}

	private int jogador;
	private ImageIcon imagem;	
	private boolean estaNaArmadilha;
	
	public Elefante(int jogador) {		
		this.jogador = jogador;		
		setImagem("imagens/elefante"+jogador+".png");
		estaNaArmadilha = false;
	}

	public int getForca() {
		return 8;
	}

	@Override
	public ImageIcon getImagem() {
		return imagem;
	}	
	
	@Override
	public int getJogador() {
		return jogador;
	}
	
	@Override
	public String getNome() {
		return "elefante";
	}
	
	@Override
	public boolean isAnimal() {
		return true;
	}

	@Override
	public void estaNaArmadilha(boolean flag) {
		estaNaArmadilha = flag;
	}
	
	@Override
	public void setImagem(String path) {
		imagem = new ImageIcon(path);		
	}
}
