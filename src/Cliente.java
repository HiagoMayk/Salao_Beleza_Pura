import java.util.ArrayList;

public class Cliente 
{
	private int idCliente;
	private ArrayList<String> servicos;
	
	public Cliente(int idCliente)
	{
		this.idCliente = idCliente;
		servicos = new ArrayList<String>();
	}
	
	public void setServico(String servico)
	{
		this.servicos.add(servico);
	}
	
	// Retorna a string só para visualização do primeiro pedido
	public String verServico()
	{
		if(servicos.isEmpty()==true)
		{
			return "";
		}
		return servicos.get(0);
	}
	
	// Retorna a string e exclui o pedido
	public String getServico()
	{
		if(servicos.isEmpty()==true)
		{
			return "";
		}
		String aux = servicos.get(0);
		servicos.remove(0);
		return aux;
	}
	
	public int getQtdServicos()
	{
		return servicos.size();
	}
	
	public int getIdCliente()
	{
		return idCliente;
	}
}
