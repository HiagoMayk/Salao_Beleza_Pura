package salao.gui;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

public class GerarResumoGUI extends AbstractGUI {

	private static final long serialVersionUID = 1L;
	
	private JList<String> linhas;
	
	private DefaultListModel<String> listModel;

	public GerarResumoGUI() {
		super("Resumo das movimentações", 400, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		linhas = new JList<String>();
		listModel = new DefaultListModel<String>();
		add(linhas);
		listModel.addElement("teste");
	}

}
