
public class Caixa extends Funcionario
{
	public void run()
	{
		for(int i = 0; i < 100; i++)
		{
			System.out.println("Cobrando");
			
			try
			{
				wait();
			}catch(Exception e){}
		}
	}
}
