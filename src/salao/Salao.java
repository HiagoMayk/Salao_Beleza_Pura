package salao;

import java.util.ArrayList;
import java.util.Random;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Salao extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private int idCliente;  // Identificador do cliente e tam serve para contar
					// quantos clientes foram atendidos até um determina momento
	
	private Random gerador; 	// Gera o que for de ser eleatório do sistema

	private FilasClientes filas;
	ArrayList<Cliente> filaCaixas;
	
	private Cabeleireira cabeleireira;
	private Manicure manicure;
	private Depiladora depiladora;
	private Massagista massagista;
	private Caixa caixa;
	
	private Thread tCabeleireira[];
	private Thread tManicure[];
	private Thread tDepiladora[];
	private Thread tMassagista;
	private Thread tCaixa[];
	
	private ThreadGroup gCabeleireiras;
	private ThreadGroup gManicures;
	private ThreadGroup gDepiladoras;
	private ThreadGroup gMassagistas;
	private ThreadGroup gCaixas;
	
	private List<JList<String>> queueLists = new ArrayList<JList<String>>();
	
	private List<DefaultListModel<String>> listModels = new ArrayList<DefaultListModel<String>>();
	
	private final int numFilas = 5;
	
	private final int numFilasCaixas = 1;
	
	private JButton logButton = new JButton("Gerar relatório");
	
	public FilasClientes getFilas() {
		return filas;
	}

	public void setFilas(FilasClientes filas) {
		this.filas = filas;
	}

	public Salao()
	{
		idCliente = 0;
		
		filas = new FilasClientes();
		filaCaixas = new ArrayList<Cliente>();
		
		gerador = new Random();
		cabeleireira = new Cabeleireira(null, null);
		manicure = new Manicure(null, null);
		depiladora = new Depiladora(null, null);
		massagista = new Massagista(null, null);
		caixa = new Caixa(null, null);
		
		tCabeleireira = new Thread[5];
		tManicure = new Thread[3];
		tDepiladora = new Thread[2];
		tMassagista = new Thread();
		tCaixa = new Thread[2];
		
		gCabeleireiras = new ThreadGroup ("cabeleireiras");
		gManicures = new ThreadGroup ("Manicures");
		gDepiladoras = new ThreadGroup ("Depiladoras");
		gMassagistas = new ThreadGroup ("Massagistas");
		gCaixas = new ThreadGroup ("Caixas");
		
		for(int i = 0; i < 5; i++)
		{
			tCabeleireira[i] = new Thread(gCabeleireiras, cabeleireira, "cabeleireira" + (i+1));
		}
		
		for(int i = 0; i < 3; i++)
		{
			tManicure[i] = new Thread(gManicures, manicure, "Manicure" + (i+1));
		}
		
		for(int i = 0; i < 2; i++)
		{
			tDepiladora[i] = new Thread(gDepiladoras, depiladora, "Depiladora" + (i+1));
		}

		for(int i = 0; i < 2; i++)
		{
			tCaixa[i] = new Thread(gCaixas, caixa, "Caixa" + (i+1));
		}
		
		tMassagista = new Thread(gMassagistas, massagista, "Massagista1");
		
		// Screen
		
		// Cria filas de servicos
		int colunas = 0;
    	for(int i = 0; i < numFilas; i++, colunas++) {
        	// create the model and add elements
        	DefaultListModel<String> listModel = new DefaultListModel<>();
            listModel.addElement("--Fila " + (i+1) + "--");
            listModels.add(listModel);

            // create the list
            queueLists.add(new JList<String>(listModels.get(colunas)));
            add(queueLists.get(colunas));
            JScrollPane sp = new JScrollPane(queueLists.get(colunas));
            add(sp);
            Dimension d = queueLists.get(colunas).getPreferredSize();
            d.width = 200;
            queueLists.get(colunas).setPreferredSize(d);
            //add(new JScrollPane(queueLists.get(colunas)));
            queueLists.get(colunas).setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            queueLists.get(colunas).setVisibleRowCount(10);
    	}
    	
    	// Cria filas de caixas
    	for(int i = 0; i < numFilasCaixas; i++, colunas++) {
        	// create the model and add elements
        	DefaultListModel<String> listModel = new DefaultListModel<>();
            listModel.addElement("--Fila" + (i+1) + " Caixa --");
            listModels.add(listModel);

            // create the list
            queueLists.add(new JList<String>(listModels.get(colunas)));
            add(queueLists.get(colunas));
            JScrollPane sp = new JScrollPane(queueLists.get(colunas));
            add(sp);
            Dimension d = queueLists.get(colunas).getPreferredSize();
            d.width = 200;
            queueLists.get(colunas).setPreferredSize(d);
            //add(new JScrollPane(queueLists.get(colunas)));
            queueLists.get(colunas).setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            queueLists.get(colunas).setVisibleRowCount(10);
    	}
    	
    	// Cria coluna para funcionarios ativos
    	// create the model and add elements
    	DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("--Funcionarios ativos--");
        listModels.add(listModel);

        // create the list
        queueLists.add(new JList<String>(listModels.get(colunas)));
        add(queueLists.get(colunas));
        JScrollPane sp = new JScrollPane(queueLists.get(colunas));
        add(sp);
        Dimension d = queueLists.get(colunas).getPreferredSize();
        d.width = 400;
        queueLists.get(colunas).setPreferredSize(d);
        //add(new JScrollPane(queueLists.get(colunas)));
        queueLists.get(colunas).setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        queueLists.get(colunas).setVisibleRowCount(5);
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
         
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Salão Beleza Pura");       
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        logButton.addActionListener(this);
        add(logButton);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		logButton.setText("Botão foi clicado!");
	}
	
	public void atualizaFilaServico(ArrayList<Cliente> fila, int i) {
		listModels.get(i).clear();
		listModels.get(i).addElement("--Fila" + (i+1) + "--");
		listModels.get(i).addElement(" ");

        for(Cliente c: fila) {	
        	listModels.get(i).addElement("cliente" + c.getIdCliente() + " - " + c.verServico() + " - " + c.getQtdServicos());
		}
	}
	
	public void atualizaFilaCaixa(ArrayList<Cliente> fila, int i) {
		listModels.get(i).clear();
		listModels.get(i).addElement("--Fila Caixa--");
		listModels.get(i).addElement(" ");

        for(Cliente c: fila) {	
        	listModels.get(i).addElement("cliente" + c.getIdCliente());
		}
	}
	
	public void atualizaColunaFunc(String s, int i) {
		listModels.get(i).clear();
		listModels.get(i).addElement("--Funcionarios ativos--");
		listModels.get(i).addElement(" ");
		listModels.get(i).addElement(s);
	}
	
	public ArrayList<Integer> geraTempoServicos(int quantidade)
	{
		int tempos[] = new int[quantidade];
		ArrayList<Integer> t = new ArrayList<Integer>();
		
		for(int i = 0; i < quantidade; i++)
		{
			tempos[i] = (gerador.nextInt(10)+1);
		}
	
		for (int i = tempos.length; i >= 1; i--) 
		{  
		    for (int j = 1; j < i; j++) 
		    {  
		        if (tempos[j-1] >tempos[j]) 
		        {  
		            int aux = tempos[j];  
		            tempos[j] = tempos[j-1];  
		            tempos[j-1] = aux;  
		        }  
		    }  
		}
		
		for(int i = tempos.length-1; i >= 0; i--)
		{
			t.add(tempos[i]);
		} 
		
		return t;
	}
	
	public void executar() throws InterruptedException
	{
		Cliente cliente;
		ArrayList<Integer> t = new ArrayList<Integer>();
		while(true)
		{
			int colunas = 0;
			cliente = criaCliente();
			t = geraTempoServicos(cliente.getQtdServicos());
			
			for(Servico c: cliente.getServicos())
			{
				if(c.getServico() == "Penteado")
				{
					c.setTempo(t.get(0));
					t.remove(0);
				}
				else if(c.getServico() == "Corte")
				{
					c.setTempo(t.get(0));
					t.remove(0);
				}
				else if(c.getServico() == "Depilação")
				{	
					c.setTempo(t.get(0));
					t.remove(0);
				}
				else if(c.getServico() == "Pedicure")
				{	
					c.setTempo(t.get(0));
					t.remove(0);
				}
				else if(c.getServico() == "Massagem")
				{	
					c.setTempo(t.get(0));
					t.remove(0);
				}
			}
			
			// Insere na fila 1, que é a fila dos clientes que acabaram de chegar no salão
			filas.setFilaCliente(1, cliente);
			
			System.out.println();
			System.out.println("===========================================");
			
			for(int fila = 5; fila >= 1; fila--, colunas++)
			{
				System.out.println(filas.getFila(fila).size() + " Clientes na fila " + fila);
				
				atualizaFilaServico(filas.getFila(fila), fila-1);
				
				for(Cliente c: filas.getFila(fila))
				{	
					System.out.println("cliente" + c.getIdCliente() + " - " + c.verServico() + " - " + c.getQtdServicos());
				}
				System.out.println("--");
			}
			
			colunas++;
			atualizaFilaCaixa(filaCaixas, colunas-1);
			System.out.println(filaCaixas.size() + " Clientes na fila do Caixa");
			for(Cliente c: filaCaixas)
			{	
				System.out.println("cliente" + c.getIdCliente());
			}
			System.out.println("--");		
			
			System.out.println("-------------------------------------------");
			
			String s = atendeCliente();
			
			System.out.println(s);
			
			atualizaColunaFunc(s, colunas);
			
			try
			{
				Thread.sleep(2000*(gerador.nextInt(5)+1));
			}
			catch(InterruptedException ex) 
			{
				 Thread.currentThread().interrupt();
			}
		}
	}
	
   /* 
	* Cada fila tem uma prioridade diferente: 
	* por exemplos: filaClientes1 é de um cliente de acabou de chegar
	* filaClientes2 é de um cliente que já foi atendido 1 vez e ainda tem pedido
	* filaClientes3 é de um cliente que já foi atendido 2 vezes e ainda tem pedido
	* filaClientes4 é de um cliente que já foi atendido 3 vezes e ainda tem pedido
	* filaClientes5 é de um cliente que já foi atendido 4 vezes e ainda tem pedido
	* */

	public String atendeCliente()
	{
		// Caixas
		if(!(filaCaixas.isEmpty()))
		{
			for(Cliente c: filaCaixas)
			{
				for(int i = 0; i < 2; i++)
				{
					if(!(tCaixa[i].isAlive()))
					{
						filaCaixas.remove(c);
	
						caixa = new Caixa(null, c);
						tCaixa[i] = new Thread(gCaixas, caixa, "Caixa" + (i+1));
						tCaixa[i].start();
						//return true;
						return tCaixa[i].getName() + ": Atendendo cliente " + caixa.getCliente().getIdCliente(); 
					}	
				}
			}
		}
				
		// A prioridade das filas segue da seguinte forma do maior para o menor
		// Mais alta: 5, 4, 3, 2, 1 :Mais baixa 
		for(int fila = 5; fila >= 1; fila--) 
		{
			if(!(filas.getFila(fila).isEmpty()))
			{
				for(Cliente c: filas.getFila(fila))
				{
					if((c.verServico().contains("Penteado") || c.verServico().contains("Corte") ||
						c.verServico().contains("Lavagem")) && gCabeleireiras.activeCount() < 5)
					{
						for(int i = 0; i < 5; i++)
						{
							if(!(tCabeleireira[i].isAlive()))
							{
								c.getServico();
								filas.removeClienteIndex(fila, filas.getFila(fila).indexOf(c));
								
								if(fila == 5 || c.getQtdServicos() == 0)
								{
									cabeleireira = new Cabeleireira(filaCaixas, c);
								}
								else
								{
									cabeleireira = new Cabeleireira(filas.getFila(fila+1), c);
								}
								
								tCabeleireira[i] = new Thread(gCabeleireiras, cabeleireira, "Cabeleireira" + (i+1));
								tCabeleireira[i].start();
						
								//return true;
								return tCabeleireira[i].getName() + ": Atendendo cliente " + cabeleireira.getCliente().getIdCliente();
							}
						}
					}
					
					if(c.verServico().contains("Pedicure") && gManicures.activeCount() < 3)
					{
						for(int i = 0; i < 3; i++)
						{
							if(!(tManicure[i].isAlive()))
							{
								c.getServico();
								filas.removeClienteIndex(fila, filas.getFila(fila).indexOf(c));
								
								if(fila == 5 || c.getQtdServicos() == 0)
								{
									manicure = new Manicure(filaCaixas, c);
								}
								else
								{
									manicure = new Manicure(filas.getFila(fila+1), c);
								}
								
								tManicure[i] = new Thread(gManicures, manicure, "Manicure" + (i+1));
								tManicure[i].start();
								
								//return true;
								return tManicure[i].getName() + ": Atendendo cliente " + manicure.getCliente().getIdCliente();
							}
						}
					}
					
					if(c.verServico().contains("Depilação") && gDepiladoras.activeCount() < 2)
					{
						for(int i = 0; i < 2; i++)
						{
							if(!(tDepiladora[i].isAlive()))
							{
								c.getServico();
								filas.removeClienteIndex(fila, filas.getFila(fila).indexOf(c));
								
								if(fila == 5 || c.getQtdServicos() == 0)
								{
									depiladora = new Depiladora(filaCaixas, c);
								}
								else
								{
									depiladora = new Depiladora(filas.getFila(fila+1), c);
								}
								
								tDepiladora[i] = new Thread(gDepiladoras, depiladora, "Depiladora" + (i+1));
								tDepiladora[i].start();
								
								//return true;
								return tDepiladora[i].getName() + ": Atendendo cliente " + depiladora.getCliente().getIdCliente();
							}
						}
					}
					
					if(c.verServico().contains("Massagem") && gMassagistas.activeCount() < 1)
					{
							if(!(tMassagista.isAlive()))
							{
								c.getServico();
								filas.removeClienteIndex(fila, filas.getFila(fila).indexOf(c));

								if(fila == 5 || c.getQtdServicos() == 0)
								{
									massagista = new Massagista(filaCaixas, c);
								}
								else
								{
									massagista = new Massagista(filas.getFila(fila+1), c);
								}
								
								tMassagista = new Thread(gMassagistas, massagista, "Massagista1");
								tMassagista.start();
								
								//return true;
								return tMassagista.getName() + ": Atendendo cliente " + massagista.getCliente().getIdCliente();
							}
					}
				}
				
			}
		}
		
		return "";
	}
	
	//Método que cria uma instancia de cliente, gera os serviços que o cliente quer e retorna essa instancia para
	// o método executar()
	public Cliente criaCliente()
	{
		idCliente++; 												//Incrementa o identificador do cliente
		boolean flag = false; 
		Servico servico;
		Cliente cliente = new Cliente(idCliente);
		ArrayList<Integer> inserido = new ArrayList<Integer>(); 	// evita a repetição de tipos de serviços
		//int quantServicos = gerador.nextInt(6)+1;
		int quantServicos = 0;
		int porcentagemQtd = gerador.nextInt(100)+1;
		
		if(porcentagemQtd >= 1 && porcentagemQtd <= 30)					// 30% dos clientes desejam todos os serviços
		{
			quantServicos = 5;
		}
		else if(porcentagemQtd >= 31 && porcentagemQtd <= 65) 			//35% desejam 4
		{
			quantServicos = 4;
		}
		else if(porcentagemQtd >= 66 && porcentagemQtd <= 85)  			// 20% desejam 3
		{
			quantServicos = 3;
		}
		else if(porcentagemQtd >= 86 && porcentagemQtd <= 95) 			 // 10% apenas 2
		{
			quantServicos = 2;
		}
		else if(porcentagemQtd >= 96 && porcentagemQtd <= 100)  			// 5% apenas 1
		{
			quantServicos = 1;
		}
		
		// Faz a inserção da escolha dos clientes inserindo por ordem de escolha
		// Não deixa escolher mais de uma vez um mesmo serviço
		for(int i = 0; i < quantServicos; i++)
		{
			// Vamos assumir que o maximo aqui é 155%
			int tipoServico = 0;
			int porcentagemTipo = gerador.nextInt(100)+1;
			
			if(porcentagemTipo >= 1 && porcentagemTipo <= 50)			// 50% para corte
			{
				tipoServico = 1;
			}
			else if(porcentagemTipo >= 51 && porcentagemTipo <= 90) 	// 40% para penteado
			{
				tipoServico = 2;
			}
			else if(porcentagemTipo >= 91 && porcentagemTipo <= 120)  	// 30% para pedicure
			{
				tipoServico = 3;
			}
			else if(porcentagemTipo >= 121 && porcentagemTipo <= 140) 	// 20% para depilação
			{
				tipoServico = 4;
			}
			else if(porcentagemTipo >= 141 && porcentagemTipo <= 155)  	// 15% para massagem
			{
				tipoServico = 5;
			}
			
			if(inserido.isEmpty() == false)
			{
				// Enquanto for serviço repetido, gera outro
				while(flag == false)
				{
					for(int num : inserido)
					{
						if(num == tipoServico)
						{	
							//tipoServico = gerador.nextInt(5)+1;
							if(tipoServico < 5)
							{
								tipoServico++;
							}
							else
							{
								tipoServico = 1;
							}
							
							flag = true;
							break;
						}
					}
					
					if(flag == true)
					{
						flag = false;
					}
					else
					{
						flag = true;
					}
				}
			}
				
			flag = false;
			inserido.add(tipoServico);
			switch(tipoServico)
			{
				case 1:
					servico = new Servico("Corte");
					cliente.setServico(servico);
					break;
				case 2:
					servico = new Servico("Penteado");
					cliente.setServico(servico);
					break;
				case 3:
					servico = new Servico("Pedicure");
					cliente.setServico(servico);
					break;
				case 4:
					servico = new Servico("Depilação");
					cliente.setServico(servico);
					break;
				case 5:
					servico = new Servico("Massagem");
					cliente.setServico(servico);
					break;
				default:
					System.out.println("Opção não existe!!!");
			}
		}
		
		return cliente;
	}
	
	public static void main (String args[]) throws InterruptedException{
        
		Salao salao = new Salao();
		
		salao.executar();            	
	}
}

