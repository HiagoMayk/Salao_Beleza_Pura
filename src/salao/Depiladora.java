package salao;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Depiladora extends Funcionario
{
	private FilasClientes filas;
	private ArrayList<Cliente> filaCaixas;
	private static Financeira financeira;
	ArrayList<Cliente> array;
	int proxFila;
	
	private Semaphore semFilas;
	private Semaphore semCaixas;

	int tempo;
	public Depiladora(FilasClientes filas, ArrayList<Cliente> filaCaixas,
						Semaphore semFilas, Semaphore semCaixas)
	{
		this.filas = new FilasClientes(null);
		this.filas = filas;
		
		this.filaCaixas = new ArrayList<Cliente>();
		this.filaCaixas = filaCaixas;
		
		this.semFilas = semFilas;
		this.semCaixas = semCaixas;
		
		financeira = new Financeira();
		proxFila = 0;
	}
	
	public void run()
	{
			Cliente  c = new Cliente(0);
			while(true)
			{
				// Acesso a RC
				c = pegaCliente();
				
				if(c != null)
				{
					System.out.println(Thread.currentThread().getName() + ": Atendendo cliente" + c.getIdCliente());
					
					try
					{
						Thread.sleep(1000 * c.getServicosSolicitados().get(0).getTempo());
					}
					catch(InterruptedException ex) 
					{
						 Thread.currentThread().interrupt();
					}
					
					// Acesso a RC
					insere(c);
				}
				
				// Espera um tempo pra tentar pegar cliente novamente
				try
				{	
					Thread.sleep(2000);
				}
				catch(InterruptedException ex)
				{
					 Thread.currentThread().interrupt();
				}
			}	
	}
	
	// Acesso a RC
	public void insere(Cliente c)
	{
		 try 
		 {     
			 c.getServico();
			 if(proxFila >= 1 && proxFila <= 5 && c.getQtdServicos() == 0)
			 {
		    	semCaixas.acquire();
		    	
				filaCaixas.add(c);
				
				semCaixas.release();
			}
			else if(proxFila >= 1 && proxFila <= 5 && c.getQtdServicos() != 0)
			{
				semFilas.acquire();
				
				filas.setFilaCliente(proxFila, c);
				
				semFilas.release();
			}
			else if(proxFila == 6)
			{
				semCaixas.acquire();
					
				filaCaixas.add(c);
					
				semCaixas.release();
			}
				
			proxFila = 0;
		 } 
		 catch (InterruptedException e) 
		 {
		     e.printStackTrace();
		 }
	}
	
	// Acesso a RC
	public Cliente pegaCliente()
	{
		 try 
		 {
		     semFilas.acquire();
		        
		     // A prioridade das filas segue da seguinte forma do maior para o menor
			 // Mais alta: 5, 4, 3, 2, 1 :Mais baixa 
			for(int fila = 5; fila >= 1; fila--)
			{
				if(!(filas.getFila(fila).isEmpty()))
				{
					for(Cliente c: filas.getFila(fila))
					{
						if(c.verServico().contains("Depilação"))
						{
							filas.removeClienteIndex(fila, filas.getFila(fila).indexOf(c));
							semFilas.release();
							
							if(fila < 5)
							{
								proxFila = fila+1;
							}
							else
							{
								proxFila = 6;
							}
							
							
							return c;
						}
					}
				}				
			}
		 } 
		 catch (InterruptedException e) 
		 {
		     e.printStackTrace();
		 } 
		 
		 semFilas.release();
		 
		
		 proxFila = 0;
		return null;
	}
}