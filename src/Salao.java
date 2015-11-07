import java.util.ArrayList;

public class Salao 
{
	ArrayList clientes;
	
	public Salao()
	{
		clientes = new ArrayList<String>();
	}
	
	public void executar()
	{
		/*
		Cabelereira cabelereira = new Cabelereira();
		Manicure manicure = new Manicure();
		Depiladora depiladora = new Depiladora();
		Massagista massagista = new Massagista();
		Caixa caixa = new Caixa();
			
		Thread tCabelereira[] = new Thread[5];
		Thread tManicure[] = new Thread[3];
		Thread tDepiladora[] = new Thread[2];
		Thread tMassagista = new Thread();
		Thread tCaixa[] = new Thread[2];
		
		for(int i = 0; i < 5; i++)
		{
			tCabelereira[i] = new Thread(cabelereira);
			tCabelereira[i].start();
		}
		
		for(int i = 0; i < 3; i++)
		{
			tManicure[i] = new Thread(manicure);
			tManicure[i].start();
		}
		
		for(int i = 0; i < 2; i++)
		{
			tDepiladora[i] = new Thread(depiladora);
			tDepiladora[i].start();
		}

		tMassagista = new Thread(massagista);
		tMassagista.start();
		
		for(int i = 0; i < 2; i++)
		{
			tCaixa[i] = new Thread(caixa);
			tCaixa[i].start();
		}
		
	
		/*
		Thread tm = new Thread(massagista);
		
		tm.start();
		
		for(int i = 0; i < 100; i++)
		{
			try{
				wait(1);
				
			}catch(Exception e){}
		}
		*/
		
		
	}
	
	public Cliente criaCliente()
	{
		//Método que cria uma instancia de cliente, gera os serviços que o cliente quer e retorna essa instancia para
		// o método executar()
	}
	
	public static void main (String args[]){
        
		Salao salao = new Salao();
		
		salao.executar();            
	}
}

