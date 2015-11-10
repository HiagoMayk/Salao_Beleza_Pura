import java.util.ArrayList;

public class Cliente 
{
	private ArrayList<String> servico;
	
	public Cliente()
	{
		servico = new ArrayList<String>();
	}
	
	public void setServico(String servico)
	{
		this.servico.add(servico);
	}
	
	// Retorna a string só para visualização do primeiro pedido
	public String verServico()
	{
		if(servico.isEmpty()==true)
		{
			return "";
		}
		return servico.get(0);
	}
	
	// Retorna a string e exclui o pedido
	public String getServico()
	{
		if(servico.isEmpty()==true)
		{
			return "";
		}
		String aux = servico.get(0);
		servico.remove(0);
		return aux;
	}
	
}
