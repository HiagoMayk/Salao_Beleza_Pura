package salao.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import salao.funcionarios.Cabeleireira;
import salao.funcionarios.Caixa;
import salao.funcionarios.Depiladora;
import salao.funcionarios.Funcionario;
import salao.funcionarios.Manicure;
import salao.funcionarios.Massagista;
import salao.simulador.GeradorClientes;

public class GerarResumoGUI extends AbstractGUI {

	private static final long serialVersionUID = 1L;
	
	private JList<String> linhas;
	
	private DefaultListModel<String> listModel;
	
	private List<Funcionario> funcionarios;
	
	private GeradorClientes geradorClientes;
	
	private List<Caixa> caixas;

	public GerarResumoGUI(List<Funcionario> funcionarios, List<Caixa> caixas, GeradorClientes geradorClientes) {
		super("Resumo das movimentações", 400, 500);
		this.funcionarios = funcionarios;
		this.geradorClientes = geradorClientes;
		this.caixas = caixas;
		
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
		double lucroTotalBruto = 0.0;
		double lucroTotalLiquido = 0.0;
		int totalAtendimentoCaixas = caixas.get(0).getQTDClientes() + caixas.get(1).getQTDClientes();
		
		listModel.clear();
		
		listModel.addElement("------------------------- Lucro Funcionários -------------------------");
		for(Funcionario f : funcionarios) {
			//qtdAtendimentos += f.getQtdServicos();
			lucroTotalBruto += f.getTotalFaturadoBruto();
			lucroTotalLiquido += f.getTotalFaturadoLiquido();
			if(f instanceof Cabeleireira) {
				f = (Cabeleireira) f;
				listModel.addElement(f.toString() + ": Bruto = R$ " + formatDouble(f.getTotalFaturadoBruto()) + ", Liq = R$ " + formatDouble(f.getTotalFaturadoLiquido()));
			} else if(f instanceof Depiladora) {
				f = (Depiladora) f;
				listModel.addElement(f.toString() +  ": Bruto = R$ " + formatDouble(f.getTotalFaturadoBruto()) + ", Liq = R$ " + formatDouble(f.getTotalFaturadoLiquido()));
			} else if(f instanceof Manicure) {
				f = (Manicure) f;
				listModel.addElement(f.toString() +  ": Bruto = R$ " + formatDouble(f.getTotalFaturadoBruto()) + ", Liq = R$ " + formatDouble(f.getTotalFaturadoLiquido()));
			} else if(f instanceof Massagista) {
				f = (Massagista) f;
				listModel.addElement(f.toString() +  ": Bruto = R$ " + formatDouble(f.getTotalFaturadoBruto()) + ", Liq = R$ " + formatDouble(f.getTotalFaturadoLiquido()));
			}
		}
		listModel.addElement(".");
		listModel.addElement("------------------------------ Lucro Salão ------------------------------");
		listModel.addElement("Bruto:" + " R$ " + formatDouble(lucroTotalBruto));
		listModel.addElement("Liquido:" + " R$ " + formatDouble(lucroTotalBruto - lucroTotalLiquido));
		
		listModel.addElement(".");
		listModel.addElement("------------------------- Total de clientes ----------------------------");
		listModel.addElement("Total de atendimentos no caixa:" + formatInt(caixas.get(0).getQTDClientes() + caixas.get(1).getQTDClientes()));
		listModel.addElement("Total de clientes no salão:" + formatInt(geradorClientes.getContClientes() - totalAtendimentoCaixas));
		listModel.addElement("Total de clientes té o momento:" + formatInt(geradorClientes.getContClientes()));
		
		
	}
	
	private String formatDouble(double x) {  
	    return String.format("%.2f", x);  
	}
	
	private String formatInt(int x) {  
	    return String.format("%d", x);  
	}

}
