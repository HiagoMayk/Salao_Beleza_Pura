package salao.funcionarios;

import java.util.concurrent.Semaphore;

import salao.cliente.Cliente;
import salao.simulador.FilasClientes;

public class Depiladora extends Funcionario {

	public Depiladora(FilasClientes f, Semaphore semFilasClientes, Semaphore semFilasCaixas, int id) {
		super(f, semFilasClientes, semFilasCaixas, id);
	}
	
	public Depiladora(FilasClientes f, Cliente c) {
		super(f, c);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			Cliente c = null;

			try {
				this.sFilasClientes.acquire();
				c = this.filas.getProxParaDepilacao();
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
				this.incrementaTotalFaturado(cliente.proximoServico().getPreco());
				cliente.popServico();
				this.reposicionaCliente();
				cliente.setFuncionario(null);
				this.cliente = null;
				this.incrementaQtdServicos();
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
		return "Depiladora [" + getId() + "]";
	}

}
