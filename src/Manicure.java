
public class Manicure extends Funcionario
{
	/*
	public Manicure(String nome)
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
		System.out.println("Fazendo pé e mão");
	}
}
