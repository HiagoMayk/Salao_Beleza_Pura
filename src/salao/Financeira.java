package salao;

public class Financeira 
{
	private Faturamento cabeleireiras[];
	private Faturamento manicures[];
	private Faturamento depiladoras[];
	private Faturamento massagista;
	
	private int totalAtendimento;
	private float totalDinheiroSalao;
	
	public Financeira()
	{
		cabeleireiras = new Faturamento[5];
		manicures = new Faturamento[3];
		depiladoras = new Faturamento[2];
		massagista = new Faturamento();
		
		for(int i = 0; i < 5; i++)
		{
			cabeleireiras[i] = new Faturamento();
		}
		
		for(int i = 0; i < 3; i++)
		{
			manicures[i] = new Faturamento();
		}
		
		for(int i = 0; i < 2; i++)
		{
			depiladoras[i] = new Faturamento();
		}
		
		massagista = new Faturamento();
		
		totalAtendimento = 0;
		totalDinheiroSalao = 0;
	}
	
	public Faturamento getFatCabelereira(int index)
	{
		return cabeleireiras[index];
	}
	
	public Faturamento getFatManicure(int index)
	{
		return manicures[index];
	}
	
	public Faturamento getFatDepiladora(int index)
	{
		return depiladoras[index];
	}
	
	public Faturamento getFatMassagista()
	{
		return massagista;
	}
	
	public int getTotalAtendimento()
	{
		return totalAtendimento;
	}
	
	public float getTotalDinheiroSalao()
	{
		return totalDinheiroSalao;
	}
	
	public void incrementaAtendimento()
	{
		totalAtendimento++;
	}
	
	public void incrementaDinheiroSalao(float valor)
	{
		totalDinheiroSalao += valor;
	}
}
