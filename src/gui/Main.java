package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import salao.Cliente;
import salao.Salao;
 
public class Main extends JFrame {
   
	private static final long serialVersionUID = 1L;
	
	private JList<String> countryList;
	
	private Salao salao = new Salao();

    public Main() throws InterruptedException {
    	
    	salao.executar();
    	
    	// create the model and add elements
        ArrayList<DefaultListModel<String>> listsFilas = new ArrayList<DefaultListModel<String>>();
        
        for(int fila = 5; fila >= 1; fila--)
		{
        	listsFilas.add(fila-1, new DefaultListModel<String>());
        	listsFilas.get(fila-1).addElement("-- Fila " + fila + " --");			
			for(Cliente c: salao.getFilas().getFila(fila)) {
				System.out.println("TESTE");
				listsFilas.get(fila).addElement("cliente" + c.getIdCliente() + " - " + c.verServico() + " - " + c.getQtdServicos());
			}
		}
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        
        ArrayList<JList<String>> filas = new ArrayList<JList<String>>();
        for(int i = 0; i < 5; i++) {
        	filas.add(i, new JList<String>(listsFilas.get(i)));
        	c.add(filas.get(i));
        }
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Salão Beleza Pura");       
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //add(new JScrollPane(countryList));
        countryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
					new Main();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }       
}
