package salao;

public class Faturamento 
{
	private int qtdServicos = 0;
	private float totalDinheiro = 0; // já atribui com o desconto de 40%
	
	public Faturamento()
	{
		qtdServicos = 0;
		totalDinheiro = 0;
	}
	
	public int getQtdServicos()
	{
		return qtdServicos;
	}
	
	public float getTotalDinheiro()
	{
		return totalDinheiro;
	}
	
	public void incrementaQtdServicos()
	{
		this.qtdServicos++;
	}
	
	public void incrementaDinheiro(float valor)
	{
		this.totalDinheiro += valor;
	}
	
}
