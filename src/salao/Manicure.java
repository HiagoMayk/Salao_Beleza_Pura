package salao;
import java.util.ArrayList;


public class Manicure extends Funcionario
{
	private FilasClientes filas;
	private ArrayList<Cliente> filaCaixas;
	private static Financeira financeira;
	ArrayList<Cliente> array;
	int proxFila;
	
	int tempo;
	public Manicure(FilasClientes filas, ArrayList<Cliente> filaCaixas)
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
						Thread.sleep(1000 * c.getServicosSolicitados().get(0).getTempo());
					}
					catch(InterruptedException ex) 
					{
						 Thread.currentThread().interrupt();
					}
					
					insere(c);
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
	
	public synchronized void insere(Cliente c)
	{
		if(proxFila < 6)
		{
			c.getServico();
			if(c.verServico() == "")
			{
				filaCaixas.add(c);
			}
			else
			{
				filas.setFilaCliente(proxFila, c);
			}			
		}
		else
		{
			c.getServico();
			filaCaixas.add(c);
		}
		
		proxFila = 0;
	}
	
	public synchronized Cliente pegaCliente()
	{
		// A prioridade das filas segue da seguinte forma do maior para o menor
		// Mais alta: 5, 4, 3, 2, 1 :Mais baixa 
		for(int fila = 5; fila >= 1; fila--)
		{
			if(!(filas.getFila(fila).isEmpty()))
			{
				for(Cliente c: filas.getFila(fila))
				{
					if(c.verServico().contains("Pedicure"))
					{	
						filas.removeClienteIndex(fila, filas.getFila(fila).indexOf(c));
						
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
		
		return null;
	}
}
