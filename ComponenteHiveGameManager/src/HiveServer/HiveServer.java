package HiveServer;

import cip.InterfacePort;
import interfaceDoComponente.HiveGameLogic;
import interfaceDoComponente.HiveGameManager;
import interfaceDoComponente.HiveManagerProxy;

public class HiveServer {

	public static void main(String[] args) {
		HiveGameManager manager = new HiveGameManager("manager");
		manager.initialize();
		HiveGameLogic logic = new HiveGameLogic("logic");
		logic.initialize();
		HiveManagerProxy proxy = new HiveManagerProxy("proxy1");
		proxy.initialize();
		InterfacePort logicPort = logic.getPort("port");
		InterfacePort managerProxyPort = manager.getPort("proxy1");
		InterfacePort proxyManagerPort = proxy.getPort("manager");
		manager.conectar(logicPort, "logic");
		manager.conectar(proxyManagerPort, "proxy1");
		proxy.conectar(managerProxyPort, "manager");
	}

}
