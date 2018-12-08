package ClassesComuns;

public class Partida {
	
	protected int gameId = 0;
	protected State gameState;
	protected int gameCode = 1;
	protected Jogador jogador1;
	protected Jogador Jogador2;
	
	// gameCode
	// 1 - game not started
	// 2 - game finished with quitting
	// 3 - irregular move (game in progress)
	// 4 - next player (game in progress)
	// 5 - game with winner
	// 6 - game tied
	
	public void setGameId (int arg) {
		gameId = arg;
	}
	
	public int getGameId () {
		return(gameId);
	}
	
	public void setGameState (State arg) {
		gameState = arg;
	}
	
	public State getGameState () {
		return(gameState);
	}
	
	public void setGameCode (int arg) {
		gameCode = arg;
	}
	
	public int getGameCode () {
		return(gameCode);
	}


	public void setPlayer1 (Jogador arg) {
		jogador1 = arg;
	}
	
	public Jogador getPlayer1 () {
		return(jogador1);
	}

	public void setPlayer2 (Jogador arg) {
		Jogador2 = arg;
	}
	
	public Jogador getPlayer2 () {
		return(Jogador2);
	}

	public Jogador getOpponent (Jogador arg) {
		if (arg == jogador1) return Jogador2;
		if (arg == Jogador2) return jogador1;
		return null;
	}


}
