package interfaceDoComponente;


import ClassesComuns.CommunicationContainer;
import ClassesComuns.Estado;
import InterfacesComuns.InterfaceProxyManager;
import cip.StandardPortOutbox;

public class PortManagerProxyOutbox extends StandardPortOutbox implements InterfaceProxyManager {

	@Override
	public boolean conectar(String n) {
		return ((InterfaceProxyManager) externalPort).conectar(n);
	}

	@Override
	public void updateState(Estado state) {
		((InterfaceProxyManager) externalPort).updateState(state);
		
	}

	@Override
	public void comunicar(CommunicationContainer notification) {
		((InterfaceProxyManager) externalPort).comunicar(notification);
		
	}

	@Override
	public void selfDestruct() {
		((InterfaceProxyManager) externalPort).selfDestruct();
		
	}

	@Override
	public void enviarMensagem(String mensagem) {
		((InterfaceProxyManager) externalPort).enviarMensagem(mensagem);
		
	}

	
}