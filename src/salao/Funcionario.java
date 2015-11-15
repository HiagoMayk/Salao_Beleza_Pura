package salao;
import java.util.ArrayList;

public abstract class Funcionario implements Runnable
{
	private Cliente cliente; 			// Representa o cliente que está sendo atendido
	private ArrayList<Cliente> array;   // Representa a próxima fila que o cliente será inserido
	
	public Funcionario(ArrayList<Cliente> array, Cliente c)
	{
		this.cliente = new Cliente(0);
		this.array = new ArrayList<>();
		this.cliente = c;
		this.array = array;
	}
	
	public Cliente getCliente()
	{
		return cliente;
	}

	public void insere()
	{
		array.add(cliente);
	}
	
        @Override
	public void run()
	{
		trabalhar();
		Thread.currentThread().interrupt();
	}

        public synchronized void trabalhar()
	{
		//System.out.println(Thread.currentThread().getName() + ": Atendendo cliente" + getCliente().getIdCliente());

		try 
		{
			Thread.sleep(10000);
		}
		catch(InterruptedException ex) 
		{
			 Thread.currentThread().interrupt();
		}
		
		//getCliente().getServico(); // gasta serviço
		insere();
	}
}
