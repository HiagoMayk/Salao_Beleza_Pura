package salao.funcionarios;

import java.util.Random;
import java.util.concurrent.Semaphore;

import salao.cliente.Cliente;
import salao.simulador.FilasClientes;

public class Cabeleireira extends Funcionario {

	public Cabeleireira(FilasClientes f, Semaphore semFilasClientes, Semaphore semFilasCaixas, int id) {
		super(f, semFilasClientes, semFilasCaixas, id);
	}
	
	public Cabeleireira(FilasClientes f, Cliente c) {
		super(f, c);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Cliente c[] = new Cliente[2];
		while(true) {
			c[0] = null;
			c[1] = null;
			
			try {
				this.sFilasClientes.acquire();
				c[0] = this.filas.getProxParaCorte();
				c[1] = this.filas.getProxParaPenteado();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				this.sFilasClientes.release();
			}

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
				this.incrementaTotalFaturado(c[0].proximoServico().getPreco());
				c[0].popServico();
				this.reposicionaCliente();
				c[0].setFuncionario(null);
				this.cliente = null;
				this.incrementaQtdServicos();
				
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
				this.incrementaTotalFaturado(c[1].proximoServico().getPreco());
				c[1].popServico();
				this.reposicionaCliente();
				c[1].setFuncionario(null);
				this.cliente = null;
				this.incrementaQtdServicos();
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
					this.incrementaTotalFaturado(c[0].proximoServico().getPreco());
					c[0].popServico();
					this.reposicionaCliente();
					c[0].setFuncionario(null);
					this.cliente = null;
					this.incrementaQtdServicos();
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
					this.incrementaTotalFaturado(c[1].proximoServico().getPreco());
					c[1].popServico();
					this.reposicionaCliente();
					c[1].setFuncionario(null);
					this.cliente = null;
					this.incrementaQtdServicos();
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
					this.incrementaTotalFaturado(c[next].proximoServico().getPreco());
					c[next].popServico();
					this.reposicionaCliente();
					c[next].setFuncionario(null);
					this.cliente = null;
					this.incrementaQtdServicos();
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

	@Override
	public String toString() {
		return "Cabeleireira [" + getId() + "]";
	}

}
