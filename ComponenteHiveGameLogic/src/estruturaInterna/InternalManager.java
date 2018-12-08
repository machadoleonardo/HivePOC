package estruturaInterna;

import java.util.*;

import ClassesComuns.Jogador;
import ClassesComuns.Lance;
import ClassesComuns.Partida;
import cip.InterfacePort;
import interfaceDoComponente.PortLogic;
import interfaceDoComponente.PortManagerProxy;

public class InternalManager {

	protected boolean conectado;
	protected InterfacePort logicPort;
	protected InterfacePort proxyPorts;
	protected Jogador availablePlayers;
	protected Jogador unavailablePlayers;
	protected Collection<Partida> games;

	/**
	 * 
	 * @param playerId
	 * @param portId
	 */
	public Jogador conectar(String playerId, String portId) {
		// TODO - implement InternalManager.conectar
		throw new UnsupportedOperationException();
	}

	public String getPortManagerId() {
		// TODO - implement InternalManager.getPortManagerId
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param portId
	 */
	public PortManagerProxy getProxyPort(String portId) {
		// TODO - implement InternalManager.getProxyPort
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param portId
	 * @param playerId
	 */
	public void desconectar(String portId, String playerId) {
		// TODO - implement InternalManager.desconectar
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param player
	 */
	public void removeAvailablePlayer(Jogador player) {
		// TODO - implement InternalManager.removeAvailablePlayer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param port
	 */
	public void removeProxyPort(PortManagerProxy port) {
		// TODO - implement InternalManager.removeProxyPort
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param portId
	 * @param player
	 */
	public void iniciar(String portId, Jogador player) {
		// TODO - implement InternalManager.iniciar
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param player
	 */
	public void addUnavailablePlayer(Jogador player) {
		// TODO - implement InternalManager.addUnavailablePlayer
		throw new UnsupportedOperationException();
	}

	public Jogador getAnyAvailablePlayer() {
		// TODO - implement InternalManager.getAnyAvailablePlayer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param playerId
	 */
	public Jogador getAvailablePlayer(String playerId) {
		// TODO - implement InternalManager.getAvailablePlayer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param player
	 */
	public InterfacePort recuperatePlayerPort(Jogador player) {
		// TODO - implement InternalManager.recuperatePlayerPort
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param game
	 * @param newGame
	 */
	public void updateGame(Partida game, Partida newGame) {
		// TODO - implement InternalManager.updateGame
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param player
	 */
	public void addAvailablePlayer(Jogador player) {
		// TODO - implement InternalManager.addAvailablePlayer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param port
	 */
	public void addPort(InterfacePort port) {
		// TODO - implement InternalManager.addPort
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param portId
	 * @param move
	 */
	public void makeMove(String portId, Lance move) {
		// TODO - implement InternalManager.makeMove
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param player
	 */
	public Partida recuperateGame(Jogador player) {
		// TODO - implement InternalManager.recuperateGame
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param player
	 */
	public void makeAvailable(Jogador player) {
		// TODO - implement InternalManager.makeAvailable
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param game
	 */
	public void discardGame(Partida game) {
		// TODO - implement InternalManager.discardGame
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param player
	 */
	public void removeUnavailablePlayer(Jogador player) {
		// TODO - implement InternalManager.removeUnavailablePlayer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param portId
	 * @param playerId
	 */
	public void giveUp(String portId, Jogador playerId) {
		// TODO - implement InternalManager.giveUp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param port
	 */
	public void addProxyPort(PortManagerProxy port) {
		// TODO - implement InternalManager.addProxyPort
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param port
	 */
	public void setLogicPort(PortLogic port) {
		// TODO - implement InternalManager.setLogicPort
		throw new UnsupportedOperationException();
	}

}