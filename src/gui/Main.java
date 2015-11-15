package gui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
 
public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JList<String> countryList;
	
	private DefaultListModel<String> listModel = new DefaultListModel<>();
    
    public Main() {
        //create the model and add elements
        listModel.addElement("USA");
        listModel.addElement("India");
        listModel.addElement("Vietnam");
        listModel.addElement("Canada");
        listModel.addElement("Denmark");
        listModel.addElement("France");
        listModel.addElement("Great Britainzdfzdfhzdfh");
        listModel.addElement("Japan");
 
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        
        //create the list
        countryList = new JList<>(listModel);
        add(countryList);
        JList<String> countryList2 = new JList<>(listModel);
        add(countryList2);
         
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Salão Beleza Pura");       
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        add(new JScrollPane(countryList));
        add(new JScrollPane(countryList2));
        countryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
    }
    
    public void execute() {
    	for(int i = 0; i < 10; i++) {
        	try {
				Thread.sleep(100);
				listModel.addElement("Teste" + i);
        	} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
     
    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
                for(int i = 0; i < 10; i++) {
                	try {
        				Thread.sleep(100);
        				listModel.addElement("Teste" + i);
                	} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
                }
            }
        });*/
    	Main m = new Main();
    	m.execute();
    }       
}
