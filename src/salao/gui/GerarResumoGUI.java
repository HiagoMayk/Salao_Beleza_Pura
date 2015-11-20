package salao.gui;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class GerarResumoGUI extends AbstractGUI {

	private static final long serialVersionUID = 1L;
	
	private JList<String> linhas;
	
	private DefaultListModel<String> listModel;

	public GerarResumoGUI() {
		super("Resumo das movimentações", 400, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		listModel = new DefaultListModel<String>();
		linhas = new JList<String>(listModel);		
		add(linhas);
		JScrollPane sp = new JScrollPane(linhas);
		add(sp);
		
		new Thread() {
        	public void run() {
        		try {
        			while(true) {
        				update();
        				Thread.sleep(100);
        			}
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        	}

        	private void update() {
        		SwingUtilities.invokeLater(new Runnable() {
        			public void run() {
        				atualizaResumo();
        			}
        		});
        	}
        }.start();
	}
	
	public void atualizaResumo() {
		listModel.addElement("teste");
	}

}
