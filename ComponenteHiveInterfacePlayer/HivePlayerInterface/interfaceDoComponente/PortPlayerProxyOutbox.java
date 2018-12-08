package interfaceDoComponente;

import ClassesComuns.Jogador;
import ClassesComuns.Lance;
import InterfacesComuns.InterfaceProxyPlayer;
import cip.StandardPortOutbox;
import interfaceDoComponente.PortPlayer;

public class PortPlayerProxyOutbox extends StandardPortOutbox implements InterfaceProxyPlayer {

	@Override
	public void confirmarConexao() {
		((PortPlayer) externalPort).confirmarConexao();
	}

	@Override
	public boolean conectar(String arg0) {
		return ((PortPlayer) externalPort).conectar(arg0);
	}

	@Override
	public void desconectar(String arg0) {
		((PortPlayer) externalPort).desconectar(arg0);
	}

	@Override
	public void getJogadoresConectados() {
		((PortPlayer) externalPort).getJogadoresConectados();
	}

	@Override
	public void desistir(String arg0) {
		((PortPlayer) externalPort).desistir(arg0);
	}

	@Override
	public void lance(Lance arg0) {
		((PortPlayer) externalPort).lance(arg0);
	}

	@Override
	public void enviarMensagem(String arg0) {
		((PortPlayer) externalPort).enviarMensagem(arg0);
	}

	@Override
	public void iniciar(Jogador arg0) {
		((PortPlayer) externalPort).iniciar(arg0);
	}

}
