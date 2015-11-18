package salao;
import java.util.ArrayList;

public class FilasClientes 
{
	ArrayList<Cliente> filaClientes1;
	ArrayList<Cliente> filaClientes2;
	ArrayList<Cliente> filaClientes3;
	ArrayList<Cliente> filaClientes4;
	ArrayList<Cliente> filaClientes5;
	
	public FilasClientes(ArrayList<Cliente> fila1)
	{
		filaClientes1 = new ArrayList<Cliente>();
		
		// Fila também usada na classe que cria os clientes
		filaClientes1 = fila1;
		filaClientes2 = new ArrayList<Cliente>();
		filaClientes3 = new ArrayList<Cliente>();
		filaClientes4 = new ArrayList<Cliente>();
		filaClientes5 = new ArrayList<Cliente>();
	}
	
	public void setFilaCliente(int fila, Cliente c)
	{
		switch(fila)
		{
			case 1:
				filaClientes1.add(c);
				break;
			case 2:
				filaClientes2.add(c);
				break;
			case 3:
				filaClientes3.add(c);
				break;
			case 4:
				filaClientes4.add(c);
				break;
			case 5:
				filaClientes5.add(c);
				break;
			default:
				System.out.println("Fila não existe!!!");
		}
	}
	
	public void removeCliente(int fila, Cliente c)
	{
		switch(fila)
		{
			case 1:
				filaClientes1.remove(c);
				break;
			case 2:
				filaClientes2.remove(c);
				break;
			case 3:
				filaClientes3.remove(c);
				break;
			case 4:
				filaClientes4.remove(c);
				break;
			case 5:
				filaClientes5.remove(c);
				break;
			default:
				System.out.println("Cliente não existe!!!");
		}
	}
	
	public void removeClienteIndex(int fila, int index)
	{
		switch(fila)
		{
			case 1:
				filaClientes1.remove(index);
				break;
			case 2:
				filaClientes2.remove(index);
				break;
			case 3:
				filaClientes3.remove(index);
				break;
			case 4:
				filaClientes4.remove(index);
				break;
			case 5:
				filaClientes5.remove(index);
				break;
			default:
				System.out.println("Cliente não existe!!!");
		}
	}
	
	public ArrayList<Cliente> getFila(int fila)
	{
		switch(fila)
		{
			case 1:
				return filaClientes1;
			case 2:
				return filaClientes2;
			case 3:
				return filaClientes3;
			case 4:
				return filaClientes4;
			case 5:
				return filaClientes5;
			default:
				System.out.println("Fila não existe!!!");
				return null;
		}
	}
}
