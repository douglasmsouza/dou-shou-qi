package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import model.Armadilha;
import model.Cao;
import model.Elefante;
import model.Gato;
import model.Grama;
import model.Lago;
import model.Leao;
import model.Leopardo;
import model.Lobo;
import model.Peca;
import model.Rato;
import model.Tigre;
import model.Toca;

public class Controle implements Serializable {

	// mensagens de aviso
	private static String jogadaNaoPermitida = "Essa jogada não é permitida! Selecione novamente...";
	private static String selecionouGrama = "Selecione um animal!";
	private static String selecionouAnimalDoAdversario = "Não é permitido selecionar um animal do adversário!";
	private static String ratoCapturouElefanteLago = "O rato não pode capturar o elefante ao sair do lago!";
	private static String pulouMaisDeUmaCasa = "Não é permitido pular mais de uma casa ou andar na diagonal!";
	private static String somenteRatoPodeEntrarNoLago = "Somente o rato pode entrar no lago!";

	private Peca tabuleiro[][];
	private Peca pecaAtual;
	private int linhaAnt, colAnt;
	private int jogador;
	private String infoJogo;
	private boolean temPecaSelecionada, jogoAcabou, comeu;
	private int qtPecasPlayer1, qtPecasPlayer2;
	// peças do jogo
	private Tigre tigre1 = new Tigre(1);
	private Tigre tigre2 = new Tigre(2);
	private Elefante elefante1 = new Elefante(1);
	private Elefante elefante2 = new Elefante(2);
	private Gato gato1 = new Gato(1);
	private Gato gato2 = new Gato(2);
	private Lobo lobo1 = new Lobo(1);
	private Lobo lobo2 = new Lobo(2);
	private Leopardo leopardo1 = new Leopardo(1);
	private Leopardo leopardo2 = new Leopardo(2);
	private Cao cao1 = new Cao(1);
	private Cao cao2 = new Cao(2);
	private Leao leao1 = new Leao(1);
	private Leao leao2 = new Leao(2);
	private Rato rato1 = new Rato(1);
	private Rato rato2 = new Rato(2);
	private Armadilha armadilha1 = new Armadilha(1);
	private Armadilha armadilha2 = new Armadilha(2);
	private Grama grama = new Grama();
	private Lago lago = new Lago();
	private Toca toca1 = new Toca(1);
	private Toca toca2 = new Toca(2);

	// lista de listeners!!!
	private List<DouShouQiListener> listeners = new ArrayList<DouShouQiListener>();
	// variavel que armazena a ultima jogada, para poder desfazer!!!
	private Command ultimaJogada;

	public Controle(ArrayList<DouShouQiListener> listeners) {
		constroiTabuleiro();
		this.listeners = listeners;
	}

	// monta o tabuleiro
	public void constroiTabuleiro() {
		jogador = 1;
		pecaAtual = null;
		temPecaSelecionada = false;
		jogoAcabou = false;
		qtPecasPlayer1 = qtPecasPlayer2 = 8;

		setUltimaJogada(null);
		setInfoJogo("Jogador 1 começa...");

		tabuleiro = new Peca[7][9];

		tabuleiro[0][0] = tigre1;
		tabuleiro[0][1] = grama;
		tabuleiro[0][2] = elefante1;
		tabuleiro[0][3] = grama;
		tabuleiro[0][4] = grama;
		tabuleiro[0][5] = grama;
		tabuleiro[0][6] = rato2;
		tabuleiro[0][7] = grama;
		tabuleiro[0][8] = leao2;

		tabuleiro[1][0] = grama;
		tabuleiro[1][1] = gato1;
		tabuleiro[1][2] = grama;
		tabuleiro[1][3] = lago;
		tabuleiro[1][4] = lago;
		tabuleiro[1][5] = lago;
		tabuleiro[1][6] = grama;
		tabuleiro[1][7] = cao2;
		tabuleiro[1][8] = grama;

		tabuleiro[2][0] = armadilha1;
		tabuleiro[2][1] = grama;
		tabuleiro[2][2] = lobo1;
		tabuleiro[2][3] = lago;
		tabuleiro[2][4] = lago;
		tabuleiro[2][5] = lago;
		tabuleiro[2][6] = leopardo2;
		tabuleiro[2][7] = grama;
		tabuleiro[2][8] = armadilha2;

		tabuleiro[3][0] = toca1;
		tabuleiro[3][1] = armadilha1;
		tabuleiro[3][2] = grama;
		tabuleiro[3][3] = grama;
		tabuleiro[3][4] = grama;
		tabuleiro[3][5] = grama;
		tabuleiro[3][6] = grama;
		tabuleiro[3][7] = armadilha2;
		tabuleiro[3][8] = toca2;

		tabuleiro[4][0] = armadilha1;
		tabuleiro[4][1] = grama;
		tabuleiro[4][2] = leopardo1;
		tabuleiro[4][3] = lago;
		tabuleiro[4][4] = lago;
		tabuleiro[4][5] = lago;
		tabuleiro[4][6] = lobo2;
		tabuleiro[4][7] = grama;
		tabuleiro[4][8] = armadilha2;

		tabuleiro[5][0] = grama;
		tabuleiro[5][1] = cao1;
		tabuleiro[5][2] = grama;
		tabuleiro[5][3] = lago;
		tabuleiro[5][4] = lago;
		tabuleiro[5][5] = lago;
		tabuleiro[5][6] = grama;
		tabuleiro[5][7] = gato2;
		tabuleiro[5][8] = grama;

		tabuleiro[6][0] = leao1;
		tabuleiro[6][1] = grama;
		tabuleiro[6][2] = rato1;
		tabuleiro[6][3] = grama;
		tabuleiro[6][4] = grama;
		tabuleiro[6][5] = grama;
		tabuleiro[6][6] = elefante2;
		tabuleiro[6][7] = grama;
		tabuleiro[6][8] = tigre2;
	}

	public Peca getPeca(int linha, int coluna) {
		return tabuleiro[linha][coluna];
	}

	// verifica se os movimentos são válidos
	// se for retorna true, senão false
	public boolean movimentaPeca(int linha, int coluna) {

		setPecaAtual(linha, coluna);

		if (temPecaSelecionada) {
			comeu = false;
			// aqui faz as seguintes comparações...
			Peca peca = getPeca(linha, coluna);// essa é a casa que foi
												// escolhida para o movimento
			// primeira condição: verifica se é armadilha ou animal de outro
			// jogador
			if (peca.getJogador() != jogador || peca.getClass() == Armadilha.class || (peca.getClass() == Toca.class && peca.getJogador() != jogador)) {
				// segundo condição: verifica se o números de casas a pular está
				// ok
				if (numerosDeCasasOk(linha, coluna)
						|| ((pecaAtual.getClass() == Leao.class || pecaAtual.getClass() == Tigre.class) && podePularLago(linha, coluna))) {
					// terceira condição: verifica se o animal tem força maior
					// ou se é grama,
					// se é rato pode entrar no lago
					// o rato não pode comer o elefante ao sair do lago
					if (peca.getClass() == Grama.class || (peca.isAnimal() && !isLago(linha, coluna) && peca.getForca() <= pecaAtual.getForca())
							|| (isLago(linha, coluna) && pecaAtual.getClass() == Rato.class)
							|| (peca.getClass() == Elefante.class && pecaAtual.getClass() == Rato.class) && !isLago(linhaAnt, colAnt)
							|| peca.getClass() == Armadilha.class || peca.estaNaArmadilha() || isTocaDoAdversario(peca)) {

						new Jogada(linha, coluna, linhaAnt, colAnt, pecaAtual, peca, jogador, peca.getImagem(), pecaAtual.getImagem(),
								qtPecasPlayer1, qtPecasPlayer2).execute();

						// altera a imagem do rato quando entrar no lago
						if (pecaAtual.getClass() == Rato.class)
							if (isLago(linha, coluna))
								((Rato) pecaAtual).setImagem(new ImageIcon("imagens/rato" + jogador + "NoLago.png"));
							else
								((Rato) pecaAtual).setImagem(new ImageIcon("imagens/rato" + jogador + ".png"));

						// verifica se esta na armadilha
						// se for sua propria armadilha só troca a imagem
						// se for armadilha do adversario troca a imagem, e faz
						// força retornar 0
						String nomePeca = pecaAtual.getClass().getName().substring(6, pecaAtual.getClass().getName().length());
						if (isArmadilha(linha, coluna)) {
							((Peca) pecaAtual).estaNaArmadilha(peca.getJogador() != jogador);
							((Peca) pecaAtual).setImagem("imagens/" + nomePeca + jogador + "NaArmadilha.png");
						} else if (!isLago(linha, coluna)) {
							((Peca) pecaAtual).estaNaArmadilha(false);
							((Peca) pecaAtual).setImagem("imagens/" + nomePeca + jogador + ".png");
						}

						temPecaSelecionada = false;

						if (peca.isAnimal()) {
							decQtPecas();
							comeu = true;
						}

						inverteJogador(pecaAtual, peca);

						jogoAcabou = (qtPecasPlayer1 == 0 || qtPecasPlayer2 == 0 || peca.getClass() == Toca.class);

						return true;
					}// fim - terceira condição
					else {
						if (isLago(linha, coluna) && pecaAtual.getClass() != Rato.class)
							setInfoJogo(somenteRatoPodeEntrarNoLago);
						else if (isLago(linhaAnt, colAnt) && peca.getClass() == Elefante.class && pecaAtual.getClass() == Rato.class)
							setInfoJogo(ratoCapturouElefanteLago);
						else if (peca.getForca() > pecaAtual.getForca())
							setInfoJogo("O " + pecaAtual.getNome() + " não pode capturar o " + peca.getNome() + "!");
						else
							setInfoJogo(jogadaNaoPermitida);

						temPecaSelecionada = false;
						return false;
					}
				}// fim - segunda condição
				else {
					if (!numerosDeCasasOk(linha, coluna))
						setInfoJogo(pulouMaisDeUmaCasa);
					else
						setInfoJogo(jogadaNaoPermitida);

					temPecaSelecionada = false;
					return false;
				}
			}// fim - primeira condição
		} else
			setPecaAtual(linha, coluna);

		return false;
	}

	public String getInfoJogo() {
		return infoJogo;
	}

	public void setInfoJogo(String infoJogo) {
		this.infoJogo = infoJogo;
		notificaListeners();
	}

	/**
	 * esse método virou uma classe , para implementar Command!!! a classe de
	 * command é privada e fica no próprio controle !
	 */
	/*
	 * private void fazJogada(int linha, int coluna){
	 * if(isLago(linhaAnt,colAnt)) tabuleiro[linhaAnt][colAnt] = lago; else
	 * if(isArmadilha(linhaAnt, colAnt)){ if(colAnt < 4)
	 * tabuleiro[linhaAnt][colAnt] = armadilha1; else
	 * tabuleiro[linhaAnt][colAnt] = armadilha2; } else
	 * tabuleiro[linhaAnt][colAnt] = grama;
	 * 
	 * tabuleiro[linha][coluna] = pecaAtual; temPecaSelecionada = false; }
	 */

	// inverte o jogador quando completar a jogada
	private void inverteJogador(Peca peca, Peca pecaComida) {
		if (comeu)
			setInfoJogo("O jogador " + jogador + " moveu o " + pecaAtual.getNome() + " e capturou o " + pecaComida.getNome());
		else {
			if (peca.getClass() == Rato.class && pecaComida.getClass() == Lago.class && !isLago(linhaAnt, colAnt))
				setInfoJogo("O " + peca.getNome() + " do jogador " + jogador + " entrou no lago.");
			else if (isLago(linhaAnt, colAnt) && pecaComida.getClass() != Lago.class)
				setInfoJogo("O " + peca.getNome() + " do jogador " + jogador + " saiu no lago.");
			else if (peca.estaNaArmadilha())
				setInfoJogo("O " + peca.getNome() + " do jogador " + jogador + " entrou na armadilha. Todos os animais podem capturá-lo!");
			else if (isArmadilha(linhaAnt, colAnt) && ((peca.getJogador() == 1 && colAnt > 4) || (peca.getJogador() == 2 && colAnt < 4)))
				setInfoJogo("O " + peca.getNome() + " do jogador " + jogador + " saiu da armadilha!");
			else
				setInfoJogo("O jogador " + jogador + " moveu o " + pecaAtual.getNome());
		}

		if (jogador == 1)
			jogador = 2;
		else
			jogador = 1;
	}

	// seta a peça selecionada
	private void setPecaAtual(int linha, int coluna) {
		Peca p = getPeca(linha, coluna);
		if (p.isAnimal() && p.getJogador() == jogador) {
			linhaAnt = linha;
			colAnt = coluna;
			pecaAtual = p;
			temPecaSelecionada = pecaAtual != null;
			if (temPecaSelecionada)
				setInfoJogo("Jogador " + jogador + " selecionou " + pecaAtual.getNome());
		} else {
			if (p.isAnimal() && p.getJogador() != jogador)
				setInfoJogo(selecionouAnimalDoAdversario);
			if (!p.isAnimal())
				setInfoJogo(selecionouGrama);
		}

	}

	// verifica se é lago
	private boolean isLago(int i, int j) {
		return ((i == 2 || i == 1 || i == 4 || i == 5) && (j >= 3 && j <= 5));
	}

	// verifica se o jogador quer pular mais de uma casa
	private boolean numerosDeCasasOk(int linha, int coluna) {
		return (linha - linhaAnt <= 1 && coluna - colAnt <= 1 // horizontais e
																// verticais
				&& linhaAnt - linha <= 1 && colAnt - coluna <= 1 // horizontais
																	// e
																	// verticais*
		&& !isDiagonal(linha, coluna)); // diagonaiss
	}

	// verifica se é diagonal em relação a casa que está
	private boolean isDiagonal(int linha, int coluna) {
		return !((Math.abs((linhaAnt - linha) + (colAnt - coluna)) != 2) && ((linhaAnt - linha) + (colAnt - coluna) != 0));
	}

	private boolean podePularLago(int linha, int coluna) {
		return (((linhaAnt == 0 && colAnt == 3) && (linha == 3 && coluna == 3)) || ((linhaAnt == 0 && colAnt == 4) && (linha == 3 && coluna == 4))
				|| ((linhaAnt == 0 && colAnt == 5) && (linha == 3 && coluna == 5)) || ((linhaAnt == 3 && colAnt == 3) && (linha == 0 && coluna == 3))
				|| ((linhaAnt == 3 && colAnt == 4) && (linha == 0 && coluna == 4)) || ((linhaAnt == 3 && colAnt == 5) && (linha == 0 && coluna == 5))
				|| ((linhaAnt == 6 && colAnt == 3) && (linha == 3 && coluna == 3)) || ((linhaAnt == 6 && colAnt == 4) && (linha == 3 && coluna == 4))
				|| ((linhaAnt == 6 && colAnt == 5) && (linha == 3 && coluna == 5)) || ((linhaAnt == 3 && colAnt == 3) && (linha == 6 && coluna == 3))
				|| ((linhaAnt == 3 && colAnt == 4) && (linha == 6 && coluna == 4)) || ((linhaAnt == 3 && colAnt == 5) && (linha == 6 && coluna == 5))
				|| ((linhaAnt == 1 && colAnt == 2) && (linha == 1 && coluna == 6)) || ((linhaAnt == 2 && colAnt == 2) && (linha == 2 && coluna == 6))
				|| ((linhaAnt == 1 && colAnt == 6) && (linha == 1 && coluna == 2)) || ((linhaAnt == 2 && colAnt == 6) && (linha == 2 && coluna == 2))
				|| ((linhaAnt == 4 && colAnt == 2) && (linha == 4 && coluna == 6)) || ((linhaAnt == 5 && colAnt == 2) && (linha == 5 && coluna == 6))
				|| ((linhaAnt == 4 && colAnt == 6) && (linha == 4 && coluna == 2)) || ((linhaAnt == 5 && colAnt == 6) && (linha == 5 && coluna == 2)))
				&& !temRatoNoLago(linha, linhaAnt, coluna);
	}

	// decremente a quantidade de peças de um jogador, quando algum animal é
	// comido
	private void decQtPecas() {
		if (jogador == 1)
			qtPecasPlayer2--;
		else
			qtPecasPlayer1--;
	}

	// verifica se o jogo acabou
	public boolean jogoAcabou() {
		return jogoAcabou;
	}

	// verifica se existe rato no lago, para o leão ou tigre poderem pular
	private boolean temRatoNoLago(int linha, int linhaAnt, int coluna) {
		if (linhaAnt == linha) {
			return getPeca(linha, 3).getClass() == Rato.class || getPeca(linha, 4).getClass() == Rato.class
					|| getPeca(linha, 5).getClass() == Rato.class;
		} else {
			if (linhaAnt == 0 || linha == 0)
				return getPeca(1, coluna).getClass() == Rato.class || getPeca(2, coluna).getClass() == Rato.class;
			if (linhaAnt == 6 || linha == 6)
				return getPeca(4, coluna).getClass() == Rato.class || getPeca(5, coluna).getClass() == Rato.class;
		}
		return false;
	}

	public int getJogador() {
		return jogador;
	}

	// verifica se a casa que vai entrar é armadilha
	// se for a armadilha do adversário, a força do animal retorna 0
	private boolean isArmadilha(int linha, int coluna) {
		return ((linha == 2 || linha == 4) && (coluna == 0 || coluna == 8)) || linha == 3 && (coluna == 1 || coluna == 7);
	}

	// verifica se é toca do adversario
	public boolean isTocaDoAdversario(Peca peca) {
		return peca.getClass() == Toca.class && peca.getJogador() != jogador;
	}

	public Peca getPecaAtual() {
		return pecaAtual;
	}

	private void notificaListeners() {
		for (DouShouQiListener l : listeners)
			l.notificaAlteracao();
	}

	public Command getUltimaJogada() {
		return ultimaJogada;
	}

	public void setUltimaJogada(Command ultimaJogada) {
		this.ultimaJogada = ultimaJogada;
	}

	/*
	 * CLASSE QUE IMPLEMENTARÁ COMMAND !
	 */
	class Jogada implements Command {

		private int linhaCaptura, colunaCaptura;
		private int linhaQueJogou, colunaQueJogou;
		private Peca pecaQueJogou, pecaCapturada;
		private int jogadorQueJogou;
		private ImageIcon imgCapturada, imgQueJogou;
		private int qtPecasJogador1, qtPecasJogador2;

		public Jogada(int linhaCaptura, int colunaCaptura, int linhaQueJogou, int colunaQueJogou, Peca pecaQueJogou, Peca pecaCapturada,
				int jogadorQueJogou, ImageIcon imgCapturada, ImageIcon imgQueJogou, int qtPecasJogador1, int qtPecasJogador2) {
			this.linhaCaptura = linhaCaptura;
			this.colunaCaptura = colunaCaptura;
			this.linhaQueJogou = linhaQueJogou;
			this.colunaQueJogou = colunaQueJogou;
			this.pecaQueJogou = pecaQueJogou;
			this.pecaCapturada = pecaCapturada;
			this.jogadorQueJogou = jogadorQueJogou;
			this.imgCapturada = imgCapturada;
			this.imgQueJogou = imgQueJogou;
			this.qtPecasJogador1 = qtPecasJogador1;
			this.qtPecasJogador2 = qtPecasJogador2;
		}

		public void execute() {
			if (isLago(linhaQueJogou, colunaQueJogou))
				tabuleiro[linhaQueJogou][colunaQueJogou] = lago;
			else if (isArmadilha(linhaQueJogou, colunaQueJogou)) {
				if (colAnt < 4)
					tabuleiro[linhaQueJogou][colunaQueJogou] = armadilha1;
				else
					tabuleiro[linhaQueJogou][colunaQueJogou] = armadilha2;
			} else
				tabuleiro[linhaQueJogou][colunaQueJogou] = grama;

			tabuleiro[linhaCaptura][colunaCaptura] = pecaAtual;

			setUltimaJogada(this);
		}

		public void unexecute() {
			pecaCapturada.setImagem(imgCapturada.toString());
			pecaQueJogou.setImagem(imgQueJogou.toString());
			tabuleiro[linhaCaptura][colunaCaptura] = pecaCapturada;
			tabuleiro[linhaQueJogou][colunaQueJogou] = pecaQueJogou;
			setUltimaJogada(null);
			setInfoJogo("Vez do jogador " + jogadorQueJogou);
			jogador = jogadorQueJogou;
			qtPecasPlayer1 = qtPecasJogador1;
			qtPecasPlayer2 = qtPecasJogador2;
		}

	}

}
