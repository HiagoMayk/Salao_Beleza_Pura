package salao.servicos;

public class Penteado extends Servico {
	
	/**
	 * Construtor padrao.
	 * @param tempo Tempo a ser gasto
	 */
	public Penteado(int tempo) {
		super(TipoServico.PENTEADO, tempo, 50.0);
	}

}
