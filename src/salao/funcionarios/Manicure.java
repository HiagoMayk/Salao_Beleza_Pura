package salao.funcionarios;

import java.util.concurrent.Semaphore;

import salao.cliente.Cliente;
import salao.simulador.FilasClientes;

public class Manicure extends Funcionario {

	public Manicure(FilasClientes f, Semaphore semFilasClientes, Semaphore semFilasCaixas) {
		super(f, semFilasClientes, semFilasCaixas);
	}
	
	public Manicure(FilasClientes f, Cliente c) {
		super(f, c);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			Cliente c = null;
			
			try {
				this.sFilasClientes.acquire();
				c = this.filas.getProxParaPedicure();
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
					Thread.sleep(1000*cliente.proximoServico().getTempo());
				}
				catch(InterruptedException ex) {
					 Thread.currentThread().interrupt();
				}
				cliente.popServico();
				this.reposicionaCliente();
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

}
