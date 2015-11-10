
public class Caixa extends Funcionario
{
	/*
	public Caixa(String nome)
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
