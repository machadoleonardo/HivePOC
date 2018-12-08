package InterfacesComuns;

import ClassesComuns.CommunicationContainer;
import ClassesComuns.State;

public interface InterfaceProxyManager {

	
	public void enviarMensagem(String mensagem);

	boolean conectar(String n);

	public void updateState(State state);
	
	public void comunicar(CommunicationContainer notification);
	
	public void selfDestruct();

}