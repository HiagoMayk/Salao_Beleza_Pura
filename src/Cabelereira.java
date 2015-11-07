
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
		for(int i = 0; i < 100; i++)
		{
			cortar();
			lavar();
			
			try
			{
				wait();
			}catch(Exception e){}
		}
	}
	
	public synchronized void cortar()
	{
		System.out.println("Cortando Cabelo");
	}
	
	public synchronized void lavar()
	{
		System.out.println("Lavando Cabelo");
	}
	
}
