package InterfacesComuns;

public interface InterfaceNetgamesManager {
	
	public void enviar(Throw object);
	
	public void conectar(String requesterId);
	
	public String getIdOponente();
	
	public void desconectar();
	
	public void enviarMensagem(String message);

}
