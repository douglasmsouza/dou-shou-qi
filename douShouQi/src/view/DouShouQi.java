package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import controller.DouShouQiListener;

public class DouShouQi extends JFrame implements DouShouQiListener{		

	private DouShouQiTableModel tableModel = new DouShouQiTableModel();
	private JTabuleiro tabuleiro = new JTabuleiro(tableModel);		
	private JToolBar toolBar = new JToolBar(); 
	private JLabel labelInfo = new JLabel("");		
	private Properties config = new Properties();
	private JCheckBoxMenuItem mostrarStatus;		
	private JButton botaoNovo = new JButton(new ImageIcon("imagens/novo.gif"));
	private JButton botaoSalvar = new JButton(new ImageIcon("imagens/salvar.gif"));
	private JButton botaoAbrir = new JButton(new ImageIcon("imagens/abrir.gif"));	
	private NovoJogo novoJogo = new NovoJogo();
	private SalvarJogo salvarJogo = new SalvarJogo();
	private AbrirJogo abrirJogo = new AbrirJogo();	
	private JMenuItem desfazer;
			
	public DouShouQi() {
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				try {
					if(!tableModel.finalizaJogo())			
						setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);					
				} catch (Exception e1) {					
				}						
			}	
		});	
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setTitle("Dou Shou Qi");	
		setJMenuBar(menu());
		setInfoJogo(tableModel.getControle().getInfoJogo());			
		setSize(640,520);		
		setLocationRelativeTo(null);
		initComponents();
		 
	}

	private void initComponents(){
		tabuleiro.adicionaListener(this);				
		//
		botaoNovo.addActionListener(novoJogo);
		botaoAbrir.addActionListener(abrirJogo);
		botaoSalvar.addActionListener(salvarJogo);		
		//
		lerArquivoConfiguracao();
		desfazer.setEnabled(false);
		//
		getContentPane().add(BorderLayout.NORTH,barraDeFerramentas());
		getContentPane().add(BorderLayout.CENTER,tabuleiro);			
	}

	private JToolBar barraDeFerramentas(){
		toolBar.add(botaoNovo);
		toolBar.add(botaoAbrir);
		toolBar.add(botaoSalvar);			
		toolBar.addSeparator(new Dimension(20,toolBar.getHeight()));		
		toolBar.add(labelInfo);	
		toolBar.setFloatable(false);	
		toolBar.setBorder(BorderFactory.createEtchedBorder());		
					
		return toolBar;
	}

	private JMenuBar menu(){
		JMenuBar menuPrincipal = new JMenuBar();

		JMenu arquivos = new JMenu("Arquivos");
		JMenuItem novo = new JMenuItem("Novo jogo");
		novo.addActionListener(novoJogo);
		JMenuItem abrir = new JMenuItem("Abrir...");
		abrir.addActionListener(abrirJogo);
		JMenuItem salvar = new JMenuItem("Salvar...");
		salvar.addActionListener(salvarJogo);
		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new FinalizarJogo());
		arquivos.add(novo);
		arquivos.add(abrir);
		arquivos.add(salvar);
		arquivos.addSeparator();
		arquivos.add(sair);		  	

		JMenu opcoes = new JMenu("Opções");
		mostrarStatus = new JCheckBoxMenuItem("Mostrar barra de ferramentas");
		mostrarStatus.addActionListener(new MostrarStatusJogo());
		desfazer = new JMenuItem("Desfazer jogada...");
		desfazer.addActionListener(new Desfazer());
		opcoes.add(desfazer);
		opcoes.add(mostrarStatus);		

		JMenu ajuda = new JMenu("Ajuda");
		JMenuItem conteudo = new JMenuItem("Conteúdo...");		
		conteudo.addActionListener(new Ajuda());
		ajuda.add(conteudo);

		menuPrincipal.add(arquivos);
		menuPrincipal.add(opcoes);
		menuPrincipal.add(ajuda);		
		
		return menuPrincipal;
	}	

	private void lerArquivoConfiguracao(){
		try {
			File f = new File("config.dsq");
			if(f.exists()){
				config.load(new FileInputStream(f));
				toolBar.setVisible(Boolean.valueOf(config.getProperty("barra_ferramentas")));								
			}	
			mostrarStatus.setSelected(toolBar.isVisible());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao acessar arquivo de configuração!");
		} 
	}
	
	public void setInfoJogo(String info){
		labelInfo.setText(info);
	}
		
	public void notificaAlteracao() {		
		desfazer.setEnabled(tableModel.getControle().getUltimaJogada() != null);
		setInfoJogo(tableModel.getControle().getInfoJogo());		
	}
			
	//classes dos botões
	class FinalizarJogo implements ActionListener{
		public void actionPerformed(ActionEvent evento){ 
			try {
				tableModel.finalizaJogo();
			} catch (Exception e) {			
			}   	
		}
	}

	class NovoJogo implements ActionListener{		
		public void actionPerformed(ActionEvent arg0) {
			tableModel.novoJogo();			
			setInfoJogo(tableModel.getControle().getInfoJogo());
		}
	}		


	class MostrarStatusJogo implements ActionListener{		
		public void actionPerformed(ActionEvent arg0) {	
			toolBar.setVisible(mostrarStatus.isSelected());
			config.put("barra_ferramentas", String.valueOf(mostrarStatus.isSelected()));
			try {
				config.store(new FileOutputStream("config.dsq"), "default");
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"Erro ao acessar arquivo de configuração!");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Erro ao acessar arquivo de configuração!");
			}
		}
	}
	
	class SalvarJogo implements ActionListener{		
		public void actionPerformed(ActionEvent arg0) {
			try {
				tableModel.salvarJogo();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Ocorreu um erro ao tentar salvar o jogo!");
			}
		}
	}

	class AbrirJogo implements ActionListener{	
		public void actionPerformed(ActionEvent arg0) {
			try {
				tableModel.abrirJogo();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Ocorreu um erro ao tentar abrir o jogo!");
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null,"Ocorreu um erro ao tentar abrir o jogo!");				
			}
		}
	}		
	
	class Ajuda implements ActionListener{	
		public void actionPerformed(ActionEvent e) {
			tableModel.mostraAjuda();
		}
	}
	
	class Desfazer implements ActionListener{		
		public void actionPerformed(ActionEvent arg0) {
			try{
				tableModel.desfazer();
			}catch(Exception e){				
			}
			
		}
	}

	public static void main(String args[]){
		UIManager.put("OptionPane.cancelButtonText", "Cancelar"); 
		UIManager.put("OptionPane.yesButtonText", "Sim");  
		UIManager.put("OptionPane.noButtonText", "Não");
		UIManager.put("FileChooser.saveInLabelText", "Salvar em");
		UIManager.put("FileChooser.lookInLabelText", "Abrir de");
		UIManager.put("FileChooser.fileNameLabelText", "Nome do arquivo");   
		UIManager.put("FileChooser.filesOfTypeLabelText", "Tipo do arquivo");
		UIManager.put("FileChooser.saveButtonText","Salvar");
		UIManager.put("FileChooser.openButtonText","Abrir");
		UIManager.put("FileChooser.cancelButtonText","Cancelar");
		UIManager.put("FileChooser.acceptAllFileFilterText","Todos os arquivos (*.*)");

		DouShouQi douShouQi = new DouShouQi();	
		douShouQi.setVisible(true);		
	}	

}
