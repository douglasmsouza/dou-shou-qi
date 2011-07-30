package model;

import java.io.Serializable;
import javax.swing.ImageIcon;


public class Grama implements Peca,Serializable{

	@Override
	public boolean estaNaArmadilha() {		
		return false;
	}
	private ImageIcon imagem;	
	
	public Grama() {
		setImagem("imagens/grama.png");		
	}
	
	@Override
	public ImageIcon getImagem() {			
			return imagem;	
	}	
	
	@Override
	public int getForca() {	
		return 0;
	}	
	@Override
	public int getJogador() {
		return 0;
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
