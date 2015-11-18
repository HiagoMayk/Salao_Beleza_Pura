package salao.resumo;

import java.util.List;

import salao.funcionarios.Funcionario;

// Em desenvolvimento

public class Resumo {

	private List<Funcionario> funcionarios;
	
	private int totalAtendimentos;
	private double totalFaturado;
	
	public Resumo() {
		// TODO Auto-generated constructor stub
		funcionarios = null;
	}
	
	public Resumo(List<Funcionario> f) {
		funcionarios = f;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public int getTotalAtendimentos() {
		return totalAtendimentos;
	}

	public void setTotalAtendimentos(int totalAtendimentos) {
		this.totalAtendimentos = totalAtendimentos;
	}

	public double getTotalFaturado() {
		return totalFaturado;
	}

	public void setTotalFaturado(double totalFaturado) {
		this.totalFaturado = totalFaturado;
	}	

}
