package salao;
import java.util.ArrayList;


public class Massagista extends Funcionario
{
	int tempo;
	public Massagista(ArrayList<Cliente> array, Cliente c, int tempo)
	{
		super(array, c);
		this.tempo = tempo;
	}
	
	public void run()
	{
		trabalhar();
		Thread.currentThread().interrupt();
	}
	
	public void trabalhar()
	{
		//System.out.println(Thread.currentThread().getName() + ": Atendendo cliente" + getCliente().getIdCliente());

		try 
		{
			Thread.sleep(10000);
		}
		catch(InterruptedException ex) 
		{
			 Thread.currentThread().interrupt();
		}
		
		//getCliente().getServico(); // gasta serviço
		insere();
	}
}
