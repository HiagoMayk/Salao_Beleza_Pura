
public class Funcionario implements Runnable
{
	private String nome;
	
	public void run()
	{
		for(int i = 0; i < 100; i++)
		{
			System.out.println("Trabalando");
			
			try
			{
				wait();
			}catch(Exception e){}
		}
	}
	
}
