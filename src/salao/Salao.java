package salao;
import java.util.ArrayList;
import java.util.Random;

public class Salao 
{
	private int idCliente;  	// Identificador do cliente e tam serve para contar
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
	Thread tCaixa[];
	
	private ThreadGroup gCabeleireiras;
	private ThreadGroup gManicures;
	private ThreadGroup gDepiladoras;
	private ThreadGroup gMassagistas;
	private ThreadGroup gCaixas;
	
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
			
			for(int fila = 5; fila >= 1; fila--)
			{
				System.out.println(filas.getFila(fila).size() + " Clientes na fila " + fila);
				
				for(Cliente c: filas.getFila(fila))
				{	
					System.out.println("cliente" + c.getIdCliente() + " - " + c.verServico() + " - " + c.getQtdServicos());
				}
				System.out.println("--");
			}
			
			System.out.println(filaCaixas.size() + " Clientes na fila do Caixa");
			for(Cliente c: filaCaixas)
			{	
				System.out.println("cliente" + c.getIdCliente());
			}
			System.out.println("--");		
			
			System.out.println("-------------------------------------------");
			
			atendeCliente();
			
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
	public boolean atendeCliente()
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
						return true;
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
						
								return true;
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
								
								return true;
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
								
								return true;
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
								
								return true;
							}
					}
				}
				
			}
		}
		
		return false;
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

