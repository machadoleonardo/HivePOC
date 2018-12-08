
package ClassesComuns;

import br.ufsc.inf.leobr.cliente.Jogada;
public class Gerenciador implements Jogada {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Jogador jogador1;
	protected Jogador jogador2;
	protected Colmeia colmeia;
	protected int rodada;
	protected boolean partidaEmAndamento;
	protected boolean conectado;
	protected Jogador vencedor;
	protected AtorJogador atorJogador;
	protected AtorNetGames rede;
	protected int buffer;
	
	
	public Gerenciador(AtorNetGames rede, AtorJogador atorJogador ) 
	{
		
		this.rede = new AtorNetGames(atorJogador);
		this.atorJogador = atorJogador;
		jogador1 = new Jogador(1);
		jogador1.setApelido(atorJogador.getNome());
		jogador2 = new Jogador(2);
		rodada = 0;
		partidaEmAndamento = false;
		conectado = false;
		colmeia = new Colmeia();
		
	}



	public Jogador getJogador1() {
		return jogador1;
	}



	public void setJogador1(Jogador jogador1) {
		this.jogador1 = jogador1;
	}



	public Jogador getJogador2() {
		return jogador2;
	}



	public void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}



	public Colmeia getColmeia() {
		return colmeia;
	}



	public void setColmeia(Colmeia colmeia) {
		this.colmeia = colmeia;
	}



	public int getRodada() {
		return rodada;
	}



	public void setRodada(int rodada) {
		this.rodada = rodada;
	}



	public boolean isPartidaEmAndamento() {
		return partidaEmAndamento;
	}



	public void setPartidaEmAndamento(boolean partidaEmAndamento) {
		this.partidaEmAndamento = partidaEmAndamento;
	}



	public boolean isConectado() {
		return conectado;
	}



	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}



	public Jogador getVencedor() {
		return vencedor;
	}



	public void setVencedor(Jogador vencedor) {
		this.vencedor = vencedor;
	}



	public AtorJogador getAtorJogador() {
		return atorJogador;
	}



	public void setAtorJogador(AtorJogador atorJogador) {
		this.atorJogador = atorJogador;
	}



	public AtorNetGames getRede() {
		return rede;
	}



	public void setRede(AtorNetGames rede) {
		this.rede = rede;
	}
	
	
	
	public void iniciarPartida() {
		
		rede.iniciarPartidaRede();
		
	}
	
	public void receberLanceRede(Lance umLance) {
		
		int x = umLance.getX();
		int y = umLance.getY();
		String z = umLance.getZ();
		int n = umLance.getN();
		atorJogador.receberMensagemRede(x, y, z,n);
		
	}
	
	
	
	public Jogador jogadorDaVez() {
		
			if(rede.getInterfaceGrafica().getNome().equals(this.getJogador1().getApelido()))
			{return this.getJogador1();}
		else return this.getJogador2();

		
	}
	
	
	public String executarLance(int x, int y, int origX, int origY, int nroPeca) {
		String notif = "";
		if( this.partidaEmAndamento ) {
			
			if(rede.getEhMinhaVez()){
				
				int rodadaAtual = this.getRodada();
				String corOp = this.getCorOponente();
				Colmeia minhaColmeia = this.getColmeia();
				boolean temAbelha = minhaColmeia.temAbelha(corOp);
				Peca posicaoDestino = this.getColmeia().getPeca(x, y);
				Peca posicaoOrigem = this.getColmeia().getPeca(origX, origY);
				boolean colmeiaVazia = minhaColmeia.colmeiaVazia();
				boolean temPecaVizinha = minhaColmeia.temPecaVizinha(x, y);
				boolean temPecaVizinhaOutroTime = minhaColmeia.temPecaVizinhaOutroTime(x, y, corOp);
				int nroPecaDestino = posicaoDestino.getNroPeca();
				
				if(rodadaAtual==0) { //primeira rodada de cada jogador
					if(nroPeca==12) {notif="Voce nao pode selecionar peca do adversario"; return notif;}
					if(nroPecaDestino != -1) { notif = "Posicao ocupada."; return notif;}
					if( ! colmeiaVazia && ! temPecaVizinha ){ notif = "Colmeia desconectada."; return notif;}
							
							Peca p = this.jogadorDaVez().getPecas(nroPeca);
							minhaColmeia.setPeca(x, y, p);
							String tipo = p.getTipo()+" "+this.getAtorJogador().getNome();
							this.getAtorJogador().modificaGrafico(x, y, tipo);
							rede.enviarJogada(x, y, p.getTipo()+" "+this.getAtorJogador().getNome(), nroPeca);
							p.setEmJogo(true);
							}
				
				
				else { //rodada > 0
						
					if(nroPeca == 12 && rodadaAtual<4 && ! temAbelha) {notif = "nao pode mover enquanto nao inserir a abelha"; return notif;}
					if(nroPeca !=0 && rodadaAtual==3 && ! temAbelha) {notif="insira a abelha"; return notif;}
														
				if(nroPeca != 12) { // eh uma insercao
											
					if(this.jogadorDaVez().getPecas(nroPeca).isEmJogo()) {notif="Peca em jogo."; return notif;} 
					if(this.getColmeia().getPeca(x, y).getNroPeca()!= -1) { notif = "Posicao ocupada."; return notif;} 
					if(! minhaColmeia.colmeiaVazia() && ! minhaColmeia.temPecaVizinha(x,y)){ notif = "Colmeia desconectada."; return notif;}
					if( temPecaVizinhaOutroTime) {notif = "Na insercao nao pode tocar na peca do adversario"; return notif;}
																	
						Peca p = this.jogadorDaVez().getPecas(nroPeca);
						minhaColmeia.setPeca(x, y, p);
						this.getAtorJogador().modificaGrafico(x, y, p.getTipo()+" "+this.getAtorJogador().getNome());
						rede.enviarJogada(x, y, p.getTipo()+" "+this.getAtorJogador().getNome(), nroPeca);
						this.jogadorDaVez().getPecas(nroPeca).setEmJogo(true);
											
					} else {
											
				if(nroPeca==12) { // eh uma movimentacao
												
					if(! posicaoOrigem.getCor().equals(this.jogadorDaVez().getCor())) {notif = "Nao pode mover peca do adversario"; return notif;}
												
					if(posicaoDestino.getNroPeca()!= -1) { notif = "Posicao ocupada."; return notif;}
														
					if(! colmeiaVazia && ! temPecaVizinha ){ notif = "Colmeia desconectada."; return notif;}
	
					if(! minhaColmeia.verificaDistancia(posicaoOrigem, x, y, origX, origY)) {notif = "Passos alem do permitido."; return notif;}
																
						this.getColmeia().setPeca(x, y, posicaoOrigem);
						this.getColmeia().limpaPosicao(origX, origY);
						Peca p = new Peca();
						this.getAtorJogador().modificaGrafico(x, y, posicaoOrigem.getTipo()+" "+this.getAtorJogador().getNome());
						this.getAtorJogador().modificaGrafico(origX, origY, p.getTipo());
						rede.enviarJogadaBuffer(origX, origY, p.getTipo(), p.getNroPeca());
						rede.enviarJogada(x, y, posicaoOrigem.getTipo()+" "+this.getAtorJogador().getNome(), posicaoOrigem.getNroPeca());
		}}}
			
			notif="Jogada realizada com sucesso";
			if(minhaColmeia.abelhaCercada(corOp)){notif="Abelha cercada. Voce ganhou o jogo."; return notif;}
				if(minhaColmeia.abelhaPropriaCercada(corOp)){notif="Abelha cercada. Voce perdeu o jogo."; return notif;}
			this.rodada++;
			this.atualizaVez();}
						
							
			else {notif = "aguarde sua vez";}}
		
		else {notif = "nao conectado";}
		
		return notif;
	}
	

public void atualizaVez() {
	
	if(this.getJogador1().isDaVez()) {
		this.getJogador1().setDaVez(false);
		this.getJogador2().setDaVez(true);} else {
		this.getJogador2().setDaVez(false);
		this.getJogador1().setDaVez(true);}	}
	


public String getCorOponente() {
	Jogador oponente;
	if(this.jogadorDaVez().equals(this.getJogador1())) {oponente=this.getJogador2();} else {oponente=this.getJogador1();}
	String corOp = oponente.getCor();
	return corOp;}



public String finalizarPartida() {
	String notif = "";
	if(this.isConectado()){
	if(this.isPartidaEmAndamento()) {
		this.getRede().finalizarPartidaComErro("partida encerrada pelo usuario");
		this.getColmeia().limparColmeia();
		this.setPartidaEmAndamento(false);
		this.getAtorJogador().limparTab();
		notif = "Partida finalizada.";
		
	} else {notif = "Nao ha partida em andamento.";}} else {notif = "Nao esta conectado.";}
	
	return notif;
}



public String reiniciarPartida() {
	String notif = "";
	if(this.isPartidaEmAndamento()) {
		
		this.getRede().finalizarPartida();
		notif = "Partida finalizada.";
		
	} else {notif = "Nao ha partida em andamento.";}
	
	this.iniciarPartida();
	
	return notif;
	
}









}





