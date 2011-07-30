package view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import controller.DouShouQiListener;

public class JTabuleiro extends JTable implements MouseListener,KeyListener{

	private DouShouQiTableModel tableModel;	

	public JTabuleiro(DouShouQiTableModel tableModel) {
		setModel(tableModel);
		setRowHeight(60);	
		setDefaultRenderer(Object.class, new DouShouQiRenderer());	
		setGridColor(Color.DARK_GRAY);
		this.tableModel = tableModel;
		addKeyListener(this);
		addMouseListener(this);
	}	

	public void adicionaListener(DouShouQiListener l){
		tableModel.adicionaListener(l);
	}

	//eventos de MouseListener
	public void mousePressed(MouseEvent e){
		try{ 
			tableModel.movimenta(getSelectedRow(),getSelectedColumn());
		}catch(Exception except){			
		}					
	}	

	public void mouseClicked(MouseEvent e) {}		
	public void mouseEntered(MouseEvent e) {}		
	public void mouseExited(MouseEvent e) {}			
	public void mouseReleased(MouseEvent e) {}	

	//eventos de KeyListener
	public void keyPressed(KeyEvent e) {	
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z)
			try{
				tableModel.desfazer();
			}catch(Exception exception){			
			}
	}	
	public void keyReleased(KeyEvent e) {}	
	public void keyTyped(KeyEvent e) {}

}
