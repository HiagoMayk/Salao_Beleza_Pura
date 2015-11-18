package salao;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;  

public class Simulador 
{	
	public static void main (String args[]) throws InterruptedException
	{
		Cabeleireira cabeleireira[];
		Manicure manicure[];
		Depiladora depiladora[];
		Massagista massagista;
		Caixa caixa[];
		
		Thread tCabeleireira[];
		Thread tManicure[];
		Thread tDepiladora[];
		Thread tMassagista;
		Thread tCaixa[];
		
		ThreadGroup gCabeleireiras;
		ThreadGroup gManicures;
		ThreadGroup gDepiladoras;
		ThreadGroup gMassagistas;
		ThreadGroup gCaixas;
		
		Semaphore semFilas = new Semaphore(1);
		Semaphore semCaixas = new Semaphore(1);
		
		ArrayList<Cliente> fila1 = new ArrayList<Cliente>();
		FilasClientes filas = new FilasClientes(fila1);
		
		ArrayList<Cliente> filaCaixas = new ArrayList<Cliente>();
		
		cabeleireira = new Cabeleireira[5];
		manicure = new Manicure[3];
		depiladora = new Depiladora[2];
		
		massagista = new Massagista(filas, filaCaixas, semFilas, semCaixas);
		
		caixa = new Caixa[2];
		
		tCabeleireira = new Thread[5];
		tManicure = new Thread[3];
		tDepiladora = new Thread[2];
		tMassagista = new Thread();
		tCaixa = new Thread[2];
		
		for(int i = 0; i < 5; i++)
		{
			cabeleireira[i] = new Cabeleireira(filas, filaCaixas, semFilas, semCaixas);
			tCabeleireira[i] = new Thread(cabeleireira[i], "cabeleireira" + (i+1));
		}
		
		for(int i = 0; i < 3; i++)
		{
			manicure[i] = new Manicure(filas, filaCaixas, semFilas, semCaixas);
			tManicure[i] = new Thread(manicure[i], "Manicure" + (i+1));
		}
	
		for(int i = 0; i < 2; i++)
		{
			depiladora[i] = new Depiladora(filas, filaCaixas, semFilas, semCaixas);
			tDepiladora[i] = new Thread(depiladora[i], "Depiladora" + (i+1));
		}

		for(int i = 0; i < 2; i++)
		{
			caixa[i] = new Caixa(filas, filaCaixas, semCaixas);
			tCaixa[i] = new Thread(caixa[i], "Caixa" + (i+1));
		}
		
		tMassagista = new Thread(massagista, "Massagista1");
		
		GeradorClientes g = new GeradorClientes(fila1, semFilas);
		Thread tg = new Thread(g, "GeradorClientes");
		tg.start();
		
		Salao salao = new Salao(filas, filaCaixas, semFilas, semCaixas);
		Thread s = new Thread(salao, "GeradorClientes");
		s.start();
		
		for(int i = 0; i < 5; i++)
		{
			tCabeleireira[i].start();
		}
		
		for(int i = 0; i < 3; i++)
		{
			tManicure[i].start();
		}
		
		for(int i = 0; i < 2; i++)
		{

			tDepiladora[i].start();
		}

		for(int i = 0; i < 2; i++)
		{
			tCaixa[i].start();
		}
		
		tMassagista.start();
		
	}
	
}
