package salao.simulador;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.Semaphore;

import salao.funcionarios.*;
import salao.gui.MainScreen;

public class Salao {
	
	private List<Funcionario> funcionarios;
	
	private FilasClientes filas;
	
	private List<Thread> threadsFuncionarios;
	
	private GeradorClientes geradorClientes;
	
	private Thread tGeradorClientes;
	
	private Semaphore sFilasClientes;
	
	private Semaphore sFilasCaixas;
	
	private MainScreen ms;
	
	private final int numCabeleireiras = 5;
	private final int numManicures = 3;
	private final int numDepiladoras = 2;
	private final int numMassagistas = 1;
	private final int numCaixas = 2;
	
	private final int maxThreadsPermitidas = 1;
	
	public Salao() {
		// TODO Auto-generated constructor stub
		funcionarios = new ArrayList<Funcionario>();
		filas = FilasClientes.getInstance();
		threadsFuncionarios = new ArrayList<Thread>();
		geradorClientes = new GeradorClientes(filas);
		tGeradorClientes = new Thread(geradorClientes, "GeradorClientes");
		sFilasClientes = new Semaphore(maxThreadsPermitidas);
		sFilasCaixas = new Semaphore(maxThreadsPermitidas);
		
		//ms = new MainScreen(filas, funcionarios, sFilasClientes, sFilasCaixas);
		
		for(int i = 0; i < numCabeleireiras; i++) {
			Cabeleireira c = new Cabeleireira(filas, sFilasClientes, sFilasCaixas, i+1);
			funcionarios.add(c);
			threadsFuncionarios.add(new Thread(c, "Cabeleireira" + (i+1)));
		}
		
		for(int i = 0; i < numManicures; i++) {
			Manicure m = new Manicure(filas, sFilasClientes, sFilasCaixas, i+1);
			funcionarios.add(m);
			threadsFuncionarios.add(new Thread(m, "Manicure" + (i+1)));
		}
		
		for(int i = 0; i < numDepiladoras; i++) {
			Depiladora d = new Depiladora(filas, sFilasClientes, sFilasCaixas, i+1);
			funcionarios.add(d);
			threadsFuncionarios.add(new Thread(d, "Depiladora" + (i+1)));
		}
		
		for(int i = 0; i < numMassagistas; i++) {
			Massagista m = new Massagista(filas, sFilasClientes, sFilasCaixas, i+1);
			funcionarios.add(m);
			threadsFuncionarios.add(new Thread(m, "Massagista" + (i+1)));
		}
		
		List<Caixa> caixas = new ArrayList<Caixa>();
		for(int i = 0; i < numCaixas; i++) {
			 Caixa c = new Caixa(filas, sFilasClientes, sFilasCaixas, i+1);
			funcionarios.add(c);
			caixas.add(c);
			threadsFuncionarios.add(new Thread(c, "Caixa" + (i+1)));
		}
		
		tGeradorClientes.start();
		//ms.run();
		ms = new MainScreen(filas, funcionarios, sFilasClientes, sFilasCaixas, caixas, geradorClientes);
		ms.setVisible(true);
		
		try {	
			Thread.sleep(2000);
		} catch(InterruptedException ex) {
			 Thread.currentThread().interrupt();
		}
		
		for(Thread t : threadsFuncionarios) {
			t.start();
		}
	}

	public Semaphore getsFilasClientes() {
		return sFilasClientes;
	}

	public void setsFilasClientes(Semaphore sFilasClientes) {
		this.sFilasClientes = sFilasClientes;
	}

	public Semaphore getsFilasCaixas() {
		return sFilasCaixas;
	}

	public void setsFilasCaixas(Semaphore sFilasCaixas) {
		this.sFilasCaixas = sFilasCaixas;
	}

}
