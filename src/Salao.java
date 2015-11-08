import java.util.ArrayList;
import java.util.Random;

public class Salao 
{
	ArrayList<Cliente> clientes;
	
	public Salao()
	{
		clientes = new ArrayList<Cliente>();
	}
	
	public void executar() throws InterruptedException
	{
		/*
		Cabelereira cabelereira = new Cabelereira();
		Manicure manicure = new Manicure();
		Depiladora depiladora = new Depiladora();
		Massagista massagista = new Massagista();
		Caixa caixa = new Caixa();
			
		Thread tCabelereira[] = new Thread[5];
		Thread tManicure[] = new Thread[3];
		Thread tDepiladora[] = new Thread[2];
		Thread tMassagista = new Thread();
		Thread tCaixa[] = new Thread[2];
		
		for(int i = 0; i < 5; i++)
		{
			tCabelereira[i] = new Thread(cabelereira);
			tCabelereira[i].start();
		}
		
		for(int i = 0; i < 3; i++)
		{
			tManicure[i] = new Thread(manicure);
			tManicure[i].start();
		}
		
		for(int i = 0; i < 2; i++)
		{
			tDepiladora[i] = new Thread(depiladora);
			tDepiladora[i].start();
		}

		tMassagista = new Thread(massagista);
		tMassagista.start();
		
		for(int i = 0; i < 2; i++)
		{
			tCaixa[i] = new Thread(caixa);
			tCaixa[i].start();
		}
		
	
		/*
		Thread tm = new Thread(massagista);
		
		tm.start();
		
		for(int i = 0; i < 100; i++)
		{
			try{
				wait(1);
				
			}catch(Exception e){}
		}
		*/
		Cliente cliente = new Cliente();;
		while(true)
		{
			cliente = criaCliente();
			clientes.add(cliente);
			
			System.out.println(clientes.size() + " Clientes na fila");
			
			try 
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException ex) 
			{
				 Thread.currentThread().interrupt();
			}
		}
		

	}
	
	//Método que cria uma instancia de cliente, gera os serviços que o cliente quer e retorna essa instancia para
	// o método executar()
	public Cliente criaCliente()
	{
		boolean flag = false; 
		Cliente cliente = new Cliente();
		Random gerador = new Random();
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

