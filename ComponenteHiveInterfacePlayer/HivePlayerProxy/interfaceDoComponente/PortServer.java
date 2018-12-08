package interfaceDoComponente;



import InterfacesComuns.InterfaceProxyServer;
import InterfacesComuns.Throw;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import cip.InterfacePort;
import estruturaInterna.InternalPlayerProxy;

public class PortServer extends InterfacePort implements InterfaceProxyServer, OuvidorProxy {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected InternalPlayerProxy internalReference;
	protected boolean serverConnected = false;


	public PortServer(String string) {
		id = string;
	}
	
	public void setInternalReference (InternalPlayerProxy internalObject) {
		internalReference = internalObject;
		((PortServerOutbox) outbox).connectToNetgames(this);
	}

	@Override
	public void initialize() {
		outbox = new PortServerOutbox();
		((PortServerOutbox) outbox).initialize();
	}

	@Override
	public void receber(Throw arg0) {
		internalReference.receber(arg0);
	}

	@Override
	public void receberMensagem(String msg) {
		internalReference.receberMensagem(msg);
	}
	
	public void setConnected (boolean value) {
		serverConnected = value;
	}

	public boolean getConnected () {
		return serverConnected;
	}

	@Override
	public void iniciar(int arg0) {
		this.setConnected(true);
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

	
	



}
