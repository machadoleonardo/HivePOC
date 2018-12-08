package interfaceDoComponente;


import ClassesComuns.Jogador;
import ClassesComuns.Lance;
import InterfacesComuns.InterfaceProxyPlayer;
import cip.InterfacePort;
import estruturaInterna.InternalServerProxy;

public class PortPlayer extends InterfacePort implements InterfaceProxyPlayer {
	
	protected InternalServerProxy internalReference;
	
	public PortPlayer(String string) {
		id = string;
	}
	
	public void setInternalReference(InternalServerProxy internalObject) {
		internalReference = internalObject;
	}
	
	@Override
	public void initialize() {
		outbox = new PortPlayerOutbox();
	}

	@Override
	public void confirmarConexao() {
		internalReference.confirmarConexao();
	}

	@Override
	public boolean conectar(String arg0) {
		return internalReference.conectar(arg0);
	}

	@Override
	public void desconectar(String arg0) {
		internalReference.desconectar(arg0);
	}

	@Override
	public void getJogadoresConectados() {
		internalReference.getJogadoresConectados();
	}

	@Override
	public void desistir(String arg0) {
		internalReference.desistir(arg0);
	}

	@Override
	public void lance(Lance arg0) {
		internalReference.lance(arg0);
	}

	@Override
	public void enviarMensagem(String arg0) {
		internalReference.enviarMensagem(arg0);
	}

	@Override
	public void iniciar(Jogador arg0) {
		internalReference.iniciar(arg0);
	}

}
