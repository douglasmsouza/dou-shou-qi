package model;
import java.io.Serializable;

import javax.swing.ImageIcon;


public class Armadilha implements Peca,Serializable{
	
	private ImageIcon imagem;	
	private int jogador;	
	
	public Armadilha(int jogador) {
		setImagem("imagens/armadilha.png");
		this.jogador = jogador;		
	}
		
	public ImageIcon getImagem() {
		return imagem;
	}	
	
	@Override
	public int getForca() {		
		return 0;
	}
	
	@Override
	public int getJogador() {
		return jogador;
	}
	
	@Override
	public String getNome() {
		return "";
	}
	
	@Override
	public boolean isAnimal() {
		return false;
	}
	
	@Override
	public void estaNaArmadilha(boolean flag) {				
	}	
	@Override
	public void setImagem(String path) {
		imagem = new ImageIcon(path);
	}

	@Override
	public boolean estaNaArmadilha() {		
		return false;
	}
}