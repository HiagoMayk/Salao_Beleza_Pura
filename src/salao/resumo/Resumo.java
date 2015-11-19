package salao.resumo;

import java.util.List;
import java.util.concurrent.Semaphore;

import salao.funcionarios.Funcionario;

// Em desenvolvimento

public class Resumo {

	private List<Funcionario> funcionarios;
	
	private int totalAtendimentos;
	private double totalFaturado;
	private int numBlockSemResumo;
	private Semaphore semResumo;
	
	public Resumo() {
		// TODO Auto-generated constructor stub
		funcionarios = null;
	}
	
	public Resumo(List<Funcionario> f, Semaphore semResumo, int numBlockSemResumo) {
		funcionarios = f;
		this.semResumo = semResumo;
		this.numBlockSemResumo = numBlockSemResumo;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public int getTotalAtendimentos() {
		
		//---------------------------------
		try {
				for(int i = 0; i < numBlockSemResumo; i++)
				{
					this.semResumo.acquire();
				}
					
				// Sleep só para simular a RC
				try {	
					Thread.sleep(1000);
				} catch(InterruptedException ex) {
					 Thread.currentThread().interrupt();
				}
					
			// Calcula valores
					
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			finally 
			{
				for(int i = 0; i < numBlockSemResumo; i++)
				{
					this.semResumo.release();
				}
			}
				//---------------------------------
		
		return totalAtendimentos;
	}

	public void setTotalAtendimentos(int totalAtendimentos) {
		this.totalAtendimentos = totalAtendimentos;
	}

	public double getTotalFaturado() {
		//---------------------------------
		try {
			for(int i = 0; i < numBlockSemResumo; i++)
			{
				this.semResumo.acquire();
			}
			
			
			// Calcula valores
			
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally 
		{
			for(int i = 0; i < numBlockSemResumo; i++)
			{
				this.semResumo.release();
			}
		}
		//---------------------------------
		
		
		return totalFaturado;
	}

	public void setTotalFaturado(double totalFaturado) {
		this.totalFaturado = totalFaturado;
	}	

}
