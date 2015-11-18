package salao;
import java.util.ArrayList;

public class Caixa extends Funcionario
{
	private FilasClientes filas;
	private ArrayList<Cliente> filaCaixas;
	private static Financeira financeira;
	ArrayList<Cliente> array;
	int proxFila;
	
	int tempo;
	public Caixa(FilasClientes filas, ArrayList<Cliente> filaCaixas)
	{
		this.filas = new FilasClientes(null);
		this.filas = filas;
		
		this.filaCaixas = new ArrayList<Cliente>();
		this.filaCaixas = filaCaixas;
		
		financeira = new Financeira();
		proxFila = 0;
	}
	
	public void run()
	{
			Cliente  c = new Cliente(0);
			while(true)
			{
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
	
	public synchronized Cliente pegaCliente()
	{
		if(!(filaCaixas.isEmpty()))
		{
			for(Cliente c: filaCaixas)
			{
				filaCaixas.remove(c);
				return c;
			}
		}
		
		return null;
	}
}
