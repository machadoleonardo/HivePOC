package interfaceDoComponente;


import ClassesComuns.CommunicationContainer;
import ClassesComuns.State;
import InterfacesComuns.InterfaceProxyManager;
import cip.StandardPortOutbox;

public class PortManagerProxyOutbox extends StandardPortOutbox implements InterfaceProxyManager {

	@Override
	public boolean conectar(String n) {
		return ((InterfaceProxyManager) externalPort).conectar(n);
	}

	@Override
	public void updateState(State state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comunicar(CommunicationContainer notification) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selfDestruct() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enviarMensagem(String mensagem) {
		// TODO Auto-generated method stub
		
	}

	
}