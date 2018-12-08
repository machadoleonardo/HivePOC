package interfaceDoComponente;

import ClassesComuns.Jogador;
import ClassesComuns.Lance;
import InterfacesComuns.InterfaceManagerProxy;
import cip.StandardPortOutbox;

public class PortManager2Outbox extends StandardPortOutbox implements InterfaceManagerProxy {
	
	@Override
	public void conectar(String playerId) {
		((PortManagerProxy) externalPort).conectar(playerId);
	}

	@Override
	public void desconectar(String arg0) {
		((PortManagerProxy) externalPort).desconectar(arg0);
	}

	@Override
	public void getJogadoresConectados() {
		((PortManagerProxy) externalPort).getJogadoresConectados();
	}

	@Override
	public void desistir(String playerId) {
		((PortManagerProxy) externalPort).desistir(playerId);
	}

	@Override
	public void makeMove(Lance lance) {
		((PortManagerProxy) externalPort).makeMove(lance);
	}

	@Override
	public void enviarMensagem(String message) {
		((PortManagerProxy) externalPort).enviarMensagem(message);
	}

	@Override
	public void iniciar(Jogador player) {
		((PortManagerProxy) externalPort).iniciar(player);
	}

}
