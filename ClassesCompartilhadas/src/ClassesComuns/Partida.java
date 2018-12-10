package ClassesComuns;

public class Partida {
	
	protected int idPartida = 0;
	protected Estado estadoPartida;
	protected int codPartida = 1;
	protected Jogador jogador1;
	protected Jogador jogador2;
	
	// codPartida
	// 1 - jogo n�o iniciado
	// 2 - jogo terminado com desistir
	// 3 - movimento irregular (jogo em andamento)
	// 4 - pr�ximo jogador (jogo em andamento)
	// 5 - jogo com vencedor
	// 6 - jogo empatado
	
	public void setIdPartida (int arg) {
		idPartida = arg;
	}
	
	public int getIdPartida () {
		return(idPartida);
	}
	
	public void setEstadoPartida (Estado arg) {
		estadoPartida = arg;
	}
	
	public Estado getEstadoPartida () {
		return(estadoPartida);
	}
	
	public void setCodPartida (int arg) {
		codPartida = arg;
	}
	
	public int getCodPartida () {
		return(codPartida);
	}


	public void setJogador1 (Jogador arg) {
		jogador1 = arg;
	}
	
	public Jogador getJogador1 () {
		return(jogador1);
	}

	public void setJogador2 (Jogador arg) {
		jogador2 = arg;
	}
	
	public Jogador getJogador2 () {
		return(jogador2);
	}

	public Jogador getOponente (Jogador arg) {
		if (arg == jogador1) return jogador2;
		if (arg == jogador2) return jogador1;
		return null;
	}


}
