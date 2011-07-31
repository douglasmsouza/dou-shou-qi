package model;

import javax.swing.ImageIcon;

public abstract class Peca {

	private ImageIcon imagem;
	private int jogador;
	private boolean estaNaArmadilha;
	private int forca;
	private boolean animal;
	private String nome;

	public Peca(String imagem, int jogador, int forca, boolean animal, String nome) {
		setImagem(imagem);
		this.jogador = jogador;
		this.estaNaArmadilha = false;
		this.forca = forca;
		this.animal = animal;
		this.nome = nome;
	}

	public Peca(String imagem, int jogador) {
		this(imagem, jogador, 0, false, "");
	}

	public int getForca() {
		return this.forca;
	}

	public String getNome() {
		return this.nome;
	}

	public boolean isAnimal() {
		return this.animal;
	}

	public ImageIcon getImagem() {
		return this.imagem;
	}

	public void setImagem(String path) {
		setImagem(new ImageIcon(path));
	}

	public void setImagem(ImageIcon imagem) {
		this.imagem = imagem;
	}

	public int getJogador() {
		return this.jogador;
	}

	public void estaNaArmadilha(boolean flag) {
		this.estaNaArmadilha = flag;
	}

	public boolean estaNaArmadilha() {
		return this.estaNaArmadilha;
	}

}
