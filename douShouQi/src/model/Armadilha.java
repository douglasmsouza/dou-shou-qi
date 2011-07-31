package model;

import java.io.Serializable;

public class Armadilha extends Peca implements Serializable {

	public Armadilha(int jogador) {
		super("imagens/armadilha.png", jogador);
	}

}