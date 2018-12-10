package estruturaInterna;

import javax.swing.JOptionPane;

import ClassesComuns.Jogador;
import ClassesComuns.Lance;
import ClassesComuns.Partida;
import ClassesComuns.Peca;
import ClassesComuns.Estado;

// Game.gameCode
// 1 - game not started
// 2 - game finished with quitting
// 3 - irregular move (game in progress)
// 4 - next player (game in progress)
// 5 - game with winner
// 6 - game tied

public class Colmeia {

	protected Peca[][] tabuleiro;
	final static int BSIZE = 24;
	protected Jogador jogador1 = null;
	protected Jogador jogador2 = null;
	protected boolean finalizada = false;
	protected boolean vencedor = false;

	public Colmeia() {

		tabuleiro = new Peca[24][24];

		for (int i = 0; i < BSIZE; i++) {
			for (int j = 0; j < BSIZE; j++) {
				tabuleiro[i][j] = new Peca();
			}
		}

	}

	public void setPeca(int x, int y, Peca p) {

		tabuleiro[x][y] = p;

	}

	public Peca getPeca(int x, int y) {

		return tabuleiro[x][y];

	}

	public void limpaPosicao(int x, int y) {

		Peca p = new Peca();
		tabuleiro[x][y] = p;
	}

	public void limparColmeia() {
		for (int i = 0; i < BSIZE; i++) {
			for (int j = 0; j < BSIZE; j++) {
				Peca p = new Peca();
				tabuleiro[i][j] = p;

			}
		}
	}

	public int getBsize() {

		return BSIZE;
	}

	public boolean colmeiaVazia() {

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

	public boolean temPecaVizinha(int i, int j) {

		boolean retorno = false;
		if (i % 2 == 0) {
			if (this.getPeca(i + 1, j).getNroPeca() == -1 && this.getPeca(i - 1, j).getNroPeca() == -1
					&& this.getPeca(i, j + 1).getNroPeca() == -1 && this.getPeca(i, j - 1).getNroPeca() == -1
					&& this.getPeca(i - 1, j - 1).getNroPeca() == -1 && this.getPeca(i + 1, j - 1).getNroPeca() == -1) {
				retorno = false;
			} else {
				retorno = true;
			}
		} else {

			if (this.getPeca(i + 1, j).getNroPeca() == -1 && this.getPeca(i - 1, j).getNroPeca() == -1
					&& this.getPeca(i, j + 1).getNroPeca() == -1 && this.getPeca(i, j - 1).getNroPeca() == -1
					&& this.getPeca(i - 1, j + 1).getNroPeca() == -1 && this.getPeca(i + 1, j + 1).getNroPeca() == -1) {
				retorno = false;
			} else {
				retorno = true;
			}

		}
		return retorno;
	}

	public boolean abelhaCercada(String corOp) {

		boolean retorno = false;

		for (int i = 0; i < BSIZE; i++) {
			for (int j = 0; j < BSIZE; j++) {
				Peca p = this.getPeca(i, j);
				if (p.getNroPeca() == 0) {
					if (cercada(i, j)) {
						if (p.getCor().equals(corOp)) {
							retorno = true;
							return retorno;
						}
					} else {
						retorno = false;
					}
				}
			}
		}
		return retorno;

	}

	public boolean abelhaPropriaCercada(String corOp) {

		boolean retorno = false;

		for (int i = 0; i < BSIZE; i++) {
			for (int j = 0; j < BSIZE; j++) {
				Peca p = this.getPeca(i, j);
				if (p.getNroPeca() == 0) {
					if (cercada(i, j)) {
						if (!p.getCor().equals(corOp)) {
							retorno = true;
							return retorno;
						}
					} else {
						retorno = false;
					}
				}
			}
		}
		return retorno;

	}

	public boolean cercada(int i, int j) {

		boolean retorno = false;
		if (i % 2 == 0) {
			if (this.getPeca(i + 1, j).getNroPeca() != -1 && this.getPeca(i - 1, j).getNroPeca() != -1
					&& this.getPeca(i, j + 1).getNroPeca() != -1 && this.getPeca(i, j - 1).getNroPeca() != -1
					&& this.getPeca(i - 1, j - 1).getNroPeca() != -1 && this.getPeca(i + 1, j - 1).getNroPeca() != -1) {
				retorno = true;
			} else {
				retorno = false;
			}
		} else {

			if (this.getPeca(i + 1, j).getNroPeca() != -1 && this.getPeca(i - 1, j).getNroPeca() != -1
					&& this.getPeca(i, j + 1).getNroPeca() != -1 && this.getPeca(i, j - 1).getNroPeca() != -1
					&& this.getPeca(i - 1, j + 1).getNroPeca() != -1 && this.getPeca(i + 1, j + 1).getNroPeca() != -1) {
				retorno = true;
			} else {
				retorno = false;
			}

		}
		return retorno;
	}

	public boolean temAbelha(String corOp) {

		boolean retorno = false;
		for (int i = 0; i < BSIZE; i++) {
			for (int j = 0; j < BSIZE; j++) {
				Peca p = this.getPeca(i, j);
				if (p.getNroPeca() == 0 && !p.getCor().equals(corOp)) {
					retorno = true;
					return retorno;
				} else {
					retorno = false;
				}
			}
		}
		return retorno;
	}

	public boolean temPecaVizinhaOutroTime(int i, int j, String corOp) {

		boolean retorno = false;

		if (i % 2 == 0) {
			if (this.getPeca(i + 1, j).getCor().contains(corOp) || this.getPeca(i - 1, j).getCor().equals(corOp)
					|| this.getPeca(i, j + 1).getCor().equals(corOp) || this.getPeca(i, j - 1).getCor().equals(corOp)
					|| this.getPeca(i - 1, j - 1).getCor().equals(corOp)
					|| this.getPeca(i + 1, j - 1).getCor().equals(corOp)) {
				retorno = true;
			} else {
				retorno = false;
			}
		} else {

			if (this.getPeca(i + 1, j).getCor().equals(corOp) || this.getPeca(i - 1, j).getCor().equals(corOp)
					|| this.getPeca(i, j + 1).getCor().equals(corOp) || this.getPeca(i, j - 1).getCor().equals(corOp)
					|| this.getPeca(i - 1, j + 1).getCor().equals(corOp)
					|| this.getPeca(i + 1, j + 1).getCor().equals(corOp)) {
				retorno = true;
			} else {
				retorno = false;
			}

		}
		return retorno;
	}

	public boolean verificaDistancia(Peca p, int x, int y, int origX, int origY) {

		boolean result = false;
		int distMax = p.getPassosMaximo();
		JOptionPane.showMessageDialog(null, "passos maximos da peca: " + distMax);
		int distPercX;
		int distPercY;
		if (x > origX) {
			distPercX = x - origX;
		} else {
			distPercX = origX - x;
		}
		if (y > origY) {
			distPercY = y - origY;
		} else {
			distPercY = origY - y;
		}

		if (distPercX == 0 && distPercY <= distMax || distPercY == 0 && distPercX <= distMax
				|| origX - x == distMax && origY - y == distMax || x - origX == distMax && y - origY == distMax
				|| origX - x == distMax && origY - y == -distMax || x - origX == distMax && y - origY == -distMax
				|| (distMax > 1 && x - origX + y - origY <= distMax && x - origX + y - origY >= 0)
				|| (distMax > 1 && x - origX + y - origY > -distMax && x - origX + y - origY < 0)) {
			result = true;
		} else {
			result = false;
		}

		return result;
	}

	public Partida tratarLance(Partida partida, Lance lance) {
		String notif = "";
		int x = lance.getX();
		int y = lance.getY();
		int nroPeca = lance.getP().getNroPeca();
		int origX = lance.getOrigX();
		int origY = lance.getOrigY();

		int rodadaAtual = partida.getRodada();
		Jogador oponente = partida.getOponente(lance.getJogadorDoLance());
		Jogador jogadorDaVez = partida.getJogadorDaVez(lance.getJogadorDoLance());
		String corOp = oponente.getCor();
		Colmeia minhaColmeia = new Colmeia();
		minhaColmeia.tabuleiro = partida.getEstadoPartida().getTabuleiro();
		boolean temAbelha = minhaColmeia.temAbelha(corOp);
		Peca posicaoDestino = minhaColmeia.getPeca(x, y);
		Peca posicaoOrigem = minhaColmeia.getPeca(origX, origY);
		boolean colmeiaVazia = minhaColmeia.colmeiaVazia();
		boolean temPecaVizinha = minhaColmeia.temPecaVizinha(x, y);
		boolean temPecaVizinhaOutroTime = minhaColmeia.temPecaVizinhaOutroTime(x, y, corOp);
		int nroPecaDestino = posicaoDestino.getNroPeca();

		if (rodadaAtual == 0) { // primeira rodada de cada jogador
			if (nroPeca == 12) {
				notif = "Voce nao pode selecionar peca do adversario";
				partida.getEstadoPartida().setEstadoMensagem(notif);
				return partida;
			}
			if (nroPecaDestino != -1) {
				notif = "Posicao ocupada.";
				partida.getEstadoPartida().setEstadoMensagem(notif);
				return partida;
			}
			if (!colmeiaVazia && !temPecaVizinha) {
				notif = "Colmeia desconectada.";
				partida.getEstadoPartida().setEstadoMensagem(notif);
				return partida;
			}

			Peca p = jogadorDaVez.getPecas(nroPeca);
			minhaColmeia.setPeca(x, y, p);
			p.setEmJogo(true);
			tabuleiro = minhaColmeia.tabuleiro;
		}

		else { // rodada > 0

			if (nroPeca == 12 && rodadaAtual < 4 && !temAbelha) {
				notif = "nao pode mover enquanto nao inserir a abelha";
				partida.getEstadoPartida().setEstadoMensagem(notif);
				return partida;
			}
			if (nroPeca != 0 && rodadaAtual == 3 && !temAbelha) {
				notif = "insira a abelha";
				partida.getEstadoPartida().setEstadoMensagem(notif);
				return partida;
			}

			if (nroPeca != 12) { // eh uma insercao

				if (jogadorDaVez.getPecas(nroPeca).isEmJogo()) {
					notif = "Peca em jogo.";
					partida.getEstadoPartida().setEstadoMensagem(notif);
					return partida;
				}
				if (this.getPeca(x, y).getNroPeca() != -1) {
					notif = "Posicao ocupada.";
					partida.getEstadoPartida().setEstadoMensagem(notif);
					return partida;
				}
				if (!minhaColmeia.colmeiaVazia() && !minhaColmeia.temPecaVizinha(x, y)) {
					notif = "Colmeia desconectada.";
					partida.getEstadoPartida().setEstadoMensagem(notif);
					return partida;
				}
				if (temPecaVizinhaOutroTime) {
					notif = "Na insercao nao pode tocar na peca do adversario";
					partida.getEstadoPartida().setEstadoMensagem(notif);
					return partida;
				}

				Peca p = jogadorDaVez.getPecas(nroPeca);
				minhaColmeia.setPeca(x, y, p);
				jogadorDaVez.getPecas(nroPeca).setEmJogo(true);

			} else {

				if (nroPeca == 12) { // eh uma movimentacao

					if (!posicaoOrigem.getCor().equals(jogadorDaVez.getCor())) {
						notif = "Nao pode mover peca do adversario";
						partida.getEstadoPartida().setEstadoMensagem(notif);
						return partida;
					}

					if (posicaoDestino.getNroPeca() != -1) {
						notif = "Posicao ocupada.";
						partida.getEstadoPartida().setEstadoMensagem(notif);
						return partida;
					}

					if (!colmeiaVazia && !temPecaVizinha) {
						notif = "Colmeia desconectada.";
						partida.getEstadoPartida().setEstadoMensagem(notif);
						return partida;
					}

					if (!minhaColmeia.verificaDistancia(posicaoOrigem, x, y, origX, origY)) {
						notif = "Passos alem do permitido.";
						partida.getEstadoPartida().setEstadoMensagem(notif);
						return partida;
					}

					setPeca(x, y, posicaoOrigem);
					limpaPosicao(origX, origY);

				}
			}
		}

		notif = "Jogada realizada com sucesso";
		if (minhaColmeia.abelhaCercada(corOp)) {
			notif = "Abelha cercada. Voce ganhou o jogo.";
			partida.getEstadoPartida().setEstadoMensagem(notif);
			Estado newEstado = getEstado();
			partida.setEstadoPartida(newEstado);
			return partida;
		}
		if (minhaColmeia.abelhaPropriaCercada(corOp)) {
			partida.getEstadoPartida().setEstadoMensagem(notif);
			Estado newEstado =  getEstado();
			partida.setEstadoPartida(newEstado);
			return partida;
		}
		partida.setRodada(partida.getRodada() + 1);
		atualizaVez(partida);
		Estado newEstado =  getEstado();
		partida.setEstadoPartida(newEstado);

		return partida;
	}
	
	private Jogador getVencedor() {
		if (jogador1.isVencedor()) {
			return jogador1;
		} else {
			if (jogador2.isVencedor()) {
				return jogador2;
			} else {
				return null;
			}
		}
	}
	
	private Estado getEstado() {
		Estado estado;
		Jogador oVencedor = null;
		estado = new Estado(tabuleiro);
	// composing the message
		if (! finalizada) {
			if (jogador1.isDaVez()) {
				estado.setEstadoMensagem("Jogador: " + jogador1.getApelido());
			} else {
				estado.setEstadoMensagem("Jogador: " + jogador2.getApelido());		
			};			
		} else {
			oVencedor = this.getVencedor();
			if (oVencedor == null) {
				estado.setEstadoMensagem("Jogo terminado empatado");
			} else {
				estado.setEstadoMensagem("Vencedor: " + oVencedor.getApelido());			
			};			
		};
		
		
		return estado;
	}

	public Partida setInitialState(Jogador jogador1, Jogador jogador2) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		Partida partida = new Partida(); // gameCode = 1
		partida.setJogador1(jogador1);
		partida.setJogador2(jogador2);
		Estado state = new Estado(tabuleiro);
		Jogador player = this.getJogadorDaVez();
		String name = player.getApelido();
		String message = "Player - " + name;
		state.setEstadoMensagem(message);
		partida.setEstadoPartida(state);
		return partida;
	}

	public Partida terminarRetirda(Partida game, Jogador player) {
		// TODO Auto-generated method stub
		return null;
	}

	private Jogador getJogadorDaVez() {
		if (jogador1.isDaVez()) {
			return jogador1;
		} else {
			return jogador2;
		}
	}

	private void atualizaVez(Partida partida) {
		if (partida.getJogador1().isDaVez()) {
			partida.getJogador1().setDaVez(false);
			partida.getJogador2().setDaVez(true);
		} else {
			partida.getJogador1().setDaVez(true);
			partida.getJogador2().setDaVez(false);
		}
	}
	
	private void avaliarFinalizada() {
		if (vencedor) {
			finalizada = true;
		} else {
			if(abelhaCercada(jogador1.getCor())) {
				finalizada = true;
				
			}else if(abelhaCercada(jogador2.getCor())){
				finalizada = true;
			}
			
		}
	}
}
