package interfaceDoComponente;

import ClassesComuns.CommunicationContainer;
import ClassesComuns.Estado;
import InterfacesComuns.InterfacePlayerProxy;
import cip.StandardPortOutbox;
import interfaceDoComponente.PortPlayerProxy;

public class PortPlayerOutbox extends StandardPortOutbox implements InterfacePlayerProxy {

	@Override
	public void comunicar(CommunicationContainer arg0) {
		((PortPlayerProxy) externalPort).comunicar(arg0);
	}

	@Override
	public void receberMensagem(String arg0) {
		((PortPlayerProxy) externalPort).receberMensagem(arg0);
	}

	@Override
	public void updateState(Estado arg0) {
		((PortPlayerProxy) externalPort).updateState(arg0);
	}

}
