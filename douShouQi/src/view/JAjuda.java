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
		areaRegras.append("1 - Cada jogador controla 8 peças representando animais.Cada animal tem sua força: \n");
		areaRegras.append("      Elefante 8 \n");
		areaRegras.append("      Leão 7 \n");
		areaRegras.append("      Tigre 6 \n");
		areaRegras.append("      Leopardo 5 \n");
		areaRegras.append("      Lobo 4 \n");
		areaRegras.append("      Cachorro 3 \n");
		areaRegras.append("      Gato 2 \n");
		areaRegras.append("      Rato 1 \n");
		areaRegras.append("\n");
		areaRegras.append("2 -Na sua vez, o jogador move uma de suas peças, apenas uma casa, em qualquer direção, na vertical ou horizontal, mas não na diagonal. Não pode haver mais de um animal na mesma casa. Somente o rato pode entrar nos lagos. Nenhum animal pode entrar na própria toca. \n");		
		areaRegras.append("\n");
		areaRegras.append("3 - Somente o rato pode entrar nos lagos\n");
		areaRegras.append("\n");
		areaRegras.append("4 - Nenhum animal pode entrar na própria toca\n");
		areaRegras.append("\n");
		areaRegras.append("5 - Um animal pode capturar somente outro mais fraco, com exceção do rato, que pode capturar o elefante, desde de que não esteja saindo do lago \n");
		areaRegras.append("\n");
		areaRegras.append("6 - O leão e o tigre podem saltar de uma casa adjacente a um lago até a margem oposta, em linha reta, na vertical ou horizontal, desde que não haja rato nas casas puladas. Podem atacar nesse movimento, caindo direto sobre a presa. \n");
		areaRegras.append("\n");
		areaRegras.append("7 - Qualquer animal que esteja numa armadilha próxima à toca do adversário fica totalmente vulnerável e pode ser atacado por qualquer um. Animais em armadilhas junto à sua própria toca são tratados normalmente. \n");
		areaRegras.append("\n");
		areaRegras.append("8 - Vence quem conseguir chegar à toca do adversário, ou capturar todos os animais do oponente. \n");
		
		
		areaSobre.append("\n  FURB - 2008  \n");
		areaSobre.append("  Centro de ciências exatas e naturais \n");
		areaSobre.append("  Departamento de sistemas e computação \n");
		areaSobre.append("  Jogo desenvolvido para a disciplina de Programação \n \n");
		areaSobre.append("\n   Desenvolvido por : \n");
		areaSobre.append("\t   Douglas Matheus de Souza");
		
		getContentPane().add(BorderLayout.NORTH,painel);
		getContentPane().add(BorderLayout.CENTER,pageControl);
		
	}		
}
