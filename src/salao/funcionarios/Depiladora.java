package salao.funcionarios;

import salao.cliente.Cliente;
import salao.salao.FilasClientes;

public class Depiladora extends Funcionario {

	public Depiladora(FilasClientes f, Cliente c) {
		super(f, c);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Cliente c = null;
		while(true) {
			c = this.filas.getProxParaDepilacao();
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
