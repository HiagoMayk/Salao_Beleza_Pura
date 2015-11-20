package salao.servicos;

/**
 * Classe abstrata que representa um servico qualquer.
 * 
 * Possui campos comuns a servicos e disponibiliza metodos que podem ser uteis,
 * como equals() e toString().
 *
 */
public abstract class Servico {

	// Tipo do servico
	protected TipoServico tipo;
	
	// Tempo que ele demora
	protected int tempo;
	
	// Preco do servico
	protected double preco;
	
	/**
	 * Construtor vazio, caso necessario.
	 * Faz uma inicializacao qualquer.
	 */
	public Servico() {
		this.tipo = null;
		this.tempo = 0;
		this.preco = 0.0;
	}
	
	
	/**
	 * Construtor padrao
	 * @param tipo Tipo do servico
	 * @param tempo Tempo que ele demora
	 * @param preco Preco do servico
	 */
	public Servico(TipoServico tipo, int tempo, double preco) {
		this.tipo = tipo;
		this.tempo = tempo;
		this.preco = preco;
	}

	public TipoServico getTipo() {
		return tipo;
	}

	public void setTipo(TipoServico tipo) {
		this.tipo = tipo;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Servico [tipo=" + tipo + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servico other = (Servico) obj;
		if (tipo != other.tipo)
			return false;
		return true;
	}	

}
