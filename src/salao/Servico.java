package salao;

public class Servico 
{
	private String servico;
	private int tempo;
	
	public Servico(String servico)
	{
		this.servico =  servico;
		this.tempo = 0;
	}
	
	public String getServico()
	{
		return this.servico;
	}
	
	public void setTempo(int tempo)
	{
		this.tempo = tempo;
	}
	
	public int getTempo()
	{
		return this.tempo;
	}
}
