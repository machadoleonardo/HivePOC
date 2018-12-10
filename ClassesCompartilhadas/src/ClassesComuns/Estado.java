package ClassesComuns;

import InterfacesComuns.Throw;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Estado implements Throw, Jogada {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String mensagemEstado;
	final static int BSIZE = 24;
	protected Peca[][] tabuleiro;
	
	public Estado() {
		this.tabuleiro = new Peca[BSIZE][BSIZE];
	}
	
	public Estado(Peca[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	public String getMensagemEstado() {
		return mensagemEstado;
	}

	public void setEstadoMensagem(String text) {
		mensagemEstado = text;
	}

	public Peca getPeca(int line, int column) {
		Peca ret;
		ret = tabuleiro[(line - 1)][(column - 1)];
		return (ret);
	}

	public boolean informaSeVazio() {
		boolean retorno = true;
		for (int i = 0; i < BSIZE; i++) {
			for (int j = 0; j < BSIZE; j++) {
				Peca p = this.getPeca(i, j);
				if (p.getNroPeca() != -1) {
					retorno = false;
					return retorno;
				} else {
					retorno = true;
				}
			}
		}
		return retorno;
	}

	public boolean informaSePosicaoVazia(int line, int column) {
		return ((this.getPeca(line, column)) == null);
	}

	public boolean informaSePosicaoOcupada(int line, int column) {
		return ((this.getPeca(line, column)) != null);
	}

	public Peca[][] getTabuleiro() {
		return tabuleiro;
	}

}
