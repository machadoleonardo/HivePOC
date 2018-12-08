package InterfacesComuns;

public interface InterfaceNetgamesPlayer {
	
	public void enviar(Throw object);
	
	public void conectar(String requesterId);
	
	public void desconectar();
	
	public void enviarMensagem(String message);
	
	public void iniciar();

}
