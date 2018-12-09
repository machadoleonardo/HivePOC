package ClassesComuns;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Peca implements Jogada {
	protected int passosMaximo;
	protected boolean pecaAcima;
	protected boolean cercada;
	protected boolean emJogo;
	protected boolean bloqueada;
	protected String tipo;
	protected boolean podeSeSobrepor;
	protected String cor;
	protected int nroPeca;


public Peca() {
	
	this.nroPeca = -1; // posicao vazia
	this.passosMaximo = 0;
	this.pecaAcima = false;
	this.cercada = false;
	this.emJogo = false;
	this.bloqueada = false;
	this.tipo = "";
	this.podeSeSobrepor = false;
	this.cor = "";
	
}
	
	
	public Peca(String tipo, String cor, int numero) {
		
		this.tipo = tipo;
		this.cor = cor;
		this.nroPeca = numero;
		this.passosMaximo = 1;
		this.pecaAcima = false;
		this.cercada = false;
		this.emJogo = false;
		this.bloqueada = false;
		this.podeSeSobrepor = false;
		
		if(tipo.contains("grilo")||tipo.contains("aranha")) {this.passosMaximo=44;}
		if(tipo.contains("besouro")){this.podeSeSobrepor = true;}
	}




	public int getPassosMaximo() {
		return passosMaximo;
	}




	public void setPassosMaximo(int passosMaximo) {
		this.passosMaximo = passosMaximo;
	}




	public boolean isPecaAcima() {
		return pecaAcima;
	}




	public void setPecaAcima(boolean pecaAcima) {
		this.pecaAcima = pecaAcima;
	}




	public boolean isCercada() {
		return cercada;
	}




	public void setCercada(boolean cercada) {
		this.cercada = cercada;
	}




	public boolean isEmJogo() {
		return emJogo;
	}




	public void setEmJogo(boolean emJogo) {
		this.emJogo = emJogo;
	}




	public boolean isBloqueada() {
		return bloqueada;
	}




	public void setBloqueada(boolean bloqueada) {
		this.bloqueada = bloqueada;
	}




	public String getTipo() {
		return tipo;
	}




	public void setTipo(String tipo) {
		this.tipo = tipo;
	}




	public boolean isPodeSeSobrepor() {
		return podeSeSobrepor;
	}




	public void setPodeSeSobrepor(boolean podeSeSobrepor) {
		this.podeSeSobrepor = podeSeSobrepor;
	}




	public String getCor() {
		return cor;
	}




	public void setCor(String cor) {
		this.cor = cor;
	}




	public int getNroPeca() {
		return nroPeca;
	}




	public void setNroPeca(int nroPeca) {
		this.nroPeca = nroPeca;
	}
	
	
	
	
}