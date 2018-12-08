package interfaceDoComponente;

import ClassesComuns.CommunicationContainer;
import ClassesComuns.State;
import InterfacesComuns.InterfaceProxyManager;
import cip.InterfacePort;
import estruturaInterna.InternalServerProxy;

public class PortManager2 extends InterfacePort implements InterfaceProxyManager {

	protected InternalServerProxy internalReference;
	private HiveManagerProxy componentRoot;
	
	public PortManager2(String string) {
		id = string;
	}
	
	public void setInternalReference(InternalServerProxy internalObject) {
		internalReference = internalObject;
	}
	
	public void setComponentRoot(HiveManagerProxy root) {
		componentRoot = root;
	}
	
	@Override
	public void initialize() {
		outbox = new PortManager2Outbox();
	}

	@Override
	public void comunicar(CommunicationContainer arg0) {
		internalReference.comunicar(arg0);
	}

	@Override
	public boolean conectar(String arg0) {
		return internalReference.conectar(arg0);
	}

	@Override
	public void enviarMensagem(String arg0) {
		internalReference.enviarMensagem(arg0);
	}

	@Override
	public void updateState(State arg0) {
		internalReference.updateState(arg0);
	}

	@Override
	public void selfDestruct() {
		internalReference.disconnectNG();
		this.desconectar();
		outbox = null;
		internalReference.selfDestruct();
		this.setInternalReference(null);	
		componentRoot.selfDestruct();
	}

}
