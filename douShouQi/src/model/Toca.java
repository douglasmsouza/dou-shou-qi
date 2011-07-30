package model;

import java.io.Serializable;
import javax.swing.ImageIcon;


public class Toca implements Peca,Serializable{
	
	@Override
	public boolean estaNaArmadilha() {
		return false;
	}

	private ImageIcon imagem;
	private int jogador;
	
	public Toca(int jogador) {
		this.jogador = jogador;
		setImagem("imagens/toca.png");
	}
	
	public ImageIcon getImagem() {
		return imagem;
	}
	
	@Override
	public int getForca() {
		// TODO Auto-generated method stub
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
	
}
