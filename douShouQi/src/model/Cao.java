package model;

import java.io.Serializable;

public class Cao extends Peca implements Serializable {

	public Cao(int jogador) {
		super("imagens/cao" + jogador + ".png", jogador, 3, true, "cachorro");
	}

}
