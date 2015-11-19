package salao.funcionarios;

import java.util.concurrent.Semaphore;

import salao.cliente.Cliente;
import salao.simulador.FilasClientes;

public abstract class Funcionario implements Runnable {

	protected int id;
	
	protected int qtdServicos;
	
	protected double totalFaturadoLiquido;
	
	protected double totalFaturadoBruto;
	
	protected Cliente cliente;
	
	protected FilasClientes filas;
	
	protected Semaphore sFilasClientes;
	
	protected Semaphore sFilasCaixas;
	
	public Funcionario() {
		// TODO Auto-generated constructor stub
		cliente = null;
		filas = null;
		qtdServicos = 0;
		totalFaturadoBruto = 0.0;
		totalFaturadoLiquido = 0.0;
		sFilasClientes = null;
		sFilasCaixas = null;
		id = 0;
	}
	
	public Funcionario(FilasClientes f, Semaphore semFilasClientes, Semaphore semFilasCaixas, int id) {
		filas = f;
		cliente = null;
		qtdServicos = 0;
		totalFaturadoBruto = 0.0;
		totalFaturadoLiquido = 0.0;
		sFilasClientes = semFilasClientes;
		sFilasCaixas = semFilasCaixas;
		this.id = id;
	}
	
	public Funcionario(FilasClientes f, Cliente c) {
		filas = f;
		cliente = c;
		qtdServicos = 0;
		totalFaturadoBruto = 0.0;
		totalFaturadoLiquido = 0.0;
		sFilasClientes = null;
		sFilasCaixas = null;
		id = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQtdServicos() {
		return qtdServicos;
	}

	public void setQtdServicos(int qtdServicos) {
		this.qtdServicos = qtdServicos;
	}

	public double getTotalFaturadoBruto() {
		return totalFaturadoBruto;
	}
	
	public double getTotalFaturadoLiquido() {
		return totalFaturadoLiquido;
	}

	public void setTotalFaturadoBruto(double totalFaturado) {
		this.totalFaturadoBruto = totalFaturado;		
	}
	
	public void setTotalFaturadoLiquido(double totalFaturado) {
		this.totalFaturadoLiquido = totalFaturado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public FilasClientes getFilas() {
		return filas;
	}

	public void setFilas(FilasClientes filas) {
		this.filas = filas;
	}
	
	public void incrementaQtdServicos() {
		qtdServicos += 1;
	}
	
	public void incrementaTotalFaturado(double d) {
		totalFaturadoBruto += d;
		d = d*(40/100);
		totalFaturadoLiquido += d;
	}
	
	protected void reposicionaCliente() {
		try {
			this.sFilasClientes.acquire();
			if(cliente.quantidadeServicosRestantes() == 0) {
				filas.insereEmFilaCaixas(cliente);
			} else {
				filas.insereEmFilaClientes
					((cliente.quantidadeServicosSolicitados() - cliente.quantidadeServicosRestantes()), cliente);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.sFilasClientes.release();
		}
	}

}
