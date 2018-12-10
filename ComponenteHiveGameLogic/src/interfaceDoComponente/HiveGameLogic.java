package interfaceDoComponente;

import cip.ComponentInterface;
import estruturaInterna.Colmeia;

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
