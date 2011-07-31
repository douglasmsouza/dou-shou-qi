package model;

import java.io.Serializable;

public class Tigre extends Peca implements Serializable {

	public Tigre(int jogador) {
		super("imagens/tigre" + jogador + ".png", jogador, 6, true, "tigre");
	}
}
