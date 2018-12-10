package InterfacesComuns;

import ClassesComuns.CommunicationContainer;
import ClassesComuns.Estado;

public interface InterfaceProxyManager {

	
	public void enviarMensagem(String mensagem);

	boolean conectar(String n);

	public void updateState(Estado state);
	
	public void comunicar(CommunicationContainer notification);
	
	public void selfDestruct();

}