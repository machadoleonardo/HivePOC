//import javax.swing.JFrame;

//import InternalStructure.TTT_GUI;
import cip.InterfacePort;
import interfaceDoComponente.HivePlayerInterface;
import interfaceDoComponente.HivePlayerProxy;

public class HivePlayer {

	public static void main(String[] args) {
		HivePlayerInterface playerInterface = new HivePlayerInterface("playerInterface");
		playerInterface.initialize();
		HivePlayerProxy proxy  = new HivePlayerProxy("proxy");
		proxy.initialize();
		InterfacePort portProxy = proxy.getPort("player");
		InterfacePort portPlayerInterface = playerInterface.getPort("port");
		proxy.conectar(portPlayerInterface, "player");
		playerInterface.conectar(portProxy, "port");
	}

}
	