
public class Funcionario implements Runnable
{
	private String nome;
	
	/*
	public Funcionario(String nome)
	{
		this.nome = nome;
	}
	*/
	
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
	
	public void setNome(String nome)
	{
		this.nome = nome;	
	}
	
	public String getNome()
	{
		return nome;	
	}
}
