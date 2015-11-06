
public class Depiladora extends Funcionario
{
	public void run()
	{
		for(int i = 0; i < 100; i++)
		{
			System.out.println("Depilando");
			
			try
			{
				wait();
			}catch(Exception e){}
		}
	}

}
