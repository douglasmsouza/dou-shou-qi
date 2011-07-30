package model;

import java.io.Serializable;
import javax.swing.ImageIcon;


public class Rato implements Peca,Serializable{
	
	private ImageIcon imagem;	
	private int jogador;
	private boolean estaNaArmadilha;
	
	public Rato(int jogador) {		
		this.jogador = jogador;
		setImagem("imagens/rato"+jogador+".png");
		estaNaArmadilha = false;
	}

	public int getForca() {	
		return 1;
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
		return "rato";
	}
	
	@Override
	public boolean isAnimal() {
		return true;
	}
	
	public void setImagem(ImageIcon imagem) {
		this.imagem = imagem;
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
