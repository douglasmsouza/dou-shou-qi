package model;

import java.io.Serializable;

public class Leopardo extends Peca implements Serializable {

	public Leopardo(int jogador) {
		super("imagens/leopardo" + jogador + ".png", jogador, 5, true, "leopardo");
	}
}
