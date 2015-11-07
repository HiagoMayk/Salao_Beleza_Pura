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
