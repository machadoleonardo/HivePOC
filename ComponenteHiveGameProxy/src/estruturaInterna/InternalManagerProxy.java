package estruturaInterna;



import ClassesComuns.CommunicationContainer;
import ClassesComuns.CommunicationKind;
import ClassesComuns.Jogador;
import ClassesComuns.Lance;
import InterfacesComuns.InterfaceProxyServer;
import InterfacesComuns.Throw;
import interfaceDoComponente.PortManager2;
import interfaceDoComponente.PortManager2Outbox;
import interfaceDoComponente.PortServer;
import interfaceDoComponente.PortServerOutbox;

public class InternalManagerProxy implements InterfaceProxyServer {
	
	protected PortManager2 portManager;
	protected PortServer portServer;
	
	public void setPortManager (PortManager2 aPortManager) {
		portManager = aPortManager;
	}
	
	public void setPortServer (PortServer aPortServer) {
		portServer = aPortServer;
	}
	
	public void selfDestruct() {
		this.setPortManager(null);
		this.setPortServer(null);
	}

	@Override
	public void receber(Throw arg0) {
		PortManager2Outbox outbox = (PortManager2Outbox) portManager.getOutbox();
		if (arg0 instanceof Lance) {
			outbox.makeMove((Lance) arg0);
		} else {
			if (arg0 instanceof Jogador) {
				outbox.iniciar((Jogador) arg0);
			} else {
				String content = ((CommunicationContainer) arg0).getContent();
				CommunicationKind kind = ((CommunicationContainer) arg0).getKind();
				if (kind == CommunicationKind.giveUpRequest) {
					outbox.desistir(content);
				} else {
					if (kind == CommunicationKind.disconnectRequest) {
						outbox.desconectar(content);
					} else {
						if (kind == CommunicationKind.playerIdRequest) outbox.getJogadoresConectados();
					}
				}
			}
		}
		
	}

	@Override
	public void receberMensagem(String message) {
		PortManager2Outbox outbox = (PortManager2Outbox) portManager.getOutbox();
		outbox.enviarMensagem(message);		
	}

	@Override
	public void iniciar(int order) {
		PortManager2Outbox managerOutbox = (PortManager2Outbox) portManager.getOutbox();
		PortServerOutbox serverOutbox = (PortServerOutbox) portServer.getOutbox();
		String playerId = serverOutbox.getIdOponente();
		managerOutbox.conectar(playerId);
	}

}
