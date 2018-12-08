package interfaceDoComponente;

import InterfacesComuns.InterfaceProxyServer;
import InterfacesComuns.Throw;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import cip.InterfacePort;
import estruturaInterna.InternalManagerProxy;

public class PortServer extends InterfacePort implements InterfaceProxyServer, OuvidorProxy {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected InternalManagerProxy internalReference;

	public PortServer(String string) {
		id = string;
	}
	
	public void setInternalReference(InternalManagerProxy internalObject) {
		internalReference = internalObject;
		((PortServerOutbox) outbox).connectToNetgames(this);
	}
	
	public void selfDestruct() {
		internalReference.selfDestruct();
		this.setInternalReference(null);		
		outbox = null;		
	}

	@Override
	public void initialize() {
		outbox = new PortServerOutbox();
		((PortServerOutbox) outbox).initialize();
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		this.iniciar(posicao);
	}



	@Override
	public void receberJogada(Jogada jogada) {
		this.receber((Throw) jogada);
	}
	
	@Override
	public void finalizarPartidaComErro(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receber(Throw object) {
		internalReference.receber(object);	
		
	}

	@Override
	public void receberMensagem(String message) {
		internalReference.receberMensagem(message);
		
	}

	@Override
	public void iniciar(int order) {
		internalReference.iniciar(order);
	}


}
