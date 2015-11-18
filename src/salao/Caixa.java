package salao;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Caixa extends Funcionario
{
	private FilasClientes filas;
	private ArrayList<Cliente> filaCaixas;
	private static Financeira financeira;
	ArrayList<Cliente> array;
	int proxFila;
	private Semaphore semCaixas;
	
	int tempo;
	public Caixa(FilasClientes filas, ArrayList<Cliente> filaCaixas, Semaphore semCaixas)
	{
		this.filas = new FilasClientes(null);
		this.filas = filas;
		
		this.filaCaixas = new ArrayList<Cliente>();
		this.filaCaixas = filaCaixas;
		
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
						Thread.sleep(2000);
					}
					catch(InterruptedException ex) 
					{
						 Thread.currentThread().interrupt();
					}
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
	
	public Cliente pegaCliente()
	{
		 try 
		 {
		     semCaixas.acquire();
		     if(!(filaCaixas.isEmpty()))
		     {
		     	for(Cliente c: filaCaixas)
		     	{
		     		filaCaixas.remove(c);
		     		semCaixas.release();
		     		return c;
		     	}
		     }
		 } 
		 catch (InterruptedException e) 
		 {
		     e.printStackTrace();
		 } 

		semCaixas.release();
		return null;
	}
}
