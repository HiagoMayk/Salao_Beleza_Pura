import java.util.ArrayList;
import java.util.Random;

public class Salao 
{
	Random gerador;
	ArrayList<Cliente> filaClientes1;
	ArrayList<Cliente> filaClientes2;
	ArrayList<Cliente> filaClientes3;
	ArrayList<Cliente> filaClientes4;
	ArrayList<Cliente> filaClientes5;
	
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
		filaClientes1 = new ArrayList<Cliente>();
		filaClientes2 = new ArrayList<Cliente>();
		filaClientes3 = new ArrayList<Cliente>();
		filaClientes4 = new ArrayList<Cliente>();
		filaClientes5 = new ArrayList<Cliente>();
		
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
			filaClientes5.add(cliente); // mudar para filaClientes1
			
			System.out.println(filaClientes5.size() + " Clientes na fila");
			
			for(Cliente c: filaClientes5)
			{	
				System.out.println("cliente - " + c.verServico());
			}
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
	public boolean atendeCliente()
	{
		int index = 0;
		if(!(filaClientes5.isEmpty()))
		{
			for(Cliente c: filaClientes5)
			{
				if(c.verServico().contains("Penteado") && gCabelereiras.activeCount() < 5)
				{
					for(int i = 0; i < 5; i++)
					{
						if(!(tCabelereira[i].isAlive()))
						{
							tCabelereira[i].start();
							filaClientes5.remove(index);
							return true;
						}
					}
				}
				/*
				else if(c.getServico().contains("Corte"))
				{
					
				}
				else if(c.getServico().contains("Lavagem"))
				{
					
				}
				else if(c.getServico().contains("Pedicure"))
				{
					
				}
				else if(c.getServico().contains("Depilação"))
				{
					
				}
				else if(c.getServico().contains("Massagem"))
				{
					
				}
				*/
				index++;
			}
			
		}
		
		if(filaClientes4.isEmpty() != true)
		{
			
		}
		
		if(filaClientes3.isEmpty() != true)
		{
			
		}
		
		if(filaClientes2.isEmpty() != true)
		{
			
		}
		
		if(filaClientes1.isEmpty() != true)
		{
			
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

