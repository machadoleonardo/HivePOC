package interfaceDoComponente;

import ClassesComuns.Colmeia;
import ClassesComuns.Jogador;
import ClassesComuns.Lance;
import ClassesComuns.Partida;
import InterfacesComuns.InterfaceLogic;
import cip.InterfacePort;

public class PortManager extends InterfacePort implements InterfaceLogic {
	
	protected Colmeia internalReference;

	public PortManager(String argId) {
		id = argId;
	}
	
	public void setInternalReference(Colmeia colmenia) {
		internalReference = colmenia;
	}

	@Override
		public void initialize() {	// empty		
	}


	@Override
	public Partida terminarRetirda(Partida game, Jogador player) {
		Partida aGame;
		aGame = internalReference.terminarRetirda(game, player);
		return aGame;
	}


	@Override
	public Partida setInitialState(Jogador player1, Jogador player2) {
		Partida aGame;
		aGame = internalReference.setInitialState(player1, player2);
		return aGame;
	}


	@Override
	public Partida tratarLance(Partida game, Lance lance) {
		Partida aGame;
		aGame = internalReference.tratarLance(game, lance);
		return aGame;
	}


}
