package ClassesComuns;

public class Partida {

	protected int idPartida = 0;
	protected Estado estadoPartida;
	protected int codPartida = 1;
	protected int rodada;
	protected boolean partidaEmAndamento;

	protected Jogador jogador1;
	protected Jogador jogador2;

	// codPartida
	// 1 - jogo não iniciado
	// 2 - jogo terminado com desistir
	// 3 - movimento irregular (jogo em andamento)
	// 4 - próximo jogador (jogo em andamento)
	// 5 - jogo com vencedor
	// 6 - jogo empatado

	public void setIdPartida(int arg) {
		idPartida = arg;
	}

	public int getIdPartida() {
		return (idPartida);
	}

	public void setEstadoPartida(Estado arg) {
		estadoPartida = arg;
	}

	public Estado getEstadoPartida() {
		return (estadoPartida);
	}

	public void setCodPartida(int arg) {
		codPartida = arg;
	}

	public int getCodPartida() {
		return (codPartida);
	}

	public void setJogador1(Jogador arg) {
		jogador1 = arg;
	}

	public Jogador getJogador1() {
		return (jogador1);
	}

	public void setJogador2(Jogador arg) {
		jogador2 = arg;
	}

	public Jogador getJogador2() {
		return (jogador2);
	}

	public Jogador getOponente(Jogador arg) {
		if (arg == jogador1)
			return jogador2;
		if (arg == jogador2)
			return jogador1;
		return null;
	}

	public void setRodada(int rodada) {
		this.rodada = rodada;
	}

	public int getRodada() {
		return rodada;
	}

	public boolean isPartidaEmAndamento() {
		return partidaEmAndamento;
	}

	public void setPartidaEmAndamento(boolean partidaEmAndamento) {
		this.partidaEmAndamento = partidaEmAndamento;
	}

	public Jogador getOponente(String jogadorDoLance) {
		if (jogadorDoLance.equals(jogador1.getApelido()))
			return jogador2;
		if (jogadorDoLance.equals(jogador2.getApelido()))
			return jogador1;
		return null;
	}

	public String getNotif() {
		return notif;
	}

	public void setNotif(String notif) {
		this.notif = notif;
	}

	public Jogador getJogadorDaVez(String jogadorDoLance) {
		if (jogadorDoLance.equals(jogador1.getApelido()))
			return jogador1;
		if (jogadorDoLance.equals(jogador2.getApelido()))
			return jogador2;
		return null;
	}

}
