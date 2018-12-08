package ClassesComuns;

import javax.swing.JOptionPane;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public class AtorNetGames implements OuvidorProxy {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Proxy proxy;
	protected AtorJogador interfaceGrafica;
	protected boolean ehMinhaVez;
	
	
	
	
	public AtorNetGames (AtorJogador interfaceGrafica) {
		super();
		this.interfaceGrafica = interfaceGrafica;
		proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
		ehMinhaVez = false;
		}
	
	
	public void conectar(String nome, String servidor){
		
		try {
			proxy.conectar(servidor, nome);
		} catch (JahConectadoException e) {
			JOptionPane.showMessageDialog(interfaceGrafica, e.getMessage());
			e.printStackTrace();
		} catch (NaoPossivelConectarException e) {
			JOptionPane.showMessageDialog(interfaceGrafica, e.getMessage());
			e.printStackTrace();
		} catch (ArquivoMultiplayerException e) {
			JOptionPane.showMessageDialog(interfaceGrafica, e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	/* 
	 * Inicia partida entre cliente e servidor
	 */
	
	public void iniciarPartidaRede() {
		
		try {
			proxy.iniciarPartida(2);
			
			} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(interfaceGrafica, e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void iniciarNovaPartida(Integer posicao) {
		
		if(posicao==1) {ehMinhaVez=true;} else {ehMinhaVez=false;}
		interfaceGrafica.iniciarPartidaRede(ehMinhaVez);
		
		
	}

	
	public void enviarJogadaBuffer (int x, int y, String z, int n) {
		if(ehMinhaVez){
		Lance umLance = new Lance(x, y, z, n);
		try {
			proxy.enviaJogada(umLance);
			//ehMinhaVez=false;
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(interfaceGrafica, e.getMessage());
			e.printStackTrace();
		}
	}
	}
	
	
	public void enviarJogada (int x, int y, String z, int n) {
		if(ehMinhaVez){
		Lance umLance = new Lance(x, y, z, n);
		try {
			proxy.enviaJogada(umLance);
			ehMinhaVez=false;
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(interfaceGrafica, e.getMessage());
			e.printStackTrace();
		}
	}
	}
	
	
	public AtorJogador getInterfaceGrafica() {
		
		return this.interfaceGrafica;
	}
	
	
	public void desconectar() {
		
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(interfaceGrafica, e.getMessage());
			e.printStackTrace();
		}
		
	
	}
	
	
	
	
	
/*
 * Mensagem enviada para todas as classes que
 * implementam o OuvidorProxy. Indica a vez do jogador.
 */
	


	@Override
	public void finalizarPartidaComErro(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receberMensagem(String msg) {
		
		
	}

	
	@Override
	public void receberJogada(Jogada jogada) {
		
		Lance umLance = (Lance) jogada;
		int x = umLance.getX();
		int y = umLance.getY();
		String z = umLance.getZ();
		int n = umLance.getN();
		
		
		interfaceGrafica.receberMensagemRede(x,y,z,n);
		ehMinhaVez=true;
	
	}
	
	

	
	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub
		
	}


	public String obterNomeAdversario() {
		String nome = "";
		if(ehMinhaVez) {
			nome = proxy.obterNomeAdversario(2);
		} else {
			nome = proxy.obterNomeAdversario(1);
		}
		return nome;
	}
	
	public boolean getEhMinhaVez() {
		
		return this.ehMinhaVez;
	}
	
	public void  finalizarPartida() {
		try {
			proxy.finalizarPartida();
		} catch (NaoConectadoException | NaoJogandoException e) {
			JOptionPane.showMessageDialog(interfaceGrafica, e.getMessage());
			e.printStackTrace();
		}
				
	}
	
	
}
