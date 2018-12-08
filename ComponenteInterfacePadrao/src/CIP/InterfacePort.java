package cip;

public abstract class InterfacePort {
	
	protected String id;
	protected PortOutbox outbox;
	
public abstract void initialize();
	
	public String getId()  {
		return id;
	}
	
	public PortOutbox getOutbox() {
		return outbox;
	}
	
	public void conectar(InterfacePort externalPort) {
		if ((outbox != null) && !(outbox instanceof NonComponentPortOutbox)) outbox.connect(externalPort);
	}
	
	public void connectNonComponent(Object externalReference) {
		if ((outbox != null) && (outbox instanceof NonComponentPortOutbox)) outbox.connectNonComponent(externalReference);		
	}
	
	public void desconectar(InterfacePort externalPort) {
		if (outbox != null) outbox.disconnect(externalPort);
	}
	
	public void desconectar() {
		if (outbox != null) outbox.disconnect();
	}
}