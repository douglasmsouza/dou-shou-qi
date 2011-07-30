package view;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import controller.Command;
import controller.Controle;
import controller.DouShouQiListener;


public class DouShouQiTableModel extends AbstractTableModel{


	private Controle controle;	  
	private JAjuda ajuda = new JAjuda();
	private ArrayList<DouShouQiListener> listeners = new ArrayList<DouShouQiListener>();
	
	public DouShouQiTableModel() {
		controle = new Controle(listeners);				
	}

	public int getColumnCount() {		
		return 9;
	}

	public int getRowCount() {	
		return 7;
	}	

	public Object getValueAt(int row,int col) {	
		return controle.getPeca(row, col);		
	}

	public String getColumnName(int column) {		
		return "";
	}

	public Controle getControle() {		
		return controle;
	}	

	public void setControle(Controle controle) {
		this.controle = controle;
	}

	public void movimenta(int linha, int coluna){		
		if (controle.movimentaPeca(linha,coluna))				
			fireTableDataChanged();	

		//verifica se o jogo acabou
		if(controle.jogoAcabou()){						
			if(JOptionPane.showConfirmDialog(null, "O jogo acabou! Jogar novamente?","DouShouQi",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				novoJogo();					
			else
				System.exit(0);
		}			
	}

	public void novoJogo(){		
		controle = new Controle(listeners);
		setControle(controle);
		fireTableDataChanged();		
	}

	public boolean finalizaJogo() throws Exception{
		int resp = JOptionPane.showConfirmDialog(null, "Deseja salvar o jogo antes de sair?","DouShouQi",JOptionPane.YES_NO_CANCEL_OPTION); 
		if(resp == JOptionPane.YES_OPTION){
			salvarJogo();
			System.exit(0);
			return true;
		}        		
		if(resp == JOptionPane.NO_OPTION){
			System.exit(0);
			return true;
		}		
		return false;
	}

	//método para salvar o jogo
	//será serializada essa classe de controle
	public void salvarJogo() throws IOException{					
		JFileChooser caixaSalvar = new JFileChooser();		
		caixaSalvar.setCurrentDirectory(new File(""));
		if(caixaSalvar.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
			FileOutputStream arq = new FileOutputStream(caixaSalvar.getSelectedFile().getPath()+".sav");
			ObjectOutputStream arqObjeto = new ObjectOutputStream(arq);
			arqObjeto.writeObject(controle);
			arq.close();
		}
	}	

	//método para abrir um jogo salvo
	//será desserializada uma classe de controle
	public void abrirJogo() throws IOException, ClassNotFoundException{		
		JFileChooser caixaAbrir = new JFileChooser();		
		caixaAbrir.setCurrentDirectory(new File(""));		
		if(caixaAbrir.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			FileInputStream arq = new FileInputStream(caixaAbrir.getSelectedFile());
			ObjectInputStream arqObjeto = new ObjectInputStream(arq);
			setControle((Controle)arqObjeto.readObject());
			fireTableDataChanged();
			arq.close();					
		}		
	}	

	public void adicionaListener(DouShouQiListener l){
		listeners.add(l);
	}
	
	public void mostraAjuda(){
		ajuda.setVisible(true);
	}	
	
	// só será possível desfazer a última jogada!!
	public void desfazer(){
		Command ultimaJogada = controle.getUltimaJogada();
		if(ultimaJogada != null)
			ultimaJogada.unexecute();
	}
	
	

}

