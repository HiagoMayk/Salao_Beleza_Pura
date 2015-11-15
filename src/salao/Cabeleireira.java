package salao;
import java.util.ArrayList;


public class Cabeleireira extends Funcionario
{
	public Cabeleireira(ArrayList<Cliente> array, Cliente c)
	{
		super(array, c);
	}
	
	public void run()
	{
			// Todo corte deve ser precedido de uma lavagem: como diferenciar as tarefas?
			trabalhar();
			Thread.currentThread().interrupt();
	}
	
	public synchronized void trabalhar()
	{
		System.out.println(Thread.currentThread().getName() + ": Atendendo cliente" + getCliente().getIdCliente());
	
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
