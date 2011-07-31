package model;

import java.io.Serializable;

public class Leao extends Peca implements Serializable {

	public Leao(int jogador) {
		super("imagens/leao" + jogador + ".png", jogador, 7, true, "leão");
	}
}
