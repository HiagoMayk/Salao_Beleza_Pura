import java.util.ArrayList;


public class Manicure extends Funcionario
{
	public Manicure(ArrayList<Cliente> array, Cliente c)
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
		System.out.println(Thread.currentThread().getName() + ": Atendendo cliente " + getCliente().getIdCliente());
		try 
		{
			Thread.sleep(10000);
		}
		catch(InterruptedException ex) 
		{
			 Thread.currentThread().interrupt();
		}
		
		if(getCliente().verServico() != "")
		{
			insere();
		}
	}
}
