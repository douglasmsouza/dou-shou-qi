package model;

import java.io.Serializable;

public class Gato extends Peca implements Serializable {

	public Gato(int jogador) {
		super("imagens/gato" + jogador + ".png", jogador, 2, true, "gato");
	}
}
