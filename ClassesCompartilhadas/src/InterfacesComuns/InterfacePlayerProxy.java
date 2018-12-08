package InterfacesComuns;

import ClassesComuns.CommunicationContainer;
import ClassesComuns.State;

public interface InterfacePlayerProxy {
	
	public void updateState(State state);
	
	public void receberMensagem(String message);
	
	public void comunicar(CommunicationContainer notification);

}
