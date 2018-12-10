package interfaceDoComponente;

import java.util.List;

import InterfacesComuns.InterfaceNetgamesManager;
import InterfacesComuns.Throw;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import cip.NonComponentPortOutbox;


public class PortServerOutbox extends NonComponentPortOutbox implements InterfaceNetgamesManager {
	
	public void initialize() {
		Proxy proxy = new Proxy();
		this.connectNonComponent(proxy);

	}
	
	public void connectToNetgames(PortServer portServer) {
		((Proxy) externalReference).addOuvinte(portServer);
	}

	@Override
	public void conectar(String arg0) {
		try {
			((Proxy) externalReference).conectar("localhost", arg0);
		} catch (JahConectadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaoPossivelConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArquivoMultiplayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getIdOponente() {
		List<String> playerList = ((Proxy) externalReference).obterNomeAdversarios();
		return playerList.get(0);
	}

	@Override
	public void enviar(Throw arg0) {
		try {
			((Proxy) externalReference).enviaJogada((Jogada) arg0);
		} catch (NaoJogandoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void enviarMensagem(String arg0) {
		((Proxy) externalReference).receberMensagem(arg0);
	}

	@Override
	public void desconectar() {		//		disconnectNG()
		try {
			((Proxy) externalReference).desconectar();
		} catch (NaoConectadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
}
