package salao;

import java.util.ArrayList;
import java.util.Random;

public class GeradorClientes implements Runnable
{
	private static int idCliente;      // Identificador do cliente e também serve para contar
	private static int qtdClientesAtendidos;	// quantos clientes foram atendidos até um determina momento
	
	private Random gerador;
	
	private ArrayList<Cliente> fila1;
	
	public GeradorClientes(ArrayList<Cliente> fila)
	{
		this.fila1 = fila;
		idCliente = 0;
		qtdClientesAtendidos = 0;
		gerador = new Random();
	}
	
	public void run()
	{
		while(true)
		{
			Cliente cliente = criaCliente();
			
			ArrayList<Integer> t = new ArrayList<Integer>();
			
			t = geraTempoServicos(cliente.getQtdServicos());
			
			// Atribui o tempo aos serviços da forma especificada na descrição do projeto
			// Obs: getServico() da classe serviço não gasta um serviço
			for(Servico s: cliente.getServicosSolicitados())
			{
				if(s.getServico() == "Penteado")
				{
					s.setTempo(t.get(0));
					t.remove(0);
				}
				else if(s.getServico() == "Corte")
				{
					s.setTempo(t.get(0));
					t.remove(0);
				}
				else if(s.getServico() == "Depilação")
				{	
					s.setTempo(t.get(0));
					t.remove(0);
				}
				else if(s.getServico() == "Pedicure")
				{	
					s.setTempo(t.get(0));
					t.remove(0);
				}
				else if(s.getServico() == "Massagem")
				{	
					s.setTempo(t.get(0));
					t.remove(0);
				}
			}
			
			//método sincronizado
			adicionaNaFila1(cliente);
				
			try
			{	
				// Tempo de geração de clientes: 1 ~ 5 segundos
				Thread.sleep(1000*(gerador.nextInt(5)+1));
			}
			catch(InterruptedException ex) 
			{
				 Thread.currentThread().interrupt();
			}
		}
	}

	//Insere na fila 1, que é a fila dos clientes que acabaram de chegar no salão
	public synchronized void adicionaNaFila1(Cliente cliente)
	{
		fila1.add(cliente);
	}
	
	//Método que cria uma instancia de cliente, gera os serviços que o cliente quer e retorna essa instancia para
		// o método executar()
	public Cliente criaCliente()
	{
		idCliente++; 												//Incrementa o identificador do cliente
		boolean flag = false; 
		Servico servico;
		Cliente cliente = new Cliente(idCliente);
		ArrayList<Integer> inserido = new ArrayList<Integer>(); 	// evita a repetição de tipos de serviços
		//int quantServicos = gerador.nextInt(6)+1;
		int quantServicos = 0;
		int porcentagemQtd = gerador.nextInt(100)+1;
			
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
			int porcentagemTipo = gerador.nextInt(100)+1;
			
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
						//tipoServico = gerador.nextInt(5)+1;
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
				servico = new Servico("Corte");
				cliente.setServico(servico);
				break;
			case 2:
				servico = new Servico("Penteado");
				cliente.setServico(servico);
				break;
			case 3:
				servico = new Servico("Pedicure");
				cliente.setServico(servico);
				break;
			case 4:
				servico = new Servico("Depilação");
				cliente.setServico(servico);
				break;
			case 5:
				servico = new Servico("Massagem");
				cliente.setServico(servico);
				break;
			default:
				System.out.println("Opção não existe!!!");
		}
	}
			
	return cliente;
}
	
	public ArrayList<Integer> geraTempoServicos(int quantidade)
	{
		int tempos[] = new int[quantidade];
		ArrayList<Integer> t = new ArrayList<Integer>();
		
		// Gera os tempos aleatoriamente
		for(int i = 0; i < quantidade; i++)
		{
			tempos[i] = (gerador.nextInt(10)+1);
		}
		
		// BubbluSort
		for (int i = tempos.length; i >= 1; i--)
		{  
		    for (int j = 1; j < i; j++) 
		    {  
		        if (tempos[j-1] >tempos[j]) 
		        {  
		            int aux = tempos[j];  
		            tempos[j] = tempos[j-1];  
		            tempos[j-1] = aux;  
		        }  
		    }
		}
		
		// Insere de forma decrescente os elemento eno array list
		for(int i = tempos.length-1; i >= 0; i--)
		{
			t.add(tempos[i]);
		} 
		
		return t;
	}
	
}
