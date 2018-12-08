package interfaceDoComponente;

import ClassesComuns.Jogador;
import ClassesComuns.Lance;
import InterfacesComuns.InterfaceManagerProxy;
import cip.InterfacePort;
import estruturaInterna.InternalManager;

public class PortManagerProxy extends InterfacePort implements InterfaceManagerProxy {

	protected Jogador player;
	protected InternalManager internalReference;
	
	public PortManagerProxy(String argId) {
		id = argId;
	}
	

	public void initialize() {
		outbox = new PortManagerProxyOutbox();
	}


	public void setPlayer (Jogador aPlayer) {
		player = aPlayer;
	}
	
	public Jogador getPlayer () {
		return player;
	}

	public boolean isYourId (String anId) {
		return (anId.equals(id));
	}
	
	public void setInternalReference (InternalManager argB) {
		internalReference = argB;
	}


	@Override
	public void enviarMensagem(String message) {
		internalReference.enviarMensagem(id, message);
		
	}


	@Override
	public void conectar(String playerId) {
		internalReference.conectar(id, playerId);
		
	}


	@Override
	public void getJogadoresConectados() {
		internalReference.getJogadoresConectados(id);
		
	}


	@Override
	public void desconectar(String playerId) {
		internalReference.desconectar(id, playerId);
		
	}


	@Override
	public void desistir(String playerId) {
		internalReference.desistir(id, playerId);
		
	}


	@Override
	public void iniciar(Jogador player) {
		internalReference.iniciar(id, player);
		
	}


	@Override
	public void makeMove(Lance move) {
		internalReference.makeMove(id, move);
		
	}

	
}