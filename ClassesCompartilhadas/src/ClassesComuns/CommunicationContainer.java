package ClassesComuns;

import InterfacesComuns.Throw;
import br.ufsc.inf.leobr.cliente.Jogada;

public class CommunicationContainer implements Throw, Jogada {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected CommunicationKind kind;	
	protected String content = "";
	
	public void setKind (CommunicationKind argKind) {
		kind = argKind;
	}
	
	public CommunicationKind getKind () {
		return(kind);
	}
	
	public void setContent (String argContent) {
		content = argContent;
	}
	
	public String getContent () {
		return(content);
	}

}
