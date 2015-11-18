package salao.servicos;

public class Pedicure extends Servico {

	/**
	 * Construtor padrao.
	 * @param tempo Tempo a ser gasto
	 */
	public Pedicure(int tempo) {
		super(TipoServico.PEDICURE, tempo, 30.0);
	}

}
