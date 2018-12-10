package InterfacesComuns;

import ClassesComuns.CommunicationContainer;
import ClassesComuns.Estado;

public interface InterfacePlayerProxy {
	
	public void updateState(Estado state);
	
	public void receberMensagem(String message);
	
	public void comunicar(CommunicationContainer notification);

}
