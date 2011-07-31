package model;

import java.io.Serializable;

public class Rato extends Peca implements Serializable {

	public Rato(int jogador) {
		super("imagens/rato" + jogador + ".png", jogador, 1, true, "rato");
	}

}
