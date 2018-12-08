package ClassesComuns;

import InterfacesComuns.Throw;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Lance implements Jogada, Throw {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x;
	int y;
	String z;
	Peca p;
	int n;
	

	public Lance(int x, int y, String z, int n) {
		super();
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.n = n;
	}


	
	public Lance(int x, int y, Peca p) {
		super();
		
		this.x = x;
		this.y = y;
		this.p = p;
	}

	
	
	public int getX () {
		return this.x;
		
	}
	
	public int getY() {
		return this.y;
	}
	
	
	public String getZ() {
		return this.z;
		
	}
	
	public int getN() {
		
		return this.n;
	}
	
	
	public Peca getP() {
		
		return this.p;
	}
		

}
