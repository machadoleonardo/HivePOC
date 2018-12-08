package InterfacesComuns;

import ClassesComuns.Jogador;
import ClassesComuns.Lance;

public interface InterfaceManagerProxy {


    public void enviarMensagem(String message);
	
	public void conectar(String playerId);
	
	public void getJogadoresConectados();
	
	public void desconectar(String playerId);
	
	public void desistir(String playerId);
	
	public void iniciar(Jogador player);
	
	public void makeMove(Lance move);

}