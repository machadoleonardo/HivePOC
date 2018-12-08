package InterfacesComuns;

import ClassesComuns.Lado;
import ClassesComuns.Peca;

public interface InterfaceInterfacePlayer {

	boolean estaConectado();

	/**
	 * 
	 * @param serv
	 * @param nick
	 */
	void conectar(String serv, String nick);

	/**
	 * 
	 * @param pOrig
	 * @param ladoOrig
	 * @param pDest
	 * @param LadoDest
	 */
	void informeLance(Peca pOrig, Lado ladoOrig, Peca pDest, Lado LadoDest);

}