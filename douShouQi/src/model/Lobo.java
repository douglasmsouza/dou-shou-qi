package model;

import java.io.Serializable;

public class Lobo extends Peca implements Serializable {

	public Lobo(int jogador) {
		super("imagens/lobo" + jogador + ".png", jogador, 4, true, "lobo");
	}

}
