package salao.funcionarios;

import salao.cliente.Cliente;
import salao.salao.FilasClientes;

public abstract class Funcionario implements Runnable {

	private int qtdServicos;
	
	private double totalFaturado;
	
	private Cliente cliente;
	
	private FilasClientes filas;
	
	public Funcionario() {
		// TODO Auto-generated constructor stub
		cliente = null;
		filas = null;
		qtdServicos = 0;
		totalFaturado = 0.0;
	}
	
	public Funcionario(FilasClientes f, Cliente c) {
		filas = f;
		cliente = c;
		qtdServicos = 0;
		totalFaturado = 0.0;
	}

	public int getQtdServicos() {
		return qtdServicos;
	}

	public void setQtdServicos(int qtdServicos) {
		this.qtdServicos = qtdServicos;
	}

	public double getTotalFaturado() {
		return totalFaturado;
	}

	public void setTotalFaturado(double totalFaturado) {
		this.totalFaturado = totalFaturado;
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
		totalFaturado += d;
	}
	
	protected void reposicionaCliente() {
		if(cliente.quantidadeServicosRestantes() == 0) {
			filas.insereEmFilaCaixas(cliente);
		} else {
			filas.insereEmFilaClientes
				((cliente.quantidadeServicosSolicitados() - cliente.quantidadeServicosRestantes()) + 1, cliente);
		}
	}

}
