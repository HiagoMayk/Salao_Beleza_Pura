import java.util.ArrayList;
import java.util.Random;

public class Salao 
{
	Random gerador;
	/*
	ArrayList<Cliente> filaClientes1;
	ArrayList<Cliente> filaClientes2;
	ArrayList<Cliente> filaClientes3;
	ArrayList<Cliente> filaClientes4;
	ArrayList<Cliente> filaClientes5;
	*/
	
	FilasClientes filas;
	
	Cabelereira cabelereira;
	Manicure manicure;
	Depiladora depiladora;
	Massagista massagista;
	Caixa caixa;
	
	Thread tCabelereira[];
	Thread tManicure[];
	Thread tDepiladora[];
	Thread tMassagista;
	Thread tCaixa[];
	
	ThreadGroup gCabelereiras;
	ThreadGroup gManicures;
	ThreadGroup gDepiladoras;
	ThreadGroup gMassagistas;
	ThreadGroup gCaixas;
	
	public Salao()
	{
		/*
		filaClientes1 = new ArrayList<Cliente>();
		filaClientes2 = new ArrayList<Cliente>();
		filaClientes3 = new ArrayList<Cliente>();
		filaClientes4 = new ArrayList<Cliente>();
		filaClientes5 = new ArrayList<Cliente>();
		*/
		
		filas = new FilasClientes();
		
		gerador = new Random();
		cabelereira = new Cabelereira();
		manicure = new Manicure();
		depiladora = new Depiladora();
		massagista = new Massagista();
		caixa = new Caixa();
		
		tCabelereira = new Thread[5];
		tManicure = new Thread[3];
		tDepiladora = new Thread[2];
		tMassagista = new Thread();
		tCaixa = new Thread[2];
		
		gCabelereiras = new ThreadGroup ("Cabelereiras");
		gManicures = new ThreadGroup ("Manicures");
		gDepiladoras = new ThreadGroup ("Depiladoras");
		gMassagistas = new ThreadGroup ("Massagistas");
		gCaixas = new ThreadGroup ("Caixas");
		
		for(int i = 0; i < 5; i++)
		{
			tCabelereira[i] = new Thread(gCabelereiras, cabelereira, "Cabelereira" + (i+1));
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
		/*
		for(int i = 0; i < 5; i++)
		{
			System.out.println(gCabelereiras.activeCount());
			tCabelereira[i].start();
		}
		
		for(int i = 0; i < 3; i++)
		{
			tManicure[i].start();
		}
		
		for(int i = 0; i < 2; i++)
		{
			tDepiladora[i].start();
		}

		for(int i = 0; i < 2; i++)
		{
			tCaixa[i].start();
		}

		tMassagista.start();
		
		for(int i = 0; i < 100; i++)
		{
			try{
				wait(1);
				
			}catch(Exception e){}
		}
		*/
		//System.out.println(gCabelereiras.activeCount() + "------------------");
		
		Cliente cliente = new Cliente();
		while(true)
		{
			cliente = criaCliente();
			filas.setFilaCliente(1, cliente); // mudar para filaClientes1
			
			System.out.println();
			System.out.println("===========================================");
			System.out.println(filas.getFila(1).size() + " Clientes na fila");
			
			for(Cliente c: filas.getFila(1))
			{	
				System.out.println("cliente - " + c.verServico());
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
			int index = 0;
			if(!(filas.getFila(fila).isEmpty()))
			{
				for(Cliente c: filas.getFila(1))
				{
					if((c.verServico().contains("Penteado") || c.verServico().contains("Corte") ||
						c.verServico().contains("Lavagem")) && gCabelereiras.activeCount() < 5)
					{
						for(int i = 0; i < 5; i++)
						{
							if(!(tCabelereira[i].isAlive()))
							{
								tCabelereira[i] = new Thread(gCabelereiras, cabelereira, "Cabelereira" + (i+1));
								tCabelereira[i].start();
	
								filas.removeClienteIndex(1, filas.getFila(fila).indexOf(c));
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
								tManicure[i] = new Thread(gManicures, manicure, "Manicure" + (i+1));
								tManicure[i].start();
	
								filas.removeClienteIndex(1, filas.getFila(fila).indexOf(c));
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
								tDepiladora[i] = new Thread(gDepiladoras, depiladora, "Depiladora" + (i+1));
								tDepiladora[i].start();
	
								filas.removeClienteIndex(1, filas.getFila(fila).indexOf(c));
								return true;
							}
						}
					}
					
					if(c.verServico().contains("Massagem") && gMassagistas.activeCount() < 1)
					{
							if(!(tMassagista.isAlive()))
							{
								tMassagista = new Thread(gMassagistas, massagista, "Massagista1");
								tMassagista.start();
	
								filas.removeClienteIndex(1, filas.getFila(fila).indexOf(c));
								return true;
							}
					}
					
					index++;
				}
			}
		}
		return false;
	}
	
	//Método que cria uma instancia de cliente, gera os serviços que o cliente quer e retorna essa instancia para
	// o método executar()
	public Cliente criaCliente()
	{
		boolean flag = false; 
		Cliente cliente = new Cliente();
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

