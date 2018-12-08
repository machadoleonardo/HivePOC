package InterfacesComuns;

import ClassesComuns.Lance;

public interface InterfaceLogicManager {

	boolean temVencedor();

	/**
	 * 
	 * @param l
	 */
	void receberJogada(Lance lance);

	void executarLance();

}