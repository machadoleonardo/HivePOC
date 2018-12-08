package InterfacesComuns;

import ClassesComuns.Jogador;
import ClassesComuns.Lance;

public interface InterfaceProxyPlayer {
	
	public void enviarMensagem(String message);
	
	public boolean conectar(String playerId);
	
	public void getJogadoresConectados();
	
	public void desconectar(String playerId);
	
	public void desistir(String playerId);
	
	public void iniciar(Jogador player);
	
	public void lance(Lance move);
	
	public void confirmarConexao();

}
