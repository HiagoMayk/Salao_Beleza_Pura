package salao;
import java.util.ArrayList;

public abstract class Funcionario implements Runnable
{
	// Representa o cliente que está sendo atendido
	private Cliente cliente;
	
	// Representa a próxima fila que o cliente será inserido
	private ArrayList<Cliente> array;
	
	public Funcionario(ArrayList<Cliente> array, Cliente c)
	{
		this.cliente = new Cliente(0);
		this.array = new ArrayList<>();
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
}
