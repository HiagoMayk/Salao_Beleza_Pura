package salao;
import java.util.ArrayList;

public class Caixa extends Funcionario
{
	public Caixa(ArrayList<Cliente> array, Cliente c)
	{
		super(array, c);
	}
	
	public void run()
	{
		trabalhar();
		Thread.currentThread().interrupt();
	}
	
	public synchronized void trabalhar()
	{
		System.out.println(Thread.currentThread().getName() + ": Atendendo cliente" + getCliente().getIdCliente());
		
		try 
		{
			Thread.sleep(5000);
		}
		catch(InterruptedException ex)
		{
			 Thread.currentThread().interrupt();
		}
	}
	
}
