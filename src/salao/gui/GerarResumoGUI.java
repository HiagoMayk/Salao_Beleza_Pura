package salao.gui;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import salao.funcionarios.Cabeleireira;
import salao.funcionarios.Depiladora;
import salao.funcionarios.Funcionario;
import salao.funcionarios.Manicure;
import salao.funcionarios.Massagista;

public class GerarResumoGUI extends AbstractGUI {

	private static final long serialVersionUID = 1L;
	
	private JList<String> linhas;
	
	private DefaultListModel<String> listModel;
	
	private List<Funcionario> funcionarios;

	public GerarResumoGUI(List<Funcionario> funcionarios) {
		super("Resumo das movimentações", 400, 250);
		this.funcionarios = funcionarios;
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
		//final int tiposFuncionarios = 4;
		//int qtdAtendimentos = 0;
		double lucroTotal = 0.0;
		listModel.clear();
		
		for(Funcionario f : funcionarios) {
			//qtdAtendimentos += f.getQtdServicos();
			lucroTotal += f.getTotalFaturadoBruto();
			if(f instanceof Cabeleireira) {
				f = (Cabeleireira) f;
				listModel.addElement(f.toString() + " R$ " + format(f.getTotalFaturadoLiquido()));
			} else if(f instanceof Depiladora) {
				f = (Depiladora) f;
				listModel.addElement(f.toString() + " R$ " + format(f.getTotalFaturadoLiquido()));
			} else if(f instanceof Manicure) {
				f = (Manicure) f;
				listModel.addElement(f.toString() + " R$ " + format(f.getTotalFaturadoLiquido()));
			} else if(f instanceof Massagista) {
				f = (Massagista) f;
				listModel.addElement(f.toString() + " R$ " + format(f.getTotalFaturadoLiquido()));
			}
		}
		listModel.addElement("Lucro total do salão:" + " R$ " + format(lucroTotal));		
	}
	
	private String format(double x) {  
	    return String.format("%.2f", x);  
	}

}
