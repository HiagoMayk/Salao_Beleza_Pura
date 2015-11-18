package salao.servicos;

public class Massagem extends Servico {
	
	/**
	 * Construtor padrao.
	 * @param tempo Tempo a ser gasto
	 */
	public Massagem(int tempo) {
		super(TipoServico.MASSAGEM, tempo, 20.0);
	}

}
