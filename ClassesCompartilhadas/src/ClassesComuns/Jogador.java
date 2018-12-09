package ClassesComuns;

import InterfacesComuns.Throw;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Jogador implements Throw, Jogada {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
protected String apelido;
protected String cor;
protected boolean daVez;
protected boolean vencedor;
protected Peca[] pecas;
int ordem;
protected int bufferInt;
protected Gerenciador umGerenciador;
protected boolean[] pecasEmJogo;
boolean abelha;
boolean formiga1;
boolean formiga2;
boolean formiga3;
boolean aranha1;
boolean aranha2;
boolean grilo1;
boolean grilo2;
boolean grilo3;
boolean besouro1;
boolean besouro2;


public Jogador(int ordem) {
	
	pecasEmJogo = new boolean[11];
	
	abelha = false;
	formiga1 = false;
	formiga2 = false;
	formiga3 = false;
	aranha1 = false;
	aranha2 = false;
	grilo1 = false;
	grilo2 = false;
	grilo3 = false;
	besouro1 = false;
	besouro2 = false;
	
	pecasEmJogo[0] = abelha;
	pecasEmJogo[1] = formiga1;
	pecasEmJogo[2] = formiga2;
	pecasEmJogo[3] = formiga3;
	pecasEmJogo[4] = aranha1;
	pecasEmJogo[5] = aranha2;
	pecasEmJogo[6] = grilo1;
	pecasEmJogo[7] = grilo2;
	pecasEmJogo[8] = grilo3;
	pecasEmJogo[9] = besouro1;
	pecasEmJogo[10] = besouro2;
	
	
	if(ordem == 1) {this.cor = "laranja";} else {this.cor = "branco";}
	
	
		Peca abelha = new Peca("abelha", cor, 0);
		Peca formiga1 = new Peca("formiga1", cor, 1);
		Peca formiga2 = new Peca("formiga2", cor, 2);
		Peca formiga3 = new Peca("formiga3", cor, 3);
		Peca aranha1 = new Peca("aranha1", cor, 4);
		Peca aranha2 = new Peca("aranha2", cor, 5);
		Peca grilo1 = new Peca("grilo1", cor, 6);
		Peca grilo2 = new Peca("grilo2", cor, 7);
		Peca grilo3 = new Peca("grilo3", cor, 8);
		Peca besouro1 = new Peca("besouro1", cor, 9);
		Peca besouro2 = new Peca("besouro2", cor, 10);
		
		pecas = new Peca[11];
			
		pecas[0] = abelha;
		pecas[1] = formiga1;
		pecas[2]= formiga2;
		pecas[3]= formiga3;
		pecas[4]= aranha1;
		pecas[5]= aranha2;
		pecas[6]= grilo1;
		pecas[7]= grilo2;
		pecas[8]= grilo3;
		pecas[9]= besouro1;
		pecas[10]= besouro2;
		
		
	} 
		

public int getOrdem() {
	
	return this.ordem;
}
		

public String getApelido() {
	return apelido;
}



public void setApelido(String apelido) {
	this.apelido = apelido;
}



public String getCor() {
	return cor;
}



public void setCor(String cor) {
	this.cor = cor;
}



public boolean isDaVez() {
	return daVez;
}



public void setDaVez(boolean daVez) {
	this.daVez = daVez;
}



public Peca getPecas(int nro) {
	return pecas[nro];
}



public void carregarBufferInt(int i) {
	this.bufferInt= i;
	
}


public void setOrdem(int ordem) {
	this.ordem = ordem;
	
}


public boolean isVencedor() {
	return vencedor;
}


public void setVencedor(boolean vencedor) {
	this.vencedor = vencedor;
}





}
