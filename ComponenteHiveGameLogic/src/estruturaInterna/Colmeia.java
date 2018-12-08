package estruturaInterna;

import javax.swing.JOptionPane;

import ClassesComuns.Jogador;
import ClassesComuns.Lance;
import ClassesComuns.Partida;
import ClassesComuns.Peca;
import ClassesComuns.State;

// Game.gameCode
// 1 - game not started
// 2 - game finished with quitting
// 3 - irregular move (game in progress)
// 4 - next player (game in progress)
// 5 - game with winner
// 6 - game tied

public class Colmeia {
	
	protected Peca[][]tabuleiro;
	final static int BSIZE = 24;
		
	public Colmeia() {
		
		tabuleiro = new Peca[24][24];
		
		for (int i =0;i<BSIZE;i++) {
			for (int j=0; j<BSIZE;j++){
			tabuleiro[i][j] = new Peca();
		}}
				
	}
	
	
	public void setPeca(int x, int y, Peca p) {
		
		tabuleiro[x][y] = p;
		
	}
	
	public Peca getPeca(int x, int y) {
		
		return tabuleiro[x][y];
		
	}
	
	public void limpaPosicao(int x, int y) {
		
		Peca p = new Peca();
		tabuleiro[x][y] = p;		
	}
	
	
	public void limparColmeia() {
		for (int i=0;i<BSIZE;i++) {
			for (int j=0;j<BSIZE;j++) {
				Peca p = new Peca();
				tabuleiro[i][j] = p;
		
	}}}
	
	public int getBsize() {
		
		return BSIZE;
	}
	
	public boolean colmeiaVazia() {
		
		boolean retorno = true;
		for (int i=0;i<BSIZE;i++) {
			for (int j=0;j<BSIZE;j++) {
				Peca p = this.getPeca(i,j);
				if(p.getNroPeca()!=-1) {retorno = false; return retorno;}
				else {retorno = true;}}}
	return retorno;	
	}
	
	public boolean temPecaVizinha(int i, int j) {
		 
		
			boolean retorno = false;
			if(i%2==0){
					if(		this.getPeca(i+1,j).getNroPeca()==-1&&
							this.getPeca(i-1,j).getNroPeca()==-1&&
							this.getPeca(i,j+1).getNroPeca()==-1&&
							this.getPeca(i,j-1).getNroPeca()==-1&&
							this.getPeca(i-1,j-1).getNroPeca()==-1&&
							this.getPeca(i+1,j-1).getNroPeca()==-1
					){retorno = false;} else {retorno = true;}} 
			else {
						
						
						if(		this.getPeca(i+1,j).getNroPeca()==-1&&
								this.getPeca(i-1,j).getNroPeca()==-1&&
								this.getPeca(i,j+1).getNroPeca()==-1&&
								this.getPeca(i,j-1).getNroPeca()==-1&&
								this.getPeca(i-1,j+1).getNroPeca()==-1&&
								this.getPeca(i+1,j+1).getNroPeca()==-1
						){retorno = false;}	else {retorno = true;}
				
					}
		return retorno;	}
	
	
	public boolean abelhaCercada(String corOp){
		
		 boolean retorno = false;
		
		 for (int i=0;i<BSIZE;i++) {
			for (int j=0;j<BSIZE;j++) {
				Peca p = this.getPeca(i,j);
				if(p.getNroPeca()==0) {
					if(cercada(i,j)) {if(p.getCor().equals(corOp)){retorno=true; return retorno;}}
					else {retorno = false;}}}}
	return retorno;	
	 
 }
 
 public boolean abelhaPropriaCercada(String corOp){
		
		 boolean retorno = false;
		
		 for (int i=0;i<BSIZE;i++) {
			for (int j=0;j<BSIZE;j++) {
				Peca p = this.getPeca(i,j);
				if(p.getNroPeca()==0) {
					if(cercada(i,j)) {if(! p.getCor().equals(corOp)){retorno=true; return retorno;}}
					else {retorno = false;}}}}
	return retorno;	
	 
}
 
public boolean cercada (int i, int j) {

	 
		boolean retorno = false;
		if(i%2==0){
				if(		this.getPeca(i+1,j).getNroPeca()!=-1&&
						this.getPeca(i-1,j).getNroPeca()!=-1&&
						this.getPeca(i,j+1).getNroPeca()!=-1&&
						this.getPeca(i,j-1).getNroPeca()!=-1&&
						this.getPeca(i-1,j-1).getNroPeca()!=-1&&
						this.getPeca(i+1,j-1).getNroPeca()!=-1
				){retorno = true;} else {retorno = false;}} 
		else {
					
					
					if(		this.getPeca(i+1,j).getNroPeca()!=-1&&
							this.getPeca(i-1,j).getNroPeca()!=-1&&
							this.getPeca(i,j+1).getNroPeca()!=-1&&
							this.getPeca(i,j-1).getNroPeca()!=-1&&
							this.getPeca(i-1,j+1).getNroPeca()!=-1&&
							this.getPeca(i+1,j+1).getNroPeca()!=-1
					){retorno = true;}	else {retorno = false;}
			
				}
	return retorno;	}
	
	
public boolean temAbelha(String corOp) {
	
	boolean retorno = false;
	for (int i=0;i<BSIZE;i++) {
		for (int j=0;j<BSIZE;j++) {
			Peca p = this.getPeca(i,j);
			if(p.getNroPeca()==0 && ! p.getCor().equals(corOp)) {retorno = true; return retorno;}
			else {retorno = false;}}}
return retorno;	
}


public boolean temPecaVizinhaOutroTime(int i, int j, String corOp) {
	 

	boolean retorno = false;
	
	if(i%2==0){
				if(		this.getPeca(i+1,j).getCor().contains(corOp)||
						this.getPeca(i-1,j).getCor().equals(corOp)||
						this.getPeca(i,j+1).getCor().equals(corOp)||
						this.getPeca(i,j-1).getCor().equals(corOp)||
						this.getPeca(i-1,j-1).getCor().equals(corOp)||
						this.getPeca(i+1,j-1).getCor().equals(corOp))
				{retorno = true;}
				else {retorno = false;}}else{
					
					if(		this.getPeca(i+1,j).getCor().equals(corOp)||
							this.getPeca(i-1,j).getCor().equals(corOp)||
							this.getPeca(i,j+1).getCor().equals(corOp)||
							this.getPeca(i,j-1).getCor().equals(corOp)||
							this.getPeca(i-1,j+1).getCor().equals(corOp)||
							this.getPeca(i+1,j+1).getCor().equals(corOp))
					{retorno = true;}
					else {retorno = false;}
					
			
				}
	return retorno;	}
	
	
public boolean verificaDistancia(Peca p, int x, int y, int origX, int origY){
	 
	 boolean result = false;
	 int distMax = p.getPassosMaximo();
	 JOptionPane.showMessageDialog(null, "passos maximos da peca: "+distMax);
	 int distPercX;
	 int distPercY; 
	 if(x>origX) {distPercX = x-origX;} else{distPercX = origX-x;}
	 if(y>origY){distPercY = y-origY;} else{distPercY = origY-y;}
	 
	 if(
			 distPercX == 0 && distPercY<=distMax ||
			 distPercY == 0 && distPercX<=distMax ||
			 origX-x == distMax && origY-y == distMax ||
			 x-origX == distMax && y-origY == distMax ||
			 origX-x == distMax && origY-y == -distMax ||
			 x-origX == distMax && y-origY == -distMax ||
			 (distMax>1&&x-origX+y-origY<=distMax&& x-origX+y-origY >= 0) ||
			 (distMax>1&&x-origX+y-origY>-distMax && x-origX+y-origY < 0)
		)
	 {result = true;} else {result = false;}
	 
	 return result;
}


public Partida tratarLance(Partida game, Lance lance) {
	// TODO Auto-generated method stub
	return null;
}


public Partida setInitialState(Jogador player1, Jogador player2) {
	// TODO Auto-generated method stub
	return null;
}


public Partida terminarRetirda(Partida game, Jogador player) {
	// TODO Auto-generated method stub
	return null;
}
}
