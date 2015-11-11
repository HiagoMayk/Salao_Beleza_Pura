
public class Massagista extends Funcionario
{
	/*
	public Massagista(String nome)
	{
		super(nome);
	}
	*/
	
	public void run()
	{
		trabalhar();
		Thread.currentThread().interrupt();
	}
	
	public synchronized void trabalhar()
	{
		System.out.println(Thread.currentThread().getName() + ": Trabalhando");
		try 
		{
			Thread.sleep(10000);
		}
		catch(InterruptedException ex) 
		{
			 Thread.currentThread().interrupt();
		}
	}
}
