package salao.simulador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import salao.servicos.Corte;
import salao.servicos.Depilacao;
import salao.servicos.Massagem;
import salao.servicos.Pedicure;
import salao.servicos.Penteado;
import salao.servicos.Servico;
import salao.servicos.TipoServico;
import salao.cliente.Cliente;

public class GeradorClientes implements Runnable {

	private int contClientes;
	
	private int qntdClientesAtendidos;
	
	private Random rand;
	
	private FilasClientes filas;
	
	public GeradorClientes() {
		// TODO Auto-generated constructor stub
	}
	
	public GeradorClientes(FilasClientes filas) {
		// TODO Auto-generated constructor stub
		contClientes = 0;
		qntdClientesAtendidos = 0;
		rand = new Random();
		this.filas = filas;
	}

	public int getContClientes() {
		return contClientes;
	}

	public void setContClientes(int contClientes) {
		this.contClientes = contClientes;
	}

	public int getQntdClientesAtendidos() {
		return qntdClientesAtendidos;
	}

	public void setQntdClientesAtendidos(int qntdClientesAtendidos) {
		this.qntdClientesAtendidos = qntdClientesAtendidos;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	public FilasClientes getFilas() {
		return filas;
	}

	public void setFilas(FilasClientes filas) {
		this.filas = filas;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			Cliente c = criaCliente();
			ArrayList<Integer> t = new ArrayList<Integer>();			
			t = geraTempoServicos(c.getServicosSolicitados().size());

			// Atribui o tempo aos serviços da forma especificada na descrição do projeto
			// Obs: getServico() da classe serviço não gasta um serviço
			for(Servico s: c.getServicosSolicitados()) {
				if(s.getTipo() == TipoServico.PENTEADO) {
					s.setTempo(t.get(0));
					t.remove(0);
				} else if(s.getTipo() == TipoServico.CORTE) {
					s.setTempo(t.get(0));
					t.remove(0);
				} else if(s.getTipo() == TipoServico.DEPILACAO) {	
					s.setTempo(t.get(0));
					t.remove(0);
				} else if(s.getTipo() == TipoServico.PEDICURE) {	
					s.setTempo(t.get(0));
					t.remove(0);
				} else if(s.getTipo() == TipoServico.MASSAGEM) {	
					s.setTempo(t.get(0));
					t.remove(0);
				}
			}
			
			filas.insereEmFilaClientes(0, c);
			
			try {	
				// Tempo de geração de clientes: 1 ~ 5 segundos
				Thread.sleep(500*(rand.nextInt(5)+1));
			} catch(InterruptedException ex) {
				 Thread.currentThread().interrupt();
			}
		}
	}
	
	/**
	 * Metodo que cria uma instancia de cliente, gera os serviços que o cliente quer e retorna essa instancia para
	 * a thread.
	 * @return Cliente
	 */
	public Cliente criaCliente() {
		contClientes += 1;
		boolean flag = false; 
		/*Servico servico = new Servico() {
		};*/
		Cliente cliente = new Cliente(contClientes);
		ArrayList<Integer> inserido = new ArrayList<Integer>();	// evita a repetição de tipos de serviços
		int quantServicos = 0;
		int porcentagemQtd = rand.nextInt(100)+1;

		if(porcentagemQtd >= 1 && porcentagemQtd <= 30)					// 30% dos clientes desejam todos os serviços
		{
			quantServicos = 5;
		}
		else if(porcentagemQtd >= 31 && porcentagemQtd <= 65) 			//35% desejam 4
		{
			quantServicos = 4;
		}
		else if(porcentagemQtd >= 66 && porcentagemQtd <= 85)  			// 20% desejam 3
		{
			quantServicos = 3;
		}
		else if(porcentagemQtd >= 86 && porcentagemQtd <= 95) 			 // 10% apenas 2
		{
			quantServicos = 2;
		}
		else if(porcentagemQtd >= 96 && porcentagemQtd <= 100)  			// 5% apenas 1
		{
			quantServicos = 1;
		}

		// Faz a inserção da escolha dos clientes inserindo por ordem de escolha
		// Não deixa escolher mais de uma vez um mesmo serviço
		for(int i = 0; i < quantServicos; i++)
		{
			// Vamos assumir que o maximo aqui é 155%
			int tipoServico = 0;
			int porcentagemTipo = rand.nextInt(100)+1;

			if(porcentagemTipo >= 1 && porcentagemTipo <= 50)			// 50% para corte
			{
				tipoServico = 1;
			}
			else if(porcentagemTipo >= 51 && porcentagemTipo <= 90) 	// 40% para penteado
			{
				tipoServico = 2;
			}
			else if(porcentagemTipo >= 91 && porcentagemTipo <= 120)  	// 30% para pedicure
			{
				tipoServico = 3;
			}
			else if(porcentagemTipo >= 121 && porcentagemTipo <= 140) 	// 20% para depilação
			{
				tipoServico = 4;
			}
			else if(porcentagemTipo >= 141 && porcentagemTipo <= 155)  	// 15% para massagem
			{
				tipoServico = 5;
			}

			if(inserido.isEmpty() == false)
			{
				// Enquanto for serviço repetido, gera outro 
				// (Na realidade, se gerar um igual ele incrementa o valor e testa novamente)
				// Fiz assim pra simplificar
				while(flag == false)
				{
					for(int num : inserido)
					{
						if(num == tipoServico)
						{	
							//tipoServico = rand.nextInt(5)+1;
							if(tipoServico < 5)
							{
								tipoServico++;
							}
							else
							{
								tipoServico = 1;
							}

							flag = true;
							break;
						}
					}

					if(flag == true)
					{
						flag = false;
					}
					else
					{
						flag = true;
					}
				}
			}

			flag = false;
			inserido.add(tipoServico);
			switch(tipoServico)
			{
			case 1:
				Servico servico = new Corte(0);
				cliente.incluirServico(servico);
				break;
			case 2:
				servico = new Penteado(0);
				cliente.incluirServico(servico);
				break;
			case 3:
				servico = new Pedicure(0);
				cliente.incluirServico(servico);
				break;
			case 4:
				servico = new Depilacao(0);
				cliente.incluirServico(servico);
				break;
			case 5:
				servico = new Massagem(0);
				cliente.incluirServico(servico);
				break;
			default:
				throw(new IndexOutOfBoundsException("Opção não existe!!!"));
			}
		}
		return cliente;
	}
		
	
	public ArrayList<Integer> geraTempoServicos(int quantidade) {
		int tempos[] = new int[quantidade];
		ArrayList<Integer> t = new ArrayList<Integer>();
		
		// Gera os tempos aleatoriamente
		for(int i = 0; i < quantidade; i++) {
			tempos[i] = (rand.nextInt(10)+1);
		}
		
		Arrays.sort(tempos);
		
		// Insere de forma decrescente os elemento eno array list
		for(int i = tempos.length-1; i >= 0; i--) {
			t.add(tempos[i]);
		} 
		
		return t;
	}

}
