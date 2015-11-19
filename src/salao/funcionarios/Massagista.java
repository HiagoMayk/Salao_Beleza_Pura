package salao.funcionarios;

import java.util.concurrent.Semaphore;

import salao.cliente.Cliente;
import salao.simulador.FilasClientes;

public class Massagista extends Funcionario {

	public Massagista(FilasClientes f, Semaphore semFilasClientes, Semaphore semFilasCaixas, Semaphore semResumo) {
		super(f, semFilasClientes, semFilasCaixas, semResumo);
	}
	
	public Massagista(FilasClientes f, Cliente c) {
		super(f, c);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			Cliente c = null;
			
			try {
				this.sFilasClientes.acquire();
				c = this.filas.getProxParaMassagem();
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
				
				//---------------------------------
				try {
					this.semResumo.acquire();
					
					
					// Sleep só para simular a RC
					try {	
						Thread.sleep(1000);
					} catch(InterruptedException ex) {
						 Thread.currentThread().interrupt();
					}
					// insere os valores
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					this.semResumo.release();
				}
				//---------------------------------
				
				
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
