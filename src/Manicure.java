
public class Manicure extends Funcionario
{
	public void run()
	{
		for(int i = 0; i < 100; i++)
		{
			System.out.println("Fazendo pé e mão");
			
			try
			{
				wait();
			}catch(Exception e){}
		}
	}
}
