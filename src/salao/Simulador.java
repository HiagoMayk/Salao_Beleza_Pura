package salao;

import java.util.ArrayList;
import java.util.Random;

public class Simulador 
{	
	public static void main (String args[]) throws InterruptedException
	{
		ArrayList<Cliente> fila1 = new ArrayList<Cliente>();
		
		GeradorClientes g = new GeradorClientes(fila1);
		Thread tg = new Thread(g, "GeradorClientes");
		tg.start();     
		
		Salao salao = new Salao(fila1);
		Thread s = new Thread(salao, "GeradorClientes");
		s.start();            	
	}
	
}
