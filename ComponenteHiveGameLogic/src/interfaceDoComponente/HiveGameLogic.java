package interfaceDoComponente;

import ClassesComuns.Colmeia;
import cip.ComponentInterface;

public class HiveGameLogic extends ComponentInterface {
	
	public HiveGameLogic(String argId) {
		id = argId;
	}

	@Override
	public void initialize() {
		PortManager myPort = new PortManager("port");
		Colmeia myBoard = new Colmeia();
		myPort.setInternalReference(myBoard);
		ports.add(myPort);
	}

}
