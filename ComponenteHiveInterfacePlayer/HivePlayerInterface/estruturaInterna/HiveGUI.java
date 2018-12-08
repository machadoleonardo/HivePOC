package estruturaInterna;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JOptionPane;

import ClassesComuns.AtorJogador;
import ClassesComuns.CommunicationContainer;
import ClassesComuns.CommunicationKind;
import ClassesComuns.State;
import InterfacesComuns.InterfacePlayerProxy;
import interfaceDoComponente.PortPlayerProxy;
import interfaceDoComponente.PortPlayerProxyOutbox;

public class HiveGUI extends AtorJogador implements InterfacePlayerProxy {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected PortPlayerProxy playerPort;

	protected enum PlayerStateValue {
		disconnected, connected, playing
	}

	protected PlayerStateValue playerState = PlayerStateValue.disconnected;

	public HiveGUI() throws HeadlessException {
		super();
		initialize();
	}

	public HiveGUI(GraphicsConfiguration arg0) {
		super(arg0);
		initialize();
	}

	public HiveGUI(String arg0) throws HeadlessException {
		super(arg0);
		initialize();
	}

	public HiveGUI(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateState(State state) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receberMensagem(String message) {
		this.comunicarMensagem("Message of the opponent: " + message);

	}

	@Override
	public void comunicar(CommunicationContainer notification) {
		String content = notification.getContent();
		CommunicationKind kind = notification.getKind();
		String title = "";
		switch (kind) {
		case connectionNotification:
			title = "Connection notification: ";
			if (content.equals("Server not connected")) {
				playerState = PlayerStateValue.disconnected;
			}
			if (content.equals("Connected to TTT Server")) {
				playerState = PlayerStateValue.connected;
			}
			break;
		case disconnectionNotification:
			title = "Disconnection notification: ";
			playerState = PlayerStateValue.disconnected;
			break;
		case irregularMoveNotification:
			title = "Irregular move notification: ";
			break;
		case connectedPlayerNotification:
			title = "Connected players: ";
			break;
		default:
			break;
		}
		this.comunicarMensagem(title + content);

	}
	
	public void comunicarMensagem(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public PortPlayerProxy getPlayerPort() {
		return playerPort;
	}

	public void setPlayerPort(PortPlayerProxy playerPort) {
		this.playerPort = playerPort;
	}
	
	protected void enviarMensagem() {
		if (playerState == PlayerStateValue.playing) {
			PortPlayerProxyOutbox portOutbox = (PortPlayerProxyOutbox) playerPort.getOutbox();
			String message = JOptionPane.showInputDialog(this, "What is the message to your opponent?");
			portOutbox.enviarMensagem(message);
		} else {
			this.comunicarMensagem("There is no game in progress");
		}
	}

}
