package interfaceDoComponente;

import cip.InterfacePort;
import estruturaInterna.InternalManager;

public class PortLogic  extends InterfacePort{

	
	protected InternalManager internalReference;
	
	public void initialize() {
		outbox = new PortLogicOutbox();
	}

	
	public PortLogic(String argId) {
		id = argId;
	}

	public void setInternalReference (InternalManager argB) {
		internalReference = argB;
	}

}