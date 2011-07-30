package model;

import java.io.Serializable;
import javax.swing.ImageIcon;


public class Leopardo implements Peca,Serializable{

	@Override
	public boolean estaNaArmadilha() {
		return estaNaArmadilha;
	}

	private ImageIcon imagem;
	private int jogador;	
	private boolean estaNaArmadilha;
	
	public Leopardo(int jogador) {			
		this.jogador = jogador;
		setImagem("imagens/leopardo"+jogador+".png");
		estaNaArmadilha = false;
	}	

	public int getForca() {		
		return 5;
	}

	public ImageIcon getImagem() {
		return imagem;
	}	
	
	@Override
	public int getJogador() {
		return jogador;
	}
	
	@Override
	public String getNome() {
		return "leopardo";
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
