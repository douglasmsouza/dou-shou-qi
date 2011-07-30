package model;

import java.io.Serializable;
import javax.swing.ImageIcon;


public class Cao implements Peca,Serializable{
	
	private ImageIcon imagem;
	private int jogador; 
	private boolean estaNaArmadilha;
	
	public Cao(int jogador) {		
		this.jogador = jogador;
		setImagem("imagens/cao"+jogador+".png");		
		estaNaArmadilha = false;
	}
	
	public int getForca() {
		return 3;
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
		return "cachorro";
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

	@Override
	public boolean estaNaArmadilha() {
		return estaNaArmadilha;
	}
}
