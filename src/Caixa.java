
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
			cobrar();
			
			try
			{
				wait();
			}catch(Exception e){}
		}
	}
	
	public synchronized void cobrar()
	{
		System.out.println("Cobrando");
	}
	
}
