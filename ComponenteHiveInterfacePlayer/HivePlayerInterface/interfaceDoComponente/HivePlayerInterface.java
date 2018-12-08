package interfaceDoComponente;

import javax.swing.JFrame;

import cip.ComponentInterface;
import estruturaInterna.HiveGUI;

public class HivePlayerInterface extends ComponentInterface {

	public HivePlayerInterface(String argId) {
		id = argId;
	}

	@Override
	public void initialize() {
		PortPlayerProxy port = new PortPlayerProxy("port");
		port.initialize();
		ports.add(port);
		HiveGUI playerWindow = new HiveGUI();
		port.setInternalReference(playerWindow);
		playerWindow.setPlayerPort(port);
		playerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playerWindow.setVisible(true);
	}

}
