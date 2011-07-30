package controller;

/*
 * A CLASSE QUE IMPLEMENTA COMMAND
 * É UMA CLASSE PRIVADA DENTRO DA CLASSE CONTROLE... 
 */

public interface Command {
	
	void execute();
	void unexecute();
	
}
