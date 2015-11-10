
public class Cabelereira extends Funcionario
{
	/*
	public Cabelereira(String nome)
	{
		super(nome);
	}
	*/
	
	public void run()
	{
			// Todo corte deve ser precedido de uma lavagem: como diferenciar as tarefas?
			
			trabalhar();
		    System.exit(0);
			// Thread.currentThread().interrupt();
	}
	
	public synchronized void trabalhar()
	{
		System.out.println(Thread.currentThread().getName() + ": Trabalhando");
		
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
