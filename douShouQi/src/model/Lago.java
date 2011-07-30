package model;

import java.io.Serializable;
import javax.swing.ImageIcon;


public class Lago implements Peca,Serializable{

	@Override
	public boolean estaNaArmadilha() {		
		return false;
	}
	private ImageIcon imagem;	
	
	public Lago(){
		setImagem("imagens/lago.png");
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
		return 0;
	}
	
	@Override
	public String getNome() {
		return "lago";
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

