package salao.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import salao.cliente.Cliente;
import salao.funcionarios.Funcionario;
import salao.simulador.FilasClientes;

public class MainScreen extends AbstractGUI implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private List<JList<String>> queueLists;
	
	private List<DefaultListModel<String>> listModels;
	
	private JButton logButton;
	
	private FilasClientes filas;
	
	private List<Funcionario> funcionarios;
	
	private Semaphore sFilasClientes;
	
	private Semaphore sFilasCaixas;
	
	private int colunas = 0;

	public MainScreen(FilasClientes filas, List<Funcionario> funcionarios, Semaphore sFilasClientes, Semaphore sFilasCaixas) {
		super("Salão Beleza Pura", 800, 1000);
		this.queueLists = new ArrayList<JList<String>>();
		listModels = new ArrayList<DefaultListModel<String>>();
		logButton = new JButton("Gerar resumo");
		this.filas = filas;
		this.funcionarios = funcionarios;
		this.sFilasClientes = sFilasClientes;
		this.sFilasCaixas = sFilasCaixas;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
        c.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);
       
		criaFilasDeServicos();
		criaFilasCaixas();
		criaColunaFuncionarios();
		
		logButton.addActionListener(this);
        add(logButton);
        
        new Thread() {
        	public void run() {
        		try {
        			while(true) {
        				update();
        				Thread.sleep(1000);
        			}
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        	}

        	private void update() {
        		SwingUtilities.invokeLater(new Runnable() {
        			public void run() {
        				atualizaFilasDeServicos();
        				atualizaFilasCaixas();
        				atualizaFuncionarios();
        			}
        		});
        	}
        }.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		GerarResumoGUI tela = new GerarResumoGUI(funcionarios);
		tela.setVisible(true);
	}
	
	public void criaFilasDeServicos() {
		for(int i = 0; i < filas.getNumFilasClientes(); i++, colunas++) {
        	DefaultListModel<String> listModel = new DefaultListModel<>();
            listModels.add(listModel);

            // create the list
            queueLists.add(new JList<String>(listModels.get(colunas)));
            JLabel rotulo1 = new JLabel("Fila " + (i+1));
            add(queueLists.get(colunas));
            add(rotulo1);
            
            Dimension d = queueLists.get(colunas).getPreferredSize();
            d.height = 70;
            d.width = 800;
            
            JScrollPane sp = new JScrollPane(queueLists.get(colunas));
            sp.setPreferredSize(d);
            add(sp);
            queueLists.get(colunas).setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
		}
	}
	
	public void criaFilasCaixas() {
		for(int i = 0; i < filas.getNumFilasCaixas(); i++, colunas++) {
        	DefaultListModel<String> listModel = new DefaultListModel<>();
            listModels.add(listModel);

            // create the list
            queueLists.add(new JList<String>(listModels.get(colunas)));
            add(queueLists.get(colunas));
            JLabel rotulo1 = new JLabel("Caixa");
            add(rotulo1);
            
            Dimension d = queueLists.get(colunas).getPreferredSize();
            d.height = 100;
            d.width = 200;
            
            JScrollPane sp = new JScrollPane(queueLists.get(colunas));
            sp.setPreferredSize(d);
            add(sp);
            queueLists.get(colunas).setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
	}
	
	public void criaColunaFuncionarios() {
    	DefaultListModel<String> listModel = new DefaultListModel<>();
        listModels.add(listModel);

        // create the list
        queueLists.add(new JList<String>(listModels.get(colunas)));
        add(queueLists.get(colunas));
        JLabel rotulo1 = new JLabel("Funcionários");
        add(rotulo1);
        
        Dimension d = queueLists.get(colunas).getPreferredSize();
        d.height = 200;
        d.width = 400;
        
        JScrollPane sp = new JScrollPane(queueLists.get(colunas));
        sp.setPreferredSize(d);
        add(sp);
        queueLists.get(colunas).setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	public void atualizaFilasDeServicos() {
		//try {
			int i = 0;
			//this.sFilasClientes.acquire();
			for(ArrayList<Cliente> array : filas.getFilasClientes()) {
				atualizaFilaDeServicos(array, i);
				i += 1;
			}
		//} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//} finally {
			///this.sFilasClientes.release();
		//}
	}
	
	private void atualizaFilaDeServicos(ArrayList<Cliente> fila, int i) {
		listModels.get(i).clear();
		for(Cliente c: fila) {	
        	listModels.get(i).addElement(c.toString());
		}
	}
	
	public void atualizaFilasCaixas() {
		//try {
			//this.sFilasCaixas.acquire();
			atualizaFilaCaixa(filas.getFilaCaixa(0));
		//} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//} finally {
			this.sFilasCaixas.release();
		//}
	}
	
	private void atualizaFilaCaixa(ArrayList<Cliente> fila) {
		int i = filas.getNumFilasClientes();
		listModels.get(i).clear();
		for(Cliente c: fila) {	
        	listModels.get(i).addElement("Cliente" + c.getId());
		}
	}
	
	public void atualizaFuncionarios() {
		int i = listModels.size()-1;
		listModels.get(i).clear();
		for(Funcionario f : funcionarios) {
			if(f.getCliente() != null) {
				listModels.get(i).addElement(f.toString() +": Atendendo cliente" + f.getCliente().getId());
			}
			
		}
	}

}
