package salao.funcionarios;

import java.util.Random;

import salao.cliente.Cliente;
import salao.salao.FilasClientes;

public class Cabeleireira extends Funcionario {

	public Cabeleireira(FilasClientes f, Cliente c) {
		super(f, c);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Cliente c[] = new Cliente[2];
		c[0] = null;
		c[1] = null;
		while(true) {
			c[0] = this.filas.getProxParaCorte();
			c[1] = this.filas.getProxParaPenteado();
			if(c[0] != null && c[1] == null) {
				this.cliente = c[0];
				c[0].setFuncionario(this);
				System.out.println(Thread.currentThread().getName() + ": Atendendo cliente" + c[0].getId());
				try {	
					Thread.sleep(1000*c[0].proximoServico().getTempo());
				}
				catch(InterruptedException ex) {
					 Thread.currentThread().interrupt();
				}
				c[0].popServico();
				this.reposicionaCliente();
			} else if(c[0] == null && c[1] != null) {
				this.cliente = c[1];
				c[1].setFuncionario(this);
				System.out.println(Thread.currentThread().getName() + ": Atendendo cliente" + c[1].getId());
				try {	
					Thread.sleep(1000*c[1].proximoServico().getTempo());
				}
				catch(InterruptedException ex) {
					 Thread.currentThread().interrupt();
				}
				c[1].popServico();
				this.reposicionaCliente();
			} else if(c[0] != null && c[1] != null) {
				if(numeroDaFila(c[0]) > numeroDaFila(c[1])) {
					this.cliente = c[0];
					c[0].setFuncionario(this);
					System.out.println(Thread.currentThread().getName() + ": Atendendo cliente" + c[0].getId());
					try {	
						Thread.sleep(1000*c[0].proximoServico().getTempo());
					}
					catch(InterruptedException ex) {
						 Thread.currentThread().interrupt();
					}
					c[0].popServico();
					this.reposicionaCliente();
				} else if(numeroDaFila(c[0]) < numeroDaFila(c[1])) {
					this.cliente = c[1];
					c[1].setFuncionario(this);
					System.out.println(Thread.currentThread().getName() + ": Atendendo cliente" + c[1].getId());
					try {	
						Thread.sleep(1000*c[1].proximoServico().getTempo());
					}
					catch(InterruptedException ex) {
						 Thread.currentThread().interrupt();
					}
					c[1].popServico();
					this.reposicionaCliente();
				} else {
					Random rand = new Random();
					int next = rand.nextInt(2);
					this.cliente = c[next];
					c[next].setFuncionario(this);
					System.out.println(Thread.currentThread().getName() + ": Atendendo cliente" + c[next].getId());
					try {	
						Thread.sleep(1000*c[next].proximoServico().getTempo());
					}
					catch(InterruptedException ex) {
						 Thread.currentThread().interrupt();
					}
					c[next].popServico();
					this.reposicionaCliente();
				}
			}
			try {	
				Thread.sleep(2000);
			}
			catch(InterruptedException ex) {
				 Thread.currentThread().interrupt();
			}
		}
	}
	
	private int numeroDaFila(Cliente cliente) {
		return (cliente.quantidadeServicosSolicitados() - cliente.quantidadeServicosRestantes()) + 1;
	}

}
