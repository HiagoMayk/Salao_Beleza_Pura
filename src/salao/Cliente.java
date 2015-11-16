package salao;
import java.util.ArrayList;

public class Cliente 
{
	private int idCliente;
	private ArrayList<Servico> servicosNA;
	private ArrayList<Servico> servicosSolicitados;
	
	public Cliente(int idCliente)
	{
		this.idCliente = idCliente;
		servicosNA = new ArrayList<Servico>();
		servicosSolicitados = new ArrayList<Servico>();
	}
	
	public void setServico(Servico servico)
	{
		this.servicosNA.add(servico);
		this.servicosSolicitados.add(servico);
	}
	
	public ArrayList<Servico> getServicosSolicitados()
	{
		return servicosSolicitados;
	};
	
	// Retorna a string só para visualização do primeiro pedido
	public String verServico()
	{
		if(servicosNA.isEmpty()==true)
		{
			return "";
		}
		return servicosNA.get(0).getServico();
	}
	
	// Retorna a string e exclui o pedido, ou seja, "gasta" um serviço
	public Servico getServico()
	{
		if(servicosNA.isEmpty()==true)
		{
			return null;
		}
		Servico aux = servicosNA.get(0);
		servicosNA.remove(0);
		return aux;
	}
	
	public int getQtdServicos()
	{
		return servicosNA.size();
	}
	
	public int getIdCliente()
	{
		return idCliente;
	}
}
