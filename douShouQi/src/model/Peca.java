package model;
import javax.swing.ImageIcon;

public interface Peca{
	ImageIcon getImagem();
	void setImagem(String path);
	int getForca();
	int getJogador();
	String getNome();	
	boolean isAnimal();
	void estaNaArmadilha(boolean flag);
	boolean estaNaArmadilha();
}
