
public class Massagista extends Funcionario
{
	public void run()
	{
		for(int i = 0; i < 100; i++)
		{
			System.out.println("Massageando");
			
			try
			{
				wait();
			}catch(Exception e){}
		}
	}
}
