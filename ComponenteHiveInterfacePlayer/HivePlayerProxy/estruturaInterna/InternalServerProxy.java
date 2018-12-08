package estruturaInterna;


import ClassesComuns.CommunicationContainer;
import ClassesComuns.CommunicationKind;
import ClassesComuns.Jogador;
import ClassesComuns.Lance;
import InterfacesComuns.InterfaceProxyPlayer;
import interfaceDoComponente.PortPlayer;
import interfaceDoComponente.PortPlayerOutbox;
import interfaceDoComponente.PortServer;
import interfaceDoComponente.PortServerOutbox;

public class InternalServerProxy implements InterfaceProxyPlayer {
	
	protected PortServer portServer;
	protected PortPlayer portPlayer;
	
	public void setPortServer (PortServer aPortServer) {
		portServer = aPortServer;
	}
	
	public void setPortPlayer (PortPlayer aPortPlayer) {
		portPlayer = aPortPlayer;
	}

	@Override
	public void confirmarConexao() {
		boolean serverConnected = portServer.getConnected();
		if (! serverConnected) {
			PortPlayerOutbox playerOutbox = (PortPlayerOutbox) portPlayer.getOutbox();
			PortServerOutbox serverOutbox = (PortServerOutbox) portServer.getOutbox();
			serverOutbox.disconnect();		//		disconnectNG()
			CommunicationContainer result = new CommunicationContainer();
			result.setKind(CommunicationKind.connectionNotification);
			result.setContent("Server not connected");
			playerOutbox.comunicar(result);
		}		
	}

	@Override
	public boolean conectar(String arg0) {
		PortServerOutbox serverOutbox = (PortServerOutbox) portServer.getOutbox();
		serverOutbox.conectar(arg0);
		serverOutbox.iniciar();
		return portServer.getConnected();
	}

	@Override
	public void desconectar(String arg0) {
		PortServerOutbox serverOutbox = (PortServerOutbox) portServer.getOutbox();
		CommunicationContainer  request = new CommunicationContainer();
		request.setKind(CommunicationKind.disconnectRequest);
		request.setContent(arg0);		
		portServer.setConnected(false);
		serverOutbox.enviar(request);	
	}

	@Override
	public void getJogadoresConectados() {
		PortServerOutbox serverOutbox = (PortServerOutbox) portServer.getOutbox();
		CommunicationContainer  request = new CommunicationContainer();
		request.setKind(CommunicationKind.playerIdRequest);
		request.setContent("");
		serverOutbox.enviar(request);	
	}

	@Override
	public void desistir(String arg0) {
		PortServerOutbox serverOutbox = (PortServerOutbox) portServer.getOutbox();
		CommunicationContainer  request = new CommunicationContainer();
		request.setKind(CommunicationKind.giveUpRequest);
		request.setContent(arg0);
		serverOutbox.enviar(request);	
	}

	@Override
	public void lance(Lance arg0) {
		PortServerOutbox serverOutbox = (PortServerOutbox) portServer.getOutbox();
		serverOutbox.enviar(arg0);	
	}

	@Override
	public void enviarMensagem(String arg0) {
		PortServerOutbox serverOutbox = (PortServerOutbox) portServer.getOutbox();
		serverOutbox.enviarMensagem(arg0);	
	}

	@Override
	public void iniciar(Jogador arg0) {
		PortServerOutbox serverOutbox = (PortServerOutbox) portServer.getOutbox();
		serverOutbox.enviar(arg0);	
	}

	
}
