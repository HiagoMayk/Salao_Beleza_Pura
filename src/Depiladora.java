
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
			depilar();
			
			try
			{
				wait();
			}catch(Exception e){}
		}
	}
	
	public synchronized void depilar()
	{
		System.out.println("Depilando");
	}

}
