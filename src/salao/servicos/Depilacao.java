package salao.servicos;

public class Depilacao extends Servico {
	
	/**
	 * Construtor padrao.
	 * @param tempo Tempo a ser gasto
	 */
	public Depilacao(int tempo) {
		super(TipoServico.DEPILACAO, tempo, 40.0);
	}

}
