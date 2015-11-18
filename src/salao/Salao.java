package salao;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Salao implements Runnable
{
	private FilasClientes filas;
	ArrayList<Cliente> filaCaixas;
	
	private static Financeira financeira;
	
	// usa o array lista para a fila 1
	public Salao(FilasClientes filas, ArrayList<Cliente> filaCaixas)
	{
		this.filas = new FilasClientes(null);
		this.filas = filas;
		
		this.filaCaixas = new ArrayList<Cliente>();
		this.filaCaixas = filaCaixas;
		
		financeira = new Financeira();
	
	}
	
	public void run()
	{
		while(true)
		{
			imprime();
		
			try
			{	
				Thread.sleep(5000);
			}
			catch(InterruptedException ex)
			{
				 Thread.currentThread().interrupt();
			}
		}
	}
	
	public synchronized void imprime()
	{
		System.out.println();
		System.out.println("===========================================");
		
		for(int fila = 5; fila >= 1; fila--)
		{
			System.out.println(filas.getFila(fila).size() + " Clientes na fila " + fila);
			
			for(Cliente c: filas.getFila(fila))
			{	
				System.out.println("cliente" + c.getIdCliente() + " - " + c.verServico() + " - " + c.getQtdServicos());
			}
			System.out.println("--");
		}

		System.out.println(filaCaixas.size() + " Clientes na fila do Caixa");
		for(Cliente c: filaCaixas)
		{	
				System.out.println("cliente" + c.getIdCliente());
		}
		
		System.out.println("--");
			
		System.out.println("-------------------------------------------");
		/*
		System.out.println("Resumo:");
		for(int i = 0; i < 5; i++)
		{
			System.out.println("Caleleireira" + (i+1) + " : " + (financeira.getFatCabelereira(i).getTotalDinheiro()*0.4));
		}
		
		for(int i = 0; i < 3; i++)
		{
			System.out.println("Manicure" + (i+1) + " : " + (financeira.getFatManicure(i).getTotalDinheiro()*0.4));
		}
		
		for(int i = 0; i < 2; i++)
		{
			System.out.println("Depiladora" + (i+1) + ": " + (financeira.getFatDepiladora(i).getTotalDinheiro()*0.4));
		}
		
		System.out.println("Massagista: " + (financeira.getFatMassagista().getTotalDinheiro()*0.4));
		
		System.out.println("Total de atendimentos: " + financeira.getTotalAtendimentoFunc());
		System.out.printf("Valor total: R$ %.2f", financeira.getValorTotalfuncionarios());
		System.out.println();
		
		System.out.println("Total de atendimentos computados no caixa: " + financeira.getTotalAtendimento());
		System.out.printf("Total de dinheiro recebido no caixa: R$ %.2f", financeira.getTotalDinheiroSalao());
		System.out.println();
		
		System.out.println("Atendimentos a serem computados: " + (financeira.getTotalAtendimentoFunc() - financeira.getTotalAtendimento()));
		System.out.printf("Dinheiro a ser recebido no caixa: R$ %.2f", (financeira.getValorTotalfuncionarios() - financeira.getTotalDinheiroSalao()));
		System.out.println();
		
		//System.out.println("Clientes que faltam ir ao caixa: " + (idCliente - qtdClientesAtendidos));
		System.out.println();
		*/
		//System.out.println("--");
		//System.out.println("-------------------------------------------");
	}

}
