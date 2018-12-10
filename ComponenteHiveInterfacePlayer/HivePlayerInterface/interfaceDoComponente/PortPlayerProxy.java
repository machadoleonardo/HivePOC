package interfaceDoComponente;

import ClassesComuns.CommunicationContainer;
import ClassesComuns.Estado;
import InterfacesComuns.InterfacePlayerProxy;
import cip.InterfacePort;
import estruturaInterna.HiveGUI;

public class PortPlayerProxy extends InterfacePort implements InterfacePlayerProxy {
	
	protected HiveGUI internalReference;
	
	public PortPlayerProxy(String argId) {
		id = argId;
	}

	public void setInternalReference(HiveGUI internalObject) {
		internalReference = internalObject;
	}

	@Override
	public void initialize() {
		outbox = new PortPlayerProxyOutbox();
	}

	@Override
	public void comunicar(CommunicationContainer arg0) {
		internalReference.comunicar(arg0);
	}

	@Override
	public void receberMensagem(String message) {
		internalReference.receberMensagem(message);
	}

	@Override
	public void updateState(Estado arg0) {
		internalReference.updateState(arg0);
	}

}
