package view;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class JAjuda extends JFrame{

	public JAjuda() {			
		setTitle("Ajuda");						
		setSize(500,400);		
		setLocationRelativeTo(null);		
		initComponents();
	}

	private void initComponents() {		
		JPanel painel = new JPanel();
		JLabel titulo = new JLabel("Dou Shou Qi");		
		painel.setFont(new Font("Verdana",Font.BOLD,14));
		painel.add(titulo);
		
		
		JTabbedPane pageControl = new JTabbedPane();		
		JTextArea areaRegras = new JTextArea();		
		JTextArea areaSobre = new JTextArea();		
		JScrollPane scroll = new JScrollPane(areaRegras);
		
		pageControl.add("Regras", scroll);
		pageControl.add("Sobre...", areaSobre);
		
		areaRegras.setLineWrap(true);
		areaRegras.setWrapStyleWord(true);
		areaRegras.setEditable(false);
		areaRegras.setBackground(getBackground());
		areaRegras.setSelectionColor(getBackground());
		areaSobre.setLineWrap(true);
		areaSobre.setWrapStyleWord(true);
		areaSobre.setEditable(false);
		areaSobre.setBackground(getBackground());
		areaSobre.setSelectionColor(getBackground());
		
		areaRegras.append("\n");		
		areaRegras.append("1 - Cada jogador controla 8 pe�as representando animais.Cada animal tem sua for�a: \n");
		areaRegras.append("      Elefante 8 \n");
		areaRegras.append("      Le�o 7 \n");
		areaRegras.append("      Tigre 6 \n");
		areaRegras.append("      Leopardo 5 \n");
		areaRegras.append("      Lobo 4 \n");
		areaRegras.append("      Cachorro 3 \n");
		areaRegras.append("      Gato 2 \n");
		areaRegras.append("      Rato 1 \n");
		areaRegras.append("\n");
		areaRegras.append("2 -Na sua vez, o jogador move uma de suas pe�as, apenas uma casa, em qualquer dire��o, na vertical ou horizontal, mas n�o na diagonal. N�o pode haver mais de um animal na mesma casa. Somente o rato pode entrar nos lagos. Nenhum animal pode entrar na pr�pria toca. \n");		
		areaRegras.append("\n");
		areaRegras.append("3 - Somente o rato pode entrar nos lagos\n");
		areaRegras.append("\n");
		areaRegras.append("4 - Nenhum animal pode entrar na pr�pria toca\n");
		areaRegras.append("\n");
		areaRegras.append("5 - Um animal pode capturar somente outro mais fraco, com exce��o do rato, que pode capturar o elefante, desde de que n�o esteja saindo do lago \n");
		areaRegras.append("\n");
		areaRegras.append("6 - O le�o e o tigre podem saltar de uma casa adjacente a um lago at� a margem oposta, em linha reta, na vertical ou horizontal, desde que n�o haja rato nas casas puladas. Podem atacar nesse movimento, caindo direto sobre a presa. \n");
		areaRegras.append("\n");
		areaRegras.append("7 - Qualquer animal que esteja numa armadilha pr�xima � toca do advers�rio fica totalmente vulner�vel e pode ser atacado por qualquer um. Animais em armadilhas junto � sua pr�pria toca s�o tratados normalmente. \n");
		areaRegras.append("\n");
		areaRegras.append("8 - Vence quem conseguir chegar � toca do advers�rio, ou capturar todos os animais do oponente. \n");
		
		
		areaSobre.append("\n  FURB - 2008  \n");
		areaSobre.append("  Centro de ci�ncias exatas e naturais \n");
		areaSobre.append("  Departamento de sistemas e computa��o \n");
		areaSobre.append("  Jogo desenvolvido para a disciplina de Programa��o \n \n");
		areaSobre.append("\n   Desenvolvido por : \n");
		areaSobre.append("\t   Douglas Matheus de Souza");
		
		getContentPane().add(BorderLayout.NORTH,painel);
		getContentPane().add(BorderLayout.CENTER,pageControl);
		
	}		
}
