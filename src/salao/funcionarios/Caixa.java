package salao.funcionarios;

import java.util.concurrent.Semaphore;

import salao.cliente.Cliente;
import salao.simulador.FilasClientes;

public class Caixa extends Funcionario {

	public Caixa(FilasClientes f, Semaphore semFilasClientes, Semaphore semFilasCaixas, int id) {
		super(f, semFilasClientes, semFilasCaixas, id);
	}
	
	public Caixa(FilasClientes f, Cliente c) {
		super(f, c);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			Cliente c = null;
			
			try {
				this.sFilasClientes.acquire();
				c = this.filas.getProxParaCaixa();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				this.sFilasClientes.release();
			}
			
			if(c != null) {
				this.cliente = c;
				cliente.setFuncionario(this);
				System.out.println(Thread.currentThread().getName() + ": Atendendo cliente" + cliente.getId());				
				try {	
					Thread.sleep(2000); // nao foi especificado tempo
				}
				catch(InterruptedException ex) {
					 Thread.currentThread().interrupt();
				}
				// Volte sempre
				cliente.setFuncionario(null);
				this.cliente = null;
			}
			try {	
				Thread.sleep(2000);
			}
			catch(InterruptedException ex) {
				 Thread.currentThread().interrupt();
			}
		}
	}

	@Override
	public String toString() {
		return "Caixa [" + getId() + "]";
	}

}
