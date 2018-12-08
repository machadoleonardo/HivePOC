package cip;

import java.util.ArrayList;

public abstract class ComponentInterface {
	
	protected String id;
	protected ArrayList<InterfacePort> ports = new ArrayList<InterfacePort> ();
	
	public abstract void initialize();
	
	public String getId()  {
		return id;
	}

	public InterfacePort getPort (String portId)  {
		for (int i = 0; i < ports.size(); ++i) {
			InterfacePort aPort = ports.get(i);
			if (aPort.getId() == portId) return aPort;
			}
		return null;
	}

	public void conectar(InterfacePort externalPort , String portId) {
		InterfacePort myPort = this.getPort(portId);
		if (myPort != null) myPort.conectar(externalPort);
	}

	public void connectNonComponent(Object externalReference, String portId) {
		InterfacePort myPort = this.getPort(portId);
		if (myPort != null) myPort.connectNonComponent(externalReference);		
	}

	public void desconectar(InterfacePort externalPort, String portId) {
		InterfacePort myPort = this.getPort(portId);
		if (myPort != null) myPort.desconectar(externalPort);	
	}

	public void desconectar(String portId) {
		InterfacePort myPort = this.getPort(portId);
		if (myPort != null) myPort.desconectar();		
	}

}