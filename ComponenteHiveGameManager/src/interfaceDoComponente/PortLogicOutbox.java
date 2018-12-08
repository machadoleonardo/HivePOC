package interfaceDoComponente;

import ClassesComuns.Jogador;
import ClassesComuns.Lance;
import ClassesComuns.Partida;
import InterfacesComuns.InterfaceLogic;
import cip.StandardPortOutbox;

public class PortLogicOutbox extends StandardPortOutbox implements InterfaceLogic {


	@Override
	public Partida setInitialState(Jogador player1, Jogador player2) {
		return ((InterfaceLogic) externalPort).setInitialState(player1, player2);
	}

	@Override
	public Partida tratarLance(Partida game, Lance lance) {
		return ((InterfaceLogic) externalPort).tratarLance(game, lance);
	}

	@Override
	public Partida terminarRetirda(Partida game, Jogador player) {
		return ((InterfaceLogic) externalPort).terminarRetirda(game, player);
	}

}