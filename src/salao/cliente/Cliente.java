package salao.cliente;

import java.util.LinkedList;

import salao.funcionarios.Funcionario;
import salao.servicos.Servico;

/**
 * Classe que modela um cliente.
 *
 */
public class Cliente {

	// Identificador do cliente
	private int id;
	
	// Lista de servicos que foram solicitados (nao muda)
	private LinkedList<Servico> servicosSolicitados;
	
	// Lista de servicos que ainda nao foram realizados (muda)
	private LinkedList<Servico> servicosRestantes;
	
	// Funcionario que o esta atendendo em um dado momento
	private Funcionario funcionario;
	
	public Cliente() {
		id = 0;
		servicosSolicitados = new LinkedList<Servico>();
		servicosRestantes = new LinkedList<Servico>();
		funcionario = null;
	}
	
	public Cliente(int id) {
		this.id = id;
		servicosSolicitados = new LinkedList<Servico>();
		servicosRestantes = new LinkedList<Servico>();
		funcionario = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LinkedList<Servico> getServicosSolicitados() {
		return servicosSolicitados;
	}

	public void setServicosSolicitados(LinkedList<Servico> servicosSolicitados) {
		this.servicosSolicitados = servicosSolicitados;
	}

	public LinkedList<Servico> getServicosRestantes() {
		return servicosRestantes;
	}

	public void setServicosRestantes(LinkedList<Servico> servicosRestantes) {
		this.servicosRestantes = servicosRestantes;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void incluirServico(Servico s) {
		servicosSolicitados.add(s);
		servicosRestantes.add(s);
	}
	
	public int quantidadeServicosSolicitados() {
		return servicosSolicitados.size();
	}
	
	public int quantidadeServicosRestantes() {
		return servicosRestantes.size();
	}
	
	public Servico proximoServico() {
		if(!servicosRestantes.isEmpty()) {
			return servicosRestantes.getFirst();
		} else {
			return null;
		}
	}
	
	public void popServico() {
		servicosRestantes.removeFirst();
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", servicosSolicitados=" + servicosSolicitados.size() + ", servicosRestantes="
				+ servicosRestantes.size() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
