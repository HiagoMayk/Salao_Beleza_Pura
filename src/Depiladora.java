
public class Depiladora extends Funcionario
{
	/*
	public Depiladora(String nome)
	{
		super(nome);
	}
	*/
	
	public void run()
	{
		for(int i = 0; i < 100; i++)
		{
			trabalhar();
			
			try
			{
				wait();
			}catch(Exception e){}
		}
	}
	
	public synchronized void trabalhar()
	{
		System.out.println(Thread.currentThread().getName() + ": Trabalhando");
	}

}
