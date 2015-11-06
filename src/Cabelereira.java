
public class Cabelereira extends Funcionario
{
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
	
	public void cortar()
	{
		System.out.println("Cortando Cabelo");
	}
	
	public void lavar()
	{
		System.out.println("Lavando Cabelo");
	}
	
}
