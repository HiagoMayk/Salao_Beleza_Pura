package salao.simulador;

import java.util.ArrayList;

import salao.cliente.Cliente;
import salao.servicos.TipoServico;

/**
 * Classe que armazena filas de clientes.
 * 
 * As 6 filas foram criadas para atender duas regras de negocio:
 * 	- Otimizar o tempo do cliente, atendendo-o da melhor forma e no menor tempo possivel.
 * 	- Cada cliente pode desejar de 1 a todos os servicos oferecidos pelo salao.
 * Cada fila tem uma prioridade diferente, por exemplos:
 * 	- filaClientes1 eh de um cliente de acabou de chegar
 * 	- filaClientes2 eh de um cliente que já foi atendido 1 vez e ainda tem pedido
 * 	- filaClientes3 eh de um cliente que já foi atendido 2 vezes e ainda tem pedido
 * 	- filaClientes4 eh de um cliente que já foi atendido 3 vezes e ainda tem pedido
 * 	- filaClientes5 eh de um cliente que já foi atendido 4 vezes e ainda tem pedido
 * Alem dessas, tambem existe a fila para ir para os caixas.
 * 
 * Usa padrao de projeto Singleton.
 */
public class FilasClientes {

	private ArrayList<ArrayList<Cliente>> filasClientes;
	private ArrayList<ArrayList<Cliente>> filasCaixas;
	
	private final int numFilasClientes = 5;
	private final int numFilasCaixas = 1;
	
	private static FilasClientes instance;
	
	private FilasClientes() {
		filasCaixas = new ArrayList<ArrayList<Cliente>>();
		for(int i = 0; i < numFilasCaixas; i++) {
			filasCaixas.add(new ArrayList<Cliente>());
		}
		filasClientes = new ArrayList<ArrayList<Cliente>>();
		for(int i = 0; i < numFilasClientes; i++) {
			filasClientes.add(new ArrayList<Cliente>());
		}
	}
	
	public static FilasClientes getInstance() {
		if(instance == null) {
			instance = new FilasClientes();
		}
		return instance;
	}
	
	public ArrayList<ArrayList<Cliente>> getFilasClientes() {
		return filasClientes;
	}

	public void setFilasClientes(ArrayList<ArrayList<Cliente>> filasClientes) {
		this.filasClientes = filasClientes;
	}
	
	public ArrayList<Cliente> getFilaClientes(int i) {
		return filasClientes.get(i);
	}

	public void setFilaClientes(int i, ArrayList<Cliente> filaClientes) {
		this.filasClientes.set(i, filaClientes);
	}

	public ArrayList<ArrayList<Cliente>> getFilasCaixas() {
		return filasCaixas;
	}

	public void setFilasCaixas(ArrayList<ArrayList<Cliente>> filasCaixas) {
		this.filasCaixas = filasCaixas;
	}
	
	public ArrayList<Cliente> getFilaCaixa(int i) {
		return filasCaixas.get(i);
	}

	public void setFilasCaixas(int i, ArrayList<Cliente> filasCaixas) {
		this.filasCaixas.set(i, filasCaixas);
	}

	public int getNumFilasClientes() {
		return numFilasClientes;
	}

	public int getNumFilasCaixas() {
		return numFilasCaixas;
	}

	/**
	 * @param fila Indice da fila [1, max]
	 * @param c
	 */
	public synchronized void insereEmFilaClientes(int fila, Cliente c) {
		filasClientes.get(fila-1).add(c);
	}
	
	/**
	 * @param fila Indice da fila [1, max]
	 * @param c
	 */
	public synchronized void removeDeFilaClientes(int fila, Cliente c) {
		filasClientes.remove(fila-1).add(c);
	}
	
	public synchronized void insereEmFilaCaixas(Cliente c) {
		filasCaixas.get(0).add(c);
	}
	
	public synchronized void removeDeFilaCaixas(Cliente c) {
		filasCaixas.remove(0).add(c);
	}
	
	public synchronized Cliente getProxParaPenteado() {
		for(int i = 0; i < filasClientes.size(); i++) {
			for(int j = 0; j < filasClientes.get(i).size(); j++) {
				if(filasClientes.get(i).get(j).proximoServico().getTipo() == TipoServico.PENTEADO) {
					Cliente c = filasClientes.get(i).get(j); 
					removeDeFilaClientes(i, c);
					return c;
				}
			}
		}
		return null;
	}
	
	public synchronized Cliente getProxParaCorte() {
		for(int i = 0; i < filasClientes.size(); i++) {
			for(int j = 0; j < filasClientes.get(i).size(); j++) {
				if(filasClientes.get(i).get(j).proximoServico().getTipo() == TipoServico.CORTE) {
					Cliente c = filasClientes.get(i).get(j); 
					removeDeFilaClientes(i, c);
					return c;
				}
			}
		}
		return null;
	}
	
	public synchronized Cliente getProxParaDepilacao() {
		for(int i = 0; i < filasClientes.size(); i++) {
			for(int j = 0; j < filasClientes.get(i).size(); j++) {
				if(filasClientes.get(i).get(j).proximoServico().getTipo() == TipoServico.DEPILACAO) {
					Cliente c = filasClientes.get(i).get(j); 
					removeDeFilaClientes(i, c);
					return c;
				}
			}
		}
		return null;
	}
	
	public synchronized Cliente getProxParaPedicure() {
		for(int i = 0; i < filasClientes.size(); i++) {
			for(int j = 0; j < filasClientes.get(i).size(); j++) {
				if(filasClientes.get(i).get(j).proximoServico().getTipo() == TipoServico.PEDICURE) {
					Cliente c = filasClientes.get(i).get(j); 
					removeDeFilaClientes(i, c);
					return c;
				}
			}
		}
		return null;
	}
	
	public synchronized Cliente getProxParaMassagem() {
		for(int i = 0; i < filasClientes.size(); i++) {
			for(int j = 0; j < filasClientes.get(i).size(); j++) {
				if(filasClientes.get(i).get(j).proximoServico().getTipo() == TipoServico.MASSAGEM) {
					Cliente c = filasClientes.get(i).get(j); 
					removeDeFilaClientes(i, c);
					return c;
				}
			}
		}
		return null;
	}
	
	public synchronized Cliente getProxParaCaixa() {
		for(int i = 0; i < filasCaixas.size(); i++) {
			for(int j = 0; j < filasCaixas.get(i).size();) {
				Cliente c = filasCaixas.get(i).get(j); 
				removeDeFilaCaixas(c);
				return c;
			}
		}
		return null;
	}

}
