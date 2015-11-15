package salao;
import java.util.ArrayList;


public class Cabeleireira extends Funcionario
{
	int tempo;
	public Cabeleireira(ArrayList<Cliente> array, Cliente c, int tempo)
	{
		super(array, c);
		this.tempo = tempo;
	}
	
	public void run()
	{
			// Todo corte deve ser precedido de uma lavagem: como diferenciar as tarefas?
			trabalhar();
			Thread.currentThread().interrupt();
	}
	
	public void trabalhar()
	{
		//System.out.println(Thread.currentThread().getName() + ": Atendendo cliente" + getCliente().getIdCliente());
	
		try 
		{
			Thread.sleep(1000 * tempo);
		}
		catch(InterruptedException ex) 
		{
			 Thread.currentThread().interrupt();
		}
		
		//getCliente().getServico(); // gasta serviço
		insere();
	}
	
}
