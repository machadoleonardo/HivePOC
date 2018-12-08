package interfaceDoComponente;

import cip.ComponentInterface;
import estruturaInterna.InternalPlayerProxy;
import estruturaInterna.InternalServerProxy;

public class HivePlayerProxy extends ComponentInterface {

	public HivePlayerProxy(String argId) {
		id = argId;
	}

	@Override
	public void initialize() {
		PortPlayer portPlayer = new PortPlayer("player");
		portPlayer.initialize();
		PortServer portServer = new PortServer("server");
		portServer.initialize();
		ports.add(portPlayer);
		ports.add(portServer);
		InternalPlayerProxy playerProxy = new InternalPlayerProxy();
		InternalServerProxy serverProxy = new InternalServerProxy();
		playerProxy.setPortPlayer(portPlayer);
		playerProxy.setPortServer(portServer);
		serverProxy.setPortPlayer(portPlayer);
		serverProxy.setPortServer(portServer);
		portPlayer.setInternalReference(serverProxy);
		portServer.setInternalReference(playerProxy);		
	}

}
