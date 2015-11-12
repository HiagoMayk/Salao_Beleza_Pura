package salao;
import java.util.ArrayList;
import java.util.Random;

public class Salao 
{
	private int idCliente;  // Identificador do cliente e tam serve para contar
					// quantos clientes foram atendidos até um determina momento
	
	private Random gerador; // Gera o que for de ser eleatório do sistema

	private FilasClientes filas;
	
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
	
	public void executar() throws InterruptedException
	{
		Cliente cliente;
		while(true)
		{
			cliente = criaCliente();
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
	* Cada fila tem uma prioridades diferentes: 
	* por exemplos: filaClientes1 é de um cliente de acabou de chegar
	* filaClientes2 é de um cliente que já foi atendido 1 vez e ainda tem pedido
	* filaClientes3 é de um cliente que já foi atendido 2 vezes e ainda tem pedido
	* filaClientes4 é de um cliente que já foi atendido 3 vezes e ainda tem pedido
	* filaClientes5 é de um cliente que já foi atendido 4 vezes e ainda tem pedido
	* 
	* */
	
	// Até o momento só estamos atendendo 1 fila, ou seja, apenas 1 pedido por cliente
	public boolean atendeCliente()
	{
		// A prioridade das filas segue da seguinte forma do maior para o menor
		//  Mais alta: 5, 4, 3, 2, 1 :Mais baixa 
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
								
								if(fila != 5)
								{
									cabeleireira = new Cabeleireira(filas.getFila(fila+1), c);
								}
								else
								{
									cabeleireira = new Cabeleireira(null, c);
								}
								
								tCabeleireira[i] = new Thread(gCabeleireiras, cabeleireira, "cabeleireira" + (i+1));
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
								
								if(fila != 5)
								{
									manicure = new Manicure(filas.getFila(fila+1), c);
								}
								else
								{
									manicure = new Manicure(null, c);
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
								
								if(fila != 5)
								{
									depiladora = new Depiladora(filas.getFila(fila+1), c);
								}
								else
								{
									depiladora = new Depiladora(null, c);
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
								
								if(fila != 5)
								{
									massagista = new Massagista(filas.getFila(fila+1), c);
								}
								else
								{
									massagista = new Massagista(null, c);
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
		idCliente++; //Incrementa o identificador do cliente
		boolean flag = false; 
		Cliente cliente = new Cliente(idCliente);
		ArrayList<Integer> inserido = new ArrayList<Integer>();
		int quantServicos = gerador.nextInt(6)+1;
		
		// Faz a inserção da escolha dos clientes inserindo por ordem de escolha
		// Não deixa escolher mais de uma vez um mesmo serviço
		for(int i = 0; i < quantServicos; i++)
		{
			int tipoServico = gerador.nextInt(6)+1;
			
			if(inserido.isEmpty() == false)
			{
				while(flag == false)
				{
					for(int num : inserido)
					{
						if(num == tipoServico)
						{
							tipoServico = gerador.nextInt(6)+1;
							flag = true;
							break;
						}
					}
					
					if(flag == true)
					{
						flag = false;
					}else{
						flag = true;
					}
				}
			}
				
			flag = false;
			inserido.add(tipoServico);
			switch(tipoServico)
			{
				case 1:
					cliente.setServico("Penteado");
					break;
				case 2:
					cliente.setServico("Corte");
					break;
				case 3:
					cliente.setServico("Lavagem");
					break;
				case 4:
					cliente.setServico("Pedicure");
					break;
				case 5:
					cliente.setServico("Depilação");
					break;
				case 6:
					cliente.setServico("Massagem");
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

