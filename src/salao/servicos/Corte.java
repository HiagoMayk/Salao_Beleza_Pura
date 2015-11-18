package salao.servicos;

public class Corte extends Servico {

	/**
	 * Construtor padrao.
	 * @param tempo Tempo a ser gasto
	 */
	public Corte(int tempo) {
		super(TipoServico.CORTE, tempo, 30.0);
	}

}
