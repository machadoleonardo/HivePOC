package interfaceDoComponente;

import cip.ComponentInterface;
import estruturaInterna.InternalManagerProxy;
import estruturaInterna.InternalServerProxy;

public class HiveManagerProxy extends ComponentInterface {

	public HiveManagerProxy(String argId) {
		id = argId;
	}

	@Override
	public void initialize() {
		PortManager2 portManager = new PortManager2("manager");
		portManager.initialize();
		PortServer portServer = new PortServer("server");
		portServer.initialize();
		ports.add(portManager);
		ports.add(portServer);
		InternalManagerProxy managerProxy = new InternalManagerProxy();
		InternalServerProxy serverProxy = new InternalServerProxy();
		managerProxy.setPortManager(portManager);
		managerProxy.setPortServer(portServer);
		serverProxy.setPortServer(portServer);
		portManager.setInternalReference(serverProxy);
		portServer.setInternalReference(managerProxy);	
		portManager.setComponentRoot(this);
	}

	public void selfDestruct() {
		for (int i=0; i<ports.size(); i++) {
			if (ports.get(i) instanceof PortServer) {
				PortServer port = (PortServer) ports.get(i);
				port.selfDestruct();
			}
		}
		ports.remove(1);
		ports.remove(0);
	}

	
}
