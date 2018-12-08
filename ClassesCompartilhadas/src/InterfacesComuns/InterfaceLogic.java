package InterfacesComuns;

import ClassesComuns.Jogador;
import ClassesComuns.Lance;
import ClassesComuns.Partida;


public interface InterfaceLogic {

	
	
	public Partida setInitialState(Jogador player1, Jogador player2);
	
	public Partida tratarLance(Partida game, Lance lance);
	
	public Partida terminarRetirda(Partida game, Jogador player);

}