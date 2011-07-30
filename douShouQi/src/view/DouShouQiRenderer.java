package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import controller.Controle;
import model.Peca;



public class DouShouQiRenderer extends DefaultTableCellRenderer{

	private Image imagem;
	private int alturaImg,larguraImg, alturaDraw, larguraDraw;	
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		Peca pAtual = ((DouShouQiTableModel)table.getModel()).getControle().getPecaAtual();
		Peca pSelecionada = ((Peca)value);
		Controle controle = ((DouShouQiTableModel)table.getModel()).getControle();
				
		try{				
			if(((hasFocus && 
					pSelecionada.isAnimal() && 
					controle.getJogador() == pSelecionada.getJogador())&& 
					pAtual == (Peca)value)){			
				
				larguraDraw = table.getColumnModel().getTotalColumnWidth() / 9;
				alturaDraw = table.getRowHeight();	
									
			}							
			else{
				alturaDraw = 0;
				larguraDraw = 0;
			}
		}
		catch(ArrayIndexOutOfBoundsException e){} 
		catch(NullPointerException exc){}					
		//		
		larguraImg = table.getColumnModel().getTotalColumnWidth() / 9;
		table.setRowHeight(larguraImg - 10);
		alturaImg = table.getRowHeight();		  
		imagem = pSelecionada.getImagem().getImage(); 	    		
	    setIcon(pSelecionada.getImagem());
		//
		return this;
	}
	
	@Override
	public void paint(Graphics g) {		
		g.drawImage(imagem, 0, 0, larguraImg, alturaImg, null);
		g.setColor(Color.WHITE);		
		g.drawRect(0, 0, larguraDraw-2, alturaDraw-2);		
		//g.draw3DRect(0, 0, larguraDraw-2, alturaDraw-2,true);		
	}
	
}

