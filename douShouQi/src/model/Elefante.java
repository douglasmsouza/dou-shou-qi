package model;

import java.io.Serializable;

public class Elefante extends Peca implements Serializable {

	public Elefante(int jogador) {
		super("imagens/elefante" + jogador + ".png", jogador, 8, true, "elefante");
	}

}
