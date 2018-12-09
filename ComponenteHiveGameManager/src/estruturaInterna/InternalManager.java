package estruturaInterna;

import java.util.*;

import ClassesComuns.CommunicationContainer;
import ClassesComuns.CommunicationKind;
import ClassesComuns.Jogador;
import ClassesComuns.Lance;
import ClassesComuns.Partida;
import ClassesComuns.State;
import cip.InterfacePort;
import interfaceDoComponente.HiveManagerProxy;
import interfaceDoComponente.PortLogic;
import interfaceDoComponente.PortLogicOutbox;
import interfaceDoComponente.PortManager2;
import interfaceDoComponente.PortManagerProxy;
import interfaceDoComponente.PortManagerProxyOutbox;
import jdk.nashorn.internal.scripts.JO;

public class InternalManager {

	protected boolean conectado;
	protected PortLogic logicPort;
	protected ArrayList<PortManagerProxy> proxyPorts = new ArrayList<PortManagerProxy>();
	protected ArrayList<Jogador> availablePlayers = new ArrayList<>();
	protected ArrayList<Jogador> unavailablePlayers = new ArrayList<>();
	protected ArrayList<Partida> games = new ArrayList<>();

	public void iniciar(String portId, Jogador player) {
		PortManagerProxy proxy1 = this.getProxyPort(portId);
		Jogador player2;
		Jogador player1 = proxy1.getPlayer();
		boolean jogoIniciara = false;
		availablePlayers.remove(player1);
		unavailablePlayers.add(player1);
		String argName = player.getApelido();
		String player1Name = player1.getApelido();
		if (argName.equals(player1Name)) {
			player2 = this.getAnyAvailablePlayer();
			if (player2 == null) {
				CommunicationContainer notification = new CommunicationContainer();
				notification.setKind(CommunicationKind.connectionNotification);
				notification.setContent("Não há jogadores disponíveis para jogar");
				PortManagerProxy firstProxy = this.getProxyPort(portId);
				firstProxy.setPlayer(player);
				PortManagerProxyOutbox firstOutbox = (PortManagerProxyOutbox) firstProxy.getOutbox();
				firstOutbox.comunicar(notification);
				availablePlayers.add(player1);
				unavailablePlayers.remove(player1);
			} else {
				availablePlayers.remove(player2);
				unavailablePlayers.add(player2);
				jogoIniciara = true;
			}
		} else {
			player2 = this.getAvailablePlayer(argName);
			if (player2 == null)
				player2 = this.getAnyAvailablePlayer();
			if (player2 == null) {
				CommunicationContainer notification = new CommunicationContainer();
				notification.setKind(CommunicationKind.connectionNotification);
				notification.setContent("Não há jogadores disponíveis para jogar");
				PortManagerProxy firstProxy = this.getProxyPort(portId);
				firstProxy.setPlayer(player);
				PortManagerProxyOutbox firstOutbox = (PortManagerProxyOutbox) firstProxy.getOutbox();
				firstOutbox.comunicar(notification);
				availablePlayers.add(player1);
				unavailablePlayers.remove(player1);
			} else {
				availablePlayers.remove(player2);
				unavailablePlayers.add(player2);
				jogoIniciara = true;
			}
		}
		if (jogoIniciara) {
			int player1Symbol = player.getOrdem();
			player1.setOrdem(player1Symbol);
			if (player1Symbol == 1) {
				player2.setOrdem(2);
			} else {
				player2.setOrdem(1);
			}
			boolean player1Starts = player.isDaVez();
			if (player1Starts) {
				player1.setDaVez(true);
				player2.setDaVez(false);
			} else {
				player2.setDaVez(true);
				player1.setDaVez(false);
			}
			PortLogicOutbox logicOutbox = (PortLogicOutbox) logicPort.getOutbox();
			Partida game = logicOutbox.setInitialState(player1, player2);
			game.setPlayer1(player1);
			game.setPlayer2(player2);
			games.add(game);
			State state = game.getGameState();

			PortManagerProxy proxy2 = (PortManagerProxy) this.recuperatePlayerPort(player2);
			PortManagerProxyOutbox outbox2 = (PortManagerProxyOutbox) proxy2.getOutbox();
			outbox2.updateState(state);
			PortManagerProxyOutbox outbox1 = (PortManagerProxyOutbox) proxy1.getOutbox();
			outbox1.updateState(state);
		}

	}

	public String getPortManagerId() {
		int number = proxyPorts.size() + 1;
		int portNumber = this.evaluatePortManagerId(number);
		return ("proxy" + String.valueOf(portNumber));
	}

	private int evaluatePortManagerId(int number) {
		PortManagerProxy existentPort = this.getProxyPort("proxy" + String.valueOf(number));
		if (existentPort == null) {
			return number;
		} else {
			return this.evaluatePortManagerId(number + 1);
		}
	}

	/**
	 * 
	 * @param portId
	 */
	private PortManagerProxy getProxyPort(String portId) {
		for (int i = 0; i < proxyPorts.size(); i++) {
			PortManagerProxy aPort = proxyPorts.get(i);
			if (aPort.isYourId(portId))
				return aPort;
		}
		return null;
	}

	/**
	 * 
	 * @param portId
	 * @param playerId
	 */
	public void desconectar(String portId, String playerId) {
		PortManagerProxy port = this.getProxyPort(portId);
		CommunicationContainer notification = new CommunicationContainer();
		notification.setKind(CommunicationKind.disconnectionNotification);
		notification.setContent("Desconectado do servidor Hive");
		PortManagerProxyOutbox outbox = (PortManagerProxyOutbox) port.getOutbox();
		outbox.comunicar(notification);
		Jogador player = port.getPlayer();
		port.setPlayer(null);
		availablePlayers.remove(player);
		proxyPorts.remove(port);
		outbox.selfDestruct();
		port.desconectar();
	}

	/**
	 * 
	 * @param player
	 */
	public void removeAvailablePlayer(Jogador player) {
		availablePlayers.remove(player);
	}

	/**
	 * 
	 * @param port
	 */
	public void removeProxyPort(PortManagerProxy port) {
		proxyPorts.remove(port);
	}

	/**
	 * 
	 * @param player
	 */
	public void addUnavailablePlayer(Jogador player) {
		unavailablePlayers.add(player);
	}

	public Jogador getAnyAvailablePlayer() {
		if (availablePlayers.isEmpty()) {
			return null;
		} else {
			return availablePlayers.get(0);
		}
	}

	/**
	 * 
	 * @param playerId
	 */
	private Jogador getAvailablePlayer(String playerId) {
		if (!availablePlayers.isEmpty()) {
			for (Jogador jogador : availablePlayers) {
				if (jogador.getApelido().equals(playerId))
					return jogador;

			}
		}
		return null;
	}

	/**
	 * 
	 * @param player
	 */
	private InterfacePort recuperatePlayerPort(Jogador player) {
		for (int i = 0; i < proxyPorts.size(); i++) {
			if (proxyPorts.get(i).getPlayer() == player)
				return (proxyPorts.get(i));
		}

		return null;
	}

	/**
	 * 
	 * @param game
	 * @param newGame
	 */
	private void updateGame(Partida game, Partida newGame) {
		game.setGameState(newGame.getGameState());
		game.setGameCode(newGame.getGameCode());
		Jogador player1 = game.getPlayer1();
		Jogador player2 = game.getPlayer2();
		player1.setOrdem(newGame.getPlayer1().getOrdem());
		player2.setOrdem(newGame.getPlayer2().getOrdem());
		player1.setVencedor(newGame.getPlayer1().isVencedor());
		player2.setVencedor(newGame.getPlayer2().isVencedor());
	}

	/**
	 * 
	 * @param player
	 */
	private void addAvailablePlayer(Jogador player) {
		availablePlayers.add(player);
	}

	public void makeMove(String portId, Lance lance) {
		PortManagerProxy proxy1 = this.getProxyPort(portId);
		Jogador player1 = proxy1.getPlayer();
		Partida game = this.recuperateGame(player1);
		Jogador player2 = game.getOpponent(player1);
		PortLogicOutbox logicOutbox = (PortLogicOutbox) logicPort.getOutbox();
		Partida newGame = logicOutbox.tratarLance(game, lance);
		int returnedCode = newGame.getGameCode();
		if (returnedCode == 3) {
			CommunicationContainer result = new CommunicationContainer();
			result.setKind(CommunicationKind.connectedPlayerNotification);
			result.setContent("Irregular move");
			((PortManagerProxyOutbox) proxy1.getOutbox()).comunicar(result);
		} else {
			this.updateGame(game, newGame);
			State state = game.getGameState();
			((PortManagerProxyOutbox) proxy1.getOutbox()).updateState(state);
			PortManagerProxy proxy2 = (PortManagerProxy) this.recuperatePlayerPort(player2);
			PortManagerProxyOutbox outbox2 = (PortManagerProxyOutbox) proxy2.getOutbox();
			outbox2.updateState(state);
			if (returnedCode == 5 || returnedCode == 6) {
				this.makeAvailable(player2);
				this.makeAvailable(player1);
				this.discardGame(game);
			}
		}
	}

	/**
	 * 
	 * @param player
	 */
	private Partida recuperateGame(Jogador player) {
		for (int i = 0; i < games.size(); i++) {
			if ((games.get(i).getPlayer1() == player) || (games.get(i).getPlayer2() == player)) {
				return (games.get(i));
			}
		}
		return null;
	}

	/**
	 * 
	 * @param player
	 */
	private void makeAvailable(Jogador player) {
		player.setOrdem(1);
		player.setDaVez(false);
		player.setVencedor(false);
		unavailablePlayers.remove(player);
		availablePlayers.add(player);
	}

	/**
	 * 
	 * @param game
	 */
	private void discardGame(Partida game) {
		game.setPlayer1(null);
		game.setPlayer2(null);
		games.remove(game);
	}

	/**
	 * 
	 * @param player
	 */
	public void removeUnavailablePlayer(Jogador player) {
		unavailablePlayers.remove(player);
	}

	/**
	 * 
	 * @param portId
	 * @param playerId
	 */
	public void desistir(String portId, String playerId) {
		PortManagerProxy proxy1 = this.getProxyPort(portId);
		Jogador player1 = proxy1.getPlayer();
		Partida game = this.recuperateGame(player1);
		PortLogicOutbox logicOutbox = (PortLogicOutbox) logicPort.getOutbox();
		Partida newGame = logicOutbox.terminarRetirda(game, player1);
		this.updateGame(game, newGame);
		State state = game.getGameState();
		PortManagerProxyOutbox outbox1 = (PortManagerProxyOutbox) proxy1.getOutbox();
		outbox1.updateState(state);
		Jogador player2 = game.getOpponent(player1);
		PortManagerProxy proxy2 = (PortManagerProxy) this.recuperatePlayerPort(player2);
		PortManagerProxyOutbox outbox2 = (PortManagerProxyOutbox) proxy2.getOutbox();
		outbox2.updateState(state);
		this.makeAvailable(player2);
		this.makeAvailable(player1);
		this.discardGame(game);
	}

	/**
	 * 
	 * @param port
	 */
	public void addProxyPort(PortManagerProxy port) {
		proxyPorts.add(port);
	}

	/**
	 * 
	 * @param port
	 */
	public void setLogicPort(PortLogic port) {
		logicPort = port;

	}

	public boolean iniciarServidor() {
		PortManagerProxyOutbox anOutbox = (PortManagerProxyOutbox) proxyPorts.get(0).getOutbox();
		boolean result = anOutbox.conectar("proxy1");
		return result;
	}

	public void conectar(String portId, String arg0) {
		Jogador player = new Jogador(1);
		String playerId;
		if (arg0.isEmpty()) {
			playerId = "player_" + portId;
		} else {
			playerId = arg0;
		}
		player.setApelido(playerId);
		availablePlayers.add(player);
		CommunicationContainer notification = new CommunicationContainer();
		notification.setKind(CommunicationKind.connectionNotification);
		notification.setContent("Conectado ao Servidor Hive");
		PortManagerProxy firstProxy = this.getProxyPort(portId);
		firstProxy.setPlayer(player);
		PortManagerProxyOutbox firstOutbox = (PortManagerProxyOutbox) firstProxy.getOutbox();
		firstOutbox.comunicar(notification);
		HiveManagerProxy newHiveManagerProxy = new HiveManagerProxy("HiveManagerProxy");
		newHiveManagerProxy.initialize();
		PortManager2 otherComponentPort = (PortManager2) newHiveManagerProxy.getPort("manager");
		String newPortName = this.getPortManagerId();
		PortManagerProxy newProxy = new PortManagerProxy(newPortName);
		newProxy.initialize();
		newProxy.setInternalReference(this);
		proxyPorts.add(newProxy);
		PortManagerProxyOutbox newOutbox = (PortManagerProxyOutbox) newProxy.getOutbox();
		newHiveManagerProxy.conectar(newProxy, "manager");
		newProxy.conectar(otherComponentPort);
		newOutbox.conectar(newPortName);
	}

	public void getJogadoresConectados(String portId) {
		CommunicationContainer result = new CommunicationContainer();
		result.setKind(CommunicationKind.connectedPlayerNotification);
		if (availablePlayers.size() > 0) {
			String content = "";
			for (int i = 0; i < availablePlayers.size(); i++) {
				Jogador aPlayer = availablePlayers.get(i);
				String name = aPlayer.getApelido();
				content = content + name + ", ";
			}
			result.setContent(content);
			PortManagerProxy aPort = this.getProxyPort(portId);
			((PortManagerProxyOutbox) aPort.getOutbox()).comunicar(result);
		}
	}

	public void enviarMensagem(String portId, String message) {
		PortManagerProxy proxy1 = this.getProxyPort(portId);
		Jogador player1 = proxy1.getPlayer();
		Partida game = this.recuperateGame(player1);
		Jogador player2 = game.getOpponent(player1);
		PortManagerProxy proxy2 = (PortManagerProxy) this.recuperatePlayerPort(player2);
		PortManagerProxyOutbox outbox2 = (PortManagerProxyOutbox) proxy2.getOutbox();
		outbox2.enviarMensagem(message);
	}
}