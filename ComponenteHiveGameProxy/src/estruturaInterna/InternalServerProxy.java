package estruturaInterna;

import ClassesComuns.CommunicationContainer;
import ClassesComuns.Estado;
import InterfacesComuns.InterfaceProxyManager;
import interfaceDoComponente.PortServer;
import interfaceDoComponente.PortServerOutbox;

public class InternalServerProxy implements InterfaceProxyManager {
	
	protected PortServer portServer;
	
	public void setPortServer (PortServer aPortServer) {
		portServer = aPortServer;
	}

	@Override
	public void selfDestruct() {
		this.setPortServer(null);
	}

	@Override
	public void comunicar(CommunicationContainer notification) {
		PortServerOutbox outbox = (PortServerOutbox) portServer.getOutbox();
		outbox.enviar(notification);
	}

	@Override
	public boolean conectar(String arg0) {
		PortServerOutbox outbox = (PortServerOutbox) portServer.getOutbox();
		outbox.conectar(arg0);
		return true;
	}

	@Override
	public void enviarMensagem(String arg0) {
		PortServerOutbox outbox = (PortServerOutbox) portServer.getOutbox();
		outbox.enviarMensagem(arg0);
	}

	@Override
	public void updateState(Estado arg0) {
		PortServerOutbox outbox = (PortServerOutbox) portServer.getOutbox();
		outbox.enviar(arg0);
	}

	public void disconnectNG() {
		PortServerOutbox outbox = (PortServerOutbox) portServer.getOutbox();
		outbox.desconectar();		//		disconnectNG()
	}

}
