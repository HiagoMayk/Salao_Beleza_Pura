package salao.gui;

import javax.swing.JFrame;

public abstract class AbstractGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	protected int largura;
	
	protected int altura;
	
	public AbstractGUI(String titulo, int largura, int altura) {
		super(titulo);
		//this.getContentPane().setLayout(new GridBagLayout());
		setLargura(largura);
		setAltura(altura);
		setSize(largura, altura);
		setResizable(false);
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

}
