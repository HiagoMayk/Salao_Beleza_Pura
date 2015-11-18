package salao.funcionarios;

import salao.cliente.Cliente;
import salao.simulador.FilasClientes;

public class Caixa extends Funcionario {

	public Caixa(FilasClientes f) {
		super(f);
	}
	
	public Caixa(FilasClientes f, Cliente c) {
		super(f, c);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Cliente c = null;
		while(true) {
			c = this.filas.getProxParaCaixa();
			if(c != null) {
				this.cliente = c;
				cliente.setFuncionario(this);
				System.out.println(Thread.currentThread().getName() + ": Atendendo cliente" + cliente.getId());
				try {	
					Thread.sleep(2000); // tempo fixo?
				}
				catch(InterruptedException ex) {
					 Thread.currentThread().interrupt();
				}
				// Volte sempre
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
