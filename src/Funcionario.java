import java.util.ArrayList;


public class Funcionario implements Runnable
{
	private Cliente cliente;
	private ArrayList<Cliente> array; // Não lembro porque uso esse array =\
	
	public Funcionario(ArrayList<Cliente> array, Cliente c)
	{
		this.cliente = new Cliente(0);
		this.array = new ArrayList<Cliente>();
		this.cliente = c;
		this.array = array;
	}
	
	public Cliente getCliente()
	{
		return cliente;
	}

	public void insere()
	{
		array.add(cliente);
	}
	
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
