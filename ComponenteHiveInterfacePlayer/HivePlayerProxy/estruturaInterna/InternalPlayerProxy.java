package estruturaInterna;

import ClassesComuns.CommunicationContainer;
import ClassesComuns.CommunicationKind;
import ClassesComuns.State;
import InterfacesComuns.InterfaceProxyServer;
import InterfacesComuns.Throw;
import interfaceDoComponente.PortPlayer;
import interfaceDoComponente.PortPlayerOutbox;
import interfaceDoComponente.PortServer;
import interfaceDoComponente.PortServerOutbox;

public class InternalPlayerProxy implements InterfaceProxyServer {
	
	protected PortServer portServer;
	protected PortPlayer portPlayer;
	
	public void setPortServer (PortServer aPortServer) {
		portServer = aPortServer;
	}
	
	public void setPortPlayer (PortPlayer aPortPlayer) {
		portPlayer = aPortPlayer;
	}
	
	@Override
	public void receber(Throw arg0) {
		PortPlayerOutbox playerOutbox = (PortPlayerOutbox) portPlayer.getOutbox();
		PortServerOutbox serverOutbox = (PortServerOutbox) portServer.getOutbox();
		if (arg0 instanceof State) {
			playerOutbox.updateState((State) arg0);
		} else {
			CommunicationKind kind = ((CommunicationContainer) arg0).getKind();
			if (kind == CommunicationKind.disconnectionNotification) {
				serverOutbox.disconnect();		//		disconnectNG()
			} 
			playerOutbox.comunicar((CommunicationContainer) arg0);
		}
	}

	@Override
	public void receberMensagem(String arg0) {
		PortPlayerOutbox outbox = (PortPlayerOutbox) portPlayer.getOutbox();
		outbox.receberMensagem(arg0);
	}

	@Override
	public void iniciar(int arg0) {
		// no action - the existence of this operation is a requirement of Netgames
	}


}
