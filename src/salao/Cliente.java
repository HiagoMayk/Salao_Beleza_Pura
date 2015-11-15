package salao;
import java.util.ArrayList;

public class Cliente 
{
	private int idCliente;
	private ArrayList<Servico> servicos;
	
	public Cliente(int idCliente)
	{
		this.idCliente = idCliente;
		servicos = new ArrayList<Servico>();
	}
	
	public void setServico(Servico servico)
	{
		this.servicos.add(servico);
	}
	
	public ArrayList<Servico> getServicos()
	{
		return servicos;
	};
	
	// Retorna a string só para visualização do primeiro pedido
	public String verServico()
	{
		if(servicos.isEmpty()==true)
		{
			return "";
		}
		return servicos.get(0).getServico();
	}
	
	// Retorna a string e exclui o pedido
	public Servico getServico()
	{
		if(servicos.isEmpty()==true)
		{
			return null;
		}
		Servico aux = servicos.get(0);
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
